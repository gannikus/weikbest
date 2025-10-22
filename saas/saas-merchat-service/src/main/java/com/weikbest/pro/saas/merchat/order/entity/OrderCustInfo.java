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
 * 客户订单与客户关联拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_cust_info")
@ApiModel(value = "OrderCustInfo对象", description = "客户订单与客户关联拆分表")
public class OrderCustInfo implements Serializable {

    public static final String ID = "id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String CUSTOMER_PROVINCE = "customer_province";
    public static final String CUSTOMER_CITY = "customer_city";
    public static final String CUSTOMER_DISTRICT = "customer_district";
    public static final String CUSTOMER_ADDR = "customer_addr";
    public static final String CUSTOMER_PHONE = "customer_phone";
    public static final String CUSTOMER_DESCRIPTION = "customer_description";
    public static final String TP_TYPE = "tp_type";
    public static final String TP_NAME = "tp_name";
    public static final String TP_PHOTO = "tp_photo";
    public static final String TP_OPENID = "tp_openid";
    public static final String TP_QRCODE = "tp_qrcode";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    @TableField("customer_id")
    private Long customerId;
    @ApiModelProperty("客户姓名")
    @TableField(value = "customer_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String customerName;
    @ApiModelProperty("客户所在地区省、直辖市")
    @TableField(value = "customer_province", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String customerProvince;
    @ApiModelProperty("客户所在地区市")
    @TableField(value = "customer_city", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String customerCity;
    @ApiModelProperty("客户所在地区区、县")
    @TableField(value = "customer_district", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String customerDistrict;
    @ApiModelProperty("详细地址")
    @TableField(value = "customer_addr", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String customerAddr;
    @ApiModelProperty("手机")
    @TableField(value = "customer_phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String customerPhone;
    @ApiModelProperty("客户备注")
    @TableField(value = "customer_description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String customerDescription;
    @ApiModelProperty("第三方平台类型 1-微信")
    @TableField(value = "tp_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tpType;
    @ApiModelProperty("昵称")
    @TableField(value = "tp_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tpName;
    @ApiModelProperty("头像")
    @TableField(value = "tp_photo", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tpPhoto;
    @ApiModelProperty("openid")
    @TableField(value = "tp_openid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tpOpenid;
    @ApiModelProperty("推广二维码")
    @TableField(value = "tp_qrcode", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tpQrcode;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
