package com.aaa.rabbitmq.testMq.receive;

import com.aaa.rabbitmq.config.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * QUEUE_A: 消费者
 * <p>
 * 配置手动消费后：
 * listener.simple.acknowledge-mode = manual 后默认不会自动确认消息
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConstants.QUEUE_A)
public class MsgQueueAReceiver2 {
    @RabbitHandler
    public void process(String content) {
        log.info("处理器 —— two --- 接收处理队列A当中的消息： " + content);
    }
}
