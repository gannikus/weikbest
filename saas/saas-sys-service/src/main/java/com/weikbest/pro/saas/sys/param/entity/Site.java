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
 * 系统站点设置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-20
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_site")
@ApiModel(value = "Site对象", description = "系统站点设置表")
public class Site implements Serializable {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ICON_URL = "icon_url";
    public static final String MERCHAT_LOGO_URL = "merchat_logo_url";
    public static final String PLATFORM_LOGO_URL = "platform_logo_url";
    public static final String WX_GZH_QRCODE_URL = "wx_gzh_qrcode_url";
    public static final String WX_GZH_APP_ID = "wx_gzh_app_id";
    public static final String WX_BUSINESS = "wx_business";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("商城名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("商城图标链接")
    @TableField(value = "icon_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String iconUrl;
    @ApiModelProperty("商城后端logo链接")
    @TableField(value = "merchat_logo_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String merchatLogoUrl;
    @ApiModelProperty("后台业务管理系统logo链接")
    @TableField(value = "platform_logo_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String platformLogoUrl;
    @ApiModelProperty("微信公众号二维码链接")
    @TableField(value = "wx_gzh_qrcode_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxGzhQrcodeUrl;
    @ApiModelProperty("微信公众号appId")
    @TableField(value = "wx_gzh_app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxGzhAppId;
    @ApiModelProperty("平台微信客服")
    @TableField(value = "wx_business", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxBusiness;
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
