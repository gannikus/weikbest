package com.weikbest.pro.saas.merchat.prod.entity;

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
import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Supplier;

/**
 * <p>
 * 商品左滑设置拆分表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("t_mmdm_prod_left_slide")
@ApiModel(value = "ProdLeftSlide对象", description = "商品左滑设置拆分表")
public class ProdLeftSlide implements Serializable {

    public static final String ID = "id";
    public static final String SWITCH_TEXT = "switch_text";
    public static final String SWITCH_JUMP = "switch_jump";
    public static final String INDEX_JUMP = "index_jump";
    public static final String JOINPAGE_STATE = "joinpage_state";
    public static final String SWITCH_RIGHT = "switch_right";
    public static final String JOINPAGE_JUMP = "joinpage_jump";
    public static final String JOINPAGE_OPEN_URL = "joinpage_open_url";
    public static final String JOINPAGE_BANNER_URL = "joinpage_banner_url";
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    public static final String UPLOAD_GOODS_IMG = "upload_goods_img";
    public static final String BOTTOM_BANNER_IMG = "bottom_banner_img";
    public static final String PICTURE_FIRST_PICTURE = "picture_first_picture";
    public static final String BACKGROUND_COLOR = "background_color";
    public static final String VIRTUAL_KEY = "virtual_key";
    public static final String MARKETING_PAGE_SET = "marketing_page_set";
    public static final String PAGE_TYPE = "page_type";
    public static final String MLC_REFLOW_SET = "mlc_reflow_set";
    public static final String AD_LINKS_NAME = "ad_links_name";
    public static final String FULL_DISCOUNT_ON_OFF = "full_discount_on_off";
    public static final String FULL_DISCOUNT_MONEY = "full_discount_money";
    public static final String DEFAULT_PAYMENT_TYPE = "default_payment_type";
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @ApiModelProperty("左滑切换弹框文本")
    @TableField(value = "switch_text", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String switchText;
    @ApiModelProperty("左滑跳转 0-不跳转 1-跳转")
    @TableField(value = "switch_jump", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String switchJump;
    @ApiModelProperty("右上角返回图标 0-不开启- 1-开启")
    @TableField(value = "switch_right", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String switchRight;
    @ApiModelProperty("主页跳转 0-不跳转 1-跳转")
    @TableField(value = "index_jump", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String indexJump;
    @ApiModelProperty("集合页样式_0:不开启 1:文字样式 2:购物红包 3:提示样式 4:商场红包")
    @TableField(value = "joinpage_state", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String joinpageState;
    @ApiModelProperty("聚合页 0不开启- 1-开启")
    @TableField(value = "joinpage_jump", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String joinpageJump;
    @ApiModelProperty("聚合页弹窗图片链接")
    @TableField(value = "joinpage_open_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String joinpageOpenUrl;
    @ApiModelProperty("聚合页banner图片链接")
    @TableField(value = "joinpage_banner_url", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String joinpageBannerUrl;
    @ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date gmtModified;

    @ApiModelProperty("上传商品图")
    @TableField(value = "upload_goods_img", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String uploadGoodsImg;

    @ApiModelProperty("底部banner图")
    @TableField(value = "bottom_banner_img", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String bottomBannerImg;

    @ApiModelProperty("主图首页")
    @TableField(value = "picture_first_picture", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String pictureFirstPicture;

    @ApiModelProperty("页面背景色")
    @TableField(value = "background_color", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String backgroundColor;

    @ApiModelProperty("虚拟按键_选项 0: 关闭 , 1: 开启")
    @TableField(value = "virtual_key", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Integer virtualKey;

    @ApiModelProperty("营销页设置_0:关闭 , 1:开启")
    @TableField(value = "marketing_page_set", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Integer marketingPageSet;

    @ApiModelProperty("页面类型_0: 集合页 , 1: 单品页")
    @TableField(value = "page_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Integer pageType;

    @ApiModelProperty("多级回流设置_0:关闭 , 1:开启")
    @TableField(value = "mlc_reflow_set", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Integer mlcReflowSet;

    @ApiModelProperty("广告链接创建时间")
    @TableField(value = "ad_links_creation_time")
    private Date adLinksCreationTime;

    @ApiModelProperty("广告链接名称")
    @TableField(value = "ad_links_name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String adLinksName;

    @ApiModelProperty("满减开关是否开启_0-关闭，1-开启")
    @TableField(value = "full_discount_on_off", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Integer fullDiscountOnOff;

    @ApiModelProperty("满多少金额可以减")
    @TableField("full_discount_money")
    private BigDecimal fullDiscountMoney;

    @ApiModelProperty("默认支付类型_0: 微信支付 , 1: 货到付款")
    @TableField(value = "default_payment_type", insertStrategy = FieldStrategy.NOT_EMPTY)
    private Integer defaultPaymentType;
}
