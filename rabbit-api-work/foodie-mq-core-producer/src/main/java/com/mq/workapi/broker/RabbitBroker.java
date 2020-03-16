package com.mq.workapi.broker;

import com.mq.workapi.Message;

/**
 * @author xulei
 * @date 2020-3-7 15:20
 */
public interface RabbitBroker {


    void rapidSend(Message message);
    void confirmSend(Message message);
    void reliantSend(Message message);
    void sendMeassages();

}
