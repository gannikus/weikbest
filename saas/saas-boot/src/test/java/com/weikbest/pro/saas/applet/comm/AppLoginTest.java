package com.weikbest.pro.saas.applet.comm;

import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.applet.comm.module.dto.AppLoginDTO;
import com.weikbest.pro.saas.applet.comm.service.AppLoginService;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class AppLoginTest {

    @Resource
    private AppLoginService appLoginService;

    @Test
    public void test() {
//        appLoginService.loginUser("wx7b8f18a7b5a3f6c8", "033v981w3z2exZ2EFg4w30qnbP0v981b", DictConstant.CustThirdType.weixin.getCode(), "127.0.0.1");
        String appId = "wx7b8f18a7b5a3f6c8";
        String openid = "oqT1g5JM4IBmk7yxEI-lGTnhs4Zc";
        String unionid = "oIQKEwja2tizY_AT1u6uKrMSjNZY";
        AppLoginDTO appLoginDTO = new AppLoginDTO().setOpenid(openid).setUnionid(unionid).setAppId(appId);
        TokenUser tokenUser = appLoginService.loginUser(appLoginDTO, DictConstant.CustThirdType.weixin.getCode(), "127.0.0.1");
        System.out.println(tokenUser);
    }
}
