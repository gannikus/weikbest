package com.weikbest.pro.saas.merchat.complaint.module.vo;

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
 * 订单投诉表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderComplaintPageVO对象", description = "订单投诉列表对象")
public class OrderComplaintPageVO extends OrderComplaintVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
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
     * 客户信息
     */
    @ApiModelProperty("昵称")
    private String tpName;

    @ApiModelProperty("头像")
    private String tpPhoto;

    /**
     * 店铺信息
     */
    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("店铺商户号")
    private String wxPayMchId;
}