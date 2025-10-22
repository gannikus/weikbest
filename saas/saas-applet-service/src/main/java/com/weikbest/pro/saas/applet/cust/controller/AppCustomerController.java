package com.weikbest.pro.saas.applet.cust.controller;

import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.AppToken;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.common.util.token.TokenUtil;
import com.weikbest.pro.saas.common.util.web.IpUtil;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustomerDTO;
import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/16
 */
@Slf4j
@Api(tags = {"appcustomer::APP用户接口"})
@RestController
@RequestMapping("/appcustomer")
public class AppCustomerController {

    @Resource
    private CustomerService customerService;

    @Resource
    private RedisContext redisContext;

    @AppToken
    @UpdateLog(value = "更新微信小程序用户表数据")
    @ApiOperation(value = "更新微信小程序用户表数据")
    @PutMapping("/updateWeixin/{id}")
    public DataResp<Map<String, Object>> update(HttpServletRequest request,
                                                @ApiParam(name = "id", value = "ID", required = true)
                                                @PathVariable Long id,
                                                @ApiParam(name = "customerDTO", value = "更新数据信息", required = true)
                                                @Valid CustomerDTO customerDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);
        String loginIp = IpUtil.getIpAddress(request);
        // 更新用户信息后获取最新的token信息
        TokenUser tokenUser = customerService.updateById(id, customerDTO, loginIp);
        // 根据用户信息生成Token
        String token = TokenUtil.generalToken(tokenUser);
        // 保存token到redis
        String redisUserTokenKey = TokenUtil.redisUserTokenKey(tokenUser);
        // 小程序token永不失效
        redisContext.set(redisUserTokenKey, token,TokenUtil.EXPIRE_SECOND);
        // 查询客户信息
        Customer customer = customerService.findById(id);
        return DataResp.BuilderMap.create().ok()
                .putHead(TokenUtil.APP_TOKEN_NAME, token)
                .put("userName", customer.getName())
                .put("loginName", tokenUser.getLoginNameOrPhone())
                .put("customerId", String.valueOf(tokenUser.getId()))
                .put("customer", customer)
                .build();
    }


}
