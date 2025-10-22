package com.weikbest.pro.saas.merchat.shop.entity;

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
 * 店铺第三方平台分账接收方拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_shop_third_receive")
@ApiModel(value = "ShopThirdReceive对象", description = "店铺第三方平台分账接收方拆分多行表")
public class ShopThirdReceive implements Serializable {

    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String WX_PAY_APP_ID = "wx_pay_app_id";
    public static final String WX_PAY_RECEIVE_MCH_ID = "wx_pay_receive_mch_id";
    public static final String JOIN_TIME = "join_time";
    public static final String WX_PAY_RECEIVE_TYPE = "wx_pay_receive_type";
    public static final String WX_PAY_RECEIVE_NAME = "wx_pay_receive_name";
    public static final String WX_PAY_RECEIVE_RELATION_TYPE = "wx_pay_receive_relation_type";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID")
    @TableId(value = "entry_id", type = IdType.ASSIGN_ID)
    private Long entryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺ID")
    @TableField("id")
    private Long id;
    @ApiModelProperty("微信支付-微信公众号或者小程序等的appid")
    @TableField(value = "wx_pay_app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayAppId;
    @ApiModelProperty("微信支付-分账接收方商户号")
    @TableField(value = "wx_pay_receive_mch_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayReceiveMchId;
    @ApiModelProperty("添加日期")
    @TableField("join_time")
    private Date joinTime;
    @ApiModelProperty("分账接收方类型  MERCHANT_ID-商户")
    @TableField(value = "wx_pay_receive_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayReceiveType;
    @ApiModelProperty("分账接收方名称")
    @TableField(value = "wx_pay_receive_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayReceiveName;
    @ApiModelProperty("分账接收方关系  PARTNER-合作伙伴")
    @TableField(value = "wx_pay_receive_relation_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxPayReceiveRelationType;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
