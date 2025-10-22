package com.weikbest.pro.saas.sysmerchat.aftersale.module.qo;

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
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/14
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "SysOrderAfterSaleGroupQO对象", description = "平台端订单售后分组查询")
public class SysOrderAfterSaleGroupQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(name = "businessId", value = "商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;

}
