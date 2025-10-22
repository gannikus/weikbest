package com.weikbest.pro.saas.sysmerchat.category.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdQO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/2
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SysAppletProdCategoryRelateQO对象", description = "商品关联小程序分类查询")
public class SysAppletProdCategoryRelateQO extends ProdQO {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品小程序类目ID")
    private Long appletProdCategotyId;
}
