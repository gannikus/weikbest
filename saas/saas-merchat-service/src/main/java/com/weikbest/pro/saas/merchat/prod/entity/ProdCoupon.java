package com.weikbest.pro.saas.merchat.prod.entity;

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
 * 广告商品优惠劵拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_coupon")
@ApiModel(value = "ProdCoupon对象", description = "广告商品优惠劵拆分表")
public class ProdCoupon implements Serializable {

    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String SHOP_COUPON_TYPE = "shop_coupon_type";
    public static final String IS_OPEN_COUPON = "is_open_coupon";
    public static final String CHARGE_OFF = "charge_off";
    public static final String COUPON_OPEN_IMG = "coupon_open_img";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "entry_id", type = IdType.ASSIGN_ID)
    private Long entryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID ")
    @TableField(value = "id")
    private Long id;
    @ApiModelProperty("店铺优惠券类型 1-商品立减劵 2-回流优惠券")
    @TableField(value = "shop_coupon_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String shopCouponType;
    @ApiModelProperty("是否设置优惠券 0-否 1-是")
    @TableField(value = "is_open_coupon", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isOpenCoupon;
    @ApiModelProperty("主动核销 0-不主动 1-主动")
    @TableField(value = "charge_off", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String chargeOff;
    @ApiModelProperty("领劵弹窗图片")
    @TableField(value = "coupon_open_img", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponOpenImg;
    @ApiModelProperty("营销回传比例")
    @TableField(value = "callback_proportion")
    private BigDecimal callbackProportion;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
