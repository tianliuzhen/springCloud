package com.aaa.bootthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@tk.mybatis.spring.annotation.MapperScan("com.aaa.bootthymeleaf.mapper")
@SpringBootApplication
public class BootThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootThymeleafApplication.class, args);
    }

}
