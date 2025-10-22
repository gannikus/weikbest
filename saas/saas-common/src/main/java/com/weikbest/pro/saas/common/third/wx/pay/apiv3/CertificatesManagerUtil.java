package com.weikbest.pro.saas.common.third.wx.pay.apiv3;


import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.After;
import org.junit.Before;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;


public class CertificatesManagerUtil {
    public CloseableHttpClient httpClient;
    public CertificatesManager certificatesManager;
    public Verifier verifier;

    @Before
    public void setup(String apiclientKey, String merchantId, String merchantSerialNumber, String apiV3Key) throws Exception {
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(new FileInputStream(apiclientKey));
        // 获取证书管理器实例
        certificatesManager = CertificatesManager.getInstance();
        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(merchantId, new WechatPay2Credentials(merchantId,
                        new PrivateKeySigner(merchantSerialNumber, merchantPrivateKey)),
                apiV3Key.getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier
        verifier = certificatesManager.getVerifier(merchantId);
        // 构造httpclient
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(merchantId, merchantSerialNumber, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier))
                .build();
    }

    @After
    public void after() throws IOException {
        httpClient.close();
    }
}
