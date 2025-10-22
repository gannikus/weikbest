package com.weikbest.pro.saas.merchat.shop.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 店铺小程序关联dto
 *
 * @author 甘之萌  2023/06/29 14:20
 */

@Data
@ApiModel(value = "ShopRelatedAppletDTO",description = "店铺小程序关联dto")
public class ShopRelatedAppletDTO {

    @NotNull(message = "店铺id不能为空")
    @ApiModelProperty("店铺id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty("小程序id的list")
    private List<Long> appletIds;

}
