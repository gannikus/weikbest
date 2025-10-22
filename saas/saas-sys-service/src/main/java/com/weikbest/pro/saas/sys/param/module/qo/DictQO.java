package com.weikbest.pro.saas.sys.param.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "DictQO对象", description = "字典表")
public class DictQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("编码")
    private String number;

    @ApiModelProperty("名称")
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("字典类型ID")
    private Long dictTypeId;

    @ApiModelProperty("字典排序")
    private Integer dictOrd;

    @ApiModelProperty("平台预置 0-否 1-是")
    private String isPreset;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}