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
import java.util.Date;

/**
 * <p>
 * 系统用户登录记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserLoginRecordDTO对象", description = "系统用户登录记录表")
public class UserLoginRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "系统用户ID不为空!")
    @ApiModelProperty(value = "系统用户ID", required = true)
    private Long userId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "系统用户ID不为空!")
    @ApiModelProperty("关联用户ID")
    private Long relateId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "系统用户登录ID不为空!")
    @ApiModelProperty(value = "系统用户登录ID", required = true)
    private Long userLoginId;

    @NotBlank(message = "本次登录IP不为空!")
    @ApiModelProperty(value = "本次登录IP", required = true)
    private String loginIp;

    @ApiModelProperty(value = "本次登录时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date loginTime;

    @ApiModelProperty(value = "本次登出时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date logoutTime;

    @NotBlank(message = "在线状态 0-登出 1-在线不为空!")
    @ApiModelProperty(value = "在线状态 0-登出 1-在线", required = true)
    private String online;


}