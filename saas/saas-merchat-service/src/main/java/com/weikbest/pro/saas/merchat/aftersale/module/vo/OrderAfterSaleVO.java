package com.weikbest.pro.saas.merchat.aftersale.module.vo;

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
@ApiModel(value = "OrderAfterSaleVO对象", description = "订单售后表")
public class OrderAfterSaleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("售后编号 规则为年月日时分秒豪秒")
    private String number;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("关联小程序ID")
    private String appId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("客户ID ")
    private Long customerId;

    @ApiModelProperty("收货状态 0-未收货 1-已收货")
    private String takeDeliveryType;

    @ApiModelProperty("订单第几次售后")
    private Integer afterSaleNum;

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

    @ApiModelProperty("联系电话")
    private String customerPhone;

    @ApiModelProperty("问题描述")
    private String questionDetail;

    @ApiModelProperty("是否极速退款售后单 0-否 1-是")
    private String isFastSale;

    @ApiModelProperty("售后关键节点 0-售后关闭 1-售后成功 10-仅退款待处理 11-仅退款处理中 20-退货退款待处理 21-退货退款待商家收货  22-退货退款处理中 30-换货待处理 31-换货待商家收货 32-换货处理中 7-待买家退货 8-客户处理中 9-平台处理中")
    private String afterSaleKey;

    @ApiModelProperty("售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后     ")
    private String afterSaleStatus;

    @ApiModelProperty("退货地址")
    private String backAddress;

    @ApiModelProperty("客户寄回物流公司 字典表CODE")
    private String backLogisticsCompany;

    @ApiModelProperty("客户寄回快递单号")
    private String backCourierNumber;

    @ApiModelProperty("客户寄回手机号")
    private String backCourierPhone;

    @ApiModelProperty("客户寄回发货时间")
    private Date backLogisticsTime;

    @ApiModelProperty("客户寄回第三方接口返回的物流信息")
    private String backContent;

    @ApiModelProperty("发货状态 0-未发货 1-已发货")
    private String sendType;

    @ApiModelProperty("商家再次发送物流公司 字典表CODE")
    private String sendLogisticsCompany;

    @ApiModelProperty("商家再次发送快递单号")
    private String sendCourierNumber;

    @ApiModelProperty("商家再次发送手机号")
    private String sendCourierPhone;

    @ApiModelProperty("商家再次发送发货时间")
    private Date sendLogisticsTime;

    @ApiModelProperty("商家再次发送第三方接口返回的物流信息")
    private String sendContent;

    @ApiModelProperty("完成时间 yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("执行退款失败原因")
    private String refundFailureReason;

    @ApiModelProperty("执行退款任务失败次数")
    private Integer refundFailureTimes;


}
