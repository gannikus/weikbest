package com.weikbest.pro.saas.applet.comm.module.vo;


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
 * 小程序首页商品轮播图表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdCarouselVO对象", description = "小程序首页商品轮播图表")
public class AppProdCarouselVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品ID ")
    private Long prodId;

    @ApiModelProperty("轮播图编码")
    private String number;

    @ApiModelProperty("轮播图名称")
    private String name;

    @ApiModelProperty("轮播图片")
    private String carouselImg;

    @ApiModelProperty("轮播类型 1-小程序首页轮播图")
    private String carouselType;

    @ApiModelProperty("链接")
    private String carouselUrl;

    @ApiModelProperty("排序")
    private Integer carouselOrd;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}
