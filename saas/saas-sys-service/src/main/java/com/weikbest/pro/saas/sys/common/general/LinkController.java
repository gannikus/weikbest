package com.weikbest.pro.saas.sys.common.general;

import com.weikbest.pro.saas.common.third.wx.miniapp.link.AppletLinkUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/22
 */
@Slf4j
@Api(tags = {"Manage——链接跳转接口"})
@Controller
public class LinkController {

    /**
     * 短信跳转页
     */
    private static final String SMS_PAGE = "sms";

    @ApiOperation(value = "通过短信打开小程序效果实现", notes = "将URl参数值保存后跳转到 weixin://dl/business/?t={code} ，进行拼接后打开微信小程序(效果:https://saas.wkyxsc.com/platformDocking/sms/XaZrVA3Gv4i)")
    @GetMapping("/sms/{code}")
    public String index(
            @ApiParam(name = "code", required = true)
            @PathVariable String code) {

        return "redirect:" + AppletLinkUtil.getAppletLink(code);
    }
}
