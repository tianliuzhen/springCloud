package com.aaa.configserver.web;

import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/3/18
 */
@RestController
@EnableEurekaClient
@EnableConfigServer
public class TestWeb {

    /**
     * Spring Cloud Config 有它的一套访问规则，我们通过这套规则在浏览器上直接访问就可以。
     *
     * /{application}/{profile}[/{label}]
     * /{application}-{profile}.yml
     * /{label}/{application}-{profile}.yml
     * /{application}-{profile}.properties
     * /{label}/{application}-{profile}.properties
     * {application} 就是应用名称，对应到配置文件上来，就是配置文件的名称部分，例如我上面创建的配置文件。
     *
     * {profile} 就是配置文件的版本，我们的项目有开发版本、测试环境版本、生产环境版本，对应到配置文件上来就是以 application-{profile}.yml 加以区分，例如application-dev.yml、application-sit.yml、application-prod.yml。
     *
     * {label} 表示 git 分支，默认是 master 分支，如果项目是以分支做区分也是可以的，那就可以通过不同的 label 来控制访问不同的配置文件了。
     *
     * 上面的 5 条规则中，我们只看前三条，因为我这里的配置文件都是 yml 格式的。根据这三条规则，我们可以通过以下地址查看配置文件内容:
     */

}
