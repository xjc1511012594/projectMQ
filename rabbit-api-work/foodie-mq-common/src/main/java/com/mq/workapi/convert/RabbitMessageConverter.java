package com.mq.workapi.convert;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author xulei
 * @date 2020-3-11 15:31
 */
public class RabbitMessageConverter implements MessageConverter {

    private GenericMessageConverter delegate;

    public RabbitMessageConverter(GenericMessageConverter genericMessageConverter) {
        this.delegate = genericMessageConverter;
    }


    @Override
    public Message toMessage(Object obj, MessageProperties messageProperties) throws MessageConversionException {
        com.mq.workapi.Message message=(com.mq.workapi.Message) obj;
        messageProperties.setDelay(message.getDelayMills());
        return this.delegate.toMessage(obj,messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        com.mq.workapi.Message msg = (com.mq.workapi.Message) this.delegate.fromMessage(message);
        return msg;
    }
}
