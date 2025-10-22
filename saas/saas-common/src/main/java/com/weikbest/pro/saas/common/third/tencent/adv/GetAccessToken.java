package com.weikbest.pro.saas.common.third.tencent.adv;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/31
 */

import com.tencent.ads.ApiContextConfig;
import com.tencent.ads.ApiException;
import com.tencent.ads.TencentAds;
import com.tencent.ads.model.OauthTokenResponseData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAccessToken {

    /**
     * 应用 ID
     */
    public static Long CLIENT_ID = 1112012343L;
    /**
     * client_secret
     */
    public static String CLIENT_SECRET = "afhNr91Z1voV1Bg3";
    /**
     * OAuth 认证 code，可通过获取 Authorization Code 接口获取
     */
    public static String AUTHORIZATION_CODE = "5615f6ae518b633831a29f4fe60147ec";// 授权代码只能使用1次
    /**
     * 应用回调地址
     */
    public static String REDIRECT_URI = "https://saas.wkyxsc.com/platformDocking/tencent/ads/authorizationAccessToken";
    /**
     * TencentAds
     */
    public TencentAds tencentAds;

    public void init() {
        this.tencentAds = TencentAds.getInstance();
        this.tencentAds.init(new ApiContextConfig().isDebug(true));
        // oauth/token不提供沙箱环境
        this.tencentAds.useProduction();
    }

    public OauthTokenResponseData getOauthTokenResponseData(Long clientId, String clientSecret, String authorizationCode, String redirectUri) throws ApiException {
        OauthTokenResponseData responseData =
                tencentAds
                        .oauth()
                        .oauthToken(
                                clientId,
                                clientSecret,
                                "authorization_code",
                                authorizationCode,
                                null,
                                redirectUri);
        if (responseData != null) {
            String accessToken = responseData.getAccessToken();
            tencentAds.setAccessToken(accessToken);
            return responseData;
        }
        return null;
    }
}
