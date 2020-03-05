package com.mq.workapi;

import com.mq.workapi.exception.MessageException;

import java.util.List;

/**
 * @author xulei
 * @date 2020-3-4 17:43
 */
public interface MessageProducer {

    void send(Message message)throws MessageException;
    void send(Message message,SendCallback sendCallback)throws MessageException;
    void send(List<Message> messages)throws MessageException;
}
