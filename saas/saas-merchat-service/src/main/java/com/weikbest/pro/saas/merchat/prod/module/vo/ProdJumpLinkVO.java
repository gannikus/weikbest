package com.weikbest.pro.saas.merchat.prod.module.vo;

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
@ApiModel(value = "ProdJumpLinkVO对象", description = "商品跳转链接拆分多行表")
public class ProdJumpLinkVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    private Long id;

    @ApiModelProperty("跳转链接类型 1-左滑跳转 2-主页跳转")
    private String jumpLinkType;

    @ApiModelProperty("跳转链接")
    private String jumpLink;

    @ApiModelProperty("链接排序")
    private Integer jumpLinkOrder;

    @ApiModelProperty("是否快速下单链接 0-否 1-是")
    private String isQuickOrderLink;


}