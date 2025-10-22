package com.weikbest.pro.saas.sys.param.module.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * 腾讯广告第三方应用配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "TencentAdvConfigDTO对象", description = "腾讯广告第三方应用配置表")
public class TencentAdvConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "应用id不为空!")
    @ApiModelProperty(value = "应用id", required = true)
    private Long clientId;

    @NotBlank(message = "应用类型1私有应用、2第三方应用不为空!")
    @ApiModelProperty(value = "应用类型1私有应用、2第三方应用", required = true)
    private String clientType;

    @NotBlank(message = "应用图标路径不为空!")
    @ApiModelProperty(value = "应用图标路径", required = true)
    private String clientIconPath;

    @NotBlank(message = "应用secret不为空!")
    @ApiModelProperty(value = "应用secret", required = true)
    private String clientSecret;

    @NotBlank(message = "应用名称不为空!")
    @ApiModelProperty(value = "应用名称", required = true)
    private String clientName;

    @NotBlank(message = "应用级别不为空!")
    @ApiModelProperty(value = "应用级别", required = true)
    private String clientLevel;

    @NotBlank(message = "应用介绍不为空!")
    @ApiModelProperty(value = "应用介绍", required = true)
    private String clientIntroduce;

    @NotBlank(message = "回调地址不为空!")
    @ApiModelProperty(value = "回调地址", required = true)
    private String clientTokenUrl;

    @NotBlank(message = "应用accessToken不为空!")
    @ApiModelProperty(value = "应用accessToken", required = true)
    private String clientAccessToken;

    @NotBlank(message = "应用refreshToken不为空!")
    @ApiModelProperty(value = "应用refreshToken", required = true)
    private String clientRefreshToken;


}