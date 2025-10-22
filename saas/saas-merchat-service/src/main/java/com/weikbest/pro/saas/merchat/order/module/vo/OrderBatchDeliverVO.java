package com.weikbest.pro.saas.merchat.order.module.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单批量发货记录表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-26
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderBatchDeliverVO对象", description = "订单批量发货记录表")
public class OrderBatchDeliverVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "关联商户ID")
    private Long businessId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "关联店铺ID")
    private Long shopId;

    @ApiModelProperty("操作类型 1-批量发货")
    private String deliverType;

    @ApiModelProperty("操作时间 yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("操作人")
    private Long operator;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("操作人类型 0-平台用户 1-商家端用户 2-App端用户")
    private String operatorType;

    @ApiModelProperty("excel导入文件名称")
    private String operateExcelName;

    @ApiModelProperty("excel导入文件")
    private String operateExcelUrl;

    @ApiModelProperty("操作订单数")
    private Integer totalNum;

    @ApiModelProperty("操作成功数")
    private Integer successNum;

    @ApiModelProperty("操作失败数")
    private Integer errorNum;

    @ApiModelProperty("状态，1-上传中，2-已上传")
    private String status;


}
