package com.aaa.rabbitmq;

import com.aaa.rabbitmq.config.RabbitConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试 死信交换机
 * @version 1.0
 * @date 2020/10/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitTestDLX {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendToMq(){
        rabbitTemplate.convertAndSend(
                RabbitConstants.EXCHANGE_A,
                RabbitConstants.ROUTINGKEY_C,
                "deadLetter");
    }
}
