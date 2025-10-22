package com.weikbest.pro.saas.common.third.aliyun.sms;


import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsResponse;
import com.weikbest.pro.saas.common.third.aliyun.sms.function.AliyunSmsParamFunc;
import com.weikbest.pro.saas.common.third.aliyun.sms.template.SendSmsTemplate;

/**
 * @author wisdomelon
 * @date 2019/9/4 0004
 * @project saas
 * @jdk 1.8
 * 阿里云发送短信接口
 */
public interface AliyunSendSmsService {

    /**
     * 发送短信
     *
     * @param template
     * @param aliyunSmsParamFunc
     * @return
     * @throws WeikbestException
     */
    AliyunSmsResponse doSendSms(SendSmsTemplate template, AliyunSmsParamFunc aliyunSmsParamFunc) throws WeikbestException;

    /**
     * 发送单条短信
     *
     * @param aliyunSmsParamFunc
     * @return
     * @throws WeikbestException
     */
    AliyunSmsResponse doSendOneSms(AliyunSmsParamFunc aliyunSmsParamFunc) throws WeikbestException;

    /**
     * 批量发送短信
     *
     * @param aliyunSmsParamFunc
     * @return
     * @throws WeikbestException
     */
    AliyunSmsResponse doSendBathSms(AliyunSmsParamFunc aliyunSmsParamFunc) throws WeikbestException;

    /**
     * 发送短信
     *
     * @param template
     * @param phone
     * @param templateCode
     * @param templateParam
     * @return
     * @throws WeikbestException
     */
    AliyunSmsResponse doSendSms(SendSmsTemplate template, String phone, String templateCode, String templateParam) throws WeikbestException;

    /**
     * 发送单条短信
     *
     * @param phone
     * @param templateCode
     * @param templateParam
     * @return
     * @throws WeikbestException
     */
    AliyunSmsResponse doSendOneSms(String phone, String templateCode, String templateParam) throws WeikbestException;

    /**
     * 批量发送短信
     *
     * @param phone
     * @param templateCode
     * @param templateParam
     * @return
     * @throws WeikbestException
     */
    AliyunSmsResponse doSendBathSms(String phone, String templateCode, String templateParam) throws WeikbestException;

}
