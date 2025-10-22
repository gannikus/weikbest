package com.weikbest.pro.saas.merchat.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.marketing.FavorCouponsUseResult;
import com.github.binarywang.wxpay.bean.marketing.UseNotifyData;
import com.github.binarywang.wxpay.bean.notify.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingBusiFavorService;
import com.github.binarywang.wxpay.service.MarketingFavorService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import com.weikbest.pro.saas.merchat.order.service.OrderPayRecordService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/15
 */
@Slf4j
@Api(tags = {"notify::微信回调消息处理类"})
@RestController
@RequestMapping("/merchat-wx-notify")
public class WxNotifyController {

    private static final Gson GSON = new GsonBuilder().create();

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private OrderPayRecordService orderPayRecordService;



//    @PassToken
//    @SaveLog("支付回调通知处理")
//    @ApiOperation(value = "支付回调通知处理")
//    @PostMapping("/notify/order")
//    public String parseOrderNotifyResult(HttpServletRequest request, HttpServletResponse response,
//                                         @ApiParam(name = "requestBody", value = "微信支付回调内容", required = true)
//                                         @RequestBody String requestBody) {
//        log.info("微信支付回调：{}", requestBody);
//
//        // 平台证书序列号字段
//        String wechatpaySerial = request.getHeader("Wechatpay-Serial");
//        String wechatpayTimestamp = request.getHeader("Wechatpay-Timestamp");
//        String wechatpayNonce = request.getHeader("Wechatpay-Nonce");
//        String wechatpaySignature = request.getHeader("Wechatpay-Signature");
//        SignatureHeader signatureHeader = new SignatureHeader();
//        signatureHeader.setSignature(wechatpaySignature);
//        signatureHeader.setNonce(wechatpayNonce);
//        signatureHeader.setSerial(wechatpaySerial);
//        signatureHeader.setTimeStamp(wechatpayTimestamp);
//
//        // 获取微信支付服务
//        WxPayService wxPayService = shopThirdService.findWxPayServiceByWxPlatformSerialNo(wechatpaySerial);
//        try {
//            WxPayOrderNotifyV3Result result = wxPayService.parseOrderNotifyV3Result(requestBody, signatureHeader);
//            // 解析退款回调结果
//
//        } catch (Exception e) {
//            log.error(String.format("微信支付回调通知处理失败：%s", e.getMessage()), e);
//            // 通知应答：HTTP应答状态码需返回5XX或4XX，同时需返回应答报文
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            return WxPayNotifyV3Response.fail(e.getMessage());
//        }
//
//        return WxPayNotifyV3Response.success("成功");
//    }


    @PassToken
    @ApiOperation(value = "退款回调通知处理")
    @PostMapping("/notify/refund")
    public String parseRefundNotifyResult(HttpServletRequest request, HttpServletResponse response,
                                          @ApiParam(name = "requestBody", value = "微信退款回调内容", required = true)
                                          @RequestBody String requestBody) {
        log.info("微信退款回调：{}", requestBody);

        // 平台证书序列号字段
        String wechatpaySerial = request.getHeader("Wechatpay-Serial");
        String wechatpayTimestamp = request.getHeader("Wechatpay-Timestamp");
        String wechatpayNonce = request.getHeader("Wechatpay-Nonce");
        String wechatpaySignature = request.getHeader("Wechatpay-Signature");
        SignatureHeader signatureHeader = new SignatureHeader();
        signatureHeader.setSignature(wechatpaySignature);
        signatureHeader.setNonce(wechatpayNonce);
        signatureHeader.setSerial(wechatpaySerial);
        signatureHeader.setTimeStamp(wechatpayTimestamp);

        // 获取微信支付服务
        WxPayService wxPayService = shopThirdService.findWxPayServiceByWxPlatformSerialNo(wechatpaySerial);
        try {
            // 解析退款回调结果
            WxPayRefundNotifyV3Result result = wxPayService.parseRefundNotifyV3Result(requestBody, signatureHeader);
            orderPayRecordService.refundNotify(result);
        } catch (Exception e) {
            log.error(String.format("退款回调通知处理失败：%s", e.getMessage()), e);
            // 通知应答：HTTP应答状态码需返回5XX或4XX，同时需返回应答报文
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return WxPayNotifyV3Response.fail(e.getMessage());
        }

        return WxPayNotifyV3Response.success("成功");
    }


}
