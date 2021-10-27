package com.aaa.customer.controller;

import com.aaa.customer.service.HystrixService;
import com.aaa.customer.webApi.RemoteApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.Pipe;

/**
 * @author liuzhen.tian
 * @version 1.0 HystrixCommandControlller.java  2020/12/20 16:06
 */
@RestController
public class HystrixCommandController {
    @Autowired
    private HystrixService hystrixService;
    @Autowired
    private RemoteApi remoteApi;

    /**
     * Hystrix单独使用
     */
    @GetMapping("/hquery")
    @HystrixCommand(fallbackMethod = "helloFallBack",
            commandProperties = {
            // 默认20个;10ms内请求数大于20个时就启动熔断器，当请求符合熔断条件时将触发getFallback()。
            @HystrixProperty(name= HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,
                    value="1000"),
            // 请求错误率大于50%时就熔断，然后for循环发起请求，当请求符合熔断条件时将触发getFallback()。
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,
                    value="50"),
            // 默认5秒;熔断多少秒后去尝试请求
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,
                    value="5000")
    })
    public  String queryByFeign(){
        return "feign:"+remoteApi.queryByFeign("xx");
    }

    public String helloFallBack(){
        return "Error occurred ！";
    }


    /**
     * SEMAPHORE 信号量隔离只是限制了总的并发数，
     * 服务使用主线程进行同步调用，即没有线程池。
     * 因此，如果只是想限制某个服务的总并发调用量或者调用的服务不涉及远程调用的话，
     * 可以使用轻量级的信号量来实现。
     * @return
     */
    @GetMapping("/order/id")
    @HystrixCommand(fallbackMethod="helloFallBack",
            commandProperties={
            @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
    })//信号量隔离
    public String findById() {
        return "feign:"+remoteApi.queryByFeign("xx");
    }


}
