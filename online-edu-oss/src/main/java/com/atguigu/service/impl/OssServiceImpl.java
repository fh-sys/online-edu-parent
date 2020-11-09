package com.atguigu.service.impl;

import com.atguigu.oss.OssTemplate;
import com.atguigu.response.RetVal;
import com.atguigu.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName OssServiceImpl
 * @Description: 业务实现类
 * @Author Hao
 * @Date 2020/11/6 19:02
 * @Version V1.0
 **/
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssTemplate ossTemplate;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        //随机生成文件名
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String ext = originalFilename.substring(originalFilename.lastIndexOf(originalFilename));
        String fileNameExt= uuid+ext;
        String retUrl = ossTemplate.uploadFile(fileNameExt, inputStream);
        return retUrl;
    }
}
