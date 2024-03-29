package com.aaa.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注意需要配置插件
 * 延时 -- 交换机和队列配置
 * @author liuzhen.tian
 * @version 1.0 DelayConfig.java  2020/11/10 15:16
 */
@Configuration
public class DelayConfig {
    /**
     *延时交换机
     */
    @Bean
    public DirectExchange delayExchange() {
        //默认是不开启的
        DirectExchange directExchange = new DirectExchange(RabbitConstants.EXCHANGE_DELAY, true, false);

        // 开启方式：1
        // fixme TODO: 2022/2/8 如果没有安装 延时队列插件， directExchange.setDelayed(true); 会导致报错
        // directExchange.setDelayed(true);

        // 开启方式：2，另外一种开启方式
        /**
         * Map<String, Object> arguments = new HashMap<>();
         *         //设置交换机支持延迟消息推送
         *         arguments.put("x-delayed-message", "direct");
         *  new DirectExchange(RabbitConstants.EXCHANGE_DELAY, true, false,arguments);
         */
        return directExchange;
    }

    @Bean
    public Queue lazyQueue(){
        return new Queue(RabbitConstants.QUEUE_DELAY);
    }

    @Bean
    public Binding lazyBinding(){
        return BindingBuilder.bind(lazyQueue()).to(delayExchange()).with(RabbitConstants.ROUTINGKEY_DELAY);
    }
}
