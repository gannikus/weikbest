package com.weikbest.pro.saas.applet.commodity.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.prod.entity.ProdReturn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


@Data
@Accessors(chain = true)
@ApiModel(value = "AddParametersVo对象", description = "根据商品ID查询多添加的参数信息")
public class AddParametersVo implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("页面banner_图片")
    private String joinpageBannerUrl;

    @ApiModelProperty("上传商品图_图片")
    private String  uploadGoodsImg;

    @ApiModelProperty("底部banner_图片")
    private String  bottomBannerImg;

    @ApiModelProperty("主图首图_图片")
    private String pictureFirstPicture;

    @ApiModelProperty("页面背景色")
    private String backgroundColor;

    @ApiModelProperty("聚合页弹窗图片链接")
    private String joinpageOpenUrl;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @ApiModelProperty("商品返回页信息")
    private List<ProdReturn> returns;
}
