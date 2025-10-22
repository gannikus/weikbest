package com.weikbest.pro.saas.merchat.category.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfigEntry;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小程序装修配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppletDecConfigVO对象", description = "小程序装修配置表")
public class AppletDecConfigVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键ID ")
    private Long id;

    @ApiModelProperty("页面名称")
    private String name;

    @ApiModelProperty("页面区域数据 key=区域类型 value=区域数据集合")
    private Map<String, List<AppletDecConfigEntry>> entryMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);

}