package com.weikbest.pro.saas.common.third.wx.util;


import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.util.*;
import java.util.Map.Entry;


/**
 * 创建时间：2016年11月9日 下午4:16:32
 *
 * @author andy
 * @version 2.2
 */
public class HttpUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final int CONNECT_TIME_OUT = 5000; // 链接超时时间3秒

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)
            .build();

    private static SSLContext wx_ssl_context = null; // 微信支付ssl证书

    /**
     * @param url     请求地址
     * @param params  参数
     * @param headers headers参数
     * @return 请求失败返回null
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) {

        CloseableHttpClient httpClient = null;
        if (params != null && !params.isEmpty()) {
            StringBuffer param = new StringBuffer();
            boolean flag = true; // 是否开始
            for (Entry<String, String> entry : params.entrySet()) {
                if (flag) {
                    param.append("?");
                    flag = false;
                } else {
                    param.append("&");
                }
                param.append(entry.getKey()).append("=");

                try {
                    param.append(URLEncoder.encode(entry.getValue(), DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    // 编码失败
                }
            }
            url += param.toString();
        }

        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom().setDefaultRequestConfig(REQUEST_CONFIG).build();
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }

    /**
     * @param url 请求地址
     * @return 请求失败返回null
     * @description 功能描述: get 请求
     */
    public static String get(String url) {
        return get(url, null);
    }

    /**
     * @param url    请求地址
     * @param params 参数
     * @return 请求失败返回null
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    /**
     * @param url    请求地址
     * @param params 参数
     * @return 请求失败返回null
     * @description 功能描述: post 请求
     */
    public static String post(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            for (Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom().setDefaultRequestConfig(REQUEST_CONFIG).build();
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }

    /**
     * @param url 请求地址
     * @param s   参数xml
     * @return 请求失败返回null
     * @description 功能描述: post 请求
     */
    public static String post(String url, String s) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom().setDefaultRequestConfig(REQUEST_CONFIG).build();
            httpPost.setEntity(new StringEntity(s, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }

    /**
     * 获取request请求中的参数值
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
     * @param url 请求地址
     * @param s   参数xml
     * @return 请求失败返回null
     * @description 功能描述: post https请求，服务器双向证书验证
     */
    public static String posts(String url, String s, String wxCertUrl, String mchId) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String body = null;
        try {
            httpClient = HttpClients.custom().setDefaultRequestConfig(REQUEST_CONFIG).setSSLSocketFactory(getSSLConnectionSocket(wxCertUrl, mchId)).build();
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(new StringEntity(s, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
            EntityUtils.consume(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }

    // 获取ssl connection链接
    private static SSLConnectionSocketFactory getSSLConnectionSocket(String wxCertUrl, String mchId) {
        return new SSLConnectionSocketFactory(getWxSslContext(wxCertUrl, mchId),
                new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"}, null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    }

    private static SSLContext getWxSslContext(String wxCertUrl, String mchId) {
        try {
            KeyStore keystore = KeyStore.getInstance("PKCS12");
            char[] keyPassword = mchId.toCharArray(); // 证书密码
            FileInputStream instream = new FileInputStream(wxCertUrl);
            keystore.load(instream, keyPassword);
            wx_ssl_context = SSLContexts.custom().loadKeyMaterial(keystore, keyPassword).build();
            instream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wx_ssl_context;
    }

    public static String creSmallSign(String characterEncoding, SortedMap<Object, Object> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = getMD5(sb.toString()).toUpperCase();
        return sign;
    }

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
            e.printStackTrace();
            return null;
        }
    }
}