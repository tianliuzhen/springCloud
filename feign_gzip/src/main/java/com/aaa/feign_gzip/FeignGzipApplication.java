package com.aaa.feign_gzip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignGzipApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignGzipApplication.class, args);
    }

}
