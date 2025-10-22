package com.weikbest.pro.saas.applet.comm.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 商品营销位入参查询
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppProdSemPositionQO对象", description = "商品营销位入参表")
public class AppProdSemPositionQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("营销位分组编码")
    private String positionnumber;

    @ApiModelProperty("分类编码")
    private String commoditynumber;


}