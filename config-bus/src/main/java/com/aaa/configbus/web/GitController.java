package com.aaa.configbus.web;

import org.springframework.beans.factory.annotation.Value;
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
@RefreshScope
public class GitController {

    @Value("${data.env}")
    private String env;

    @Value("${data.user.username}")
    private String username;

    @Value("${data.user.password}")
    private String password;

    @GetMapping(value = "show")
    public Object show() {
        return password;
    }


    /**
     * 触发 gitlab 更新yml文件
     */
    @PostMapping(value = "/actuator/refresh")
    public void actuatorRefresh(@RequestBody Map map) {
    }

    /**
     * 触发 gitlab 更新yml文件
     */
    @PostMapping(value = "/actuator/bus-refresh")
    public void busRefresh(@RequestBody Map map) {
    }
}
