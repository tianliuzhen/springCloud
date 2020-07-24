package com.aaa.rabbitmq.confirmBoot;

import com.aaa.rabbitmq.config.RabbitConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuzhen.tian
 * @version 1.0 MqService.java  2020/7/24 19:14
 */
@Service
public class MqService {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToMq(){
        rabbitTemplate.convertAndSend(RabbitConstants.EXCHANGE_CONFIRM, RabbitConstants.EXCHANGE_B,
                "springBoot 发消息");
    }
}
