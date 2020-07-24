package com.aaa.rabbitmq.confirm.syn;

import com.aaa.rabbitmq.pattern5.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 这里是采用 事务的方式确认发送消息，一把效率低不建议使用
 * @author liuzhen.tian
 * @version 1.0    2020/7/24 16:09
 */
@Slf4j
public class TransactionalTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 建立通道
        Channel channel = connection.createChannel();
        // 定义一个交换机
        channel.exchangeDeclare("ex1","fanout");
        //定一个一个队列
        channel.queueDeclare("queue1", false, false, false, null);
        //绑定队列    参数介绍：队列名称、目标交换机、绑定路由的key
        channel.queueBind("queue1","ex1","k1");

        // 1. 开启事务
        channel.txSelect();
        String msg= null;
        try {
            msg = "测试事物的消息";
            channel.basicPublish("","queue1",null,msg.getBytes());
        } catch (Exception e) {
            //2. 发生异常回滚
            channel.txRollback();
        }
        // 3. 提交事务
        channel.txCommit();
        log.info("已经成功发送：{}",msg);
    }
}
