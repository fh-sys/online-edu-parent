package com.atguigu.edu.controller;


import com.atguigu.entity.EduCourse;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.condition.CourseCondition;
import com.atguigu.vo.CourseInfoVo;
import com.atguigu.vo.PublishCourseVO;
import com.atguigu.response.RetVal;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author fh
 * @since 2020-11-09
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @ApiOperation("分页带条件查询课程信息")
    @PostMapping("/getAllCourseByPageAndCondition/{pageNum}/{pageSize}")
    public RetVal getAllCourseByPageAndCondition(@PathVariable("pageNum") long pageNum,
                                                 @PathVariable("pageSize") long pageSize,
                                                 CourseCondition courseCondition) {
        Page<EduCourse> eduCoursePage = new Page<>(pageNum, pageSize);
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseCondition.getTitle())) {
            courseQueryWrapper.like("title", courseCondition.getTitle());
        }
        if (!StringUtils.isEmpty(courseCondition.getStatus())) {
            courseQueryWrapper.eq("status", courseCondition.getStatus());
        }
        courseService.page(eduCoursePage, courseQueryWrapper);
        return RetVal.success().data("courseList", eduCoursePage.getRecords()).data("total", eduCoursePage.getTotal());
    }

    @ApiOperation("根据id查询课程信息")
    @GetMapping("{courseId}")
    public RetVal getCourseById(@PathVariable("courseId") String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseById(courseId);
        return RetVal.success().data("courseInfo", courseInfoVo);
    }

    @ApiOperation("添加课程")
    @PostMapping
    public RetVal saveCourseInfo(CourseInfoVo courseInfoVo) {
        String courseId = courseService.saveCourseInfo(courseInfoVo);
        return RetVal.success().data("courseId", courseId);
    }

    @ApiOperation("修改课程信息")
    @PutMapping
    public RetVal updateCourseInfo(CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfoById(courseInfoVo);
        return RetVal.success();
    }
    @ApiOperation("查询发布课程信息")
    @GetMapping("/getPublishCourseInfo/{courseId}")
    public RetVal getPublishCourseInfo(@PathVariable("courseId") String courseId){
       PublishCourseVO publishCourseVO = courseService.getPublishCourseInfo(courseId);
       return RetVal.success().data("publishCourseVO",publishCourseVO);
    }

    @ApiOperation("发布课程")
    @PostMapping("publishCourse")
    public RetVal publishCourse(PublishCourseVO publishCourseVO){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(publishCourseVO.getCourseId());
        eduCourse.setStatus("Normal");
        boolean flag = courseService.updateById(eduCourse);
        if (flag) {
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }

    @ApiOperation("删除课程")
    @DeleteMapping("{courseId}")
    public RetVal deleteCourseByCourseId(@PathVariable("courseId")String courseId){
         courseService.deleteCourseByCourseId(courseId);
        return RetVal.success();
    }
}

