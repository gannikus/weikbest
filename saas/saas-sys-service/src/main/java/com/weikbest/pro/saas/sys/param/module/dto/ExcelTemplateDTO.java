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
 * 系统excel模板表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ExcelTemplateDTO对象", description = "系统excel模板表")
public class ExcelTemplateDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "excel模板编码不为空!")
    @ApiModelProperty(value = "excel模板编码", required = true)
    private String number;

    @NotBlank(message = "excel模板名称不为空!")
    @ApiModelProperty(value = "excel模板名称", required = true)
    private String name;

    @NotBlank(message = "excel模板链接不为空!")
    @ApiModelProperty(value = "excel模板链接", required = true)
    private String templateUrl;


}