package com.aaa.rabbitmq.web;

import com.aaa.rabbitmq.config.RabbitConstants;
import com.aaa.rabbitmq.testAnnotation.Sends;
import com.aaa.rabbitmq.testMq.send.MsgQueueAProducer;
import com.aaa.rabbitmq.testMq.send2.MsgQueueBProducer;
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
    private MsgQueueAProducer msgQueueAProducer;
    @Autowired
    private MsgQueueBProducer msgQueueBProducer;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("/msgQueueAProducer")
    public void msgQueueAProducer() {
        for (int i = 0; i < 10; i++) {
            msgQueueAProducer.sendMsg("消息QueueA实体" + i);
        }
    }


    @GetMapping("/msgQueueBProducer")
    public void msgQueueBProducer() {
        for (int i = 0; i < 10; i++) {
            msgQueueBProducer.sendMsg("消息QueueB实体" + i);
        }
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
                "deadLetter", correlationId);
    }

    /**
     * mq 延时消息发送：插件实现
     */
    @GetMapping("/sendsDelayMsg")
    public void sendsDelayMsg() {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送时间:" + sdf.format(new Date()));
        rabbitTemplate.convertAndSend(
                RabbitConstants.EXCHANGE_DELAY,
                RabbitConstants.ROUTINGKEY_DELAY, "i am delay msg",
                message -> {
                    // 方法一
                    // message.getMessageProperties().setHeader("x-delay",3000);
                    // 方法二
                    message.getMessageProperties().setDelay(1000 * 3);
                    return message;
                },
                correlationId);
    }

    /**
     * mq 延时消息发送: DLX和TTL 实现
     */
    @GetMapping("/sendsDelayMsg2")
    public void sendsDelayMsg2() {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送时间:" + sdf.format(new Date()));
        rabbitTemplate.convertAndSend(
                RabbitConstants.EXCHANGE_A,
                RabbitConstants.ROUTINGKEY_C, "deadLetter",
                message -> {
                    // 吐槽一下，这里为啥是string 不是整型
                    message.getMessageProperties().setExpiration(1000 * 8 + "");
                    return message;
                },
                correlationId);
    }

}
