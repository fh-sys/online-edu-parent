package com.atguigu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName StatisticsApplication
 * @Description: springboot启动类
 * @Author Hao
 * @Date 2020/11/13 21:28
 * @Version V1.0
 **/
@MapperScan("com.atguigu.edu.mapper")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class StatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class,args);
    }
}
