package com.chain.study.file.controller;

import com.chain.study.common.api.CommonResult;
import com.chain.study.common.api.ResultCode;
import com.chain.study.file.service.FileService;
import com.chain.study.file.service.impl.MinioFileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/minio")
public class MinioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);

    @Resource(type = MinioFileServiceImpl.class)
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(@RequestPart("file") MultipartFile file) {
        try {
            return CommonResult.success(fileService.upload(file));
        } catch (Exception e) {
            LOGGER.error("upload file exception {}", e.getMessage(), e);
            return CommonResult.failed(ResultCode.FAILED);
        }

    }
}