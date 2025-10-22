package com.weikbest.pro.saas.sys.param;

import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.sys.param.service.SmsTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class SmsTemplateTest {

    @Resource
    private SmsTemplateService smsTemplateService;

    @Test
    public void testSendOneSmsAndSaveRecord() {
        smsTemplateService.sendOneSmsAndSaveRecord("/waitPayTimeout", "15700802337", "XaZrVA3Gv4i");
    }


    @Test
    public void testSendOneSmsJumpAppletAndSaveRecord() {
        smsTemplateService.sendOneSmsJumpAppletAndSaveRecord("wx7b8f18a7b5a3f6c8", "/waitPayTimeout", "15700802337", "20221023083221561097");
    }
}
