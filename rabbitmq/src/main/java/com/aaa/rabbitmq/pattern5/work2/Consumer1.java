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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
/*
 * 消费者1
 */
public class Consumer1 {

    private final static String QUEUE_NAME = "work_queue";
    public static void main(String[] args) throws IOException, TimeoutException{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        Map<String, Object> arg = new HashMap<String , Object>();
        arg.put("x-message-ttl" , 30*1000);//设置队列里消息的ttl的时间30s
        // channel.queueDeclare(QUEUE_NAME, true, false, false, arg);
        channel.queueDeclare(QUEUE_NAME, true, false, false,null);

        //同一时刻服务器只发送1条消息给消费者（能者多劳，消费消息快的，会消费更多的消息）
        //保证在接收端一个消息没有处理完时不会接收另一个消息，即消费者端发送了ack后才会接收下一个消息。
        //在这种情况下生产者端会尝试把消息发送给下一个空闲的消费者。
        channel.basicQos(1);

        //声明队列的消费者O
        Consumer consumer1 = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                //channel.basicConsume(QUEUE_NAME, false, consumer1);
                String message = new String(body, "UTF-8");
                System.out.println("customer1 消费消息："+message);
                //手动返回结果
                channel.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //定义的消费者监听队列 autoAck：true自动返回结果，false手动返回
        channel.basicConsume(QUEUE_NAME, false,consumer1);
    }
}
