package com.weikbest.pro.saas.sys.common.general;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisService;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.common.util.SecurestUtil;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.common.util.token.TokenUtil;
import com.weikbest.pro.saas.common.util.web.IpUtil;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.constant.SmsTemplateConstant;
import com.weikbest.pro.saas.sys.common.delaytaskprocess.LogoutDelayTaskProcess;
import com.weikbest.pro.saas.sys.common.module.dto.LoginDTO;
import com.weikbest.pro.saas.sys.param.service.SmsTemplateService;
import com.weikbest.pro.saas.sys.system.entity.UserLogin;
import com.weikbest.pro.saas.sys.system.module.dto.UserRegisterDTO;
import com.weikbest.pro.saas.sys.system.service.UserLoginService;
import com.weikbest.pro.saas.sys.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2020/6/21
 * @project saas
 * @jdk 1.8
 */
@Slf4j
@Api(tags = {"Sys::系统后台登录接口"})
@RestController
//@CrossOrigin
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private RedisContext redisContext;

    @Resource
    private RedisService redisService;

    @Resource
    private SmsTemplateService smsTemplateService;

    @Resource
    private LogoutDelayTaskProcess logoutDelayTaskProcess;

    @PassToken
    @SaveLog("生成验证码")
    @ApiOperation(value = "生成验证码")
    @GetMapping("/verify")
    public DataResp<String> verify(HttpServletRequest request) {

        // 生成验证码对应的key
        String verifyKey = SecureUtil.md5(IpUtil.getIpAddress(request));
        // 生成4位随机值验证码，每次刷新
        String redisVerify = redisService.generalNumberVerify(RedisKey.generalKey(RedisKey.MANAGE_VERIFY_EXPIRE_KEY, verifyKey), true);
        return DataResp.ok(redisVerify);
    }


    @PassToken
    @SaveLog("发送验证码")
    @ApiOperation(value = "发送验证码")
    @GetMapping("/verifyPhone")
    public DataResp<String> verify(HttpServletRequest request,
                                   @ApiParam(name = "phone", value = "手机号", required = true)
                                   @RequestParam String phone) {

        // 手机号验证
        if (!ReUtil.isMatch(Validator.MOBILE, phone)) {
            throw new WeikbestException(ResultConstant.PHONE_CHECK_FAIL);
        }
        // 生成验证码对应的key
        String verifyKey = SecureUtil.md5(IpUtil.getIpAddress(request) + phone);
        // 生成6位随机值验证码，每次刷新
        String redisVerify = redisService.generalNumberVerify(RedisKey.generalKey(RedisKey.MANAGE_VERIFY_EXPIRE_KEY, verifyKey), 6, true);

        // 发送验证码给用户
        smsTemplateService.sendOneSmsAndSaveRecord(SmsTemplateConstant.VERIFYPHONE, phone, redisVerify);
        return DataResp.ok(redisVerify);
    }


    @PassToken
    @SaveOrUpdateLog("用户登录")
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public DataResp<Map<String, Object>> login(HttpServletRequest request,
                                               @ApiParam(name = "loginDTO", value = "登录数据信息", required = true)
                                               @Valid LoginDTO loginDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        String loginIp = IpUtil.getIpAddress(request);

        if (StrUtil.isBlank(loginDTO.getPassword())) {
            // 通过手机号+手机验证码登录
            return loginByPhone(loginDTO, loginIp);
        } else {
            // 通过用户名+ 密码 + 验证码登录
            return loginByLoginNameAndPassword(loginDTO, loginIp);
        }
    }

    private DataResp<Map<String, Object>> loginByPhone(LoginDTO loginDTO, String loginIp) {
        // 判断验证码是否失效
        String verifyKey = SecureUtil.md5(loginIp + loginDTO.getLoginName());
        // 通过手机号+手机验证码登录
        redisService.validateVerify(verifyKey, loginDTO.getVerify());

        // 根据手机号和登录类型去系统用户登录表中查找记录
        UserLogin userLogin = userLoginService.getOne(new QueryWrapper<UserLogin>()
                .eq(UserLogin.LOGIN_NAME, loginDTO.getLoginName())
                .eq(UserLogin.LOGIN_TYPE, DictConstant.LoginType.phone.getCode()));
        if (ObjectUtil.isEmpty(userLogin)) {
            throw new WeikbestException("该用户不存在，请核实您的信息！");
        }

        return loginUser(userLogin, loginDTO.getRelateType(), loginIp, loginDTO.getLoginName());
    }

    private DataResp<Map<String, Object>> loginByLoginNameAndPassword(LoginDTO loginDTO, String loginIp) {
        // 判断验证码是否失效
//        String verifyKey = SecureUtil.md5(loginIp);
//        redisService.validateVerify(verifyKey, loginDTO.getVerify());

        // 根据用户名和密码去系统用户登录表中查找记录
        String securestPassword = SecurestUtil.securestPassword(loginDTO.getPassword());
        UserLogin userLogin = userLoginService.getOne(new QueryWrapper<UserLogin>()
                .eq(UserLogin.LOGIN_NAME, loginDTO.getLoginName())
                .eq(UserLogin.PASSWORD, securestPassword));
        if (ObjectUtil.isEmpty(userLogin)) {
            throw new WeikbestException("用户名密码错误或该用户不存在，请核实您的信息！");
        }

        // 用户登录
        return loginUser(userLogin, loginDTO.getRelateType(), loginIp, loginDTO.getLoginName());
    }

    private DataResp<Map<String, Object>> loginUser(UserLogin userLogin, String relateType, String loginIp, String loginName) {
        // 用户登录
        TokenUser tokenUser = userLoginService.loginUser(userLogin, relateType, loginIp, loginName);
        // 根据用户信息生成Token
        String token = TokenUtil.generalToken(tokenUser);
        // 保存token到延时队列
        logoutDelayTaskProcess.putTask(tokenUser);
        // 保存token到redis
        String redisUserTokenKey = TokenUtil.redisUserTokenKey(tokenUser);
        redisContext.set(redisUserTokenKey, token, TokenUtil.EXPIRE_SECOND);

        return DataResp.BuilderMap.create().ok()
                .putHead(TokenUtil.TOKEN_NAME, token)
                .put("userName", tokenUser.getUserName())
                .put("loginName", tokenUser.getLoginNameOrPhone())
                .put("firstLogin", StrUtil.isBlank(userLogin.getPassword()))
                .put("userId", String.valueOf(tokenUser.getUserId()))
                .build();
    }

    private void validateVerify(String verifyKey, String verify) {
        String redisVerify = (String) redisContext.get(RedisKey.generalKey(RedisKey.MANAGE_VERIFY_EXPIRE_KEY, verifyKey));
        if (StrUtil.isBlank(redisVerify)) {
            throw new WeikbestException(ResultConstant.VERIFY_TIMEOUT_FAIL);
        }
        // 判断验证码是否正确
        if (!StrUtil.equalsAnyIgnoreCase(redisVerify, verify)) {
            throw new WeikbestException(ResultConstant.VERIFY_CHECK_FAIL);
        }
    }

    @PassToken
    @SaveOrUpdateLog("平台用户注册")
    @ApiOperation(value = "平台用户注册")
    @PostMapping("/regist")
    public DataResp<Object> regist(HttpServletRequest request,
                                   @ApiParam(name = "verify", value = "验证码", required = true)
                                   @RequestParam String verify,
                                   @ApiParam(name = "userRegisterDTO", value = "用户注册信息", required = true)
                                   @Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        // 判断验证码是否失效
        String verifyKey = SecureUtil.md5(IpUtil.getIpAddress(request) + userRegisterDTO.getPhone());
        validateVerify(verifyKey, verify);

        // 用户注册
        userService.registUser(userRegisterDTO, DictConstant.UserRelateType.sys.getCode());
        // 删除验证码
        redisContext.del(RedisKey.generalKey(RedisKey.MANAGE_VERIFY_EXPIRE_KEY, verifyKey));
        return DataResp.ok();
    }


    @PassToken
    @SaveOrUpdateLog("无登录状态下修改用户密码")
    @ApiOperation(value = "无登录状态下修改用户密码")
    @PostMapping("/repass")
    public DataResp<Object> repass(HttpServletRequest request,
                                   @ApiParam(name = "phone", value = "登录手机号", required = true)
                                   @RequestParam String phone,
                                   @ApiParam(name = "password", value = "密码", required = true)
                                   @RequestParam String password,
                                   @ApiParam(name = "verify", value = "验证码", required = true)
                                   @RequestParam String verify) {
        // 手机号验证
        if (!ReUtil.isMatch(Validator.MOBILE, phone)) {
            throw new WeikbestException(ResultConstant.PHONE_CHECK_FAIL);
        }

        // 判断验证码是否失效
        String verifyKey = SecureUtil.md5(IpUtil.getIpAddress(request) + phone);
        validateVerify(verifyKey, verify);

        // 根据手机号修改密码
        userLoginService.repass(phone, password);

        // 删除验证码
        redisContext.del(RedisKey.generalKey(RedisKey.MANAGE_VERIFY_EXPIRE_KEY, verifyKey));

        return DataResp.ok();
    }


    @UseToken
    @SaveOrUpdateLog("用户登出")
    @ApiOperation(value = "用户登出")
    @PostMapping("/logout")
    public DataResp<Object> logout(HttpServletRequest request) {
        // 登出
        userLoginService.logout();
        return DataResp.ok();
    }
}
