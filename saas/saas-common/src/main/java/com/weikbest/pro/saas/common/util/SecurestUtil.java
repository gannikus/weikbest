package com.weikbest.pro.saas.common.util;

import cn.hutool.crypto.SecureUtil;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/3
 * <p>
 * 密码加密
 */
public class SecurestUtil {

    /**
     * 安全密码
     *
     * @param password 明文密码
     * @return 安全密码
     */
    public static String securestPassword(String password) {
        return SecureUtil.sha1(SecureUtil.md5(password));
    }


}
