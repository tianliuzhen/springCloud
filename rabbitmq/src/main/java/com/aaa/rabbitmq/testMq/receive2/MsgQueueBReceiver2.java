package com.aaa.rabbitmq.testMq.receive2;

import com.aaa.rabbitmq.config.RabbitConstants;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuzhen.tian
 * @version 1.0 MsgQueueBReceiver.java  2022/2/8 19:44
 */
@Component
public class MsgQueueBReceiver2 {
    @RabbitListener(queues = RabbitConstants.QUEUE_DELAY)
    @RabbitHandler
    public void process(Message msg, Channel channel) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息接受时间:" + sdf.format(new Date()));

        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);
        System.out.println("lazy receive " + new String(msg.getBody()));

    }
}
