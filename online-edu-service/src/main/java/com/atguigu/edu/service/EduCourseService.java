package com.atguigu.edu.service;

import com.atguigu.entity.EduCourse;
import com.atguigu.vo.CourseDetailInfoVO;
import com.atguigu.vo.CourseInfoVo;
import com.atguigu.vo.PublishCourseVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

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

    /**
     * 根据id删除课程包括章节小节
     * @param courseId
     * @return
     */
    void deleteCourseByCourseId(String courseId);


    /**
     * 前端分页查询课程信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String, Object> getCourseByPage(long pageNum, long pageSize);

    /**
     * 前端根据课程id查询课程详细信息
     * @param courseId
     * @return
     */
    CourseDetailInfoVO getCourseDetailInfoById(String courseId);
}
