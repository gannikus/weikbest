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
 * 商户店铺表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopDetailVO对象", description = "商户店铺表")
public class ShopDetailVO extends ShopVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺ID")
    private Long id;

    @ApiModelProperty(value = "店铺支付信息")
    private ShopThirdVO shopThirdVO;

    @ApiModelProperty("商户名称")
    private String businessName;
    @ApiModelProperty("商户类别 1-普通商户 2-品牌商户 3-特约商户")
    private String businessType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商家主账号ID")
    private Long busiUserId;
    @ApiModelProperty("商家手机号")
    private String busiPhone;
    @ApiModelProperty("商家姓名")
    private String busiUserName;

}