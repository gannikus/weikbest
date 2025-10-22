package com.weikbest.pro.saas.merchat.shop.module.dto;

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
 * 商户店铺表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-16
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopSetupDTO", description = "商户店铺 开店")
public class ShopSetupDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商户ID不为空!")
    @ApiModelProperty(value = "商户ID", required = true)
    private Long businessId;

    @NotBlank(message = "店铺名称不为空!")
    @ApiModelProperty(value = "店铺名称", required = true)
    private String name;

    @NotBlank(message = "店铺logo不为空!")
    @ApiModelProperty(value = "店铺logo", required = true)
    private String logo;

    @ApiModelProperty(value = "是否品牌店铺 0-否 1-是")
    private String isBrand;

    @NotBlank(message = "联系人电话不为空!")
    @ApiModelProperty(value = "联系人电话", required = true)
    private String contact;

    @NotBlank(message = "企业名称（工商注册全名）不为空!")
    @ApiModelProperty(value = "企业名称（工商注册全名）", required = true)
    private String companyName;

    @ApiModelProperty(value = "企业地址")
    private String companyAddress;

    @ApiModelProperty(value = "企业人数")
    private Integer companyStaff;

    @NotBlank(message = "营业执照不为空!")
    @ApiModelProperty(value = "营业执照", required = true)
    private String businessListence;

    @ApiModelProperty(value = "店铺类型 0-非自营店铺 1-自营店铺")
    private String shopType;

    @NotBlank(message = "所属行业不为空!")
    @ApiModelProperty(value = "所属行业", required = true)
    private String tradeType;

    @ApiModelProperty("服务说明")
    private String serviceIntro;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("是否能控制广告回传开关; 0-关闭，1-开启")
    private String isControlAdCallback;


}
