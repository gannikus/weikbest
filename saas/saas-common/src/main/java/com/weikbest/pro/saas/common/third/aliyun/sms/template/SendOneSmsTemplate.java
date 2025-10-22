package com.weikbest.pro.saas.common.third.aliyun.sms.template;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunOneSmsParam;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsConfig;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.SmsConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wisdomelon
 * @date 2019/9/4 0004
 * @project saas
 * @jdk 1.8
 * 发送单条短信模板
 */
@Slf4j
public class SendOneSmsTemplate extends SendSmsTemplate {

    private SendOneSmsTemplate() {
    }

    public static SendOneSmsTemplate getInstance() {
        return SendOneSmsTemplateInstance.INSTANCE;
    }

    /**
     * 发送单条短信
     */
    @Override
    public String sendSms(AliyunSmsConfig aliyunSmsConfig, AliyunOneSmsParam aliyunOneSmsParam) throws ClientException {
        IAcsClient client = super.getDefaultAcsClient(aliyunSmsConfig);

        CommonRequest request = super.getCommonRequest(SmsConstant.ACTION_ONE);
        request.putQueryParameter(SmsConstant.SIGN_NAME, aliyunOneSmsParam.getSignName());
        request.putQueryParameter(SmsConstant.PHONE_NUMBERS, aliyunOneSmsParam.getPhoneNumbers());
        request.putQueryParameter(SmsConstant.TEMPLATE_CODE, aliyunOneSmsParam.getTemplateCode());
        request.putQueryParameter(SmsConstant.TEMPLATE_PARAM, aliyunOneSmsParam.getTemplateParam());

        return send(client, request);
    }

    private static class SendOneSmsTemplateInstance {
        private static SendOneSmsTemplate INSTANCE = new SendOneSmsTemplate();
    }
}
