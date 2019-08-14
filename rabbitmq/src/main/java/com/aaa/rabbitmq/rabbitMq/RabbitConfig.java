package com.aaa.rabbitmq.rabbitMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * description: 该文件说明
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

    public static final String EXCHANGE_A = "my-mq-exchange_A";
    public static final String EXCHANGE_B = "my-mq-exchange_B";
    public static final String EXCHANGE_C = "my-mq-exchange_C";

    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username); connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/"); connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

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
        return new DirectExchange(EXCHANGE_A);
    }
/**
     * 获取队列A
     * @return
     */
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
        return new Queue(QUEUE_A, false,false,false,args);
        //设置消息过期
    }

    @Bean
    public Queue queueB() {
        //队列持久
        return new Queue(QUEUE_B, false);
    }
    /**
     * 一个交换机可以绑定多个消息队列，也就是消息通过一个交换机，可以分发到不同的队列当中去
     * 路由：相当于密钥/第三者，与交换机绑定即可路由消息到指定的队列！
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }

 @Bean
 public Binding bindingB(){
        return BindingBuilder.bind(queueB()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }
    /*
    *另外一种消息处理机制的写法如下，在RabbitMQConfig类里面增加bean
    * */
   /* @Bean
    public SimpleMessageListenerContainer messageContainer() {
        //加载处理消息A的队列
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        //设置接收多个队列里面的消息，这里设置接收队列A
        //假如想一个消费者处理多个队列里面的信息可以如下设置：
        //container.setQueues(queueA(),queueB(),queueC());
        container.setQueues(queueA());
        container.setExposeListenerChannel(true);
        //设置最大的并发的消费者数量
        container.setMaxConcurrentConsumers(10);
        //最小的并发消费者的数量
        container.setConcurrentConsumers(1);
        //设置确认模式手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                *//**通过basic.qos方法设置prefetch_count=1，这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message，
                 换句话说,在接收到该Consumer的ack前,它不会将新的Message分发给它 *//*
                channel.basicQos(1);
                byte[] body = message.getBody();
                logger.info("接收处理队列A当中的消息:" + new String(body));
                *//**为了保证永远不会丢失消息，RabbitMQ支持消息应答机制。
                 当消费者接收到消息并完成任务后会往RabbitMQ服务器发送一条确认的命令，然后RabbitMQ才会将消息删除。*//*
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        });
        return container;
    }*/
}

