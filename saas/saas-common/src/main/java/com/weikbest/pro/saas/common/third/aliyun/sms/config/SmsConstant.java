package com.weikbest.pro.saas.common.third.aliyun.sms.config;

import java.util.Arrays;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/9/4 0004
 * @project saas
 * @jdk 1.8
 * 短信常量类
 */
public class SmsConstant {

    /**
     * 短信返回状态
     */
    public final static String OK = "OK";

    /**
     * 阿里云短信服务KEY
     */
    public final static String ACCESS_KEY_ID = "accessKeyId";
    /**
     * 阿里云短信服务密钥
     */
    public final static String SECRET = "secret";

    /**
     * 阿里云短信签名名称-每个商家都有一个短信签名名称
     */
    public final static String SIGN_NAME = "SignName";
    /**
     * 接收短信的手机号码
     */
    public final static String PHONE_NUMBERS = "PhoneNumbers";
    /**
     * 短信模板code,需要在阿里云申请模板内容--每个商家都会有一个唯一的模板code
     */
    public final static String TEMPLATE_CODE = "TemplateCode";
    /**
     * 短信模板变量对应的实际值，JSON格式。
     */
    public final static String TEMPLATE_PARAM = "TemplateParam";


    /**
     * 固定值
     */
    public final static String REGION_ID = "cn-hangzhou";
    public final static String REGION_ID_NAME = "RegionId";
    public final static String DOMAIN = "dysmsapi.aliyuncs.com";
    public final static String VERSION = "2017-05-25";

    /**
     * 单条短信
     */
    public final static String ACTION_ONE = "SendSms";
    /**
     * 批量短信
     */
    public final static String ACTION_BATCH = "SendBatchSms";

    /**
     * 发短信所需参数
     */
    public final static List<String> paramList = Arrays.asList(new String[]{PHONE_NUMBERS, SIGN_NAME, TEMPLATE_CODE, TEMPLATE_PARAM});


    /**
     * 短信记录
     */
    public static final String SMS_TITLE = "smsTitle";
    public static final String SMS_TYPE = "smsType";
    public static final String SMS_CONTENT = "smsContent";
    public static final String ACCOUNT_ID = "accountId";
    public static final String ACCOUNT_NAME = "accountName";
    public static final String BUSINESS_ID = "businessId";

}
