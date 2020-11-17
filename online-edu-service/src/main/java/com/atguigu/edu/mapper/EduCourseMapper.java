package com.atguigu.edu.mapper;

import com.atguigu.edu.entity.EduCourse;
import com.atguigu.edu.vo.CourseDetailInfoVO;
import com.atguigu.edu.vo.PublishCourseVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author fh
 * @since 2020-11-09
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 根据课程id查询发布课程信息，包括讲师名称，课程分类
     * @param courseId
     * @return
     */
    PublishCourseVO selectPublishCourseInfoByCourseId(String courseId);

    /**
     * 根据课程id查询课程详细信息
     * @param courseId
     * @return
     */
    CourseDetailInfoVO selectCourseDetailInfoById(String courseId);
}
