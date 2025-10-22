package com.weikbest.pro.saas.merchat.aftersale.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/22
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderAfterSaleBusinessConfirmCustomerApplyDTO对象", description = "客户发起申请后，商家确认是否同意客户申请 实体")
public class OrderAfterSaleBusinessConfirmCustomerApplyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "商家是否同意不为空！")
    @ApiModelProperty(value = "商家是否同意 false-不同意 true-同意", required = true)
    Boolean businessConfirmFlag;

    @ApiModelProperty("退款去向 1-原路返回（商家同意，且类型不是换货时填写）")
    private String refundTrend;

    @ApiModelProperty("拒绝原因 9-其他（商家不同意时填写）")
    private String rejectReason;

    @ApiModelProperty("拒绝说明（商家不同意时填写）")
    private String rejectDetail;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商家地址ID（商家同意时填写）")
    private Long busiAddressId;
}

