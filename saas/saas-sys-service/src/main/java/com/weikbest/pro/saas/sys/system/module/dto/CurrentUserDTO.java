package com.weikbest.pro.saas.sys.system.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wisdomelon
 * @date 2020/6/24
 * @project mystery-boxes
 * @jdk 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "CurrentUserDTO", description = "CurrentUserDTO")
public class CurrentUserDTO implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "当前登录用户的系统关联ID")
    private Long currentRelateId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "当前登录用户的系统用户ID")
    private Long currentUserId;

    @ApiModelProperty(value = "当前登录用户登录名")
    private String currentLoginName;

    public static CurrentUserDTO defaultCurrentUserDTO() {
        return new CurrentUserDTO(WeikbestConstant.ZERO_LONG, WeikbestConstant.ZERO_LONG, "");
    }

}
