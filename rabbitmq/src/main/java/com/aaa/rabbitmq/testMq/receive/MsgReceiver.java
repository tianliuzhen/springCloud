package com.aaa.rabbitmq.testMq.receive;

import com.aaa.rabbitmq.config.RabbitConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * description: 消费者
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/6/21
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConstants.QUEUE_A)
public class MsgReceiver {
    @RabbitHandler
    public void process(String content) {
        log.info("处理器 ——two --- 接收处理队列A当中的消息： " + content);
    }
}
