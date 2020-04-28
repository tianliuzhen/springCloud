package com.aaa.rabbitmq.confirm;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/28
 */

import com.aaa.rabbitmq.pattern5.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

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
public class ConfirmAsySend {

    public static final String QUEUE_NAME = "asy_test_confirm";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 建立通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 开启事务
        channel.confirmSelect();
        // 创建set
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
        // 监听事务
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("未确认消息，标识：" + deliveryTag);
            }
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(String.format("已确认消息，标识：%d，多个消息：%b", deliveryTag, multiple));
            }
        });
        // 发送的数据
        String msg = "msg data";
        for (int i = 0; i < 100; i++) {
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            confirmSet.add(seqNo);
        }

    }

}
