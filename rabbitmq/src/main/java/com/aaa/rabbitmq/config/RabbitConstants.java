package com.aaa.rabbitmq.config;

/**
 * description: rabbitmq常量
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/17
 */
public class RabbitConstants {
    public static final String EXCHANGE_A = "my-mq-exchange-A";
    public static final String EXCHANGE_B = "my-mq-exchange-B";
    public static final String EXCHANGE_C = "my-mq-exchange-C";
    public static final String EXCHANGE_CONFIRM = "my-mq-exchange-confirm";
    // 死信交换机
    public static final String EXCHANGE_DEAD_LETTER= "my-dead-exchange";



    public static final String QUEUE_A = "queue-a";
    public static final String QUEUE_B = "queue-b";
    public static final String QUEUE_C = "queue-c";
    public static final String QUEUE_TRANSITION = "queue-transition";
    public static final String QUEUE_CONFIRM = "queue-confirm";
    // 死信队列
    public static final String QUEUE_A_DEAD_LETTER= "queue-a-dead-letter-A";
    public static final String QUEUE_B_DEAD_LETTER = "queue-a-dead-letter-B";



    public static final String ROUTINGKEY_A = "routingKey-a";
    public static final String ROUTINGKEY_B = "routingKey-b";
    public static final String ROUTINGKEY_C = "routingKey-c";
    public static final String ROUTINGKEY_CONFIRM = "routingKey-confirm-C";

    // 死信路由key
    public static final String ROUTINGKEY_DEAD_LETTER = "routingKey-dead-letter";
}
