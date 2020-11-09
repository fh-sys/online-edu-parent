package com.atguigu.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName OssTemplate
 * @Description: 阿里云上传文件工具类
 * @Author Hao
 * @Date 2020/11/6 18:42
 * @Version V1.0
 **/
@Component
public class OssTemplate {

    @Value("gulixueyuan2020")
    private String bucketName;
    @Value("oss-cn-shenzhen.aliyuncs.com")
    private String endpoint;
    @Value("LTAI4GDwakZfc4ws4xRNZqWA")
    private String accessKeyId;
    @Value("N7F3riR7uLU21aoJ0ZqJpZIJRbC5HW")
    private String accessKeySecret;

    /**
     * 文件上传
     * @param
     * @throws IOException
     */
    public String uploadFile(String fileName,InputStream inputStream) throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建PutObjectRequest对象。

        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);
        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
        //https://gulixueyuan2020.oss-cn-shenzhen.aliyuncs.com/589b2d0827fe4f0388cb0e5df3e6138cfile.png
        return "http://"+bucketName+"."+endpoint+File.separator+fileName;

    }

    /**
     * 根据文件名删除文件
     * @param fileName
     */
    public void deleteFile(String fileName){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, fileName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
