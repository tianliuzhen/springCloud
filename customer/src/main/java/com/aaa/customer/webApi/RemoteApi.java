package com.aaa.customer.webApi;

import com.aaa.customer.config.FeignLogConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
/**
 * description: fallbackFactory（类似于断容器）与fallback方法
 * feign的注解@FeignClient：fallbackFactory（可以知道发生回退的原因）与fallback方法不能同时使用，这个两个方法其实都类似于Hystrix的功能，当网络不通时返回默认的配置数据.
 * 注意： fallbackFactory与fallback   要注入bean
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/5/10
 */
@Component
@FeignClient(name = "provider",/*fallback = RemoteApiFallBack.class*/ fallbackFactory = RemoteApiFallbackFactory.class,configuration = FeignLogConfiguration.class)
public  interface RemoteApi {
    /**
     *   待定 两个坑：1. @GetMapping不支持   2. @PathVariable得设置value
     * @param  name
     * @return
     */
    @GetMapping("/query")
    String queryByFeign(@RequestParam(value = "name")String name);

    @PostMapping("/query2")
    String queryByFeign2(@RequestBody  Map  map) ;

}

