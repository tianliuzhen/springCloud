package com.aaa.rabbitmq.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/17
 */
@Slf4j
@Component
public class Receives implements RabbitTemplate.ConfirmCallback  {
    /**
     * 需要手动在39...50:15672/ 下的RabbitMQ management 界面下创建一个队列 myQueue
     * @param msg
     */
    @RabbitListener(queues = "myQueue")
    public void receive(String msg){
        log.info("mqReceive = {}" , msg );
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }
}
