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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品运费信息表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_feight")
@ApiModel(value = "ProdFeight对象", description = "商品运费信息表")
public class ProdFeight implements Serializable {

    public static final String ID = "id";
    public static final String FEIGHT_TYPE = "feight_type";
    public static final String FEIGHT_AMOUNT = "feight_amount";
    public static final String FEIGHT_TEMPLATE_ID = "feight_template_id";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("运费类型 1-统一运费 2-运费模板")
    @TableField(value = "feight_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String feightType;
    @ApiModelProperty("运费金额（元）")
    @TableField("feight_amount")
    private BigDecimal feightAmount;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("运费模板ID")
    @TableField("feight_template_id")
    private Long feightTemplateId;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
