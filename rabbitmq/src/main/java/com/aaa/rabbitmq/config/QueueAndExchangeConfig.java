package com.aaa.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * description: 交换机和队列配置
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/17
 */
@Configuration
public class QueueAndExchangeConfig {
    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange ：通过添加属性key-value匹配
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(RabbitConstants.EXCHANGE_A, true, false);
    }

    @Bean
    public DirectExchange confirmExchange() {
        return new DirectExchange(RabbitConstants.EXCHANGE_CONFIRM, true, false);
    }

    /**
     * 声明队列：队列有五个参数（String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments）
     * name：队列名称
     * durable：持久性
     * exclusive：排他性（独立性）
     * autoDelete：自动删除
     * arguments：其他相关参数
     * arguments（参数配置）
     * 　　　　x-message-ttl(Time-To-Live)：消息存活时间，单位毫秒
     * 　　　　x-expires：队列没有访问超时时，自动删除（包含没有消费的消息），单位毫秒。
     * 　　　　x-max-length：限制队列最大长度（新增后挤出最早的），单位个数。
     * 　　　　x-max-length-bytes ：限制队列最大容量
     * 　　　　x-dead-letter-exchange：死信交换机，将删除/过期的数据，放入指定交换机。
     * 　　　　x-dead-letter-routing-key：死信路由，将删除/过期的数据，放入指定routingKey
     * 　　　　x-max-priority：队列优先级。
     * 　　　　x-queue-mode：对列模式，默认lazy（将数据放入磁盘，消费时放入内存）。
     * 　　　　x-queue-master-locator：镜像队列
     *
     * @return
     */

    @Bean
    public Queue queueA() {
        //队列持久
        // 设置队列最大消息数量为5
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-max-length", 5);
        //设置队列溢出方式    保留前10条
        args.put("x-overflow", "reject-publish");

        return new Queue(RabbitConstants.QUEUE_A, false, false, false, args);
        //设置消息过期
    }

    @Bean
    public Queue queueB() {
        //队列持久 方式一
        //   new Queue(RabbitConstants.QUEUE_B, true);
        //队列持久 方式二
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-message-ttl", 30 * 1000);//设置队列里消息的ttl的时间30s
        return QueueBuilder.durable(RabbitConstants.QUEUE_B).withArguments(args).build();
    }

    @Bean
    public Queue queueC() {
        Map<String, Object> args = new HashMap<>(2);
        // DLX  x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", RabbitConstants.EXCHANGE_DEAD_LETTER);
        // DLK  x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", RabbitConstants.ROUTINGKEY_DEAD_LETTER);
        // TTL  x-message-ttl 设置队列里消息的ttl的时间3s（注意：如果同时设置队列和队列中消息的TTL，则TTL值以两者中较小的值为准。而队列中的消息存在队列中的时间，一旦超过TTL过期时间则成为Dead Letter（死信）。）
        args.put("x-message-ttl", 1000 * 3);
        return QueueBuilder.durable(RabbitConstants.QUEUE_C).withArguments(args).build();
    }

    @Bean
    public Queue transition() {
        return new Queue(RabbitConstants.QUEUE_TRANSITION, false);
    }

    @Bean
    public Queue queueConfirm() {
        return QueueBuilder.durable(RabbitConstants.QUEUE_CONFIRM).build();
    }

    /**
     * 一个交换机可以绑定多个消息队列，也就是消息通过一个交换机，可以分发到不同的队列当中去
     * 路由：相当于密钥/第三者，与交换机绑定即可路由消息到指定的队列！
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitConstants.ROUTINGKEY_A);
    }

    @Bean
    public Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(defaultExchange()).with(RabbitConstants.ROUTINGKEY_B);
    }

    @Bean
    public Binding bindingQueueConfirm() {
        return BindingBuilder.bind(queueConfirm()).to(confirmExchange()).with(RabbitConstants.ROUTINGKEY_CONFIRM);
    }

    @Bean
    public Binding bindingC() {
        return BindingBuilder.bind(queueC()).to(defaultExchange()).with(RabbitConstants.ROUTINGKEY_C);
    }


}
