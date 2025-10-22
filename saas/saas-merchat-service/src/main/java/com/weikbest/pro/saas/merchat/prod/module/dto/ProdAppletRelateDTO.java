package com.weikbest.pro.saas.merchat.prod.module.dto;

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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "ProdAppletRelateDTO对象", description = "商品关联小程序拆分表")
public class ProdAppletRelateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联小程序配置表ID不为空!")
    @ApiModelProperty(value = "关联小程序配置表ID", required = true)
    private Long appletConfigId;

    @NotBlank(message = "小程序appID不为空!")
    @ApiModelProperty(value = "小程序appID", required = true)
    private String appletAppId;

    @NotBlank(message = "小程序原始ID不为空!")
    @ApiModelProperty(value = "小程序原始ID", required = true)
    private String appletOriginalId;

    @NotBlank(message = "小程序落地页链接不为空!")
    @ApiModelProperty(value = "小程序落地页链接", required = true)
    private String appletPageUrl;
}