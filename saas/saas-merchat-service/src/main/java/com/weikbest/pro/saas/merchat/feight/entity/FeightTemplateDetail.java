package com.weikbest.pro.saas.merchat.feight.entity;

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
 * 运费模板详情拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_feight_template_detail")
@ApiModel(value = "FeightTemplateDetail对象", description = "运费模板详情拆分多行表")
public class FeightTemplateDetail implements Serializable {

    public static final String ENTRY_ID = "entry_id";
    public static final String ID = "id";
    public static final String FIRST_PIECE = "first_piece";
    public static final String FIRST_AMOUNT = "first_amount";
    public static final String KEEP_PIECE = "keep_piece";
    public static final String KEEP_AMOUNT = "keep_amount";
    public static final String IS_PINKAGE = "is_pinkage";
    public static final String PINKAGE_NUM = "pinkage_num";
    public static final String PINKAGE_TYPE = "pinkage_type";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    @TableId(value = "entry_id", type = IdType.ASSIGN_ID)
    private Long entryId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("运费模板ID")
    @TableField("id")
    private Long id;
    @ApiModelProperty("首件(个)")
    @TableField("first_piece")
    private Integer firstPiece;
    @ApiModelProperty("首件金额(元)")
    @TableField("first_amount")
    private BigDecimal firstAmount;
    @ApiModelProperty("续件(个)")
    @TableField("keep_piece")
    private Integer keepPiece;
    @ApiModelProperty("续件金额(元)")
    @TableField("keep_amount")
    private BigDecimal keepAmount;
    @ApiModelProperty("是否指定包邮条件 0-否 1-是")
    @TableField(value = "is_pinkage", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String isPinkage;
    @ApiModelProperty("满包邮数量")
    @TableField("pinkage_num")
    private Integer pinkageNum;
    @ApiModelProperty("包邮条件类型 1-元 2-件")
    @TableField(value = "pinkage_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String pinkageType;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
