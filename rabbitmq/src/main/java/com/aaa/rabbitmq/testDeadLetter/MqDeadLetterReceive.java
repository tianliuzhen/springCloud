package com.aaa.rabbitmq.testDeadLetter;

import com.aaa.rabbitmq.config.RabbitConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("消息接受时间:"+sdf.format(new Date()));
        log.info("MqReceiver  : " + new String(message.getBody()));
        String messageText = new String(message.getBody());
        if("deadLetter".equalsIgnoreCase(messageText)){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }

    }
    @RabbitListener(queues = RabbitConstants.QUEUE_B_DEAD_LETTER)
    public void processB(Message message, Channel channel) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("消息接受时间:"+sdf.format(new Date()));
        log.info("MqReceiver  : " + new String(message.getBody()));
        String messageText = new String(message.getBody());
        if("deadLetter".equalsIgnoreCase(messageText)){
            // 第三个参数为true的话，会一直，重新投递，一般设置false
            // channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }

    }
}
