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
import java.util.Date;

/**
 * <p>
 * 客户评论表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-24
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "CustCommentDTO对象", description = "客户评论表")
public class CustCommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "商户ID不为空!")
    @ApiModelProperty(value = "商户ID", required = true)
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "客户ID 不为空!")
    @ApiModelProperty(value = "客户ID ", required = true)
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品SKUID 不为空!")
    @ApiModelProperty(value = "关联商品SKUID ", required = true)
    private Long prodSkuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联商品套餐ID 不为空!")
    @ApiModelProperty(value = "关联商品套餐ID ", required = true)
    private Long prodComboId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "关联订单ID不为空!")
    @ApiModelProperty(value = "关联订单ID", required = true)
    private Long orderId;

    @NotBlank(message = "是否匿名 0-否 1-是不为空!")
    @ApiModelProperty(value = "是否匿名 0-否 1-是", required = true)
    private String isAnonymous;

    @NotBlank(message = "客户评价不为空!")
    @ApiModelProperty(value = "客户评价", required = true)
    private String commentDetail;

    @ApiModelProperty(value = "评价订单 1-5星", required = true)
    private Byte commentOrder;

    @ApiModelProperty(value = "评价订单 1-5星", required = true)
    private Byte commentProd;

    @ApiModelProperty(value = "评价服务 1-5星", required = true)
    private Byte commentServer;

    @ApiModelProperty(value = "评价物流 1-5星", required = true)
    private Byte commentLogistics;

    @NotBlank(message = "评论类型 0-好评 1-中评 2-差评不为空!")
    @ApiModelProperty(value = "评论类型 0-好评 1-中评 2-差评", required = true)
    private String commentType;

    @NotBlank(message = "评论标记 0-正常 1-商家回复 2-客户回复  3-追评不为空!")
    @ApiModelProperty(value = "评论标记 0-正常 1-商家回复 2-客户回复  3-追评", required = true)
    private String commentFlag;

    @NotBlank(message = "商家是否回复客户 0-否 1-是不为空!")
    @ApiModelProperty(value = "商家是否回复客户 0-否 1-是", required = true)
    private String isBusinessComm;

    @ApiModelProperty(value = "评价时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date commTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "上一条评论ID不为空!")
    @ApiModelProperty(value = "上一条评论ID", required = true)
    private Long prevCommId;

    @NotBlank(message = "审核类型 0-待审核 1-审核不通过 2-审核通过不为空!")
    @ApiModelProperty(value = "审核类型 0-待审核 1-审核不通过 2-审核通过", required = true)
    private String auditType;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}