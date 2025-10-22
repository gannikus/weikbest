package com.weikbest.pro.saas.merchat.order.entity;

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
 * 订单收货地址拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_rec_addr")
@ApiModel(value = "OrderRecAddr对象", description = "订单收货地址拆分表")
public class OrderRecAddr implements Serializable {

    public static final String ID = "id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String CONSIGNEE = "consignee";
    public static final String PHONE = "phone";
    public static final String ADDR_PROVINCE = "addr_province";
    public static final String ADDR_CITY = "addr_city";
    public static final String ADDR_DISTRICT = "addr_district";
    public static final String ADDR = "addr";
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
    @ApiModelProperty("收货人")
    @TableField(value = "consignee", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String consignee;
    @ApiModelProperty("手机号 ")
    @TableField(value = "phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String phone;
    @ApiModelProperty("收货地址 省、直辖市")
    @TableField(value = "addr_province", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String addrProvince;
    @ApiModelProperty("收货地址 市")
    @TableField(value = "addr_city", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String addrCity;
    @ApiModelProperty("收货地址 区、县")
    @TableField(value = "addr_district", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String addrDistrict;
    @ApiModelProperty("详细地址 ")
    @TableField(value = "addr", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String addr;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
