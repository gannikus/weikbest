package com.weikbest.pro.saas.merchat.order.entity;

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
@TableName("t_mmdm_order_info")
@ApiModel(value = "OrderInfo对象", description = "订单表")
public class OrderInfo implements Serializable {

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String BUSINESS_ID = "business_id";
    public static final String SHOP_ID = "shop_id";
    public static final String PROD_ID = "prod_id";
    public static final String PROD_SKU_ID = "prod_sku_id";
    public static final String PROD_COMBO_ID = "prod_combo_id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String APP_ID = "app_id";
    public static final String ORDER_APPLET_TYPE = "order_applet_type";
    public static final String ORDER_TIME = "order_time";
    public static final String ORDER_SOURCE = "order_source";
    public static final String ORDER_PATH = "order_path";
    public static final String SOURCE_PROD_ID = "source_prod_id";
    public static final String SOURCE_COUPON_CODE = "source_coupon_code";
    public static final String RECEIVE_BUSINESS_ID = "receive_business_id";
    public static final String WX_PAY_RECEIVE_MCH_ID = "wx_pay_receive_mch_id";
    public static final String ORDER_STATUS = "order_status";
    public static final String PAY_TIME = "pay_time";
    public static final String PAY_TYPE = "pay_type";
    public static final String ORDER_AMOUNT = "order_amount";
    public static final String PAY_AMOUNT = "pay_amount";
    public static final String PAY_CARRIAGE = "pay_carriage";
    public static final String DISCOUNT_AMOUNT = "discount_amount";
    public static final String CUST_COUPON_RESTRICT_ID = "cust_coupon_restrict_id ";
    public static final String COUPON_ID = "coupon_id";
    public static final String COUPON_TYPE = "coupon_type";
    public static final String ORDER_DELIVER_TIMEOUT = "order_deliver_timeout";
    public static final String ORDER_AFTER_FLAG = "order_after_flag";
    public static final String ORDER_AFTER_SALE_ID = "order_after_sale_id";
    public static final String ORDER_AFTER_SALE_NUM = "order_after_sale_num";
    public static final String CUSTOMER_OPERATE_DEL = "customer_operate_del";
    public static final String ORDER_LOGISTICS_ID = "order_logistics_id";
    public static final String ORDER_FINISH_TIME = "order_finish_time";
    public static final String IS_ADV_BACK = "is_adv_back";
    public static final String AD_AID = "ad_aid";
    public static final String CLICK_ID = "click_id";
    public static final String ADV_ACCOUNT_ID = "adv_account_id";
    public static final String ADV_CHANNEL_TYPE = "adv_channel_type";
    public static final String BACK_RATIO = "back_ratio";
    public static final String DELIVER_NOTIFY = "deliver_notify";
    public static final String WAIT_PAY_NOTIFY = "wait_pay_notify";
    public static final String IS_RECEIVE_ORDER = "is_receive_order";
    public static final String ORDER_RECEIVE_STATUS = "order_receive_status";
    public static final String USER_BEHAVIOR = "user_behavior";
    public static final String DESCRIPTION = "description";
    public static final String DATA_STATUS = "data_status";
    public static final String FLAG = "flag";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    public static final String PROD_RETURN_PAGE_ID = "prod_return_page_id";
    public static final String MAIN_NUMBER = "main_number";
    public static final String ORDER_DISCOUNT_RECORD = "order_discount_record";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("订单号 规则为年月日时分秒豪秒")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("订单名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    @TableField("business_id")
    private Long businessId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    @TableField("shop_id")
    private Long shopId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    @TableField("prod_id")
    private Long prodId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品SKUID")
    @TableField(value = "prod_sku_id", insertStrategy = FieldStrategy.NOT_NULL)
    private Long prodSkuId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品套餐ID")
    @TableField("prod_combo_id")
    private Long prodComboId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    @TableField("customer_id")
    private Long customerId;
    @ApiModelProperty("关联小程序ID")
    @TableField("app_id")
    private String appId;
    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    @TableField(value = "order_applet_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderAppletType;
    @ApiModelProperty("下单时间 yyyy-MM-dd HH:mm:ss")
    @TableField("order_time")
    private Date orderTime;
    @ApiModelProperty("订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-营销流量")
    @TableField(value = "order_source", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderSource;
    @ApiModelProperty("用户行为 1-主动打开微信小程序 5-点击所投放的广告进入小程序 10-卡包回流优惠券 15-卡包平台券")
    @TableField(value = "user_behavior", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String userBehavior;
    @ApiModelProperty("下单路径")
    @TableField(value = "order_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderPath;
    @ApiModelProperty("订单来源商品_prod_id")
    @TableField("source_prod_id")
    private Long sourceProdId;
    @ApiModelProperty("订单来源优惠券_coupon_code")
    @TableField("source_coupon_code")
    private String sourceCouponCode;
    @ApiModelProperty("分账商户business_id")
    @TableField("receive_business_id")
    private Long receiveBussinessId;
    @ApiModelProperty("分账商户mch_id或者sub_mch_id")
    @TableField("wx_pay_receive_mch_id")
    private String wxPayReceiveMchId;
    @ApiModelProperty("订单状态 1-待付款 10-待发货 20-已发货 30-已完成 99-已关闭")
    @TableField(value = "order_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderStatus;
    @ApiModelProperty("支付时间 yyyy-MM-dd HH:mm:ss")
    @TableField("pay_time")
    private Date payTime;
    @ApiModelProperty("支付方式 1-微信支付 2-货到付款")
    @TableField(value = "pay_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String payType;
    @ApiModelProperty("订单金额")
    @TableField(value = "order_amount", insertStrategy = FieldStrategy.NOT_NULL)
    private BigDecimal orderAmount;
    @ApiModelProperty("支付金额")
    @TableField(value = "pay_amount", insertStrategy = FieldStrategy.NOT_NULL)
    private BigDecimal payAmount;
    @ApiModelProperty("支付运费")
    @TableField(value = "pay_carriage", insertStrategy = FieldStrategy.NOT_NULL)
    private BigDecimal payCarriage;
    @ApiModelProperty("优惠金额")
    @TableField(value = "discount_amount", insertStrategy = FieldStrategy.NOT_NULL)
    private BigDecimal discountAmount;
    @ApiModelProperty("发货超时 0-否 1-是")
    @TableField(value = "order_deliver_timeout", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderDeliverTimeout;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户领用优惠券记录ID ")
    @TableField("cust_coupon_restrict_id ")
    private Long custCouponRestrictId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联优惠券ID ")
    @TableField(value = "coupon_id", insertStrategy = FieldStrategy.NOT_NULL)
    private Long couponId;
    @ApiModelProperty("优惠券类型 1-商品立减劵 2-回流优惠券 3-平台优惠券")
    @TableField(value = "coupon_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponType;
    @ApiModelProperty("售后中 0-否 1-是")
    @TableField(value = "order_after_flag", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderAfterFlag;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("当前售后服务ID")
    @TableField(value = "order_after_sale_id", insertStrategy = FieldStrategy.NOT_NULL)
    private Long orderAfterSaleId;
    @ApiModelProperty("售后次数")
    @TableField(value = "order_after_sale_num", insertStrategy = FieldStrategy.NOT_NULL)
    private Integer orderAfterSaleNum;
    @ApiModelProperty("客户是否删除订单 0-否 1-是")
    @TableField(value = "customer_operate_del", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String customerOperateDel;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单物流ID")
    @TableField(value = "order_logistics_id", insertStrategy = FieldStrategy.NOT_NULL)
    private Long orderLogisticsId;
    @ApiModelProperty("交易完成时间 yyyy-MM-dd HH:mm:ss")
    @TableField("order_finish_time")
    private Date orderFinishTime;
    @ApiModelProperty("广告是否回传 0-否 1-是")
    @TableField(value = "is_adv_back", insertStrategy = FieldStrategy.NOT_NULL)
    private String isAdvBack;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联广告账户ID ")
    @TableField(value = "adv_account_id", insertStrategy = FieldStrategy.NOT_NULL)
    private Long advAccountId;
    @ApiModelProperty("腾讯广告id/腾讯广告计划id")
    @TableField(value = "ad_aid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String adAid;
    @ApiModelProperty("点击id")
    @TableField(value = "click_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clickId;
    @ApiModelProperty("广告渠道类型 1-腾讯广告 2-快手广告 3-序言泽联盟广告 4-腾讯视频号广告 5-美柚广告")
    @TableField(value = "adv_channel_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String advChannelType;
    @ApiModelProperty("回传比率")
    @TableField(value = "back_ratio", insertStrategy = FieldStrategy.NOT_EMPTY)
    private BigDecimal backRatio;
    @ApiModelProperty("开启订单发货通知 0-否 1-是")
    @TableField(value = "deliver_notify", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String deliverNotify;
    @ApiModelProperty("开启订单待付款通知 0-否 1-是")
    @TableField(value = "wait_pay_notify", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String waitPayNotify;
    @ApiModelProperty("是否为分账订单 0-否 1-是")
    @TableField(value = "is_receive_order", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isReceiveOrder;
    @ApiModelProperty("订单分账状态 0-未分账 1-已分账 2-已回退 3-分账失败 4-回退失败")
    @TableField(value = "order_receive_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderReceiveStatus;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
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
//    @Version
    private Date gmtModified;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品返回页ID ")
    @TableField(value = "prod_return_page_id")
    private Long prodReturnPageId;

    @ApiModelProperty("到付应付金额")
    @TableField(value = "delivery_pay_amount")
    private BigDecimal deliveryPayAmount;

    @ApiModelProperty("主单号")
    @TableField(value = "main_number")
    private String mainNumber;

    @ApiModelProperty("订单优惠记录")
    @TableField(value = "order_discount_record")
    public String orderDiscountRecord;

}
