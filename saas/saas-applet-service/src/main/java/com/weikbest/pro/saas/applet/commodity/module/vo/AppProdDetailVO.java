package com.weikbest.pro.saas.applet.commodity.module.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.weikbest.pro.saas.merchat.prod.entity.ProdReturn;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdServiceCommitmentDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdPurchaseRestrictionsVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ProdVO对象", description = "商品基本信息表")
public class AppProdDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品短标题")
    private String tips;

    @ApiModelProperty("展示销量")
    private Integer sales;

    @ApiModelProperty("商品详情页轮播图")
    private List<String> mainimgs;

    @ApiModelProperty("商品详细图")
    private List<String> prodDetailImgs;

    @ApiModelProperty("套餐商品销售金额（最低）")
    private BigDecimal comboMinPrice;

    @ApiModelProperty("套餐商品划线价（最低）")
    private BigDecimal comboMinStandardPrice;

    @ApiModelProperty("套餐商品实付金额（最低）")
    private BigDecimal comboMinPaidInAmount;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联店铺ID")
    private Long shopId;

    @ApiModelProperty("官方补贴金额")
    private BigDecimal subsidyPrice;

    @ApiModelProperty("是否开启支付失败弹框 0-否 1-是")
    private String isFailPayHint;

    @ApiModelProperty("支付失败默认弹窗样式")
    private String payFailThemeUrl;

    @ApiModelProperty("支付优惠金额")
    private BigDecimal discountPrice;

    @ApiModelProperty("隐私协议 0-不展示 1-展示")
    private String safeguard;

    @ApiModelProperty("商品套餐信息")
    private List<AppProdComboVO> appProdComboVOS;

    @ApiModelProperty("商品样式信息")
    private AppProdThemeVO appProdThemeVO;

    @ApiModelProperty("运费类型 1-统一运费 2-运费模板")
    private String feightType;

    @ApiModelProperty("运费金额（元）")
    private BigDecimal feightAmount;

    @ApiModelProperty("运费模块实体信息")
    private AppFeightTemplateVO appFeightTemplateVO;

    @ApiModelProperty("商户类别 1-普通商户 2-品牌商户 3-特约商户")
    private String businessType;

    @ApiModelProperty("商品返回页信息")
    private List<ProdReturn> returns;

    @ApiModelProperty("聚合页弹窗图片链接")
    private String joinpageOpenUrl;

    @ApiModelProperty("页面banner_图片")
    private String joinpageBannerUrl;

    @ApiModelProperty("商品服务承诺")
    private ProdServiceCommitmentDTO prodServiceCommitmentDTO;

    @ApiModelProperty("虚拟按键_选项 0: 关闭 , 1: 开启")
    private Integer virtualKey;

    @ApiModelProperty("商品类别 1- 自营商品 ，2-广告商品")
    private Integer goodsType;

    @ApiModelProperty("当前返回次数")
    private Integer click;

    @ApiModelProperty("是否为第一次进入 0:是第一次 , 不是0 不是第一次")
    private Integer isFirstEntry;

    @ApiModelProperty("回显多规格_key是规格名对应规格值和图片")
    private Map<String,List<EchoMuchSize>> echoMuchSizes;

    @ApiModelProperty("套餐类型 1:普通套餐 , 2:多规格套餐")
    private Integer setMealType;

    @ApiModelProperty("最低价规格")
    private String minPriceSpec;

    @ApiModelProperty("是否有返回页")
    private Integer isThereAReturnPage;

    @ApiModelProperty("是否开启到付验证开关 0-关闭，1-开启")
    private Integer isOpenCashOnDeliverySms;

    @ApiModelProperty("支付类型list")
    private List<Map<String,String>> payTypeList;

    @ApiModelProperty("满减开关是否开启_0-关闭，1-开启")
    private Integer fullDiscountOnOff;

    @ApiModelProperty("满多少金额可以减")
    private BigDecimal fullDiscountMoney;

    @ApiModelProperty("默认支付类型_0: 微信支付 , 1: 货到付款")
    private Integer DefaultPaymentType;

    @ApiModelProperty("是否开启限购 0-关闭，1-开启")
    private String isOpenOrderLimit;

    @ApiModelProperty("客服开关，0-关闭，1-开启")
    private String customerServiceSwitch;

    @ApiModelProperty("prodPurchaseRestrictionsVo商品限购配置类")
    private ProdPurchaseRestrictionsVo prodPurchaseRestrictionsVo;

    @Data
    @ApiModel(value = "echoMuchSize对象", description = "多规格回显信息")
    public static class EchoMuchSize{

        @ApiModelProperty("规格值")
        private String value;

        @ApiModelProperty("配图")
        private String img;

    }
}
