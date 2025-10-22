package com.weikbest.pro.saas.common.third.aliyun.sms.template;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunOneSmsParam;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsConfig;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.SmsConstant;
import org.springframework.stereotype.Component;

/**
 * @author wisdomelon
 * @date 2019/9/4 0004
 * @project saas
 * @jdk 1.8
 * 发送多条短信模板
 */
@Component
public class SendBatchSmsTemplate extends SendSmsTemplate {

    private SendBatchSmsTemplate() {
    }

    public static SendBatchSmsTemplate getInstance() {
        return SendBatchSmsTemplate.SendBatchSmsTemplateInstance.INSTANCE;
    }

    /**
     * 发送多条短信
     */
    @Override
    public String sendSms(AliyunSmsConfig aliyunSmsConfig, AliyunOneSmsParam aliyunOneSmsParam) throws ClientException {
        IAcsClient client = super.getDefaultAcsClient(aliyunSmsConfig);

        CommonRequest request = super.getCommonRequest(SmsConstant.ACTION_BATCH);
        request.putQueryParameter(SmsConstant.SIGN_NAME, aliyunOneSmsParam.getSignName());
        request.putQueryParameter(SmsConstant.PHONE_NUMBERS, aliyunOneSmsParam.getPhoneNumbers());
        request.putQueryParameter(SmsConstant.TEMPLATE_CODE, aliyunOneSmsParam.getTemplateCode());
        request.putQueryParameter(SmsConstant.TEMPLATE_PARAM, aliyunOneSmsParam.getTemplateParam());

        return send(client, request);

    }

    private static class SendBatchSmsTemplateInstance {
        private static SendBatchSmsTemplate INSTANCE = new SendBatchSmsTemplate();
    }
}
