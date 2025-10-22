package com.weikbest.pro.saas.merchat.order.module.dto;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value = "OrderBatchDeliverDTO对象", description = "订单批量发货记录表")
public class OrderBatchDeliverDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "关联商户ID不为空!")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "关联商户ID", required = true)
    private Long businessId;

    @NotNull(message = "关联店铺ID不为空!")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "关联店铺ID", required = true)
    private Long shopId;

    @NotBlank(message = "操作类型 1-批量发货不为空!")
    @ApiModelProperty(value = "操作类型 1-批量发货", required = true)
    private String deliverType;

    @ApiModelProperty(value = "操作时间 yyyy-MM-dd HH:mm:ss", required = true)
    private Date recordTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "操作人不为空!")
    @ApiModelProperty(value = "操作人", required = true)
    private Long operator;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "操作人类型 0-平台用户 1-商家端用户 2-App端用户不为空!")
    @ApiModelProperty(value = "操作人类型 0-平台用户 1-商家端用户 2-App端用户", required = true)
    private String operatorType;

    @ApiModelProperty("excel导入文件名称")
    private String operateExcelName;

    @NotBlank(message = "excel导入文件不为空!")
    @ApiModelProperty(value = "excel导入文件", required = true)
    private String operateExcelUrl;

    @ApiModelProperty(value = "操作订单数", required = true)
    private Integer totalNum;

    @ApiModelProperty(value = "操作成功数", required = true)
    private Integer successNum;

    @ApiModelProperty(value = "操作失败数", required = true)
    private Integer errorNum;


}