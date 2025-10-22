package com.weikbest.pro.saas.common.third.aliyun.oss;

import com.aliyun.oss.model.OSSObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
public interface AliyunOssService {

    /**
     * 文件上传
     *
     * @param file
     * @param uploadDir
     * @return
     */
    String uploadFileAvatar(MultipartFile file, String uploadDir);

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);

    /**
     * 文件上传
     *
     * @param file
     * @param uploadDir
     * @param originalFilename
     * @return
     */
    String uploadFileAvatar(InputStream file, String uploadDir, String originalFilename);


    /**
     * 文件下载
     *
     * @param fileUrl
     * @param downloadFileName
     * @param response
     */
    void downloadFileAvatarToWeb(String fileUrl, String downloadFileName, HttpServletResponse response);

    /**
     * 文件下载
     *
     * @param fileUrl
     * @param consumer
     */
    void downloadFileAvatar(String fileUrl, Consumer<OSSObject> consumer);

    /**
     * 文件下载
     *
     * @param fileUrl
     * @param function
     */
    <R> R downloadFileAvatar(String fileUrl, Function<OSSObject, R> function);

    /**
     * 文件删除
     *
     * @param fileUrl
     */
    void removeFileAvatar(String fileUrl);

}
