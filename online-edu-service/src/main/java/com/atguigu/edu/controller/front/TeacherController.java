package com.atguigu.edu.controller.front;

import com.atguigu.entity.EduCourse;
import com.atguigu.entity.EduTeacher;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.service.EduTeacherService;
import com.atguigu.response.RetVal;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TeacherController
 * @Description: 前端教师控制器
 * @Author Hao
 * @Date 2020/11/16 16:55
 * @Version V1.0
 **/
@Api(tags = "前端教师控制器")
@RestController
@RequestMapping("/edu/front/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @GetMapping("/getTeacherByLimit")
    public RetVal getTeacherByLimit() {
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.last("limit 8");
        List<EduTeacher> teacherList = teacherService.list(teacherQueryWrapper);
        return RetVal.success().data("teacherLimitList",teacherList);
    }

    @GetMapping("/getTeacherByPage/{pageNum}/{pageSize}")
    public RetVal getTeacherByPage(@PathVariable("pageNum") long pageNum,
                                   @PathVariable("pageSize") long pageSize) {
        Map<String, Object> retMap = teacherService.getTeacherByPage(pageNum, pageSize);
        return RetVal.success().data(retMap);
    }

    @GetMapping("/getTeacherDetailByTeacherId/{teacherId}")
    public RetVal getTeacherDetailByTeacherId(@PathVariable("teacherId") String teacherId) {
        //查询讲师的基本信息
        EduTeacher teacherInfo = teacherService.getById(teacherId);

        //根据讲师id查询讲师所讲的课程
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = courseService.list(courseQueryWrapper);
        return RetVal.success().data("teacherInfo", teacherInfo).data("courseList",courseList);
    }

}
