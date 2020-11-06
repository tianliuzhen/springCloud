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
            /**
             *  这里记录一下啊，这些常用方法的说明
             *  basicNack(long deliveryTag, boolean multiple, boolean requeue)
             *  basicAck(long deliveryTag, boolean multiple)
             *  basicReject(long deliveryTag, boolean requeue)
             *  RecoverOk basicRecover(boolean requeue)
             *
             * 这里的 basicNack 和 basicAck 不同的是，basicNack 会把消息放进死信队列（前提是，当前队列绑定的有死信队列）
             *                             相同的是，都会确认消息从该队列中消费掉
             * 这里的 basicNack 和 basicReject 不同的是，basicReject 不支持多个消息因此少一个参数
             *                              相同的是，都会把消息放入死信队列，并把消息从该队列中消费掉
             * 这里的 basicRecover 参数为true 是重新投递的意思，参数为false，是不消费的意思
             */
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                // channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
                // channel.basicRecover(true);
            }else {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }

        }
    }

