package com.weikbest.pro.saas.merchat.shop.module.vo;

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
 * 商户店铺表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopVO对象", description = "商户店铺表")
public class ShopVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("店铺ID")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @ApiModelProperty("店铺编码")
    private String number;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("店铺logo")
    private String logo;

    @ApiModelProperty("是否品牌店铺 0-否 1-是")
    private String isBrand;

    @ApiModelProperty("联系人")
    private String contact;

    @ApiModelProperty("企业名称")
    private String companyName;

    @ApiModelProperty("企业地址")
    private String companyAddress;

    @ApiModelProperty("企业人数")
    private Integer companyStaff;

    @ApiModelProperty("营业执照")
    private String businessListence;

    @ApiModelProperty("所属行业")
    private String tradeType;

    @ApiModelProperty("服务说明")
    private String serviceIntro;

    @ApiModelProperty("备注")
    private String description;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @ApiModelProperty("是否能控制广告回传开关; 0-关闭，1-开启")
    private String isControlAdCallback;

    @ApiModelProperty("手动回传开关； 0-关闭，1-开启")
    private String switchManualAdCallback;
}
