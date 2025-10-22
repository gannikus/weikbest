package com.weikbest.pro.saas.merchat.shop.module.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 商户店铺表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-16
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopDTO对象", description = "商户店铺表")
public class ShopDTO extends ShopSetupDTO {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "店铺支付信息不为空!")
    @ApiModelProperty(value = "店铺支付信息", required = true)
    private ShopThirdDTO shopThirdDTO;

//    @NotBlank(message = "商户名称不为空!")
//    @ApiModelProperty(value = "商户名称", required = true)
//    private String businessName;
//    @NotBlank(message = "商户类别不为空!")
//    @ApiModelProperty(value = "商户类别 1-普通商户 2-品牌商户 3-特约商户", required = true)
//    private String businessType;
//
//    @NotNull(message = "商家主账号ID不为空!")
//    @JsonSerialize(using = ToStringSerializer.class)
//    @ApiModelProperty(value = "商家主账号ID", required = true)
//    private String busiUserId;
//    @NotBlank(message = "商家手机号不为空!")
//    @ApiModelProperty(value = "商家手机号", required = true)
//    private String busiPhone;
//    @NotBlank(message = "商家姓名不为空!")
//    @ApiModelProperty(value = "商家姓名", required = true)
//    private String busiUserName;


}
