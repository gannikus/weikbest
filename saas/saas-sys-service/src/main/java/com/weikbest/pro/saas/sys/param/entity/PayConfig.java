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
 * 系统支付商户号配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-14
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_pay_config")
@ApiModel(value = "PayConfig对象", description = "系统支付商户号配置表")
public class PayConfig implements Serializable {

    public static final String ID = "id";
    public static final String PAY_CONFIG_TYPE = "pay_config_type";
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
    public static final String WX_PAY_MCH_EFFECTIVE_TIME = "wx_pay_mch_effective_time";
    public static final String WX_PAY_MCH_EXPIRE_TIME = "wx_pay_mch_expire_time";
    public static final String WX_PLATFORM_SERIAL_NO = "wx_platform_serial_no";
    public static final String WX_PLATFORM_EFFECTIVE_TIME = "wx_platform_effective_time";
    public static final String WX_PLATFORM_EXPIRE_TIME = "wx_platform_expire_time";
    public static final String WX_OPERATE_PASSWORD = "wx_operate_password";
    public static final String WX_PAY_API_SERVICE_ID = "wx_pay_api_service_id";
    public static final String WX_PAY_PAY_SCORE_NOTIFY_URL = "wx_pay_pay_score_notify_url";
    public static final String WX_PAY_PAY_SCORE_PERMISSION_NOTIFY_URL = "wx_pay_pay_score_permission_notify_url";
    public static final String IS_BIND_BUSI_FAVOR = "is_bind_busi_favor";
    public static final String BUSI_FAVOR_CALLBACK = "busi_favor_callback";
    public static final String DATA_STATUS = "data_status";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("支付商户号类型  1-普通商户  3-特约商户")
    @TableField(value = "pay_config_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String payConfigType;
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
    @ApiModelProperty("微信支付-微信支付异步回调地址，通知url必须为直接可访问的url，不能携带参数")
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
    @ApiModelProperty("微信商户号证书序列号生效时间")
    @TableField("wx_pay_mch_effective_time")
    private Date wxPayMchEffectiveTime;
    @ApiModelProperty("微信商户号证书序列号失效时间")
    @TableField("wx_pay_mch_expire_time")
    private Date wxPayMchExpireTime;
    @ApiModelProperty("微信平台证书序列号")
    @TableField(value = "wx_platform_serial_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPlatformSerialNo;
    @ApiModelProperty("微信平台证书序列号生效时间")
    @TableField("wx_platform_effective_time")
    private Date wxPlatformEffectiveTime;
    @ApiModelProperty("微信平台证书序列号失效时间")
    @TableField("wx_platform_expire_time")
    private Date wxPlatformExpireTime;
    @ApiModelProperty("微信支付-微信在线申请新证书时填写的操作密码")
    @TableField(value = "wx_operate_password", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxOperatePassword;
    @ApiModelProperty("微信支付-微信支付分")
    @TableField(value = "wx_pay_api_service_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayApiServiceId;
    @ApiModelProperty("微信支付-微信支付分回调地址")
    @TableField(value = "wx_pay_pay_score_notify_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayPayScoreNotifyUrl;
    @ApiModelProperty("微信支付-微信支付分授权回调地址")
    @TableField(value = "wx_pay_pay_score_permission_notify_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayPayScorePermissionNotifyUrl;
    @ApiModelProperty("是否绑定商家券回调 0-否 1-是")
    @TableField(value = "is_bind_busi_favor", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isBindBusiFavor;
    @ApiModelProperty("绑定商家券回调返回数据")
    @TableField(value = "busi_favor_callback", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String busiFavorCallback;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
