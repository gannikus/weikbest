package com.weikbest.pro.saas.merchat.shop.entity;

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
 * 商户店铺表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-16
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_shop")
@ApiModel(value = "Shop对象", description = "商户店铺表")
public class Shop implements Serializable {

    public static final String ID = "id";
    public static final String BUSINESS_ID = "business_id";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String LOGO = "logo";
    public static final String IS_BRAND = "is_brand";
    public static final String CONTACT = "contact";
    public static final String COMPANY_NAME = "company_name";
    public static final String COMPANY_ADDRESS = "company_address";
    public static final String COMPANY_STAFF = "company_staff";
    public static final String BUSINESS_LISTENCE = "business_listence";
    public static final String SHOP_TYPE = "shop_type";
    public static final String TRADE_TYPE = "trade_type";
    public static final String SERVICE_INTRO = "service_intro";
    public static final String DESCRIPTION = "description";
    public static final String DATA_STATUS = "data_status";
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
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    @TableField("business_id")
    private Long businessId;
    @ApiModelProperty("店铺编码")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("店铺名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("店铺logo")
    @TableField(value = "logo", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String logo;
    @ApiModelProperty("是否品牌店铺 0-否 1-是")
    @TableField(value = "is_brand", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isBrand;
    @ApiModelProperty("联系人电话")
    @TableField(value = "contact", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String contact;
    @ApiModelProperty("企业名称（工商注册全名）")
    @TableField(value = "company_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String companyName;
    @ApiModelProperty("企业地址")
    @TableField(value = "company_address", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String companyAddress;
    @ApiModelProperty("企业人数")
    @TableField("company_staff")
    private Integer companyStaff;
    @ApiModelProperty("营业执照")
    @TableField(value = "business_listence", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String businessListence;
    @ApiModelProperty("店铺类型 0-非自营店铺 1-自营店铺")
    @TableField(value = "shop_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String shopType;
    @ApiModelProperty("所属行业")
    @TableField(value = "trade_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tradeType;
    @ApiModelProperty("服务说明")
    @TableField(value = "service_intro", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String serviceIntro;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
    @ApiModelProperty("逻辑删除 0-否 1-是")
    @TableField(value = "flag", insertStrategy = FieldStrategy.NOT_EMPTY)
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

    @ApiModelProperty("是否能控制广告回传开关; 0-关闭，1-开启")
    @TableField(value = "is_control_ad_callback")
    private String isControlAdCallback;

    @ApiModelProperty("手动回传开关； 0-关闭，1-开启")
    @TableField(value = "switch_manual_ad_callback")
    private String switchManualAdCallback;
}
