package com.weikbest.pro.saas.applet.comm.controller;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.annotation.token.AppToken;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wisdomelon
 * @date 2019/6/28 0028
 * @project mystery-boxes
 * @jdk 1.8
 */
@Slf4j
@Api(tags = {"App——文件上传接口"})
@RestController
@RequestMapping("/appupload")
public class AppUploadController {

    @Autowired
    private ThirdConfigService thirdConfigService;

    @AppToken
    @ApiOperation(value = "将图片输出为base64格式并返回")
    @PostMapping("/coverImg")
    public DataResp<String> coverImg(
            @ApiParam(name = "img", value = "文件", required = true)
            @RequestParam("img") MultipartFile imgFile) {

        if (imgFile == null || imgFile.isEmpty()) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL);
        }

        String encode = null;
        try {
            encode = Base64.encode(imgFile.getBytes());
        } catch (IOException e) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL);
        }

        return DataResp.ok(encode);
    }

    @AppToken
    @ApiOperation(value = "将图片上传到指定路径后返回uploadUrl")
    @PostMapping("/uploadImg/{uploadDir}")
    public DataResp<String> uploadImg(
            @ApiParam(name = "uploadDir", value = "上传文件夹名称", required = true)
            @PathVariable("uploadDir") String uploadDir,
            @ApiParam(name = "img", value = "文件", required = true)
            @RequestParam("img") MultipartFile imgFile) {

        if (imgFile == null || imgFile.isEmpty()) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL);
        }

        String uploadUrl = thirdConfigService.aliyunOssService().uploadFileAvatar(imgFile, uploadDir);
        return DataResp.ok(uploadUrl);
    }


    @AppToken
    @ApiOperation(value = "将图片上传到指定路径后返回uploadUrl,支持两级路径")
    @PostMapping("/uploadImg/{uploadDir}/{subDir}")
    public DataResp<String> uploadImg(
            @ApiParam(name = "uploadDir", value = "上传文件夹名称", required = true)
            @PathVariable("uploadDir") String uploadDir,
            @ApiParam(name = "subDir", value = "上传子文件夹名称", required = true)
            @PathVariable("subDir") String subDir,
            @ApiParam(name = "img", value = "文件", required = true)
            @RequestParam("img") MultipartFile imgFile) {

        if (imgFile == null || imgFile.isEmpty()) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL);
        }

        String uploadUrl = thirdConfigService.aliyunOssService().uploadFileAvatar(imgFile, StrUtil.join("/", uploadDir, subDir));
        return DataResp.ok(uploadUrl);
    }

    @AppToken
    @ApiOperation(value = "批量将图片上传到指定路径后返回uploadUrl")
    @PostMapping("/uploadBatchImg/{uploadDir}")
    public DataResp<List<String>> uploadBatchImg(
            @ApiParam(name = "uploadDir", value = "上传文件夹名称", required = true)
            @PathVariable("uploadDir") String uploadDir,
            @ApiParam(name = "imgs", value = "多个文件", required = true)
            @RequestParam("imgs") List<MultipartFile> imgFiles) {

        if (CollectionUtil.isEmpty(imgFiles)) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL);
        }

        List<String> uploadUrls = imgFiles.stream().map(imgFile -> thirdConfigService.aliyunOssService().uploadFileAvatar(imgFile, uploadDir)).collect(Collectors.toList());
        return DataResp.ok(uploadUrls);
    }


    @AppToken
    @ApiOperation(value = "批量将图片上传到指定路径后返回uploadUrl,支持两级路径")
    @PostMapping("/uploadBatchImg/{uploadDir}/{subDir}")
    public DataResp<List<String>> uploadBatchImg(
            @ApiParam(name = "uploadDir", value = "上传文件夹名称", required = true)
            @PathVariable("uploadDir") String uploadDir,
            @ApiParam(name = "subDir", value = "上传子文件夹名称", required = true)
            @PathVariable("subDir") String subDir,
            @ApiParam(name = "imgs", value = "多个文件", required = true)
            @RequestParam("imgs") List<MultipartFile> imgFiles) {

        if (CollectionUtil.isEmpty(imgFiles)) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL);
        }

        List<String> uploadUrls = imgFiles.stream().map(imgFile -> thirdConfigService.aliyunOssService().uploadFileAvatar(imgFile, StrUtil.join("/", uploadDir, subDir))).collect(Collectors.toList());
        return DataResp.ok(uploadUrls);
    }
}
