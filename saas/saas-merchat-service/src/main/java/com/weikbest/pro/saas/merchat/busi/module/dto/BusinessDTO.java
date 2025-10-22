package com.weikbest.pro.saas.merchat.busi.module.dto;

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
 * <p>
 * 商户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "BusinessDTO对象", description = "商户表")
public class BusinessDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "商户名称不为空!")
    @ApiModelProperty(value = "商户名称", required = true)
    private String name;

    @NotBlank(message = "商户类别不为空!")
    @ApiModelProperty(value = "商户类别 1-普通商户 2-品牌商户 3-特约商户", required = true)
    private String businessType;

    @ApiModelProperty("是否平台超级商户 0-否 1-是")
    private String isSuper;

    @Pattern(regexp = RegConstant.MOBILE, message = "手机号格式不正确！")
    @NotBlank(message = "手机号不为空!")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @NotBlank(message = "姓名不为空!")
    @ApiModelProperty(value = "姓名", required = true)
    private String userName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @Pattern(regexp = RegConstant.EMAIL, message = "邮箱格式不正确！")
    @ApiModelProperty("邮箱")
    private String email;

}