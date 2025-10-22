package com.weikbest.pro.saas.merchat.comment.entity;

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
 * 客户评论表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-24
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_ccmm_cust_comment")
@ApiModel(value = "CustComment对象", description = "客户评论表")
public class CustComment implements Serializable {

    public static final String ID = "id";
    public static final String BUSINESS_ID = "business_id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String PROD_SKU_ID = "prod_sku_id";
    public static final String PROD_COMBO_ID = "prod_combo_id";
    public static final String ORDER_ID = "order_id";
    public static final String IS_ANONYMOUS = "is_anonymous";
    public static final String COMMENT_DETAIL = "comment_detail";
    public static final String COMMENT_ORDER = "comment_order";
    public static final String COMMENT_PROD = "comment_prod";
    public static final String COMMENT_SERVER = "comment_server";
    public static final String COMMENT_LOGISTICS = "comment_logistics";
    public static final String COMMENT_TYPE = "comment_type";
    public static final String COMMENT_FLAG = "comment_flag";
    public static final String IS_BUSINESS_COMM = "is_business_comm";
    public static final String COMM_TIME = "comm_time";
    public static final String PREV_COMM_ID = "prev_comm_id";
    public static final String AUDIT_TYPE = "audit_type";
    public static final String DATA_STATUS = "data_status";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    @TableField("business_id")
    private Long businessId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("客户ID ")
    @TableField("customer_id")
    private Long customerId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品SKUID ")
    @TableField("prod_sku_id")
    private Long prodSkuId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品套餐ID ")
    @TableField("prod_combo_id")
    private Long prodComboId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联订单ID")
    @TableField("order_id")
    private Long orderId;
    @ApiModelProperty("是否匿名 0-否 1-是")
    @TableField(value = "is_anonymous", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isAnonymous;
    @ApiModelProperty("客户评价")
    @TableField(value = "comment_detail", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String commentDetail;
    @ApiModelProperty("评价订单 1-5星")
    @TableField("comment_order")
    private Byte commentOrder;
    @ApiModelProperty("评价订单 1-5星")
    @TableField("comment_prod")
    private Byte commentProd;
    @ApiModelProperty("评价服务 1-5星")
    @TableField("comment_server")
    private Byte commentServer;
    @ApiModelProperty("评价物流 1-5星")
    @TableField("comment_logistics")
    private Byte commentLogistics;
    @ApiModelProperty("评论类型 0-好评 1-中评 2-差评")
    @TableField(value = "comment_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String commentType;
    @ApiModelProperty("评论标记 0-正常 1-商家回复 2-客户回复  3-追评")
    @TableField(value = "comment_flag", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String commentFlag;
    @ApiModelProperty("商家是否回复客户 0-否 1-是")
    @TableField(value = "is_business_comm", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isBusinessComm;
    @ApiModelProperty("评价时间 yyyy-MM-dd HH:mm:ss")
    @TableField("comm_time")
    private Date commTime;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("上一条评论ID")
    @TableField("prev_comm_id")
    private Long prevCommId;
    @ApiModelProperty("审核类型 0-待审核 1-审核不通过 2-审核通过")
    @TableField(value = "audit_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String auditType;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
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
