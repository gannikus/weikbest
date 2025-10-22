package com.weikbest.pro.saas.sysmerchat.order.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/14
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SysOrderQO对象", description = "平台端订单分组查询")
public class SysOrderGroupQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @ApiModelProperty("订单号 规则为年月日时分秒豪秒")
    private String number;

    @ApiModelProperty("订单名称")
    private String name;

    @ApiModelProperty("订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭")
    private String orderStatus;

    @ApiModelProperty("发货超时 0-否 1-是")
    private String orderDeliverTimeout;

    @ApiModelProperty("售后中 0-否 1-是")
    private String orderAfterFlag;

    @ApiModelProperty("订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量")
    private String orderSource;

    @ApiModelProperty("下单路径")
    private String orderPath;

    @ApiModelProperty("支付方式 1-微信支付")
    private String payType;

    @ApiModelProperty("广告是否回传 0-否 1-是")
    private String isAdvBack;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联广告账户ID ")
    private Long advAccountId;

    @ApiModelProperty("广告渠道类型 1-腾讯广告 2-快手广告 3-序言泽联盟广告 4-腾讯视频号广告 5-美柚广告")
    private String advChannelType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品套餐ID")
    private Long prodComboId;

    @ApiModelProperty(value = "关联小程序ID")
    private String appId;

    @ApiModelProperty(value = "下单客户openid")
    private String openId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @ApiModelProperty("下单开始时间 yyyy-MM-dd HH:mm:ss")
    private Date orderStartTime;

    @ApiModelProperty("下单结束时间 yyyy-MM-dd HH:mm:ss")
    private Date orderEndTime;

    @ApiModelProperty("下单时间排序 asc-从小到大 desc-从大到小，默认asc")
    private String orderByOrderTime;

    @ApiModelProperty("订单分组状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭 15-24小时待发货状态 40-发货已超时 60-售后中")
    private String orderGroupStatus;

    @ApiModelProperty("售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后     ")
    private String afterSaleStatus;


    @ApiModelProperty("商品名称")
    private String prodName;

    @ApiModelProperty("收货人姓名")
    private String consignee;

    @ApiModelProperty("收货人手机号")
    private String consigneePhone;


    @ApiModelProperty("买家姓名")
    private String customerName;

    @ApiModelProperty("买家手机号")
    private String customerPhone;

    @ApiModelProperty("快递单号")
    private String courierNumber;

    @ApiModelProperty("发货开始时间 yyyy-MM-dd HH:mm:ss")
    private Date logisticsStartTime;

    @ApiModelProperty("发货结束时间 yyyy-MM-dd HH:mm:ss")
    private Date logisticsEndTime;


    @ApiModelProperty("支付流水号")
    private String tradingFlow;

    @ApiModelProperty("支付订单号")
    private String outTradeNo;

}
