package com.example.security_aouth2_client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Create date 2017/9/20
 * <p>Spring Security 初始化权限配置</p>
 *
 * @author zhiheng.li
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入AditorzAware，获取当前操作用户
     * @param authenticationTrustResolver
     * @return
     */
    @Bean
    public AuditorAware<String> auditorAwareBean(@Autowired AuthenticationTrustResolver authenticationTrustResolver) {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authenticationTrustResolver.isAnonymous(authentication)) {
                return Optional.of("@SYSTEM");
            }
            Object principal = authentication.getPrincipal();
            if (principal instanceof String) {
                return Optional.of((String) principal);
            } else if (principal instanceof CustomPrincipal) {
                return Optional.ofNullable(((CustomPrincipal) principal).getId().toString());
            } else {
                return Optional.ofNullable(String.valueOf(principal));
            }
        };
    }


}
