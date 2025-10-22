package com.weikbest.pro.saas.common.third.aliyun.sms.util;

import com.aliyuncs.exceptions.ClientException;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunOneSmsParam;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsConfig;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsResponse;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.SmsConstant;
import com.weikbest.pro.saas.common.third.aliyun.sms.function.AliyunSmsParamFunc;
import com.weikbest.pro.saas.common.third.aliyun.sms.template.SendSmsTemplate;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
public class AliyunSendSmsUtil {

    /**
     * 发送短信
     *
     * @param aliyunSmsConfig
     * @param template
     * @param aliyunSmsParamFunc
     * @return
     * @throws WeikbestException
     */
    public static AliyunSmsResponse doSendSms(AliyunSmsConfig aliyunSmsConfig, SendSmsTemplate template, AliyunSmsParamFunc aliyunSmsParamFunc) throws WeikbestException {
        AliyunSmsResponse aliyunSmsResponse = null;
        AliyunOneSmsParam smsParam = new AliyunOneSmsParam();
        smsParam.setSignName(aliyunSmsConfig.getSignname());
        try {
            String sendResponse = template.sendSms(aliyunSmsConfig, aliyunSmsParamFunc.buildAliyunSmsParam(smsParam));
            aliyunSmsResponse = AliyunSmsResponse.explainResponse(sendResponse);

            if (!SmsConstant.OK.equals(aliyunSmsResponse.getStatus())) {
                throw new WeikbestException(ResultConstant.EXCEPTION_FAIL.getCode(), aliyunSmsResponse.getStatus());
            }
        } catch (ClientException e) {
            throw new WeikbestException(ResultConstant.EXCEPTION_FAIL.getCode(), e.getMessage(), e);
        }

        return aliyunSmsResponse;
    }

    /**
     * 发送短信
     *
     * @param aliyunSmsConfig
     * @param template
     * @param phone
     * @param templateCode
     * @param templateParam
     * @return
     * @throws WeikbestException
     */
    public static AliyunSmsResponse doSendSms(AliyunSmsConfig aliyunSmsConfig, SendSmsTemplate template, String phone, String templateCode, String templateParam) throws WeikbestException {
        AliyunOneSmsParam smsParam = new AliyunOneSmsParam();
        smsParam.setSignName(aliyunSmsConfig.getSignname());
        smsParam.setPhoneNumbers(phone);
        smsParam.setTemplateCode(templateCode);
        smsParam.setTemplateParam(templateParam);
        try {
            String sendResponse = template.sendSms(aliyunSmsConfig, smsParam);
            AliyunSmsResponse aliyunSmsResponse = AliyunSmsResponse.explainResponse(sendResponse);

            if (!SmsConstant.OK.equals(aliyunSmsResponse.getStatus())) {
                throw new WeikbestException(ResultConstant.EXCEPTION_FAIL.getCode(), "response status:" + aliyunSmsResponse.getStatus());
            }

            return aliyunSmsResponse;
        } catch (ClientException e) {
            throw new WeikbestException(ResultConstant.EXCEPTION_FAIL.getCode(), e.getMessage(), e);
        }
    }
}
