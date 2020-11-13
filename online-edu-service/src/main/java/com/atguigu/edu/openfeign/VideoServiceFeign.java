package com.atguigu.edu.openfeign;

import com.atguigu.response.RetVal;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName VideoServiceFeign
 * @Description: 视频服务远程调用
 * @Author Hao
 * @Date 2020/11/12 16:21
 * @Version V1.0
 **/
@FeignClient(value = "EDU-VIDEO")
public interface VideoServiceFeign {
    @PostMapping("/aliyun/video/uploadSingleVideo")
    RetVal uploadSingleVideo(MultipartFile file);

    @DeleteMapping("/aliyun/video/deleteSingleVideo/{videoId}")
    RetVal deleteSingleVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/aliyun/video/deleteMultiVideo")
    RetVal deleteMutilVideo(@RequestParam("videoIds") List<String> videoIds);
}
