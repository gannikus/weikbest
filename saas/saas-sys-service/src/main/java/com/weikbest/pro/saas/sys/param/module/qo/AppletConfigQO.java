package com.weikbest.pro.saas.sys.param.module.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 小程序配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppletConfigQO对象", description = "小程序配置表")
public class AppletConfigQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("小程序名称")
    private String appletName;

    @ApiModelProperty("小程序健康状态 1-健康 2-中风险 3-高风险")
    private String appletHealthType;

    @ApiModelProperty("最低小程序交易体验分")
    private Integer minAppletDealTrialScore;

    @ApiModelProperty("最高小程序交易体验分")
    private Integer maxAppletDealTrialScore;

}