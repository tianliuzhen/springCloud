package com.aaa.rabbitmq.testTransaction;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/27
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

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
        int a = 1 / 0;
        this.rabbitTemplate.convertAndSend("transition", sendMsg);

    }

}
