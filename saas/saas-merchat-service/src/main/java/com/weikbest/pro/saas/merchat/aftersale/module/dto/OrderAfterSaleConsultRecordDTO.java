package com.weikbest.pro.saas.merchat.aftersale.module.dto;

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
 * 订单售后协商历史记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderAfterSaleConsultRecordDTO对象", description = "订单售后协商历史记录表")
public class OrderAfterSaleConsultRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "售后编号不为空!")
    @ApiModelProperty(value = "售后编号 规则为年月日时分秒豪秒", required = true)
    private String number;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "订单ID不为空!")
    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

    @NotBlank(message = "关联小程序ID不为空!")
    @ApiModelProperty(value = "关联小程序ID", required = true)
    private String appId;

    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    private String orderAppletType;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "客户ID 不为空!")
    @ApiModelProperty(value = "客户ID ", required = true)
    private Long customerId;

    @ApiModelProperty(value = "协商排序", required = true)
    private Integer historyOrd;

    @NotBlank(message = "收货状态 0-未收货 1-已收货不为空!")
    @ApiModelProperty(value = "收货状态 0-未收货 1-已收货", required = true)
    private String takeDeliveryType;

    @ApiModelProperty(value = "订单第几次售后", required = true)
    private Integer afterSaleNum;

    @ApiModelProperty(value = "申请时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date applyTime;

    @NotBlank(message = "申请类型 1-仅退款 2-退货退款 3-换货不为空!")
    @ApiModelProperty(value = "申请类型 1-仅退款 2-退货退款 3-换货", required = true)
    private String applyType;

    @NotBlank(message = "申请原因 1-少发/漏发 2-质量问题 3-货物与描述不符 4-未按约定时间发货 5-发票问题 6-卖家发错货 7-假冒品牌 8-退运费 9-尺寸拍错/不喜欢/效果不好 10-其他不为空!")
    @ApiModelProperty(value = "申请原因 1-少发/漏发 2-质量问题 3-货物与描述不符 4-未按约定时间发货 5-发票问题 6-卖家发错货 7-假冒品牌 8-退运费 9-尺寸拍错/不喜欢/效果不好 10-其他", required = true)
    private String applyReason;

    @ApiModelProperty(value = "货物件数", required = true)
    private Integer goodsNum;

    @ApiModelProperty(value = "申请金额", required = true)
    private BigDecimal applyAmount;

    @NotBlank(message = "联系电话不为空!")
    @ApiModelProperty(value = "联系电话", required = true)
    private String customerPhone;

    @NotBlank(message = "问题描述不为空!")
    @ApiModelProperty(value = "问题描述", required = true)
    private String questionDetail;

    @NotBlank(message = "是否极速退款售后单 0-否 1-是不为空!")
    @ApiModelProperty(value = "是否极速退款售后单 0-否 1-是", required = true)
    private String isFastSale;

    @ApiModelProperty("退款去向 1-原路返回")
    private String refundTrend;

    @ApiModelProperty("拒绝说明")
    private String rejectDetail;

    @NotBlank(message = "0-售后关闭 1-售后成功 10-仅退款待处理 11-仅退款处理中 20-退货退款待处理 21-退货退款待商家收货  22-退货退款处理中 30-换货待处理 31-换货待商家收货 32-换货处理中 7-待买家退货 9-平台处理中 不为空!")
    @ApiModelProperty(value = "0-售后关闭 1-售后成功 10-仅退款待处理 11-仅退款处理中 20-退货退款待处理 21-退货退款待商家收货  22-退货退款处理中 30-换货待处理 31-换货待商家收货 32-换货处理中 7-待买家退货 9-平台处理中 ", required = true)
    private String afterSaleKey;

    @NotBlank(message = "售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后     不为空!")
    @ApiModelProperty(value = "售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后     ", required = true)
    private String afterSaleStatus;

    @ApiModelProperty("拒绝原因 1-已协商一致不退款 2-买家要求退款和应退金额不一致 3-买家退/换货商品不在承诺的售后范围 4-买家退/换货商品为空包裹 5-买家退/换货商品少件/漏发  9-其他")
    private String rejectReason;

    @NotBlank(message = "退货地址不为空!")
    @ApiModelProperty(value = "退货地址", required = true)
    private String backAddress;

    @NotBlank(message = "客户寄回物流公司 字典表CODE不为空!")
    @ApiModelProperty(value = "客户寄回物流公司 字典表CODE", required = true)
    private String backLogisticsCompany;

    @NotBlank(message = "客户寄回快递单号不为空!")
    @ApiModelProperty(value = "客户寄回快递单号", required = true)
    private String backCourierNumber;

    @ApiModelProperty(value = "客户寄回发货时间", required = true)
    private Date backLogisticsTime;

    @NotBlank(message = "客户寄回第三方接口返回的物流信息不为空!")
    @ApiModelProperty(value = "客户寄回第三方接口返回的物流信息", required = true)
    private String backContent;

    @ApiModelProperty("发货状态 0-未发货 1-已发货")
    private String sendType;

    @NotBlank(message = "商家再次发送物流公司 字典表CODE不为空!")
    @ApiModelProperty(value = "商家再次发送物流公司 字典表CODE", required = true)
    private String sendLogisticsCompany;

    @NotBlank(message = "商家再次发送快递单号不为空!")
    @ApiModelProperty(value = "商家再次发送快递单号", required = true)
    private String sendCourierNumber;

    @ApiModelProperty(value = "商家再次发送发货时间", required = true)
    private Date sendLogisticsTime;

    @NotBlank(message = "商家再次发送第三方接口返回的物流信息不为空!")
    @ApiModelProperty(value = "商家再次发送第三方接口返回的物流信息", required = true)
    private String sendContent;

    @ApiModelProperty("完成时间 yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    @NotBlank(message = "变更类型 1-客户申请 2-状态变更 3-客户物流信息变更 4-商家物流信息变更不为空!")
    @ApiModelProperty(value = "变更类型 1-客户申请 2-状态变更 3-客户物流信息变更 4-商家物流信息变更", required = true)
    private String changeType;

    @NotBlank(message = "变更人类型 0-平台用户 1-商家端用户 2-App端用户不为空!")
    @ApiModelProperty(value = "变更人类型 0-平台用户 1-商家端用户 2-App端用户", required = true)
    private String changerUserType;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "变更人ID不为空!")
    @ApiModelProperty(value = "变更人ID", required = true)
    private Long changerUserId;

    @NotBlank(message = "变更时间 yyyy-MM-dd HH:mm:ss不为空!")
    @ApiModelProperty(value = "变更时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date changeTime;

    @ApiModelProperty("备注")
    private String description;


}