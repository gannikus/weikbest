package com.weikbest.pro.saas.merchat.prod.module.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
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
import java.math.BigDecimal;

/**
 * <p>
 * 商品装修落地页广告回传信息拆分多行表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdAdvBackAccountDTO对象", description = "商品装修落地页广告回传信息拆分多行表")
public class ProdAdvBackAccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联广告账户ID不为空!")
    @ApiModelProperty(value = "关联广告账户ID", required = true)
    private Long advAccountId;

    @NotNull(message = "回传比率不为空!")
    @ApiModelProperty(value = "回传比率", required = true)
    private BigDecimal backRatio;


}