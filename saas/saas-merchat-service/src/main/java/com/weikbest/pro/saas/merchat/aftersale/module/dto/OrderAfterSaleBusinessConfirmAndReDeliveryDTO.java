package com.weikbest.pro.saas.merchat.aftersale.module.dto;

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
import java.util.List;

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
@ApiModel(value = "OrderAfterSaleBusinessConfirmAndReDeliveryDTO对象", description = "商户确认收货后，商户重新发货实体")
public class OrderAfterSaleBusinessConfirmAndReDeliveryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "售后单ID不为空!")
    @ApiModelProperty(value = "售后单ID", required = true)
    private Long id;

    @NotBlank(message = "商家再次发送物流公司 字典表CODE不为空!")
    @ApiModelProperty(value = "商家再次发送物流公司 字典表CODE", required = true)
    private String sendLogisticsCompany;

    @NotBlank(message = "商家再次发送快递单号不为空!")
    @ApiModelProperty(value = "商家再次发送快递单号", required = true)
    private String sendCourierNumber;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("售后单重新发货物流图片列表")
    private List<String> courierImgPathList;
}
