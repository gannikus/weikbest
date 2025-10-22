package com.weikbest.pro.saas.sys.param.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "DictTypeVO对象", description = "字典类型表")
public class DictTypeVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("编码")
    private String number;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("平台预置 0-否 1-是")
    private String isPreset;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}