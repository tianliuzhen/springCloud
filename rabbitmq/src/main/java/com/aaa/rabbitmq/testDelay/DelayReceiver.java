package com.aaa.rabbitmq.testDelay;

import com.aaa.rabbitmq.config.RabbitConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 延时消息消费者
 * @author liuzhen.tian
 * @version 1.0 delayReceiver.java  2020/11/10 15:11
 */
@Component
@Slf4j
public class DelayReceiver {

    @RabbitListener(queues = RabbitConstants.QUEUE_DELAY)
    @RabbitHandler
    public void process(Message msg, Channel channel) throws IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息接受时间:"+sdf.format(new Date()));

        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);
        System.out.println("lazy receive " + new String(msg.getBody()));

    }
}
