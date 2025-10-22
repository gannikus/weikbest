package com.weikbest.pro.saas.common.third.aliyun.sms.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliyunOneSmsParam {

    /**
     * 系统规定参数
     */
    private String action;
    /**
     * 接收短信的手机号码 支持对多个手机号码发送短信，手机号码之间以英文逗号（,）分隔
     */
    private String phoneNumbers;
    /**
     * 短信签名名称
     */
    private String signName;
    /**
     * 短信模板ID
     */
    private String templateCode;
    /**
     * 短信模板变量对应的实际值，JSON格式
     */
    private String templateParam;
    /**
     * 上行短信扩展码
     */
    private String smsUpExtendCode;
    /**
     * 外部流水扩展字段
     */
    private String outId;
}
