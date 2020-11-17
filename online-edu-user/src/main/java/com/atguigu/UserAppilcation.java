package com.atguigu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName UserAppilcation
 * @Description: springboot用户启动类
 * @Author Hao
 * @Date 2020/11/13 20:51
 * @Version V1.0
 **/
@MapperScan("com.atguigu.edu.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class UserAppilcation {

    public static void main(String[] args) {
        SpringApplication.run(UserAppilcation.class,args);
    }
}
