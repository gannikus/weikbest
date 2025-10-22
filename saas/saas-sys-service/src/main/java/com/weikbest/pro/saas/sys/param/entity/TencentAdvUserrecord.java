package com.weikbest.pro.saas.sys.param.entity;

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
 * 腾讯广告数据上报用户行为数据记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_tencent_adv_userrecord")
@ApiModel(value = "TencentAdvUserrecord对象", description = "腾讯广告数据上报用户行为数据记录表")
public class TencentAdvUserrecord implements Serializable {

    public static final String ID = "id";
    public static final String ORDER_ID = "order_id";
    public static final String ACTION_TYPE = "action_type";
    public static final String ACCOUNT_ID = "account_id";
    public static final String USER_ACTION_SET_ID = "user_action_set_id";
    public static final String AD_AID = "ad_aid";
    public static final String CLICK_ID = "click_id";
    public static final String PROD_ID = "prod_id";
    public static final String PAY_AMOUNT = "pay_amount";
    public static final String WECHAT_APP_ID = "wechat_app_id";
    public static final String WECHAT_OPENID = "wechat_openid";
    public static final String WECHAT_UNIONID = "wechat_unionid";
    public static final String RETURN_RESULTS = "return_results";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联订单ID")
    @TableField(value = "order_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long orderId;
    @ApiModelProperty("标准行为类型")
    @TableField(value = "action_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String actionType;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id")
    @TableField(value = "account_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long accountId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("用户行为源 id")
    @TableField(value = "user_action_set_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long userActionSetId;
    @ApiModelProperty("广告id/广告计划id")
    @TableField(value = "ad_aid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String adAid;
    @ApiModelProperty("点击id")
    @TableField(value = "click_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clickId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    @TableField("prod_id")
    private Long prodId;
    @ApiModelProperty("支付金额")
    @TableField("pay_amount")
    private BigDecimal payAmount;
    @ApiModelProperty("微信 小程序AppID")
    @TableField(value = "wechat_app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wechatAppId;
    @ApiModelProperty("小程序用户openid")
    @TableField(value = "wechat_openid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wechatOpenid;
    @ApiModelProperty("小程序用户unionid")
    @TableField(value = "wechat_unionid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wechatUnionid;
    @ApiModelProperty("应答结果json")
    @TableField(value = "return_results", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String returnResults;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
