package com.weikbest.pro.saas.merchat.shop.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value = "ShopThirdReceiveVO对象", description = "店铺第三方平台分账接收方拆分多行表")
public class ShopThirdReceiveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺ID")
    private Long id;

    @ApiModelProperty("微信支付-微信公众号或者小程序等的appid")
    private String wxPayAppId;

    @ApiModelProperty("微信支付-分账接收方商户号")
    private String wxPayReceiveMchId;

    @ApiModelProperty("添加日期")
    private Date joinTime;

    @ApiModelProperty("分账接收方类型  MERCHANT_ID-商户")
    private String wxPayReceiveType;

    @ApiModelProperty("分账接收方名称")
    private String wxPayReceiveName;

    @ApiModelProperty("分账接收方关系  PARTNER-合作伙伴")
    private String wxPayReceiveRelationType;


}