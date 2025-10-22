package com.weikbest.pro.saas.common.third.wx.pay.apiv3;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.weikbest.pro.saas.common.third.wx.util.CommonUtil;
import com.weikbest.pro.saas.common.third.wx.util.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * @author ql
 */
@Slf4j
public class SignUtil {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";
    public static final int KEY_SIZE = 2048;
    public static String certificates = "https://api.mch.weixin.qq.com/v3/certificates";
    private static String CHARSET_ENCODING = "UTF-8";
    private static String ALGORITHM = "SHA256withRSA";

    /**
     * 验证签名
     *
     * @param token     微信服务器token，在env.properties文件中配置的和在开发者中心配置的必须一致
     * @param signature 微信服务器传过来sha1加密的证书签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return
     */
    public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序  
        Arrays.sort(arr);

        // 将三个参数字符串拼接成一个字符串进行sha1加密  
        String tmpStr = SHA1.encode(arr[0] + arr[1] + arr[2]);

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信  
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 签名
     *
     * @param srcData
     * @param privateKeyPath
     * @param privateKeyPwd
     * @return
     */
    public static String sign(String srcData, String privateKeyPath, String privateKeyPwd) {
        if (srcData == null || privateKeyPath == null || privateKeyPwd == null) {
            return "";
        }
        try {
            // 获取证书的私钥
            PrivateKey key = readPrivate(privateKeyPath, privateKeyPwd);
            // 进行签名服务
            Signature signature = Signature.getInstance(ALGORITHM);
            signature.initSign(key);
            signature.update(srcData.getBytes(CHARSET_ENCODING));
            byte[] signedData = signature.sign();
            return Base64.getEncoder().encodeToString(signedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 验签
     *
     * @param srcData
     * @param signedData
     * @param publicKeyPath
     * @return
     */
    public static boolean verify(String srcData, String signedData, String publicKeyPath) {
        if (srcData == null || signedData == null || publicKeyPath == null) {
            return false;
        }
        try {
            PublicKey publicKey = readPublic(publicKeyPath);
            Signature sign = Signature.getInstance(ALGORITHM);
            sign.initVerify(publicKey);
            sign.update(srcData.getBytes(CHARSET_ENCODING));
            return sign.verify(Base64.getDecoder().decode(signedData));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 读取公钥
     *
     * @param publicKeyPath
     * @return
     */
    private static PublicKey readPublic(String publicKeyPath) {
        if (publicKeyPath == null) {
            return null;
        }
        PublicKey pk = null;
        FileInputStream bais = null;
        try {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            bais = new FileInputStream(publicKeyPath);
            X509Certificate cert = (X509Certificate) certificatefactory.generateCertificate(bais);
            pk = cert.getPublicKey();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pk;
    }

    /**
     * 读取私钥
     *
     * @param privateKeyPath
     * @param privateKeyPwd
     * @return
     */
    public static PrivateKey readPrivate(String privateKeyPath, String privateKeyPwd) {
        if (privateKeyPath == null || privateKeyPwd == null) {
            return null;
        }
        InputStream stream = null;
        try {
            // 获取JKS 服务器私有证书的私钥，取得标准的JKS的 KeyStore实例
            KeyStore store = KeyStore.getInstance("JKS");
            stream = new FileInputStream(new File(privateKeyPath));
            // jks文件密码，根据实际情况修改
            store.load(stream, privateKeyPwd.toCharArray());
            // 获取jks证书别名
            Enumeration en = store.aliases();
            String pName = null;
            while (en.hasMoreElements()) {
                String n = (String) en.nextElement();
                if (store.isKeyEntry(n)) {
                    pName = n;
                }
            }
            // 获取证书的私钥
            PrivateKey key = (PrivateKey) store.getKey(pName,
                    privateKeyPwd.toCharArray());
            return key;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static JSONObject httpGet(String url, String sign, String UserAgent) {
        // get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", "WECHATPAY2-SHA256-RSA2048 " + sign);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-Type", "application/json");
            request.setHeader("User-Agent", UserAgent);
            HttpResponse response = client.execute(request);
            /** 请求发送成功，并得到响应 **/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /** 读取服务器返回过来的json字符串数据 **/
                String strResult = EntityUtils.toString(response.getEntity());
                /** 把json字符串转换成json对象 **/
                jsonResult = JSONObject.parseObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                log.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            log.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    public static JSONObject httpPost(String url, String sign, String UserAgent) {
        JSONObject jsonResult = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(url);
        try {
            request.setHeader("Authorization", "WECHATPAY2-SHA256-RSA2048 " + sign);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-Type", "application/json");
            request.setHeader("User-Agent", UserAgent);
            HttpResponse result = httpClient.execute(request);
            url = URLDecoder.decode(url, "UTF-8");
            /** 请求发送成功，并得到响应 **/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /** 读取服务器返回过来的json字符串数据 **/
                    str = EntityUtils.toString(result.getEntity());
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


    /**
     * 获取证书。
     *
     * @param filename 证书文件路径 (required)
     * @return X509证书
     */
    public static X509Certificate getCertificate(String filename) throws IOException {
        InputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(bis);
            cert.checkValidity();
            return cert;
        } catch (CertificateExpiredException e) {
            throw new RuntimeException("证书已过期", e);
        } catch (CertificateNotYetValidException e) {
            throw new RuntimeException("证书尚未生效", e);
        } catch (CertificateException e) {
            throw new RuntimeException("无效的证书文件", e);
        } finally {
            bis.close();
        }
    }

    public static String buildMessage(String method, String url, long timestamp, String nonceStr, String body) {
		/*String canonicalUrl = url.encodedPath();
		if (url.encodedQuery() != null) {
			canonicalUrl += "?" + url.encodedQuery();
		}*/

        return method + "\n" + url + "\n" + timestamp + "\n" + nonceStr + "\n" + body + "\n";
    }

    public static String getToken(String method, String url, String body, String apiclientCert, String mchid, String apiclientCertPem) throws IOException {
        String nonceStr = CommonUtil.getMD5(String.valueOf(new Random().nextInt(10000)));
        long timestamp = System.currentTimeMillis() / 1000;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        String signature = null;
        try {
            signature = sign(message.getBytes("utf-8"), apiclientCert, mchid);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (SignatureException e) {

            e.printStackTrace();
        }
        X509Certificate certificate = getCertificate(apiclientCertPem);
        String sreial_no = certificate.getSerialNumber().toString(16);
        return "mchid=\"" + mchid + "\"," + "nonce_str=\"" + nonceStr + "\"," + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + sreial_no + "\"," + "signature=\"" + signature + "\"";
    }

    public static X509Certificate getToken2(String method, String url, String body, String apiclientCert, String mchid, String apiclientCertPem) throws IOException {
        String nonceStr = CommonUtil.getMD5(String.valueOf(new Random().nextInt(10000)));
        long timestamp = System.currentTimeMillis() / 1000;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        String signature = null;
        try {
            signature = sign(message.getBytes("utf-8"), apiclientCert, mchid);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (SignatureException e) {

            e.printStackTrace();
        }
        return getCertificate(apiclientCertPem);
    }

    /**
     * 获取平台证书列表
     *
     * @param userAgent
     * @return JSONObject
     * @throws IOException
     */
    public static JSONObject getWechatpaySerial(String userAgent, String apiclientCert, String mchid, String apiclientCertPem) throws IOException {
        String sing = getToken("GET", "/v3/certificates", "", apiclientCert, mchid, apiclientCertPem);
        return httpGet(certificates, sing, userAgent);
    }

    /**
     * 获取平台证书key
     *
     * @return String
     * @throws IOException
     */
    public static String getWechatpayKey(String userAgent, String apiclientCert, String mchid, String apiclientCertPem, String ApiV3Key) throws IOException {
        String sing = getToken("GET", "/v3/certificates", "", apiclientCert, mchid, apiclientCertPem);
        JSONObject json = httpGet(certificates, sing, userAgent);
        JSONArray array = (JSONArray) json.get("data");
        json = (JSONObject) array.get(0);
        json = (JSONObject) json.get("encrypt_certificate");
        byte[] associatedData = json.get("associated_data").toString().getBytes();
        byte[] nonce = json.get("nonce").toString().getBytes();
        String ciphertext = json.get("ciphertext").toString();
        byte[] buffer = ApiV3Key.getBytes();
        AesUtil aesUtilDto = new AesUtil(buffer);
        String aes = "";
        try {
            aes = aesUtilDto.decryptToString(associatedData, nonce, ciphertext);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return aes;
    }

    /**
     * pacth请求根据URL查询微信ApiV3接口返回的信息
     *
     * @param url
     * @param sign
     * @param userAgent
     * @param jsonParam
     * @return json字符串
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String doHttpClientPostRequestForSSL(String url, String sign, String userAgent, JSONObject jsonParam) {
        BufferedReader in = null;
        String responseAsString = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPatch httpPost = new HttpPatch(url);
        httpPost.setHeader("Authorization", "WECHATPAY2-SHA256-RSA2048 " + sign);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("User-Agent", userAgent);
        httpPost.setHeader("x-http-method-override", "PATCH");
        if (null != jsonParam) {
            // 解决中文乱码问题
            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
        }
        HttpResponse response;
        try {
            response = httpClient.execute(httpPost);
            responseAsString = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return responseAsString;
    }

    /**
     * 数据密文
     *
     * @param params
     * @return boolean
     */
    public static com.alibaba.fastjson.JSONObject resourceJson(Map params) {
        com.alibaba.fastjson.JSONObject resourceJson = null;
        com.alibaba.fastjson.JSONObject resource = com.alibaba.fastjson.JSONObject.parseObject(params.get("resource").toString());
        if (null != resource) {
            byte[] associatedData = resource.get("associated_data").toString().getBytes();
            byte[] nonce = resource.get("nonce").toString().getBytes();
            String ciphertext = resource.get("ciphertext").toString();
            String ApiV3Key = "YOUR_API_V3_KEY"; // 替换为实际的API V3密钥
            byte[] buffer = ApiV3Key.getBytes();
            AesUtil aesUtilDto = new AesUtil(buffer);
            String aes = "";
            try {
                aes = aesUtilDto.decryptToString(associatedData, nonce, ciphertext);
                resourceJson = com.alibaba.fastjson.JSONObject.parseObject(aes);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resourceJson;
    }

    public static String sign(byte[] message, String privateKeyPath, String privateKeyPwd) throws SignatureException, IOException {
        PrivateKey key = readPrivate(privateKeyPath, privateKeyPwd);
        Signature sign = null;
        try {
            sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(key);
            sign.update(message);
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        } catch (InvalidKeyException e) {

            e.printStackTrace();
        } catch (SignatureException e) {

            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    /**
     * 获取私钥。
     *
     * @param filename 私钥文件路径 apiclient_key.pem
     * @return 私钥对象
     */
    public static PrivateKey getPrivateKey(String filename) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filename)), "utf-8");
        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "").replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }

    /**
     * 获取微信支付api V3支付证书
     *
     * @return
     */
    public static X509Certificate getCertificate() {
        try {
            return PemUtil.loadCertificate(new FileInputStream("D:/Program Files/微信证书/微客盲盒/apiclient_cert.pem"));
        } catch (FileNotFoundException e) {
            log.error("加载微信支付APIv3支付证书失败{}", e.getMessage(), e);
        }
        return null;
    }
}