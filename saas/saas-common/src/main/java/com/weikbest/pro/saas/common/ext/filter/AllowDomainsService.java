package com.weikbest.pro.saas.common.ext.filter;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/20
 */
public interface AllowDomainsService {

    /**
     * 获取放开跨域访问的url
     *
     * @return
     */
    String accessControlAllowOriginURL();
}
