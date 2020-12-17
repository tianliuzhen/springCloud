package com.aaa.rabbitmq.web;

import com.aaa.rabbitmq.config.RabbitConstants;
import com.aaa.rabbitmq.testAnnotation.Sends;
import com.aaa.rabbitmq.testMq.send.MsgProducer;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * mq 延时消息发送，
     * 一般可用于场景是，取消订单
     */
    @GetMapping("/sendsDelayMsg")
    public void sendsDelayMsg() {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送时间:"+sdf.format(new Date()));
        rabbitTemplate.convertAndSend(
                RabbitConstants.EXCHANGE_DELAY,
                RabbitConstants.ROUTINGKEY_DELAY, "i am delay msg",
                message -> {
                    // message.getMessageProperties().setHeader("x-delay",3000);
                    message.getMessageProperties().setDelay(1000 * 3);
                    return message;
                },
                correlationId);
    }

}
