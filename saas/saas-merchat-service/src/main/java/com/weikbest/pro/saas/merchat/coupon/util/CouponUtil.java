package com.weikbest.pro.saas.merchat.coupon.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.binarywang.wxpay.bean.marketing.BusiFavorStocksCreateRequest;
import com.github.binarywang.wxpay.bean.marketing.busifavor.*;
import com.github.binarywang.wxpay.bean.marketing.enums.StockTypeEnum;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.third.wx.util.WxPayAmountConvertUtil;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.ProdStandard;
import com.weikbest.pro.saas.sys.param.entity.Site;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/7
 */
public class CouponUtil {

    /**
     * 构建微信商家券请求
     *
     * @param wxPayService
     * @param coupon
     * @param outRequestNo
     * @param prodStandard
     * @param site
     * @return
     */
    public static BusiFavorStocksCreateRequest buildBusiFavorStocksCreateRequest(WxPayService wxPayService, Coupon coupon, String outRequestNo, ProdStandard prodStandard, Site site) {
        // 构建微信商家券请求
        BusiFavorStocksCreateRequest request = new BusiFavorStocksCreateRequest();
        request.setStockName(coupon.getName());
        request.setBelongMerchant(wxPayService.getConfig().getMchId());
        if (StrUtil.isNotBlank(coupon.getTips())) {
            request.setComment(coupon.getTips());
        }
        request.setGoodsName(CouponUtil.buildGoodsName());
        request.setStockType(StockTypeEnum.NORMAL);
        request.setCouponCodeMode(prodStandard.getCouponCodeMode());
        request.setOutRequestNo(outRequestNo);
        // 核销规则
        CouponUseRule couponUseRule = new CouponUseRule();
        couponUseRule.setUseMethod(prodStandard.getUseMethod());
        couponUseRule.setMiniProgramsAppid(coupon.getAppId());
        couponUseRule.setMiniProgramsPath(buildMiniProgramsPath(coupon, prodStandard));
        // 券生效时间
        CouponAvailableTime couponAvailableTime = new CouponAvailableTime();
        couponAvailableTime.setAvailableBeginTime(DateUtil.format(coupon.getGetStartTime(), WeikbestConstant.WX_COUPON_DATETIME_FORMAT));
        couponAvailableTime.setAvailableEndTime(DateUtil.format(coupon.getGetEndTime(), WeikbestConstant.WX_COUPON_DATETIME_FORMAT));
        couponAvailableTime.setAvailableDayAfterReceive(coupon.getValidityDay());
        if(coupon.getDelayEnableDay() != null && coupon.getDelayEnableDay() > 0) {
            couponAvailableTime.setWaitDaysAfterReceive(coupon.getDelayEnableDay());
        }
        couponUseRule.setCouponAvailableTime(couponAvailableTime);
        // 固定面额满减券使用规则
        FixedNormalCoupon fixedNormalCoupon = buildFixedNormalCoupon(coupon);
        couponUseRule.setFixedNormalCoupon(fixedNormalCoupon);
        request.setCouponUseRule(couponUseRule);
        // 券发放相关规则
        StockSendRule stockSendRule = new StockSendRule();
        stockSendRule.setMaxCoupons(coupon.getCouponNum());
        stockSendRule.setMaxCouponsPerUser(coupon.getRestrictCount());
        stockSendRule.setMaxCouponsByDay(coupon.getCouponNum());
        stockSendRule.setNaturalPersonLimit(Boolean.FALSE);
        stockSendRule.setPreventApiAbuse(Boolean.FALSE);
        request.setStockSendRule(stockSendRule);
        // 自定义入口
        CustomEntrance customEntrance = new CustomEntrance();
        // 这里是公众号ID
        if(StrUtil.isNotBlank(site.getWxGzhAppId())) {
            customEntrance.setAppId(site.getWxGzhAppId());
        }
        // 小程序入口
        CustomEntrance.MiniProgramsInfo miniProgramsInfo = new CustomEntrance.MiniProgramsInfo();
        miniProgramsInfo.setMiniProgramsAppid(coupon.getAppId());
        miniProgramsInfo.setMiniProgramsPath(buildMiniProgramsPath(coupon, prodStandard));
        miniProgramsInfo.setEntranceWords("欢迎选购");
        miniProgramsInfo.setGuidingWords("获取更多优惠");
        customEntrance.setMiniProgramsInfo(miniProgramsInfo);
        request.setCustomEntrance(customEntrance);
        // 样式信息
        DisplayPatternInfo displayPatternInfo = buildDisplayPatternInfo(coupon, prodStandard);
        request.setDisplayPatternInfo(displayPatternInfo);
        // 事件通知配置
        NotifyConfig notifyConfig = new NotifyConfig();
        notifyConfig.setNotifyAppId(coupon.getAppId());
        request.setNotifyConfig(notifyConfig);
        return request;
    }


    /**
     * 构建微信商家券请求
     *
     * @param coupon
     * @param outRequestNo
     * @param prodStandard
     * @param site
     * @return
     */
    public static BusiFavorStocksCreateRequest buildUpdateBusiFavorStocksCreateRequest(Coupon coupon, String outRequestNo, ProdStandard prodStandard, Site site) {
        // 构建微信商家券请求
        BusiFavorStocksCreateRequest request = new BusiFavorStocksCreateRequest();
        if (StrUtil.isNotBlank(coupon.getTips())) {
            request.setComment(coupon.getTips());
        }
        request.setGoodsName(CouponUtil.buildGoodsName());
        request.setOutRequestNo(outRequestNo);
        // 核销规则
        CouponUseRule couponUseRule = new CouponUseRule();
        couponUseRule.setUseMethod(prodStandard.getUseMethod());
        couponUseRule.setMiniProgramsAppid(coupon.getAppId());
        couponUseRule.setMiniProgramsPath(buildMiniProgramsPath(coupon, prodStandard));

        // 券发放相关规则
        StockSendRule stockSendRule = new StockSendRule();
        stockSendRule.setPreventApiAbuse(Boolean.FALSE);
        request.setStockSendRule(stockSendRule);
        // 自定义入口
        CustomEntrance customEntrance = new CustomEntrance();
        // 这里是公众号ID
        if(StrUtil.isNotBlank(site.getWxGzhAppId())) {
            customEntrance.setAppId(site.getWxGzhAppId());
        }
        // 小程序入口
        CustomEntrance.MiniProgramsInfo miniProgramsInfo = new CustomEntrance.MiniProgramsInfo();
        miniProgramsInfo.setMiniProgramsAppid(coupon.getAppId());
        miniProgramsInfo.setMiniProgramsPath(buildMiniProgramsPath(coupon, prodStandard));
        miniProgramsInfo.setEntranceWords("欢迎选购");
        miniProgramsInfo.setGuidingWords("获取更多优惠");
        customEntrance.setMiniProgramsInfo(miniProgramsInfo);
        request.setCustomEntrance(customEntrance);
        // 样式信息
        DisplayPatternInfo displayPatternInfo = buildDisplayPatternInfo(coupon, prodStandard);
        request.setDisplayPatternInfo(displayPatternInfo);
        // 事件通知配置
        NotifyConfig notifyConfig = new NotifyConfig();
        notifyConfig.setNotifyAppId(coupon.getAppId());
        request.setNotifyConfig(notifyConfig);
        return request;
    }

    /**
     * 生成商家小程序path
     *
     * @param coupon
     * @param prodStandard
     * @return
     */
    private static String buildMiniProgramsPath(Coupon coupon, ProdStandard prodStandard) {
        String couponType = coupon.getCouponType();
        if (StrUtil.equals(couponType, DictConstant.CouponType.TYPE_2.getCode())) {
            // 回流优惠券
           return coupon.getCouponUseUrl();
        }
        return StrUtil.isNotBlank(coupon.getCouponUseUrl()) ? coupon.getCouponUseUrl() : prodStandard.getMiniProgramsPath();
    }

    /**
     * 生成满减券信息
     *
     * @param coupon
     * @return
     */
    private static FixedNormalCoupon buildFixedNormalCoupon(Coupon coupon) {
        FixedNormalCoupon fixedNormalCoupon = new FixedNormalCoupon();
        fixedNormalCoupon.setDiscountAmount(WxPayAmountConvertUtil.multiplyConvert(coupon.getDiscountAmount()));
        if (StrUtil.equals(coupon.getCouponUseType(), DictConstant.CouponUseType.none.getCode())) {
            // 默认放一个0.01元
            fixedNormalCoupon.setTransactionMinimum(WeikbestConstant.ONE_INT);
        } else {
            fixedNormalCoupon.setTransactionMinimum(WxPayAmountConvertUtil.multiplyConvert(coupon.getCouponUsePrice()));
        }
        return fixedNormalCoupon;
    }

    /**
     * 生成样式信息
     *
     * @param coupon
     * @param prodStandard
     * @return
     */
    private static DisplayPatternInfo buildDisplayPatternInfo(Coupon coupon, ProdStandard prodStandard) {
        DisplayPatternInfo displayPatternInfo = new DisplayPatternInfo();
        displayPatternInfo.setDescription("无门槛使用，部分商品可用");
        String couponType = coupon.getCouponType();
        if (StrUtil.equals(couponType, DictConstant.CouponType.TYPE_2.getCode())) {
            // 回流优惠券（回流优惠券默认也用平台优惠券的默认图片）
            displayPatternInfo.setCouponImageUrl(StrUtil.isEmpty(coupon.getCouponImageUrl()) ?  prodStandard.getPlatformCouponImageUrl(): coupon.getCouponImageUrl());
            displayPatternInfo.setMerchantLogoUrl(prodStandard.getPlatformMerchatLogoUrl());
            displayPatternInfo.setBackgroundColor(prodStandard.getRefluxBackgroundColor());
        } else if (StrUtil.equals(couponType, DictConstant.CouponType.TYPE_1.getCode())) {
            // 立减优惠券
            displayPatternInfo.setCouponImageUrl(StrUtil.isEmpty(coupon.getCouponImageUrl()) ? prodStandard.getPromptlyCouponImageUrl() : coupon.getCouponImageUrl());
            displayPatternInfo.setMerchantLogoUrl(prodStandard.getPromptlyMerchatLogoUrl());
            displayPatternInfo.setBackgroundColor(prodStandard.getPromptlyBackgroundColor());
        } else if (StrUtil.equals(couponType, DictConstant.CouponType.TYPE_3.getCode())) {
            // 平台优惠券
            displayPatternInfo.setCouponImageUrl(StrUtil.isEmpty(coupon.getCouponImageUrl()) ? prodStandard.getPlatformCouponImageUrl() : coupon.getCouponImageUrl());
            displayPatternInfo.setMerchantLogoUrl(prodStandard.getPlatformMerchatLogoUrl());
            displayPatternInfo.setBackgroundColor(prodStandard.getPlatformBackgroundColor());
        }

        return displayPatternInfo;
    }

    /**
     * 设置商品名称
     *
     * @return
     */
    public static String buildGoodsName() {
//        if (StrUtil.equals(coupon.getCouponUseType(), DictConstant.CouponUseType.none.getCode())) {
//            goodsName += "无门槛使用,";
//            goodsName += Memory.getDict(DictConstant.CouponProdType.getDictTypeNumber(), coupon.getCouponProdType()) + "可用";
//        } else {
//            goodsName += String.format("满%s元可用", coupon.getCouponUsePrice().toString());
//        }

        return "使用本店铺商品";
    }

}
