package com.weikbest.pro.saas.applet.aftersale.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 客户售后信息
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "AppOrderAfterLogisticsInfoDTO对象", description = "售后客户物流信息")
public class AppOrderAfterLogisticsInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("客户寄回物流公司 字典表CODE")
    private String backLogisticsCompany;

    @ApiModelProperty("客户寄回快递单号")
    private String backCourierNumber;

    @ApiModelProperty("客户寄回快递图片")
    private List<String> backCourierImgPaths;

    @ApiModelProperty("联系电话")
    private String backCourierPhone;

    @ApiModelProperty("问题描述")
    private String backDetail;

}