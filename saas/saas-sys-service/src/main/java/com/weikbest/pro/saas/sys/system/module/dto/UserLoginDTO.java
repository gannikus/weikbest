package com.weikbest.pro.saas.sys.system.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 系统用户登录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserLoginDTO对象", description = "系统用户登录表")
public class UserLoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "登录名不为空!")
    @ApiModelProperty(value = "登录名", required = true)
    private String loginName;

    @NotBlank(message = "密码不为空!")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotBlank(message = "登录类型 0-工号 1-手机号不为空!")
    @ApiModelProperty(value = "登录类型 0-工号 1-手机号", required = true)
    private String loginType;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "系统用户ID不为空!")
    @ApiModelProperty(value = "系统用户ID", required = true)
    private Long userId;


}