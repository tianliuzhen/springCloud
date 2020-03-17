package com.aaa.rabbitmq.test;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/17
 */
@Component
public class Sends  implements RabbitTemplate.ConfirmCallback {


    private RabbitTemplate rabbitTemplate;
    /**
     * 构造方法注入rabbitTemplate
     */
    public  Sends(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    public void send(){
        String msg = "mqsender send ..." + new Date();
        rabbitTemplate.convertAndSend("myQueue", msg);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }
}
