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
import java.util.Date;

/**
 * <p>
 * 商品与优惠券绑定拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_coupon_relate")
@ApiModel(value = "ProdCouponRelate对象", description = "商品与优惠券绑定拆分表")
public class ProdCouponRelate implements Serializable {

    public static final String SUB_ENTRY_ID = "sub_entry_id";
    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String COUPON_ID = "coupon_id";
    public static final String COUPON_TYPE = "coupon_type";
    public static final String IS_EXPIRED = "is_expired";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID")
    @TableId(value = "sub_entry_id", type = IdType.ASSIGN_ID)
    private Long subEntryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品优惠券id")
    @TableField(value = "entry_id")
    private Long entryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    @TableField(value = "id")
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联优惠券ID ")
    @TableField("coupon_id")
    private Long couponId;
    @ApiModelProperty("优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券")
    @TableField(value = "coupon_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponType;
    @ApiModelProperty("优惠券是否过期 0-否 1-是")
    @TableField(value = "is_expired", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isExpired;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
