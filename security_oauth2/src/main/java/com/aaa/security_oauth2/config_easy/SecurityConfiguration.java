package com.aaa.security_oauth2.config_easy;


import com.aaa.security_oauth2.service.MyAuthenticationProvider;
import com.aaa.security_oauth2.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHander;

    //注入我们自己的AuthenticationProvider
    
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Resource
    private DataSource dataSource;


    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**").and().authorizeRequests()
                .antMatchers("/oauth/**").authenticated().and().formLogin().
                permitAll();
     //一般用于认证的服务器不需要  设置  token访问
        // 开启oauth2.0 接口验证
      /*  http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).
                permitAll().
                anyRequest().authenticated().
                and().httpBasic();*/

    }
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
          auth.authenticationProvider(myAuthenticationProvider);
       /* auth
                .inMemoryAuthentication()
                .withUser("admin").password("123456").roles("USER")
                .and()
                .withUser("test").password("test123").roles("ADMIN");*/
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        final HttpSecurity http = getHttp();
        web.ignoring()
                /* 一般用于服务调用，忽略验证接口 */
                .antMatchers("/v2/*","/user/**");
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * 记住我功能的token存取器配置  只是 secrity授权未采用 oauth2
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
        //
        // tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
    /**
     * *需要配置这个支持password模式 support password grant type
     */
    @Override @Bean public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    /**
     * 全局用户信息
     * @param auth 认证管理
     * @throws Exception 用户认证异常信息
     */
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
