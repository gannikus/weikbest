package com.weikbest.pro.saas.sys.system.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "UserLoginVO对象", description = "系统用户登录表")
public class UserLoginVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("登录类型 0-工号 1-手机号")
    private String loginType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("系统用户ID")
    private Long userId;


}