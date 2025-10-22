package com.weikbest.pro.saas.common.third.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * HttpServletRequest帮助类
 */
public class RequestUtils {

    /**
     * 通过request获取post传输过来的json
     *
     * @param request
     * @return
     */
    public static String getJsonString(HttpServletRequest request) {
        BufferedReader br;
        String s;
        try {
            br = request.getReader();
            StringBuffer sb = new StringBuffer();
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取QueryString的参数，并使用URLDecoder以UTF-8格式转码。如果请求是以post方法提交的，
     * 那么将通过HttpServletRequest#getParameter获取。
     *
     * @param request web请求
     * @param name    参数名称
     * @return
     */
    public static String getQueryParam(HttpServletRequest request, String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        if (request.getMethod().equalsIgnoreCase(Const.POST)) {
            return request.getParameter(name);
        }
        String s = request.getQueryString();
        if (StringUtils.isBlank(s)) {
            return null;
        }
        try {
            s = URLDecoder.decode(s, Const.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] values = parseQueryString(s).get(name);
        if (values != null && values.length > 0) {
            return values[values.length - 1];
        } else {
            return null;
        }
    }

    public static Map<String, Object> getQueryParams(HttpServletRequest request) {
        Map<String, String[]> map;
        if (request.getMethod().equalsIgnoreCase(Const.POST)) {
            map = request.getParameterMap();
        } else {
            String s = request.getQueryString();
            if (StringUtils.isBlank(s)) {
                return new HashMap<String, Object>();
            }
            try {
                s = URLDecoder.decode(s, Const.UTF8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map = parseQueryString(s);
        }

        Map<String, Object> params = new HashMap<String, Object>(map.size());
        int len;
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            len = entry.getValue().length;
            if (len == 1) {
                params.put(entry.getKey(), entry.getValue()[0]);
            } else if (len > 1) {
                params.put(entry.getKey(), entry.getValue());
            }
        }
        return params;
    }

    /**
     * Parses a query string passed from the client to the server and builds a
     * <code>HashTable</code> object with key-value pairs. The query string
     * should be in the form of a string packaged by the GET or POST method,
     * that is, it should have key-value pairs in the form <i>key=value</i>,
     * with each pair separated from the next by a &amp; character.
     *
     * <p>
     * A key can appear more than once in the query string with different
     * values. However, the key appears only once in the hashtable, with its
     * value being an array of strings containing the multiple values sent by
     * the query string.
     *
     * <p>
     * The keys and values in the hashtable are stored in their decoded form, so
     * any + characters are converted to spaces, and characters sent in
     * hexadecimal notation (like <i>%xx</i>) are converted to ASCII characters.
     *
     * @param s a string containing the query to be parsed
     * @return a <code>HashTable</code> object built from the parsed key-value
     * pairs
     * @throws IllegalArgumentException if the query string is invalid
     */
    public static Map<String, String[]> parseQueryString(String s) {
        String valArray[] = null;
        if (s == null) {
            throw new IllegalArgumentException();
        }
        Map<String, String[]> ht = new HashMap<String, String[]>();
        StringTokenizer st = new StringTokenizer(s, "&");
        while (st.hasMoreTokens()) {
            String pair = (String) st.nextToken();
            int pos = pair.indexOf('=');
            if (pos == -1) {
                continue;
            }
            String key = pair.substring(0, pos);
            String val = pair.substring(pos + 1, pair.length());
            if (ht.containsKey(key)) {
                String oldVals[] = (String[]) ht.get(key);
                valArray = new String[oldVals.length + 1];
                for (int i = 0; i < oldVals.length; i++) {
                    valArray[i] = oldVals[i];
                }
                valArray[oldVals.length] = val;
            } else {
                valArray = new String[1];
                valArray[0] = val;
            }
            ht.put(key, valArray);
        }
        return ht;
    }

    public static Map<String, String> getRequestMap(HttpServletRequest request, String prefix) {
        return getRequestMap(request, prefix, false);
    }

    public static Map<String, String> getRequestMapWithPrefix(HttpServletRequest request, String prefix) {
        return getRequestMap(request, prefix, true);
    }

    private static Map<String, String> getRequestMap(HttpServletRequest request, String prefix,
                                                     boolean nameWithPrefix) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> names = request.getParameterNames();
        String name, key, value;
        while (names.hasMoreElements()) {
            name = names.nextElement();
            if (name.startsWith(prefix)) {
                key = nameWithPrefix ? name : name.substring(prefix.length());
                value = StringUtils.join(request.getParameterValues(name), ',');
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 获取访问者IP
     * <p>
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * <p>
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (ip.contains("../") || ip.contains("..\\")) {
                return "";
            }
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                ip = ip.substring(0, index);
            }
            if (ip.contains("../") || ip.contains("..\\")) {
                return "";
            }
            return ip;
        } else {
            ip = request.getRemoteAddr();
            if (ip.contains("../") || ip.contains("..\\")) {
                return "";
            }
            return ip;
        }
    }

    /**
     * httpPost
     *
     * @param url       路径
     * @param jsonParam 参数
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam) {
        return httpPost(url, jsonParam, false);
    }

    /**
     * post请求
     *
     * @param url            url地址
     * @param jsonParam      参数
     * @param noNeedResponse 不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /** 请求发送成功，并得到响应 **/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /** 读取服务器返回过来的json字符串数据 **/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /** 把json字符串转换成json对象 **/
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    public static JSONObject httpPostImage(String url, JSONObject jsonParam) {
        JSONObject jsonResult = new JSONObject();
        RestTemplate rest = new RestTemplate();
        try {
            JSONObject lineColor = new JSONObject();
            lineColor.put("r", 0);
            lineColor.put("g", 0);
            lineColor.put("b", 0);

            jsonParam.put("width", 450);
            jsonParam.put("line_color", lineColor);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<String>(jsonParam.toString(), headers);

            ResponseEntity<byte[]> responseEntity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            byte[] result = responseEntity.getBody();
            jsonResult.put("error", "0");
            jsonResult.put("imgByte", result);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("error", "-1");
        }
        return jsonResult;
    }

    public static JSONObject httpPostImage(String url, JSONObject jsonParam, String imgPath) {
        JSONObject jsonResult = new JSONObject();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {

            jsonResult = httpPostImage(url, jsonParam);
            byte[] result = jsonResult.getBytes("imgByte");

            inputStream = new ByteArrayInputStream(result);
            File file = new File(imgPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();

            jsonResult.put("imgPath", imgPath);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("error", "-1");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResult;
    }

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static JSONObject httpGet(String url) {
        // get请求返回结果
        JSONObject jsonResult = null;
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /** 请求发送成功，并得到响应 **/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /** 读取服务器返回过来的json字符串数据 **/
                String strResult = EntityUtils.toString(response.getEntity());
                /** 把json字符串转换成json对象 **/
                // jsonResult.parse(strResult);
                jsonResult = JSONObject.parseObject(new String(strResult.getBytes("ISO-8859-1"), "UTF-8"));
                //url = URLDecoder.decode(url, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
