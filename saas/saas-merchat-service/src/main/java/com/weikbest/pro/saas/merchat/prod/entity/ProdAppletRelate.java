package com.weikbest.pro.saas.merchat.prod.entity;

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
 * 商品关联小程序拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_applet_relate")
@ApiModel(value = "ProdAppletRelate对象", description = "商品关联小程序拆分表")
public class ProdAppletRelate implements Serializable {

    public static final String ID = "id";
    public static final String APPLET_CONFIG_ID = "applet_config_id";
    public static final String APPLET_APP_ID = "applet_app_id";
    public static final String APPLET_ORIGINAL_ID = "applet_original_id";
    public static final String APPLET_PAGE_URL = "applet_page_url";
    public static final String APPLET_APP_QRCODE_URL = "applet_app_qrcode_url";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id（商品ID） ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联小程序配置表ID")
    @TableField("applet_config_id")
    private Long appletConfigId;
    @ApiModelProperty("小程序appID")
    @TableField(value = "applet_app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appletAppId;
    @ApiModelProperty("小程序原始ID")
    @TableField(value = "applet_original_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appletOriginalId;
    @ApiModelProperty("小程序落地页链接")
    @TableField(value = "applet_page_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appletPageUrl;
    @ApiModelProperty("小程序二维码图片")
    @TableField(value = "applet_app_qrcode_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appletAppQrcodeUrl;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
