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
import java.util.Date;

/**
 * <p>
 * 订单批量发货记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-26
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_batch_deliver")
@ApiModel(value = "OrderBatchDeliver对象", description = "订单批量发货记录表")
public class OrderBatchDeliver implements Serializable {

    public static final String ID = "id";
    public static final String BUSINESS_ID = "business_id";
    public static final String SHOP_ID = "shop_id";
    public static final String DELIVER_TYPE = "deliver_type";
    public static final String RECORD_TIME = "record_time";
    public static final String OPERATOR = "operator";
    public static final String OPERATOR_TYPE = "operator_type";
    public static final String OPERATE_EXCEL_NAME = "operate_excel_name";
    public static final String OPERATE_EXCEL_URL = "operate_excel_url";
    public static final String TOTAL_NUM = "total_num";
    public static final String SUCCESS_NUM = "success_num";
    public static final String ERROR_NUM = "error_num";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    @TableField("business_id")
    private Long businessId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    @TableField("shop_id")
    private Long shopId;
    @ApiModelProperty("操作类型 1-批量发货")
    @TableField(value = "deliver_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String deliverType;
    @ApiModelProperty("操作时间 yyyy-MM-dd HH:mm:ss")
    @TableField("record_time")
    private Date recordTime;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("操作人")
    @TableField("operator")
    private Long operator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("操作人类型 0-平台用户 1-商家端用户 2-App端用户")
    @TableField("operator_type")
    private String operatorType;
    @ApiModelProperty("excel导入文件名称")
    @TableField(value = "operate_excel_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String operateExcelName;
    @ApiModelProperty("excel导入文件")
    @TableField(value = "operate_excel_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String operateExcelUrl;
    @ApiModelProperty("操作订单数")
    @TableField("total_num")
    private Integer totalNum;
    @ApiModelProperty("操作成功数")
    @TableField("success_num")
    private Integer successNum;
    @ApiModelProperty("操作失败数")
    @TableField("error_num")
    private Integer errorNum;
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

    @ApiModelProperty("状态，1-上传中，2-已上传")
    @TableField(value = "status")
    private String status;
}
