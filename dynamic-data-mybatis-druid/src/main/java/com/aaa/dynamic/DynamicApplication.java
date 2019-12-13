package com.aaa.dynamic;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.netflix.config.DynamicConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//指定aop事务执行顺序，已保证在切换数据源的后面
@EnableTransactionManagement(order = 2)
//排除数据源自动配置
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Import({DynamicConfiguration.class})
public class DynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicApplication.class, args);
    }

}
