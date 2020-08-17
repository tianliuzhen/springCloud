package com.aaa.rabbitmq.retrySend.mq;

import com.aaa.rabbitmq.retrySend.DetailRes;
import com.aaa.rabbitmq.retrySend.MessageSender;
import com.aaa.rabbitmq.retrySend.RetryCache;
import com.aaa.rabbitmq.config.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author liuzhen.tian
 * @version 1.0 Constants.java  2020/8/17 14:09
 */
@Slf4j
@Component
public class MsgRetryProducer implements MessageSender {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public DetailRes  sendMsg(Object message,String id,int type) {

        try {
            //首次发送消息放入map缓存中
            if (type==1) {
                RetryCache.add(id, message);
            }
            rabbitTemplate.convertAndSend(RabbitConstants.EXCHANGE_A, RabbitConstants.ROUTINGKEY_A, message, new CorrelationData(id));
        } catch (AmqpException e) {
            return new DetailRes(false, "");
        }
        return new DetailRes(true, "");
    }


}
