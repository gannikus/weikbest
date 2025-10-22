package com.weikbest.pro.saas.sys.param.module.qo;

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
@ApiModel(value = "TencentAdvUsersourceQO对象", description = "腾讯广告数据上报用户行为数据源表")
public class TencentAdvUsersourceQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("应用id")
    private Long clientId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id")
    private Long accountId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("用户行为源 id")
    private Long userActionSetId;

    @ApiModelProperty("用户行为源名称")
    private String name;

    @ApiModelProperty("用户行为源类型WEB")
    private String type;

    @ApiModelProperty("是否开启转化归因，true 表示开启，false 表示不开启，不传则默认开启 0-否 1-是")
    private String enableConversionClaim;

    @ApiModelProperty("微信 AppID")
    private String wechatAppId;

    @ApiModelProperty("用户行为源描述")
    private String description;


}