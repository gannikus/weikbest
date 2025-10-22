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
 * 系统延时任务表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "DelayTaskVO对象", description = "系统延时任务表")
public class DelayTaskConfigVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("延时任务编码")
    private String number;

    @ApiModelProperty("延时任务名称")
    private String name;


}