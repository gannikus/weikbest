package com.weikbest.pro.saas.merchat.prod.module.vo;

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
import javax.validation.constraints.NotNull;

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
@ApiModel(value = "ProdVO对象", description = "商品基本信息表")
public class ProdVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @ApiModelProperty("关联店铺名")
    private String shopName;

    @ApiModelProperty("关联商户名")
    private String businessName;

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


    @ApiModelProperty("商品卖点")
    private String prodSellingPoint;


    @ApiModelProperty(value = "商品类别 1- 自营商品 ，2-广告商品")
    private String goodsType;

    @ApiModelProperty("支付类型 ,1-微信支付，2-货到付款")
    private String payType;

    @ApiModelProperty("是否开启到付验证开关 0-关闭，1-开启")
    private Integer isOpenCashOnDeliverySms;

    @ApiModelProperty("是否开启限购 0-关闭，1-开启")
    private String isOpenOrderLimit;

    @ApiModelProperty("客服开关，0-关闭，1-开启")
    private String customerServiceSwitch;

}
