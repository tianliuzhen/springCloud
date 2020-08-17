package com.aaa.rabbitmq.retrySend;

/**
 * @author liuzhen.tian
 * @version 1.0 Constants.java  2020/8/17 14:09
 */
public class Constants {
    //线程数
    public final static int THREAD_COUNT = 5;

    //处理间隔时间
    //mils
    public final static int INTERVAL_MILS = 0;

    /**
     * consumer失败后等待时间(mils)
     */
    public static final int ONE_SECOND = 1 * 60;

    /**
     * 异常sleep时间(mils)
     */
    public static final int ONE_MINUTE = 1 * 60 ;
    //MQ消息retry时间
    public static final int RETRY_TIME_INTERVAL = ONE_MINUTE;
    //MQ消息有效时间
    public static final int VALID_TIME = ONE_MINUTE;

    /**
     * 重试次数默认三次
     */
    public static final int RETRY_TIME = 3;
}
