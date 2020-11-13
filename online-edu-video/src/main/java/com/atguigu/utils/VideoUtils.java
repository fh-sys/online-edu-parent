package com.atguigu.utils;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName VideoUtils
 * @Description:
 * @Author Hao
 * @Date 2020/11/12 13:54
 * @Version V1.0
 **/
public class VideoUtils {


    /**
     * 初始化客户端
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws ClientException
     */
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        // 点播服务接入区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }


    /**
     * 上传单个视频
     *
     * @param
     * @param inputStream
     * @return
     */
    public static String uploadSingleVideo(String accessKeyId, String accessKeySecret, String fileName, String title, InputStream inputStream) throws ClientException {
        //得到一个网络流的请求
        String videoId = "";
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        if (response.isSuccess()) {
            videoId = response.getVideoId();
        }
        return videoId;
    }


    public static void deleteMultiVideo(List<String> videoIds, String accessKeyId, String accessKeySecret) throws ClientException {
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        DeleteVideoResponse response = new DeleteVideoResponse();
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        String multiVideoIdString = StringUtils.join(videoIds, ",");
        request.setVideoIds(multiVideoIdString);
        response = client.getAcsResponse(request);
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    public static void deleteSingleVideo(String videoId, String accessKeyId, String accessKeySecret) throws ClientException {
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        DeleteVideoResponse response = new DeleteVideoResponse();
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoId);
        response = client.getAcsResponse(request);
    }
}
