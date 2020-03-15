package com.mq.workapi.broker;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Map;

/**
 * Created by xl on 2020/3/16.
 * $RabbitTemplateContainer池化封装
 * 1.  提高发送的效率
 * 2.  可以根据不同的需求定制化不同的RabbitTemplate，比如每一个topic 都有自己的routingKey原则
 */
public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback{

    private Map<String,RabbitTemplate> rabbitMap= Maps.newConcurrentMap();

    private Splitter splitter= Splitter.on("#");

    

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }
}
