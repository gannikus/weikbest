package com.weikbest.pro.saas.merchat.complaint.module.vo;

import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderComplaintDetailConsultRecordDetailVO对象", description = "订单投诉协商历史记录")
public class OrderComplaintDetailRecordDetailVO extends OrderComplaintDetailVO {

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

    /**
     * 构建详情
     *
     * @param fieldValue
     * @return
     */
    public static OrderComplaintDetailRecordDetailVO.DetailVO buildDetailVO(String fieldValue) {
        return new OrderComplaintDetailRecordDetailVO.DetailVO("", fieldValue, DictConstant.DetailFieldType.text.getCode());
    }

    /**
     * 构建详情
     *
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public static OrderComplaintDetailRecordDetailVO.DetailVO buildDetailVO(String fieldName, String fieldValue) {
        return new OrderComplaintDetailRecordDetailVO.DetailVO(fieldName, fieldValue, DictConstant.DetailFieldType.text.getCode());
    }

    /**
     * 构建详情
     *
     * @param fieldName
     * @param fieldValue
     * @param fieldType
     * @return
     */
    public static OrderComplaintDetailRecordDetailVO.DetailVO buildDetailVO(String fieldName, String fieldValue, String fieldType) {
        return new OrderComplaintDetailRecordDetailVO.DetailVO(fieldName, fieldValue, fieldType);
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