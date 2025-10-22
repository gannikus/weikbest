package com.weikbest.pro.saas.sys.system.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 系统用户控件关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-20
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserActivexDTO对象", description = "系统用户控件关联表")
public class UserActivexDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "头像不为空!")
    @ApiModelProperty(value = "头像", required = true)
    private String avatar;

    @NotBlank(message = "工号不为空!")
    @ApiModelProperty(value = "工号", required = true)
    private String number;

    @NotBlank(message = "用户名不为空!")
    @ApiModelProperty(value = "用户名", required = true)
    private String name;

    @NotBlank(message = "手机号不为空!")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @NotBlank(message = "邮箱不为空!")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    @NotBlank(message = "控件数据状态 0-已删除 1-可用不为空!")
    @ApiModelProperty(value = "控件数据状态 0-已删除 1-可用", required = true)
    private String activexDataStatus;


}