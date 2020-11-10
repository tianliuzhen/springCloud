package com.aaa.rabbitmq.testAnnotation;

import com.aaa.rabbitmq.config.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * description: 注解
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/17
 */
@Slf4j
@Component
public class Receives  {
    /**
     * 1. 需要手动在39...50:15672/ 下的RabbitMQ management 界面下创建一个队列 myQueue
     * 这种形式是   队列必须要存在
     * @param msg
     */
    // @RabbitListener(queues = RabbitConstants.QUEUE_C)
    // @RabbitHandler
    // public void receive(String msg){
    //     log.warn("mqReceive = {}" , msg );
    // }

    /**
     * 2. 通过注解自动创建 myQueue 队列
     * @param msg
     */
    @RabbitListener(queuesToDeclare = @Queue("myQueue2"))
    @RabbitHandler
    public void receive2(String msg){
        log.warn("mqReceive = {}" , msg );
    }

    /**
     * 3. 自动创建，queue 和 exchange 绑定
     * @param msg
     * @return void
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue("myQueue3"),
            exchange = @Exchange("myExchange")
    ))
    public void receive3(String msg){
        log.warn("mqReceive = {}" , msg );
    }


    /**-------------------------- 模拟消息分组 -----------------------------------*/
    /**
     * 数码供应商服务  接收消息
     * 消息发到交换机，交换机根据不同的key 发送到不同的队列
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void receiveComputer(String msg){
        log.warn(" receiveComputer service = {}" , msg );
    }
    /**
     * 水果供应商服务  接收消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fruitOrder"),
            key = "fruit",
            exchange = @Exchange("myOrder")
    ))
    public void receiveFruit(String msg){
        log.warn(" receiveFruit service = {}" , msg );
    }

}
