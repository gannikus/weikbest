package com.weikbest.pro.saas.common.transfervo.req.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wisdomelon
 * @date 2020/6/22 0022
 * @project saas
 * @jdk 1.8
 */
@Data
@ApiModel(value = "PageReq", description = "PageReq")
public class PageReq implements Serializable {

    @ApiModelProperty(value = "当前页", required = true)
    private int page = 1;

    @ApiModelProperty(value = "页大小", required = true)
    private int limit = 20;

}
