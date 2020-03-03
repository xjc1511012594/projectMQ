package com.mq.mqdemo.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xulei
 * @date 2020-2-27 10:30
 */
public class Producer {


    public static void main(String[] args) throws IOException, TimeoutException {
        //1.connectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //2.通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        //3.通过connection创建一个Channel
        Channel channel = connection.createChannel();
        //4.通过Channel发送信息
        for(int i=0;i<5;i++){
            String msg="Hello RabbitMQ";
            channel.basicPublish("","test001",null,msg.getBytes());
        }
        //5.关闭相关连接
        channel.close();
        connection.close();
    }

}
