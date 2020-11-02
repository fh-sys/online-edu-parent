package com.atguigu.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName EduConfig
 * @Description: 配置文件
 * @Author Hao
 * @Date 2020/11/2 19:08
 * @Version V1.0
 **/
@Configuration
public class EduConfig {

    /**
     * 配置mp逻辑删除插件
     * @return
     */
    @Bean
    public ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }

    /**
     * 配置mp分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
