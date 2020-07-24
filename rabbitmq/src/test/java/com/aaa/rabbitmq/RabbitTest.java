package com.aaa.rabbitmq;

import com.aaa.rabbitmq.bootConfirm.BootSender;
import com.aaa.rabbitmq.testAnnotation.Sends;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitTest {
    @Autowired
    Sends sends;
    @Autowired
    BootSender bootSender;
    @Test
    public  void  testSend(){
//        sends.send();
//        sends.send2();
//        sends.send3();

        //消息发送到交换机，交换机通过路由key 发送到对应的队列。
        //因此computerOrder队列得到了消息，进而receiveComputer()接收到了消息。
        sends.sendOrder();
    }
    @Test
    public  void  testSendV2(){
        bootSender.send("新事物");
    }
}
