package com.example.ant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心启动类
 */
@SpringBootApplication
@EnableEurekaServer
public class AntEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntEurekaApplication.class, args);
    }
}
