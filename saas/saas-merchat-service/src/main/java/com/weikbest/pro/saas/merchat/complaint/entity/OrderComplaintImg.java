package com.weikbest.pro.saas.merchat.complaint.entity;

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
 * 订单投诉图片拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_order_complaint_img")
@ApiModel(value = "OrderComplaintImg对象", description = "订单投诉图片拆分表")
public class OrderComplaintImg implements Serializable {

    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String IMG_PATH = "img_path";
    public static final String IMG_TYPE = "img_type";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "entry_id", type = IdType.ASSIGN_ID)
    private Long entryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单投诉ID")
    @TableField("id")
    private Long id;
    @ApiModelProperty("投诉图片链接")
    @TableField(value = "img_path", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String imgPath;
    @ApiModelProperty("投诉图片类型 1-客户申请投诉 2-协商上传凭证")
    @TableField(value = "img_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String imgType;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
