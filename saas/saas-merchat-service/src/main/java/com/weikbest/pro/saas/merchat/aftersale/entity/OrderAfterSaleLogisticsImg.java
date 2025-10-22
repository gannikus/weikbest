package com.weikbest.pro.saas.merchat.aftersale.entity;

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
 * 订单售后物流图片拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_after_sale_logistics_img")
@ApiModel(value = "OrderAfterSaleLogisticsImg对象", description = "订单售后物流图片拆分表")
public class OrderAfterSaleLogisticsImg implements Serializable {

    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String COURIER_IMG_TYPE = "courier_img_type";
    public static final String COURIER_IMG_PATH = "courier_img_path";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "entry_id", type = IdType.ASSIGN_ID)
    private Long entryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableField("id")
    private Long id;
    @ApiModelProperty("快递物流图片类型 1-商家发货 2-客户寄回 3-商家再次发货")
    @TableField(value = "courier_img_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String courierImgType;
    @ApiModelProperty("快递图片链接")
    @TableField(value = "courier_img_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String courierImgPath;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
