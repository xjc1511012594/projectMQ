package com.rabbit.producer.component;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @author xulei
 * @date 2020-3-3 19:16
 */
@Component
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 确认消息的回调监听接口，用于确认消息是否被broker收到
     * */
    final RabbitTemplate.ConfirmCallback confirmCallback=new RabbitTemplate.ConfirmCallback() {
        /**
         * @param correlationData   作为唯一标识
         * @param ack               发送给broker是否成功
         * @param cause             失败的原因
         * */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("消息ACK结果:" + ack + ", correlationData: " + correlationData.getId());
        }
    };
    /**
     * @param message  消息
     * @param properties 额外的附加属性
     * @throws Exception
     * */
    public void send(Object message, Map<String,Object> properties) throws Exception{
        //消息构造
        MessageHeaders mhs = new MessageHeaders(properties);
        Message<?> msg = MessageBuilder.createMessage(message, mhs);
        //发送消息
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message) throws AmqpException {
                System.out.println("post to do :" + message);
                return message;
            }
        };
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.convertAndSend("exchange-1", "springboot.rabbit",
                msg,
                messagePostProcessor,
                correlationData);
    }
}
