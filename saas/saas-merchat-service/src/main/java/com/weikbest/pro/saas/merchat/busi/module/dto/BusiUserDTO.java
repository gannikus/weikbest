package com.weikbest.pro.saas.merchat.busi.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.common.constant.RegConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 商户账户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "BusiUserDTO对象", description = "商户账户表")
public class BusiUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商户ID不为空!")
    @ApiModelProperty(value = "商户ID", required = true)
    private Long businessId;

    @Pattern(regexp = RegConstant.MOBILE, message = "手机号格式不正确！")
    @NotBlank(message = "手机号不为空!")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @NotBlank(message = "姓名不为空!")
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @NotBlank(message = "是否主账号不为空!")
    @ApiModelProperty(value = "是否主账号 0-否 1-是", required = true)
    private String isMainUser;

    @Pattern(regexp = RegConstant.EMAIL, message = "邮箱格式不正确！")
    @ApiModelProperty("邮箱")
    private String email;

}