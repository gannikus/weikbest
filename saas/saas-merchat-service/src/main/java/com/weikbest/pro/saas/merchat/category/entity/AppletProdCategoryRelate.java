package com.weikbest.pro.saas.merchat.category.entity;

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
 * 小程序商品类目关联商品表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_applet_prod_category_relate")
@ApiModel(value = "AppletProdCategoryRelate对象", description = "小程序商品类目关联商品表")
public class AppletProdCategoryRelate implements Serializable {

    public static final String ID = "id";
    public static final String APPLET_PROD_CATEGOTY_ID = "applet_prod_categoty_id";
    public static final String PROD_ID = "prod_id";
    public static final String PROD_ORD = "prod_ord";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "MODIFIER";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品小程序类目ID  ")
    @TableField("applet_prod_categoty_id")
    private Long appletProdCategotyId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID ")
    @TableField("prod_id")
    private Long prodId;
    @ApiModelProperty("商品在小程序类目中的排序")
    @TableField("prod_ord")
    private Integer prodOrd;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField(value = "MODIFIER", fill = FieldFill.INSERT_UPDATE)
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
