package com.weikbest.pro.saas.merchat.shop.entity;

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
 * 店铺第三方平台拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_shop_third")
@ApiModel(value = "ShopThird对象", description = "店铺第三方平台拆分表")
public class ShopThird implements Serializable {

    public static final String ID = "id";
    public static final String IS_ADD_RECEIVE = "is_add_receive";
    public static final String WX_PAY_RECEIVE_MCH_ID = "wx_pay_receive_mch_id";
    public static final String WX_MCH_TYPE = "wx_mch_type";
    public static final String WX_PAY_MCH_ID = "wx_pay_mch_id";
    public static final String WX_PAY_MCH_KEY = "wx_pay_mch_key";
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
    public static final String IS_BIND_BUSI_FAVOR = "is_bind_busi_favor";
    public static final String BUSI_FAVOR_CALLBACK = "busi_favor_callback";
    public static final String WX_BUSINESS = "wx_business";
    public static final String FLAG = "flag";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("是否添加分账接收方 0-否1-是")
    @TableField(value = "is_add_receive", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isAddReceive;
    @ApiModelProperty("微信商户类型 1-普通商户")
    @TableField(value = "wx_mch_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMchType;
    @ApiModelProperty("微信支付-商户号")
    @TableField(value = "wx_pay_mch_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayMchId;
    @ApiModelProperty("微信支付-微信支付商户密钥")
    @TableField(value = "wx_pay_mch_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayMchKey;
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
    @ApiModelProperty("是否绑定商家券回调 0-否 1-是")
    @TableField(value = "is_bind_busi_favor", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isBindBusiFavor;
    @ApiModelProperty("绑定商家券回调返回数据")
    @TableField(value = "busi_favor_callback", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String busiFavorCallback;
    @ApiModelProperty("企业微信客服")
    @TableField(value = "wx_business", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxBusiness;
    @ApiModelProperty("逻辑删除 0-否 1-是")
    @TableField(value = "flag", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String flag;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
