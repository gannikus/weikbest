package com.weikbest.pro.saas.common.third.aliyun.sms;

import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsConfig;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsResponse;
import com.weikbest.pro.saas.common.third.aliyun.sms.function.AliyunSmsParamFunc;
import com.weikbest.pro.saas.common.third.aliyun.sms.template.SendBatchSmsTemplate;
import com.weikbest.pro.saas.common.third.aliyun.sms.template.SendOneSmsTemplate;
import com.weikbest.pro.saas.common.third.aliyun.sms.template.SendSmsTemplate;
import com.weikbest.pro.saas.common.third.aliyun.sms.util.AliyunSendSmsUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 手动创建第三方配置信息
 *
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
@Slf4j
public class AliyunSendSmsServiceImpl implements AliyunSendSmsService {

    private AliyunSmsConfig aliyunSmsConfig;

    protected AliyunSendSmsServiceImpl() {
    }

    /**
     * 构建AliyunSendSmsServiceSimple对象
     *
     * @param aliyunSmsConfig
     * @return
     */
    public static AliyunSendSmsServiceImpl build(AliyunSmsConfig aliyunSmsConfig) {
        AliyunSendSmsServiceImpl aliyunWuliuConfig = new AliyunSendSmsServiceImpl();
        aliyunWuliuConfig.aliyunSmsConfig = aliyunSmsConfig;
        return aliyunWuliuConfig;
    }

    /**
     * 构建AliyunSendSmsServiceSimple对象
     *
     * @param signname
     * @param accesskeyid
     * @param accesskeysecret
     * @return
     */
    public static AliyunSendSmsServiceImpl build(String signname, String accesskeyid, String accesskeysecret) {
        AliyunSendSmsServiceImpl aliyunSendSmsServiceImpl = new AliyunSendSmsServiceImpl();
        aliyunSendSmsServiceImpl.aliyunSmsConfig = new AliyunSmsConfig(signname, accesskeyid, accesskeysecret);
        return aliyunSendSmsServiceImpl;
    }

    /***
     * 发送短信
     * @param template
     * @param aliyunSmsParamFunc
     * @throws WeikbestException
     * @return
     */
    @Override
    public AliyunSmsResponse doSendSms(SendSmsTemplate template, AliyunSmsParamFunc aliyunSmsParamFunc) throws WeikbestException {
        return AliyunSendSmsUtil.doSendSms(aliyunSmsConfig, template, aliyunSmsParamFunc);
    }

    @Override
    public AliyunSmsResponse doSendOneSms(AliyunSmsParamFunc aliyunSmsParamFunc) throws WeikbestException {
        return doSendSms(SendOneSmsTemplate.getInstance(), aliyunSmsParamFunc);
    }

    @Override
    public AliyunSmsResponse doSendBathSms(AliyunSmsParamFunc aliyunSmsParamFunc) throws WeikbestException {
        return doSendSms(SendBatchSmsTemplate.getInstance(), aliyunSmsParamFunc);
    }

    @Override
    public AliyunSmsResponse doSendSms(SendSmsTemplate template, String phone, String templateCode, String templateParam) throws WeikbestException {
        return AliyunSendSmsUtil.doSendSms(aliyunSmsConfig, template, phone, templateCode, templateParam);
    }

    @Override
    public AliyunSmsResponse doSendOneSms(String phone, String templateCode, String templateParam) throws WeikbestException {
        return doSendSms(SendOneSmsTemplate.getInstance(), phone, templateCode, templateParam);
    }

    @Override
    public AliyunSmsResponse doSendBathSms(String phone, String templateCode, String templateParam) throws WeikbestException {
        return doSendSms(SendBatchSmsTemplate.getInstance(), phone, templateCode, templateParam);
    }
}
