package com.weikbest.pro.saas.sys.common.service;

import cn.hutool.core.util.ObjectUtil;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.common.util.token.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/25
 * <p>
 * 获取当前登录用户服务
 */
@Slf4j
@Component
public class CurrentUserService {

    /**
     * 从请求头中拿到当前登录的用户信息
     *
     * @return
     */
    public TokenUser getTokenUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (!ObjectUtils.isEmpty(servletRequestAttributes)) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return getTokenUser(request);
        }
        log.info("servletRequestAttributes is null ,return defaultTokenUser");
        return TokenUser.defaultTokenUser();
    }

    public TokenUser getTokenUser(HttpServletRequest request) {
        TokenUser tokenUser = TokenUtil.getTokenUserByJwtToken(request);
        if (ObjectUtil.isNotNull(tokenUser)) {
            return tokenUser;
        }
        log.info("request tokenUser is null ,return defaultTokenUser");
        return TokenUser.defaultTokenUser();
    }

    /**
     * 从请求头中拿到当前登录的用户信息
     *
     * @return
     */
    public TokenUser getAppTokenUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (!ObjectUtils.isEmpty(servletRequestAttributes)) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return getAppTokenUser(request);
        }
        log.info("servletRequestAttributes is null ,return defaultTokenUser");
        return TokenUser.defaultTokenUser();
    }

    public TokenUser getAppTokenUser(HttpServletRequest request) {
        TokenUser tokenUser = TokenUtil.getAppTokenUserByJwtToken(request);
        if (ObjectUtil.isNotNull(tokenUser)) {
            return tokenUser;
        }
        log.info("app request tokenUser is null ,return defaultTokenUser");
        return TokenUser.defaultTokenUser();
    }

}
