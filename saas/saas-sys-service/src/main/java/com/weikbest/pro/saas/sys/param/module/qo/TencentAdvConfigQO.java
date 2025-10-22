package com.weikbest.pro.saas.sys.param.module.qo;

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
@ApiModel(value = "TencentAdvConfigQO对象", description = "腾讯广告第三方应用配置表")
public class TencentAdvConfigQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("应用id")
    private Long clientId;

    @ApiModelProperty("应用类型1私有应用、2第三方应用")
    private String clientType;

    @ApiModelProperty("应用图标路径")
    private String clientIconPath;

    @ApiModelProperty("应用secret")
    private String clientSecret;

    @ApiModelProperty("应用名称")
    private String clientName;

    @ApiModelProperty("应用级别")
    private String clientLevel;

    @ApiModelProperty("应用介绍")
    private String clientIntroduce;

    @ApiModelProperty("回调地址")
    private String clientTokenUrl;

    @ApiModelProperty("应用accessToken")
    private String clientAccessToken;

    @ApiModelProperty("应用refreshToken")
    private String clientRefreshToken;


}