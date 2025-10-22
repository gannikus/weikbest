package com.weikbest.pro.saas.merchat.shop.module.qo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel(value = "ShopQO对象", description = "商户店铺表")
public class ShopQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商户ID")
    private Long businessId;

    @ApiModelProperty("店铺编码")
    private String number;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("是否品牌店铺 0-否 1-是")
    private String isBrand;

    @ApiModelProperty("联系人电话")
    private String contact;

    @ApiModelProperty("企业名称（工商注册全名）")
    private String companyName;

    @ApiModelProperty("店铺类型 0-非自营店铺 1-自营店铺")
    private String shopType;

    @ApiModelProperty("所属行业")
    private String tradeType;

    @ApiModelProperty("数据状态 0-禁用 1-可用")
    private String dataStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty("创建开始时间 yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty("创建结束时间 yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty("微信商户号证书序列号失效时间 yyyy-MM-dd HH:mm:ss")
    private Date wxPayMchExpireTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @ApiModelProperty("微信平台证书序列号失效时间 yyyy-MM-dd HH:mm:ss")
    private Date wxPlatformExpireTime;

    @ApiModelProperty("微信支付-商户号")
    private String wxPayMchId;

    @ApiModelProperty("商户类别 1-普通商户 2-品牌商户 3-特约商户")
    private String businessType;

    @ApiModelProperty("商家账号")
    private String busiUserId;
    @ApiModelProperty("商家手机号")
    private String busiPhone;
    @ApiModelProperty("商家姓名")
    private String busiUserName;
}