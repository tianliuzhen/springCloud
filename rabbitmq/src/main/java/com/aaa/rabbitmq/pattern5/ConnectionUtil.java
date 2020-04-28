package com.aaa.rabbitmq.pattern5;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/4/28
 */
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义连接池
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //连接地址
        connectionFactory.setHost("47.98.253.2");
        //连接端口
        connectionFactory.setPort(5672);
        //用户名
        connectionFactory.setUsername("guest");
        //密码
        connectionFactory.setPassword("guest");
        //设置虚拟主机
        connectionFactory.setVirtualHost("/");
        //通过连接工厂获取连接
        Connection connection = connectionFactory.newConnection();
        //返回连接
        return connection;
    }
}
