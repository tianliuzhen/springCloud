package com.aaa.rabbitmq.confirm;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/28
 */

import com.aaa.rabbitmq.pattern5.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConfirmAsyRevc {

    public static final String QUEUE_NAME = "asy_test_confirm";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接
        Connection connetion = ConnectionUtil.getConnection();

        // 创建通道
        Channel channel = connetion.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 接受数据，并监听
        channel.basicConsume(QUEUE_NAME, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {

                System.out.println("confirm Revc：" + new String(body, "utf-8"));

            }
        });

    }

}
