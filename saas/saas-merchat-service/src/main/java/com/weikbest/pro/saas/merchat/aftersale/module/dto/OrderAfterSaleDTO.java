package com.weikbest.pro.saas.merchat.aftersale.module.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
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
@ApiModel(value = "OrderAfterSaleDTO对象", description = "订单售后表")
public class OrderAfterSaleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "订单编号不为空!")
    @ApiModelProperty(value = "订单编号", required = true)
    private String orderNumber;

    @NotBlank(message = "售后类型不为空!")
    @ApiModelProperty(value = "申请类型 1-仅退款 2-退货退款 3-换货", required = true)
    private String applyType;

    @NotBlank(message = "收货状态不为空!")
    @ApiModelProperty(value = "收货状态 0-未收货 1-已收货", required = true)
    private String takeDeliveryType;

    @NotBlank(message = "售后原因不为空!")
    @ApiModelProperty(value = "申请原因 1-少发/漏发 2-质量问题 3-货物与描述不符 4-未按约定时间发货 5-发票问题 6-卖家发错货 7-假冒品牌 8-退运费 9-尺寸拍错/不喜欢/效果不好 10-其他", required = true)
    private String applyReason;

    @NotNull(message = "售后件数不为空!")
    @ApiModelProperty(value = "货物件数", required = true)
    private Integer goodsNum;

    @NotNull(message = "申请金额不为空!")
    @ApiModelProperty(value = "申请金额", required = true)
    private BigDecimal applyAmount;

    @NotBlank(message = "联系方式不为空!")
    @ApiModelProperty(value = "联系电话", required = true)
    private String customerPhone;

    @ApiModelProperty(value = "申请说明", required = true)
    private String questionDetail;

    @ApiModelProperty("执行退款失败原因")
    private String refundFailureReason;

    @ApiModelProperty(value = "售后凭证链接集合")
    private List<String> courierImgPathList;

    @ApiModelProperty("执行退款任务失败次数")
    private Integer refundFailureTimes;

}
