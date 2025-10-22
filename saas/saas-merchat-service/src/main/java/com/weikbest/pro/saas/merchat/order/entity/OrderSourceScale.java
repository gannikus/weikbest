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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单来源分账比例表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_source_scale")
@ApiModel(value = "OrderSourceScale对象", description = "订单来源分账比例表")
public class OrderSourceScale implements Serializable {

    public static final String ID = "id";
    public static final String ORDER_SOURCE = "order_source";
    public static final String ORDER_PATH = "order_path";
    public static final String BUSINESS_SCALE = "business_scale";
    public static final String PLATFORM_SCALE = "platform_scale";
    public static final String DATA_STATUS = "data_status";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量")
    @TableField(value = "order_source", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderSource;
    @ApiModelProperty("下单路径")
    @TableField(value = "order_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderPath;
    @ApiModelProperty("商户分账比例")
    @TableField("business_scale")
    private BigDecimal businessScale;
    @ApiModelProperty("平台分账比例")
    @TableField("platform_scale")
    private BigDecimal platformScale;
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
