package com.weikbest.pro.saas.sys.param;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaSubscribeService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.marketing.*;
import com.github.binarywang.wxpay.bean.media.MarketingImageUploadResult;
import com.github.binarywang.wxpay.bean.profitsharingV3.*;
import com.github.binarywang.wxpay.bean.request.WxPayDefaultRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundV3Result;
import com.github.binarywang.wxpay.bean.result.WxPaySandboxSignKeyResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingBusiFavorService;
import com.github.binarywang.wxpay.service.MarketingMediaService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.common.third.aliyun.logistics.AliyunWuliuService;
import com.weikbest.pro.saas.common.third.aliyun.logistics.config.AliyunWuliuCompanyResult;
import com.weikbest.pro.saas.common.third.wx.pay.WxPayServiceBuildFactory;
import com.weikbest.pro.saas.common.third.wx.pay.apiv3.CertificatesManagerUtil;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.constant.ConfigConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.service.PayConfigService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import com.weikbest.pro.saas.sys.param.util.ThirdConfigUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.subscribemsg.TemplateInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/17
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SaasBootApplication.class})
public class ThirdConfigTest {

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private MemoryService memoryService;

    @Resource
    private PayConfigService payConfigService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private WxPayServiceBuildFactory wxPayServiceBuildFactory;

    /**
     * 获取平台证书信息
     *
     * @throws WxPayException
     */
    @Test
    public void testGetV3Certificates() throws WxPayException {
        WxPayService wxPayService = thirdConfigService.wxPayService();
        String url = String.format("%s/v3/certificates", wxPayService.getPayBaseUrl());
        String response = wxPayService.getV3(url);
        System.out.println(response);
    }


    /**
     * 获取仿真测试系统的验签密钥 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=23_1
     * https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey
     *
     * @throws WxPayException
     */
    @Test
    public void testGetSandboxSignKeys() throws WxPayException {
        WxPayService wxPayService = thirdConfigService.wxPayService();
        wxPayService.getConfig().setUseSandboxEnv(true);

        WxPayDefaultRequest request = new WxPayDefaultRequest();
        request.checkAndSign(wxPayService.getConfig());

//        String url = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
        String url = "https://api.mch.weixin.qq.com/xdc/apiv2getsignkey/sign/getsignkey";
        String responseContent = wxPayService.post(url, request.toXML(), false);
        WxPaySandboxSignKeyResult result = BaseWxPayResult.fromXML(responseContent, WxPaySandboxSignKeyResult.class);
        result.checkResult(wxPayService, request.getSignType(), true);
        String sandboxSignKey = result.getSandboxSignKey();
        System.out.println(sandboxSignKey);
    }

    /**
     * 微信支付-下单, "https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_1.shtml"
     * <p>
     * "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi"
     *
     * @throws WxPayException
     */
    @Test
    public void testCreateOrderV3() throws WxPayException {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
//        request.setOutTradeNo()
//                .setNotifyUrl()
//                .setAmount(new WxPayUnifiedOrderV3Request.Amount().setCurrency().setTotal());
        WxPayUnifiedOrderV3Result.JsapiResult jsapiResult = wxPayService.createOrderV3(TradeTypeEnum.JSAPI, request);
        System.out.println(jsapiResult);
    }

    /**
     * 微信支付-退款申请  此接口只做退款申请操作，具体退款到账结果业务处理放在微信支付-退款结果通知接口中处理。https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_9.shtml
     * https://api.mch.weixin.qq.com/v3/refund/domestic/refunds
     *
     * @throws WxPayException
     */
    @Test
    public void testRefundV3() throws WxPayException {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        WxPayRefundV3Request request = new WxPayRefundV3Request();
//        request.setOutTradeNo().setNotifyUrl();
        WxPayRefundV3Result wxPayRefundV3Result = wxPayService.refundV3(request);
        System.out.println(wxPayRefundV3Result);
    }

    /**
     * 微信支付-分账-添加分账接收方 指定一个账户为分账收款方账号。https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_8.shtml
     * https://api.mch.weixin.qq.com/v3/profitsharing/receivers/add
     *
     * @throws WxPayException
     */
    @Test
    public void testAddProfitSharingReceiver() throws WxPayException {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        ProfitSharingReceiver request = new ProfitSharingReceiver();
//        request.setRelationType();
        ProfitSharingReceiver response = wxPayService.getProfitSharingV3Service().addProfitSharingReceiver(request);
        System.out.println(response);
    }

    /**
     * 微信支付-分账-请求分账 如是分账订单，支付成功后，在回调通知里立即调用分账请求接口，收货确认后,不用将分账钱再还给商家,如客户申请退款，需要请求分账回退还给商家，再调退款接口。https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_1.shtml
     * https://api.mch.weixin.qq.com/v3/profitsharing/orders
     *
     * @throws WxPayException
     */
    @Test
    public void testProfitSharing() throws WxPayException {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        ProfitSharingRequest request = new ProfitSharingRequest();
//        request.setReceivers();
        ProfitSharingResult response = wxPayService.getProfitSharingV3Service().profitSharing(request);
        System.out.println(response);
    }


    /**
     * 微信支付-分账-查询分账结果 https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_2.shtml
     * https://api.mch.weixin.qq.com/v3/profitsharing/orders/{out_order_no}?&transaction_id={transaction_id}"
     *
     * @throws WxPayException
     */
    @Test
    public void testGetProfitSharingResult() throws WxPayException {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        ProfitSharingResult response = wxPayService.getProfitSharingV3Service().getProfitSharingResult("", "");
        System.out.println(response);
    }


    /**
     * 微信支付-分账-请求分账回退  如果订单参与了分账，在退款时，先调此接口，将已分账的资金从分账接收方的账户回退给分账方，再发起退款。https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_3.shtml
     * https://api.mch.weixin.qq.com/v3/profitsharing/return-orders
     *
     * @throws WxPayException
     */
    @Test
    public void testProfitSharingReturn() throws WxPayException {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        ProfitSharingReturnRequest request = new ProfitSharingReturnRequest();
//        request.setOutOrderNo();
        ProfitSharingReturnResult response = wxPayService.getProfitSharingV3Service().profitSharingReturn(request);
        System.out.println(response);
    }


    /**
     * 获取小程序接口凭证(access_token的有效期目前为2个小时，需定时刷新)
     * https://api.weixin.qq.com/cgi-bin/token
     *
     * @throws WxErrorException
     */
    @Test
    public void testGetAccessToken() throws WxErrorException {
        WxMaService wxMaService = thirdConfigService.wxMaService();

        String accessToken = wxMaService.getAccessToken();
        System.out.println(accessToken);
    }


    /**
     * 获取小程序接口凭证(access_token的有效期目前为2个小时，需定时刷新)
     * https://api.weixin.qq.com/sns/jscode2session
     *
     * @throws WxErrorException
     */
    @Test
    public void testJsCode2SessionInfo() throws WxErrorException {
        WxMaService wxMaService = thirdConfigService.wxMaService();

        WxMaJscode2SessionResult result = wxMaService.jsCode2SessionInfo("");
        System.out.println(result);
    }


    /**
     * 获取小程序接口凭证(access_token的有效期目前为2个小时，需定时刷新)
     * https://api.weixin.qq.com/sns/jscode2session
     *
     * @throws WxErrorException
     */
    @Test
    public void testJsCode2SessionInfo1() throws WxErrorException {
        WxMaService wxMaService = thirdConfigService.wxMaService();

        WxMaPhoneNumberInfo result = wxMaService.getUserService().getNewPhoneNoInfo("");
        System.out.println(result);
    }

    /**
     * 获取小程序当前账号下的个人模板列表
     * https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate
     *
     * @throws WxErrorException
     */
    @Test
    public void testGetTemplateList() throws WxErrorException {
        WxMaService wxMaService = thirdConfigService.wxMaService();

        List<TemplateInfo> templateList = wxMaService.getSubscribeService().getTemplateList();
        System.out.println(templateList);
    }

    /**
     * 发送小程序订阅消息
     * https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate
     *
     * @throws WxErrorException
     */
    @Test
    public void testSendSubscribeMsg() throws WxErrorException {
        WxMaService wxMaService = thirdConfigService.wxMaService();

        WxMaSubscribeService subscribeService = wxMaService.getSubscribeService();
        WxMaSubscribeMessage wxMaSubscribeMessage = new WxMaSubscribeMessage();
        wxMaSubscribeMessage.setTemplateId("nW4IQiVlau-BKNcz2sge3QEpCSHrXoXM9xmyt8ni6Xk");
        wxMaSubscribeMessage.setToUser("oqT1g5JM4IBmk7yxEI-lGTnhs4Zc");
        wxMaSubscribeMessage.setPage("/");
        List<WxMaSubscribeMessage.MsgData> msgDataList = new ArrayList<>();
        msgDataList.add(new WxMaSubscribeMessage.MsgData("thing2", "新款男装T恤"));
        msgDataList.add(new WxMaSubscribeMessage.MsgData("number11", "10"));
        msgDataList.add(new WxMaSubscribeMessage.MsgData("amount9", "200元"));
        msgDataList.add(new WxMaSubscribeMessage.MsgData("date8", "2019/11/11"));
        msgDataList.add(new WxMaSubscribeMessage.MsgData("thing5", "请在13:57之前完成支付"));
        wxMaSubscribeMessage.setData(msgDataList);
        wxMaSubscribeMessage.setMiniprogramState(WxMaConstants.MiniProgramState.FORMAL);

        subscribeService.sendSubscribeMsg(wxMaSubscribeMessage);
    }

    /**
     * 阿里云物流公司查询
     */
    @Test
    public void testQueryAliyunWuliuCompany() throws Exception {
        AliyunWuliuService aliyunWuliuService = thirdConfigService.aliyunWuliuService();
        AliyunWuliuCompanyResult aliyunWuliuCompanyResult = aliyunWuliuService.queryAliyunWuliuCompany();
        System.out.println(aliyunWuliuCompanyResult);
    }

    /**
     * 微信短信配置
     */
    @Test
    public void testGenerate() throws Exception {
        // 获取短信服务码
//        String queryStr = TemplateEngineUtil.renderTemplate(appletSmsConfig.getAppletUrlParam(), appletUrlParam);
//        String jumpWxaEnvVersion = DictConstant.WxMiniappType.getWxMiniappTypeByCode(appletConfig.getWxMiniappType()).getJumpWxaEnvVersion();
//        WxMaGenerateSchemeRequest request = WxMaGenerateSchemeRequest.newBuilder()
//                .jumpWxa(WxMaGenerateSchemeRequest.JumpWxa.newBuilder().path(appletSmsConfig.getAppletPage()).query(queryStr).envVersion(jumpWxaEnvVersion).build())
//                .expireType(WeikbestConstant.ZERO_INT)
//                .build();
//        // weixin://dl/business/?t=XaZrVA3Gv4i
//        String generate = wxMaSchemeService.generate(request);
//        String code = AppletLinkUtil.getCode(generate);
    }


    /**
     * 商家券接口-设置商家券事件通知地址 API
     * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_7.shtml
     * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/callbacks
     *
     * @throws Exception
     */
    @Test
    public void testCreateBusiFavorCallbacks() throws Exception {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();

        BusiFavorCallbacksRequest request = new BusiFavorCallbacksRequest();
        request.setMchid(wxPayService.getConfig().getMchId());
        request.setNotifyUrl(memoryService.queryConfig(ConfigConstant.WX_REFUND_NOTIFY_URL));
        BusiFavorCallbacksResult busiFavorCallbacks = marketingBusiFavorService.createBusiFavorCallbacks(request);
        System.out.println(busiFavorCallbacks);
    }

    /**
     * 营销专用接口-图片上传API
     * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_0_1.shtml
     * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/favor/media/image-upload
     *
     * @throws Exception
     */
    @Test
    public void testImageUploadV3() throws Exception {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        MarketingMediaService marketingMediaService = wxPayService.getMarketingMediaService();

        File file = new File("XX");
        MarketingImageUploadResult marketingImageUploadResult = marketingMediaService.imageUploadV3(file);
        System.out.println(marketingImageUploadResult);

    }


    /**
     * 商家券接口-创建商家券API
     * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
     * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/stocks
     *
     * @throws Exception
     */
    @Test
    public void testCreateBusiFavorStocksV3() throws Exception {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();

        BusiFavorStocksCreateRequest request = new BusiFavorStocksCreateRequest();
        request.setStockName("");
        BusiFavorStocksCreateResult busiFavorStocksCreateResult = marketingBusiFavorService.createBusiFavorStocksV3(request);
        System.out.println(busiFavorStocksCreateResult);
    }


    /**
     * 商家券接口-创建商家券API
     * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_12.shtml
     * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/stocks/{stock_id}
     *
     * @throws Exception
     */
    @Test
    public void testUpdateBusiFavorStocksV3() throws Exception {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();

        BusiFavorStocksCreateRequest request = new BusiFavorStocksCreateRequest();
        request.setStockName("");
        String result = marketingBusiFavorService.updateBusiFavorStocksV3("", request);
        System.out.println(result);
    }


    /**
     * 商家券接口-查询商家券详情API
     * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_2.shtml
     * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/stocks/{stock_id}
     *
     * @throws Exception
     */
    @Test
    public void testGetBusiFavorStocksV3() throws Exception {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();

        BusiFavorStocksGetResult busiFavorStocksV3 = marketingBusiFavorService.getBusiFavorStocksV3("");
        System.out.println(busiFavorStocksV3);
    }

    /**
     * 商家券接口-核销用户券API
     * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_3.shtml
     * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/coupons/use
     *
     * @throws Exception
     */
    @Test
    public void testVerifyBusiFavorCouponsUseV3() throws Exception {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();

        BusiFavorCouponsUseRequest request = new BusiFavorCouponsUseRequest();
        request.setStockId("");

        BusiFavorCouponsUseResult busiFavorCouponsUseResult = marketingBusiFavorService.verifyBusiFavorCouponsUseV3(request);
        System.out.println(busiFavorCouponsUseResult);
    }

    /**
     * 商家券接口-使券失效API
     * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_15.shtml
     * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/coupons/deactivate
     *
     * @throws Exception
     */
    @Test
    public void testDeactiveBusiFavorCoupons() throws Exception {
        WxPayService wxPayService = thirdConfigService.wxPayService();

        MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();

        BusiFavorCouponsDeactivateRequest request = new BusiFavorCouponsDeactivateRequest();
        request.setStockId("");
        BusiFavorCouponsDeactivateResult busiFavorCouponsDeactivateResult = marketingBusiFavorService.deactiveBusiFavorCoupons(request);
        System.out.println(busiFavorCouponsDeactivateResult);
    }


    /**
     * 商家券接口-使券失效API
     * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_15.shtml
     * 接口链接：https://api.mch.weixin.qq.com/v3/marketing/busifavor/coupons/deactivate
     *
     * @throws Exception
     */
    @Test
    public void testPartnershipsBuild() throws Exception {
        WxPayService wxPayService = thirdConfigService.wxPayService();


        String stockId = "";
        String partnerMerchantId = "";


        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/marketing/partnerships/build");
        JSONObject reqdataJSON = new JSONObject();
        /** 合作方信息 **/
        JSONObject authorized_data = new JSONObject();
        authorized_data.put("business_type", "BUSIFAVOR_STOCK");
        authorized_data.put("stock_id", stockId);
        /** 被授权数据 **/
        JSONObject partner = new JSONObject();
        partner.put("type", "MERCHANT");
        partner.put("merchant_id", partnerMerchantId);
        reqdataJSON.put("authorized_data", authorized_data);
        reqdataJSON.put("partner", partner);
        // 请求body参数
        StringEntity entity = new StringEntity(reqdataJSON.toString(), "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Idempotency-Key", stockId);
        String response = wxPayService.postV3("https://api.mch.weixin.qq.com/v3/marketing/partnerships/build", httpPost);

        System.out.println(response);
    }

    @Test
    public void testCreateWxaCodeUnlimit() throws Exception {
//        AppletConfig appletConfig = appletConfigService.findById(prodAppletRelate.getAppletConfigId());
//        WxMaService wxMaService = appletConfigService.wxMaService(prodAppletRelate.getAppletAppId());
//        // 调用微信小程序图片生成接口
//        File wxaCodeUnlimitFile = qrcodeService.createWxaCodeUnlimit(scene, page, false, appletConfig.getWxMiniappType(), 430, false, new WxMaCodeLineColor(), false);
    }


    @Test
    public void testPartnerships() throws Exception {
        String partnerMerchantId = payConfigService.findByPayConfigType(DictConstant.PayConfigType.TYPE_3.getCode()).getWxPayMchId();
        WxPayService wxPayService = shopThirdService.findWxPayServiceById(1577680310580154368L);

//        String decode = URLUtil.decode("https://api.mch.weixin.qq.com/v3/marketing/partnerships?authorized_data=%7b%22business_type%22%3a%22BUSIFAVOR_STOCK%22%7d&partner=%7b%22type%22%3a%22MERCHANT%22%7d&offset=0&limit=50");
//
        String responseStr = ThirdConfigUtil.partnershipsBuild(wxPayService, "1225760000000049", partnerMerchantId);
        System.out.println(StrUtil.format("回流优惠券stockId:{}, 建立合作关系结果: {}", "1225760000000049", responseStr));


        String url = StrUtil.format("https://api.mch.weixin.qq.com/v3/marketing/partnerships?authorized_data=%7b%22business_type%22%3a%22BUSIFAVOR_STOCK%22%7d&partner=%7b%22type%22%3a%22MERCHANT%22%7d&offset=0&limit=50");
        String v3 = wxPayService.getV3(url);
        System.out.println(v3);


        // 原生调用
        String mchId = "1631844213";
        String apiclientKey = "D:/working/20220824/doc/需求梳理/微信支付商户证书/1631844213/apiclient_key.pem";
        String merchantSerialNumber = "2A9FB30C9CC6A82FE0CFC9C0368B3774AC2F8ECB";
        String apiV3Key = "GvYQA9uylPBAuVwuaZycIqlgVIcyKcP7";

        JSONObject bodyAsJSON = new JSONObject();
        CertificatesManagerUtil builder = new CertificatesManagerUtil();
        builder.setup(apiclientKey, mchId, merchantSerialNumber, apiV3Key);
        CloseableHttpClient httpClient = builder.httpClient;

        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("Accept", "application/json");

        // 完成签名并执行请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) { // 处理成功
                log.info("success,return body = " + EntityUtils.toString(response.getEntity()));
                // {"data":[{"authorized_data":{"business_type":"BUSIFAVOR_STOCK","scenarios":[],"stock_id":"1225760000000035"},"update_time":"2022-11-15T23:37:40+08:00","create_time":"2022-11-15T22:33:38+08:00","partner":{"merchant_id":"1601457001","type":"MERCHANT"},"build_time":"2022-11-15T22:33:38+08:00"}],"offset":0,"total_count":1,"limit":5}
            } else if (statusCode == 204) { // 处理成功，无返回Body
                log.info("success");
            } else {
                log.info("failed,resp code = " + statusCode + ",return body = "
                        + EntityUtils.toString(response.getEntity()));
            }
            bodyAsJSON = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        } finally {
            try {
                httpClient.close();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(bodyAsJSON);

    }

    /**
     * 查询投诉单列表API[https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_11.shtml]
     * https://api.mch.weixin.qq.com/v3/merchant-service/complaints-v2
     *
     * @throws Exception
     */
    @Test
    public void testComplaintsV2() throws Exception {
        WxPayService wxPayService = shopThirdService.findWxPayServiceById(1577680310580154368L);
        String mchId = wxPayService.getConfig().getMchId();

        String url = StrUtil.format("https://api.mch.weixin.qq.com/v3/merchant-service/complaints-v2?limit=50&offset=0&begin_date=2019-01-01&end_date=2019-01-01&complainted_mchid=" + mchId);
        String v3 = wxPayService.getV3(url);
        System.out.println(v3);
    }

}
