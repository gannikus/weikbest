package com.weikbest.pro.saas.merchat.cust.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value = "CustomerQO对象", description = "客户表")
public class CustomerQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("客户编码")
    private String number;

    @ApiModelProperty("客户姓名")
    private String name;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("平台用户唯一索引")
    private String userUnique;

    @ApiModelProperty("性别 1-男性 2-女性 9-未知")
    private String sex;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("客户所在国家")
    private String custCountry;

    @ApiModelProperty("客户所在地区省")
    private String custProvince;

    @ApiModelProperty("客户所在地区市")
    private String custCity;

    @ApiModelProperty("客户所在地区区")
    private String custDistrict;

    @ApiModelProperty("详细地址")
    private String custAddr;

    @ApiModelProperty("第三方平台类型 1-微信")
    private String custThirdType;

    @ApiModelProperty("关联小程序ID")
    private String appId;

    @ApiModelProperty("openid")
    private String wxOpenid;

    @ApiModelProperty("微信开放平台")
    private String wxUnionid;

    @ApiModelProperty("推广二维码")
    private String wxQrcode;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @ApiModelProperty("备注")
    private String description;


}