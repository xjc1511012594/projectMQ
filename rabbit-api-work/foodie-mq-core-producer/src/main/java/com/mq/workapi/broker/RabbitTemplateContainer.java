package com.mq.workapi.broker;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.mq.workapi.Message;
import com.mq.workapi.MessageType;
import com.mq.workapi.convert.GenericMessageConverter;
import com.mq.workapi.convert.RabbitMessageConverter;
import com.mq.workapi.exception.MessageRunTimeException;
import com.mq.workapi.serializer.Serializer;
import com.mq.workapi.serializer.SerializerFactory;
import com.mq.workapi.serializer.impl.JacksonSerializerFactory;
import com.mq.workapi.service.MessageStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by xl on 2020/3/16.
 * $RabbitTemplateContainer池化封装
 * 1.  提高发送的效率
 * 2.  可以根据不同的需求定制化不同的RabbitTemplate，比如每一个topic 都有自己的routingKey原则
 */
@Slf4j
public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback{

    private Map<String,RabbitTemplate> rabbitMap= Maps.newConcurrentMap();

    private Splitter splitter= Splitter.on("#");

    private SerializerFactory serializerFactory=JacksonSerializerFactory.INSTANCE;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private MessageStoreService messageStoreService;

    public RabbitTemplate getTemplate(Message message) throws MessageRunTimeException {
        Preconditions.checkNotNull(message);
        String topic = message.getTopic();
        RabbitTemplate rabbitTemplate = rabbitMap.get(topic);
        if(rabbitTemplate!=null){
            return rabbitTemplate;
        }
        log.info("#RabbitTemplateContainer.getTemplate# topic: {} is not exists,create one",topic);

        //创建 rabbitTemplate对象
        RabbitTemplate newTemplate = new RabbitTemplate(connectionFactory);
        newTemplate.setExchange(topic);
        newTemplate.setRoutingKey(message.getRoutingKey());
        newTemplate.setRetryTemplate(new RetryTemplate());

        // 添加序列化反序列和converter对象
        Serializer serializer = serializerFactory.create();
        GenericMessageConverter gmc = new GenericMessageConverter(serializer);
        RabbitMessageConverter rmc = new RabbitMessageConverter(gmc);
        // rabbitTemplate 对象转换 序列化
        newTemplate.setMessageConverter(rmc);

        String messageType = message.getMessageType();
        if(!MessageType.PAPID.equals(messageType)){
            newTemplate.setConfirmCallback(this);
        }

        rabbitMap.putIfAbsent(topic,newTemplate);
        return rabbitMap.get(topic);
    }

    /**
     * 无论是 confirm 消息 还是 reliant 消息，发送消息后 broker都会调用confirm
     * */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        // 具体的消息应答
        List<String> strings = splitter.splitToList(correlationData.getId());
        String messageId = strings.get(0);
        long sendTime = Long.parseLong(strings.get(1));
        String messageType = strings.get(2);
        if(ack){
            // 当Broker 返回ACK成功时
            // 更新一下日志表里对应消息发送状态 SEND_OK
            // 当前消息类型为reliant 去数据库查找并进行更新
            if(MessageType.RELIANT.endsWith(messageType)){
                this.messageStoreService.success(messageId);
            }
            log.info("send message is OK, confirm messageId: {},sendTime {}",messageId,sendTime);
        }else{
            log.info("send message is OK, confirm messageId: {},sendTime {}",messageId,sendTime);
        }
    }

}
