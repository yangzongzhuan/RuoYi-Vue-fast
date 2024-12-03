package com.ruoyi.common.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
public class OSSUtils {
    // 阿里云OSS自定义域名
    @Value("${alibaba.cloud.oss.accessDomain}")
    private String accessDomain;

    // AccessKey ID
    @Value("${alibaba.cloud.aliyun.secretID}")
    private String secretID;

    // AccessKey Secret
    @Value("${alibaba.cloud.aliyun.secretKey}")
    private String secretKey;

    // bucket名称
    @Value("${alibaba.cloud.oss.bucketName}")
    private String bucketName;

    /**
     *  作用：上传文件到阿里云OSS服务器
     * @param file 文件
     * @param filePath 文件上传地址
     * @throws IOException 报错信息
     */
    public void upload(MultipartFile file, String filePath) throws IOException {
        // 创建OSSClient实例
        OSS ossClient = null;

        try {
            // 创建ClientBuilderConfiguration实例，根据实际情况修改默认参数
            ClientBuilderConfiguration conf = new ClientBuilderConfiguration();

            // 设置是否支持CNAME。CNAME用于将自定义域名绑定到目标Bucket
            conf.setSupportCname(true);

            // 赋值OSSClient实例
            ossClient = new OSSClientBuilder().build(accessDomain,secretID,secretKey,conf);

            // 获取文件流
            InputStream inputStream = file.getInputStream();

            //调用方法实现上传
            ossClient.putObject(bucketName, filePath, inputStream);
        } catch (ClientException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if(ossClient != null) {
                // 关闭OSSClient
                ossClient.shutdown();
            }
        }
    }

}
