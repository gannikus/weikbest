package com.weikbest.pro.saas.common.third.wx.pay.immediately;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.weikbest.pro.saas.common.third.wx.pay.config.WxPayProperties;
import com.weikbest.pro.saas.common.third.wx.util.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
public class WeixinTradeAppPay {

    private static final String ORDER_PAY = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 统一下单
    private static final String ORDER_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery"; // 查询订单
    private static final String ORDER_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";//申请退款
    private static final String QUERY_REFUND = "https://api.mch.weixin.qq.com/pay/refundquery";//退款查询
    private static final String ORDER_TRANSFERS = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";//付款到零钱

    /**
     * 调用支付统一下单api，返回签名
     *
     * @param wxConfig
     * @param openId
     * @param totalFee
     * @param body
     * @param outTradeNo
     * @return String {"appId":"wx32a70fda5facdecf","nonceStr":"mHanOLcLFqkuRAKD","package":"prepay_id=wx16233237202403807e6e90b78b40a40000","paySign":"BD4225FD00CCEC122E15E5D411D4AF95","signType":"MD5","timeStamp":"1623857556"}
     */
    public static JSONObject smallPay(WxPayProperties wxConfig, String openId, BigDecimal totalFee, String body, String outTradeNo) {
        Map<String, String> restmap = null;
        try {
            // 组装发送给微信的数据
            SortedMap<Object, Object> map = new TreeMap<Object, Object>();
            String total_fee = totalFee.multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
            map.put("appid", wxConfig.getAppId());
            map.put("mch_id", wxConfig.getMchId());
            map.put("notify_url", wxConfig.getNotifyUrl());
            map.put("openid", openId);
            map.put("total_fee", total_fee);
            map.put("body", body);
            map.put("out_trade_no", outTradeNo);
            map.put("trade_type", "JSAPI");
            map.put("nonce_str", PayUtil.getNonceStr());
            map.put("sign", WXSignUtils.creSmallSign("UTF-8", map, wxConfig.getMchKey()));

            String requestXml = HttpXmlUtils.getRequestXml(map);
            String method = "POST";
            String restxml = HttpXmlUtils.httpsRequest(ORDER_PAY, method, requestXml);
            restmap = XmlUtil.xmlParse(restxml);
        } catch (Exception e) {
            log.error("微信支付调用支付统一下单api，返回签名出现异常结果=========" + e.getMessage());
            throw new RuntimeException(e);
        }

        // 后台给app的时候要二次签名sign
        SortedMap<Object, Object> sortMap = new TreeMap<Object, Object>();
        if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
            sortMap.put("appId", wxConfig.getAppId());
            sortMap.put("timeStamp", PayUtil.payTimestamp());
            sortMap.put("nonceStr", restmap.get("nonce_str"));
            sortMap.put("package", "prepay_id=" + restmap.get("prepay_id"));
            sortMap.put("signType", "MD5");
            sortMap.put("paySign", WXSignUtils.creSmallSign("UTF-8", sortMap, wxConfig.getMchKey()));
        }
        return JSONObject.parseObject(JSONObject.toJSONString(sortMap));
    }

    /**
     * 调用支付统一下单api，返回签名
     *
     * @param jsonParam
     * @return String {"appId":"wx32a70fda5facdecf","nonceStr":"mHanOLcLFqkuRAKD","package":"prepay_id=wx16233237202403807e6e90b78b40a40000","paySign":"BD4225FD00CCEC122E15E5D411D4AF95","signType":"MD5","timeStamp":"1623857556"}
     */
    public static String smallPay(JSONObject jsonParam) {
        String jsonStr = jsonParam.toString();
        Map<String, String> mapParam = (Map<String, String>) JSONObject.parse(jsonStr);
        Map<String, String> restmap = null;
        try {
            // 组装发送给微信的数据
            SortedMap<Object, Object> map = new TreeMap<Object, Object>();
            String total_fee = new BigDecimal(mapParam.get("total_fee")).multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
            map.put("appid", mapParam.get("wx_appid"));
            map.put("mch_id", mapParam.get("wx_mch_id"));
            map.put("body", mapParam.get("body"));
            map.put("nonce_str", PayUtil.getNonceStr());
            map.put("out_trade_no", mapParam.get("out_trade_no"));
            map.put("total_fee", total_fee);
            map.put("notify_url", mapParam.get("notify_url"));
            map.put("trade_type", "JSAPI");
            map.put("openid", mapParam.get("openid"));
            map.put("sign", WXSignUtils.creSmallSign("UTF-8", map, mapParam.get("wx_key")));

            String requestXml = HttpXmlUtils.getRequestXml(map);
            String method = "POST";
            String restxml = HttpXmlUtils.httpsRequest(ORDER_PAY, method, requestXml).toString();
            restmap = XmlUtil.xmlParse(restxml);
        } catch (Exception e) {
            log.error("微信支付调用支付统一下单api，返回签名出现异常结果=========" + e.getMessage());
            throw new RuntimeException(e);
        }

        // 后台给app的时候要二次签名sign
        SortedMap<Object, Object> sortMap = new TreeMap<Object, Object>();
        if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
            sortMap.put("appId", mapParam.get("wx_appid"));
            sortMap.put("timeStamp", PayUtil.payTimestamp());
            sortMap.put("nonceStr", restmap.get("nonce_str"));
            sortMap.put("package", "prepay_id=" + restmap.get("prepay_id"));
            sortMap.put("signType", "MD5");
            sortMap.put("paySign", WXSignUtils.creSmallSign("UTF-8", sortMap, mapParam.get("wx_key")));
        }
        return JSONObject.toJSONString(sortMap);
    }

    /**
     * 主动查询订单状态
     *
     * @param wxConfig
     * @param outTradeNo
     * @return Map {transaction_id=4200001220202106165568668890, nonce_str=3fHjVTVF6khdPdCf, trade_state=SUCCESS, bank_type=OTHERS, openid=owPWC4ocjuEKPT1OUZdu7OlTKSQ0, sign=EEAA5E53AAA6BCAF8072947DA0078F16, return_msg=OK, fee_type=CNY, mch_id=1601457001, cash_fee=1, out_trade_no=wxzf2020080812572079111, cash_fee_type=CNY, appid=wx32a70fda5facdecf, total_fee=1, trade_state_desc=支付成功, trade_type=JSAPI, result_code=SUCCESS, attach=, time_end=20210616234128, is_subscribe=N, return_code=SUCCESS}
     */
    public static JSONObject queryOrder(WxPayProperties wxConfig, String outTradeNo) {
        Map<String, String> restmap = null;
        try {
            // 组装发送给微信的数据
            SortedMap<Object, Object> map = new TreeMap<Object, Object>();
            map.put("appid", wxConfig.getAppId());
            map.put("mch_id", wxConfig.getMchId());
            map.put("nonce_str", PayUtil.getNonceStr());
            map.put("out_trade_no", outTradeNo);
            map.put("sign", WXSignUtils.creSmallSign("UTF-8", map, wxConfig.getMchKey()));
            String requestXml = HttpXmlUtils.getRequestXml(map);
            String method = "POST";
            String restxml = HttpXmlUtils.httpsRequest(ORDER_QUERY, method, requestXml);
            restmap = XmlUtil.xmlParse(restxml);
        } catch (Exception e) {
            log.error("微信支付主动查询订单状态出现异常结果=========" + e.getMessage());
            throw new RuntimeException(e);
        }
        return JSONObject.parseObject(JSONObject.toJSONString(restmap));
    }

    /**
     * 主动查询订单状态
     *
     * @param jsonParam
     * @return Map {transaction_id=4200001220202106165568668890, nonce_str=3fHjVTVF6khdPdCf, trade_state=SUCCESS, bank_type=OTHERS, openid=owPWC4ocjuEKPT1OUZdu7OlTKSQ0, sign=EEAA5E53AAA6BCAF8072947DA0078F16, return_msg=OK, fee_type=CNY, mch_id=1601457001, cash_fee=1, out_trade_no=wxzf2020080812572079111, cash_fee_type=CNY, appid=wx32a70fda5facdecf, total_fee=1, trade_state_desc=支付成功, trade_type=JSAPI, result_code=SUCCESS, attach=, time_end=20210616234128, is_subscribe=N, return_code=SUCCESS}
     */
    public static Map queryOrder(JSONObject jsonParam) {
        Map<String, String> restmap = null;
        try {
            // 组装发送给微信的数据
            SortedMap<Object, Object> map = new TreeMap<Object, Object>();
            map.put("appid", jsonParam.get("appid"));
            map.put("mch_id", jsonParam.get("mch_id"));
            map.put("nonce_str", PayUtil.getNonceStr());
            map.put("out_trade_no", jsonParam.get("out_trade_no"));
            map.put("sign", WXSignUtils.creSmallSign("UTF-8", map, jsonParam.get("app_secret").toString()));
            String requestXml = HttpXmlUtils.getRequestXml(map);
            String method = "POST";
            String restxml = HttpXmlUtils.httpsRequest(ORDER_QUERY, method, requestXml).toString();
            restmap = XmlUtil.xmlParse(restxml);
        } catch (Exception e) {
            log.error("微信支付主动查询订单状态出现异常结果=========" + e.getMessage());
            throw new RuntimeException(e);
        }
        return restmap;
    }

    /**
     * 申请退款方法
     *
     * @param wxConfig
     * @param totalFee
     * @param refundFee
     * @param outTradeNo
     * @return
     */
    public static JSONObject refundOrder(WxPayProperties wxConfig, BigDecimal totalFee, BigDecimal refundFee, String outTradeNo) {
        String total_fee = new BigDecimal(totalFee.toString()).multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        String refund_fee = new BigDecimal(refundFee.toString()).multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        Map<String, String> restmap = null;
        try {
            SortedMap<Object, Object> parm = new TreeMap<Object, Object>();
            parm.put("appid", wxConfig.getAppId());
            parm.put("mch_id", wxConfig.getMchId());
            parm.put("nonce_str", StringUtil.getNoncestr());
            // 订单号
            parm.put("out_trade_no", outTradeNo);
            // 退款单号
            parm.put("out_refund_no", StringUtil.getOutrefundno());
            // 订单总金额
            parm.put("total_fee", total_fee);
            // 退款金额
            parm.put("refund_fee", refund_fee);
            parm.put("sign", HttpUtils.creSmallSign("UTF-8", parm, wxConfig.getMchKey()));
            String requestXml = HttpXmlUtils.getRequestXml(parm);
            String restxml = HttpUtils.posts(ORDER_REFUND, requestXml, wxConfig.getKeyPath(), wxConfig.getMchId());
            restmap = XmlUtil.xmlParse(restxml);
        } catch (Exception e) {
            log.error("微信支付申请退款出现异常结果=========" + e.getMessage());
            throw new RuntimeException(e);
        }
        return JSONObject.parseObject(JSONObject.toJSONString(restmap));
    }

    /**
     * 申请退款方法
     *
     * @param jsonParam
     * @return
     */
    public static Map refundOrder(JSONObject jsonParam) {
        String total_fee = new BigDecimal(jsonParam.get("total_fee").toString()).multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        String refund_fee = new BigDecimal(jsonParam.get("refund_fee").toString()).multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        Map<String, String> restmap = null;
        try {
            SortedMap<Object, Object> parm = new TreeMap<Object, Object>();
            parm.put("appid", jsonParam.get("appid"));
            parm.put("mch_id", jsonParam.get("mch_id"));
            parm.put("nonce_str", StringUtil.getNoncestr());
            parm.put("out_trade_no", jsonParam.get("outTradeNo"));// 订单号
            parm.put("out_refund_no", StringUtil.getOutrefundno()); // 退款单号
            parm.put("total_fee", total_fee); // 订单总金额
            parm.put("refund_fee", refund_fee); // 退款金额
            //parm.put("notify_url", jsonParam.get("notify_url"));
            parm.put("sign", HttpUtils.creSmallSign("UTF-8", parm, jsonParam.get("app_secret").toString()));
            String requestXml = HttpXmlUtils.getRequestXml(parm);
            String restxml = HttpUtils.posts(ORDER_REFUND, requestXml, jsonParam.get("wxCertUrl").toString(), jsonParam.get("mch_id").toString());
            restmap = XmlUtil.xmlParse(restxml);
        } catch (Exception e) {
            log.error("微信支付申请退款出现异常结果=========" + e.getMessage());
            throw new RuntimeException(e);
        }
        return restmap;
    }

    /**
     * 主动查询退款状态
     *
     * @param jsonParam
     * @return
     */
    public static Map queryrefund(JSONObject jsonParam) {
        Map<String, String> restmap = null;
        try {
            // 组装发送给微信的数据
            SortedMap<Object, Object> map = new TreeMap<Object, Object>();
            map.put("appid", jsonParam.get("appid"));
            map.put("mch_id", jsonParam.get("mch_id"));
            map.put("nonce_str", PayUtil.getNonceStr());
            map.put("out_trade_no", jsonParam.get("out_trade_no"));
            map.put("sign", WXSignUtils.creSmallSign("UTF-8", map, jsonParam.get("app_secret").toString()));
            String requestXml = HttpXmlUtils.getRequestXml(map);
            String method = "POST";
            String restxml = HttpXmlUtils.httpsRequest(QUERY_REFUND, method, requestXml).toString();
            restmap = XmlUtil.xmlParse(restxml);
        } catch (Exception e) {
            log.error("微信支付主动查询退款状态出现异常结果=========" + e.getMessage());
            throw new RuntimeException(e);
        }
        return restmap;
    }

    /**
     * 付款到零钱方法
     *
     * @param wxPayProperties
     * @param openId
     * @param amountFee
     * @param outTradeNo
     * @param desc
     * @return
     */
    public static JSONObject transfers(WxPayProperties wxPayProperties, String openId, BigDecimal amountFee, String outTradeNo, String desc) {
        String amount = amountFee.multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        Map<String, String> restmap = null;
        try {
            SortedMap<Object, Object> parm = new TreeMap<Object, Object>();
            parm.put("mch_appid", wxPayProperties.getAppId());
            parm.put("mchid", wxPayProperties.getMchId());
            parm.put("nonce_str", StringUtil.getNoncestr());
            //付款到零钱订单号，需保持唯一性(只能是字母或者数字，不能包含有其它字符)
            parm.put("partner_trade_no", outTradeNo);
            // 微信openid
            parm.put("openid", openId);
            //不校验真实姓名
            parm.put("check_name", "NO_CHECK");
            //金额
            parm.put("amount", amount);
            //付款备注
            parm.put("desc", desc);
            parm.put("sign", HttpUtils.creSmallSign("UTF-8", parm, wxPayProperties.getMchKey()));
            String requestXml = HttpXmlUtils.getRequestXml(parm);
            String restxml = HttpUtils.posts(ORDER_TRANSFERS, requestXml, wxPayProperties.getKeyPath(), wxPayProperties.getMchId());
            restmap = XmlUtil.xmlParse(restxml);
        } catch (Exception e) {
            log.error("微信支付付款到零钱出现异常结果=========" + e.getMessage());
            throw new RuntimeException(e);
        }
        return JSONObject.parseObject(JSONObject.toJSONString(restmap));
    }

    /**
     * 付款到零钱方法
     *
     * @param jsonParam
     * @return
     */
    public static Map transfers(JSONObject jsonParam) {
        String amount = new BigDecimal(jsonParam.get("amount").toString()).multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        Map<String, String> restmap = null;
        try {
            SortedMap<Object, Object> parm = new TreeMap<Object, Object>();
            parm.put("mch_appid", jsonParam.get("appid"));
            parm.put("mchid", jsonParam.get("mch_id"));
            parm.put("nonce_str", StringUtil.getNoncestr());
            parm.put("partner_trade_no", jsonParam.get("outTradeNo"));//付款到零钱订单号，需保持唯一性(只能是字母或者数字，不能包含有其它字符)
            parm.put("openid", jsonParam.get("openid")); // 微信openid
            parm.put("check_name", "NO_CHECK"); //不校验真实姓名
            parm.put("amount", amount); //金额
            parm.put("desc", jsonParam.get("desc"));//付款备注
            parm.put("sign", HttpUtils.creSmallSign("UTF-8", parm, jsonParam.get("app_secret").toString()));
            String requestXml = HttpXmlUtils.getRequestXml(parm);
            String restxml = HttpUtils.posts(ORDER_TRANSFERS, requestXml, jsonParam.get("wxCertUrl").toString(), jsonParam.get("mch_id").toString());
            restmap = XmlUtil.xmlParse(restxml);
        } catch (Exception e) {
            e.getMessage();
        }
        return restmap;
    }

    public static void main(String[] args) {
        // 调用支付统一下单api，返回签名
	   /* JSONObject jsonParam = new JSONObject(); 
		  jsonParam.put("wx_appid","YOUR_APP_ID");//微信小程序id
		  jsonParam.put("wx_mch_id", "YOUR_MCH_ID");//微信支付商户号
		  jsonParam.put("body", "test-手机下单");//商品描述
		  jsonParam.put("out_trade_no", "YOUR_OUT_TRADE_NO");//订单编号
		  jsonParam.put("openid","YOUR_OPENID");//wx_openid
		  jsonParam.put("notify_url","YOUR_NOTIFY_URL");//支付成功后回调方法,只支持通过https请求。
		  jsonParam.put("total_fee", "YOUR_TOTAL_FEE");//金额 
		  jsonParam.put("wx_key","YOUR_WX_KEY");//商户号API密钥
		  String param = smallPay(jsonParam);
		  System.out.println(param);*/
        //{"appId":"YOUR_APP_ID","nonceStr":"YOUR_NONCE_STR","package":"prepay_id=YOUR_PREPAY_ID","paySign":"YOUR_PAY_SIGN","signType":"MD5","timeStamp":"YOUR_TIMESTAMP"}


        // 主动查询订单状态
	    /* JSONObject jsonParam = new JSONObject(); 
	  	jsonParam.put("appid","YOUR_APP_ID");//微信小程序id
	  	jsonParam.put("mch_id", "YOUR_MCH_ID");//微信支付商户号
	  	jsonParam.put("out_trade_no","YOUR_OUT_TRADE_NO");//订单编号
	  	jsonParam.put("app_secret", "YOUR_APP_SECRET");//商户号API密钥
	  	Map map = queryOrder(jsonParam);
	  	System.out.println(map.toString());*/
        //{transaction_id=YOUR_TRANSACTION_ID, nonce_str=YOUR_NONCE_STR, trade_state=SUCCESS, bank_type=OTHERS, openid=YOUR_OPENID, sign=YOUR_SIGN, return_msg=OK, fee_type=CNY, mch_id=YOUR_MCH_ID, cash_fee=YOUR_CASH_FEE, out_trade_no=YOUR_OUT_TRADE_NO, cash_fee_type=CNY, appid=YOUR_APP_ID, total_fee=YOUR_TOTAL_FEE, trade_state_desc=支付成功, trade_type=JSAPI, result_code=SUCCESS, attach=, time_end=YOUR_TIME_END, is_subscribe=N, return_code=SUCCESS}

        // 退款方法
		/*JSONObject jsonParam = new JSONObject();
		jsonParam.put("outTradeNo", "YOUR_OUT_TRADE_NO"); // 订单编号
		jsonParam.put("refund_fee", "YOUR_REFUND_FEE"); // 申请退款金额
		jsonParam.put("total_fee", "YOUR_TOTAL_FEE");// 订单金额
		jsonParam.put("appid", "YOUR_APP_ID"); //微信小程序id
		jsonParam.put("mch_id", "YOUR_MCH_ID"); //微信支付商户号
		jsonParam.put("app_secret", "YOUR_APP_SECRET"); //商户号API密钥
		jsonParam.put("wxCertUrl", "YOUR_WX_CERT_URL");// 
		Map result = refundOrder(jsonParam);
		System.out.println(result.toString());*/
        //{transaction_id=YOUR_TRANSACTION_ID, nonce_str=YOUR_NONCE_STR, out_refund_no=YOUR_OUT_REFUND_NO, sign=YOUR_SIGN, return_msg=OK, mch_id=YOUR_MCH_ID, refund_id=YOUR_REFUND_ID, cash_fee=YOUR_CASH_FEE, out_trade_no=YOUR_OUT_TRADE_NO, coupon_refund_fee=YOUR_COUPON_REFUND_FEE, refund_channel=, appid=YOUR_APP_ID, refund_fee=YOUR_REFUND_FEE, total_fee=YOUR_TOTAL_FEE, result_code=SUCCESS, coupon_refund_count=YOUR_COUPON_REFUND_COUNT, cash_refund_fee=YOUR_CASH_REFUND_FEE, return_code=SUCCESS}

        //主动查询退款状态
		/*JSONObject jsonParam = new JSONObject();
		jsonParam.put("out_trade_no", "YOUR_OUT_TRADE_NO"); // 订单编号
		jsonParam.put("appid", "YOUR_APP_ID"); //微信小程序id
		jsonParam.put("mch_id", "YOUR_MCH_ID"); //微信支付商户号
		jsonParam.put("app_secret", "YOUR_APP_SECRET"); //商户号API密钥
		Map result = queryrefund(jsonParam);
		System.out.println(result.toString());*/
        //{transaction_id=YOUR_TRANSACTION_ID, nonce_str=YOUR_NONCE_STR, out_refund_no_0=YOUR_OUT_REFUND_NO_0, refund_status_0=SUCCESS, sign=YOUR_SIGN, refund_fee_0=YOUR_REFUND_FEE_0, refund_recv_accout_0=支付用户的零钱, return_msg=OK, mch_id=YOUR_MCH_ID, refund_success_time_0=YOUR_REFUND_SUCCESS_TIME_0, cash_fee=YOUR_CASH_FEE, refund_id_0=YOUR_REFUND_ID_0, out_trade_no=YOUR_OUT_TRADE_NO, appid=YOUR_APP_ID, refund_fee=YOUR_REFUND_FEE, total_fee=YOUR_TOTAL_FEE, result_code=SUCCESS, refund_account_0=REFUND_SOURCE_RECHARGE_FUNDS, refund_count=YOUR_REFUND_COUNT, return_code=SUCCESS, refund_channel_0=ORIGINAL}

        //付款到零钱方法
		/*JSONObject jsonParam = new JSONObject();
		jsonParam.put("outTradeNo", "YOUR_OUT_TRADE_NO"); // 付款到零钱订单编号
		jsonParam.put("amount", "YOUR_AMOUNT"); // 零钱
		jsonParam.put("appid", "YOUR_APP_ID"); //微信小程序id
		jsonParam.put("mch_id", "YOUR_MCH_ID"); //微信支付商户号
		jsonParam.put("app_secret", "YOUR_APP_SECRET"); //商户号API密钥
		jsonParam.put("openid", "YOUR_OPENID");//微信openid
		jsonParam.put("desc", "YOUR_DESC");//付款备注
		jsonParam.put("wxCertUrl", "YOUR_WX_CERT_URL");//
		Map result = transfers(jsonParam);
		System.out.println(result.toString());*/
        //{nonce_str=YOUR_NONCE_STR, mchid=YOUR_MCH_ID, partner_trade_no=YOUR_PARTNER_TRADE_NO, payment_time=YOUR_PAYMENT_TIME, mch_appid=YOUR_MCH_APPID, payment_no=YOUR_PAYMENT_NO, return_msg=, result_code=SUCCESS, return_code=SUCCESS}
    }

}
