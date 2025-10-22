package com.weikbest.pro.saas.common.util.token;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Token生成器
 *
 * @author wisdomelon
 * @date 2019/6/11 0011
 * @project saas
 * @jdk 1.8
 */
@Slf4j
public class TokenUtil {

    public static final long EXPIRE = 1000L * 60 * 60 * 24;

    public static final long EXPIRE_SECOND = 60L * 60 * 24;

    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    public static final String TOKEN_NAME = "Access_token";

    public static final String APP_TOKEN_NAME = "token";

    /**
     * 获取存放在redis中的key token数据
     *
     * @param tokenUser
     * @return
     */
    public static String redisUserTokenKey(TokenUser tokenUser) {
        return redisUserTokenKey(tokenUser.getId(), tokenUser.getLoginNameOrPhone(), tokenUser.getRelateType(), tokenUser.getUserLoginRecordId());
    }

    /**
     * 获取存放在redis中的key token数据
     *
     * @param relateId
     * @param loginName
     * @param relateType
     * @param userLoginRecordId
     * @return
     */
    public static String redisUserTokenKey(Long relateId, String loginName, String relateType, Long userLoginRecordId) {
        return RedisKey.generalKey(RedisKey.USER_TOKEN_KEY, relateId, loginName, relateType, userLoginRecordId);
    }

    /**
     * 获取存放在redis中的key hash数据
     *
     * @param tokenUser
     * @return
     */
    public static String redisUserDataKey(TokenUser tokenUser) {
        return redisUserDataKey(tokenUser.getId(), tokenUser.getLoginNameOrPhone(), tokenUser.getRelateType(), tokenUser.getUserLoginRecordId());
    }

    /**
     * 获取存放在redis中的key hash数据
     *
     * @param relateId
     * @param loginName
     * @param relateType
     * @param userLoginRecordId
     * @return
     */
    public static String redisUserDataKey(Long relateId, String loginName, String relateType, Long userLoginRecordId) {
        return RedisKey.generalKey(RedisKey.USER_DATA_HASH_KEY, relateId, loginName, relateType, userLoginRecordId);
    }

    /**
     * 生成一个token
     *
     * @param tokenUser
     * @return
     */
    public static String generalToken(TokenUser tokenUser) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("saas")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .claim("id", String.valueOf(tokenUser.getId()))
                .claim("userId", String.valueOf(tokenUser.getUserId()))
                .claim("userLoginId", String.valueOf(tokenUser.getUserLoginId()))
                .claim("userLoginRecordId", String.valueOf(tokenUser.getUserLoginRecordId()))
                .claim("relateType", String.valueOf(tokenUser.getRelateType()))
                .claim("loginName", tokenUser.getLoginNameOrPhone())
                .claim("loginIp", tokenUser.getLoginIp())
                .claim("tokenUser", JsonUtils.bean2Json(tokenUser));

        if (WeikbestObjectUtil.isNotEmpty(tokenUser.getExpiration())) {
            jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + tokenUser.getExpiration()));
        }
        if (StrUtil.isNotEmpty(tokenUser.getOpenId())) {
            jwtBuilder.claim("openid", tokenUser.getOpenId());
        }
        if (StrUtil.isNotEmpty(tokenUser.getUnionId())) {
            jwtBuilder.claim("unionid", tokenUser.getUnionId());
        }

        return jwtBuilder.compact();
    }

    /**
     * 生成一个token
     *
     * @param tokenUser
     * @return
     */
//    public static String generalAppToken(TokenUser tokenUser) {
//        JwtBuilder jwtBuilder = Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setHeaderParam("alg", "HS256")
//                .setSubject("saas")
//                .setIssuedAt(new Date())
//                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
//                .claim("id", String.valueOf(tokenUser.getId()))
//                .claim("userId", String.valueOf(tokenUser.getUserId()))
//                .claim("userLoginId", String.valueOf(tokenUser.getUserLoginId()))
//                .claim("relateType", String.valueOf(tokenUser.getRelateType()))
//                .claim("loginName", tokenUser.getLoginNameOrPhone())
//                .claim("loginIp", tokenUser.getLoginIp())
//                .claim("tokenUser", JsonUtils.bean2Json(tokenUser));
//
//        if (StrUtil.isNotEmpty(tokenUser.getOpenId())) {
//            jwtBuilder.claim("openid", tokenUser.getOpenId());
//        }
//        if (StrUtil.isNotEmpty(tokenUser.getUnionId())) {
//            jwtBuilder.claim("unionid", tokenUser.getUnionId());
//        }
//
//        return jwtBuilder.compact();
//    }

    /**
     * 判断token是否存在与有效
     *
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        String jwtToken = getToken(request);
        return checkToken(jwtToken);
    }

    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            log.error("jwts checkToken error! ", e);
            return false;
        }

        return true;
    }

    /**
     * 根据token获取id
     *
     * @param request
     * @return
     */
    public static TokenUser getTokenUserByJwtToken(HttpServletRequest request) {
        String jwtToken = getToken(request);
        return getTokenUserByJwtToken(jwtToken);
    }

    /**
     * 根据token获取id
     *
     * @param request
     * @return
     */
    public static TokenUser getAppTokenUserByJwtToken(HttpServletRequest request) {
        String jwtToken = getAppToken(request);
        return getTokenUserByJwtToken(jwtToken);
    }

    /**
     * 根据token获取id
     *
     * @param jwtToken
     * @return
     */
    public static TokenUser getTokenUserByJwtToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        if (ObjectUtil.isNotEmpty(claims.get("tokenUser"))) {
            return JsonUtils.json2Bean(String.valueOf(claims.get("tokenUser")), TokenUser.class);
        }
        return null;
    }

    /**
     * 根据token获取id
     *
     * @param request
     * @return
     */
    public static Long getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = getToken(request);
        return getMemberIdByJwtToken(jwtToken);
    }

    /**
     * 根据token获取id
     *
     * @param jwtToken
     * @return
     */
    public static Long getMemberIdByJwtToken(String jwtToken) {
        return getLongMemberByJwtToken(jwtToken, "id");
    }

    /**
     * 根据token获取id
     *
     * @param request
     * @return
     */
    public static Long getMemberUserIdByJwtToken(HttpServletRequest request) {
        String jwtToken = getToken(request);
        return getMemberUserIdByJwtToken(jwtToken);
    }

    /**
     * 根据token获取id
     *
     * @param request
     * @return
     */
    public static Long getMemberUserIdByJwtAppToken(HttpServletRequest request) {
        String jwtToken = getAppToken(request);
        return getMemberUserIdByJwtToken(jwtToken);
    }

    /**
     * 根据token获取userid
     *
     * @param jwtToken
     * @return
     */
    public static Long getMemberUserIdByJwtToken(String jwtToken) {
        return getLongMemberByJwtToken(jwtToken, "userId");
    }

    /**
     * 根据token获取openid
     *
     * @param request
     * @return
     */
    public static String getMemberOpenidByJwtToken(HttpServletRequest request) {
        String jwtToken = getToken(request);
        return getMemberOpenidByJwtToken(jwtToken);
    }

    /**
     * 根据token获取openid
     *
     * @param request
     * @return
     */
    public static String getMemberOpenidByJwtAppToken(HttpServletRequest request) {
        String jwtToken = getAppToken(request);
        return getMemberOpenidByJwtToken(jwtToken);
    }

    /**
     * 根据token获取openid
     *
     * @param jwtToken
     * @return
     */
    public static String getMemberOpenidByJwtToken(String jwtToken) {
        return getStringMemberByJwtToken(jwtToken, "openid");
    }

    /**
     * 根据token获取loginName
     *
     * @param request
     * @return
     */
    public static String getMemberLoginNameOrPhoneByJwtToken(HttpServletRequest request) {
        String jwtToken = getToken(request);
        return getMemberLoginNameOrPhoneByJwtToken(jwtToken);
    }

    /**
     * 根据token获取loginName
     *
     * @param request
     * @return
     */
    public static String getMemberLoginNameOrPhoneByJwtAppToken(HttpServletRequest request) {
        String jwtToken = getAppToken(request);
        return getMemberLoginNameOrPhoneByJwtToken(jwtToken);
    }

    /**
     * 根据token获取loginName
     *
     * @param jwtToken
     * @return
     */
    public static String getMemberLoginNameOrPhoneByJwtToken(String jwtToken) {
        return getStringMemberByJwtToken(jwtToken, "loginName");
    }

    /**
     * 根据token获取ip
     *
     * @param request
     * @return
     */
    public static String getMemberIpByJwtToken(HttpServletRequest request) {
        String jwtToken = getToken(request);
        return getMemberIpByJwtToken(jwtToken);
    }

    /**
     * 根据token获取ip
     *
     * @param jwtToken
     * @return
     */
    public static String getMemberIpByJwtToken(String jwtToken) {
        return getStringMemberByJwtToken(jwtToken, "loginIp");
    }


    public static Long getMemberUserLoginIdByJwtToken(HttpServletRequest request) {
        String jwtToken = getToken(request);
        return getMemberUserLoginIdByJwtToken(jwtToken);
    }

    private static Long getMemberUserLoginIdByJwtToken(String jwtToken) {
        return getLongMemberByJwtToken(jwtToken, "userLoginId");
    }


    private static String getStringMemberByJwtToken(String jwtToken, String name) {
        if (StringUtils.isEmpty(jwtToken)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        if (ObjectUtil.isNotEmpty(claims.get(name))) {
            return (String) claims.get(name);
        }
        return null;
    }


    private static Long getLongMemberByJwtToken(String jwtToken, String name) {
        if (StringUtils.isEmpty(jwtToken)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        if (ObjectUtil.isNotEmpty(claims.get(name))) {
            return Long.valueOf(String.valueOf(claims.get(name)));
        }
        return null;
    }


    public static String getToken(HttpServletRequest request) {
        // 从 http 请求头中取出 access_token
        String token = request.getHeader(TokenUtil.TOKEN_NAME);

        // 检查是否存在token
        if (StrUtil.isBlank(token)) {
            // 从 http 请求域中取出 access_token
            token = request.getParameter(TokenUtil.TOKEN_NAME);
            if (StrUtil.isBlank(token)) {
                log.warn(ResultConstant.TOKEN_NOT_EXIST_WARN.getMsg());
            }
        }
        return token;
    }

    public static String getAppToken(HttpServletRequest request) {
        // 从 http 请求头中取出 access_token
        String token = request.getHeader(TokenUtil.APP_TOKEN_NAME);

        // 检查是否存在token
        if (StrUtil.isBlank(token)) {
            // 从 http 请求域中取出 access_token
            token = request.getParameter(TokenUtil.APP_TOKEN_NAME);
            if (StrUtil.isBlank(token)) {
                log.warn(ResultConstant.TOKEN_NOT_EXIST_WARN.getMsg());
            }
        }
        return token;
    }


}
