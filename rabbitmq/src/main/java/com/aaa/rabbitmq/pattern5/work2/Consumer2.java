package com.aaa.rabbitmq.pattern5.work2;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/28
 */
import com.aaa.rabbitmq.pattern5.ConnectionUtil;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/*
 * 消费者2
 */
public class Consumer2 {
    private final static String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws IOException, TimeoutException{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //同一时刻服务器只发送1条消息给消费者（能者多劳，消费消息快的，会消费更多的消息）
        // channel.basicQos(1);

        //声明队列的消费者
        Consumer consumer2 = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                //channel.basicConsume(QUEUE_NAME, false, consumer1);
                String message = new String(body, "UTF-8");
                System.out.println("customer2 消费消息："+message);
                channel.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //定义的消费者监听队列(第二个参数：true自动返回结果，false手动返回)
        channel.basicConsume(QUEUE_NAME, false,consumer2);
    }
}
