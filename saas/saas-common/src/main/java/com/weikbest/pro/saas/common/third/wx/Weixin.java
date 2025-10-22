package com.weikbest.pro.saas.common.third.wx;

import com.alibaba.fastjson.JSONObject;
import com.weikbest.pro.saas.common.third.util.RequestUtils;

import java.io.UnsupportedEncodingException;

public class Weixin {

    /** 从app_config配置表里取字节跳动对应的weixin_appid字段。 */
    // public static final String APPID = "wx001a1cc03e965a7f";
    /** 从app_config配置表里取字节跳动对应的weixin_appsecret字段。 */
    // public static final String APPSECRET = "7b27a7cd42d6abe234ae27f3918919d2";

    /**
     * 获取微信openid。
     */
    public static final String JSCODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session";
    /**
     * 获取小程序/小游戏的二维码。
     */
    public static final String CREATEQRCODE = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
    /**
     * 获取 access_token 的值。
     */
    public static final String TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 获取 access_token 的值
     *
     * @param appid  从app配置表里取字节跳动对应的toutiao_appid字段。
     * @param secret 从app配置表里取字节跳动对应的toutiao_appsecret字段。
     * @return {"access_token":"46_5e-T18lv-__izuKeyWs74NVXwg6mIBnnrY0sbnfAeoXyQrs2TRanKeLPnyQRbaI0rfgYLLqorb8XFtdrBE1xt49fGNftQAJGRUfpEBdUwwSd8nnare6ccK5LQ9W-UVQFbGVmge9fSD6KI07gTQHaAAAAXO","expires_in":7200}
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
     * 获取微信openid的值
     *
     * @param appId
     * @param appsecret
     * @param code
     * @return JSONObject {"unionid":"oqBmn0onft1Fw7qO-A5Pp_K9GiiI","openid":"oh14h0SNOy5ODkvQUuh0zODRCZ6M","session_key":"DFNnoUM0J97T3Hic3hFRFQ==","expires_in":7200}
     */
    public static JSONObject jscode2session(String appId, String appsecret, String code) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(JSCODE2SESSION);
        buffer.append("?appid=" + appId);
        buffer.append("&secret=" + appsecret);
        buffer.append("&js_code=" + code);
        buffer.append("&grant_type=authorization_code");
        return RequestUtils.httpGet(buffer.toString());
    }

    /**
     * 生成微信小程序里的当前用户推广二维码图片
     *
     * @param userId       用户id(用户参数)
     * @param access_token 从app配置表里取weixin_access_token字段
     * @param path         小程序传path字段
     * @param imgPath      后台生成一个图片访问路径(不能重复)。
     * @return JSONObject     {"imgPath":"d:/test.jpg","error":"0"}
     * @throws UnsupportedEncodingException
     */
    public static JSONObject createQRCode(String userId, String access_token, String path, String imgPath) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("scene", userId);
        jsonParam.put("page", path);
        jsonParam.put("auto_color", false);
        return RequestUtils.httpPostImage(CREATEQRCODE + access_token, jsonParam, imgPath);
    }


    /**
     * 生成微信小程序里的当前用户推广二维码图片
     *
     * @param userId       用户id(用户参数)
     * @param access_token 从app配置表里取weixin_access_token字段
     * @param path         小程序传path字段
     * @return JSONObject     {"imgPath":"d:/test.jpg","error":"0"}
     * @throws UnsupportedEncodingException
     */
    public static JSONObject createQRCode(String userId, String access_token, String path) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("scene", userId);
        jsonParam.put("page", path);
        jsonParam.put("auto_color", false);
        return RequestUtils.httpPostImage(CREATEQRCODE + access_token, jsonParam);
    }

    public static void main(String[] args) {
        ///**获取 access_token 的值*/
		/*JSONObject json = getAccessToken(APPID,APPSECRET);
		System.out.println(json);*/
        //{"access_token":"46_5e-T18lv-__izuKeyWs74NVXwg6mIBnnrY0sbnfAeoXyQrs2TRanKeLPnyQRbaI0rfgYLLqorb8XFtdrBE1xt49fGNftQAJGRUfpEBdUwwSd8nnare6ccK5LQ9W-UVQFbGVmge9fSD6KI07gTQHaAAAAXO","expires_in":7200}

        //获取微信openid的值
		/*JSONObject json = jscode2session(APPID,APPSECRET,"023oHoFa1x8IdB0YyeJa1bJiic2oHoFq");
		System.out.println(json.toString());*/
        //{"unionid":"oqBmn0onft1Fw7qO-A5Pp_K9GiiI","openid":"oh14h0SNOy5ODkvQUuh0zODRCZ6M","session_key":"DFNnoUM0J97T3Hic3hFRFQ==","expires_in":7200}

        /**
         * 获取小程序/小游戏的二维码*/
        JSONObject json = createQRCode("2", "46_gv--GdDDf_x9mY6nOmFhvsTQc8W0lqpVErULA8ijQ7D0qEi1bkNxoXqfMoaiJ0PUcd8gGu_W5KrJ6sbLM9A25fS0SOWY_5AjXL32Us3UZkOWYk_5mHW0H6UFVeGNY1Qdlq1OQj-F0QdjvkXlPBNaAFAWIU", "pages/index/index", "d:/test4.jpg");
        System.out.println(json.toString());
        //{"imgPath":"d:/test4.jpg","error":"0"}

    }

}
