package com.weikbest.pro.saas.common.util.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * @author wisdomelon
 * @date 2019/6/11 0011
 * @project saas
 * @jdk 1.8
 */
public class CookUtil {

    /**
     * 设置cookie
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     *
     * @param request
     * @param cookieName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getCookie(HttpServletRequest request, String cookieName) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(cookieName)) {
                return URLDecoder.decode(cookies[i].getValue(), "utf-8");
            }
        }
        return null;
    }

    public static String[] getCookies(HttpServletRequest request) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        ArrayList<String> list = new ArrayList<String>();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            list.add(cookies[i].getName() + " = " + URLDecoder.decode(cookies[i].getValue(), "utf-8"));
        }
        return list.toArray(new String[0]);
    }
}
