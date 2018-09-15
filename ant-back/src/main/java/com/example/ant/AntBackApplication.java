package com.example.ant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 服务启动类
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AntBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntBackApplication.class, args);
    }
}
