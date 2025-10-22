package com.weikbest.pro.saas.merchat.prod.module.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
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

/**
 * <p>
 * 商品关联小程序拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdAppletRelateVO对象", description = "商品关联小程序拆分表")
public class ProdAppletRelateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID（商品ID） ")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联小程序配置表ID")
    private Long appletConfigId;

    @ApiModelProperty("小程序appID")
    private String appletAppId;

    @ApiModelProperty("小程序原始ID")
    private String appletOriginalId;

    @ApiModelProperty("小程序落地页链接")
    private String appletPageUrl;

    @ApiModelProperty("小程序二维码图片")
    private String appletAppQrcodeUrl;
}