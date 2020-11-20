package com.atguigu.edu.controller;


import com.atguigu.entity.EduSubject;
import com.atguigu.edu.service.EduSubjectService;
import com.atguigu.response.RetVal;
import com.atguigu.response.SubjectResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author fh
 * @since 2020-11-06
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation("从Excel中导入课程分类信息")
    @PostMapping("/importSubject")
    public RetVal importSubject(MultipartFile file) throws IOException {
        subjectService.importByExcel(file);
        return RetVal.success().message("数据导入成功");
    }

    @ApiOperation("查询所有的课程分类信息")
    @GetMapping("getAllSubject")
    public RetVal getAllSubject(){
        List<SubjectResponse> subjectResponseList = subjectService.getAllSubject();
        return RetVal.success().data("subjectList",subjectResponseList);
    }

    @PostMapping("/saveParentSubject")
    @ApiOperation("添加一级分类信息")
    public RetVal saveParentSubject(EduSubject eduSubject){
        boolean flag = subjectService.saveParentSubject(eduSubject);
        if (flag){
            return RetVal.success().message("一级分类添加成功");
        }else {
            return RetVal.error();
        }
    }
    @PostMapping("/saveChildrenSubject")
    @ApiOperation("添加二级分类信息")
    public RetVal saveChildrenSubject(EduSubject eduSubject){
        boolean flag = subjectService.saveChildrenSubject(eduSubject);
        if (flag){
            return RetVal.success().message("二级分类添加成功");
        }else {
            return RetVal.error();
        }
    }

    @ApiOperation("根据id删除课程分类信息")
    @DeleteMapping("{subjectId}")
    public RetVal deleteSubjectById(@PathVariable("subjectId")String subjectId){
        boolean flag = subjectService.deleteSubjectById(subjectId);
        if (flag){
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }

    @ApiOperation("修改课程分类的信息")
    @PutMapping
    public RetVal updateSubjectById(EduSubject eduSubject){
        boolean flag = subjectService.updateById(eduSubject);
        if (flag) {
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }


}

