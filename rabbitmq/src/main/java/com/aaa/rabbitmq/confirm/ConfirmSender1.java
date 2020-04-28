package com.aaa.rabbitmq.confirm;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/28
 */


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 这是java原生类支持RabbitMQ，直接运行该类
 */
public class ConfirmSender1 {

    private final static String QUEUE_NAME = "confirm1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        /**
         * 创建连接连接到RabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();

        // 设置RabbitMQ所在主机ip或者主机名
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("47.98.253.2");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        // 创建一个连接
        Connection connection = factory.newConnection();

        // 创建一个频道
        Channel channel = connection.createChannel();

        // 指定一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 发送的消息
        String message = "This is a confirm message！";

        channel.confirmSelect();
        final long start = System.currentTimeMillis();
        //发送持久化消息
        //***************确认方式1 ，单个确认********************

     /*   for (int i = 0; i < 5; i++) {
             if (channel.waitForConfirms()) {
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, (" Confirm模式， 第" + (i + 1) + "条消息").getBytes());
                System.out.println("发送成功");
            }else{
                // 进行消息重发
            }
        }*/

        //***************确认方式2 ，批量确认********************这里主要更改代码为发送批量消息后再进行等待服务器确认
        for(int i=0;i<5;i++){
            //第一个参数是exchangeName(默认情况下代理服务器端是存在一个""名字的exchange的,
            //因此如果不创建exchange的话我们可以直接将该参数设置成"",如果创建了exchange的话
            //我们需要将该参数设置成创建的exchange的名字),第二个参数是路由键

            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, (" Confirm模式， 第" + (i + 1) + "条消息").getBytes());
        }
        if(channel.waitForConfirms()){
            System.out.println("发送成功");
        }else{
            // 进行消息重发
        }
        System.out.println("执行waitForConfirms耗费时间: " + (System.currentTimeMillis() - start) + "ms");
        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}
