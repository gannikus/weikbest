package com.weikbest.pro.saas.merchat.prod.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 商品服务承诺表
 * </p>
 *
 * @author macro
 * @since 2023-03-22
 */
@Getter
@Setter
@TableName("t_mmdm_prod_service_commitment")
@Accessors(chain = true)
@ToString
@ApiModel(value = "ProdServiceCommitment对象", description = "商品服务承诺表")
public class ProdServiceCommitment implements Serializable {

    public static final String ID = "id";

    public static final String PROD_ID = "prod_id";

    public static final String DELIVERY_TIME_SERVICE = "delivery_time_service";

    public static final String AFTER_SALES_SERVICE = "after_sales_service";

    public static final String OTHER_SERVICE = "other_service";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFY = "gmt_modify";

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty("商品id")
    @TableField("prod_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long prodId;

    @ApiModelProperty("承诺发货时间 1：当天发货 2：24小时 3：48小时 4：72小时")
    @TableField("delivery_time_service")
    private Integer deliveryTimeService;

    @ApiModelProperty("承诺售后服务 1：7天无理由 2：退货包邮费；多个用逗号分隔")
    @TableField("after_sales_service")
    private String afterSalesService;

    @ApiModelProperty("承诺其他服务，1：全场包邮；多个用逗号分隔")
    @TableField("other_service")
    private String otherService;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty("修改时间")
    @TableField(value = "gmt_modify",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModify;


}
