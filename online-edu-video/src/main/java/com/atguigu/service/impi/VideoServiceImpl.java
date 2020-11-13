package com.atguigu.service.impi;

import com.aliyuncs.exceptions.ClientException;
import com.atguigu.handler.MyRuntimeException;
import com.atguigu.service.VideoService;
import com.atguigu.utils.VideoUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName VideoServiceImpl
 * @Description: 视频上传实现类
 * @Author Hao
 * @Date 2020/11/12 12:37
 * @Version V1.0
 **/
@Service
public class VideoServiceImpl implements VideoService {

    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;


    @Override
    public String uploadSingleVideo(MultipartFile file) {
        String videoId = null;
        try {
            String filename = file.getOriginalFilename();
            String title = filename.substring(0, filename.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            videoId = VideoUtils.uploadSingleVideo(accessKeyId, accessKeySecret, filename, title, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyRuntimeException(20001, "视频上传失败");
    }
        return videoId;
    }

    @Override
    public void deleteSingleVideo(String videoId)  {
        try {
            VideoUtils.deleteSingleVideo(videoId, accessKeyId, accessKeySecret);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new MyRuntimeException(20001, "视频删除失败");
        }
    }

    @Override
    public void deleteMultiVideo(List<String> videoIds) {
        try {
            VideoUtils.deleteMultiVideo(videoIds, accessKeyId, accessKeySecret);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new MyRuntimeException(20001, "视频删除失败");
        }
    }
}
