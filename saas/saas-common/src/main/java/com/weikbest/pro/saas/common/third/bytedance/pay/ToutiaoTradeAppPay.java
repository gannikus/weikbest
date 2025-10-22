package com.weikbest.pro.saas.common.third.bytedance.pay;

import com.alibaba.fastjson.JSONObject;
import com.weikbest.pro.saas.common.third.bytedance.Toutiao;
import com.weikbest.pro.saas.common.third.bytedance.util.ToutiaoMD5Utils;
import com.weikbest.pro.saas.common.third.util.RequestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付下单
 *
 * @author ql
 */
public class ToutiaoTradeAppPay {

    /**
     * 获取支付下单-服务端预下单
     *
     * @param app_id       从app_config配置表里取字节跳动对应的toutiao_appid字段。
     * @param out_order_no 订单号(系统自定义生成)
     * @param total_amount 支付价格; 单位为[分]
     * @param subject      商品描述; 长度限制 128 字节，不超过 42 个汉字
     * @param body         商品详情; 长度限制 128 字节，不超过 42 个汉字
     * @param valid_time   订单过期时间(秒); 最小 15 分钟，最大两天
     * @param salt         从app_config配置表里取字节跳动对应的toutiao_salt签名字段。
     * @param notify_url   从app_config配置表里取字节跳动对应的toutiao_notify_url支付回调字段。
     * @return JSONObject     {"err_tips":"","data":{"order_token":"CgsIARDiDRgBIAEoARJOCkzqLfLeGkO5pzC0B3Rk9T6psrV32ECYVFI2QLmKy2rYtul1yqcgUndgaHOEssrY0JMdkg6MtS+UiWriDaAehlqQ/J/lAQKGy8Vb7FlVGgA=","order_id":"6964246852111796521"},"err_no":0}
     * @return_param order_token String 订单校验
     * @return_param order_id String 平台商支付订单号(抖音支付订单号)
     * @return_param err_no int 状态码 0-业务处理成功
     * @return_param err_tips String 错误提示信息,常见错误处理可参考附录常见问题章节
     */
    public static JSONObject create_order(String app_id, String out_order_no, Integer total_amount, String subject, String body, Integer valid_time, String salt, String notify_url) {
        JSONObject jsonParam = new JSONObject();
        Map map = new HashMap();
        map.put("app_id", app_id);
        map.put("out_order_no", out_order_no);
        map.put("total_amount", total_amount);
        map.put("subject", subject);
        map.put("body", body);
        map.put("valid_time", valid_time);
        map.put("salt", salt);
        map.put("notify_url", notify_url);
        jsonParam.putAll(map);
        jsonParam.put("sign", ToutiaoMD5Utils.getSign(map));
        return RequestUtils.httpPost(Toutiao.CREATEORDER, jsonParam);
    }

    /**
     * 订单查询
     *
     * @param app_id       从app_config配置表里取字节跳动对应的toutiao_appid字段。
     * @param out_order_no 订单号(预下单时系统生成的订单号)
     * @param salt         从app_config配置表里取字节跳动对应的toutiao_salt签名字段。
     * @return JSONObject     {"err_tips":"","out_order_no":"a20210519120008","payment_info":{"order_status":"SUCCESS","total_fee":1,"channel_no":"2021052022001498021402379297","channel_gateway_no":"12105200081986505349","way":2,"pay_time":"2021-05-20 12:01:54"},"order_id":"6964214364568127755","err_no":0}
     */
    public static JSONObject query_order(String app_id, String out_order_no, String salt) {
        JSONObject jsonParam = new JSONObject();
        Map map = new HashMap();
        map.put("app_id", app_id);
        map.put("out_order_no", out_order_no);
        map.put("salt", salt);
        jsonParam.putAll(map);
        jsonParam.put("sign", ToutiaoMD5Utils.getSign(map));
        return RequestUtils.httpPost(Toutiao.QUERYORDER, jsonParam);
    }

    /**
     * 订单退款
     *
     * @param app_id        从app_config配置表里取字节跳动对应的toutiao_appid字段。
     * @param out_order_no  订单号(预下单时系统生成的订单号)
     * @param out_refund_no 退款号(系统自定义生成)
     * @param refund_amount 退款金额，单位[分]
     * @param salt          从app_config配置表里取字节跳动对应的toutiao_salt签名字段。
     * @param reason        退款原因
     * @param notify_url    从app_config配置表里取字节跳动对应的toutiao_refund_notifyUrl签名字段。
     * @return JSONObject   {"err_tips":"","refund_no":"6964302778860701992","err_no":0}
     */
    public static JSONObject create_refund(String app_id, String out_order_no, String out_refund_no, Integer refund_amount, String salt, String reason, String notify_url) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("app_id", app_id);
        jsonParam.put("out_order_no", out_order_no);
        jsonParam.put("out_refund_no", out_refund_no);
        jsonParam.put("refund_amount", refund_amount);
        jsonParam.put("reason", reason);
        jsonParam.put("notify_url", notify_url);
        Map map = new HashMap();
        map.put("app_id", app_id);
        map.put("out_order_no", out_order_no);
        map.put("out_refund_no", out_refund_no);
        map.put("refund_amount", refund_amount);
        map.put("reason", reason);
        map.put("notify_url", notify_url);
        map.put("salt", salt);
        jsonParam.put("sign", ToutiaoMD5Utils.getSign(map));
        return RequestUtils.httpPost(Toutiao.CREATEREFUND, jsonParam);
    }

    /**
     * 退款查询
     *
     * @param app_id        从app_config配置表里取字节跳动对应的toutiao_appid字段。
     * @param out_refund_no 订单号(服务端预下单时由系统自定义生成)
     * @param salt          从app_config配置表里取字节跳动对应的toutiao_salt签名字段。
     * @return JSONObject    {"err_tips":"","refundInfo":{"refund_status":"SUCCESS","refund_no":"6964302778860701992","refund_amount":1},"err_no":0}
     */
    public static JSONObject query_refund(String app_id, String out_refund_no, String salt) {
        JSONObject jsonParam = new JSONObject();
        Map map = new HashMap();
        map.put("app_id", app_id);
        map.put("out_refund_no", out_refund_no);
        map.put("salt", salt);
        jsonParam.putAll(map);
        jsonParam.put("sign", ToutiaoMD5Utils.getSign(map));
        return RequestUtils.httpPost(Toutiao.QUERYREFUND, jsonParam);
    }

    public static void main(String[] args) {
		/*获取支付下单-服务端预下单
		JSONObject result = create_order(toutiao.APPID, "a20210519120009", 1, "标题", "内容", 1200, toutiao.SALT, toutiao.NOTIFYURL);
		System.out.println(result.toString());
		//result = {"err_tips":"","data":{"order_token":"CgsIARDiDRgBIAEoARJOCkwceMWsyJkJiMHO9K2IQWI3YpPRX011BR9jwQhVJgb+P7tHA/4HLFQgiz0lgq6xqxyBHy+n82a8lSFJZtuDb3x+hqrDtulAEjhtgo5vGgA=","order_id":"6963917069247154465"},"err_no":0}
		*/
		
		/*根据订单号查询订单信息
		JSONObject result = query_order(toutiao.APPID, "a20210519120008",toutiao.SALT);
		System.out.println(result.toString());
		//result = {"err_tips":"","out_order_no":"a20210519120008","payment_info":{"order_status":"SUCCESS","total_fee":1,"channel_no":"2021052022001498021402379297","channel_gateway_no":"12105200081986505349","way":2,"pay_time":"2021-05-20 12:01:54"},"order_id":"6964214364568127755","err_no":0}
		*/
		
		/*订单退款
		JSONObject result = create_refund(toutiao.APPID, "a20210519120008", "a20210519120008", 1, toutiao.SALT, "123", toutiao.REFUNDNOTIFYURL);
		System.out.println(result.toString());
		//result = {"err_tips":"","refund_no":"6964302778860701992","err_no":0}
		*/
		
		/*退款查询
		JSONObject result = query_refund(toutiao.APPID, "a20210519120008",toutiao.SALT);
		System.out.println(result.toString());
		//result = {"err_tips":"","refundInfo":{"refund_status":"SUCCESS","refund_no":"6964302778860701992","refund_amount":1},"err_no":0}
		*/
    }
}
