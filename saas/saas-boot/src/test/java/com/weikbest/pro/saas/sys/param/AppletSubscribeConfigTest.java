package com.weikbest.pro.saas.sys.param;

import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.sys.param.service.AppletSubscribeConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class AppletSubscribeConfigTest {

    @Resource
    private AppletSubscribeConfigService appletSubscribeConfigService;

    @Test
    public void testSendOneSubscribeAndSaveRecord() {
        appletSubscribeConfigService.sendOneSubscribeAndSaveRecord("wx7b8f18a7b5a3f6c8", "", "oqT1g5JM4IBmk7yxEI-lGTnhs4Zc", "20221207000413413085", "新款男装T恤", "10", "200元", "2019/11/11", "请在13:57之前完成支付");
    }
}
