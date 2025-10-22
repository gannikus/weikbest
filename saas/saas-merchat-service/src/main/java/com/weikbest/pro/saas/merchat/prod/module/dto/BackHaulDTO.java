package com.weikbest.pro.saas.merchat.prod.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Mr.Wang
 */
@Data
@ToString
@Accessors(chain = true)
@ApiModel(value = "BackHaulDTO对象", description = "卷后订单回传接受类")
public class BackHaulDTO {

    @NotNull(message = "商品ID不为空!")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long prodId;

    @ApiModelProperty(value = "支付成功回传 0-不回传 1-回传", required = true)
    private String successPayBack;

    @ApiModelProperty(value = "回传比率", required = true)
    private BigDecimal backRatio;

}
