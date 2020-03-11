package com.mq.workapi.convert;

import com.google.common.base.Preconditions;
import com.mq.workapi.serializer.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author xulei
 * @date 2020-3-11 15:32
 */
public class GenericMessageConverter implements MessageConverter {


    private Serializer serializer;

    public GenericMessageConverter(Serializer serializer) {
        Preconditions.checkNotNull(serializer);
        this.serializer = serializer;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return this.serializer.deserialize(message.getBody());
    }

    @Override
    public Message toMessage(Object obj, MessageProperties messageProperties) throws MessageConversionException {
        return new Message(this.serializer.serializeRaw(obj),messageProperties);
    }


}
