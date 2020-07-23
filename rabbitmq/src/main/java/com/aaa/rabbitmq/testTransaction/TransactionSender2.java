package com.aaa.rabbitmq.testTransaction;

/**
 * description: 生产确认
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/27
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionSender2 implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    /**
     * 需要通过生产者的构造器去注入RabbitTemplate，并设置他 回调确认对象为 当前对象。
     */
    public TransactionSender2(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    @Transactional(rollbackFor = Exception.class)
    public void send(String msg) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendMsg = msg + time.format(new Date()) + " This is a transaction message！ ";
        /**
         * 这里可以执行数据库操作
         *
         **/
        System.out.println("TransactionSender2 : " + sendMsg);
        // 模拟异常
//        int a = 1 / 0;
         CorrelationData correlationData = new CorrelationData();
        rabbitTemplate.convertAndSend("transition",  sendMsg.getBytes(),correlationData);

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("CallBackConfirm UUID: " + correlationData.getId());
        if(ack) {
            System.out.println("CallBackConfirm 消息确认发生成功！");
        }else {
            System.out.println("CallBackConfirm 消息确认发生失败！");
        }

        if(cause!=null) {
            System.out.println("CallBackConfirm Cause: " + cause);
        }
    }
}
