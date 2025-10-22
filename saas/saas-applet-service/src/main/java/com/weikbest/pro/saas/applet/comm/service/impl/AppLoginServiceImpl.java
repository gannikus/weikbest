package com.weikbest.pro.saas.applet.comm.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.applet.comm.module.dto.AppLoginDTO;
import com.weikbest.pro.saas.applet.comm.module.mapstruct.AppLoginMapStruct;
import com.weikbest.pro.saas.applet.comm.service.AppLoginService;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import com.weikbest.pro.saas.merchat.cust.util.CustomerUtil;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import com.weikbest.pro.saas.sys.system.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/16
 */
@Slf4j
@Service
public class AppLoginServiceImpl implements AppLoginService {

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private CustomerService customerService;

    @Resource
    private UserLoginService userLoginService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TokenUser loginUser(String appId, String code, String custThirdType, String loginIp) {

        WxMaService wxMaService = thirdConfigService.wxMaService(appId);

        try {
            WxMaJscode2SessionResult wxMaJscode2SessionResult = wxMaService.jsCode2SessionInfo(code);
            String openid = wxMaJscode2SessionResult.getOpenid();
            String unionid = wxMaJscode2SessionResult.getUnionid();
            AppLoginDTO appLoginDTO = new AppLoginDTO().setOpenid(openid).setUnionid(unionid).setAppId(appId);

            return loginUser(appLoginDTO, custThirdType, loginIp);
        } catch (Exception e) {
            log.error(String.format("用户登录小程序失败！%s", e.getMessage()), e);
            throw new WeikbestException(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TokenUser loginUser(AppLoginDTO appLoginDTO, String custThirdType, String loginIp) {

        // 1. 根据openid+类型检查用户是否存在
        Customer customer = customerService.getCustomerByOpenidAndCustThirdType(appLoginDTO.getOpenid(), custThirdType);
        TokenUser tokenUser;
        if (ObjectUtil.isNull(customer)) {
            // 不存在，新增用户
            customer = AppLoginMapStruct.INSTANCE.converToCustomerEntity(appLoginDTO);

            // 设置平台用户关联
            customer.setUserUnique(StrUtil.isBlank(customer.getPhone()) ? CustomerUtil.defaultUserUnique(customer) : customer.getPhone());
            tokenUser = customerService.registerCustomer(customer, custThirdType, loginIp);
        } else {
            customer = AppLoginMapStruct.INSTANCE.converToCustomerEntity(appLoginDTO, customer);
            tokenUser = customerService.loginCustomerAndUpdate(customer, loginIp);
        }


        return tokenUser;
    }
}
