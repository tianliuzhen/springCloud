package com.aaa.rabbitmq.web;

import com.aaa.rabbitmq.config.RabbitConstants;
import com.aaa.rabbitmq.testAnnotation.Sends;
import com.aaa.rabbitmq.testMq.send.MsgProducer;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/27
 */
@RestController
public class TestController {
    @Autowired
    Sends sends;
    @Autowired
    private MsgProducer msgProducer;
    @Autowired
    private RabbitTemplate rabbitTemplate;



    @GetMapping("/msgProducer")
    public void msgProducer() {

        msgProducer.sendMsg("消息实体");
    }
    @GetMapping("/sends")
    public void sends() {
        sends.sendOrder();
    }

    @GetMapping("/sendsQc")
    public void sendsQc() {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(
                RabbitConstants.EXCHANGE_A,
                RabbitConstants.ROUTINGKEY_C,
                "deadLetter",correlationId);
    }

}
