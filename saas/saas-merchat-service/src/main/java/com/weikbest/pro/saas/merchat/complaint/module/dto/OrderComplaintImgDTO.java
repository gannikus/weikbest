package com.weikbest.pro.saas.merchat.complaint.module.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
@ApiModel(value = "OrderComplaintImgDTO对象", description = "订单投诉图片拆分表")
public class OrderComplaintImgDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotBlank(message = "投诉图片链接不为空!")
    @ApiModelProperty(value = "投诉图片链接", required = true)
    private String imgPath;


}