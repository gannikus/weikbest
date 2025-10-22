package com.weikbest.pro.saas.common.third.wx.util;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 *
 * @author andy
 * @date 2015-5-16 下午4:04:22
 */
public class StringUtil {

    /**
     * 判断字符串是否为空的正则表达式，空白字符对应的unicode编码
     */
    private static final String EMPTY_REGEX = "[\\s\\u00a0\\u2007\\u202f\\u0009-\\u000d\\u001c-\\u001f]+";
    private static final String NUM_REG = "(\\+|\\-)?\\s*\\d+(\\.\\d+)?";
    private static final String PUNCT_REG = "[^a-zA-Z0-9\\u4e00-\\u9fa5]";

    private StringUtil() {
        super();
    }

    /**
     * 出去null和""
     *
     * @param src
     * @return
     */
    public static String formatNull(String src) {
        return (src == null || "null".equals(src)) ? "" : src;
    }

    /**
     * 验证字符串是否为空
     *
     * @param input
     * @return
     */
    public static boolean isEmpty(String input) {
        return input == null || input.equals("") || input.matches(EMPTY_REGEX);
    }

    /**
     * @param strs
     * @return Integer[]
     * @Description: 将JSONArray转为int数组
     */
    public static Integer[] JSONArrayToInt(JSONArray strs) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < strs.size(); i++) {
            String s = (String) strs.get(i);
            if (StringUtils.isBlank(s) || s == "") {
                continue;
            }
            list.add(Integer.parseInt(s));

        }
        return list.toArray(new Integer[0]);
    }

    /**
     * @param strs
     * @return String[]
     * @Description: 将JSONArray转为String数组
     */
    public static String[] JSONArrayToStr(JSONArray strs) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < strs.size(); i++) {
            list.add((String) strs.get(i));
        }
        return list.toArray(new String[0]);
    }

    public static boolean isNotEmpty(String input) {
        return !isEmpty(input);
    }

    /**
     * 判断是否数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }

        if (str.trim().matches(NUM_REG)) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否包含有乱码的数据,如果字符串中包含有替换字符就认为是乱码
     *
     * @param str
     * @return
     */
    public static boolean containUnreadableCode(String str) {
        return contain(str, "\\ufffd");
    }

    /**
     * 判读是否包含数字
     *
     * @param str
     * @return
     */
    public static boolean containNumber(String str) {
        return contain(str, "\\d");
    }

    /**
     * 判断是否包含a-zA-Z_0-9
     *
     * @param str
     * @return
     */
    public static boolean containWord(String str) {
        return contain(str, "\\w");
    }

    /**
     * 是否包含有标点符号
     *
     * @param str
     * @return
     */
    public static boolean containPunct(String str) {
        return contain(str, PUNCT_REG);
    }

    public static boolean contain(String str, String regex) {
        if (isEmpty(str) || isEmpty(regex)) {
            return false;
        }

        if (str.trim().matches(regex)) {
            return true;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        }

        return false;
    }

    /**
     * 替换所有的（不区分大小写）
     *
     * @param input
     * @param regex
     * @param replacement
     * @return
     */
    public static String replaceAll(String input, String regex,
                                    String replacement) {
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(input)
                .replaceAll(replacement);
    }

    /**
     * 移除所有的空格
     *
     * @param text
     * @return
     */
    public static String removeAllSpace(String text) {
        if (isEmpty(text)) {
            return text;
        }

        return text.replaceAll("[ ]+", "");
    }

    /**
     * 移除字符串中的所有的中英文标点符号
     *
     * @param str
     * @return
     */
    public static String removeAllPunct(String str) {
        if (isEmpty(str)) {
            return str;
        }

        return str.replaceAll(PUNCT_REG, "");
    }

    /**
     * 计算str中包含多少个子字符串sub
     *
     * @param str
     * @param sub
     * @return
     */
    public static int countMatches(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }

        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }

        return count;
    }

    /**
     * 获得源字符串的一个子字符串
     *
     * @param str        ：源字符串
     * @param beginIndex ：开始索引（包括）
     * @param endIndex   ：结束索引（不包括）
     * @return
     */
    public static String substring(String str, int beginIndex, int endIndex) {
        if (isEmpty(str)) {
            return str;
        }

        int length = str.length();

        if (beginIndex >= length || endIndex <= 0 || beginIndex >= endIndex) {
            return null;
        }

        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if (endIndex > length) {
            endIndex = length;
        }

        return str.substring(beginIndex, endIndex);
    }

    /**
     * 计算str中包含子字符串sub所在位置的前一个字符或者后一个字符和sub所组成的新字符串
     *
     * @param str
     * @param sub
     * @return
     */
    public static Set<String> substring(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return null;
        }

        Set<String> result = new HashSet<String>();
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            String temp = substring(str, idx - 1, idx + sub.length());
            if (!isEmpty(temp)) {
                temp = removeAllPunct(temp);
                if (!sub.equalsIgnoreCase(temp) && !containWord(temp)) {
                    result.add(temp);
                }

            }

            temp = substring(str, idx, idx + sub.length() + 1);
            if (!isEmpty(temp)) {
                temp = removeAllPunct(temp);
                if (!sub.equalsIgnoreCase(temp) && !containWord(temp)) {
                    result.add(temp);
                }
            }

            idx += sub.length();
        }

        return result;
    }

    /**
     * 过滤掉XML中无法解析的非法字符
     *
     * @param content
     * @return
     */
    public static String wrapXmlContent(String content) {
        if (isEmpty(content)) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            if ((ch == '\t') || (ch == '\n') || (ch == '\r')
                    || ((ch >= ' ') && (ch <= 55295))
                    || ((ch >= 57344) && (ch <= 65533))
                    || ((ch >= 65536) && (ch <= 1114111))) {
                result.append(ch);
            }
        }

        return result.toString();
    }

    /**
     * 判断字符串的长度
     *
     * @param str
     * @return
     */
    public static boolean overLength(String str) {
        if (isEmpty(str)) {
            return false;
        }

        return str.length() > 1 ? true : false;
    }

    /**
     * @param str
     * @param n   控制长度
     * @return String
     * @Description: 控制字符串长度超出补充省略号
     */
    public static String overSubstr(String str, Integer n) {
        if (n < 1) {
            return "";
        }
        if (str.length() > n)
            return str.substring(0, n) + "...";
        return str;
    }

    /**
     * 字符串中含有特殊字符的处理
     *
     * @param str
     * @return
     */
    public static String specialStr(String str) {
        str = str.replaceAll("[^\\u4e00-\\u9fa5 | 0-9| a-zA-Z | \\.]+", " ")
                .replaceAll("[\\.]{2,}", " ").trim();
        return str;
    }

    /**
     * 将特殊符号去掉，但是保留空格
     *
     * @param str
     * @return
     */
    public static String replaceInValidateChar(String str) {
        return str.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5\\s+]", " ");
    }

    /**
     * 返回字符串对应的unicode编码
     *
     * @param str
     * @return
     */
    public static String[] toHexString(String str) {
        char[] chars = str.toCharArray();

        String[] result = new String[chars.length];

        for (int i = 0; i < chars.length; i++) {
            result[i] = Integer.toHexString((int) chars[i]);
        }

        return result;
    }

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    public static boolean isUrl(String src) {
        String regex = "http[s]?:\\/\\/([\\w-]+\\.[\\w-]+)(\\.[\\w-])+(:\\d{2,10})?.*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(src);
        return matcher.matches();
    }

    /**
     * sql 查询转义
     *
     * @param str
     * @return
     */
    public static String escapeSql(String str) {
        if (StringUtil.isNotEmpty(str)) {
            StringBuffer strbuff = new StringBuffer();
            for (String s : str.split("")) {
                if (s.equals("%") || s.equals("_") || s.equals("\\")) {
                    strbuff.append("\\");
                }
                strbuff.append(s);
            }
            return strbuff.toString();
        }
        return str;
    }

    /**
     * HH
     *
     * @return
     */
    public static String getNowHH() {
        SimpleDateFormat df = new SimpleDateFormat("HH");//设置日期格式
        return df.format(new Date());
    }

    /**
     * yyyy-MM-dd
     *
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        return df.format(new Date());
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }

    /**
     * 生成订单号 订单号 规则为年月日时分秒豪秒   + 3位随机数，共20位
     *
     * @return
     */
    public static String getOrderNumber() {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("000");
        String no = new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()) + df.format(random.nextInt(100));
        return no;
    }

    /**
     * 生成订单号 订单号 规则为年月日时分秒豪秒   + 5位随机数，共22位
     *
     * @return
     */
    public static String getNoncestr() {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("00000");
        String no = new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()) + df.format(random.nextInt(10000));
        return no;
    }

    /**
     * 生成订单号 订单号 规则为年月日时分秒豪秒   + 7位随机数，共24位
     *
     * @return
     */
    public static String getOutrefundno() {
        Random random = new Random();
        DecimalFormat df = new DecimalFormat("0000000");
        String no = new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()) + df.format(random.nextInt(1000000));
        return no;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 指定的字符串中获取参数
     * @param url
     * @param name
     * @return
     */
    public static String getUrlparameter(String url, String name) {
        url += "&";
        String pattern = "(?<="+name+"=).*?(?=&)";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(url);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return null;
        }
    }


}
