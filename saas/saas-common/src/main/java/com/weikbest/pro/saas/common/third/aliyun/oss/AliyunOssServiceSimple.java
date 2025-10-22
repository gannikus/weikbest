package com.weikbest.pro.saas.common.third.aliyun.oss;

import com.aliyun.oss.model.OSSObject;
import com.weikbest.pro.saas.common.third.aliyun.oss.config.AliyunOssConfig;
import com.weikbest.pro.saas.common.third.aliyun.oss.util.AliyunOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author wisdomelon
 * @date 2020/4/5 0005
 * @project saas
 * @jdk 1.8
 */
@Slf4j
@Service
public class AliyunOssServiceSimple implements AliyunOssService {

    @Autowired
    private AliyunOssConfig aliyunOssConfig;

    /**
     * 文件上传
     *
     * @param file
     * @param uploadDir
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile file, String uploadDir) {
        return AliyunOssUtil.uploadFileAvatar(aliyunOssConfig, file, uploadDir);
    }

    /**
     * 文件上传
     *
     * @param is
     * @param uploadDir
     * @param originalFilename
     * @return
     */
    @Override
    public String uploadFileAvatar(InputStream is, String uploadDir, String originalFilename) {
        return AliyunOssUtil.uploadFileAvatar(aliyunOssConfig, is, uploadDir, originalFilename);
    }

    @Override
    public void downloadFileAvatarToWeb(String fileUrl, String downloadFileName, HttpServletResponse response) {
        AliyunOssUtil.downloadFileAvatar(aliyunOssConfig, fileUrl, downloadFileName, response);
    }

    @Override
    public void downloadFileAvatar(String fileUrl, Consumer<OSSObject> consumer) {
        AliyunOssUtil.downloadFileAvatar(aliyunOssConfig, fileUrl, consumer);
    }

    @Override
    public <R> R downloadFileAvatar(String fileUrl, Function<OSSObject, R> function) {
        return AliyunOssUtil.downloadFileAvatar(aliyunOssConfig, fileUrl, function);
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        return uploadFileAvatar(file, "");
    }

    /**
     * 文件删除
     *
     * @param fileUrl
     * @return
     */
    @Override
    public void removeFileAvatar(String fileUrl) {
        AliyunOssUtil.removeFileAvatar(aliyunOssConfig, fileUrl);
    }
}
