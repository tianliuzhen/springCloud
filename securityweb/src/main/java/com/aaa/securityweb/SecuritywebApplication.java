package com.aaa.securityweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecuritywebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuritywebApplication.class, args);
    }
    /**
     *  本来放在 SecurityConfiguration 里面
     *  但是 用java -jar 执行会报错(注入bean的顺序问题)  idea运行不会报错
     * @param
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
