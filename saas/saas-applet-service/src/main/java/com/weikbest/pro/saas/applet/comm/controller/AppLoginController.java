package com.weikbest.pro.saas.applet.comm.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.weikbest.pro.saas.applet.comm.service.AppLoginService;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.common.util.token.TokenUtil;
import com.weikbest.pro.saas.common.util.web.IpUtil;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * App用户 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2021/5/20 0002
 */
@Slf4j
@Api(tags = {"APP——登录接口"})
@RestController
public class AppLoginController {

    @Resource
    private AppLoginService appLoginService;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private RedisContext redisContext;

//    @PassToken
//    @ApiOperation(value = "获取小程序接口凭证(access_token的有效期目前为2个小时，需定时刷新)")
//    @GetMapping(value = "/weixin/miniProgram/getAccessToken")
//    public DataResp<String> getAccessToken(
//            @ApiParam(name = "appId", value = "小程序的appId", required = true)
//            @RequestParam String appId) {
//        WxMaService wxMaService = thirdConfigService.wxMaService(appId);
//
//        try {
//            String accessToken = wxMaService.getAccessToken();
//            return DataResp.ok(accessToken);
//        } catch (WxErrorException e) {
//            log.error(String.format("获取小程序接口凭证失败！%s", e.getMessage()), e);
//            throw new WeikbestException(e);
//        }
//    }

    @PassToken
    @QueryLog(value = "获取小程序当前用户的openid,unionid值，并登录")
    @ApiOperation(value = "获取小程序当前用户的openid,unionid值，并登录")
    @PostMapping(value = "/weixin/miniProgram/getJscode2session")
    public DataResp<Map<String, Object>> getJscode2session(HttpServletRequest request,
                                                           @ApiParam(name = "appId", value = "小程序的appId", required = true)
                                                           @RequestParam String appId,
                                                           @ApiParam(name = "code", value = "获取凭证凭证", required = true)
                                                           @RequestParam String code) {

        String loginIp = IpUtil.getIpAddress(request);
        // 用户登录
        TokenUser tokenUser = appLoginService.loginUser(appId, code, DictConstant.CustThirdType.weixin.getCode(), loginIp);
        // 根据用户信息生成Token
        String token = TokenUtil.generalToken(tokenUser);
        // 保存token到redis
        String redisUserTokenKey = TokenUtil.redisUserTokenKey(tokenUser);
        // 小程序token永不失效
        redisContext.set(redisUserTokenKey, token, TokenUtil.EXPIRE_SECOND);

        return DataResp.BuilderMap.create().ok()
                .putHead(TokenUtil.APP_TOKEN_NAME, token)
                .put("userName", tokenUser.getUserName())
                .put("loginName", tokenUser.getLoginNameOrPhone())
                .put("customerId", String.valueOf(tokenUser.getId()))
                .put("openid", tokenUser.getOpenId())
                .put("unionid", tokenUser.getUnionId())
                .build();

    }

    @PassToken
    @QueryLog(value = "获取小程序当前用户的手机号")
    @ApiOperation(value = "获取小程序当前用户的手机号")
    @GetMapping(value = "/weixin/miniProgram/getUserPhoneNumber")
    public DataResp<WxMaPhoneNumberInfo> getUserPhoneNumber(
            @ApiParam(name = "appId", value = "小程序的appId", required = true)
            @RequestParam String appId,
            @ApiParam(name = "code", value = "手机号获取凭证，有效期为5min", required = true)
            @RequestParam String code) {
        WxMaService wxMaService = thirdConfigService.wxMaService(appId);

        try {
            WxMaPhoneNumberInfo newPhoneNoInfo = wxMaService.getUserService().getNewPhoneNoInfo(code);
            return DataResp.ok(newPhoneNoInfo);
        } catch (WxErrorException e) {
            log.error(String.format("获取小程序当前用户的openid,unionid值失败！%s", e.getMessage()), e);
            throw new WeikbestException(e);
        }
    }


//    @PassToken
//    @SaveOrUpdateLog(value = "微信小程序登录")
//    @ApiOperation(value = "微信小程序登录")
//    @PostMapping(value = "/weixin/miniProgram/login")
//    public DataResp<Map<String, Object>> weixinLogin(HttpServletRequest request,
//                                                     @ApiParam(name = "appLoginDTO", value = "小程序登录实体", required = true)
//                                                     @Valid AppLoginDTO appLoginDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        String loginIp = IpUtil.getIpAddress(request);
//        // 用户登录
//        TokenUser tokenUser = appLoginService.loginUser(appLoginDTO, DictConstant.CustThirdType.weixin.getCode(), loginIp);
//        // 根据用户信息生成Token
//        String token = TokenUtil.generalToken(tokenUser);
//        // 保存token到redis
//        String redisUserTokenKey = TokenUtil.redisUserTokenKey(tokenUser);
//        redisContext.set(redisUserTokenKey, token, TokenUtil.EXPIRE_SECOND);
//
//        return DataResp.BuilderMap.create().ok()
//                .putHead(TokenUtil.APP_TOKEN_NAME, token)
//                .put("userName", tokenUser.getUserName())
//                .put("loginName", tokenUser.getLoginNameOrPhone())
//                .put("customerId", String.valueOf(tokenUser.getId()))
//                .build();
//    }
}
