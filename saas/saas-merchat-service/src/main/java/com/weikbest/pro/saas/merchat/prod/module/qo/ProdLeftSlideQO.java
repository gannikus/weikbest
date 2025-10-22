package com.weikbest.pro.saas.merchat.prod.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 商品左滑设置拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdLeftSlideQO对象", description = "商品左滑设置拆分表")
public class ProdLeftSlideQO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("左滑切换弹框文本")
    private String switchText;

    @ApiModelProperty("左滑跳转 0-不跳转 1-跳转")
    private String switchJump;

    @ApiModelProperty("主页跳转 0-不跳转 1-跳转")
    private String indexJump;

    @ApiModelProperty("右上角返回图标 0-不开启- 1-开启")
    private String switchRight;


}