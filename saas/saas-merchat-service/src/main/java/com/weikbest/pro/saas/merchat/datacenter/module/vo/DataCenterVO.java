package com.weikbest.pro.saas.merchat.datacenter.module.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/4
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "DataCenterVO对象", description = "数据中心显示实体")
public class DataCenterVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("总概览")
    private TotalVO totalVO;

    @ApiModelProperty("支付概览")
    private PayVO payVO;

    @ApiModelProperty("流量概览")
    private FlowVO flowVO;

    @ApiModelProperty("退款概况")
    private RefundVO refundVO;

    @ApiModelProperty("货到付款概况")
    private DeliveryVO deliveryVO;

    @ApiModelProperty("货到付款广告概况")
    private DeliveryAdVO deliveryAdVO;

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @ApiModel(value = "TotalVO对象", description = "总概览")
    public static class TotalVO {

        @ApiModelProperty("总支付金额(元)")
        private BigDecimal totalPayAmount;

        @ApiModelProperty("总支付订单数")
        private Integer totalPayOrderNum;

        @ApiModelProperty("总支付人数")
        private Long totalPayCount;

        @ApiModelProperty("总支付件数")
        private Integer totalPayGoodsNum;

        @ApiModelProperty("支付客单价(元)")
        private BigDecimal totalCustomerPayAmount;
    }

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @ApiModel(value = "PayVO对象", description = "支付概览")
    public static class PayVO {

        @ApiModelProperty("广告流量支付总额(元)")
        private BigDecimal advFlowTotalPayAmount;

        @ApiModelProperty("广告流量支付订单数")
        private Integer advFlowTotalPayOrderNum;

        @ApiModelProperty("广告流量支付人数")
        private Long advFlowTotalPayCount;

        @ApiModelProperty("广告流量支付件数")
        private Integer advFlowTotalPayGoodsNum;

        @ApiModelProperty("广告流量支付客单价(元)")
        private BigDecimal advFlowTotalCustomerPayAmount;


        @ApiModelProperty("自然流量支付总额(元)")
        private BigDecimal naturalFlowTotalPayAmount;

        @ApiModelProperty("自然流量支付订单数")
        private Integer naturalFlowTotalPayOrderNum;

        @ApiModelProperty("自然流量支付人数")
        private Long naturalFlowTotalPayCount;

        @ApiModelProperty("自然流量支付件数")
        private Integer naturalFlowTotalPayGoodsNum;

        @ApiModelProperty("自然流量支付客单价(元)")
        private BigDecimal naturalFlowTotalCustomerPayAmount;
    }

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @ApiModel(value = "FlowVO对象", description = "流量概览")
    public static class FlowVO {

        @ApiModelProperty("访客数")
        private Long visitorCount;

        @ApiModelProperty("浏览量")
        private Long pageView;

        @ApiModelProperty("被访问商品数")
        private Long visitorProdCount;

        @ApiModelProperty("访问-下单转化率")
        private BigDecimal visitorOrderConversionRate;

        @ApiModelProperty("访问-支付转化率")
        private BigDecimal visitorPayConversionRate;
    }

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @ApiModel(value = "RefundVO对象", description = "退款概况")
    public static class RefundVO {

        @ApiModelProperty("成功退款金额(元)")
        private BigDecimal refundAmount;

        @ApiModelProperty("成功退款订单数")
        private Integer refundOrderCount;

        @ApiModelProperty("广告流量成功退款人数")
        private Long advFlowRefundCount;

        @ApiModelProperty("自然流量成功退款人数")
        private Long naturalFlowRefundCount;

        @ApiModelProperty("退款率")
        private BigDecimal refundRatio;
    }

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @ApiModel(value = "DeliveryVO对象", description = "货到付款订单概况")
    public static class DeliveryVO {
        @ApiModelProperty("货到付款下单数")
        private Long deliveryOrderCount;

        @ApiModelProperty("货到付款下单金额")
        private BigDecimal deliveryOrderAmounts;

        @ApiModelProperty("货到付款总支付人数")
        private Integer deliveryOrderTotalNumberOfPeople;

        @ApiModelProperty("货到付款总支付件数")
        private BigDecimal deliveryOrderTotalNumberOfPackages;

        @ApiModelProperty("货到付款支付客单价")
        private BigDecimal deliveryOrderARPA;
    }

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @ApiModel(value = "DeliveryVO对象", description = "货到付款订单概况")
    public static class DeliveryAdVO {
        @ApiModelProperty("货到付款广告回传数")
        private Long deliveryOrderAdCallbackCount;

        @ApiModelProperty("货到付款广告回传金额")
        private BigDecimal deliveryOrderAdCallbackAmounts;

        @ApiModelProperty("货到付款广告回传支付总人数")
        private Integer deliveryOrderAdTotalNumberOfPeople;

        @ApiModelProperty("货到付款广告回传支付总件数")
        private BigDecimal deliveryOrderAdTotalNumberOfPackages;

        @ApiModelProperty("货到付款广告回传客单价")
        private BigDecimal deliveryOrderAdARPA;
    }
}
