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
 * 系统用户关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserRelateVO对象", description = "系统用户关联表")
public class UserRelateVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("系统用户ID")
    private Long userId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联其他端用户ID")
    private Long relateId;

    @ApiModelProperty("关联类型 0-系统平台用户 1-商家端用户 2-小程序端用户")
    private String relateType;


}