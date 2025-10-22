package com.weikbest.pro.saas.sys.param.module.dto;

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
 * 腾讯广告数据上报用户行为数据源表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "TencentAdvUsersourceDTO对象", description = "腾讯广告数据上报用户行为数据源表")
public class TencentAdvUsersourceDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "应用id不为空!")
    @ApiModelProperty(value = "应用id", required = true)
    private Long clientId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id不为空!")
    @ApiModelProperty(value = "推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id", required = true)
    private Long accountId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "用户行为源 id不为空!")
    @ApiModelProperty(value = "用户行为源 id", required = true)
    private Long userActionSetId;

    @NotBlank(message = "用户行为源名称不为空!")
    @ApiModelProperty(value = "用户行为源名称", required = true)
    private String name;

    @NotBlank(message = "用户行为源类型WEB不为空!")
    @ApiModelProperty(value = "用户行为源类型WEB", required = true)
    private String type;

    @NotBlank(message = "是否开启转化归因，true 表示开启，false 表示不开启，不传则默认开启 0-否 1-是不为空!")
    @ApiModelProperty(value = "是否开启转化归因，true 表示开启，false 表示不开启，不传则默认开启 0-否 1-是", required = true)
    private String enableConversionClaim;

    @NotBlank(message = "微信 AppID不为空!")
    @ApiModelProperty(value = "微信 AppID", required = true)
    private String wechatAppId;

    @ApiModelProperty("用户行为源描述")
    private String description;


}