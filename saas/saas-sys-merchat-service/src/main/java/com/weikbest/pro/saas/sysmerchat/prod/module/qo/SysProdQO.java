package com.weikbest.pro.saas.sysmerchat.prod.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@ApiModel(value = "SysProdQO对象", description = "平台商品列表查询对象")
public class SysProdQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
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

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品状态 1-上架中 2-已下架")
    private String prodStatus;

    @ApiModelProperty("加入店铺首页 0-关闭 1-已加入")
    private String joinShopMainpage;

    @ApiModelProperty("创建日期范围-开始 yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @ApiModelProperty("创建日期范围-结束 yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @ApiModelProperty("最低销量")
    private Integer minSales;

    @ApiModelProperty("最高销量")
    private Integer maxSales;

    @ApiModelProperty("最低套餐商品销售金额")
    private BigDecimal minComboMinPrice;

    @ApiModelProperty("最高套餐商品销售金额")
    private BigDecimal maxComboMinPrice;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("商户类别 1-普通商户 2-品牌商户 3-特约商户")
    private String businessType;

}
