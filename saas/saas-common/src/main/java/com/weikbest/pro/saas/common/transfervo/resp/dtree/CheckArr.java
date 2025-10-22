package com.weikbest.pro.saas.common.transfervo.resp.dtree;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wisdomelon
 * @date 2019/6/11 0011
 * @project saas
 * @jdk 1.8
 * 树复选框类
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CheckArr对象", description = "树形对象的复选框对象")
public class CheckArr implements Serializable {

    @ApiModelProperty("复选框标记")
    private String type;

    @ApiModelProperty("复选框是否选中 0-不选中 1-选中 2-半选")
    private String checked;
}
