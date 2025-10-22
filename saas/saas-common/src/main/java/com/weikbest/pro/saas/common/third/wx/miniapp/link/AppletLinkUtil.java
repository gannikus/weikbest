package com.weikbest.pro.saas.common.third.wx.miniapp.link;

import cn.hutool.core.util.StrUtil;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/22
 * <p>
 * 小程序链接跳转
 */
public class AppletLinkUtil {

    /**
     * 小程序链接前缀
     */
    public static final String LINK_PREFIX = "weixin://dl/business/?t=";

    /**
     * 获取小程序链接码: weixin://dl/business/?t=XaZrVA3Gv4i
     *
     * @param generate wxMaSchemeService.generate()
     * @return 链接码 XaZrVA3Gv4i
     */
    public static String getCode(String generate) {
        return StrUtil.split(generate, "?t=")[1];
    }

    /**
     * 获取小程序链接
     *
     * @param code
     * @return
     */
    public static String getAppletLink(String code) {
        return LINK_PREFIX + code;
    }

}
