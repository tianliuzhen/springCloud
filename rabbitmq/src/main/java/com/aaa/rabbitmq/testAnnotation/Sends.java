package com.aaa.rabbitmq.testAnnotation;

import com.aaa.rabbitmq.config.RabbitConstants;
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
public class Sends  {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        String msg = "mqsender send ..." + new Date();
        rabbitTemplate.convertAndSend(RabbitConstants.QUEUE_C, msg);
    }
    public void send2(){
        String msg = "mqsender send ..." + new Date();
        rabbitTemplate.convertAndSend("myQueue2", msg);
    }
    public void send3(){
        String msg = "mqsender send ..." + new Date();
        rabbitTemplate.convertAndSend("myQueue3", msg);
    }

    /**-------------------------- 模拟消息分组 -----------------------------------*/
    /**
     * 模拟消息分组 发送方
     */
    public void sendOrder(){
        String msg = "mqsender send ..." + new Date();
        // 参数：交换机，路由key, 消息
        rabbitTemplate.convertAndSend("myOrder","computer", msg);
    }
}
