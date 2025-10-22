package com.weikbest.pro.saas.common.third.aliyun.sms.template;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunOneSmsParam;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsConfig;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.SmsConstant;

/**
 * @author wisdomelon
 * @date 2019/9/4 0004
 * @project saas
 * @jdk 1.8
 */
public abstract class SendSmsTemplate {

    /***
     * 发短信
     * @param aliyunSmsConfig
     * @param aliyunOneSmsParam
     * @return
     * @throws ClientException
     */
    public abstract String sendSms(AliyunSmsConfig aliyunSmsConfig, AliyunOneSmsParam aliyunOneSmsParam) throws ClientException;

    /***
     * 调用阿里云接口发送短信
     * @param client
     * @param request
     * @return
     * @throws ClientException
     */
    String send(IAcsClient client, CommonRequest request) throws ClientException {
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (WeikbestException | ClientException e) {
            throw e;
        }
    }

    /**
     * 获取默认的 DefaultAcsClient
     *
     * @param aliyunSmsConfig
     * @return
     */
    protected DefaultAcsClient getDefaultAcsClient(AliyunSmsConfig aliyunSmsConfig) {
        DefaultProfile profile = getDefaultProfile(aliyunSmsConfig);
        return new DefaultAcsClient(profile);
    }

    /**
     * 获取默认的 DefaultProfile
     *
     * @param aliyunSmsConfig
     * @return
     */
    protected DefaultProfile getDefaultProfile(AliyunSmsConfig aliyunSmsConfig) {
        return DefaultProfile.getProfile(SmsConstant.REGION_ID,
                aliyunSmsConfig.getAccesskeyid(), aliyunSmsConfig.getAccesskeysecret());
    }

    /**
     * 获取默认的CommonRequest
     *
     * @param action
     * @return
     */
    protected CommonRequest getCommonRequest(String action) {
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(SmsConstant.DOMAIN);
        request.setVersion(SmsConstant.VERSION);
        request.setAction(action);

        request.putQueryParameter(SmsConstant.REGION_ID_NAME, SmsConstant.REGION_ID);
        return request;
    }
}
