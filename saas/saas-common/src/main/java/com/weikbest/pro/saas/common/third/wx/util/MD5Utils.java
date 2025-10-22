package com.weikbest.pro.saas.common.third.wx.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MD5Utils {

    /**
     * @param paramsMap
     * @return
     * @Title: getSign
     * @Description: 字节跳动小程序请求签名算法
     * @author
     * @date 2021-04-27 17:19:08
     */
    public static String getSign(Map<String, Object> paramsMap) {
        String[] skipArray = {"app_id", "sign", "thirdparty_id"};
        Map<String, Object> map = MapUtil.removeAny(paramsMap, skipArray);
        List<String> list = new ArrayList<String>();
        for (String key : map.keySet()) {
            list.add(MapUtil.getStr(map, key));
        }
        CollUtil.sort(list, null);
        String str = CollUtil.join(list, "&");
        return DigestUtil.md5Hex(str);
    }


    public static String md5FromStr(String inStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes(StandardCharsets.UTF_8);
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String getMD5(String content) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(content.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }
}
