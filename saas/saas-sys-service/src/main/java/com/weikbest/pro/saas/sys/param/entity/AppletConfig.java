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
 * 小程序配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_applet_config")
@ApiModel(value = "AppletConfig对象", description = "小程序配置表")
public class AppletConfig implements Serializable {

    public static final String ID = "id";
    public static final String APPLET_NAME = "applet_name";
    public static final String APPLET_TYPE = "applet_type";
    public static final String APPLET_DEAL_TRIAL_SCORE = "applet_deal_trial_score";
    public static final String APPLET_HEALTH_TYPE = "applet_health_type";
    public static final String WX_MINIAPP_TYPE = "wx_miniapp_type";
    public static final String WX_MINIAPP_APP_ID = "wx_miniapp_app_id";
    public static final String WX_MINIAPP_APP_SECRET = "wx_miniapp_app_secret";
    public static final String WX_MINIAPP_TOKEN = "wx_miniapp_token";
    public static final String WX_MINIAPP_AES_KEY = "wx_miniapp_aes_key";
    public static final String WX_MINIAPP_ORIGINAL_ID = "wx_miniapp_original_id";
    public static final String WX_MINIAPP_MSG_DATA_FORMAT = "wx_miniapp_msg_data_format";
    public static final String WX_MINIAPP_COLUD_ENV = "wx_miniapp_colud_env";
    public static final String DATA_STATUS = "data_status";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("小程序名称")
    @TableField(value = "applet_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appletName;
    @ApiModelProperty("小程序类型 1-微信小程序")
    @TableField(value = "applet_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appletType;
    @ApiModelProperty("小程序交易体验分 0-100分")
    @TableField(value = "applet_deal_trial_score", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Integer appletDealTrialScore;
    @ApiModelProperty("小程序健康状态 1-健康 2-中风险 3-高风险")
    @TableField(value = "applet_health_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appletHealthType;
    @ApiModelProperty("微信小程序类型 developer-开发版 trial-体验版；formal-正式版")
    @TableField(value = "wx_miniapp_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappType;
    @ApiModelProperty("微信小程序-appId")
    @TableField(value = "wx_miniapp_app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappAppId;
    @ApiModelProperty("微信小程序-secret")
    @TableField(value = "wx_miniapp_app_secret", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappAppSecret;
    @ApiModelProperty("微信小程序-消息服务器配置的token")
    @TableField(value = "wx_miniapp_token", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappToken;
    @ApiModelProperty("微信小程序-消息服务器配置的EncodingAESKey")
    @TableField(value = "wx_miniapp_aes_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappAesKey;
    @ApiModelProperty("微信小程序-小程序原始ID")
    @TableField(value = "wx_miniapp_original_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappOriginalId;
    @ApiModelProperty("微信小程序-消息格式，XML或者JSON，默认JSON")
    @TableField(value = "wx_miniapp_msg_data_format", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappMsgDataFormat;
    @ApiModelProperty("微信小程序-云环境ID")
    @TableField(value = "wx_miniapp_colud_env", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String wxMiniappColudEnv;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
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
