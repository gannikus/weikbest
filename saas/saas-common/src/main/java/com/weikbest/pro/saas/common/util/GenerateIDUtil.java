package com.weikbest.pro.saas.common.util;

import cn.hutool.core.util.IdUtil;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/3
 * <p>
 * ID生成器
 */
public class GenerateIDUtil {

    /**
     * 获取雪花算法ID
     *
     * @return 雪花算法ID
     */
    public static Long nextId() {
        return IdUtil.getSnowflake(1, 1).nextId();
    }

    /**
     * 获取临时路径
     *
     * @return
     */
    public static String tempPath() {
        return "/tempPath/" + nextId();
    }
}
