package com.weikbest.pro.saas.merchat.prod.module.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@ApiModel(value = "ProdDTO对象", description = "商品基本信息表")
public class ProdDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商户ID不为空!")
    @ApiModelProperty(value = "关联商户ID", required = true)
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联店铺ID不为空!")
    @ApiModelProperty(value = "关联店铺ID", required = true)
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "类目ID不为空!")
    @ApiModelProperty(value = "类目ID", required = true)
    private Long categoryId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "品牌ID")
    private Long brandId;

    @ApiModelProperty(value = "商品类别 1- 自营商品 ，2-广告商品")
    private String goodsType;

    @NotBlank(message = "商品名称不为空!")
    @ApiModelProperty(value = "商品名称", required = true)
    private String name;

    @ApiModelProperty(value = "商品内部备注")
    private String tips;

    @ApiModelProperty(value = "商品排序", required = true)
    private Integer prodOrd;

    @ApiModelProperty(value = "展示销量", required = true)
    private Integer sales;

    @NotBlank(message = "隐私协议不为空!")
    @ApiModelProperty(value = "隐私协议 0-不展示 1-展示", required = true)
    private String safeguard;

    @ApiModelProperty(value = "商品关联销售套餐信息")
    private List<ProdComboDTO> prodComboDTOList;

    @NotNull(message = "商品价格信息不为空!")
    @ApiModelProperty(value = "商品价格信息", required = true)
    private ProdPriceDTO prodPriceDTO;

    @NotNull(message = "商品运费信息不为空!")
    @ApiModelProperty(value = "商品运费信息", required = true)
    private ProdFeightDTO prodFeightDTO;

    @NotNull(message = "商品样式信息不为空!")
    @ApiModelProperty(value = "商品样式信息", required = true)
    private ProdThemeDTO prodThemeDTO;

//    @NotNull(message = "商品服务承诺信息不为空!")
    @ApiModelProperty(value = "商品服务承诺信息", required = true)
    private ProdServiceCommitmentDTO prodServiceCommitmentDTO;

    @NotNull(message = "商品详情信息不为空!")
    @Size(min = 1, max = 100, message = "商品详情信息不为空！")
    @ApiModelProperty(value = "商品详情信息", required = true)
    private List<ProdDetailDTO> prodDetailDTOList;

    @NotNull(message = "商品详情页轮播图信息不为空!")
    @Size(min = 1, max = 100, message = "商品详情页轮播图信息不为空！")
    @ApiModelProperty(value = "商品详情页轮播图信息", required = true)
    private List<ProdMainimgDTO> prodMainimgDTOList;

//    @NotNull(message = "商品卖点信息不为空!")
    @ApiModelProperty("商品卖点")
    private String prodSellingPoint;


    @ApiModelProperty(value = "商品套餐规格 1：普通套餐，2：多规格" ,required = true)
    private Integer setMealType;

    @ApiModelProperty("支付类型 ,1-微信支付，2-货到付款")
    private String payType;

    @ApiModelProperty("是否开启到付验证开关 0-关闭，1-开启")
    private Integer isOpenCashOnDeliverySms;

    @ApiModelProperty("是否开启限购 0-关闭，1-开启")
    private String isOpenOrderLimit;

    @ApiModelProperty("客服开关，0-关闭，1-开启")
    private String customerServiceSwitch;

    @ApiModelProperty("商品限购配置dto")
    private ProdPurchaseRestrictionsDto prodPurchaseRestrictionsDto;
}
