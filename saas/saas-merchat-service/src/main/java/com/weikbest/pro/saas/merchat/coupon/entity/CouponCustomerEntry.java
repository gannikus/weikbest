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
 * 优惠券与适用客户拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_coupon_customer_entry")
@ApiModel(value = "CouponCustomerEntry对象", description = "优惠券与适用客户拆分表")
public class CouponCustomerEntry implements Serializable {

    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String COUPON_TYPE = "coupon_type";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String RESTRICT_USER_PHONE = "restrict_user_phone";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "entry_id", type = IdType.ASSIGN_ID)
    private Long entryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联优惠券ID ")
    @TableField("id")
    private Long id;
    @ApiModelProperty("优惠券类型 1-立减券 2-回流劵 3-平台券")
    @TableField(value = "coupon_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponType;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    @TableField("customer_id")
    private Long customerId;
    @ApiModelProperty("领取人手机号")
    @TableField(value = "restrict_user_phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String restrictUserPhone;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
