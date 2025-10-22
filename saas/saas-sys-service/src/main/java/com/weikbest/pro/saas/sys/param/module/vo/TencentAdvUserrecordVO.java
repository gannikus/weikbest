package com.weikbest.pro.saas.sys.param.module.vo;

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
@ApiModel(value = "TencentAdvUserrecordVO对象", description = "腾讯广告数据上报用户行为数据记录表")
public class TencentAdvUserrecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联订单ID")
    private Long orderId;

    @ApiModelProperty("标准行为类型")
    private String actionType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id")
    private Long accountId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("用户行为源 id")
    private Long userActionSetId;

    @ApiModelProperty("广告id/广告计划id")
    private String adAid;

    @ApiModelProperty("点击id")
    private String clickId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long prodId;

    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("微信 小程序AppID")
    private String wechatAppId;

    @ApiModelProperty("小程序用户openid")
    private String wechatOpenid;

    @ApiModelProperty("小程序用户unionid")
    private String wechatUnionid;

    @ApiModelProperty("应答结果json")
    private String returnResults;


}