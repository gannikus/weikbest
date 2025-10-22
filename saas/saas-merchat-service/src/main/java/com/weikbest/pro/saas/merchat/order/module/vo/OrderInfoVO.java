package com.weikbest.pro.saas.merchat.order.module.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 订单表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderInfoVO对象", description = "订单表")
public class OrderInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单号 规则为年月日时分秒豪秒")
    private String number;

    @ApiModelProperty("订单名称")
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品SKUID")
    private Long prodSkuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品套餐ID")
    private Long prodComboId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @ApiModelProperty(value = "关联小程序ID")
    private String appId;

    @ApiModelProperty(value = "下单客户openid")
    private String openId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @ApiModelProperty("下单时间 yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    @ApiModelProperty("订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量")
    private String orderSource;

    @ApiModelProperty("下单路径")
    private String orderPath;

    @ApiModelProperty("订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭")
    private String orderStatus;

    @ApiModelProperty("支付时间 yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    @ApiModelProperty("支付方式 1-微信支付")
    private String payType;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("支付运费")
    private BigDecimal payCarriage;

    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmount;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("当前售后服务ID")
    private Long orderAfterSaleId;

    @ApiModelProperty("售后次数")
    private Integer orderAfterSaleNum;

    @ApiModelProperty("客户是否删除订单 0-否 1-是")
    private String customerOperateDel;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单物流ID")
    private Long orderLogisticsId;

    @ApiModelProperty("交易完成时间 yyyy-MM-dd HH:mm:ss")
    private Date orderFinishTime;

    @ApiModelProperty("广告是否回传 0-否 1-是")
    private String isAdvBack;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联广告账户ID ")
    private String advAccountId;

    @ApiModelProperty("广告渠道类型 1-腾讯广告 2-快手广告 3-序言泽联盟广告 4-腾讯视频号广告 5-美柚广告")
    private String advChannelType;

    @ApiModelProperty("开启订单发货通知 0-否 1-是")
    private String deliverNotify;

    @ApiModelProperty("开启订单待付款通知 0-否 1-是")
    private String waitPayNotify;

    @ApiModelProperty("广告id/广告计划id")
    private String adAid;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @ApiModelProperty("到付应付金额")
    private BigDecimal deliveryPayAmount;

    @ApiModelProperty("主单号")
    private String mainNumber;

    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;


}
