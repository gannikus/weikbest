package com.weikbest.pro.saas.sys.param.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "ExcelTemplateQO对象", description = "系统excel模板表")
public class ExcelTemplateQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("excel模板编码")
    private String number;

    @ApiModelProperty("excel模板名称")
    private String name;

    @ApiModelProperty("excel模板链接")
    private String templateUrl;


}