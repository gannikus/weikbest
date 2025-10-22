package com.weikbest.pro.saas.merchat.busi.module.dto;

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

/**
 * <p>
 * 商户与商户账户关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2023-02-23
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "BusiUserRelateDTO对象", description = "商户与商户账户关联表")
public class BusiUserRelateDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商户ID不为空!")
    @ApiModelProperty(value = "商户ID", required = true)
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商户用户ID不为空!")
    @ApiModelProperty(value = "商户用户ID", required = true)
    private Long businessUserId;

    @NotBlank(message = "是否主账号不为空!")
    @ApiModelProperty(value = "是否主账号 0-否 1-是", required = true)
    private String isMainUser;


}