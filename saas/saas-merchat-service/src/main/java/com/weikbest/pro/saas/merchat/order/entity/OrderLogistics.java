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
 * 订单物流记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_logistics")
@ApiModel(value = "OrderLogistics对象", description = "订单物流记录表")
public class OrderLogistics implements Serializable {

    public static final String ID = "id";
    public static final String ORDER_ID = "order_id";
    public static final String LOGISTICS_COMPANY = "logistics_company";
    public static final String COURIER_NUMBER = "courier_number";
    public static final String LOGISTICS_TIME = "logistics_time";
    public static final String CONTENT = "content";
    public static final String IS_UPDATE = "is_update";
    public static final String IS_BATCH_DELIVER = "is_batch_deliver";
    public static final String BATCH_DELIVER_ID = "batch_deliver_id";
    public static final String DESCRIPTION = "description";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    public static final String CONSIGNER = "consigner";
    public static final String CONTACT = "contact";
    public static final String ADDR_PROVINCE = "addr_province";
    public static final String ADDR_CITY = "addr_city";
    public static final String ADDR_DISTRICT = "addr_district";
    public static final String ADDR = "addr";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    @TableField("order_id")
    private Long orderId;
    @ApiModelProperty("物流公司 字典表CODE")
    @TableField(value = "logistics_company", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String logisticsCompany;
    @ApiModelProperty("快递单号")
    @TableField(value = "courier_number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String courierNumber;
    @ApiModelProperty("发货时间")
    @TableField("logistics_time")
    private Date logisticsTime;
    @ApiModelProperty("第三方接口返回的物流信息")
    @TableField(value = "content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String content;
    @ApiModelProperty("是否已修改 0-否 1-是")
    @TableField(value = "is_update", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isUpdate;
    @ApiModelProperty("是否批量发货 0-否 1-是")
    @TableField(value = "is_batch_deliver", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isBatchDeliver;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("批量发货记录ID")
    @TableField(value = "batch_deliver_id", insertStrategy = FieldStrategy.NOT_NULL)
    private Long batchDeliverId;
    @ApiModelProperty("发货人")
    @TableField(value = "consigner", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String consigner;
    @ApiModelProperty("发货人联系方式")
    @TableField(value = "contact", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String contact;
    @ApiModelProperty("发货地址 省、直辖市")
    @TableField(value = "addr_province", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String addrProvince;
    @ApiModelProperty("发货地址 市")
    @TableField(value = "addr_city", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String addrCity;
    @ApiModelProperty("发货地址 区、县")
    @TableField(value = "addr_district", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String addrDistrict;
    @ApiModelProperty("发货详细地址 ")
    @TableField(value = "addr", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String addr;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
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
