package com.aaa.rabbitmq;

import com.aaa.rabbitmq.send.MsgProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    @Test
    public  void  testSend() throws InterruptedException {

        for (int i = 0; i < 20; i++) {
            testRunAble t5=new testRunAble("我是线程————"+i);
            Thread t15= new Thread(t5);
            t15.start();
            Thread.sleep(100);
        }
    }
    public  class testRunAble implements  Runnable {
        private String name;

        public testRunAble(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            msgProducer.sendMsg("helloWord__"+name);
        }
    }

}
