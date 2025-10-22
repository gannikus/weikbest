package com.weikbest.pro.saas.merchat.busi.module.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value = "BusiUserVO对象", description = "商户账户表")
public class BusiUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("是否主账号 0-否 1-是")
    private String isMainUser;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


    @ApiModelProperty("对外接口key")
    private String apiKey;

    @ApiModelProperty("对外接口secret")
    private String apiSecret;

}
