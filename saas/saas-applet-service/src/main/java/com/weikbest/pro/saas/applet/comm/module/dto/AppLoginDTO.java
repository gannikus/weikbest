package com.weikbest.pro.saas.applet.comm.module.dto;

import com.weikbest.pro.saas.common.constant.RegConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/16
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppLoginDTO对象", description = "小程序登录实体")
public class AppLoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户的openid不为空!")
    @ApiModelProperty(value = "用户的openid", required = true)
    private String openid;

    @NotBlank(message = "当前用户的unionid不为空!")
    @ApiModelProperty(value = "当前用户的unionid", required = true)
    private String unionid;

    @NotBlank(message = "当前用户的appId不为空!")
    @ApiModelProperty(value = "当前用户的appId", required = true)
    private String appId;


    @ApiModelProperty(value = "当前用户的手机号")
    private String phone;

    @ApiModelProperty(value = "客户姓名")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别 1-男性 2-女性 9-未知")
    private String sex;

    @Pattern(regexp = RegConstant.EMAIL, message = "邮箱格式不正确！")
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty(value = "客户所在国家")
    private String custCountry;

    @ApiModelProperty(value = "客户所在地区省")
    private String custProvince;

    @ApiModelProperty(value = "客户所在地区市")
    private String custCity;

    @ApiModelProperty(value = "客户所在地区区")
    private String custDistrict;

    @ApiModelProperty(value = "详细地址")
    private String custAddr;
}

