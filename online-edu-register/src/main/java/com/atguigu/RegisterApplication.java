package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName RegisterApplication
 * @Description: springboot注册中心启动类
 * @Author Hao
 * @Date 2020/11/12 15:23
 * @Version V1.0
 **/
//@EnableEurekaServer
@SpringBootApplication
@EnableDiscoveryClient
public class RegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class,args);
    }
}
