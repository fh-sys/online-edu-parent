package com.atguigu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName OssService
 * @Description: 文件上传
 * @Author Hao
 * @Date 2020/11/6 19:01
 * @Version V1.0
 **/
public interface OssService {
    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    String uploadFile(MultipartFile file) throws IOException;

}
