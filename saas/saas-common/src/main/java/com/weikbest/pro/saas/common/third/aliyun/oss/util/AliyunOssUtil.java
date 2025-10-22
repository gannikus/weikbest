package com.weikbest.pro.saas.common.third.aliyun.oss.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.func.Func;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.oss.config.AliyunOssConfig;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
@Slf4j
public class AliyunOssUtil {

    /**
     * 文件上传
     *
     * @param aliyunOssConfig
     * @param file
     * @param uploadDir
     * @return
     */
    public static String uploadFileAvatar(AliyunOssConfig aliyunOssConfig, MultipartFile file, String uploadDir) {
        //获取阿里云存储相关常量
        String endPoint = aliyunOssConfig.getEndpoint();
        String accessKeyId = aliyunOssConfig.getKeyid();
        String accessKeySecret = aliyunOssConfig.getKeysecret();
        String bucketName = aliyunOssConfig.getBucketname();

        String uploadUrl = null;

        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

            //获取上传文件流
            InputStream inputStream = file.getInputStream();

            //文件名
            String originalFilename = file.getOriginalFilename();
            String fileSuffix = StrUtil.subSuf(originalFilename, StrUtil.lastIndexOfIgnoreCase(originalFilename, "."));
            String fileName = StrUtil.sub(originalFilename, 0, StrUtil.lastIndexOfIgnoreCase(originalFilename, "."));
            String md5 = SecureUtil.md5(fileName);
            String uuid = IdUtil.fastSimpleUUID();

            //上传后的日期文件夹
            String datePath = DateUtil.date().toString("yyyy/MM/dd");
            String fileObject = new StringBuilder(datePath).append("/")
                    .append(uploadDir).append("/")
                    .append(uuid).append(md5).append(fileSuffix).toString();

            //文件上传至阿里云
            ossClient.putObject(bucketName, fileObject, inputStream);
            log.info("阿里云Oss文件上传，文件路径：{}", fileObject);

            // 关闭OSSClient。
            ossClient.shutdown();

            //获取url地址
            uploadUrl = getUrlPrefix(bucketName, endPoint) + fileObject;

        } catch (IOException e) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL, e);
        }

        return uploadUrl;
    }

    /**
     * 文件上传
     *
     * @param aliyunOssConfig
     * @param is
     * @param uploadDir
     * @param originalFilename
     * @return
     */
    public static String uploadFileAvatar(AliyunOssConfig aliyunOssConfig, InputStream is, String uploadDir, String originalFilename) {
        //获取阿里云存储相关常量
        String endPoint = aliyunOssConfig.getEndpoint();
        String accessKeyId = aliyunOssConfig.getKeyid();
        String accessKeySecret = aliyunOssConfig.getKeysecret();
        String bucketName = aliyunOssConfig.getBucketname();

        String uploadUrl = null;

        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        try {
            //文件名
            String fileSuffix = StrUtil.subSuf(originalFilename, StrUtil.lastIndexOfIgnoreCase(originalFilename, "."));
            String fileName = StrUtil.sub(originalFilename, 0, StrUtil.lastIndexOfIgnoreCase(originalFilename, "."));
            String md5 = SecureUtil.md5(fileName);
            String uuid = IdUtil.fastSimpleUUID();

            //上传后的日期文件夹
            String datePath = DateUtil.date().toString("yyyy/MM/dd");
            String fileObject = new StringBuilder(datePath).append("/")
                    .append(uploadDir).append("/")
                    .append(uuid).append(md5).append(fileSuffix).toString();

            //文件上传至阿里云
            ossClient.putObject(bucketName, fileObject, is);
            log.info("阿里云Oss文件上传，文件路径：{}", fileObject);

            //获取url地址
            uploadUrl = getUrlPrefix(bucketName, endPoint) + fileObject;

        } catch (Exception e) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL, e);
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }

        return uploadUrl;
    }

    /**
     * 文件上传
     *
     * @param aliyunOssConfig
     * @param file
     * @return
     */
    public static String uploadFileAvatar(AliyunOssConfig aliyunOssConfig, MultipartFile file) {
        return uploadFileAvatar(aliyunOssConfig, file, "");
    }

    /**
     * 文件下载
     *
     * @param aliyunOssConfig
     * @param fileUrl
     * @param downloadFileName
     * @param response
     */
    public static void downloadFileAvatar(AliyunOssConfig aliyunOssConfig, String fileUrl, String downloadFileName, HttpServletResponse response) {
        downloadFileAvatar(aliyunOssConfig, fileUrl, ossObject -> {
            DownloadUtil.downloadToWeb(response, ossObject, downloadFileName);
        });
    }

    /**
     * 文件下载
     *
     * @param aliyunOssConfig
     * @param fileUrl
     * @param consumer
     */
    public static void downloadFileAvatar(AliyunOssConfig aliyunOssConfig, String fileUrl, Consumer<OSSObject> consumer) {
        //获取阿里云存储相关常量
        String endPoint = aliyunOssConfig.getEndpoint();
        String accessKeyId = aliyunOssConfig.getKeyid();
        String accessKeySecret = aliyunOssConfig.getKeysecret();
        String bucketName = aliyunOssConfig.getBucketname();

        String fileObject = StrUtil.subSuf(fileUrl, getUrlPrefix(bucketName, endPoint).length());

        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        try {
            OSSObject clientObject = ossClient.getObject(bucketName, fileObject);
            consumer.accept(clientObject);
        } catch (Exception e) {
            throw new WeikbestException(ResultConstant.DOWNLOADFILE_FAIL, e);
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }

    }


    /**
     * 文件下载
     *
     * @param aliyunOssConfig
     * @param fileUrl
     * @param function
     */
    public static <R> R downloadFileAvatar(AliyunOssConfig aliyunOssConfig, String fileUrl, Function<OSSObject, R> function) {
        //获取阿里云存储相关常量
        String endPoint = aliyunOssConfig.getEndpoint();
        String accessKeyId = aliyunOssConfig.getKeyid();
        String accessKeySecret = aliyunOssConfig.getKeysecret();
        String bucketName = aliyunOssConfig.getBucketname();

        String fileObject = StrUtil.subSuf(fileUrl, getUrlPrefix(bucketName, endPoint).length());

        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        try {
            OSSObject clientObject = ossClient.getObject(bucketName, fileObject);
            return function.apply(clientObject);
        } catch (Exception e) {
            throw new WeikbestException(ResultConstant.DOWNLOADFILE_FAIL, e);
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }

    }

    /**
     * 文件删除
     *
     * @param aliyunOssConfig
     * @param fileUrl
     * @return
     */
    public static void removeFileAvatar(AliyunOssConfig aliyunOssConfig, String fileUrl) {
        //获取阿里云存储相关常量
        String endPoint = aliyunOssConfig.getEndpoint();
        String accessKeyId = aliyunOssConfig.getKeyid();
        String accessKeySecret = aliyunOssConfig.getKeysecret();
        String bucketName = aliyunOssConfig.getBucketname();

        String fileObject = StrUtil.subSuf(fileUrl, getUrlPrefix(bucketName, endPoint).length());

        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        try {
            // 删除文件。如需删除文件夹，请将第二个参数设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            log.info("阿里云Oss文件删除，文件名称：{}", fileObject);
            ossClient.deleteObject(bucketName, fileObject);

        } catch (Exception e) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL, e);
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }

    /**
     * 链接前缀
     *
     * @param bucketName
     * @param endPoint
     * @return
     */
    private static String getUrlPrefix(String bucketName, String endPoint) {
        return "http://" + bucketName + "." + endPoint + "/";
    }


}
