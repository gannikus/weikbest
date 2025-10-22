package com.weikbest.pro.saas.merchat.prod.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品运费信息表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdFeightDTO对象", description = "商品运费信息表")
public class ProdFeightDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "运费类型不为空!")
    @ApiModelProperty(value = "运费类型 1-统一运费 2-运费模板", required = true)
    private String feightType;

    @ApiModelProperty(value = "运费金额（元）")
    private BigDecimal feightAmount;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "运费模板ID")
    private Long feightTemplateId;


}