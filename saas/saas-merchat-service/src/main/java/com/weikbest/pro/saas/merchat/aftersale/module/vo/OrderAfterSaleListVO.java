package com.weikbest.pro.saas.merchat.aftersale.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单售后表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderAfterSaleListVO对象", description = "订单售后列表对象")
public class OrderAfterSaleListVO extends OrderAfterSaleVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;


    /** 其他附表信息 */
    /**
     * 订单信息
     */
    @ApiModelProperty("订单编号")
    private String orderNumber;

    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭")
    private String orderStatus;

    @ApiModelProperty("订单是否发货 0-否 1-是")
    private String orderSendType;

    @ApiModelProperty("支付时间 yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    @ApiModelProperty("超时时间 yyyy-MM-dd HH:mm:ss")
    private Date timeOut;

    /**
     * 商品信息
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品SKUID")
    private Long prodSkuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品套餐ID")
    private Long prodComboId;

    @ApiModelProperty("商品名称")
    private String prodName;

    @ApiModelProperty("商品销售套餐名称")
    private String prodComboTitle;

    @ApiModelProperty("商品销售套餐属性组合（JSON）")
    private String prodComboAttrValues;

    @ApiModelProperty("商品销售套餐金额")
    private BigDecimal prodComboPrice;

    @ApiModelProperty("购买数量")
    private Integer buyNumber;

    @ApiModelProperty("商品图片")
    private String prodImg;


    /**
     * 买家收货人信息
     */
    @ApiModelProperty("客户微信名称")
    private String tpName;

    @ApiModelProperty("收货人姓名")
    private String consignee;

    @ApiModelProperty("收货人手机号")
    private String consigneePhone;

    @ApiModelProperty("收货地址 省、直辖市")
    private String addrProvince;

    @ApiModelProperty("收货地址 市")
    private String addrCity;

    @ApiModelProperty("收货地址 区、县")
    private String addrDistrict;

    @ApiModelProperty("详细地址")
    private String addr;

    @ApiModelProperty("客户备注")
    private String customerDescription;

    /**
     * 支付信息
     */
    @ApiModelProperty("付款状态 1-未支付 2-支付中 3-支付成功 4-支付失败")
    private String payStatus;

    @ApiModelProperty("支付流水号")
    private String tradingFlow;

    @ApiModelProperty("支付订单号")
    private String outTradeNo;

    @ApiModelProperty("退款号")
    private String refundTradeNo;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款状态 0-未退款 1-已退款 2-退款中")
    private String refundStatus;

    @ApiModelProperty("退款时间 yyyy-MM-dd HH:mm:ss")
    private Date refundTime;

}
