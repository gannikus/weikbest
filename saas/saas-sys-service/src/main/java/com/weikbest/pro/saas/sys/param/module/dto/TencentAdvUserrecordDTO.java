package com.weikbest.pro.saas.sys.param.module.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "TencentAdvUserrecordDTO对象", description = "腾讯广告数据上报用户行为数据记录表")
public class TencentAdvUserrecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "关联订单ID不为空!")
    @ApiModelProperty("关联订单ID")
    private Long orderId;

    @NotBlank(message = "标准行为类型不为空!")
    @ApiModelProperty(value = "标准行为类型", required = true)
    private String actionType;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id不为空!")
    @ApiModelProperty(value = "推广帐号id,有操作权限的帐号 id，包括代理商和广告主帐号 id", required = true)
    private Long accountId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotBlank(message = "用户行为源 id不为空!")
    @ApiModelProperty(value = "用户行为源 id", required = true)
    private Long userActionSetId;

    @NotBlank(message = "广告id/广告计划id不为空!")
    @ApiModelProperty(value = "广告id/广告计划id", required = true)
    private String adAid;

    @NotBlank(message = "点击id不为空!")
    @ApiModelProperty(value = "点击id", required = true)
    private String clickId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商品ID不为空!")
    @ApiModelProperty(value = "商品ID", required = true)
    private Long prodId;

    @ApiModelProperty(value = "支付金额", required = true)
    private BigDecimal payAmount;

    @NotBlank(message = "微信 小程序AppID不为空!")
    @ApiModelProperty(value = "微信 小程序AppID", required = true)
    private String wechatAppId;

    @NotBlank(message = "小程序用户openid不为空!")
    @ApiModelProperty(value = "小程序用户openid", required = true)
    private String wechatOpenid;

    @NotBlank(message = "小程序用户unionid不为空!")
    @ApiModelProperty(value = "小程序用户unionid", required = true)
    private String wechatUnionid;

    @NotBlank(message = "应答结果json不为空!")
    @ApiModelProperty(value = "应答结果json", required = true)
    private String returnResults;


}