package com.weikbest.pro.saas.common.transfervo.resp.dict;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/19
 * <p>
 * 字典项
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(value = "DictEntry对象", description = "数据字典")
public class DictEntry {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("字典id")
    private Long id;

    @ApiModelProperty("字典key")
    private String key;

    @ApiModelProperty("字典值")
    private String value;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("排序")
    private Integer ord;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("字典父id")
    private Long pid;
}
