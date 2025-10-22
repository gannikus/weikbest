package com.weikbest.pro.saas.sys.param.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
@ApiModel(value = "DictTypeDTO对象", description = "字典类型表")
public class DictTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "编码不为空!")
    @ApiModelProperty(value = "编码", required = true)
    private String number;

    @NotBlank(message = "名称不为空!")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty("平台预置 0-否 1-是")
    private String isPreset;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}