package com.weikbest.pro.saas.merchat.category.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@ApiModel(value = "AppletDecConfigEntryDTO对象", description = "小程序装修配置分录表")
public class AppletDecConfigEntryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "分录区域类型不为空!")
    @ApiModelProperty(value = "分录区域类型 1-轮播区 2-金刚区 3-魔方区 4-广告营销区", required = true)
    private String appletConfigType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "关联商品ID ")
    private Long prodId;

    @ApiModelProperty("名称")
    private String entryName;

    @NotBlank(message = "图片不为空!")
    @ApiModelProperty(value = "图片", required = true)
    private String entryImg;

    @ApiModelProperty(value = "跳转链接")
    private String entryUrl;

    @ApiModelProperty(value = "顺序")
    private Integer entryOrd;

    @ApiModelProperty("备注")
    private String description;

}