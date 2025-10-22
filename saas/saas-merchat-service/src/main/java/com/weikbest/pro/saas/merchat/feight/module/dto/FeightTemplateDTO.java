package com.weikbest.pro.saas.merchat.feight.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 运费模板表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "FeightTemplateDTO对象", description = "运费模板表")
public class FeightTemplateDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商户ID不为空!")
    @ApiModelProperty(value = "商户ID", required = true)
    private Long businessId;

    @NotBlank(message = "模板名称不为空!")
    @ApiModelProperty(value = "模板名称", required = true)
    private String name;

    @NotBlank(message = "计件方式不为空!")
    @ApiModelProperty(value = "计件方式 1-按件数", required = true)
    private String pieceworkType;

    @NotNull(message = "运费模板详情不为空！")
    @ApiModelProperty(value = "运费模板详情", required = true)
    private List<FeightTemplateDetailDTO> feightTemplateDetailDTOList;


}