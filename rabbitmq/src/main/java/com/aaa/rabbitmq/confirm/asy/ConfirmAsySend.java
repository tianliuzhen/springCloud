package com.aaa.rabbitmq.confirm.asy;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/28
 */

import com.aaa.rabbitmq.pattern5.ConnectionUtil;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 之confirm异步
 *
 * Channel 对象提供的ConfirmListener（）回调方法只包含 deliveryTag（当前Chanel发出的消息序号），我们需要自己
 * 为每一个Channel维护一个unconfirm的消息序号集合，每publish
 * 一条数据，集合中元素加1，每回调一次handleAck方法，unconfirm集合
 * 删除相应的一条（multiple=false）或多条（multiple=true）记录，从 程序运行效率上看，这个unconfirm集合最好采用有序集合
 * SortedSet存储结构
 *
 * @author zhang
 *
 */
@Slf4j
public class ConfirmAsySend {

    public static final String QUEUE_NAME = "asy_test_confirm1";
    public static final String EXCHANGE_NAME = "ex_asy_1";
    public static final String ROUTE_KEY = "s1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 建立通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义一个交换机，
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //绑定队列    参数介绍：队列名称、目标交换机、绑定路由的key
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME, ROUTE_KEY);
        // 开启确认机制
        channel.confirmSelect();
        // 创建set
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());


        /**
         * 这里的发送确认机制分为两个，
         *   一个确认消息发送到交换机  ==》 addConfirmListener
         *   一个确认消息确认发送到交换机且 交换机分配到队列 ==》 addReturnListener
         */

        //1. 确认是否发送到交换机  ==》 异步监听，这里相当于新开启一个线程，不阻塞，不影响下面消息的发送
        channel.addConfirmListener(new ConfirmListener() {
            /**
             * @param deliveryTag  消息标识
             * @param multiple     是否批量确认
             */
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                log.info(String.format("确认发送消息到交换机【成功】，：%d，多个消息：%b",
                        deliveryTag, multiple));
            }
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                log.error("确认发送消息到交换机【失败】，标识：" + deliveryTag);
            }

        });
        //2.  这个是监听  交换机  =》 是否发送到队列
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange,
                                     String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 如果交换机发送到队列失败，则是失败，则会进入这个方法
                log.info("确认从交换机发送到队列【失败】，标识：" + new String(body));
            }
        });

        // 发送消息
        String msg = "msg data";
        for (int i = 0; i < 10; i++) {
            long seqNo = channel.getNextPublishSeqNo();
            // Thread.sleep(1000); // 模拟业务时间是一秒
            // 第三个参数，如果不设置，无法监听 addReturnListener
            channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY,true, null, msg.getBytes());
            // 可以模拟把这里的  ROUTE_KEY 改成其他值 =》  ROUTE_KEY***，再去跑会发现，channel.addReturnListener 会打印错误日志
            confirmSet.add(seqNo);
        }
        log.info(confirmSet.toString());
       // channel.waitForConfirms(); //批量确认
      /**
        channel.close();
        connection.close(); // 不能close，因为是异步监听
       */

    }

}
