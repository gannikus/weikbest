package com.weikbest.pro.saas.sys.param.module.dto;

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
import java.math.BigDecimal;

/**
 * <p>
 * 腾讯广告数据上报用户行为数据记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "TencentAdvUserrecordSendDTO对象", description = "从订单支付成功回调过来上报用户行为数据")
public class TencentAdvUserrecordSendDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "回传比率")
    private BigDecimal backRatio;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联订单ID")
    private Long orderId;

    @ApiModelProperty(value = "广告id/广告计划id")
    private String adAid;

    @ApiModelProperty(value = "点击id")
    private String clickId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "商品ID")
    private Long prodId;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "微信小程序AppID")
    private String wechatAppId;

    @ApiModelProperty(value = "小程序用户openid")
    private String wechatOpenid;

    @ApiModelProperty(value = "小程序用户unionid")
    private String wechatUnionid;

}