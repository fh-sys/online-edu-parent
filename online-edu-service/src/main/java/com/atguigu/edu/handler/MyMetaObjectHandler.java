package com.atguigu.edu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName MyMetaObjectHandler
 * @Description: 自动填充配置类
 * @Author Hao
 * @Date 2020/11/3 21:13
 * @Version V1.0
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmt_create", new Date(), metaObject);
        this.setFieldValByName("gmt_modified", new Date(), metaObject);
        this.setFieldValByName("isDeleted", 0, metaObject);
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmt_modified", new Date(), metaObject);
    }
}
