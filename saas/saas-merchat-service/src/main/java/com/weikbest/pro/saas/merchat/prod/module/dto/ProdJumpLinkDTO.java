package com.weikbest.pro.saas.merchat.prod.module.dto;

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
 * 商品跳转链接拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdJumpLinkDTO对象", description = "商品跳转链接拆分多行表")
public class ProdJumpLinkDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "跳转链接类型不为空!")
    @ApiModelProperty(value = "跳转链接类型 1-左滑跳转 2-主页跳转", required = true)
    private String jumpLinkType;

    @NotBlank(message = "跳转链接不为空!")
    @ApiModelProperty(value = "跳转链接", required = true)
    private String jumpLink;

    @ApiModelProperty(value = "链接排序", required = true)
    private Integer jumpLinkOrder;

    @NotBlank(message = "是否快速下单链接不为空!")
    @ApiModelProperty(value = "是否快速下单链接 0-否 1-是", required = true)
    private String isQuickOrderLink;

}