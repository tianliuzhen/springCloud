package com.aaa.rabbitmq.pattern5.simple1;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/28
 */
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.aaa.rabbitmq.pattern5.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
public class Producter {
    private final static String QUEUE_NAME = "hello_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //从连接中声明通道
        Channel channel = connection.createChannel();
        //队列申明
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //消息内容
        String message = "simple queue hello world !";
        //推送发布消息
        //basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, message.getBytes());
        //信道关闭
        channel.close();
        //连接关闭
        connection.close();
    }
}

