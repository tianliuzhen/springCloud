package com.aaa.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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
@Component
public class QueueAndExchangeConfig {
    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(RabbitConstants.EXCHANGE_A);
    }
    /**
     * 声明队列：队列有五个参数（String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments）
     * name：队列名称
     * durable：持久性
     * exclusive：排他性（独立性）
     * autoDelete：自动删除
     * arguments：其他相关参数
     *         arguments（参数配置）
     * 　　　　x-message-ttl(Time-To-Live)：消息存活时间，单位毫秒
     * 　　　　x-expires：队列没有访问超时时，自动删除（包含没有消费的消息），单位毫秒。
     * 　　　　x-max-length：限制队列最大长度（新增后挤出最早的），单位个数。
     * 　　　　x-max-length-bytes ：限制队列最大容量
     * 　　　　x-dead-letter-exchange：死信交换机，将删除/过期的数据，放入指定交换机。
     * 　　　　x-dead-letter-routing-key：死信路由，将删除/过期的数据，放入指定routingKey
     * 　　　　x-max-priority：队列优先级。
     * 　　　　x-queue-mode：对列模式，默认lazy（将数据放入磁盘，消费时放入内存）。
     * 　　　　x-queue-master-locator：镜像队列
     * @return
     */
    @Bean
    public Queue queueA() {
        //队列持久
        // 设置队列最大消息数量为5
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-max-length", 5);
        //设置队列溢出方式    保留前10条
        args.put("x-overflow","reject-publish" );
        return new Queue(RabbitConstants.QUEUE_A, false,false,false,args);
        //设置消息过期
    }

    @Bean
    public Queue queueB() {
        //队列持久
        return new Queue(RabbitConstants.QUEUE_B, false);
    }

    @Bean
    public Queue queueC() {
        //队列持久
        return new Queue(RabbitConstants.QUEUE_C, false);
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
    public Binding bindingB(){
        return BindingBuilder.bind(queueB()).to(defaultExchange()).with(RabbitConstants.ROUTINGKEY_A);
    }
}
