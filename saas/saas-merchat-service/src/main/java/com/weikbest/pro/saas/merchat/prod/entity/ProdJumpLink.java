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
 * 商品跳转链接拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_jump_link")
@ApiModel(value = "ProdJumpLink对象", description = "商品跳转链接拆分多行表")
public class ProdJumpLink implements Serializable {

    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String JUMP_LINK_TYPE = "jump_link_type";
    public static final String JUMP_LINK = "jump_link";
    public static final String JUMP_LINK_ORDER = "jump_link_order";
    public static final String IS_QUICK_ORDER_LINK = "is_quick_order_link";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("多行表主键id ")
    @TableId(value = "entry_id", type = IdType.ASSIGN_ID)
    private Long entryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableField("id")
    private Long id;
    @ApiModelProperty("跳转链接类型 1-左滑跳转 2-主页跳转")
    @TableField(value = "jump_link_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String jumpLinkType;
    @ApiModelProperty("跳转链接")
    @TableField(value = "jump_link", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String jumpLink;
    @ApiModelProperty("链接排序")
    @TableField("jump_link_order")
    private Integer jumpLinkOrder;
    @ApiModelProperty("是否快速下单链接 0-否 1-是")
    @TableField(value = "is_quick_order_link", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isQuickOrderLink;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
