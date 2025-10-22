package com.weikbest.pro.saas.merchat.complaint.entity;

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
import java.util.Date;

/**
 * <p>
 * 订单投诉处理记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-05
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_complaint_record")
@ApiModel(value = "OrderComplaintRecord对象", description = "订单投诉处理记录表")
public class OrderComplaintRecord implements Serializable {

    public static final String HISTORY_ID = "history_id";
    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_AFTER_SALE_ID = "order_after_sale_id";
    public static final String APP_ID = "app_id";
    public static final String MCH_ID = "mch_id";
    public static final String ORDER_APPLET_TYPE = "order_applet_type";
    public static final String PROD_ID = "prod_id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String COMPLAINT_TYPE = "complaint_type";
    public static final String COMPLAINT_REASON = "complaint_reason";
    public static final String COMPLAINT_BUSI_STATUS = "complaint_busi_status";
    public static final String COMPLAINT_CUST_STATUS = "complaint_cust_status";
    public static final String COMPLAINT_PLATFORM_STATUS = "complaint_platform_status";
    public static final String COMPLAINT_STATUS = "complaint_status";
    public static final String CUSTOMER_PHONE = "customer_phone";
    public static final String FEEDBACK_REASON = "feedback_reason";
    public static final String COMPLAINT_CONTENT = "complaint_content";
    public static final String APPLY_TIME = "apply_time";
    public static final String BUSI_OPERATE_TIME = "busi_operate_time";
    public static final String CUST_OPERATE_TIME = "cust_operate_time";
    public static final String FINISH_TIME = "finish_time";
    public static final String COMPLAINT_OBJECT = "complaint_object";
    public static final String SHOW_IMG = "show_img";
    public static final String CHANGER_USER_TYPE = "changer_user_type";
    public static final String CHANGER_USER_ID = "changer_user_id";
    public static final String CHANGE_TIME = "change_time";
    public static final String DESCRIPTION = "description";
    public static final String FLAG = "flag";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "history_id", type = IdType.ASSIGN_ID)
    private Long historyId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单投诉ID")
    @TableField("id")
    private Long id;
    @ApiModelProperty("投诉编号 规则为年月日时分秒豪秒")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    @TableField("order_id")
    private Long orderId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单售后ID")
    @TableField("order_after_sale_id")
    private Long orderAfterSaleId;
    @ApiModelProperty("关联小程序ID")
    @TableField(value = "app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appId;
    @ApiModelProperty("关联微信支付商户号ID")
    @TableField(value = "mch_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String mchId;
    @ApiModelProperty("下单小程序类型来源 1-微信小程序")
    @TableField(value = "order_applet_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String orderAppletType;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID")
    @TableField("prod_id")
    private Long prodId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    @TableField("customer_id")
    private Long customerId;
    @ApiModelProperty("投诉类型 1-微信支付投诉 2-店铺投诉")
    @TableField(value = "complaint_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String complaintType;
    @ApiModelProperty("投诉原因 1-发货问题 2-商品问题 3-客服问题 4-其他问题")
    @TableField(value = "complaint_reason", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String complaintReason;
    @ApiModelProperty("投诉状态（商家处理）0-未处理 1-处理中 2-已处理")
    @TableField(value = "complaint_busi_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String complaintBusiStatus;
    @ApiModelProperty("投诉状态（用户认可）0-不认可 1-认可")
    @TableField(value = "complaint_cust_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String complaintCustStatus;
    @ApiModelProperty("投诉状态（平台介入）0-未介入 1-已介入")
    @TableField(value = "complaint_platform_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String complaintPlatformStatus;
    @ApiModelProperty("投诉状态1-发起投诉 100-商家同意和解 101-商家不同意和解 109-商家处理超时 200-用户认可和解 201-用户不认可和解 209-用户撤销投诉")
    @TableField(value = "complaint_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String complaintStatus;
    @ApiModelProperty("客户联系电话")
    @TableField(value = "customer_phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String customerPhone;
    @ApiModelProperty("反馈原因")
    @TableField(value = "feedback_reason", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String feedbackReason;
    @ApiModelProperty("沟通内容")
    @TableField(value = "complaint_content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String complaintContent;
    @ApiModelProperty("申请时间 yyyy-MM-dd HH:mm:ss")
    @TableField("apply_time")
    private Date applyTime;
    @ApiModelProperty("商家处理时间 yyyy-MM-dd HH:mm:ss")
    @TableField("busi_operate_time")
    private Date busiOperateTime;
    @ApiModelProperty("客户处理时间 yyyy-MM-dd HH:mm:ss")
    @TableField("cust_operate_time")
    private Date custOperateTime;
    @ApiModelProperty("完成时间 yyyy-MM-dd HH:mm:ss")
    @TableField("finish_time")
    private Date finishTime;
    @ApiModelProperty("投诉对象")
    @TableField(value = "complaint_object", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String complaintObject;
    @ApiModelProperty("商品展示图（缩略图）")
    @TableField(value = "show_img", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String showImg;
    @ApiModelProperty("变更人类型 0-平台用户 1-商家端用户 2-App端用户")
    @TableField(value = "changer_user_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String changerUserType;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("变更人ID")
    @TableField("changer_user_id")
    private Long changerUserId;
    @ApiModelProperty("变更时间 yyyy-MM-dd HH:mm:ss")
    @TableField("change_time")
    private Date changeTime;
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
}
