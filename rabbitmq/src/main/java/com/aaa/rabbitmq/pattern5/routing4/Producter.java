package com.aaa.rabbitmq.pattern5.routing4;

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

public class Producter {

    private final static String EXCHANGE_NAME = "routing_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机Exchange类型为direct
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //发布消息3种routingKey的消息
        String message = "hello info";
        channel.basicPublish(EXCHANGE_NAME, "info", null, message.getBytes());
        System.out.println("路由模式发布info消息："+message);

        message = "hello warning";
        channel.basicPublish(EXCHANGE_NAME, "warning", null, message.getBytes());
        System.out.println("路由模式发布warning消息："+message);

        message = "hello error";
        channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes());
        System.out.println("路由模式发布error消息："+message);
    }
}
