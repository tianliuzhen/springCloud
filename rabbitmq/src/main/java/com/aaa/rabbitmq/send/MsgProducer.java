package com.aaa.rabbitmq.send;

import com.aaa.rabbitmq.config.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class MsgProducer implements RabbitTemplate.ConfirmCallback {


    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    @Autowired
    private RabbitTemplate rabbitTemplate;
     /**
     * 构造方法注入rabbitTemplate
     */
     public  MsgProducer(RabbitTemplate rabbitTemplate) {
         this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
         //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
     }
    public void sendMsg(String content) {
         CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
         //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        // 这里就像分组一样。依次是 交换机、路由、消息实体、uuid
            rabbitTemplate.convertAndSend(RabbitConstants.EXCHANGE_A, RabbitConstants.ROUTINGKEY_A, content, correlationId);
        }


    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        log.info(" 回调id:" + correlationData);
        if (ack) {
//            log.info("消息成功消费");
        }
        else { log.info("消息消费失败:" + cause); }

    }

}
