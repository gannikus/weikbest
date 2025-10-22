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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 * <p>
 * 阿里云短信服务类，通过读取application.properties中的配置生成
 */
@Slf4j
@Service
public class AliyunSendSmsServiceSimple implements AliyunSendSmsService {

    @Autowired
    private AliyunSmsConfig aliyunSmsConfig;

    protected AliyunSendSmsServiceSimple() {
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
