package com.atguigu.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName VideoService
 * @Description:
 * @Author Hao
 * @Date 2020/11/12 12:37
 * @Version V1.0
 **/
public interface VideoService {

    /**
     * 上传视频
     * @param file
     * @return
     */
    String uploadSingleVideo(MultipartFile file) ;

    /**
     * 删除单个视频
     * @param videoId
     */
    void deleteSingleVideo(String videoId) ;

    /**
     * 删除多个视频
     * @param videoIds
     */
    void deleteMultiVideo(List<String> videoIds);

    /**
     * 获取视频播放凭证
     * @param videoId
     * @return
     */
    String getVideoPlayAuth(String videoId);
}
