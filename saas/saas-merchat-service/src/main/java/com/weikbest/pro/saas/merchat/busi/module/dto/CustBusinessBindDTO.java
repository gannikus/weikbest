package com.weikbest.pro.saas.merchat.busi.module.dto;

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
import java.util.Date;

/**
 * <p>
 * 分账商户绑定表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CustBusinessBindDTO对象", description = "分账商户绑定表")
public class CustBusinessBindDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "客户ID不为空!")
    @ApiModelProperty(value = "客户ID", required = true)
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商户ID不为空!")
    @ApiModelProperty(value = "商户ID", required = true)
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "店铺ID不为空!")
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商品ID不为空!")
    @ApiModelProperty(value = "商品ID", required = true)
    private Long prodId;

    @NotBlank(message = "订单号不为空!")
    @ApiModelProperty(value = "订单号", required = true)
    private String number;

    @NotBlank(message = "关联小程序appid不为空!")
    @ApiModelProperty(value = "关联小程序appid", required = true)
    private String appId;

    @ApiModelProperty(value = "绑定时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date bindTime;

    @NotBlank(message = "有效期不为空!")
    @ApiModelProperty(value = "有效期", required = true)
    private String validityDay;

    @NotBlank(message = "绑定状态 1-绑定 2-解除绑定不为空!")
    @ApiModelProperty(value = "绑定状态 1-绑定 2-解除绑定", required = true)
    private String bindStatus;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}