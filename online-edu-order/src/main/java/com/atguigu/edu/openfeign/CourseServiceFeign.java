package com.atguigu.edu.openfeign;

import com.atguigu.response.RetVal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CourseServiceFeign
 * @Description:
 * @Author Hao
 * @Date 2020/11/18 21:05
 * @Version V1.0
 **/
@FeignClient(value = "EDU-SERVICE")
public interface CourseServiceFeign {

    /**
     * 前端获取课程详情的接口
     * @param courseId
     * @return
     */
    @GetMapping("/edu/front/course/getCourseDetailByCourseId/{courseId}")
    public RetVal getCourseDetailByCourseId(@PathVariable("courseId") String courseId) ;
}
