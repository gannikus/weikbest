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
@ApiModel(value = "PrivacyPolicyVO对象", description = "系统隐私声明表")
public class PrivacyPolicyVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("隐私声明")
    private String privacyPolicy;


}