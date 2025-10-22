package com.weikbest.pro.saas.merchat.busi.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "BusiUserRelateVO对象", description = "商户与商户账户关联表")
public class BusiUserRelateVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户用户ID")
    private Long businessUserId;

    @ApiModelProperty("是否主账号 0-否 1-是")
    private String isMainUser;


}