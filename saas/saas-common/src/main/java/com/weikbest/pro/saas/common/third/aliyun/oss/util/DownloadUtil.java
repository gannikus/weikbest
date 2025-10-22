package com.weikbest.pro.saas.common.third.aliyun.oss.util;

import com.aliyun.oss.model.OSSObject;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/26
 */
@Slf4j
public class DownloadUtil {

    /**
     * 下载文件
     *
     * @param response
     * @param ossObject
     * @param downloadFileName
     */
    public static void downloadToWeb(HttpServletResponse response, OSSObject ossObject, String downloadFileName) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            String fileName = URLEncoder.encode(downloadFileName, "utf-8");
            InputStream is = ossObject.getObjectContent();
            response.setContentType(ossObject.getObjectMetadata().getContentType());
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            response.setHeader("filename", fileName);

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new WeikbestException(ResultConstant.DOWNLOADFILE_FAIL);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }
}
