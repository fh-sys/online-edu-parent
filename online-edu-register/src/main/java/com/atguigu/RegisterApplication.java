package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName RegisterApplication
 * @Description: springboot注册中心启动类
 * @Author Hao
 * @Date 2020/11/12 15:23
 * @Version V1.0
 **/
@EnableEurekaServer
@SpringBootApplication
public class RegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class,args);
    }
}
