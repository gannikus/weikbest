package com.weikbest.pro.saas.sys.param.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 第三方平台配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_third_config")
@ApiModel(value = "ThirdConfig对象", description = "第三方平台配置表  ")
public class ThirdConfig implements Serializable {

    public static final String ID = "id";
    public static final String WX_PAY_APP_ID = "wx_pay_app_id";
    public static final String WX_PAY_SUB_APP_ID = "wx_pay_sub_app_id";
    public static final String WX_PAY_MCH_ID = "wx_pay_mch_id";
    public static final String WX_PAY_MCH_NAME = "wx_pay_mch_name";
    public static final String WX_PAY_MCH_KEY = "wx_pay_mch_key";
    public static final String WX_PAY_ENT_PAY_KEY = "wx_pay_ent_pay_key";
    public static final String WX_PAY_SUB_MCH_ID = "wx_pay_sub_mch_id";
    public static final String WX_PAY_NOTIFY_URL = "wx_pay_notify_url";
    public static final String WX_PAY_KEY_PATH = "wx_pay_key_path";
    public static final String WX_PAY_PRIVATE_KEY_PATH = "wx_pay_private_key_path";
    public static final String WX_PAY_PRIVATE_CERT_PATH = "wx_pay_private_cert_path";
    public static final String WX_PAY_API_V3_KEY = "wx_pay_api_v3_key";
    public static final String WX_PAY_API_CERT_SERIAL_NO = "wx_pay_api_cert_serial_no";
    public static final String WX_PAY_API_SERVICE_ID = "wx_pay_api_service_id";
    public static final String WX_PAY_PAY_SCORE_NOTIFY_URL = "wx_pay_pay_score_notify_url";
    public static final String WX_PAY_PAY_SCORE_PERMISSION_NOTIFY_URL = "wx_pay_pay_score_permission_notify_url";
    public static final String WX_MINIAPP_APP_ID = "wx_miniapp_app_id";
    public static final String WX_MINIAPP_APP_SECRET = "wx_miniapp_app_secret";
    public static final String WX_MINIAPP_TOKEN = "wx_miniapp_token";
    public static final String WX_MINIAPP_AES_KEY = "wx_miniapp_aes_key";
    public static final String WX_MINIAPP_ORIGINAL_ID = "wx_miniapp_original_id";
    public static final String WX_MINIAPP_MSG_DATA_FORMAT = "wx_miniapp_msg_data_format";
    public static final String WX_MINIAPP_COLUD_ENV = "wx_miniapp_colud_env";
    public static final String ALIYUN_WULIU_APPKEY = "aliyun_wuliu_appkey";
    public static final String ALIYUN_WULIU_APPCODE = "aliyun_wuliu_appcode";
    public static final String ALIYUN_WULIU_APPSECRET = "aliyun_wuliu_appsecret";
    public static final String ALIYUN_SMS_SIGNNAME = "aliyun_sms_signname";
    public static final String ALIYUN_SMS_ACCESSKEY_ID = "aliyun_sms_accesskey_id";
    public static final String ALIYUN_SMS_ACCESSKEY_SECRET = "aliyun_sms_accesskey_secret";
    public static final String ALIYUN_OSS_FILE_ENDPOINT = "aliyun_oss_file_endpoint";
    public static final String ALIYUN_OSS_FILE_KEYID = "aliyun_oss_file_keyid";
    public static final String ALIYUN_OSS_FILE_KEYSECRET = "aliyun_oss_file_keysecret";
    public static final String ALIYUN_OSS_FILE_BUCKETNAME = "aliyun_oss_file_bucketname";
    public static final String ALIYUN_OCR_APPSECRET = "aliyun_ocr_appsecret";
    public static final String ALIYUN_OCR_APPCODE = "aliyun_ocr_appcode";
    public static final String ALIPAY_APPID = "alipay_appid";
    public static final String ALIPAY_PID = "alipay_pid";
    public static final String ALIPAY_PRIVATE_KEY = "alipay_private_key";
    public static final String ALIPAY_PUBLIC_KEY = "alipay_public_key";
    public static final String ALIPAY_RETURN_URL = "alipay_return_url";
    public static final String ALIPAY_APP_CERT_PATH = "alipay_app_cert_path";
    public static final String ALIPAY_CERT_PATH = "alipay_cert_path";
    public static final String ALIPAY_ROOT_CERT_PATH = "alipay_root_cert_path";
    public static final String TOUTIAO_APPID = "toutiao_appid";
    public static final String TOUTIAO_APPSECRET = "toutiao_appsecret";
    public static final String TOUTIAO_SALT = "toutiao_salt";
    public static final String TOUTIAO_NOTIFY_URL = "toutiao_notify_url";
    public static final String TOUTIAO_REFUND_NOTIFY_URL = "toutiao_refund_notify_url";
    public static final String TOUTIAO_ACCESS_TOKEN = "toutiao_access_token";
    public static final String TOUTIAO_ACCESS_TOKEN_UPDATE_TIME = "toutiao_access_token_update_time";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("微信支付-微信公众号或者小程序等的appid")
    @TableField(value = "wx_pay_app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayAppId;
    @ApiModelProperty("微信支付-服务商模式下的子商户公众账号ID")
    @TableField(value = "wx_pay_sub_app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPaySubAppId;
    @ApiModelProperty("微信支付-商户号")
    @TableField(value = "wx_pay_mch_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayMchId;
    @ApiModelProperty("微信支付-商户工商注册全名")
    @TableField(value = "wx_pay_mch_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayMchName;
    @ApiModelProperty("微信支付-微信支付商户密钥")
    @TableField(value = "wx_pay_mch_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayMchKey;
    @ApiModelProperty("微信支付-企业支付密钥")
    @TableField(value = "wx_pay_ent_pay_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayEntPayKey;
    @ApiModelProperty("微信支付-服务商模式下的子商户号")
    @TableField(value = "wx_pay_sub_mch_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPaySubMchId;
    @ApiModelProperty("微信支付-微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数")
    @TableField(value = "wx_pay_notify_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayNotifyUrl;
    @ApiModelProperty("微信支付-微信支付商户号证书路径 p12证书的位置")
    @TableField(value = "wx_pay_key_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayKeyPath;
    @ApiModelProperty("微信支付-apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径")
    @TableField(value = "wx_pay_private_key_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayPrivateKeyPath;
    @ApiModelProperty("微信支付-apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径")
    @TableField(value = "wx_pay_private_cert_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayPrivateCertPath;
    @ApiModelProperty("微信支付-APIv3密钥")
    @TableField(value = "wx_pay_api_v3_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayApiV3Key;
    @ApiModelProperty("微信支付-APIv3 证书序列号值")
    @TableField(value = "wx_pay_api_cert_serial_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayApiCertSerialNo;
    @ApiModelProperty("微信支付-微信支付分")
    @TableField(value = "wx_pay_api_service_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayApiServiceId;
    @ApiModelProperty("微信支付-微信支付分回调地址")
    @TableField(value = "wx_pay_pay_score_notify_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayPayScoreNotifyUrl;
    @ApiModelProperty("微信支付-微信支付分授权回调地址")
    @TableField(value = "wx_pay_pay_score_permission_notify_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPaypayScorePermissionNotifyUrl;
    @ApiModelProperty("微信小程序-appId")
    @TableField(value = "wx_miniapp_app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappAppId;
    @ApiModelProperty("微信小程序-secret")
    @TableField(value = "wx_miniapp_app_secret", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappAppSecret;
    @ApiModelProperty("微信小程序-消息服务器配置的token")
    @TableField(value = "wx_miniapp_token", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappToken;
    @ApiModelProperty("微信小程序-消息服务器配置的EncodingAESKey")
    @TableField(value = "wx_miniapp_aes_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappAesKey;
    @ApiModelProperty("微信小程序-小程序原始ID")
    @TableField(value = "wx_miniapp_original_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappOriginalId;
    @ApiModelProperty("微信小程序-消息格式，XML或者JSON，默认JSON")
    @TableField(value = "wx_miniapp_msg_data_format", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappMsgDataFormat;
    @ApiModelProperty("微信小程序-云环境ID")
    @TableField(value = "wx_miniapp_colud_env", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappColudEnv;
    @ApiModelProperty("阿里云物流appkey")
    @TableField(value = "aliyun_wuliu_appkey", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunWuliuAppkey;
    @ApiModelProperty("阿里云物流appcode")
    @TableField(value = "aliyun_wuliu_appcode", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunWuliuAppcode;
    @ApiModelProperty("阿里云物流appsecret")
    @TableField(value = "aliyun_wuliu_appsecret", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunWuliuAppsecret;
    @ApiModelProperty("阿里云短信签名")
    @TableField(value = "aliyun_sms_signname", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunSmsSignname;
    @ApiModelProperty("阿里云短信id")
    @TableField(value = "aliyun_sms_accesskey_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunSmsAccesskeyId;
    @ApiModelProperty("阿里云短信secret")
    @TableField(value = "aliyun_sms_accesskey_secret", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunSmsAccesskeySecret;
    @ApiModelProperty("阿里云对象存储endpoint")
    @TableField(value = "aliyun_oss_file_endpoint", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunOssFileEndpoint;
    @ApiModelProperty("阿里云对象存储keyid")
    @TableField(value = "aliyun_oss_file_keyid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunOssFileKeyid;
    @ApiModelProperty("阿里云对象存储keysecret")
    @TableField(value = "aliyun_oss_file_keysecret", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunOssFileKeysecret;
    @ApiModelProperty("阿里云对象存储bucketname")
    @TableField(value = "aliyun_oss_file_bucketname", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunOssFileBucketname;
    @ApiModelProperty("阿里云OCRappsrcret")
    @TableField(value = "aliyun_ocr_appsecret", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunOcrAppsecret;
    @ApiModelProperty("阿里云OCRappcode")
    @TableField(value = "aliyun_ocr_appcode", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String aliyunOcrAppcode;
    @ApiModelProperty("支付宝应用")
    @TableField(value = "alipay_appid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String alipayAppid;
    @ApiModelProperty("支付宝商户账号")
    @TableField(value = "alipay_pid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String alipayPid;
    @ApiModelProperty("支付宝私钥")
    @TableField(value = "alipay_private_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String alipayPrivateKey;
    @ApiModelProperty("支付宝公钥")
    @TableField(value = "alipay_public_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String alipayPublicKey;
    @ApiModelProperty("支付宝支付回调")
    @TableField(value = "alipay_return_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String alipayReturnUrl;
    @ApiModelProperty("应用公钥证书文件路径")
    @TableField(value = "alipay_app_cert_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String alipayAppCertPath;
    @ApiModelProperty("支付宝公钥证书文件路径")
    @TableField(value = "alipay_cert_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String alipayCertPath;
    @ApiModelProperty("支付宝CA根证书文件路径")
    @TableField(value = "alipay_root_cert_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String alipayRootCertPath;
    @ApiModelProperty("字节跳动小程序id")
    @TableField(value = "toutiao_appid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String toutiaoAppid;
    @ApiModelProperty("字节跳动小程序密钥")
    @TableField(value = "toutiao_appsecret", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String toutiaoAppsecret;
    @ApiModelProperty("字节跳动小程序签名")
    @TableField(value = "toutiao_salt", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String toutiaoSalt;
    @ApiModelProperty("字节跳动小程序支付回调地址")
    @TableField(value = "toutiao_notify_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String toutiaoNotifyUrl;
    @ApiModelProperty("字节跳动小程序退款回调地址")
    @TableField(value = "toutiao_refund_notify_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String toutiaoRefundNotifyUrl;
    @ApiModelProperty("字节跳动小程序调用凭据")
    @TableField(value = "toutiao_access_token", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String toutiaoAccessToken;
    @ApiModelProperty("字节跳动小程序调用凭据更新时间")
    @TableField(value = "toutiao_access_token_update_time", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String toutiaoAccessTokenUpdateTime;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
