package com.weikbest.pro.saas.merchat.shop.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 店铺第三方平台分账接收方拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopThirdReceiveDTO对象", description = "店铺第三方平台分账接收方拆分多行表")
public class ShopThirdReceiveDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "微信支付-微信公众号或者小程序等的appid")
    private String wxPayAppId;

    @ApiModelProperty(value = "微信支付-微信公众号或者小程序等的appid 集合")
    private List<String> wxPayAppIds;

    @NotBlank(message = "微信支付-分账接收方商户号不为空!")
    @ApiModelProperty(value = "微信支付-分账接收方商户号", required = true)
    private String wxPayReceiveMchId;

    @NotBlank(message = "分账接收方名称不为空!")
    @ApiModelProperty(value = "分账接收方名称", required = true)
    private String wxPayReceiveName;


}