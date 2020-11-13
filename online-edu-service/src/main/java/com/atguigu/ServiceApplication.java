package com.atguigu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName ServiceApplication
 * @Description: 业务springboot启动类
 * @Author Hao
 * @Date 2020/11/2 17:55
 * @Version V1.0
 **/
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.atguigu.edu.mapper")
@SpringBootApplication
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class,args);
    }
}
