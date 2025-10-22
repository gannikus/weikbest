package com.weikbest.pro.saas.merchat.coupon;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.profitsharingV3.*;
import com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayRefundV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.common.third.wx.util.WxPayAmountConvertUtil;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopPromptlyCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopRefluxCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.common.constant.ConfigConstant;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.service.PlatformCouponService;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class ShopCouponTest {

    @Resource
    private CouponService couponService;

    @Resource
    private ShopThirdService shopThirdService;


    /**
     * 查询微信分账结果
     * @throws WxPayException
     */
    @Test
    public void getWxPayShare() throws WxPayException {
        WxPayService wxPayService = shopThirdService.findWxPayServiceByOrderNumber("20230712134255790072");
        /**
         * 退款
         * 1: shopThirdService.findWxPayServiceByOrderNumber() : 填写上单号
         * 2: request.setTransactionId() : 填写上流水号
         * 3: payAmount : 填写上退款金额
         */
        BigDecimal payAmount = new BigDecimal(2);
        String outrefundno = OrderUtil.getOutrefundno();
        WxPayRefundV3Request request = new WxPayRefundV3Request();
        request.setTransactionId("4200001873202307122205892561")
                .setOutRefundNo(outrefundno)
                .setReason("退款")
                .setAmount(new WxPayRefundV3Request.Amount().setCurrency("CNY").setTotal(WxPayAmountConvertUtil.multiplyConvert(payAmount))
                        .setRefund(WxPayAmountConvertUtil.multiplyConvert(payAmount)))
                .setNotifyUrl("https://saas.test.baoliangmall.com/AppNotifyUrl/wxPayNotifyUrl");
//        log.info("微信退款参数：订单号：{}，退款单号：{}，退款请求参数：{}",orderInfo.getNumber(),outrefundno, JSON.toJSON(request));
        WxPayRefundV3Result wxPayRefundV3Result = wxPayService.refundV3(request);

//        ProfitSharingUnfreezeRequest request = new ProfitSharingUnfreezeRequest();
//        request.setTransactionId("4200001850202305096068974610");
//        request.setOutOrderNo("2023050910114367006763683");
//        request.setDescription("解冻订单资金");
//
//        ProfitSharingUnfreezeResult response = wxPayService.getProfitSharingV3Service().profitSharingUnfreeze(request);
//        System.out.println(response);
//        //分账退回
//        ProfitSharingReturnRequest request = new ProfitSharingReturnRequest();
////        request.setOrderId("30002603502023050948564360802");
//        request.setOutReturnNo("202305110948493690217380");
//        request.setOutOrderNo("2023050910114367006763683");
//        request.setReturnMchid("1630082993");
//        request.setAmount(1480L);
//        request.setDescription("客户退款");
//        ProfitSharingReturnResult response = wxPayService.getProfitSharingV3Service().profitSharingReturn(request);
//        System.out.println(response);
        //查询分账结果
//        ProfitSharingResult result = wxPayService.getProfitSharingV3Service().getProfitSharingResult("2023050910114367006763683", "4200001850202305096068974610");
//        System.out.println(result);

        //查询分账回退结果
//        ProfitSharingReturnResult profitSharingReturnResult = wxPayService.getProfitSharingV3Service().getProfitSharingReturnResult("2023050910114367006763683", "202305101233411920164391");
//        System.out.println(profitSharingReturnResult);
        //查询剩余可分账金额
//        ProfitSharingUnsplitResult respone = wxPayService.getProfitSharingV3Service().getProfitSharingUnsplitAmount("4200001850202305096068974610");
//        System.out.println(respone);
    }

    @Test
    public void testInsertShopRefluxCoupon() {
        ShopRefluxCouponDTO shopRefluxCouponDTO = new ShopRefluxCouponDTO();
        shopRefluxCouponDTO.setShopId(1577680310580154368L)
                .setName("美背内衣立减20元！券后仅需59元！")
                .setTips("活动使用")
                .setEventStartTime(DateUtil.parse("2022-11-07 00:00:00"))
                .setEventEndTime(DateUtil.parse("2022-11-11 00:00:00"))
                .setCouponNum(100)
                .setProdId(1578409189112418304L)
                .setCouponUseType("1")
                .setCouponUsePrice(new BigDecimal("7900"))
                .setDiscountAmount(new BigDecimal("2000"))
                .setGetStartTime(DateUtil.parse("2022-11-07 00:00:00"))
                .setGetEndTime(DateUtil.parse("2022-11-11 00:00:00"))
                .setRestrictCount(4)
                .setDelayEnableDay(0)
                .setValidityDay(7)
                .setCouponUseUrl("pages/special/new-customers/index");

        boolean insert = couponService.insertShopRefluxCoupon(shopRefluxCouponDTO);
        System.out.println(insert);
    }


    @Test
    public void testInsertShopPromptlyCoupon() {
        ShopPromptlyCouponDTO shopPromptlyCouponDTO = new ShopPromptlyCouponDTO();
        shopPromptlyCouponDTO.setShopId(1577680310580154368L)
                .setName("美背内衣立减20元！券后仅需59元！")
                .setTips("活动使用")
                .setEventStartTime(DateUtil.parse("2022-11-07 00:00:00"))
                .setEventEndTime(DateUtil.parse("2022-11-11 00:00:00"))
                .setCouponNum(100)
                .setCouponProdType("1")
                .setCouponUseType("1")
                .setCouponUsePrice(new BigDecimal("7900"))
                .setDiscountAmount(new BigDecimal("2000"))
                .setAppId("wx7b8f18a7b5a3f6c8")
                .setGetStartTime(DateUtil.parse("2022-11-07 00:00:00"))
                .setGetEndTime(DateUtil.parse("2022-11-11 00:00:00"))
                .setRestrictCount(4)
                .setRestrictUserType("1")
                .setIsOpen("1")
                .setDelayEnableDay(0)
                .setValidityDay(7);

        boolean insert = couponService.insertShopPromptlyCoupon(shopPromptlyCouponDTO);
        System.out.println(insert);
    }

    @Test
    public void testGenrage(){
        Long aLong = GenerateIDUtil.nextId();
        System.out.println(aLong);
    }
}
