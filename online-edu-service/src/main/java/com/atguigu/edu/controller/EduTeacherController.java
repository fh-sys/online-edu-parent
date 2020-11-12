package com.atguigu.edu.controller;


import com.atguigu.edu.entity.EduTeacher;
import com.atguigu.edu.service.EduTeacherService;
import com.atguigu.edu.vo.TeacherConditionVO;
import com.atguigu.handler.MyRuntimeException;
import com.atguigu.response.RetVal;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author fh
 * @since 2020-11-02
 */
@Api(tags = "讲师管理模块")
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "没有统一结果集，查询所有讲师的信息")
    @GetMapping("getTeacherList")
    public List<EduTeacher> getTeachers(){
        return eduTeacherService.list(null);
    }

    @ApiOperation(value = "查询所有讲师信息")
    @GetMapping
    public RetVal getAllTeacher(){

        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return RetVal.success().data("teacherList",teacherList);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping
    public RetVal saveTeacher(EduTeacher eduTeacher) throws MyRuntimeException {
        boolean flag = eduTeacherService.save(eduTeacher);
        if (flag){
            return RetVal.success();
        } else {
            return RetVal.error();
        }
    }

    @ApiOperation(value = "删除讲师")
    @DeleteMapping("{id}")
    public RetVal deleteTeacher(@PathVariable("id") String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }
    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("{id}")
    public RetVal getTeacherById(@PathVariable("id") String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return RetVal.success().data("teacher",eduTeacher);
    }

    @ApiOperation(value = "根据id修改讲师")
    @PutMapping
    public RetVal updateTeacher(EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }

    @ApiOperation(value = "分页查询讲师信息")
    @GetMapping("/getTeacherByPage/{pageNum}/{pageSize}")
    public RetVal getTeacherByPage(@PathVariable("pageNum")long pageNum,
                                   @PathVariable("pageSize")long pageSize){
        IPage<EduTeacher> eduTeacherPage = new Page<>(pageNum,pageSize);
        eduTeacherService.page(eduTeacherPage,null);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> teacherList = eduTeacherPage.getRecords();
        return RetVal.success().data("total",total).data("teacherList",teacherList);
    }

    @ApiOperation(value = "分页带条件查询讲师信息")
    @PostMapping("/getTeacherByPageAndCondition/{pageNum}/{pageSize}")
    public RetVal getTeacherByPageAndCondition(TeacherConditionVO teacherConditionVO,
                                               @PathVariable("pageNum") long pageNum,
                                               @PathVariable("pageSize") long pageSize){
        //判断前端传输对象中的天剑时候为空，做条件查询
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String conditionName = teacherConditionVO.getName();
        Integer conditionVOLevel = teacherConditionVO.getLevel();
        Date conditionBeginTime = teacherConditionVO.getBeginTime();
        Date conditionEndTime = teacherConditionVO.getEndTime();

        IPage<EduTeacher> eduTeacherPage = new Page<>(pageNum,pageSize);

        if (!StringUtils.isEmpty(conditionName)){
            wrapper.like("name",conditionName);
        }
        if (!StringUtils.isEmpty(conditionVOLevel)){
            wrapper.eq("level",conditionVOLevel);
        }
        if (!StringUtils.isEmpty(conditionBeginTime)){
            wrapper.between("gmt_create",conditionBeginTime,conditionEndTime);
        }
        eduTeacherService.page(eduTeacherPage,wrapper);
        List<EduTeacher> teacherList = eduTeacherPage.getRecords();
        long total = eduTeacherPage.getTotal();
        return RetVal.success().data("total",total).data("teacherList",teacherList);
    }






}

