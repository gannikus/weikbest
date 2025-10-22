package com.weikbest.pro.saas.merchat.complaint.module.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微信订单投诉
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "WxOrderComplaintVO对象", description = "微信订单投诉")
public class WxOrderComplaintVO implements Serializable {

    private static final long serialVersionUID = -2720805531074695908L;

    @ApiModelProperty("未处理总数")
    private Map<String , Object> totalUnprocessed;

    @ApiModelProperty("处理中总数")
    private Map<String , Object> totalNumberInProcess;

    @ApiModelProperty("已处理总数")
    private Map<String , Object> totalProcessed;

    @ApiModelProperty("列表数据")
    private List<WxComplaintVO> wxComplaintVOS;


    @Data
    @ApiModel(value = "WxComplaintVO对象", description = "微信订单投诉内")
    public static class WxComplaintVO{

        @ApiModelProperty("投诉单号")
        private String complaintId;

        /**
         * 投诉用户
         **/
        @JsonSerialize(using = ToStringSerializer.class)
        @ApiModelProperty("客户ID ")
        private Long customerId;

        @ApiModelProperty("昵称")
        private String tpName;

        @ApiModelProperty("头像")
        private String tpPhoto;

        @ApiModelProperty("手机")
        private String customerPhone;

        @ApiModelProperty("投诉时间")
        private String complaintTime;

        @ApiModelProperty("投诉详情")
        private String complaintDetail;

        /**
         * 投诉订单
         **/
        @ApiModelProperty("商品名称")
        private String prodName;

        @ApiModelProperty("商品图片")
        private String prodImg;

        @JsonSerialize(using = ToStringSerializer.class)
        @ApiModelProperty("商品ID")
        private Long prodId;

        @JsonSerialize(using = ToStringSerializer.class)
        @ApiModelProperty("订单ID")
        private Long orderId;

        @ApiModelProperty("实付款")
        private BigDecimal payAmount;

        /**
         * 投诉类型
         **/
        @ApiModelProperty("投诉类型 REFUND：退款类型的问题投诉 SERVICE_NOT_WORK：服务权益未生效 OTHERS：其他类型")
        private String problemType;

        /**
         * 微信支付订单号/商户号
         **/
        @ApiModelProperty("微信支付订单号")
        private String outTradeNo;

        @ApiModelProperty("商户号")
        private String mchId;

        /**
         * 处理状态
         **/
        @ApiModelProperty("处理状态 PENDING：待处理 PROCESSING：处理中 PROCESSED：已处理完成")
        private String complaintState;

    }
}