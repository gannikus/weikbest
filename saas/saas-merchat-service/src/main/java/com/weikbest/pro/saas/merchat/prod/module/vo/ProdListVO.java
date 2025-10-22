package com.weikbest.pro.saas.merchat.prod.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
@ApiModel(value = "ProdListVO对象", description = "商品基本信息列表对象")
public class ProdListVO extends ProdVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long modifier;

    @ApiModelProperty("更新人名称")
    private String modifierName;

    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    @ApiModelProperty("商品图片")
    private String showImg;

    @ApiModelProperty("售价（元）")
    private BigDecimal comboMinPrice;

    @ApiModelProperty("实际销量")
    private Integer realSales;

    @ApiModelProperty("回传是否显示_0-不显示 1-显示")
    private String backIsOpen;

    @ApiModelProperty(value = "支付成功回传 0-不回传 1-回传")
    private String successPayBack;

    @ApiModelProperty(value = "回传比率")
    private BigDecimal backRatio;

    @ApiModelProperty("累计销量统计，key=订单来源 1-自然流量 5-广告引流 10-回流流量-未回传 15-回流流量-已回传 20-平台流量")
    private Map<String, Integer> orderSourceCountMap = new HashMap<>();

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "随手购商品Id")
    private Long shoppingProdId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "随手购商品套餐Id")
    private Long shoppingComboId;

    @ApiModelProperty(value = "商品关联小程序AppId")
    private List<String> appIds;
}
