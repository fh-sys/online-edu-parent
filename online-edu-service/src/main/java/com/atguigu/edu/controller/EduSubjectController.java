package com.atguigu.edu.controller;


import com.atguigu.edu.service.EduSubjectService;
import com.atguigu.response.RetVal;
import com.atguigu.response.SubjectResponse;
import io.swagger.annotations.Api;
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

    @PostMapping("/importSubject")
    public RetVal importSubject(MultipartFile file) throws IOException {
        subjectService.importByExcel(file);
        return RetVal.success().message("数据导入成功");
    }
    @GetMapping("getAllSubject")
    public RetVal getAllSubject(){
        List<SubjectResponse> subjectResponseList = subjectService.getAllSubject();
        return RetVal.success().data("subjectList",subjectResponseList);
    }
}

