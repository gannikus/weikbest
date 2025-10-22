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
@ApiModel(value = "UserLoginRecordVO对象", description = "系统用户登录记录表")
public class UserLoginRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("系统用户ID")
    private Long userId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联用户ID")
    private Long relateId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("系统用户登录ID")
    private Long userLoginId;

    @ApiModelProperty("本次登录IP")
    private String loginIp;

    @ApiModelProperty("本次登录时间 yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    @ApiModelProperty("本次登出时间 yyyy-MM-dd HH:mm:ss")
    private Date logoutTime;

    @ApiModelProperty("在线状态 0-登出 1-在线")
    private String online;


}