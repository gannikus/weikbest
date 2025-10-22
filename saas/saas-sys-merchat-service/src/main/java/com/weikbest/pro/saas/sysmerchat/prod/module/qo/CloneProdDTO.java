package com.weikbest.pro.saas.sysmerchat.prod.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
@Accessors(chain = true)
@ApiModel(value = "SysProdQO对象", description = "平台商品列表查询对象")
public class CloneProdDTO implements Serializable {

    private static final long serialVersionUID = -4293968435474637202L;

    @NotNull(message = "商品ID不能为空!")
    @ApiModelProperty("商品ID")
    private Long id;

    @NotNull(message = "店铺Id不能为空!")
    @ApiModelProperty("店铺Id")
    private Long shopId;

    @NotNull(message = "商户Id不能为空!")
    @ApiModelProperty("商户Id")
    private Long businessId;
}
