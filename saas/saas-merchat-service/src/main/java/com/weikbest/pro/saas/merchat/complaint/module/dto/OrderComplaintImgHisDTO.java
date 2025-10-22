package com.weikbest.pro.saas.merchat.complaint.module.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 订单投诉图片拆分历史表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-05
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderComplaintImgHisDTO对象", description = "订单投诉图片拆分历史表")
public class OrderComplaintImgHisDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "订单投诉历史ID不为空!")
    @ApiModelProperty(value = "订单投诉历史ID", required = true)
    private Long historyId;


    @NotBlank(message = "投诉图片链接不为空!")
    @ApiModelProperty(value = "投诉图片链接", required = true)
    private String imgPath;


}