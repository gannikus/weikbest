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
@ApiModel(value = "TencentAdvScopeConfigQO对象", description = "腾讯广告主授权腾讯广告第三方应用表")
public class TencentAdvScopeConfigQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("应用id")
    private Long clientId;

    @ApiModelProperty("授权code值有效期5分钟")
    private String authorizationCode;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id")
    private Long accountId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("授权的推广帐号对应的 QQ 号")
    private Long accountUin;

    @ApiModelProperty("权限列表，若为空，则表示拥有所属应用的所有权限")
    private String scopeList;

    @ApiModelProperty("授权的推广帐号对应的微信帐号 id")
    private String wechatAccountId;

    @ApiModelProperty("授权账号身份类型，授权账号类型广告主,代理商,T1 账户,商务管家账户")
    private String accountRoleType;

    @ApiModelProperty("账号类型")
    private String accountType;

    @ApiModelProperty("角色")
    private String roleType;

    @ApiModelProperty("应用accessToken")
    private String accessToken;

    @ApiModelProperty("应用 refresh token，当 grant_type=refresh_token 时不返回")
    private String refreshToken;

    @ApiModelProperty("access_token 过期时间，单位（秒）")
    private Integer accessTokenExpiresIn;

    @ApiModelProperty("refresh_token 过期时间，单位（秒），当 grant_type=refresh_token 时不返回")
    private Integer refreshTokenExpiresIn;


}