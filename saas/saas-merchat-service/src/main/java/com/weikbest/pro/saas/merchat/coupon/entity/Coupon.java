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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 优惠券表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_coupon")
@ApiModel(value = "Coupon对象", description = "优惠券表")
public class Coupon implements Serializable {

    public static final String ID = "id";
    public static final String SHOP_ID = "shop_id";
    public static final String NAME = "name";
    public static final String TIPS = "tips";
    public static final String COUPON_TYPE = "coupon_type";
    public static final String COUPON_STATUS = "coupon_status";
    public static final String EVENT_START_TIME = "event_start_time";
    public static final String EVENT_END_TIME = "event_end_time";
    public static final String COUPON_NUM = "coupon_num";
    public static final String COUPON_PROD_TYPE = "coupon_prod_type";
    public static final String COUPON_USE_TYPE = "coupon_use_type";
    public static final String COUPON_USE_PRICE = "coupon_use_price";
    public static final String APP_ID = "app_id";
    public static final String DISCOUNT_AMOUNT = "discount_amount";
    public static final String GET_START_TIME = "get_start_time";
    public static final String GET_END_TIME = "get_end_time";
    public static final String RESTRICT_COUNT = "restrict_count";
    public static final String RESTRICT_USER_TYPE = "restrict_user_type";
    public static final String IS_OPEN = "is_open";
    public static final String USE_START_TIME = "use_start_time";
    public static final String USE_END_TIME = "use_end_time";
    public static final String ENABLE_TYPE = "enable_type";
    public static final String DELAY_ENABLE_DAY = "delay_enable_day";
    public static final String VALIDITY_DAY = "validity_day";
    public static final String COUPON_USE_URL = "coupon_use_url";
    public static final String COUPON_THEME_TYPE = "coupon_theme_type";
    public static final String COUPON_IMAGE_URL = "coupon_image_url";
    public static final String COUPON_IMAGE_OSSURL = "coupon_image_ossurl";
    public static final String MERCHAT_LOGO_URL = "merchat_logo_url";
    public static final String MERCHAT_LOGO_OSSURL = "merchat_logo_ossurl";
    public static final String OUT_REQUEST_NO = "out_request_no";
    public static final String BIND_MCH_ID = "bind_mch_id";
    public static final String BIND_WXGZH_APP_ID = "bind_wxgzh_app_id";
    public static final String BIND_PARTNER_MERCHANT_ID = "bind_partner_merchant_id";
    public static final String STOCK_ID = "stock_id";
    public static final String CREATE_TIME = "create_time";
    public static final String DESCRIPTION = "description";
    public static final String DATA_STATUS = "data_status";
    public static final String FLAG = "flag";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    @TableField("shop_id")
    private Long shopId;
    @ApiModelProperty("优惠券名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("优惠券内部备注")
    @TableField(value = "tips", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tips;
    @ApiModelProperty("优惠券类型 1-立减券 2-回流劵 3-平台券")
    @TableField(value = "coupon_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponType;
    @ApiModelProperty("优惠券状态 1-待发布 5-活动未开始 10-进行中 15-已结束 20-已取消")
    @TableField(value = "coupon_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponStatus;
    @ApiModelProperty("活动开始时间")
    @TableField("event_start_time")
    private Date eventStartTime;
    @ApiModelProperty("活动结束时间")
    @TableField("event_end_time")
    private Date eventEndTime;
    @ApiModelProperty("优惠券发行总量")
    @TableField("coupon_num")
    private Integer couponNum;
    @ApiModelProperty("适用商品类型 1-全部商品 2-部分商品")
    @TableField(value = "coupon_prod_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponProdType;
    @ApiModelProperty("使用门槛 0-无使用门槛 1-订单满多少金额可用")
    @TableField(value = "coupon_use_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponUseType;
    @ApiModelProperty("使用门槛价格（元）")
    @TableField("coupon_use_price")
    private BigDecimal couponUsePrice;
    @ApiModelProperty("核销小程序ID")
    @TableField(value = "app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appId;
    @ApiModelProperty("优惠金额（元）")
    @TableField("discount_amount")
    private BigDecimal discountAmount;
    @ApiModelProperty("领劵开始时间")
    @TableField("get_start_time")
    private Date getStartTime;
    @ApiModelProperty("领劵结束时间")
    @TableField("get_end_time")
    private Date getEndTime;
    @ApiModelProperty("每人限领次数")
    @TableField("restrict_count")
    private Integer restrictCount;
    @ApiModelProperty("领取人限制类型 1-不限制，所有人可领取 2-指定客户领取")
    @TableField(value = "restrict_user_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String restrictUserType;
    @ApiModelProperty("是否公开 0-否 1-是")
    @TableField(value = "is_open", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isOpen;
    @ApiModelProperty("用劵开始时间")
    @TableField("use_start_time")
    private Date useStartTime;
    @ApiModelProperty("用劵结束时间")
    @TableField("use_end_time")
    private Date useEndTime;
    @ApiModelProperty("生效类型 1-领券后立即生效 2-延迟生效")
    @TableField(value = "enable_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String enableType;
    @ApiModelProperty("领券（天）后生效")
    @TableField("delay_enable_day")
    private Integer delayEnableDay;
    @ApiModelProperty("有效期（天）")
    @TableField("validity_day")
    private Integer validityDay;
    @ApiModelProperty("用券页面链接")
    @TableField(value = "coupon_use_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponUseUrl;
    @ApiModelProperty("优惠券样式 1-默认图片 2-上传图片")
    @TableField(value = "coupon_theme_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponThemeType;
    @ApiModelProperty("卡包详情图片链接")
    @TableField(value = "coupon_image_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponImageUrl;
    @ApiModelProperty("卡包详情图片链接(oss存储)")
    @TableField(value = "coupon_image_ossurl", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponImageOssurl;
    @ApiModelProperty("卡包图标链接")
    @TableField(value = "merchat_logo_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String merchatLogoUrl;
    @ApiModelProperty("卡包图标链接(oss存储)")
    @TableField(value = "merchat_logo_ossurl", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String merchatLogoOssUrl;
    @ApiModelProperty("商户创建批次凭据号")
    @TableField(value = "out_request_no", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String outRequestNo;
    @ApiModelProperty("绑定商户号ID")
    @TableField(value = "bind_mch_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String bindMchId;
    @ApiModelProperty("绑定微信公众号ID")
    @TableField(value = "bind_wxgzh_app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String bindWxgzhAppId;
    @ApiModelProperty("微信优惠券批次号")
    @TableField(value = "stock_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String stockId;
    @ApiModelProperty("微信优惠券创建时间 YYYY-MM-DDTHH:mm:ss+TIMEZONE")
    @TableField(value = "create_time", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String createTime;
    @ApiModelProperty("绑定微信优惠券合作伙伴商户号ID")
    @TableField(value = "bind_partner_merchant_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String bindPartnerMerchantId;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
    @ApiModelProperty("逻辑删除 0-否 1-是")
    @TableField(value = "flag", insertStrategy = FieldStrategy.NOT_EMPTY)
    @TableLogic
    private String flag;
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

    @ApiModelProperty("领取比例")
    @TableField(value = "get_percentage")
    private BigDecimal getPercentage;

}
