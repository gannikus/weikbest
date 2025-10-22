package com.weikbest.pro.saas.sys.system.module.dto;

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
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserDTO对象", description = "系统用户表")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @NotBlank(message = "用户名不为空!")
    @ApiModelProperty(value = "用户名", required = true)
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;

    @Pattern(regexp = RegConstant.MOBILE, message = "手机号格式不正确！")
    @NotBlank(message = "手机号不为空!")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @Pattern(regexp = RegConstant.EMAIL, message = "邮箱格式不正确！")
    @ApiModelProperty("邮箱")
    private String email;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "部门ID")
    private Long orgId;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}