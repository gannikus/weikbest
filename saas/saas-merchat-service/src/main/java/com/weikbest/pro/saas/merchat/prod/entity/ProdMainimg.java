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
 * 商品详情页轮播图拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_mainimg")
@ApiModel(value = "ProdMainimg对象", description = "商品详情页轮播图拆分多行表")
public class ProdMainimg implements Serializable {

    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String MAIN_IMG = "main_img";
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
    @ApiModelProperty("商品详情页轮播图")
    @TableField(value = "main_img", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String mainImg;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
