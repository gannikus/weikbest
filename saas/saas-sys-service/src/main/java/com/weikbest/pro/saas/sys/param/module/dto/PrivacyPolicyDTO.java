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
 * 系统隐私声明表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-23
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "PrivacyPolicyDTO对象", description = "系统隐私声明表")
public class PrivacyPolicyDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "隐私声明不为空!")
    @ApiModelProperty(value = "隐私声明", required = true)
    private String privacyPolicy;


}