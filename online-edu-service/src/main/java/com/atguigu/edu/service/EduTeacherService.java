package com.atguigu.edu.service;

import com.atguigu.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author fh
 * @since 2020-11-02
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 分页查询前端讲师信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String, Object> getTeacherByPage(long pageNum, long pageSize);
}
