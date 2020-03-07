package com.mq.workapi.broker;

import com.mq.workapi.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xulei
 * @date 2020-3-7 15:25
 */
@Component
@Slf4j
public class RabbitBrokerImpl implements RabbitBroker {
    @Override
    public void rapidSend(Message message) {

    }

    @Override
    public void confirmSend(Message message) {

    }

    @Override
    public void reliantSend(Message message) {

    }

    @Override
    public void sendMeassages() {

    }
}
