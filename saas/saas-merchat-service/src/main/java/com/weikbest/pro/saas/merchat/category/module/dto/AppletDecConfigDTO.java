package com.weikbest.pro.saas.merchat.category.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

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
@ApiModel(value = "AppletDecConfigDTO对象", description = "小程序装修配置表")
public class AppletDecConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "页面名称不为空!")
    @ApiModelProperty(value = "页面名称", required = true)
    private String name;

    @ApiModelProperty("页面区域数据合")
    private List<AppletDecConfigEntryDTO> appletDecConfigEntryDTOList;

}