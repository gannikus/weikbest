package com.weikbest.pro.saas.merchat.prod.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 商品与商家详细地址管理表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdBusiAddrDTO对象", description = "商品与商家详细地址管理表")
public class ProdBusiAddrDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品ID 不为空!")
    @ApiModelProperty(value = "关联商品ID ", required = true)
    private Long prodId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联地址ID 不为空!")
    @ApiModelProperty(value = "关联地址ID ", required = true)
    private Long busiAddrId;


}