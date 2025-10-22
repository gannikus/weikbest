package com.weikbest.pro.saas.merchat.busi.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopListVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 商户账户表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "BusiUserLoginInfoVO对象", description = "商户账户登录信息")
public class BusiUserLoginInfoVO extends BusiUserVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户端用户id")
    private Long id;

    @ApiModelProperty("店铺信息")
    private List<ShopVO> shopVOList;

}