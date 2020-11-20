package com.atguigu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName OrderApplication
 * @Description: sprigboot启动类
 * @Author Hao
 * @Date 2020/11/18 21:07
 * @Version V1.0
 **/
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.atguigu.edu.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
