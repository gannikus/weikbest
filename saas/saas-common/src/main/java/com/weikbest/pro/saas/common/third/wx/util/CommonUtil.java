package com.weikbest.pro.saas.common.third.wx.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 公共工具类
 */
public class CommonUtil {

    private static final Log log = LogFactory.getLog(CommonUtil.class);
    private static long lastId = System.currentTimeMillis();

    /**
     * 自动根据时间生成id
     *
     * @return
     */
    public static synchronized long createId() {
        long currentId = System.currentTimeMillis();
        if (currentId <= lastId) {
            currentId = lastId + 1;
        }
        lastId = currentId;
        return currentId;
    }

    /**
     * 得到String的MD5码
     *
     * @param srcString 将要加密码的字符串
     * @return String 加密后的字符串
     */
    public static String getMD5(String srcString) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(srcString.getBytes("UTF8"));
            byte s[] = md.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化文件大小
     *
     * @param dataSize
     * @return
     */
    public static String formatDataSize(Long dataSize) {
        double byteSize = dataSize / 1024;
        String suffix = "KB";
        if (byteSize > 1000) {
            byteSize = byteSize / 1024;
            suffix = "MB";
        }
        DecimalFormat format = new DecimalFormat("0.##");
        String result = format.format(byteSize);
        if ("0".equals(result) || "0.00".equals(result)) {
            return "0.01KB";
        }
        return result + suffix;
    }

    /**
     * 格式化数字
     *
     * @param number 原数字
     * @param fmt    格式 如 "0.00  #.##"
     * @return
     */
    public static String formatNumber(double number, String fmt) {
        DecimalFormat format = new DecimalFormat(fmt);
        return format.format(number);
    }

    /**
     * 随机数 比如width=1，则结果为1-9
     *
     * @param width 几位的
     * @return
     */
    public static String random(int width) {

        //低效算法
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < width; i++) {
//			int n = new Random().nextInt(10);
//			sb.append(n);
//		}
//		return sb.toString();

        //高效算法
        int base = ((Double) Math.pow(10, width)).intValue();
        int correction = base / 10;
        int rand = new Random().nextInt(base - correction) + correction;
        return String.valueOf(rand);
    }

    /**
     * ================= 转换编码 =======================
     **/
    public static String iso2utf8(String str) {
        try {
            return new String(str.getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}