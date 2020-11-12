package com.atguigu.edu.service;

import com.atguigu.edu.entity.EduCourse;
import com.atguigu.edu.vo.CourseInfoVo;
import com.atguigu.edu.vo.PublishCourseVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author fh
 * @since 2020-11-09
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 根据课程id查询课程信息
     * @param courseId
     * @return
     */
    CourseInfoVo getCourseById(String courseId);

    /**
     * 添加课程信息
     * @param courseInfoVo
     * @return
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 修改课程信息
     * @param courseInfoVo
     * @return
     */
    void updateCourseInfoById(CourseInfoVo courseInfoVo);

    /**
     * 获取发布课程信息
     * @param courseId
     * @return
     */
    PublishCourseVO getPublishCourseInfo(String courseId);
}
