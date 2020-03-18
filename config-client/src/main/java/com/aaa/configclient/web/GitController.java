package com.aaa.configclient.web;

import com.aaa.configclient.config.GitAutoRefreshConfig;
import com.aaa.configclient.config.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/18
 */
@RestController
/**
 * 它提供了一个刷新机制，但是需要我们主动触发。
 * 那就是 @RefreshScope 注解并结合 actuator ，
 * 注意要引入 spring-boot-starter-actuator 包。
 */
@RefreshScope
public class GitController {

    /**
     * 这种配置刷新**********不会生效
     */
    @Autowired
    private GitConfig gitConfig;

    /**
     * 这种配置刷新***********会生效 ***** 推荐使用
     */
    @Autowired
    private GitAutoRefreshConfig gitAutoRefreshConfig;

    @GetMapping(value = "show")
    public Object show() {
        return gitConfig;
    }

    @GetMapping(value = "autoShow")
    public Object autoShow() {
        return gitAutoRefreshConfig;
    }
    /**
     * 触发 gitlab 更新yml文件
     */
    @PostMapping(value = "/actuator/refresh")
    public void actuatorRefresh(@RequestBody Map map) {
    }
}
