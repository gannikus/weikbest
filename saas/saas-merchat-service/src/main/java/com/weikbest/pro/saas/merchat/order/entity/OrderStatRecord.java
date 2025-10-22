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
 * 订单状态变更记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_stat_record")
@ApiModel(value = "OrderStatRecord对象", description = "订单状态变更记录表 ")
public class OrderStatRecord implements Serializable {

    public static final String ID = "id";
    public static final String ORDER_ID = "order_id";
    public static final String CURRENT_STATE = "current_state";
    public static final String CHANGE_STATUS = "change_status";
    public static final String CHANGER_USER_ID = "changer_user_id";
    public static final String CHANGER_USER = "changer_user";
    public static final String CHANGE_TIME = "change_time";
    public static final String DESCRIPTION = "description";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    @TableField("order_id")
    private Long orderId;
    @ApiModelProperty("当前状态（订单状态）")
    @TableField(value = "current_state", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String currentState;
    @ApiModelProperty("变更状态（订单状态）")
    @TableField(value = "change_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String changeStatus;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("变更人ID")
    @TableField("changer_user_id")
    private Long changerUserId;
    @ApiModelProperty("变更人")
    @TableField(value = "changer_user", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String changerUser;
    @ApiModelProperty("变更时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "change_time", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Date changeTime;
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
