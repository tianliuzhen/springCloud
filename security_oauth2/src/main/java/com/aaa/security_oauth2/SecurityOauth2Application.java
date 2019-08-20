package com.aaa.security_oauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
/**
 * 通过 @EnableAuthorizationServer 来启用AuthorizationServer的默认实现，
 * 以提供/oauth/token、/oauth/check_token、/oauth/authorize等endpoint
 *
 * Mapper接口要加上@Mapper注解或者在启动类加上@MapperScan("xxx.xxx.xxx")包扫描的注解
 *
 * @param
 * @return
 */
@SpringBootApplication
@EnableAuthorizationServer
@EnableEurekaClient
@MapperScan("com.aaa.security_oauth2.mapper")
public class SecurityOauth2Application {

    public static void main(String[] args) {
        /**
         * * 1、【密码授权模式-client】 同样支持 base auth
         * 		密码模式需要参数：username,password,grant_type,client_id,client_secret
         * 		http://localhost:8080/oauth/token?username=admin&password=123456&grant_type=password&client_id=demoApp&client_secret=demoAppSecret
         *
         * *2、【客户端授权模式-password】 客户端模式需要参数：grant_type,client_id,client_secret
         * 		http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=demoApp&client_secret=demoAppSecret
         *
         * *3、【授权码模式-code】 获取code
         * 		http://localhost:8080/oauth/authorize?response_type=code&client_id=demoApp&redirect_uri=http://baidu.com
         *
         **    【通过code】 换token
         * 		http://localhost:8080/oauth/token?grant_type=authorization_code&code=A2f3bO&client_id=demoApp&client_secret=demoAppSecret&redirect_uri=http://baidu.com
         * 		这里的code字段是授权码模式中返回的code  例如： https://www.baidu.com/?code=tsuHSh
         *
         ** 4、【简易模式-code】 获取token
         *      这种模式比 授权码模式少了 code环节，回调url直接携带token
         *      http://localhost:8080/oauth/authorize?response_type=token&client_id=demoApp&redirect_uri=http://localhost:1080/&state=123
         *      申请授权token，参数和申请授权码类似，client_id，redirect_uri回调地址，response_type有变动，
         *      改为直接获取token，scope权限，state用于认证标记，传过去什么回调时传回来什么
         *
         *
         * 【通过refresh token】 刷新token
         *		http://localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=7ba47059-d853-4050-9c64-69d0cade71a7&client_id=demoApp&client_secret=demoAppSecret
         *		其中grant_type为固定值：grant_type=refresh_token    , refresh_token = 通过code获取的token中的refresh_token
         *
         */
        SpringApplication.run(SecurityOauth2Application.class, args);
    }
}
