package com.rabbit.consumer.component;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xulei
 * @date 2020-3-4 9:37
 */
@Component
public class RabbitReceive {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",durable = "true"),
            exchange = @Exchange(name = "exchange-1",
            durable = "true",
            type = "topic",
            ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    ))
    @RabbitHandler
    private void onMessage(Message message, Channel channel) throws IOException {
        System.out.println("-----------------------");
        System.out.println(message.getPayload());
        //处理成功后 获取deliveryTag  进行手工ACK操作  因为我们配置的
        //spring.rabbitmq.listener.simple.acknowledge-mode=manual
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag,false);
    }

}
