package com.chain.study.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    String upload(MultipartFile file) throws Exception;
}
