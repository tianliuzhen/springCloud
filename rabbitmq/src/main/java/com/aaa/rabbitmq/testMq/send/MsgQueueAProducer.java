package com.aaa.rabbitmq.testMq.send;

import com.aaa.rabbitmq.config.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * description: 消息生产者
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/6/21
 */
@Slf4j
@Component
public class MsgQueueAProducer {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMsg(String content) {
        /**
         *
         * CorrelationData对象的作用是作为消息的附加信息传递，通常我们用它来保存消息的自定义id
         * 在Message中的headers
         *                 headers={amqp_receivedDeliveryMode=PERSISTENT, amqp_receivedRoutingKey=javaboy.mail.routing.key, amqp_receivedExchange=javaboy.mail.exchange, spring_returned_message_correlation=8170350b-3b4d-4f20-89fc-f9e72bf754cb
         * 获取msgId
         * String msgId = (String) headers.get("spring_returned_message_correlation");
         */


        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //模拟异常
//        if (true) {
//            throw new RuntimeException("自定义异常");
//        }

         //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        // 这里就像分组一样。依次是 交换机、路由、消息实体、uuid
            rabbitTemplate.convertAndSend(RabbitConstants.EXCHANGE_A, RabbitConstants.ROUTINGKEY_A, content, correlationId);
        }



}
