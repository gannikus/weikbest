package com.weikbest.pro.saas.merchat.cust.entity;

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
 * 客户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-16
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_ccmm_customer")
@ApiModel(value = "Customer对象", description = "客户表")
public class Customer implements Serializable {

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String AVATAR = "avatar";
    public static final String PHONE = "phone";
    public static final String USER_UNIQUE = "user_unique";
    public static final String SEX = "sex";
    public static final String EMAIL = "email";
    public static final String CUST_COUNTRY = "cust_country";
    public static final String CUST_PROVINCE = "cust_province";
    public static final String CUST_CITY = "cust_city";
    public static final String CUST_DISTRICT = "cust_district";
    public static final String CUST_ADDR = "cust_addr";
    public static final String CUST_THIRD_TYPE = "cust_third_type";
    public static final String APP_ID = "app_id";
    public static final String WX_OPENID = "wx_openid";
    public static final String WX_UNIONID = "wx_unionid";
    public static final String WX_QRCODE = "wx_qrcode";
    public static final String DATA_STATUS = "data_status";
    public static final String DESCRIPTION = "description";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("客户编码")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("客户姓名")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("头像")
    @TableField(value = "avatar", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String avatar;
    @ApiModelProperty("手机号")
    @TableField(value = "phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String phone;
    @ApiModelProperty("平台用户唯一索引")
    @TableField(value = "user_unique", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String userUnique;
    @ApiModelProperty("性别 1-男性 2-女性 9-未知")
    @TableField(value = "sex", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sex;
    @ApiModelProperty("邮箱")
    @TableField(value = "email", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String email;
    @ApiModelProperty("客户所在国家")
    @TableField(value = "cust_country", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String custCountry;
    @ApiModelProperty("客户所在地区省")
    @TableField(value = "cust_province", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String custProvince;
    @ApiModelProperty("客户所在地区市")
    @TableField(value = "cust_city", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String custCity;
    @ApiModelProperty("客户所在地区区")
    @TableField(value = "cust_district", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String custDistrict;
    @ApiModelProperty("详细地址")
    @TableField(value = "cust_addr", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String custAddr;
    @ApiModelProperty("第三方平台类型 1-微信")
    @TableField(value = "cust_third_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String custThirdType;
    @ApiModelProperty("关联小程序ID")
    @TableField(value = "app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appId;
    @ApiModelProperty("openid")
    @TableField(value = "wx_openid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxOpenid;
    @ApiModelProperty("微信开放平台")
    @TableField(value = "wx_unionid", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxUnionid;
    @ApiModelProperty("推广二维码")
    @TableField(value = "wx_qrcode", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxQrcode;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
    @ApiModelProperty("备注")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
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
