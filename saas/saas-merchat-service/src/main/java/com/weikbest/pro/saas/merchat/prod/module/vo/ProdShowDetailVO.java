package com.weikbest.pro.saas.merchat.prod.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdServiceCommitmentDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdShowDetailVO对象", description = "商品基本信息详情表")
public class ProdShowDetailVO extends ProdVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty(value = "商品关联销售套餐信息")
    private List<ProdComboVO> prodComboVOList;

    @ApiModelProperty("回显多规格_key是规格名对应规格值和图片")
    private Map<String,List<EchoMuchSize>> echoMuchSizes;

    @ApiModelProperty("套餐类型 1:普通套餐 , 2:多规格套餐")
    private Integer setMealType;

    @ApiModelProperty("多规格中配图按钮_0关闭 , 1:开启")
    private Integer isImg;

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

    @ApiModelProperty(value = "商品服务承诺信息")
    private ProdServiceCommitmentDTO prodServiceCommitmentDTO;

    @ApiModelProperty("是否开启限购 0-关闭，1-开启")
    private String isOpenOrderLimit;

    @ApiModelProperty("prodPurchaseRestrictionsVo商品限购配置类")
    private ProdPurchaseRestrictionsVo prodPurchaseRestrictionsVo;

    @Data
    @ApiModel(value = "echoMuchSize对象", description = "多规格回显信息")
    public static class EchoMuchSize{

        @ApiModelProperty("规格值")
        private String value;

        @ApiModelProperty("配图")
        private String img;

        @ApiModelProperty(value = "套餐编码", required = true)
        private String comboCode;

    }

}
