package com.weikbest.pro.saas.common.third.wx.util;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;


/**
 * 微信签名sign
 *
 * @author PC
 */
@Slf4j
public class WXSignUtils {


    /**
     * 签名的时候不携带的参数
     */
    private static final List<String> NO_SIGN_PARAMS = Lists.newArrayList("sign", "key", "xmlString", "xmlDoc", "couponList");

    /**
     * 微信支付
     *
     * @param characterEncoding
     * @param parameters
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    /**
     * 微信支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3).
     *
     * @param params        参数信息
     * @param signType      签名类型，如果为空，则默认为MD5
     * @param signKey       签名Key
     * @param ignoredParams 签名时需要忽略的特殊参数
     * @return 签名字符串 string
     */
    public static String createSign(Map<String, String> params, String signType, String signKey, String[] ignoredParams) {
        StringBuilder toSign = new StringBuilder();
        for (String key : new TreeMap<>(params).keySet()) {
            String value = params.get(key);
            boolean shouldSign = false;
            if (StringUtils.isNotEmpty(value) && !ArrayUtils.contains(ignoredParams, key)
                    && !NO_SIGN_PARAMS.contains(key)) {
                shouldSign = true;
            }

            if (shouldSign) {
                toSign.append(key).append("=").append(value).append("&");
            }
        }

        toSign.append("key=").append(signKey);

        log.info("进入Sign生成器： "+toSign + " v2key: " + signKey);

        if ("HMAC-SHA256".equals(signType)) {
            return createHmacSha256Sign(toSign.toString(), signKey);
        } else {
            return MD5Util.getMD5(toSign.toString()).toUpperCase();
        }
    }

    /**
     * HmacSHA256 签名算法
     *
     * @param message 签名数据
     * @param key     签名密钥
     */
    public static String createHmacSha256Sign(String message, String key) {
        try {
            Mac sha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256.init(secretKeySpec);
            byte[] bytes = sha256.doFinal(message.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(bytes).toUpperCase();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.getMessage();
        }

        return null;
    }


    /**
     * @param characterEncoding
     * @param parameters
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String creSmallSign(String characterEncoding, SortedMap<Object, Object> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5Util.getMD5(sb.toString()).toUpperCase();
        return sign;
    }

    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("sign".equalsIgnoreCase(k)) {

            } else if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("<" + "sign" + ">" + "<![CDATA[" + parameters.get("sign") + "]]></" + "sign" + ">");
        sb.append("</xml>");
        return sb.toString();
    }

}
