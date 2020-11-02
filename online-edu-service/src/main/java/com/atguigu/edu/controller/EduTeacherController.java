package com.atguigu.edu.controller;


import com.atguigu.edu.entity.EduTeacher;
import com.atguigu.edu.service.EduTeacherService;
import com.atguigu.response.RetVal;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/edu-teacher")
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
    public RetVal saveTeacher(EduTeacher eduTeacher){
        boolean flag = eduTeacherService.save(eduTeacher);
        if (flag){
            return RetVal.success();
        } else {
            return RetVal.error();
        }
    }

    @ApiOperation(value = "删除讲师")
    @DeleteMapping("{id}")
    public RetVal deleteTeacher(@PathVariable("id") Integer id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return RetVal.success();
        }else {
            return RetVal.error();
        }
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






}

