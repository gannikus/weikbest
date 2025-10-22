package com.weikbest.pro.saas.applet.aftersale.module.vo;

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
@ApiModel(value = "AppOrderAfterSaleDetailVO对象", description = "订单售后详情信息")
public class AppOrderAfterSaleDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("申请时间 yyyy-MM-dd HH:mm:ss")
    private Date applyTime;

    @ApiModelProperty("申请类型 1-仅退款 2-退货退款 3-换货")
    private String applyType;

    @ApiModelProperty("申请原因 1-少发/漏发 2-质量问题 3-货物与描述不符 4-未按约定时间发货 5-发票问题 6-卖家发错货 7-假冒品牌 8-退运费 9-尺寸拍错/不喜欢/效果不好 10-其他")
    private String applyReason;

    @ApiModelProperty("货物件数")
    private Integer goodsNum;

    @ApiModelProperty("申请金额")
    private BigDecimal applyAmount;

    @ApiModelProperty("售后关键节点 0-售后关闭 1-售后成功 10-仅退款待处理 11-仅退款处理中 20-退货退款待处理 21-退货退款待商家收货  22-退货退款处理中 30-换货待处理 31-换货待商家收货 32-换货处理中 7-待买家退货 8-客户处理中 9-平台处理中")
    private String afterSaleKey;

    @ApiModelProperty("客户寄回物流公司 字典表CODE")
    private String backLogisticsCompany;

    @ApiModelProperty("客户寄回快递单号")
    private String backCourierNumber;

    @ApiModelProperty("客户寄回发货时间")
    private Date backLogisticsTime;

    /**
     * 订单信息表
     **/

    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("订单名称")
    private String name;

    /**
     * 订单参数详细表
     **/

    @ApiModelProperty("数量")
    private Integer buyNumber;

    @ApiModelProperty("商品名称")
    private String prodName;

    @ApiModelProperty("商品销售套餐属性组合（JSON）  {\"attrname\":attrValue,\"attrname\":attrValue}")
    private String prodComboAttrValues;

    @ApiModelProperty("商品图片")
    private String prodImg;

}