package com.atguigu.controller;

import com.atguigu.response.RetVal;
import com.atguigu.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName OssController
 * @Description: oss控制器
 * @Author Hao
 * @Date 2020/11/6 18:58
 * @Version V1.0
 **/
@Api(tags= "阿里云文件上传")
@RestController
@RequestMapping("/oss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("文件上传接口")
    @PostMapping("/uploadFile")
    public RetVal uploadFile(MultipartFile file) throws IOException {
        String retUrl = ossService.uploadFile(file);
        return RetVal.success().data("retUrl",retUrl);
    }
}
