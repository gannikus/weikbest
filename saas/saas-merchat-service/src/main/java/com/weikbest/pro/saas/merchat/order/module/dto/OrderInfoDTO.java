package com.weikbest.pro.saas.merchat.order.module.dto;

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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "OrderInfoDTO对象", description = "订单表")
public class OrderInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "订单号 规则为年月日时分秒豪秒不为空!")
    @ApiModelProperty(value = "订单号 规则为年月日时分秒豪秒", required = true)
    private String number;

    @NotBlank(message = "订单名称不为空!")
    @ApiModelProperty(value = "订单名称", required = true)
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商户ID不为空!")
    @ApiModelProperty(value = "关联商户ID", required = true)
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联店铺ID不为空!")
    @ApiModelProperty(value = "关联店铺ID", required = true)
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品ID不为空!")
    @ApiModelProperty(value = "关联商品ID", required = true)
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品SKUID不为空!")
    @ApiModelProperty(value = "关联商品SKUID", required = true)
    private Long prodSkuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商品套餐ID不为空!")
    @ApiModelProperty(value = "商品套餐ID", required = true)
    private Long prodComboId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联客户ID 不为空!")
    @ApiModelProperty(value = "关联客户ID ", required = true)
    private Long customerId;

    @NotNull(message = "关联小程序ID 不为空!")
    @ApiModelProperty(value = "关联小程序ID", required = true)
    private String appId;

    @NotNull(message = "下单客户openid 不为空!")
    @ApiModelProperty(value = "下单客户openid", required = true)
    private String openId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @ApiModelProperty(value = "下单时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date orderTime;

    @NotBlank(message = "订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量不为空!")
    @ApiModelProperty(value = "订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量", required = true)
    private String orderSource;

    @ApiModelProperty("下单路径")
    private String orderPath;

    @NotBlank(message = "订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭不为空!")
    @ApiModelProperty(value = "订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭", required = true)
    private String orderStatus;

    @ApiModelProperty(value = "支付时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date payTime;

    @NotBlank(message = "支付方式 1-微信支付不为空!")
    @ApiModelProperty(value = "支付方式 1-微信支付", required = true)
    private String payType;

    @ApiModelProperty(value = "订单金额", required = true)
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "支付金额", required = true)
    private BigDecimal payAmount;

    @ApiModelProperty(value = "支付运费", required = true)
    private BigDecimal payCarriage;

    @ApiModelProperty(value = "优惠金额", required = true)
    private BigDecimal discountAmount;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "当前售后服务ID不为空!")
    @ApiModelProperty(value = "当前售后服务ID", required = true)
    private Long orderAfterSaleId;

    @ApiModelProperty(value = "售后次数", required = true)
    private Integer orderAfterSaleNum;

    @NotBlank(message = "客户是否删除订单 0-否 1-是不为空!")
    @ApiModelProperty(value = "客户是否删除订单 0-否 1-是", required = true)
    private String customerOperateDel;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "订单物流ID不为空!")
    @ApiModelProperty(value = "订单物流ID", required = true)
    private Long orderLogisticsId;

    @ApiModelProperty(value = "交易完成时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date orderFinishTime;

    @ApiModelProperty("广告是否回传 0-否 1-是")
    private String isAdvBack;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联广告账户ID ")
    private Long advAccountId;

    @ApiModelProperty("广告渠道类型 1-腾讯广告 2-快手广告 3-序言泽联盟广告 4-腾讯视频号广告 5-美柚广告")
    private String advChannelType;

    @ApiModelProperty("开启订单发货通知 0-否 1-是")
    private String deliverNotify;

    @ApiModelProperty("开启订单待付款通知 0-否 1-是")
    private String waitPayNotify;

    @ApiModelProperty("腾讯广告id/腾讯广告计划id")
    private String adAid;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


    @ApiModelProperty("到付应付金额")
    private BigDecimal deliveryPayAmount;


    @ApiModelProperty("主单号")
    private String mainNumber;

}
