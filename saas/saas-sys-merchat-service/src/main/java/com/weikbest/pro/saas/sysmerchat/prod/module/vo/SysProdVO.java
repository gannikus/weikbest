package com.weikbest.pro.saas.sysmerchat.prod.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.prod.module.vo.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SysProdVO对象", description = "平台商品详细信息表")
public class SysProdVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("类目ID")
    private Long categoryId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("品牌ID")
    private Long brandId;

    @ApiModelProperty("商品编码")
    private String number;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品内部备注")
    private String tips;

    @ApiModelProperty("商品排序")
    private Integer prodOrd;

    @ApiModelProperty("展示销量")
    private Integer sales;

    @ApiModelProperty("隐私协议 0-不展示 1-展示")
    private String safeguard;

    @ApiModelProperty("商品状态 1-上架中 2-已下架")
    private String prodStatus;

    @ApiModelProperty("加入店铺首页 0-关闭 1-已加入")
    private String joinShopMainpage;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @ApiModelProperty("套餐类型 1:普通套餐 , 2:多规格套餐")
    private Integer setMealType;

    @ApiModelProperty("多规格下套餐标题")
    private String title;

    @ApiModelProperty(value = "商品关联销售套餐信息")
    private List<ProdComboVO> prodComboVOList;

    @ApiModelProperty(value = "多规格套餐信息")
    private List<MoreSpecVo> moreSpecs;

    @ApiModelProperty(value = "商品价格信息")
    private ProdPriceVO prodPriceVO;

    @ApiModelProperty(value = "商品运费信息")
    private ProdFeightVO prodFeightVO;

    @ApiModelProperty(value = "商品样式信息")
    private ProdThemeVO prodThemeVO;

    @ApiModelProperty(value = "商品详情信息")
    private List<ProdDetailVO> prodDetailVOList;

    @ApiModelProperty(value = "商品详情页轮播图信息")
    private List<ProdMainimgVO> prodMainimgVOList;

    @ApiModelProperty(value = "商品装修落地页信息")
    private ProdDecFloorVO prodDecFloorVO;

    @ApiModelProperty(value = "商品左滑信息")
    private ProdLeftSlideVO prodLeftSlideVO;

    @ApiModelProperty(value = "商品劵信息")
    private List<ProdCouponVO> prodCouponVOList;

    @ApiModelProperty(value = "商品访问信息")
    private ProdAppletRelateVO prodAppletRelateVO;
}
