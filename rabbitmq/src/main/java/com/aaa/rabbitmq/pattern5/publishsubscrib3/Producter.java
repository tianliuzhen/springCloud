package com.aaa.rabbitmq.pattern5.publishsubscrib3;

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

    private final static String EXCHANGE_NAME = "publishSubscrible_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机Exchange类型为fanout
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = "publish/subscrible hello world";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println("发布订阅 生产者 发布消息："+message);
        channel.close();
        connection.close();
    }
}
