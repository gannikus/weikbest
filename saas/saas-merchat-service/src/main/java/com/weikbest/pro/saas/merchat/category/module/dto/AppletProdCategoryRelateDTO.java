package com.weikbest.pro.saas.merchat.category.module.dto;

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
import java.util.List;

/**
 * <p>
 * 小程序商品类目关联商品表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppletProdCategoryRelateDTO对象", description = "小程序商品类目关联商品表")
public class AppletProdCategoryRelateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品小程序类目ID不为空!")
    @ApiModelProperty(value = "关联商品小程序类目ID  ", required = true)
    private Long appletProdCategotyId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品ID 不为空!")
    @ApiModelProperty(value = "关联商品ID ", required = true)
    private Long prodId;

    @ApiModelProperty(value = "商品在小程序类目中的排序", required = true)
    private Integer prodOrd;

    @NotNull(message = "关联小程序AppId不可为空!")
    @ApiModelProperty(value = "关联小程序AppId", required = true)
    private List<String> appIds;

}