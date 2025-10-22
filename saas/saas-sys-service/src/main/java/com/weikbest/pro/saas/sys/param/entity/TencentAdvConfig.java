package com.weikbest.pro.saas.sys.param.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("t_sys_tencent_adv_config")
@ApiModel(value = "TencentAdvConfig对象", description = "腾讯广告第三方应用配置表")
public class TencentAdvConfig implements Serializable {

    public static final String ID = "id";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_TYPE = "client_type";
    public static final String CLIENT_ICON_PATH = "client_icon_path";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String CLIENT_NAME = "client_name";
    public static final String CLIENT_LEVEL = "client_level";
    public static final String CLIENT_INTRODUCE = "client_introduce";
    public static final String CLIENT_TOKEN_URL = "client_token_url";
    public static final String CLIENT_ACCESS_TOKEN = "client_access_token";
    public static final String CLIENT_REFRESH_TOKEN = "client_refresh_token";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("应用id")
    @TableField(value = "client_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Long clientId;
    @ApiModelProperty("应用类型1私有应用、2第三方应用")
    @TableField(value = "client_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clientType;
    @ApiModelProperty("应用图标路径")
    @TableField(value = "client_icon_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clientIconPath;
    @ApiModelProperty("应用secret")
    @TableField(value = "client_secret", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clientSecret;
    @ApiModelProperty("应用名称")
    @TableField(value = "client_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clientName;
    @ApiModelProperty("应用级别")
    @TableField(value = "client_level", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clientLevel;
    @ApiModelProperty("应用介绍")
    @TableField(value = "client_introduce", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clientIntroduce;
    @ApiModelProperty("回调地址")
    @TableField(value = "client_token_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clientTokenUrl;
    @ApiModelProperty("应用accessToken")
    @TableField(value = "client_access_token", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clientAccessToken;
    @ApiModelProperty("应用refreshToken")
    @TableField(value = "client_refresh_token", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clientRefreshToken;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
