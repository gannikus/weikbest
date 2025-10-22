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
 * 腾讯广告主授权腾讯广告第三方应用表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "TencentAdvScopeConfigDTO对象", description = "腾讯广告主授权腾讯广告第三方应用表")
public class TencentAdvScopeConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "应用id不为空!")
    @ApiModelProperty(value = "应用id", required = true)
    private Long clientId;

    @NotBlank(message = "授权code值有效期5分钟不为空!")
    @ApiModelProperty(value = "授权code值有效期5分钟", required = true)
    private String authorizationCode;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id不为空!")
    @ApiModelProperty(value = "推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id", required = true)
    private Long accountId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "授权的推广帐号对应的 QQ 号不为空!")
    @ApiModelProperty(value = "授权的推广帐号对应的 QQ 号", required = true)
    private Long accountUin;

    @NotBlank(message = "权限列表，若为空，则表示拥有所属应用的所有权限不为空!")
    @ApiModelProperty(value = "权限列表，若为空，则表示拥有所属应用的所有权限", required = true)
    private String scopeList;

    @NotBlank(message = "授权的推广帐号对应的微信帐号 id不为空!")
    @ApiModelProperty(value = "授权的推广帐号对应的微信帐号 id", required = true)
    private String wechatAccountId;

    @NotBlank(message = "授权账号身份类型，授权账号类型广告主,代理商,T1 账户,商务管家账户不为空!")
    @ApiModelProperty(value = "授权账号身份类型，授权账号类型广告主,代理商,T1 账户,商务管家账户", required = true)
    private String accountRoleType;

    @NotBlank(message = "账号类型不为空!")
    @ApiModelProperty(value = "账号类型", required = true)
    private String accountType;

    @NotBlank(message = "角色不为空!")
    @ApiModelProperty(value = "角色", required = true)
    private String roleType;

    @NotBlank(message = "应用accessToken不为空!")
    @ApiModelProperty(value = "应用accessToken", required = true)
    private String accessToken;

    @NotBlank(message = "应用 refresh token，当 grant_type=refresh_token 时不返回不为空!")
    @ApiModelProperty(value = "应用 refresh token，当 grant_type=refresh_token 时不返回", required = true)
    private String refreshToken;

    @ApiModelProperty(value = "access_token 过期时间，单位（秒）", required = true)
    private Integer accessTokenExpiresIn;

    @ApiModelProperty(value = "refresh_token 过期时间，单位（秒），当 grant_type=refresh_token 时不返回", required = true)
    private Integer refreshTokenExpiresIn;


}