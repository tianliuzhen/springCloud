package com.aaa.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * description: rabbitmq账号配置
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/6/21
 */

@Configuration
@Component
public class RabbitConfig {

/**
     * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
     * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。 Queue:消息的载体,每个消息都会被投到一个或多个队列。
     * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
     * Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
     * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
     * Producer:消息生产者,就是投递消息的程序.
     * Consumer:消息消费者,就是接受消息的程序.
     * Channel:消息通道,在客户端的每个连接里,可建立多个channel.
     * */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${rq.rabbitmq.host}")
    private String host;
    @Value("${rq.rabbitmq.port}")
    private int port;
    @Value("${rq.rabbitmq.username}")
    private String username;
    @Value("${rq.rabbitmq.password}")
    private String password;



    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        // 消息发送到交换机确认机制，是否确认回调
        connectionFactory.setPublisherConfirms(true);
        // 支持消息发送失败返回队列
        connectionFactory.setPublisherReturns(true);

        return connectionFactory;
    }

    /**
     * 两种确认消息机制。但是不能同时配置,同时配置会异常
     * 1、事物机制（同步阻塞）
     * 2、确实机制（异步不阻塞）
     *
      */

    /**
     * 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
     * @return
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        // template.setChannelTransacted(true); // 这行代码加上会报错
        // 设置为 true 后 消费者在消息没有被路由到合适队列情况下会被return监听，而不会自动删除\
        template.setMandatory(true);
        return template;
    }

    /**
     * 1.向Spring中注册RabbitMQ事务管理器
     * @param connectionFactory
     * @return
     */
    // @Bean
    // public RabbitTransactionManager rabbitTransactionManager(ConnectionFactory connectionFactory) {
    //     return new RabbitTransactionManager(connectionFactory);
    // }

}

