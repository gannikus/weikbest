package com.weikbest.pro.saas.common.third.bytedance;

import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.weikbest.pro.saas.common.third.util.RequestUtils;

import java.io.UnsupportedEncodingException;

public class Toutiao {

    /** 从app_config配置表里取字节跳动对应的toutiao_appid字段。 */
    //public static final String APPID = "tt5a382c85c500ccef";
    /** 从app_config配置表里取字节跳动对应的toutiao_appsecret字段。。 */
    //public static final String APPSECRET = "1484206007025526ca83f2395b5a5698f0077c1c";
    /** 从app_config配置表里取字节跳动对应的toutiao_salt签名字段。。 */
    //public static final String SALT = "IgVelV41EKld3rcFwdpKvZEOCL19OnBSas1FWk5h";
    /** 从app_config配置表里取字节跳动对应的toutiao_notifyUrl支付回调字段。。 */
    //public static final String NOTIFYURL = "https:/** xxxxxx/business/api/pay/notifyUrl";
    /** 从app_config配置表里取字节跳动对应的toutiao_refund_notifyUrl退款回调字段。。 */
    //public static final String REFUNDNOTIFYURL = "https://xxxxxx/business/api/pay/refundNotifyUrls";

    /**
     * 获取 access_token 的值。
     */
    public static final String TOKEN = "https://developer.toutiao.com/api/apps/token";
    /**
     * 获取 session_key 和 openId。
     */
    public static final String CODE2SESSION = "https://developer.toutiao.com/api/apps/jscode2session";
    /**
     * 获取小程序/小游戏的二维码。
     */
    public static final String CREATEQRCODE = "https://developer.toutiao.com/api/apps/qrcode";
    /**
     * 获取支付下单-服务端预下单。
     */
    public static final String CREATEORDER = "https://developer.toutiao.com/api/apps/ecpay/v1/create_order";
    /**
     * 订单查询。
     */
    public static final String QUERYORDER = "https://developer.toutiao.com/api/apps/ecpay/v1/query_order";
    /**
     * 退款请求。
     */
    public static final String CREATEREFUND = "https://developer.toutiao.com/api/apps/ecpay/v1/create_refund";
    /**
     * 退款查询。
     */
    public static final String QUERYREFUND = "https://developer.toutiao.com/api/apps/ecpay/v1/query_refund";


    /**
     * 获取 access_token 的值
     *
     * @param appid  从app配置表里取字节跳动对应的toutiao_appid字段。
     * @param secret 从app配置表里取字节跳动对应的toutiao_appsecret字段。
     * @return {"access_token":"0801129d0131323864303462303361343530653435373932353266656665383534626165303430623636316464633535653433346232663834633731363939373832653166386463343062323562666136316233643835626137636464633263396533626364393935363437326437393739343238613262326530623266356238396133623465323661316461656239346665366131343036313736623364623634","expires_in":7200}
     */
    public static JSONObject getAccessToken(String appid, String secret) {
        StringBuilder url = new StringBuilder();
        url.append(TOKEN);
        url.append("?appid=" + appid);
        url.append("&secret=" + secret);
        url.append("&grant_type=client_credential");
        return RequestUtils.httpGet(url.toString());
    }

    /**
     * 获取 session_key 和 openId
     *
     * @param appid  从app配置表里取字节跳动对应的toutiao_appid字段。
     * @param secret 从app配置表里取字节跳动对应的toutiao_appsecret字段。
     * @param code   小程序传code字段(临时登录凭证, 有效期 3 分钟)
     * @return {"anonymous_openid":"","unionid":"57db6e68-52d0-4c89-b481-a65655d96a3a","openid":"1bf01798-807a-4044-9e12-f55deb4c6278","session_key":"oMmdnMcyAIO8WJNYRiccKA==","error":0}
     */
    public static JSONObject getJscode2session(String appid, String secret, String code) {
        StringBuilder url = new StringBuilder();
        url.append(CODE2SESSION);
        url.append("?appid=" + appid);
        url.append("&secret=" + secret);
        url.append("&code=" + code);
        return RequestUtils.httpGet(url.toString());
    }

    /**
     * 生成字节跳动小程序里的当前用户推广二维码图片
     *
     * @param access_token 从app配置表里取toutiao_access_token字段
     * @param appname      小程序传douyin字段
     * @param path         小程序传path字段(字段后面带上用户id)
     * @param imgPath      后台生成一个图片访问路径(不能重复)。
     * @return JSONObject     {"imgPath":"d:/test.jpg","error":"0"}
     * @throws UnsupportedEncodingException
     */
    public static JSONObject createQRCode(String access_token, String appname, String path, String imgPath) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("access_token", access_token);
        jsonParam.put("appname", appname);
        jsonParam.put("path", path);
        return RequestUtils.httpPostImage(CREATEQRCODE, jsonParam, imgPath);
    }

    /**
     * 生成字节跳动小程序里的当前用户推广二维码图片
     *
     * @param access_token 从app配置表里取toutiao_access_token字段
     * @param appname      小程序传douyin字段
     * @param path         小程序传path字段(字段后面带上用户id)
     * @return JSONObject     {"imgByte":图片的byte,"error":"0"}
     * @throws UnsupportedEncodingException
     */
    public static JSONObject createQRCode(String access_token, String appname, String path) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("access_token", access_token);
        jsonParam.put("appname", appname);
        jsonParam.put("path", path);
        return RequestUtils.httpPostImage(CREATEQRCODE, jsonParam);
    }

    public static void main(String[] args) {
        /**获取 access_token 的值
         JSONObject json = getAccessToken(APPID,APPSECRET);
         System.out.println(json);*/
        //json={"access_token":"0801129d0131323864303462303361343530653435373932353266656665383534626165303430623636316464633535653433346232663834633731363939373832653166386463343062323562666136316233643835626137636464633263396533626364393935363437326437393739343238613262326530623266356238396133623465323661316461656239346665366131343036313736623364623634","expires_in":7200}
		
		/*获取 session_key 和 openId
		JSONObject json = getJscode2session(APPID,APPSECRET,"YMPYLW2bTlxb2grvSGwFcdzTiL02vpQpXVgOhWypJkuVNXy1HLFqA0NImMJGq4p3fVaOO2hcoraDIKcDHa3gpal-GplC0uV8GHRdgQ");
		System.out.println(json);
		//json= {"anonymous_openid":"","unionid":"57db6e68-52d0-4c89-b481-a65655d96a3a","openid":"1bf01798-807a-4044-9e12-f55deb4c6278","session_key":"oMmdnMcyAIO8WJNYRiccKA==","error":0}
		*/

        /**
         * 获取小程序/小游戏的二维码
         JSONObject json = createQRCode("0801129d0165333164363531626331646463336163666632623362333339666636353461366230333833393036666339396262356233363462356234393135306135636138333835323537613836666438363062666336326562383333363333373434363265343237323865376361656530623334313939376130373465313437623331376161653564623261643964633931653766383530653533316330323465","douyin","pages%2Findex%2Findex","d:/test.jpg");
         System.out.println(json); */

        String test = "/pages/index/index?userId=3";
        String escape = EscapeUtil.escape(test, c -> false == (
                Character.isDigit(c)
                        || Character.isLowerCase(c)
                        || Character.isUpperCase(c)
                        || StrUtil.contains("*@-_+.", c)));
        System.out.println(escape);
    }
}
