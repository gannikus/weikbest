package com.weikbest.pro.saas.sysmerchat.prod.module.vo;

import com.baomidou.mybatisplus.annotation.Version;
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
@ApiModel(value = "SysProdListVO对象", description = "平台商品信息列表对象")
public class SysProdListVO implements Serializable {

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

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    private Long creator;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    private Long modifier;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @Version
    private Date gmtModified;


    @ApiModelProperty("商品图片")
    private String showImg;

    @ApiModelProperty("售价（元）")
    private BigDecimal comboMinPrice;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("店铺名称")
    private String shopName;

}
