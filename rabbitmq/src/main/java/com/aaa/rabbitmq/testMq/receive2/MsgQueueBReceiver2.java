package com.aaa.rabbitmq.testMq.receive2;

import com.aaa.rabbitmq.config.RabbitConstants;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 MsgQueueBReceiver.java  2022/2/8 19:44
 */
@Slf4j
@Component
public class MsgQueueBReceiver2 {
    @SneakyThrows
    @RabbitListener(queues = RabbitConstants.QUEUE_B)
    public void process(Message msg, Channel channel) throws IOException {
        TimeUnit.MILLISECONDS.sleep(200);
        log.info("处理器 —— two --- 接收处理队列B当中的消息： " + new String(msg.getBody()));
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), true);
    }
}
