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
 * 系统编码规则表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_sys_code_rule")
@ApiModel(value = "CodeRule对象", description = "系统编码规则表")
public class CodeRule implements Serializable {

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String NAME = "name";
    public static final String RULE_TYPE = "rule_type";
    public static final String PREFIX = "prefix";
    public static final String CONNECTOR = "connector";
    public static final String INIT_DIGIT = "init_digit";
    public static final String REAL_DIGIT = "real_digit";
    public static final String CURR_NUM = "curr_num";
    public static final String DESCRIPTION = "description";
    public static final String DATA_STATUS = "data_status";
    public static final String CREATOR = "creator";
    public static final String MODIFIER = "modifier";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("编码规则编码")
    @TableField(value = "number", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String number;
    @ApiModelProperty("编码规则名称")
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;
    @ApiModelProperty("编码规则类型 1-流水号 2-日期时间+流水号")
    @TableField(value = "rule_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String ruleType;
    @ApiModelProperty("固定前缀")
    @TableField(value = "prefix", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String prefix;
    @ApiModelProperty("连接符号")
    @TableField(value = "connector", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String connector;
    @ApiModelProperty("初始编码位数")
    @TableField("init_digit")
    private Integer initDigit;
    @ApiModelProperty("实际编码位数")
    @TableField("real_digit")
    private Integer realDigit;
    @ApiModelProperty("当前编码")
    @TableField(value = "curr_num", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String currNum;
    @ApiModelProperty("描述")
    @TableField(value = "description", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String description;
    @ApiModelProperty("数据状态 0-禁用 1-可用")
    @TableField(value = "data_status", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String dataStatus;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    @TableField("creator")
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    @TableField("modifier")
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
