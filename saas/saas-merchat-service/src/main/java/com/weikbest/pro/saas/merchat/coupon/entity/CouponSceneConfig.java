package com.weikbest.pro.saas.merchat.coupon.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * 优惠券使用场景配置表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-15
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_coupon_scene_config")
@ApiModel(value = "CouponSceneConfig对象", description = "优惠券使用场景配置表")
public class CouponSceneConfig implements Serializable {

    public static final String ID = "id";
    public static final String COUPON_SCENE_TYPE = "coupon_scene_type";
    public static final String PLATFORM_COUPON_RECEIVE_OPEN_URL = "platform_coupon_receive_open_url";
    public static final String PLATFORM_COUPON_RECEIVE_BANNER_URL = "platform_coupon_receive_banner_url";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("场景类型 1-广告进入绑定回流优惠券领取 2-新用户进入小程序点击领取")
    @TableField(value = "coupon_scene_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String couponSceneType;
    @ApiModelProperty("平台优惠券领取弹窗")
    @TableField(value = "platform_coupon_receive_open_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String platformCouponReceiveOpenUrl;
    @ApiModelProperty("平台券领取banner图")
    @TableField(value = "platform_coupon_receive_banner_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String platformCouponReceiveBannerUrl;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;
}
