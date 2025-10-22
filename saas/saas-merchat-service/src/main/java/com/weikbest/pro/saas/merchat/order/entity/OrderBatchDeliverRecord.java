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
 * 订单批量发货记录拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_batch_deliver_record")
@ApiModel(value = "OrderBatchDeliverRecord对象", description = "订单批量发货记录拆分表")
public class OrderBatchDeliverRecord implements Serializable {

    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_NUMBER = "order_number";
    public static final String IMPORT_STATUS = "import_status";
    public static final String LOGISTICS_COMPANY_NAME = "logistics_company_name";
    public static final String COURIER_NUMBER = "courier_number";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "entry_id", type = IdType.ASSIGN_ID)
    private Long entryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单批量发货ID")
    @TableField("id")
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    @TableField("order_id")
    private Long orderId;
    @ApiModelProperty("订单号")
    @TableField(value = "order_number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderNumber;
    @ApiModelProperty("导入状态 0-失败 1-成功")
    @TableField(value = "import_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String importStatus;
    @ApiModelProperty("物流公司")
    @TableField(value = "logistics_company_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String logisticsCompanyName;
    @ApiModelProperty("快递单号")
    @TableField(value = "courier_number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String courierNumber;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
