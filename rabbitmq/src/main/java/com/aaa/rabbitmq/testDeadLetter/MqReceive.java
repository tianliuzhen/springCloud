package com.aaa.rabbitmq.testDeadLetter;

/**
 * description: 消费确认
 * 参考：https://www.jianshu.com/p/1581f9dcdda4
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/27
 */

import com.aaa.rabbitmq.config.RabbitConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MqReceive {
    @RabbitListener(queues = RabbitConstants.QUEUE_C)
    public void process(Message message, Channel channel) throws IOException {
        log.info("MqReceiver  : " + new String(message.getBody()));
        String messageText = new String(message.getBody());
        boolean ack = true;
            if("deadLetter".equalsIgnoreCase(messageText)){
                try {
                    throw new RuntimeException("死信异常...");
                } catch (Exception e) {
                    ack = false;
                }
            }
            if (!ack){
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }else {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }

        }
    }

