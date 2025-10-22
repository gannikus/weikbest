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
 * 小程序订阅消息配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_applet_subscribe_config")
@ApiModel(value = "AppletSubscribeConfig对象", description = "小程序订阅消息配置表")
public class AppletSubscribeConfig implements Serializable {

    public static final String ID = "id";
    public static final String APPLET_CONFIG_ID = "applet_config_id";
    public static final String APP_ID = "app_id";
    public static final String APPLET_PAGE = "applet_page";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String SUBSCRIBE_TYPE = "subscribe_type";
    public static final String CONTENT = "content";
    public static final String PARAMS = "params";
    public static final String BIND_URL = "bind_url";
    public static final String DESCRIPTION = "description";
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
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("小程序配置表ID")
    @TableField("applet_config_id")
    private Long appletConfigId;
    @ApiModelProperty("小程序ID")
    @TableField(value = "app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appId;
    @ApiModelProperty("小程序页面路径")
    @TableField(value = "applet_page", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appletPage;
    @ApiModelProperty("消息模板编码")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("消息模板名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("消息模板类型 2-一次性订阅 3-长期订阅")
    @TableField(value = "subscribe_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String subscribeType;
    @ApiModelProperty("消息模板内容")
    @TableField(value = "content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String content;
    @ApiModelProperty("消息模板参数详情")
    @TableField(value = "params", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String params;
    @ApiModelProperty("消息绑定路径，多个路径用,分割")
    @TableField(value = "bind_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String bindUrl;
    @ApiModelProperty("描述")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
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
