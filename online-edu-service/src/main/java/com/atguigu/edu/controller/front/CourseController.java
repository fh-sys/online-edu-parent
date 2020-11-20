package com.atguigu.edu.controller.front;

import com.atguigu.entity.EduCourse;
import com.atguigu.edu.service.EduChapterService;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.service.EduTeacherService;
import com.atguigu.vo.ChapterVO;
import com.atguigu.vo.CourseDetailInfoVO;
import com.atguigu.response.RetVal;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CourseController
 * @Description: 前端课程控制器
 * @Author Hao
 * @Date 2020/11/16 19:28
 * @Version V1.0
 **/
@Api(tags = "前端课程控制器")
@RestController
@CrossOrigin
@RequestMapping("/edu/front/course")
public class CourseController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @GetMapping("/getCourseByLimit")
    public RetVal getCourseByLimit() {
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(courseQueryWrapper);
        return RetVal.success().data("courseLimitList",courseList);
    }

    @GetMapping("/getCourseByPage/{pageNum}/{pageSize}")
    public RetVal getTeacherByPage(@PathVariable("pageNum") long pageNum,
                                   @PathVariable("pageSize") long pageSize) {
        Map<String, Object> retMap = courseService.getCourseByPage(pageNum, pageSize);
        return RetVal.success().data(retMap);
    }

    @GetMapping("/getCourseDetailByCourseId/{courseId}")
    public RetVal getCourseDetailByCourseId(@PathVariable("courseId") String courseId) {
        //查询课程的基本信息
        CourseDetailInfoVO courseDetailInfoVO  = courseService.getCourseDetailInfoById(courseId);
        //查询章节小节信息
        List<ChapterVO> chapterAndSectionList = chapterService.getChapterAndSection(courseId);
        return RetVal.success().data("courseDetailInfo",courseDetailInfoVO).data("chapterAndSectionList",chapterAndSectionList);
    }
}
