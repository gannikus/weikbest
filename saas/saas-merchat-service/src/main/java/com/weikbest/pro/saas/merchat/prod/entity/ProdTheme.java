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
 * 商品样式拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_theme")
@ApiModel(value = "ProdTheme对象", description = "商品样式拆分表")
public class ProdTheme implements Serializable {

    public static final String ID = "id";
    public static final String SHOW_IMG = "show_img";
    public static final String MAIN_NUM = "main_num";
    public static final String MAIN_RATIO_TYPE = "main_ratio_type";
    public static final String DETAIL_NUM = "detail_num";
    public static final String PROD_THEME = "prod_theme";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("商品展示图（缩略图）")
    @TableField(value = "show_img", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String showImg;
    @ApiModelProperty("商品详情页轮播图数量上限")
    @TableField("main_num")
    private Integer mainNum;
    @ApiModelProperty("商品详情页轮播图比例 1-1:1 2-3:4")
    @TableField(value = "main_ratio_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String mainRatioType;
    @ApiModelProperty("商品详情图数量上限")
    @TableField("detail_num")
    private Integer detailNum;
    @ApiModelProperty("跑马灯样式  1-淡入淡出浮层跑马灯 2-主图下方跑马灯")
    @TableField(value = "prod_theme", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prodTheme;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
