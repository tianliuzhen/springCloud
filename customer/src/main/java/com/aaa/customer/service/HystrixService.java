package com.aaa.customer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 HystrixService.java  2020/12/20 16:08
 */
@Service
public class HystrixService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 服务降级处理。
     * 当前方法远程调用application service服务的时候，如果service服务出现了任何错误（超时，异常等）
     * 不会将异常抛到客户端，而是使用本地的一个fallback（错误返回）方法来返回一个托底数据。
     * 避免客户端看到错误页面。
     * 使用注解来描述当前方法的服务降级逻辑。
     * @HystrixCommand - 开启Hystrix命令的注解。代表当前方法如果出现服务调用问题，使用Hystrix逻辑来处理。
     *  重要属性 - fallbackMethod
     *      错误返回方法名。如果当前方法调用服务，远程服务出现问题的时候，调用本地的哪个方法得到托底数据。
     *      Hystrix会调用fallbackMethod指定的方法，获取结果，并返回给客户端。
     * @return
     */
    @HystrixCommand(fallbackMethod="downgradeFallback")
    public List<Map<String, Object>> testDowngrade() {
        System.out.println("testDowngrade method : " + Thread.currentThread().getName());
        ServiceInstance si =
                this.loadBalancerClient.choose("eureka-application-service");
        StringBuilder sb = new StringBuilder();
        sb.append("http://").append(si.getHost())
                .append(":").append(si.getPort()).append("/test");
        System.out.println("request application service URL : " + sb.toString());
        RestTemplate rt = new RestTemplate();
        ParameterizedTypeReference<List<Map<String, Object>>> type =
                new ParameterizedTypeReference<List<Map<String, Object>>>() {
                };
        ResponseEntity<List<Map<String, Object>>> response =
                rt.exchange(sb.toString(), HttpMethod.GET, null, type);
        List<Map<String, Object>> result = response.getBody();
        return result;
    }

    /**
     * fallback方法。本地定义的。用来处理远程服务调用错误时，返回的基础数据。
     */
    private List<Map<String, Object>> downgradeFallback(){
        List<Map<String, Object>> result = new ArrayList<>();

        Map<String, Object> data = new HashMap<>();
        data.put("id", -1);
        data.put("name", "downgrade fallback datas");
        data.put("age", 0);
        result.add(data);
        return result;
    }
}
