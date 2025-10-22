package com.weikbest.pro.saas.merchat.complaint.module.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderCustInfoVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderPayRecordVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderProdInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单售后表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderComplaintDetailVO对象", description = "订单投诉详情")
public class OrderComplaintDetailVO extends OrderComplaintVO {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("售后单ID")
    private Long id;

    @ApiModelProperty("投诉单过期时间")
    private Date timeout;

    @ApiModelProperty("订单信息")
    private OrderInfoVO orderInfoVO;

    @ApiModelProperty("商品信息")
    private OrderProdInfoVO orderProdInfoVO;

    @ApiModelProperty("订单客户信息")
    private OrderCustInfoVO orderCustInfoVO;

    @ApiModelProperty("订单支付记录信息")
    private OrderPayRecordVO orderPayRecordVO;

    @ApiModelProperty("处理记录")
    private List<OrderComplaintDetailRecordDetailVO> orderComplaintDetailRecordDetailVOList;

    @ApiModelProperty("投诉图片")
    private List<String> imgList;

    @ApiModelProperty("企业微信客服")
    private String wxBusiness;

}