package com.aaa.rabbitmq;

import com.aaa.rabbitmq.retrySend.mq.MsgRetryProducer;
import com.aaa.rabbitmq.testMq.send.MsgProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description: 测试rabbitMq
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/6/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Rabbit {
    @Autowired
    MsgProducer msgProducer;

    @Autowired
    MsgRetryProducer msgRetryProducer;
    @Test
    public  void  testSend() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new testRunAble("我是线程————"+i));
            Thread.sleep(100);
        }
        executorService.shutdown();

    }
    public  class testRunAble implements  Runnable {
        private String name;

        public testRunAble(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            msgRetryProducer.sendMsg("helloWord__"+name);
        }
    }

}
