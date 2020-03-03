package com.mq.mqdemo.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xulei
 * @date 2020-2-27 10:30
 */
public class consumer {


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //1.创建ConnectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //2.通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        //3.通过connection创建一个Channel
        Channel channel = connection.createChannel();
        //4.声明创建一个队列
        String queueName="test001";
        channel.queueDeclare(queueName,true,false,false,null);
        //5.创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //6.设置Channel
        channel.basicConsume(queueName,true,queueingConsumer);
        //7.使用while循环轮询消息
        while (true){
            //8.获取消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("消费端："+msg);
            //Envelope envelope = delivery.getEnvelope();
        }

    }

}
