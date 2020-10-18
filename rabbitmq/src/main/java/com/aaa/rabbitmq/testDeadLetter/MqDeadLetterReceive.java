package com.aaa.rabbitmq.testDeadLetter;

import com.aaa.rabbitmq.config.RabbitConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/10/18
 */
@Component
@Slf4j
public class MqDeadLetterReceive {
    @RabbitListener(queues = RabbitConstants.QUEUE_A_DEAD_LETTER)
    public void process(Message message, Channel channel) throws IOException {
        log.info("MqReceiver  : " + new String(message.getBody()));
        String messageText = new String(message.getBody());
        if("deadLetter".equalsIgnoreCase(messageText)){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }

    }
}
