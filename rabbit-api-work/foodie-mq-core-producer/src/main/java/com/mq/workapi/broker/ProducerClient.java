package com.mq.workapi.broker;

import com.google.common.base.Preconditions;
import com.mq.workapi.Message;
import com.mq.workapi.MessageProducer;
import com.mq.workapi.MessageType;
import com.mq.workapi.SendCallback;
import com.mq.workapi.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xulei
 * @date 2020-3-10 11:54
 */
@Component
public class ProducerClient implements MessageProducer {

    @Autowired
    private RabbitBroker rabbitBroker;


    @Override
    public void send(Message message) throws MessageException {
        Preconditions.checkNotNull(message.getTopic());
        String messageType = message.getMessageType();

        switch (messageType){
            case MessageType.PAPID:
                rabbitBroker.rapidSend(message);
            case MessageType.CONFIRM:
                rabbitBroker.confirmSend(message);
            case MessageType.RELIANT:
                rabbitBroker.reliantSend(message);
        }

    }

    @Override
    public void send(Message message, SendCallback sendCallback) throws MessageException {

    }

    @Override
    public void send(List<Message> messages) throws MessageException {

    }
}
