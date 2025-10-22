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
 * 小程序订阅消息发送记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_applet_subscribe_record")
@ApiModel(value = "AppletSubscribeRecord对象", description = "小程序订阅消息发送记录表")
public class AppletSubscribeRecord implements Serializable {

    public static final String ID = "id";
    public static final String APP_ID = "app_id";
    public static final String APPLET_PAGE = "applet_page";
    public static final String SUBSCRIBE_CONFIG_ID = "subscribe_config_id";
    public static final String SEND_CONTENT = "send_content";
    public static final String SEND_PARAM = "send_param";
    public static final String SEND_PHONE = "send_phone";
    public static final String BIND_URL = "bind_url";
    public static final String RECEIPT_OPENID = "receipt_openid";
    public static final String RECEIPT_CUST_NAME = "receipt_cust_name";
    public static final String SEND_STATUS = "send_status";
    public static final String SEND_TIME = "send_time";
    public static final String SEND_EXCEPTION = "send_exception";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String SUBSCRIBE_TYPE = "subscribe_type";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("小程序ID")
    @TableField(value = "app_id", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appId;
    @ApiModelProperty("小程序页面路径")
    @TableField(value = "applet_page", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String appletPage;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("短信模板ID")
    @TableField("subscribe_config_id")
    private Long subscribeConfigId;
    @ApiModelProperty("发送内容 ")
    @TableField(value = "send_content", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendContent;
    @ApiModelProperty("发送参数映射 ")
    @TableField(value = "send_param", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendParam;
    @ApiModelProperty("发送手机号 ")
    @TableField(value = "send_phone", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendPhone;
    @ApiModelProperty("发送消息绑定路径")
    @TableField(value = "bind_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String bindUrl;
    @ApiModelProperty("接收客户openid ")
    @TableField("receipt_openid")
    private String receiptOpenid;
    @ApiModelProperty("接收客户名称 ")
    @TableField(value = "receipt_cust_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String receiptCustName;
    @ApiModelProperty("发送状态 ")
    @TableField(value = "send_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendStatus;
    @ApiModelProperty("发送时间  yyyy-MM-dd HH:mm:ss ")
    @TableField("send_time")
    private Date sendTime;
    @ApiModelProperty("发送失败错误信息 ")
    @TableField(value = "send_exception", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String sendException;
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
    @ApiModelProperty("消息模板编码")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("消息模板名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("消息模板类型 2-一次性订阅 3-长期订阅")
    @TableField(value = "subscribe_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String subscribeType;
}
