package com.weikbest.pro.saas.merchat.aftersale.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("t_mmdm_order_after_sale")
@ApiModel(value = "OrderAfterSale对象", description = "订单售后表")
public class OrderAfterSale implements Serializable {

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String ORDER_ID = "order_id";
    public static final String APP_ID = "app_id";
    public static final String ORDER_APPLET_TYPE = "order_applet_type";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String TAKE_DELIVERY_TYPE = "take_delivery_type";
    public static final String AFTER_SALE_NUM = "after_sale_num";
    public static final String APPLY_TIME = "apply_time";
    public static final String APPLY_TYPE = "apply_type";
    public static final String APPLY_REASON = "apply_reason";
    public static final String APPLY_SOURCE_TYPE = "apply_source_type";
    public static final String GOODS_NUM = "goods_num";
    public static final String APPLY_AMOUNT = "apply_amount";
    public static final String CUSTOMER_PHONE = "customer_phone";
    public static final String QUESTION_DETAIL = "question_detail";
    public static final String IS_FAST_SALE = "is_fast_sale";
    public static final String REFUND_TREND = "refund_trend";
    public static final String AFTER_SALE_KEY = "after_sale_key";
    public static final String AFTER_SALE_STATUS = "after_sale_status";
    public static final String REJECT_REASON = "reject_reason";
    public static final String REJECT_DETAIL = "reject_detail";
    public static final String BACK_ADDRESS = "back_address";
    public static final String BACK_LOGISTICS_COMPANY = "back_logistics_company";
    public static final String BACK_COURIER_NUMBER = "back_courier_number";
    public static final String BACK_COURIER_PHONE = "back_courier_phone";
    public static final String BACK_LOGISTICS_TIME = "back_logistics_time";
    public static final String BACK_CONTENT = "back_content";
    public static final String SEND_LOGISTICS_COMPANY = "send_logistics_company";
    public static final String SEND_TYPE = "send_type";
    public static final String SEND_COURIER_NUMBER = "send_courier_number";
    public static final String SEND_COURIER_PHONE = "send_courier_phone";
    public static final String SEND_LOGISTICS_TIME = "send_logistics_time";
    public static final String SEND_CONTENT = "send_content";
    public static final String FINISH_TIME = "finish_time";
    public static final String DESCRIPTION = "description";
    public static final String FLAG = "flag";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("售后编号 规则为年月日时分秒豪秒")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    @TableField("order_id")
    private Long orderId;
    @ApiModelProperty("关联小程序ID")
    @TableField("app_id")
    private String appId;
    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    @TableField("order_applet_type")
    private String orderAppletType;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("客户ID ")
    @TableField("customer_id")
    private Long customerId;
    @ApiModelProperty("收货状态 0-未收货 1-已收货")
    @TableField(value = "take_delivery_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String takeDeliveryType;
    @ApiModelProperty("订单第几次售后")
    @TableField("after_sale_num")
    private Integer afterSaleNum;
    @ApiModelProperty("申请时间 yyyy-MM-dd HH:mm:ss")
    @TableField("apply_time")
    private Date applyTime;
    @ApiModelProperty("申请类型 1-仅退款 2-退货退款 3-换货")
    @TableField(value = "apply_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String applyType;
    @ApiModelProperty("申请原因 1-少发/漏发 2-质量问题 3-货物与描述不符 4-未按约定时间发货 5-发票问题 6-卖家发错货 7-假冒品牌 8-退运费 9-尺寸拍错/不喜欢/效果不好 10-其他")
    @TableField(value = "apply_reason", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String applyReason;
    @ApiModelProperty("申请来源 1-客户申请 2-商家代申请")
    @TableField(value = "apply_source_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String applySourceType;
    @ApiModelProperty("货物件数")
    @TableField("goods_num")
    private Integer goodsNum;
    @ApiModelProperty("申请金额")
    @TableField("apply_amount")
    private BigDecimal applyAmount;
    @ApiModelProperty("联系电话")
    @TableField(value = "customer_phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String customerPhone;
    @ApiModelProperty("问题描述")
    @TableField(value = "question_detail", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String questionDetail;
    @ApiModelProperty("退款去向 1-原路返回")
    @TableField(value = "refund_trend", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String refundTrend;
    @ApiModelProperty("是否极速退款售后单 0-否 1-是")
    @TableField(value = "is_fast_sale", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isFastSale;
    @ApiModelProperty("售后关键节点 0-售后关闭 1-售后成功 10-仅退款待处理 11-仅退款处理中 20-退货退款待处理 21-退货退款待商家收货  22-退货退款处理中 30-换货待处理 31-换货待商家收货 32-换货处理中 7-待买家退货 8-客户处理中 9-平台处理中")
    @TableField(value = "after_sale_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String afterSaleKey;
    @ApiModelProperty("售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 12-退款处理中 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后     ")
    @TableField(value = "after_sale_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String afterSaleStatus;
    @ApiModelProperty("拒绝原因 1-已协商一致不退款 2-买家要求退款和应退金额不一致 3-买家退/换货商品不在承诺的售后范围 4-买家退/换货商品为空包裹 5-买家退/换货商品少件/漏发  9-其他")
    @TableField(value = "reject_reason", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String rejectReason;
    @ApiModelProperty("拒绝说明")
    @TableField(value = "reject_detail", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String rejectDetail;
    @ApiModelProperty("退货地址")
    @TableField(value = "back_address", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String backAddress;
    @ApiModelProperty("客户寄回物流公司 字典表CODE")
    @TableField(value = "back_logistics_company", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String backLogisticsCompany;
    @ApiModelProperty("客户寄回快递单号")
    @TableField(value = "back_courier_number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String backCourierNumber;
    @ApiModelProperty("客户寄回手机号")
    @TableField(value = "back_courier_phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String backCourierPhone;
    @ApiModelProperty("客户寄回发货时间")
    @TableField("back_logistics_time")
    private Date backLogisticsTime;
    @ApiModelProperty("客户寄回第三方接口返回的物流信息")
    @TableField(value = "back_content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String backContent;
    @ApiModelProperty("发货状态 0-未发货 1-已发货")
    @TableField(value = "send_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendType;
    @ApiModelProperty("商家再次发送物流公司 字典表CODE")
    @TableField(value = "send_logistics_company", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendLogisticsCompany;
    @ApiModelProperty("商家再次发送快递单号")
    @TableField(value = "send_courier_number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendCourierNumber;
    @ApiModelProperty("商家再次发送手机号")
    @TableField(value = "send_courier_phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendCourierPhone;
    @ApiModelProperty("商家再次发送发货时间")
    @TableField("send_logistics_time")
    private Date sendLogisticsTime;
    @ApiModelProperty("商家再次发送第三方接口返回的物流信息")
    @TableField(value = "send_content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendContent;
    @ApiModelProperty("完成时间 yyyy-MM-dd HH:mm:ss")
    @TableField("finish_time")
    private Date finishTime;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("逻辑删除 0-否 1-是")
    @TableField(value = "flag", insertStrategy = FieldStrategy.NOT_EMPTY)
    @TableLogic
    private String flag;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;

    @ApiModelProperty("执行退款失败原因")
    @TableField(value = "refund_failure_reason")
    private String refundFailureReason;

    @ApiModelProperty("执行退款任务失败次数")
    @TableField(value = "refund_failure_times")
    private Integer refundFailureTimes;

}
