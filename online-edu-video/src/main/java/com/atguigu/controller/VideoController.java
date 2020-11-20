package com.atguigu.controller;

import com.atguigu.response.RetVal;
import com.atguigu.service.VideoService;
import com.sun.org.apache.bcel.internal.generic.RET;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName VideoUploadController
 * @Description: 视频上传服务
 * @Author Hao
 * @Date 2020/11/12 11:41
 * @Version V1.0
 **/
@Api(tags = "阿里云视频管理控制器")
@RestController
@RequestMapping("/aliyun/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("上传视频")
    @PostMapping("/uploadSingleVideo")
    public RetVal uploadSingleVideo(MultipartFile file) {
        String videoId = videoService.uploadSingleVideo(file);
        return RetVal.success().data("videoId", videoId);
    }

    @ApiOperation("删除单个视频")
    @DeleteMapping("/deleteSingleVideo/{videoId}")
    public RetVal deleteSingleVideo(@PathVariable("videoId") String videoId) {
        videoService.deleteSingleVideo(videoId);
        return RetVal.success();
    }

    @ApiOperation("删除多个视频")
    @DeleteMapping("/deleteMultiVideo")
    public RetVal deleteMultiVideo(@RequestParam("videoIds") List<String> videoIds) {
        videoService.deleteMultiVideo(videoIds);
        return RetVal.success();
    }

    @ApiOperation("获取视频播放凭证")
    @GetMapping("/getVideoPlayAuth/{videoId}")
    public RetVal getVideoPlayAuth(@PathVariable("videoId")String videoId){
        String playAuth=videoService.getVideoPlayAuth(videoId);
        return RetVal.success().data("playAuth",playAuth);
    }

}
