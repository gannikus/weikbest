package com.weikbest.pro.saas.merchat.category.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 小程序装修配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppletDecConfigQO对象", description = "小程序装修配置表")
public class AppletDecConfigQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("页面名称")
    private String name;


}