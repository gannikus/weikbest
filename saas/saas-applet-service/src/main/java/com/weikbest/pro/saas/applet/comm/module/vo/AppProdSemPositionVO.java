package com.weikbest.pro.saas.applet.comm.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppProdSemPositionVO对象", description = "商品营销位位置表")
public class AppProdSemPositionVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("营销位分组编码")
    private String number;

    @ApiModelProperty("营销位分组名称")
    private String name;

    @ApiModelProperty("营销位分组容纳元素数量")
    private Integer semNum;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @ApiModelProperty("该广告位下的分类信息")
    private List<AppProdSemCategoryVO> appProdSemCategoryVOList;


}