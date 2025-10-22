package com.weikbest.pro.saas.merchat.complaint.module.dto;

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
import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/22
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "OrderComplaintBusinessConfirmDTO对象", description = "商户处理投诉实体")
public class OrderComplaintBusinessConfirmDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "投诉状态不为空!")
    @ApiModelProperty(value = "投诉状态 100-商家同意和解 101-商家不同意和解", required = true)
    private String complaintStatus;

    @ApiModelProperty(value = "沟通内容")
    private String complaintContent;

    @ApiModelProperty("上传凭证")
    private List<String> imgPathList;
}
