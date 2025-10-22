package com.weikbest.pro.saas.merchat.coupon.entity;

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
 * 客户领用优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_ccmm_cust_coupon_restrict")
@ApiModel(value = "CustCouponRestrict对象", description = "客户领用优惠券表")
public class CustCouponRestrict implements Serializable {

    public static final String ID = "id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String SHOP_ID = "shop_id";
    public static final String PROD_ID = "prod_id";
    public static final String APP_ID = "app_id";
    public static final String COUPON_ID = "coupon_id";
    public static final String COUPON_TYPE = "coupon_type";
    public static final String RESTRICT_TYPE = "restrict_type";
    public static final String RESTRICT_USER_PHONE = "restrict_user_phone";
    public static final String GET_REQUEST_NO = "get_request_no";
    public static final String RESTRICT_DATE = "restrict_date";
    public static final String RESTRICT_USE_DATE = "restrict_use_date";
    public static final String COUPON_CODE = "coupon_code";
    public static final String OUT_REQUEST_NO = "out_request_no";
    public static final String IS_COUPONS_USE = "is_coupons_use";
    public static final String COUPONS_USE_TIME = "coupons_use_time";
    public static final String IS_COUPONS_RETURN = "is_coupons_return";
    public static final String RETURN_REQUEST_NO = "return_request_no";
    public static final String COUPONS_RETURN_TIME = "coupons_return_time";
    public static final String IS_COUPONS_DEACTIVATE = "is_coupons_deactivate";
    public static final String DEACTIVATE_REQUEST_NO = "deactivate_request_no";
    public static final String COUPONS_DEACTIVATE_TIME = "coupons_deactivate_time";
    public static final String IS_TERRACE = "is_terrace";
    public static final String TERRACE_WX_PAY_MCH_ID = "terrace_wx_pay_mch_id";
    public static final String TERRACE_WX_PAY_MCH_KEY = "terrace_wx_pay_mch_key";
    public static final String AD_AID = "ad_aid";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    @TableField("customer_id")
    private Long customerId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    @TableField("shop_id")
    private Long shopId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    @TableField("prod_id")
    private Long prodId;
    @ApiModelProperty("关联小程序appid")
    @TableField(value = "app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("优惠券ID")
    @TableField("coupon_id")
    private Long couponId;
    @ApiModelProperty("优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券")
    @TableField(value = "coupon_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponType;
    @ApiModelProperty("优惠券领用状态 1-未生效 5-未使用 10-已过期 15-冻结中 20-用券核销 25-主动核销")
    @TableField(value = "restrict_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String restrictType;
    @ApiModelProperty("领取人手机号")
    @TableField(value = "restrict_user_phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String restrictUserPhone;
    @ApiModelProperty("领券请求单号")
    @TableField(value = "get_request_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String getRequestNo;
    @ApiModelProperty("领用日期 yyyy-MM-dd HH:mm:ss")
    @TableField("restrict_date")
    private Date restrictDate;
    @ApiModelProperty("领用使用日期 yyyy-MM-dd HH:mm:ss")
    @TableField("restrict_use_date")
    private Date restrictUseDate;
    @ApiModelProperty("微信优惠券唯一标识")
    @TableField(value = "coupon_code", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponCode;
    @ApiModelProperty("是否核销标识 0-否 1-是")
    @TableField(value = "is_coupons_use", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isCouponsUse;
    @ApiModelProperty("微信优惠券核销凭据号")
    @TableField(value = "out_request_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String outRequestNo;
    @ApiModelProperty("核销时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "coupons_use_time", fill = FieldFill.INSERT)
    private Date couponsUseTime;
    @ApiModelProperty("是否退券标识 0-否 1-是")
    @TableField(value = "is_coupons_return", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isCouponsReturn;
    @ApiModelProperty("退券请求单据号")
    @TableField(value = "return_request_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String returnRequestNo;
    @ApiModelProperty("退券时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "coupons_return_time", fill = FieldFill.INSERT)
    private Date couponsReturnTime;
    @ApiModelProperty("是否失效标识 0-否 1-是")
    @TableField(value = "is_coupons_deactivate", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isCouponsDeactivate;
    @ApiModelProperty("失效请求单据号")
    @TableField(value = "deactivate_request_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String deactivateRequestNo;
    @ApiModelProperty("失效时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "coupons_deactivate_time", fill = FieldFill.INSERT)
    private Date couponsDeactivateTime;
    @ApiModelProperty("是否授权平台 0-否 1-是")
    @TableField(value = "is_terrace", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isTerrace;
    @ApiModelProperty("平台wxPayMchId")
    @TableField(value = "terrace_wx_pay_mch_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String terraceWxPayMchId;
    @ApiModelProperty("平台v2key")
    @TableField(value = "terrace_wx_pay_mch_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String terraceWxPayMchKey;
    @ApiModelProperty("腾讯广告id/腾讯广告计划id")
    @TableField(value = "ad_aid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String adAid;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
