package com.weikbest.pro.saas.merchat.prod.module.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Wang
 */
@Data
@ToString
@Accessors(chain = true)
@ApiModel(value = "AdLinksDTO对象", description = "广告链接新增接受实体")
public class AdLinksDTO {

    @NotNull(message = "商品ID不为空!")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品ID")
    private Long prodId;

    @ApiModelProperty("广告链接(页面)名称")
    private String adLinksName;

    @ApiModelProperty("立即购买按钮(原版有)")
    private String buyBtnTitle;

    @ApiModelProperty("悬浮按钮文字(原版有)")
    private String floatBtnTitle;

    @ApiModelProperty("优惠倒计时是否开启_0: 关闭 , 1: 开启(原版有)")
    private String countdownOffers;

    @ApiModelProperty("倒计时文字(原版有)")
    private String countdownTitle;

    @ApiModelProperty("倒计时时间(原版有)")
    private Integer countdownMinute;

    @ApiModelProperty("关联广告推广账号Id")
    private Long advAccountId;

    @ApiModelProperty("支付成功回传 0-不回传 1-回传")
    private String successPayBack;

    @ApiModelProperty(value = "回传比率", required = true)
    private BigDecimal backRatio;

    @ApiModelProperty("营销页设置_0: 关闭 , 1: 开启")
    private Integer marketingPageSet;

    @ApiModelProperty("页面类型_0: 集合页 , 1: 单品页(现用来传二次挽回动漫的开启与关闭)")
    private Integer pageType;

    @ApiModelProperty("首次挽回优惠_金额")
    private BigDecimal backCost;

    @ApiModelProperty("二次挽回动画_金额")
    private BigDecimal backSecondCost;

    @ApiModelProperty("营销页弹窗_图片")
    private String joinpageOpenUrl;

    @ApiModelProperty("落地页弹窗_选项 0:关闭 , 1:开启")
    private String joinpageJump;

    @ApiModelProperty("页面banner_图片")
    private String joinpageBannerUrl;

    @ApiModelProperty("上传商品图_图片")
    private String  uploadGoodsImg;

    @ApiModelProperty("底部banner_图片")
    private String  bottomBannerImg;

    @ApiModelProperty("主图首图_图片")
    private String pictureFirstPicture;

    @ApiModelProperty("页面背景色")
    private String backgroundColor;

    @ApiModelProperty("多级回流设置_0:关闭 , 1:开启")
    private Integer mlcReflowSet;

    @ApiModelProperty("多级回流设置")
    private List<MlcReflowSet> mlcReflowSets;

    /*@ApiModelProperty("回流优惠劵_0:关闭 , 1:开启")
    private Integer backflowCouponSet;*/

    /*@ApiModelProperty("商品绑定优惠券数据集合")
    private List<ProdCouponRelateDTO> prodCouponRelateDTOList;

    @ApiModelProperty(value = "领劵弹窗图片")
    private String couponOpenImg;

    @ApiModelProperty(value = "主动核销 0-不主动 1-主动", required = true)
    private String chargeOff;*/

    /*@ApiModelProperty(value = "支付失败优惠_0:关闭 , 1:开启")
    private Integer paymentFailureDiscounts; //原表有

    @ApiModelProperty(value = "首次失败优惠_金额")
    private BigDecimal failCost;

    @ApiModelProperty(value = "二次失败优惠_选项 0: 关闭 , 1: 开启")
    private Integer failCostStatus;*/

    @ApiModelProperty(value = "虚拟按键_选项 0: 关闭 , 1: 开启")
    private Integer virtualKey;

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;

    @ApiModelProperty("支付类型 ,1-微信支付，2-货到付款")
    private String payType;

    @ApiModelProperty("是否开启到付验证开关 0-关闭，1-开启")
    private Integer isOpenCashOnDeliverySms;

    @ApiModelProperty("支付类型list")
    private List<Map<String,String>> payTypeList;

    @ApiModelProperty("满减开关是否开启_0-关闭，1-开启")
    private Integer fullDiscountOnOff;

    @ApiModelProperty("满多少金额可以减")
    private BigDecimal fullDiscountMoney;

    @ApiModelProperty("默认支付类型_0: 微信支付 , 1: 货到付款")
    public Integer defaultPaymentType;

    @ApiModelProperty("新增或者编辑传入_0:新增 , 1:编辑")
    public Integer addOrUpdate;

    @ApiModelProperty("客服开关，0-关闭，1-开启")
    private String customerServiceSwitch;

    @Data
    @ApiModel(value = "MlcReflowSet", description = "多级回流设置")
    public static class MlcReflowSet{

        @ApiModelProperty("回流名称_1:涨红包活动 , 2:惊喜福气卡 , 3:幸运大转盘 , 4:手气红包 , 5:摇摇乐")
        private Integer reflowType;

        @ApiModelProperty("回流金额")
        private BigDecimal reflowSum;

        @ApiModelProperty("回流是否开启_0:关闭,1:开启")
        private Integer openOrNot;

        @ApiModelProperty("排序")
        private Integer sort;

    }

}
