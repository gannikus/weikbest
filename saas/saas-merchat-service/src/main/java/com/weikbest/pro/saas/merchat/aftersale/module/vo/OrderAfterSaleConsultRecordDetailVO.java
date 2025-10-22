package com.weikbest.pro.saas.merchat.aftersale.module.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单售后协商历史记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderAfterSaleConsultRecordDetailVO对象", description = "订单售后协商历史记录")
public class OrderAfterSaleConsultRecordDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("变更时间 yyyy-MM-dd HH:mm:ss")
    private Date changeTime;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("详细字段")
    private List<DetailVO> detailVOList;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单售后ID")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("变更人类型 0-平台用户 1-商家端用户 2-App端用户")
    private String changerUserType;

    @ApiModelProperty("收货状态 0-未收货 1-已收货")
    private String takeDeliveryType;

    @ApiModelProperty("申请类型 1-仅退款 2-退货退款 3-换货")
    private String applyType;

    @ApiModelProperty("发货状态 0-未发货 1-已发货")
    private String sendType;

    @ApiModelProperty("是否极速退款售后单 0-否 1-是")
    private String isFastSale;

    @ApiModelProperty("售后关键节点 0-售后关闭 1-售后成功 10-仅退款待处理 11-仅退款处理中 20-退货退款待处理 21-退货退款待商家收货  22-退货退款处理中 30-换货待处理 31-换货待商家收货 32-换货处理中 7-待买家退货 8-客户处理中 9-平台处理中")
    private String afterSaleKey;

    @ApiModelProperty("售后状态 0-售后关闭 1-售后成功 2-客户申请售后，待商家处理 3-客户未处理，平台自动关闭售后 4-客户撤销申请 5-客户修改申请信息，待商家处理 6-客户已寄回商品，待商家收货 8-极速退款中 9-极速退款成功 10-商家不同意申请，待客户处理 11-商家未处理，平台同意申请 101-商家同意仅退款  201-商家同意退货退款，待客户寄回商品 203-商家已收货，待商家确认签收 204-商家已签收，待商家确认 205-商家已同意退款 206-商家拒绝退款，待客户处理 301-商家同意换货，待客户寄回商品 303-商家已收货，待商家确认 304-商家已重新发货，待客户确认 305-客户确认收货 306-商家拒绝换货，待客户处理 307-客户未收到货，待重新发起售后     ")
    private String afterSaleStatus;

    @ApiModelProperty("变更类型 1-客户申请 2-状态变更 3-客户物流信息变更 4-商家物流信息变更")
    private String changeType;

    @ApiModelProperty("商家再次发送快递单号")
    private String sendCourierNumber;

    /**
     * 构建详情
     *
     * @param fieldValue
     * @return
     */
    public static DetailVO buildDetailVO(String fieldValue) {
        return new DetailVO("", fieldValue, DictConstant.DetailFieldType.text.getCode());
    }

    /**
     * 构建详情
     *
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public static DetailVO buildDetailVO(String fieldName, String fieldValue) {
        return new DetailVO(fieldName, fieldValue, DictConstant.DetailFieldType.text.getCode());
    }

    /**
     * 构建详情
     *
     * @param fieldName
     * @param fieldValue
     * @param fieldType
     * @return
     */
    public static DetailVO buildDetailVO(String fieldName, String fieldValue, String fieldType) {
        return new DetailVO(fieldName, fieldValue, fieldType);
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailVO {

        @ApiModelProperty("详情字段名")
        private String fieldName;

        @ApiModelProperty("详情字段值")
        private String fieldValue;

        @ApiModelProperty("详情字段类型 1-文本 2-图片链接")
        private String fieldType;
    }

}
