package com.aaa.security_oauth2.config_easy;


import com.aaa.security_oauth2.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * description: 该文件说明
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/6/28
 */

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Resource
    private DataSource dataSource;
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.
                // code授权添加 一般建议添加。但是测试不添加貌似也可以
                realm("oauth2-resources")
                /* 配置token获取合验证时的策略 */
                .tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                // allow check token
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                // 允许 GET、POST 请求获取 token，即访问端点：oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        // 要使用refresh_token的话，需要额外配置userDetailsService
        endpoints.userDetailsService(myUserDetailsService);
    }
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 1、 从数据库读取
        clients.withClientDetails(clientDetails());
        //2、   从内存读取
       /* clients.inMemory().
                withClient("demoApp").
                secret(bCryptPasswordEncoder.encode("demoAppSecret"))
                .redirectUris("http://baidu.com")
                // 密码授权模式和刷新令牌
                .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token")
                // scopes的值就是all（全部权限），read，write等权限。就是第三方访问资源的一个权限，访问范围
                .scopes("all")
                // 这个资源服务的ID，这个属性是可选的，但是推荐设置并在授权服务中进行验证。
                .resourceIds("oauth2-resource")
                //有效时间 2小时
                .accessTokenValiditySeconds(72000)
                .refreshTokenValiditySeconds(50000);*/
    }
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("demoAppSecret"));
    }

}