package com.weikbest.pro.saas.merchat.aftersale.module.qo;

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
@ApiModel(value = "OrderAfterSaleQO对象", description = "订单售后表")
public class OrderAfterSaleQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("售后编号 规则为年月日时分秒豪秒")
    private String number;

    @ApiModelProperty("订单编号 规则为年月日时分秒豪秒")
    private String orderNumber;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("发货状态 0-未发货 1-已发货")
    private String sendType;

    @ApiModelProperty("是否极速退款售后单 0-否 1-是")
    private String isFastSale;

    @ApiModelProperty("售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后     ")
    private String afterSaleStatus;

    @ApiModelProperty("售后关键节点 0-售后关闭 1-售后成功 10-仅退款待处理 11-仅退款处理中 20-退货退款待处理 21-退货退款待商家收货  22-退货退款处理中 30-换货待处理 31-换货待商家收货 32-换货处理中 7-待买家退货 8-客户处理中 9-平台处理中")
    private String afterSaleKey;

    @ApiModelProperty("售后类型 1-仅退款 2-退货退款 3-换货")
    private String applyType;

    @ApiModelProperty("关联小程序ID")
    private String appId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @ApiModelProperty("买家退货运单号")
    private String backCourierNumber;

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
    @ApiModelProperty("商品套餐ID")
    private Long prodComboId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;


    @ApiModelProperty("商品名称")
    private String prodName;

    @ApiModelProperty("收货人姓名")
    private String consignee;

    @ApiModelProperty("收货人手机号")
    private String consigneePhone;


    @ApiModelProperty("售后申请开始时间 yyyy-MM-dd HH:mm:ss")
    private Date applyStartTime;

    @ApiModelProperty("售后申请结束时间 yyyy-MM-dd HH:mm:ss")
    private Date applyEndTime;

    @ApiModelProperty("下单时间排序 asc-从小到大 desc-从大到小，默认asc")
    private String orderByOrderTime;

}