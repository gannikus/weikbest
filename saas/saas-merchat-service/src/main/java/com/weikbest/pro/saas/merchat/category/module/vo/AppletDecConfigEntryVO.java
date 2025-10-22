package com.weikbest.pro.saas.merchat.category.module.vo;

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
 * 小程序装修配置分录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppletDecConfigEntryVO对象", description = "小程序装修配置分录表")
public class AppletDecConfigEntryVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("小程序装修配置ID ")
    private Long id;

    @ApiModelProperty("分录区域类型 1-轮播区 2-金刚区 3-魔方区 4-广告营销区")
    private String appletConfigType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID ")
    private Long prodId;

    @ApiModelProperty("名称")
    private String entryName;

    @ApiModelProperty("图片")
    private String entryImg;

    @ApiModelProperty("跳转链接")
    private String entryUrl;

    @ApiModelProperty("顺序")
    private Integer entryOrd;

    @ApiModelProperty("备注")
    private String description;


}