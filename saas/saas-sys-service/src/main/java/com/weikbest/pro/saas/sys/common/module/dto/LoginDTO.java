package com.weikbest.pro.saas.sys.common.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/12
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "LoginDTO对象", description = "用户登录实体")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "登录名不为空!")
    @ApiModelProperty(value = "登录名", required = true)
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank(message = "用户类型不为空!")
    @ApiModelProperty(value = "用户类型 0-平台用户 1-商家端用户", required = true)
    private String relateType;

    @NotBlank(message = "验证码不为空!")
    @ApiModelProperty(value = "验证码", required = true)
    private String verify;

}

