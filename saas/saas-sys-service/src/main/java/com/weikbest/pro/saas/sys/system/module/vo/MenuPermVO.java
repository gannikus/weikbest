package com.weikbest.pro.saas.sys.system.module.vo;

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
 * 系统菜单权限项关联表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "MenuPermVO对象", description = "系统菜单权限项关联表")
public class MenuPermVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("菜单ID")
    private Long menuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("权限项ID")
    private Long permId;

    @ApiModelProperty("权限项编码")
    private String permNumber;

    @ApiModelProperty("权限项名称")
    private String permName;

    @ApiModelProperty("是否选中 0-否 1-是")
    private Boolean checked = false;

}