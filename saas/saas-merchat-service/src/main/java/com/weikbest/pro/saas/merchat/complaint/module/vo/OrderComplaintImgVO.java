package com.weikbest.pro.saas.merchat.complaint.module.vo;

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
 * 订单投诉图片拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderComplaintImgVO对象", description = "订单投诉图片拆分表")
public class OrderComplaintImgVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单投诉ID")
    private Long id;

    @ApiModelProperty("投诉图片链接")
    private String imgPath;


}