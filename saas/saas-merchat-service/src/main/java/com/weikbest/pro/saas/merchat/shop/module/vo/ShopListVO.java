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
 * @since 2022-09-16
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ShopListVO对象", description = "商户店铺表列表")
public class ShopListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
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

    @ApiModelProperty("联系人电话")
    private String contact;

    @ApiModelProperty("企业名称（工商注册全名）")
    private String companyName;

    @ApiModelProperty("企业地址")
    private String companyAddress;

    @ApiModelProperty("企业人数")
    private Integer companyStaff;

    @ApiModelProperty("店铺类型 0-非自营店铺 1-自营店铺")
    private String shopType;

    @ApiModelProperty("所属行业")
    private String tradeType;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("更新人")
    private Long modifier;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    @ApiModelProperty("微信支付-商户号")
    private String wxPayMchId;
    @ApiModelProperty("微信支付-APIv3 证书序列号值")
    private String wxPayApiCertSerialNo;
    @ApiModelProperty("微信商户号证书序列号生效时间")
    private Date wxPayMchEffectiveTime;
    @ApiModelProperty("微信商户号证书序列号失效时间")
    private Date wxPayMchExpireTime;
    @ApiModelProperty("是否添加分账接收方 0-否1-是")
    private String isAddReceive;
    @ApiModelProperty("微信平台证书序列号生效时间")
    private Date wxPlatformEffectiveTime;
    @ApiModelProperty("微信平台证书序列号失效时间")
    private Date wxPlatformExpireTime;

    @ApiModelProperty("商户名称")
    private String businessName;
    @ApiModelProperty("商户类别 1-普通商户 2-品牌商户 3-特约商户")
    private String businessType;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商家主账号ID")
    private String busiUserId;
    @ApiModelProperty("商家手机号")
    private String busiPhone;
    @ApiModelProperty("商家姓名")
    private String busiUserName;

    @ApiModelProperty("是否能控制广告回传开关; 0-关闭，1-开启")
    private String isControlAdCallback;

    @ApiModelProperty("手动回传开关； 0-关闭，1-开启")
    private String switchManualAdCallback;

}
