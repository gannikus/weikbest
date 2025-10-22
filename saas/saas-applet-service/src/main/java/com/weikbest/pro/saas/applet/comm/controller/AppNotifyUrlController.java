package com.weikbest.pro.saas.applet.comm.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.marketing.UseNotifyData;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyV3Response;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingFavorService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weikbest.pro.saas.applet.coupon.service.AppCouponService;
import com.weikbest.pro.saas.applet.order.service.AppOrderService;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.common.third.wx.pay.apiv3.AesUtil;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.param.entity.PayConfig;
import com.weikbest.pro.saas.sys.param.service.PayConfigService;
import com.weikbest.pro.saas.sys.param.service.TencentAdvScopeConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Map;


/**
 * <p>
 * 回调处理
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"notify::回调消息处理类"})
@RestController
@RequestMapping("/AppNotifyUrl")
public class AppNotifyUrlController {

    private static final Gson GSON = new GsonBuilder().create();

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private AppOrderService appOrderService;

    @Resource
    private AppCouponService appCouponService;

    @Resource
    private PayConfigService payConfigService;

    @Resource
    private TencentAdvScopeConfigService tencentAdvScopeConfigService;


    public static String read(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[512];
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return new String(baos.toByteArray(), 0, baos.size(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @ApiOperation(value = "订单支付回调消息", notes = "1，更新订单表状态为 待发货 2，新增订单支付记录表信息  3，新增订单状态变更记录信息 4，回传腾讯广告")
    @PostMapping(value = "/wxPayNotifyUrl")
    public JSONObject wxPayNotifyUrl(HttpServletRequest request, HttpServletResponse response) {

        log.info("进入回调方法wxPayNotifyUrl");


        JSONObject wxPayResult = new JSONObject();
        try {
            // 平台证书序列号字段
            String WechatpaySerial = request.getHeader("Wechatpay-Serial");
            log.info("Wechatpay-Serial------------------------" + WechatpaySerial);
            // 微信支付商戶号对应的APIV3密钥字段
            WxPayService wxPayService = shopThirdService.findWxPayServiceByWxPlatformSerialNo(WechatpaySerial);
            String privateApiV3Key = wxPayService.getConfig().getApiV3Key();

            String reqParams = read(request.getInputStream());
            log.info("-------支付结果:" + reqParams);
            JSONObject json = JSONObject.parseObject(reqParams);
            if (json.getString("event_type").equals("TRANSACTION.SUCCESS")) {
                log.info("-------支付成功");

                String ciphertext = json.getJSONObject("resource").getString("ciphertext");
                final String associated_data = json.getJSONObject("resource").getString("associated_data");
                final String nonce = json.getJSONObject("resource").getString("nonce");
                AesUtil aesUtil = new AesUtil(privateApiV3Key.getBytes());
                ciphertext = aesUtil.decryptToString(associated_data.getBytes(), nonce.getBytes(), ciphertext);
                log.info("-------ciphertext:" + ciphertext);
                // -------ciphertext:{"mchid":"1601457001","appid":"wx7b8f18a7b5a3f6c8","out_trade_no":"1217752501202209053233368026","transaction_id":"4200001596202209066064970025","trade_type":"JSAPI","trade_state":"SUCCESS","trade_state_desc":"支付成功","bank_type":"OTHERS","attach":"1601457001","success_time":"2022-09-06T22:41:30+08:00","payer":{"openid":"oqT1g5JM4IBmk7yxEI-lGTnhs4Zc"},"amount":{"total":1,"payer_total":1,"currency":"CNY","payer_currency":"CNY"}}
                log.info(JSONObject.parseObject(ciphertext).getString("out_trade_no"));
                // 订单号: 1217752501202209053233368026
                //如支付使用代金券的话，返回结果会多一组推广详情参数，ciphertext:{"mchid":"1631844213","appid":"wx7b8f18a7b5a3f6c8","out_trade_no":"202209211353102","transaction_id":"4200001622202209215015802387","trade_type":"JSAPI","trade_state":"SUCCESS","trade_state_desc":"支付成功","bank_type":"OTHERS","attach":"","success_time":"2022-09-21T11:35:16+08:00","payer":{"openid":"oqT1g5JM4IBmk7yxEI-lGTnhs4Zc"},"amount":{"total":300,"payer_total":200,"currency":"CNY","payer_currency":"CNY"},"promotion_detail":[{"coupon_id":"37695285082","name":"[懒小毛]1元代金券","scope":"GLOBAL","type":"NOCASH","amount":100,"stock_id":"16764277","wechatpay_contribute":0,"merchant_contribute":100,"other_contribute":0,"currency":"CNY"}]}

                //进入业务代码
                appOrderService.wxPayNotifyUrl(ciphertext, reqParams);

                // 通知应答：接收成功：HTTP应答状态码需返回200或204，无需返回应答报文。
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("支付回调失败：{}", e.getLocalizedMessage());
            // 通知应答：HTTP应答状态码需返回5XX或4XX，同时需返回应答报文
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            wxPayResult.put("code", "FAIL");
            wxPayResult.put("message", "失败");
            return wxPayResult;
        }

        return null;
    }


    @PassToken
    @ApiOperation(value = "微信领券事件回调地址")
    @PostMapping("/notify/busiFavor")
    public String parseBusiFavorNotifyResult(HttpServletRequest request, HttpServletResponse response,
                                             @ApiParam(name = "requestBody", value = "微信领券事件回调地址", required = true)
                                             @RequestBody String requestBody) {
        log.info("微信领券事件回调：{}", requestBody);

        // 平台证书序列号字段
        String wechatpaySerial = request.getHeader("Wechatpay-Serial");
        String wechatpayTimestamp = request.getHeader("Wechatpay-Timestamp");
        String wechatpayNonce = request.getHeader("Wechatpay-Nonce");
        String wechatpaySignature = request.getHeader("Wechatpay-Signature");
        com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader signatureHeader = new com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader();
        signatureHeader.setSigned(wechatpaySignature);
        signatureHeader.setNonce(wechatpayNonce);
        signatureHeader.setSerialNo(wechatpaySerial);
        signatureHeader.setTimeStamp(wechatpayTimestamp);

        // 获取微信支付服务
        WxPayService wxPayService = shopThirdService.findCouponWxPayServiceByWxPlatformSerialNo(wechatpaySerial);
        try {
            MarketingFavorService marketingFavorService = wxPayService.getMarketingFavorService();
            UseNotifyData useNotifyData = marketingFavorService.parseNotifyData(requestBody, signatureHeader);
            Map<String, Object> result = decryptNotifyDataResource(wxPayService, useNotifyData);
            // TODO 解析微信领券事件回调结果
            for (String ke:result.keySet()) {
                log.info("微信领券事件回调返回结果打印：{} key:"+ke +"------------val:"+result.get(ke));
            }
            appCouponService.notifybusiFavor(result);


        } catch (Exception e) {
            log.error(String.format("微信领券事件回调处理失败：%s", e.getMessage()), e);
            // 通知应答：HTTP应答状态码需返回5XX或4XX，同时需返回应答报文
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return WxPayNotifyV3Response.fail(e.getMessage());
        }

        return WxPayNotifyV3Response.success("成功");
    }

    /**
     * 解析微信领券事件回调结果,解密返回map
     *
     * @param wxPayService
     * @param data
     * @return
     * @throws WxPayException
     */
    public Map<String, Object> decryptNotifyDataResource(WxPayService wxPayService, UseNotifyData data) throws WxPayException {
        UseNotifyData.Resource resource = data.getResource();
        String cipherText = resource.getCipherText();
        String associatedData = resource.getAssociatedData();
        String nonce = resource.getNonce();
        String apiV3Key = wxPayService.getConfig().getApiV3Key();
        try {
            return GSON.fromJson(AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key), Map.class);
        } catch (GeneralSecurityException | IOException e) {
            try{
                PayConfig payConfig = payConfigService.getOne(new QueryWrapper<PayConfig>().eq(PayConfig.PAY_CONFIG_TYPE ,"3"));
                return GSON.fromJson(AesUtils.decryptToString(associatedData, nonce, cipherText, payConfig.getWxPayApiV3Key()), Map.class);
            }catch (GeneralSecurityException | IOException e1){
                throw new WxPayException("解析报文异常！", e1);
            }
        }
    }


    @PassToken
    @ApiOperation(value = "获取access_token", notes = "access_token：腾讯广告api全局鉴权参数，默认有效期5分钟\r\n"
            + "对接链接:https://developers.e.qq.com/docs/api/authorize/oauth/oauth_token?version=1.3")
    @GetMapping(value = "/tencent/ads/oauthToken")
    public JSONObject oauthToken(@ApiParam(value = "授权码") @RequestParam String authorization_code) {
        JSONObject result = new JSONObject();
        String accessToken = tencentAdvScopeConfigService.getAccessToken(authorization_code);
        result.put("result", accessToken);
        log.info("获取access_token结果--------" + result);
        return result;
    }

    @ApiOperation(value = "订单合单支付回调消息", notes = "1，更新订单表状态为 待发货 2，新增订单支付记录表信息  3，新增订单状态变更记录信息 4，回传腾讯广告")
    @PostMapping(value = "/wxCombinePayNotifyUrl")
    public JSONObject wxCombinePayNotifyUrl(HttpServletRequest request, HttpServletResponse response) {

        log.info("进入回调方法wxCombinePayNotifyUrl");


        JSONObject wxPayResult = new JSONObject();
        try {
            // 平台证书序列号字段
            String WechatpaySerial = request.getHeader("Wechatpay-Serial");
            log.info("Wechatpay-Serial------------------------" + WechatpaySerial);
            // 微信支付商戶号对应的APIV3密钥字段
            WxPayService wxPayService = shopThirdService.findWxPayServiceByWxPlatformSerialNo(WechatpaySerial);
            String privateApiV3Key = wxPayService.getConfig().getApiV3Key();

            String reqParams = read(request.getInputStream());
            log.info("-------支付结果:" + reqParams);
            JSONObject json = JSONObject.parseObject(reqParams);
            if (json.getString("event_type").equals("TRANSACTION.SUCCESS")) {
                log.info("-------支付成功");

                String ciphertext = json.getJSONObject("resource").getString("ciphertext");
                final String associated_data = json.getJSONObject("resource").getString("associated_data");
                final String nonce = json.getJSONObject("resource").getString("nonce");
                AesUtil aesUtil = new AesUtil(privateApiV3Key.getBytes());
                ciphertext = aesUtil.decryptToString(associated_data.getBytes(), nonce.getBytes(), ciphertext);
                log.info("wxCombinePay-------ciphertext:" + ciphertext);
                // -------ciphertext:{"mchid":"1601457001","appid":"wx7b8f18a7b5a3f6c8","out_trade_no":"1217752501202209053233368026","transaction_id":"4200001596202209066064970025","trade_type":"JSAPI","trade_state":"SUCCESS","trade_state_desc":"支付成功","bank_type":"OTHERS","attach":"1601457001","success_time":"2022-09-06T22:41:30+08:00","payer":{"openid":"oqT1g5JM4IBmk7yxEI-lGTnhs4Zc"},"amount":{"total":1,"payer_total":1,"currency":"CNY","payer_currency":"CNY"}}
                log.info(JSONObject.parseObject(ciphertext).getString("out_trade_no"));
                // 订单号: 1217752501202209053233368026
                //如支付使用代金券的话，返回结果会多一组推广详情参数，ciphertext:{"mchid":"1631844213","appid":"wx7b8f18a7b5a3f6c8","out_trade_no":"202209211353102","transaction_id":"4200001622202209215015802387","trade_type":"JSAPI","trade_state":"SUCCESS","trade_state_desc":"支付成功","bank_type":"OTHERS","attach":"","success_time":"2022-09-21T11:35:16+08:00","payer":{"openid":"oqT1g5JM4IBmk7yxEI-lGTnhs4Zc"},"amount":{"total":300,"payer_total":200,"currency":"CNY","payer_currency":"CNY"},"promotion_detail":[{"coupon_id":"37695285082","name":"[懒小毛]1元代金券","scope":"GLOBAL","type":"NOCASH","amount":100,"stock_id":"16764277","wechatpay_contribute":0,"merchant_contribute":100,"other_contribute":0,"currency":"CNY"}]}

                //进入业务代码
                appOrderService.wxCombinePayNotifyUrl(ciphertext, reqParams);

                // 通知应答：接收成功：HTTP应答状态码需返回200或204，无需返回应答报文。
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("支付回调失败：{}", e.getLocalizedMessage());
            // 通知应答：HTTP应答状态码需返回5XX或4XX，同时需返回应答报文
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            wxPayResult.put("code", "FAIL");
            wxPayResult.put("message", "失败");
            return wxPayResult;
        }

        return null;
    }
}
