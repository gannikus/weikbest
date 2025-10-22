package com.weikbest.pro.saas.merchat.prod.entity;

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
 * 商品装修落地页拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_dec_floor")
@ApiModel(value = "ProdDecFloor对象", description = "商品装修落地页拆分表")
public class ProdDecFloor implements Serializable {

    public static final String ID = "id";
    public static final String PAGE_TITLE = "page_title";
    public static final String BUY_BTN_TITLE = "buy_btn_title";
    public static final String FLOAT_BTN_TITLE = "float_btn_title";
    public static final String COUNTDOWN_OFFERS = "countdown_offers";
    public static final String COUNTDOWN_TITLE = "countdown_title";
    public static final String COUNTDOWN_MINUTE = "countdown_minute";
    public static final String SUCCESS_PAY_BACK = "success_pay_back";
    public static final String ADV_BACK_TYPE = "adv_back_type";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("页面名称")
    @TableField(value = "page_title", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String pageTitle;
    @ApiModelProperty("立即购买按钮文字")
    @TableField(value = "buy_btn_title", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String buyBtnTitle;
    @ApiModelProperty("悬浮按钮文字")
    @TableField(value = "float_btn_title", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String floatBtnTitle;
    @ApiModelProperty("优惠倒计时 0-不开启 1-开启")
    @TableField(value = "countdown_offers", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String countdownOffers;
    @ApiModelProperty("优惠倒计时文字")
    @TableField(value = "countdown_title", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String countdownTitle;
    @ApiModelProperty("优惠倒计时分钟数")
    @TableField("countdown_minute")
    private Integer countdownMinute;
    @ApiModelProperty("支付成功回传 0-不回传 1-回传")
    @TableField(value = "success_pay_back", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String successPayBack;
    @ApiModelProperty("广告回传类型 1-全部回传 2-按投放账号回传")
    @TableField(value = "adv_back_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String advBackType;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
