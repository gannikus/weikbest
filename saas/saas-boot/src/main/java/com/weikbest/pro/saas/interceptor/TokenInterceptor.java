package com.weikbest.pro.saas.interceptor;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.weikbest.pro.saas.common.annotation.token.*;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.third.wx.util.MD5Util;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.context.SpringContext;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.common.util.token.TokenUtil;
import com.weikbest.pro.saas.common.util.web.IpUtil;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author wisdomelon
 * @date 2019/6/12 0012
 * @project saas
 * @jdk 1.8
 * 用户令牌拦截器，验证token是否合法或存在
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 本地IP
     */
    private Set<String> localhostIpSet = Sets.newHashSet("0:0:0:0:0:0:0:1", "127.0.0.1");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        //检查是否有SwaggerToken注释，有则跳过认证
        if (method.isAnnotationPresent(SwaggerToken.class)) {
            log.info("@@@@@@@@@@ Swagger test class.method() is : [{}.{}()] @@@@@@@@@@", method.getDeclaringClass().getSimpleName(), method.getName());
            return true;
        }

        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // App用户Token认证
        if (method.isAnnotationPresent(AppToken.class)) {
            return appTokenInterceptor(request, method);
        }

        // 后台用户Token认证
        if (method.isAnnotationPresent(UseToken.class)) {
            return manageTokenInterceptor(request, method);
        }

        // API认证
        if (method.isAnnotationPresent(ApiToken.class)) {
            return ApiTokenInterceptor(request, method);
        }

        return true;
    }

    /**
     * 对外接口验证
     * @param request
     * @param method
     * @return
     */
    private boolean ApiTokenInterceptor(HttpServletRequest request, Method method) {
        ApiToken annotation = method.getAnnotation(ApiToken.class);
        if (annotation.required()){
            //依次检查各变量是否存在？
            String apiKey = request.getHeader("apiKey");
            if (StrUtil.isBlank(apiKey)) {
               throw new WeikbestException(ResultConstant.API_KEY_IS_NULL);
            }
            String timestampStr = request.getHeader("timestamp");
            if (StrUtil.isBlank(timestampStr)) {
                throw new WeikbestException(ResultConstant.TIMESTAMP_NOT_EXIST);
            }
            String sign = request.getHeader("sign");
            if (StrUtil.isBlank(sign)) {
                throw new WeikbestException(ResultConstant.SIGN_NOT_EXIST);
            }

            //得到正确的sign供检验用
            BusiUserService busiUserService = SpringContext.getInstance().getBean(BusiUserService.class);
            BusiUser byApiKey = busiUserService.findByApiKey(apiKey);
            if (ObjectUtil.isNull(byApiKey)) {
                throw new  WeikbestException(ResultConstant.API_KEY_NOT_EXIST);
            }
            String origin = apiKey + byApiKey.getApiSecret() + timestampStr;
            String signEcrypt = SecureUtil.md5(origin);
            long timestamp = 0;
            try {
                timestamp = Long.parseLong(timestampStr);
            } catch (Exception e) {
                throw new  WeikbestException(ResultConstant.TIMESTAMP_NOT_INVALID);
            }
            //前端的时间戳与服务器当前时间戳相差如果大于180，判定当前请求的timestamp无效
            long abs = Math.abs((timestamp - System.currentTimeMillis()) / 1000);
            if (abs > 180) {
               throw new  WeikbestException(ResultConstant.TIMESTAMP_NOT_INVALID);
            }

            //后端MD5签名校验与前端签名sign值比对
            if (!(sign.equalsIgnoreCase(signEcrypt))) {
                throw new WeikbestException(ResultConstant.SIGN_NOT_MATCH);
            }

        }
        return true;
    }

    /**
     * 后台Token认证
     *
     * @param request
     * @param method
     * @return
     */
    private boolean manageTokenInterceptor(HttpServletRequest request, Method method) {
        UseToken useToken = method.getAnnotation(UseToken.class);
        if (useToken.required()) {
            // 从 http 请求头中取出 access_token
            String token = TokenUtil.getToken(request);

            // 执行认证
            if (!TokenUtil.checkToken(token)) {
                throw new WeikbestException(ResultConstant.TOKEN_NOT_EXIST);
            }

            // 获取 token 中的 用户id
            Long id = TokenUtil.getMemberIdByJwtToken(token);
            if (ObjectUtil.isEmpty(id)) {
                throw new WeikbestException(ResultConstant.TOKEN_AUTH_FAIL);
            }

//            // 获取 token 中的 用户登录的IP 并判断
//            String ip = TokenUtil.getMemberIpByJwtToken(token);
//            if (ObjectUtil.isEmpty(ip)) {
//                throw new WeikbestException(ResultConstant.TOKEN_AUTH_FAIL);
//            }
//            String nowIp = IpUtil.getIpAddress(request);
//            if (!StrUtil.equalsAnyIgnoreCase(nowIp, ip) && !localhostIp(nowIp, ip)) {
//                String errorMsg = ResultConstant.OTHER_IP_LOGIN_FAIL.getMsg() + " nowIp: " + nowIp + ", tokenIp:" + ip;
//                throw new WeikbestException(ResultConstant.OTHER_IP_LOGIN_FAIL.getCode(), errorMsg);
//            }

            // 获取 token 中的 用户登录名称
            String loginName = TokenUtil.getMemberLoginNameOrPhoneByJwtToken(token);
            if (ObjectUtil.isEmpty(loginName)) {
                throw new WeikbestException(ResultConstant.TOKEN_AUTH_FAIL);
            }

            // 获取 token 中的 tokenUser
            TokenUser tokenUser = TokenUtil.getTokenUserByJwtToken(token);
            if (ObjectUtil.isNull(tokenUser)) {
                throw new WeikbestException(ResultConstant.TOKEN_AUTH_FAIL);
            }

            // 判断token是否存在缓存中
            String redisUserTokenKey = TokenUtil.redisUserTokenKey(tokenUser);
            String redisUserDataKey = TokenUtil.redisUserDataKey(tokenUser);

            RedisContext redisContext = SpringContext.getInstance().getBean(RedisContext.class);
            boolean tokenExistRedis = redisContext.hasKey(redisUserTokenKey);
            if (!tokenExistRedis) {
                throw new WeikbestException(ResultConstant.USER_NOT_EXIST);
            }

            boolean userExistRedis = redisContext.hasKey(redisUserDataKey);
            if (!userExistRedis) {
                throw new WeikbestException(ResultConstant.USER_NOT_EXIST);
            }

            // 重新设置失效时间,1天
            redisContext.set(redisUserTokenKey, token, TokenUtil.EXPIRE_SECOND);
//            LogoutDelayTaskProcess logoutDelayTaskProcess = SpringContext.getInstance().getBean(LogoutDelayTaskProcess.class);
//            logoutDelayTaskProcess.putTask(tokenUser);
            return true;
        }

        return true;
    }

    /**
     * 是否都是本地IP
     *
     * @param nowIp
     * @param ip
     * @return
     */
    private boolean localhostIp(String nowIp, String ip) {
        return localhostIpSet.contains(nowIp) && localhostIpSet.contains(ip);
    }

    /**
     * App Token认证
     *
     * @param request
     * @param method
     * @return
     */
    private boolean appTokenInterceptor(HttpServletRequest request, Method method) {
        AppToken appToken = method.getAnnotation(AppToken.class);
        if (appToken.required()) {
            // 从 http 请求头中取出 token
            String token = TokenUtil.getAppToken(request);

            // 执行认证
            if (!TokenUtil.checkToken(token)) {
                throw new WeikbestException(ResultConstant.TOKEN_NOT_EXIST);
            }

            // 获取 token 中的 用户id
            Long id = TokenUtil.getMemberIdByJwtToken(token);
            if (ObjectUtil.isEmpty(id)) {
                throw new WeikbestException(ResultConstant.TOKEN_AUTH_FAIL);
            }

            // 获取 token 中的 用户登录的IP 并判断
//            String ip = TokenUtil.getMemberIpByJwtToken(token);
//            if (ObjectUtil.isEmpty(ip)) {
//                throw new WeikbestException(ResultConstant.TOKEN_AUTH_FAIL);
//            }
//            String nowIp = IpUtil.getIpAddress(request);
//            if (!StrUtil.equalsAnyIgnoreCase(nowIp, ip) && !localhostIp(nowIp, ip)) {
//                String errorMsg = ResultConstant.OTHER_IP_LOGIN_FAIL.getMsg() + " nowIp: " + nowIp + ", tokenIp:" + ip;
//                throw new WeikbestException(ResultConstant.OTHER_IP_LOGIN_FAIL.getCode(), errorMsg);
//            }

            // 获取 token 中的 用户登录名称，小程序端可能没有用户名称
//            String loginName = TokenUtil.getMemberLoginNameOrPhoneByJwtToken(token);
//            if (ObjectUtil.isEmpty(loginName)) {
//                throw new WeikbestException(ResultConstant.TOKEN_AUTH_FAIL);
//            }

            // 获取 token 中的 tokenUser
            TokenUser tokenUser = TokenUtil.getTokenUserByJwtToken(token);
            if (ObjectUtil.isNull(tokenUser)) {
                throw new WeikbestException(ResultConstant.TOKEN_AUTH_FAIL);
            }

            // 判断token是否存在缓存中
            String redisUserTokenKey = TokenUtil.redisUserTokenKey(tokenUser);
            String redisUserDataKey = TokenUtil.redisUserDataKey(tokenUser);

            RedisContext redisContext = SpringContext.getInstance().getBean(RedisContext.class);
            boolean tokenExistRedis = redisContext.hasKey(redisUserTokenKey);
            if (!tokenExistRedis) {
                throw new WeikbestException(ResultConstant.USER_NOT_EXIST);
            }

            boolean userExistRedis = redisContext.hasKey(redisUserDataKey);
            if (!userExistRedis) {
                throw new WeikbestException(ResultConstant.USER_NOT_EXIST);
            }

            // 重新设置失效时间 永不过期
            // redisContext.set(RedisKey.generalKey(RedisKey.APP_USER_TOKEN_KEY, id, phone), token);

            return true;

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
