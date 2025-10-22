package com.weikbest.pro.saas.sys.system.module.dto;

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
 * 系统用户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserRegisterDTO对象", description = "系统用户表")
public class UserRegisterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名不为空!")
    @ApiModelProperty(value = "用户名", required = true)
    private String name;

    @Pattern(regexp = RegConstant.MOBILE, message = "手机号格式不正确！")
    @NotBlank(message = "手机号不为空!")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @Pattern(regexp = RegConstant.EMAIL, message = "邮箱格式不正确！")
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;
}