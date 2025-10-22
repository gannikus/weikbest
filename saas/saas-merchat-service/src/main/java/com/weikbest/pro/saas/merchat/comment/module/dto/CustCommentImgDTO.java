package com.weikbest.pro.saas.merchat.comment.module.dto;

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
 * 用户评论图片表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CustCommentImgDTO对象", description = "用户评论图片表")
public class CustCommentImgDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "评论ID不为空!")
    @ApiModelProperty(value = "评论ID", required = true)
    private Long commentId;

    @NotBlank(message = "图片地址不为空!")
    @ApiModelProperty(value = "图片地址", required = true)
    private String imgUrl;


}