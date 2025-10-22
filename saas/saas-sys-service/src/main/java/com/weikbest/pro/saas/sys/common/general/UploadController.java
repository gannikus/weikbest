package com.weikbest.pro.saas.sys.common.general;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.FilesUtils;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
@Api(tags = {"Manage——文件上传接口"})
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private ThirdConfigService thirdConfigService;

    @UseToken
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

    @UseToken
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


    @UseToken
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


    @UseToken
    @ApiOperation(value = "将商品主图图片上传到指定路径后返回uploadUrl,支持两级路径")
    @PostMapping("/uploadMainImg/{uploadDir}/{subDir}")
    public DataResp<String> uploadMainImg(
            @ApiParam(name = "uploadDir", value = "上传文件夹名称", required = true)
            @PathVariable("uploadDir") String uploadDir,
            @ApiParam(name = "subDir", value = "上传子文件夹名称", required = true)
            @PathVariable("subDir") String subDir,
            @ApiParam(name = "img", value = "文件", required = true)
            @RequestParam("img") MultipartFile imgFile) {

        if (imgFile == null || imgFile.isEmpty()) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL);
        }
        //大小限制2M内
        boolean checked = FilesUtils.checkFileSize(imgFile.getSize(), 2, "M");
        if (!checked) {
            throw new WeikbestException("文件过大，请上传小于2M的文件！");
        }
        StringBuilder builder = new StringBuilder();
        try {
            //获取图片buffer流
            BufferedImage bufferedImage = ImageIO.read(imgFile.getInputStream());
            if (bufferedImage!=null) {
                //长
                int height = bufferedImage.getHeight();
                //宽
                int width = bufferedImage.getWidth();

//                 if (height>1060 || width>800) {
//                     throw new WeikbestException("图片尺寸过大，请上传长小于1050px或宽小于800px的图片！");
//                }
                //拼接参数
                builder.append("?").append("height=").append(height).append("&").append("width=").append(width);
            }
        }catch (Exception e){
            throw new WeikbestException("读取文件流失败！" +e.getMessage());
        }
        String uploadUrl = thirdConfigService.aliyunOssService().uploadFileAvatar(imgFile, StrUtil.join("/", uploadDir, subDir));
        builder.insert(0,uploadUrl);
        return DataResp.ok(builder.toString());
    }

    @UseToken
    @ApiOperation(value = "将图片上传到指定路径后返回uploadUrl,支持两级路径")
    @PostMapping("/uploadImg1/{uploadDir}/{subDir}")
    public DataResp<String> uploadImg1(
            @ApiParam(name = "uploadDir", value = "上传文件夹名称", required = true)
            @PathVariable("uploadDir") String uploadDir,
            @ApiParam(name = "subDir", value = "上传子文件夹名称", required = true)
            @PathVariable("subDir") String subDir,
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile imgFile) {

        if (imgFile == null || imgFile.isEmpty()) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL);
        }

        String uploadUrl = thirdConfigService.aliyunOssService().uploadFileAvatar(imgFile, StrUtil.join("/", uploadDir, subDir));
        return DataResp.ok(uploadUrl);
    }

    @UseToken
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


    @UseToken
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
