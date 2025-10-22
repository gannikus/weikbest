package com.weikbest.pro.saas.applet.order.module.vo;

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
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppOrderDetailVO对象", description = "订单详情")
public class AppOrderDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** -----------  以下为订单信息  ----------- ***/

    @ApiModelProperty("订单ID")
    private String id;

    @ApiModelProperty("订单号 规则为年月日时分秒豪秒")
    private String number;

    @ApiModelProperty("订单名称")
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @ApiModelProperty("商品名称")
    private String prodName;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品套餐ID")
    private Long prodComboId;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("支付运费")
    private BigDecimal payCarriage;

    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmount;

    @ApiModelProperty("订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭")
    private String orderStatus;

    @ApiModelProperty("待支付订单结束时间，取值：下单时间+1天")
    private Date endTime;

    @ApiModelProperty("下单时间 yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    @ApiModelProperty("支付时间 yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    @ApiModelProperty("流水号")
    private String tradingFlow;

    @ApiModelProperty("客户是否删除订单 0-否 1-是")
    private String customerOperateDel;

    @ApiModelProperty("支付方式")
    private String payType;

    @ApiModelProperty("到付应付金额")
    private BigDecimal deliveryPayAmount;


    /*** -----------  以下为订单商品信息  ----------- ***/


    @ApiModelProperty("商品销售套餐名称")
    private String prodComboTitle;

    @ApiModelProperty("商品销售套餐属性组合（JSON）  {\"attrname\":attrValue,\"attrname\":attrValue}")
    private String prodComboAttrValues;

    @ApiModelProperty("商品销售套餐金额")
    private BigDecimal prodComboPrice;

    @ApiModelProperty("商品套餐划线价")
    private BigDecimal prodComboStandardPrice;

    @ApiModelProperty("商品成本价")
    private BigDecimal prodComboCostPrice;

    @ApiModelProperty("商品图片")
    private String prodImg;

    @ApiModelProperty("数量")
    private Integer buyNumber;


    /*** -----------  以下为收货地址信息  ----------- ***/

    @ApiModelProperty("收货地址信息")
    private AppOrderRecAddrVO appOrderRecAddrVO;


    @ApiModelProperty("企业微信客服")
    private String wxBusiness;

}
