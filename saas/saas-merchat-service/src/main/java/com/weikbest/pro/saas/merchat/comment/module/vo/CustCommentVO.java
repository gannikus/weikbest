package com.weikbest.pro.saas.merchat.comment.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "CustCommentVO对象", description = "客户评论表")
public class CustCommentVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("客户ID ")
    private Long customerId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品SKUID ")
    private Long prodSkuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联商品套餐ID ")
    private Long prodComboId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联订单ID")
    private Long orderId;

    @ApiModelProperty("是否匿名 0-否 1-是")
    private String isAnonymous;

    @ApiModelProperty("客户评价")
    private String commentDetail;

    @ApiModelProperty("评价订单 1-5星")
    private Byte commentOrder;

    @ApiModelProperty("评价订单 1-5星")
    private Byte commentProd;

    @ApiModelProperty("评价服务 1-5星")
    private Byte commentServer;

    @ApiModelProperty("评价物流 1-5星")
    private Byte commentLogistics;

    @ApiModelProperty("评论类型 0-好评 1-中评 2-差评")
    private String commentType;

    @ApiModelProperty("评论标记 0-正常 1-商家回复 2-客户回复  3-追评")
    private String commentFlag;

    @ApiModelProperty("商家是否回复客户 0-否 1-是")
    private String isBusinessComm;

    @ApiModelProperty("评价时间 yyyy-MM-dd HH:mm:ss")
    private Date commTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("上一条评论ID")
    private Long prevCommId;

    @ApiModelProperty("审核类型 0-待审核 1-审核不通过 2-审核通过")
    private String auditType;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;


}