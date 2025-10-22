package com.weikbest.pro.saas.common.util.web;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.annotation.token.*;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.util.token.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 判断是否是手机请求
 *
 * @author admin
 */
@Slf4j
public class RequestUtil {

    /**
     * 手机设备
     */
    static String[] MobileDeviceArray = new String[]{"android", "iphone", "ios", "windows phone"};

    /**
     * 微信网站
     */
    static String WE_CHART = "micromessenger";

    /**
     * android : 所有android设备 mac os : iphone ipad windows
     * phone: Nokia等windows系统的手机
     * weixin浏览器
     *
     * @param request
     * @return
     */
    public static boolean isMobileDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        if (userAgent == null) {
            return false;
        }
        //微信客户端
        if (userAgent.contains(WE_CHART)) {
            return true;
        }
        userAgent = userAgent.toLowerCase();
        for (int i = 0; i < MobileDeviceArray.length; i++) {
            if (userAgent.indexOf(MobileDeviceArray[i]) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * android : 所有android设备 mac os : iphone ipad windows
     * phone:Nokia等windows系统的手机
     *
     * @param request 请求头
     * @return
     */
    public static String getDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");

        if (userAgent == null) {
            return "PC端";
        }

        userAgent = userAgent.toLowerCase();

        //微信客户端
        if (userAgent.contains(WE_CHART)) {
            return "微信端";
        }

        for (int i = 0; i < MobileDeviceArray.length; i++) {
            if (userAgent.indexOf(MobileDeviceArray[i]) > 0) {
                return "WAP端";
            }
        }


        return "PC端";
    }

    /**
     * 从 http 请求头中取出 traceid
     *
     * @param request
     * @return
     */
    public static String getTraceId(HttpServletRequest request) {
        // 从 http 请求头中取出 traceid
        String traceid = request.getHeader(WeikbestConstant.TRACEID);
        // 检查是否存在token
        if (StrUtil.isBlank(traceid)) {
            // 从 http 请求域中取出 traceid
            traceid = request.getParameter(WeikbestConstant.TRACEID);
            if (StrUtil.isBlank(traceid)) {
                // 从 http 请求参数域中取出 traceid
                traceid = (String) request.getAttribute(WeikbestConstant.TRACEID);
            }
        }
        return traceid;
    }


    /**
     * 从 http 请求头中取出 traceid
     *
     * @return
     */
    public static String getTraceId() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isEmpty(servletRequestAttributes)) {
            return WeikbestConstant.ZERO_STR;
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return getTraceId(request);
    }

    /**
     * 获取操作人ID
     *
     * @param method
     * @param request
     * @return
     */
    public static Long getUserId(Method method, HttpServletRequest request) {
        Long userId = WeikbestConstant.ZERO_LONG;
        if (method.isAnnotationPresent(UseToken.class)) {
            userId = TokenUtil.getMemberUserIdByJwtToken(request);
        } else if (method.isAnnotationPresent(AppToken.class)) {
            userId = TokenUtil.getMemberUserIdByJwtAppToken(request);
        } else if (method.isAnnotationPresent(PassToken.class) || method.isAnnotationPresent(SwaggerToken.class)) {
        }else if (method.isAnnotationPresent(ApiToken.class)) {
            userId = WeikbestConstant.ONE_LONG;
        }
        else {
            userId = getUserId(request);
        }
        return userId;
    }

    /**
     * 获取操作人ID
     *
     * @param request
     * @return
     */
    public static Long getUserId(HttpServletRequest request) {
        Long userId = WeikbestConstant.ZERO_LONG;
        try {
            String token = TokenUtil.getToken(request);
            if (StrUtil.isNotEmpty(token)) {
                return TokenUtil.getMemberUserIdByJwtToken(request);
            }
            String appToken = TokenUtil.getAppToken(request);
            if (StrUtil.isNotEmpty(appToken)) {
                userId = TokenUtil.getMemberUserIdByJwtAppToken(request);
            }
            return userId;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return WeikbestConstant.ZERO_LONG;
        }
    }

}

