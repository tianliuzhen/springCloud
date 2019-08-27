package com.aaa.security_oauth2.config.config_oauth;


import com.aaa.security_oauth2.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * description: oauth2  配置
 *
 * @author 田留振(tianliuzhen @ haoxiaec.com)
 * @version 1.0
 * @date 2019/6/28
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AccessTokenConverter accessTokenConverter;

    @Autowired
    private ClientDetailsService clientDetails;

    @Resource
    private DataSource dataSource;

   /* @Autowired
    private AuthorizationEndpoint authorizationEndpoint;
    @PostConstruct
    public void init() {
        authorizationEndpoint.setUserApprovalPage("forward:/oauth/approvale/confirm");
        authorizationEndpoint.setErrorPage("forward:/oauth/approvale/error");
    }*/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.
                // code授权添加 一般建议添加。但是测试不添加貌似也可以
                //当开启basic验证时，如果访问某个受保护的资源，资源服务器会给出如下的响应信息
                realm("oauth2-resources")
                /* 配置token获取合验证时的策略 */
                .tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                // allow check token
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // authenticationManager：认证管理器。若我们上面的Grant
        // Type设置为password，则需设置一个AuthenticationManager对象
        // userDetailsService：若是我们实现了UserDetailsService,来管理用户信息，那么得设我们的userDetailsService对象
        // authorizationCodeServices：授权码服务。若我们上面的Grant
        // Type设置为authorization_code，那么得设一个AuthorizationCodeServices对象
        // tokenStore：这个就是我们上面说到，把我们想要是实现的Access Token类型设置
        // accessTokenConverter：Access Token的编码器。也就是JwtAccessTokenConverter
        // tokenEnhancer:token的拓展。当使用jwt时候，可以实现TokenEnhancer来进行jwt对包含信息的拓展
        // tokenGranter：当默认的Grant Type已经不够我们业务逻辑，实现TokenGranter 接口，授权将会由我们控制，并且忽略Grant
        // Type的几个属性。
        endpoints.authenticationManager(authenticationManager)
                // 允许 GET、POST 请求获取 token，即访问端点：oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // 要使用refresh_token的话，需要额外配置userDetailsService
                .userDetailsService(userDetailsService)
                // 指定token存储位置
                .tokenStore(tokenStore)
                // 配置JwtAccessToken转换器
                //配置后 token将会变的特别长 一般用不用即可
                // .accessTokenConverter(accessTokenConverter)
                .setClientDetailsService(clientDetails);
         endpoints.tokenEnhancer(tokenEnhancer());
                //

    }
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 1、 从数据库读取
       //表中存储的secret值是加密后的值，并非明文；
        //但是如果 http://localhost:8080/oauth/token  里的参数确实明文
        clients.withClientDetails(clientDetails());
        //2、   从内存读取
       /* clients.inMemory().
                withClient("demoApp").
                secret(bCryptPasswordEncoder.encode("demoAppSecret"))
                .redirectUris("http://baidu.com")
                // 密码授权模式和刷新令牌
                .authorizedGrantTypes("authorization_code", "client_credentials", "password","implicit", "refresh_token")
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