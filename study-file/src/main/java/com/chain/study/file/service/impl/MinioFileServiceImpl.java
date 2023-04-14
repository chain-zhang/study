package com.chain.study.file.service.impl;

import com.chain.study.common.utils.XString;
import com.chain.study.file.service.FileService;
import io.minio.MinioClient;
import io.minio.ObjectWriteArgs;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MinioFileServiceImpl implements FileService {
    @Value("${minio.bucket}")
    private String bucket;

    @Value("${minio.readUrl}")
    private String readUrl;
    @Resource
    private MinioClient minioClient;

    @Override
    public String upload(MultipartFile file) throws Exception{
        String filename = getFileName(file.getOriginalFilename());
        ObjectWriteResponse res = minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(filename)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build());
        // 返回可访问的图片链接
        return readUrl + "/" + bucket + "/" + filename;
    }

    private String getFileName(String filename){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 设置存储对象名称
        String dir = sdf.format(new Date());
        int idx = filename.lastIndexOf(".");
        if (idx >= 0) {
            String ext = filename.substring(idx+1);
            String name = System.currentTimeMillis() + filename.substring(0, idx);
            filename = XString.md5(name) + "." + ext;
        }
        return dir + "/" +filename;
    }
}
