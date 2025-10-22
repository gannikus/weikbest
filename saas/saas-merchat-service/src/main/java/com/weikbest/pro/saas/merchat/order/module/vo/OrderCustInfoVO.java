package com.weikbest.pro.saas.merchat.order.module.vo;

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
 * 客户订单与客户关联拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderCustInfoVO对象", description = "客户订单与客户关联拆分表")
public class OrderCustInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联客户ID ")
    private Long customerId;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("客户所在地区省、直辖市")
    private String customerProvince;

    @ApiModelProperty("客户所在地区市")
    private String customerCity;

    @ApiModelProperty("客户所在地区区、县")
    private String customerDistrict;

    @ApiModelProperty("详细地址")
    private String customerAddr;

    @ApiModelProperty("手机")
    private String customerPhone;

    @ApiModelProperty("客户备注")
    private String customerDescription;

    @ApiModelProperty("第三方平台类型 1-微信")
    private String tpType;

    @ApiModelProperty("昵称")
    private String tpName;

    @ApiModelProperty("头像")
    private String tpPhoto;

    @ApiModelProperty("openid")
    private String tpOpenid;

    @ApiModelProperty("推广二维码")
    private String tpQrcode;


}