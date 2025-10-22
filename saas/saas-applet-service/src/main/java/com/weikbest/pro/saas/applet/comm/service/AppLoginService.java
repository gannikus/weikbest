package com.weikbest.pro.saas.applet.comm.service;

import com.weikbest.pro.saas.applet.comm.module.dto.AppLoginDTO;
import com.weikbest.pro.saas.common.util.token.TokenUser;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/16
 * <p>
 * APP登录服务
 */
public interface AppLoginService {

    /**
     * 小程序用户登录
     *
     * @param appId
     * @param code
     * @param custThirdType
     * @param loginIp
     * @return
     */
    TokenUser loginUser(String appId, String code, String custThirdType, String loginIp);

    /**
     * 小程序用户登录
     *
     * @param appLoginDTO
     * @param custThirdType
     * @param loginIp
     * @return
     */
    TokenUser loginUser(AppLoginDTO appLoginDTO, String custThirdType, String loginIp);
}
