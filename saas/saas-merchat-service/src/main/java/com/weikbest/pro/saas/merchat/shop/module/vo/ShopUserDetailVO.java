package com.weikbest.pro.saas.merchat.shop.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/27
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopUserDetailVO对象", description = "商户店铺用户详情对象")
public class ShopUserDetailVO extends ShopUserVO {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("角色列表")
    private List<String> roleIdList;
}
