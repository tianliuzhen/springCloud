package com.aaa.securityweb.config;


import com.aaa.securityweb.service.MyAuthenticationProvider;
import com.aaa.securityweb.service.MyUserDetailsService;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@EnableWebSecurity
@Order
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter  implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println(":::::::::::::::::::::::::WebSecurityConfigurerAdapter::::::::::::::::::::::::::::");
    }
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        //super.configure(http);
        http    //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
               // loginPage("/login")表示登录时跳转的页面，因为登录页面我们不需要登录认证，所以我们需要添加 permitAll() 方法。
                .formLogin().loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll()
                // .successForwardUrl("/success")  //登录成功跳转的页面
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHander)
                .and()
                .authorizeRequests()
                .antMatchers("/index").permitAll()
                /*
                * .antMatchers 这里也可以限定HttpMethod的不同要求不同的权限（用于适用于Restful风格的API).
                   如：Post需要 管理员权限，get 需要user权限，我们可以这么个改造，同时也可以通过通配符来是实现 如：/user/1 这种带参数的URL
                * */
                //    .antMatchers(HttpMethod.POST,"/user/*").hasRole("ADMIN")
                //这就表示/whoim的这个资源需要有ROLE_ADMIN的这个角色才能访问。不然就会提示拒绝访问
                .antMatchers("/whoim").hasRole("ADMIN")
                //必须经过认证以后才能访问
                .anyRequest()
                .authenticated()
                .and()
                .rememberMe()
                //表单里面  name="remember-me"   写死该默认字段
                .rememberMeParameter("remember-me").userDetailsService(myUserDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)
                .and()
                .csrf().disable();
                //当我们登陆时勾选自动登录时，会自动在 Cookie 中保存一个名为 remember-me 的cookie，默认有效期为2周，其值是一个加密字符串：
        //默认token数据库配置
/*        CREATE TABLE `persistent_logins` (
                `username` varchar(64) NOT NULL,
        `series` varchar(64) NOT NULL,
        `token` varchar(64) NOT NULL,
        `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;*/

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
                .antMatchers("/v2/*","/user/**","/test");
    }
    /**
     *  写在这里 java -jar 运行时
     *  在MyAuthenticationProvider  会报错（无法引用正在创建的bean）
     *  但是idea运行不会有问题
     * @param
     * @return
     */

    // TODO: 2019/8/6
  /*  @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println(":::::::::::::::::::::::::passwordEncoder::::::::::::::::::::::::::::");
        return new BCryptPasswordEncoder();
    }*/
    /**
     * 记住我功能的token存取器配置
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


}
