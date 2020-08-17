package com.aaa.rabbitmq.retrySend;

/**
 * @author liuzhen.tian
 * @version 1.0 MessageSender.java  2020/8/17 14:02
 */
public interface MessageSender {
    DetailRes sendMsg(Object message,String key,int type);
}
