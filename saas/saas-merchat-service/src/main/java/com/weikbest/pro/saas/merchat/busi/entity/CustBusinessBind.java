package com.weikbest.pro.saas.merchat.busi.entity;

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
 * 分账商户绑定表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_cust_business_bind")
@ApiModel(value = "CustBusinessBind对象", description = "分账商户绑定表")
public class CustBusinessBind implements Serializable {

    public static final String ID = "id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String BUSINESS_ID = "business_id";
    public static final String SHOP_ID = "shop_id";
    public static final String PROD_ID = "prod_id";
    public static final String NUMBER = "number";
    public static final String APP_ID = "app_id";
    public static final String BIND_TIME = "bind_time";
    public static final String VALIDITY_DAY = "validity_day";
    public static final String BIND_STATUS = "bind_status";
    public static final String DATA_STATUS = "data_status";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("客户ID")
    @TableField("customer_id")
    private Long customerId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    @TableField("business_id")
    private Long businessId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺ID")
    @TableField("shop_id")
    private Long shopId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    @TableField("prod_id")
    private Long prodId;
    @ApiModelProperty("订单号")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("关联小程序appid")
    @TableField(value = "app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appId;
    @ApiModelProperty("绑定时间 yyyy-MM-dd HH:mm:ss")
    @TableField("bind_time")
    private Date bindTime;
    @ApiModelProperty("有效期")
    @TableField(value = "validity_day", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String validityDay;
    @ApiModelProperty("绑定状态 1-绑定 2-解除绑定")
    @TableField(value = "bind_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String bindStatus;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
