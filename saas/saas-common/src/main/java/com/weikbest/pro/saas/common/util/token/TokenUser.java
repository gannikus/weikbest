package com.weikbest.pro.saas.common.util.token;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wisdomelon
 * @date 2020/7/2 0002
 * @project saas
 * @jdk 1.8
 * Token中的保存的用户信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TokenUser implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "系统用户关联其他端用户ID")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "系统用户ID")
    private Long userId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "系统用户登录ID")
    private Long userLoginId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "系统用户登录记录ID")
    private Long userLoginRecordId;

    @ApiModelProperty(value = "系统用户关联其他端用户类型")
    private String relateType;

    @ApiModelProperty(value = "登录名")
    private String loginNameOrPhone;

    @ApiModelProperty(value = "登录用户姓名")
    private String userName;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "过期时长（秒）")
    private Long expiration;

    @ApiModelProperty(value = "登录IP")
    private String loginIp;

    @ApiModelProperty(value = "openid")
    private String openId;

    @ApiModelProperty(value = "unionid")
    private String unionId;

    /**
     * 没有Token时候的默认数据
     *
     * @return
     */
    public static TokenUser defaultTokenUser() {
        TokenUser tokenUser = new TokenUser();
        tokenUser.setUserId(WeikbestConstant.ZERO_LONG);
        tokenUser.setId(WeikbestConstant.ZERO_LONG);
        tokenUser.setRelateType("0");
        return tokenUser;
    }
}
