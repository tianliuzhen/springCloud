package com.aaa.hystrix.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * description: 断路器配置
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/5/11
 */
@Configuration
public class HystrixConfig {
    @Bean
    public HystrixCommandAspect hystrixAspect() {
        //用来拦截处理HystrixCommand注解
        return new HystrixCommandAspect();
    }
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet() {
        //用来像监控中心Dashboard发送stream信息   （2.0 需要 注册servlet）
        ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registration.addUrlMappings("/hystrix.stream");
        return registration;
    }


}
