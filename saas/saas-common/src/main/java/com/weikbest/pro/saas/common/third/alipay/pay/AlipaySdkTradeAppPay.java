package com.weikbest.pro.saas.common.third.alipay.pay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.response.AlipayFundAccountQueryResponse;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.alipay.config.AlipayConfig;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlipaySdkTradeAppPay {

    public static final String SUCCESS = "10000";

    private static final String appId = "2021002145628669";// 从app配置表里取支付宝对应的alipay_app_appid字段。
    private static final String gateway = "https://openapi.alipay.com/gateway.do";
    private static final String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCgSp7wazzkVQZJa0fTa4v8kcIFsxa1um6V+BVZp6x4OZmu6fO/aseKqzp9+U4Kf72J3S6SR5uGOeBiRn/9pzy76ZbFoPtNpP71lUr8uVsBHmptuIigeqgsJDGStrhBPyuSGetQMk0Zvnpf9SbzI2vbkW4v3lf18Tb8nhEPm1svm/nM62Mx/csKHi4VqpPlOKTWvDhutthpUiuXNoy0X1K4JnCEjLqC6g8WeyFsXx8948Uox47/YH0iZ/EVpGqs9KPp0FTlkdiSVbkAI7p7WmH4F4DTAsHBj9xfHO1CW92cHjKpZRjewegHAxew1Q/QIuonPubaYyb7noQpHLdhAsyhAgMBAAECggEAVS7SwFl9BqrYqHPGu+hPgG3FelnuG61zz2cDkZ213j5eGcJisrsASAUvFHtEqfewtm9ArKQ5Vl+ziwaERjx13jcI1QX0BxNEGya3rw6h+jo7vQtwMDh+3tZtMdbAnIwBj6cOkpPovWGojw4tINEWJJ1ovS+f9ye1XRJzZqPdFbMq040j082FhYDglI7Ew99MyWu46pIU8F0pDuyn8lAShG/KH6Eqke+joJZJsi60NYJsFDkt4nEP48t2aL67YtsflIbWZfQtup1g11cyDy2UqZBYX9s/qKFz0bqKtEQ0QLB5j49zzZck6ufxeCV000lQDOW6RYCmIntUBMc3q0GJAQKBgQDTL2L4dWAH75IQCQee1jHGNUbIemT/L8iAFTDKh8vLi9Q5TIKev7M/hk8/9BLj8FfcxLMluD9CACQUFMIz6Td47/0soBV1jp0+EQkvpAPh8uPCv+PG9saqqPwnE6ocDnudcdJafntYTNoQ5LS3ylTPahrDbPaUy3E9QMgbNufH6QKBgQDCTnFXWqtlDhfCgvQYlfrW7BOKx/1LBT8JxNb9IckmhrbWspc4WPhDFJyW/fvrEHXBtVYqzVsik8KY92/1Tg4fP0h6S9FUllLfRsdxa7N/pPaSpaHSCtgJ6FuplVfuJiLQ9gAIwSln7oSrLmT9UCzt+SqN1mzeI5UL3kfgNgij+QKBgGK2DpPGAVoUP/igz+ANjHxyIKDKnPSuuBAuV30DVl3RASS7P37GahlyRQ7GniHPELKPepFR+rd162c7gL7U7unnezZlqUImpBcDzYBoMKxnZNB98xDn/lp83EfDqEqu9lbUghdscWkHmxI0ZMhXmabNmK7p+qP4J/kSk7YsxtApAoGATzVjBtUzYXKpROXVU+Bv7QU3wi9cliSBWh4f9fi74zSvfD45XwMF0XKpkvPeqih/mIOvqKsmJ6ZMgcb8oOc84ogNp60YnH7bMaHM0/OvrK02xvk2SkWZZBCQrUTwH2j0cHKX+PmlQ3T8KDw1dv4kEq6sJj3vC2895ySp8ECWrSECgYACTbIsj/1QpowwMED7ENzEemqQ6m6B5SLYOwNroaK40vj6pV9JGk8PHv3wRjqxPjZpdAfDSfLqqlKwAafM5LnwX0hLPhmMe+87rLng3J7/6w/6zX+DqcVm1etG+OvGjIWlxfHYsxlE93lzc7spOe5VvNuja7uvy62bBEJSiwCweA==";// 从app配置表里取支付宝对应的alipay_app_privateKey私钥字段
    private static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoEqe8Gs85FUGSWtH02uL/JHCBbMWtbpulfgVWaeseDmZrunzv2rHiqs6fflOCn+9id0ukkebhjngYkZ//ac8u+mWxaD7TaT+9ZVK/LlbAR5qbbiIoHqoLCQxkra4QT8rkhnrUDJNGb56X/Um8yNr25FuL95X9fE2/J4RD5tbL5v5zOtjMf3LCh4uFaqT5Tik1rw4brbYaVIrlzaMtF9SuCZwhIy6guoPFnshbF8fPePFKMeO/2B9ImfxFaRqrPSj6dBU5ZHYklW5ACO6e1ph+BeA0wLBwY/cXxztQlvdnB4yqWUY3sHoBwMXsNUP0CLqJz7m2mMm+56EKRy3YQLMoQIDAQAB"; // 从app配置表里取支付宝对应的alipay_app_publicKey公钥字段
    private static final String notifyUrl = "https://xxxx/business/api/pay/alipay/doCallback";// 从app配置表里取支付宝对应的alipay_app_notifyUrl字段。

    private static final String app_cert_path = "D:/ql/创业/项目/2021/盲盒拍/支付宝/应用公钥证书/appCertPublicKey_2021002145628669.crt";// 从app配置表里取支付宝对应的应用公钥证书app_cert_path字段。
    private static final String alipay_cert_path = "D:/ql/创业/项目/2021/盲盒拍/支付宝/支付宝公钥证书/alipayCertPublicKey_RSA2.crt";// 从app配置表里取支付宝对应的支付宝公钥证书alipay_cert_path字段。
    private static final String alipay_root_cert_path = "D:/ql/创业/项目/2021/盲盒拍/支付宝/支付宝根证书/alipayRootCert.crt";// 从app配置表里取支付宝对应的支付宝CA根证书alipay_root_cert_path字段。

    /**
     * 支付宝单笔转账接口
     *
     * @param appId                 从app配置表里取支付宝对应的alipay_app_appid字段
     * @param privateKey            从app配置表里取支付宝对应的alipay_app_privateKey私钥字段
     * @param out_biz_no            转账订单号(商家自定义,订单号在自身系统唯一)
     * @param trans_amount          订单总金额(>=0.1)，单位为元，精确到小数点后两位
     * @param order_title           转账业务的标题，用于在支付宝用户的账单里显示
     * @param name                  收款人支付宝真实姓名
     * @param identity              收款人支付宝账号
     * @param app_cert_path         从app配置表里取支付宝对应的alipay_app_cert_path支付宝应用公钥证书路径
     * @param alipay_cert_path      从app配置表里取支付宝对应的alipay_cert_path支付宝公钥证书文件路径
     * @param alipay_root_cert_path 从app配置表里取支付宝对应的alipay_cert_path支付宝CA根证书文件路径
     * @return String
     * {"alipay_fund_trans_uni_transfer_response":{"code":"10000","msg":"Success","order_id":"20210523110070000006860066337636","out_biz_no":"20210522155400001","pay_fund_order_id":"20210523110070001506860095282117","status":"SUCCESS","trans_date":"2021-05-23
     * 12:00:57"},"alipay_cert_sn":"ff05e52121d7fc6517725c65788c1a46","sign":"fv9GfcN5Jm3h9ButdmVS2jYOcrNKQbGBoKdBAEMDk/QVfG0HrRozyIJWoXti2ckEugZg/xW4bQB8/8BgwIIOqgJu6oZLQ9D3W9r6puEBoYXaYdEC/3WjV8pM78XC73PkOS+R+GokE7479KSjdJ9ucUJi9uNg92sDwDrDbernmGWPhTfqMUM5SCzrmTvG+Dwk/H8DrpG9uvBnhNpuqJ2Fg4Gj0bDPRxRyhqFhqAnWv9V5+fEd3IMYGwTJljsSLUq6XZMd0g6DM6uYXeH0pixk3qashHTdSbg+YOQEG5/ZNf+XJH7FUsyqXjaCNbx0MEU4K9workHJl4zJv/YLvw9LZw=="}
     */
    public static String payAlipayTransfer(String appId, String privateKey, String out_biz_no, Double trans_amount, String order_title, String name,
                                           String identity, String app_cert_path, String alipay_cert_path, String alipay_root_cert_path) {
        String result = "";
        JSONObject jsonParam = new JSONObject();
        try {
            CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
            certAlipayRequest.setServerUrl(gateway);
            certAlipayRequest.setAppId(appId);
            certAlipayRequest.setPrivateKey(privateKey);
            certAlipayRequest.setFormat("json");
            certAlipayRequest.setCharset("utf-8");
            certAlipayRequest.setSignType("RSA2");
            certAlipayRequest.setCertPath(app_cert_path);
            certAlipayRequest.setAlipayPublicCertPath(alipay_cert_path);
            certAlipayRequest.setRootCertPath(alipay_root_cert_path);
            AlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);

            // 发送API请求
            AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
            jsonParam.put("out_biz_no", out_biz_no);
            jsonParam.put("trans_amount", trans_amount);
            jsonParam.put("product_code", "TRANS_ACCOUNT_NO_PWD");
            jsonParam.put("biz_scene", "DIRECT_TRANSFER");
            jsonParam.put("order_title", order_title);
            JSONObject payeeInfo = new JSONObject();
            payeeInfo.put("identity", identity);
            payeeInfo.put("identity_type", "ALIPAY_LOGON_ID");
            payeeInfo.put("name", name);
            jsonParam.put("payee_info", payeeInfo);
            request.setBizContent(jsonParam.toString());
            AlipayFundTransUniTransferResponse response = alipayClient.certificateExecute(request);
            if (response.isSuccess()) {
                log.info("支付宝退款成功：code:{},msg:{}", response.getCode(), response.getSubMsg());
                result = response.getBody();
            } else {
                String subMsg = response.getSubMsg();
                log.error("支付宝退款失败：code:{},msg:{}", response.getCode(), response.getSubMsg());
                throw new WeikbestException(ResultConstant.SUCCESS.getCode(), subMsg);
            }
        } catch (AlipayApiException e) {
            log.error("单笔转账接口出现异常结果=========" + e.getErrMsg());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 支付宝单笔转账接口
     *
     * @param alipayConfig 支付宝配置类
     * @param out_biz_no   转账订单号(商家自定义,订单号在自身系统唯一)
     * @param trans_amount 订单总金额(>=0.1)，单位为元，精确到小数点后两位
     * @param order_title  转账业务的标题，用于在支付宝用户的账单里显示
     * @param name         收款人支付宝真实姓名
     * @param identity     收款人支付宝账号
     * @return String
     * {"alipay_fund_trans_uni_transfer_response":{"code":"10000","msg":"Success","order_id":"20210523110070000006860066337636","out_biz_no":"20210522155400001","pay_fund_order_id":"20210523110070001506860095282117","status":"SUCCESS","trans_date":"2021-05-23
     * 12:00:57"},"alipay_cert_sn":"ff05e52121d7fc6517725c65788c1a46","sign":"fv9GfcN5Jm3h9ButdmVS2jYOcrNKQbGBoKdBAEMDk/QVfG0HrRozyIJWoXti2ckEugZg/xW4bQB8/8BgwIIOqgJu6oZLQ9D3W9r6puEBoYXaYdEC/3WjV8pM78XC73PkOS+R+GokE7479KSjdJ9ucUJi9uNg92sDwDrDbernmGWPhTfqMUM5SCzrmTvG+Dwk/H8DrpG9uvBnhNpuqJ2Fg4Gj0bDPRxRyhqFhqAnWv9V5+fEd3IMYGwTJljsSLUq6XZMd0g6DM6uYXeH0pixk3qashHTdSbg+YOQEG5/ZNf+XJH7FUsyqXjaCNbx0MEU4K9workHJl4zJv/YLvw9LZw=="}
     */
    public static JSONObject payAlipayTransfer(AlipayConfig alipayConfig, String out_biz_no, Double trans_amount, String order_title, String name,
                                               String identity) {
        String response = payAlipayTransfer(alipayConfig.getAppid(),
                alipayConfig.getPrivatekey(),
                out_biz_no,
                trans_amount,
                order_title,
                name,
                identity,
                alipayConfig.getAppcertpath(),
                alipayConfig.getCertpath(),
                alipayConfig.getRootcertpath());
        log.info("------payAlipayTransfer:" + response);
        JSONObject payJsonObject = JsonUtils.json2Bean(response, JSONObject.class);
        JSONObject transferResponse = payJsonObject.getJSONObject("alipay_fund_trans_uni_transfer_response");
        return transferResponse;
    }

    /**
     * 查询转账订单接口
     *
     * @param appId                 从app配置表里取支付宝对应的alipay_app_appid字段
     * @param privateKey            从app配置表里取支付宝对应的alipay_app_privateKey私钥字段
     * @param app_cert_path         从app配置表里取支付宝对应的alipay_app_cert_path应用公钥证书路径
     * @param alipay_cert_path      从app配置表里取支付宝对应的alipay_cert_path支付宝公钥证书文件路径
     * @param alipay_root_cert_path 从app配置表里取支付宝对应的alipay_root_cert_path支付宝CA根证书文件路径
     * @param alipay_user_id        从app配置表里取支付宝对应的alipay_alipay_Pid字段
     * @param out_biz_no            商户转账唯一订单号
     * @return {"alipay_fund_trans_order_query_response":{"code":"10000","msg":"Success","order_fee":"0.00","order_id":"20210523110070001506860095282117","out_biz_no":"20210522155400001","pay_date":"2021-05-23 12:00:57","status":"SUCCESS"},"alipay_cert_sn":"ff05e52121d7fc6517725c65788c1a46","sign":"DLfAajqnapRWnYErjgr8DPVl5O8SOoD04RVLjLxJGGQ0b9zLJTnAGQFla+uqAzqXfsaFS6jS0LeeI6npbsNE50w9BKvnV5UU2bCDiNFlJpmQyU2sa0aPe6IHJAUSGUp6MKytYrOkTj5V1M1VF/DJBJAl0dCkA8LvxS6fmdTTkiJoEtnJE+qmFDjnRO/35AafpZAaE1kuKc3HjGJR65kpqw1ELDPXVA+R83mX7MNfeaeOStIXExDrFkS12BtjsgUWbMO0VlUS0YcovOF87iHKmSEtMhOvWt5LhEc6Rrif7dVOibgx9U2UCvDsTrbt7ve2ZUIirs1LwHXE0PnZPBIP9w=="}
     */
    public static String alipayFundTransCommonQueryRequest(String appId, String privateKey, String app_cert_path, String alipay_cert_path, String alipay_root_cert_path, String alipay_user_id, String out_biz_no) {
        String result = "";
        JSONObject jsonParam = new JSONObject();
        try {
            CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
            certAlipayRequest.setServerUrl(gateway);
            certAlipayRequest.setAppId(appId);
            certAlipayRequest.setPrivateKey(privateKey);
            certAlipayRequest.setFormat("json");
            certAlipayRequest.setCharset("utf-8");
            certAlipayRequest.setSignType("RSA2");
            certAlipayRequest.setCertPath(app_cert_path);
            certAlipayRequest.setAlipayPublicCertPath(alipay_cert_path);
            certAlipayRequest.setRootCertPath(alipay_root_cert_path);
            AlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);
            com.alipay.api.request.AlipayFundTransOrderQueryRequest request = new com.alipay.api.request.AlipayFundTransOrderQueryRequest();
            jsonParam.put("out_biz_no", out_biz_no);
            jsonParam.put("alipay_user_id", alipay_user_id);
            request.setBizContent(jsonParam.toString());
            AlipayFundTransOrderQueryResponse response = alipayClient.certificateExecute(request);
            if (response.isSuccess()) {
                log.info("支付宝查询转账订单成功：code:{},msg:{}", response.getCode(), response.getSubMsg());
                result = response.getBody();
            } else {
                String subMsg = response.getSubMsg();
                log.error("支付宝查询转账订单失败：code:{},msg:{}", response.getCode(), response.getSubMsg());
                throw new WeikbestException(ResultConstant.SUCCESS.getCode(), subMsg);
            }
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 查询转账订单接口
     *
     * @param alipayConfig 支付宝配置类
     * @param out_biz_no   商户转账唯一订单号
     * @return {"alipay_fund_trans_order_query_response":{"code":"10000","msg":"Success","order_fee":"0.00","order_id":"20210523110070001506860095282117","out_biz_no":"20210522155400001","pay_date":"2021-05-23 12:00:57","status":"SUCCESS"},"alipay_cert_sn":"ff05e52121d7fc6517725c65788c1a46","sign":"DLfAajqnapRWnYErjgr8DPVl5O8SOoD04RVLjLxJGGQ0b9zLJTnAGQFla+uqAzqXfsaFS6jS0LeeI6npbsNE50w9BKvnV5UU2bCDiNFlJpmQyU2sa0aPe6IHJAUSGUp6MKytYrOkTj5V1M1VF/DJBJAl0dCkA8LvxS6fmdTTkiJoEtnJE+qmFDjnRO/35AafpZAaE1kuKc3HjGJR65kpqw1ELDPXVA+R83mX7MNfeaeOStIXExDrFkS12BtjsgUWbMO0VlUS0YcovOF87iHKmSEtMhOvWt5LhEc6Rrif7dVOibgx9U2UCvDsTrbt7ve2ZUIirs1LwHXE0PnZPBIP9w=="}
     */
    public static JSONObject alipayFundTransCommonQueryRequest(AlipayConfig alipayConfig, String out_biz_no) {
        String response = alipayFundTransCommonQueryRequest(alipayConfig.getAppid(), alipayConfig.getPrivatekey(), alipayConfig.getAppcertpath(),
                alipayConfig.getCertpath(), alipayConfig.getRootcertpath(), alipayConfig.getPid(), out_biz_no);
        // log.info("------alipayFundTransCommonQueryRequest:" + response);
        JSONObject alipayFundTransCommonQueryJsonObject = JsonUtils.json2Bean(response, JSONObject.class);
        JSONObject alipayFundTransOrderQueryResponse = alipayFundTransCommonQueryJsonObject.getJSONObject("alipay_fund_trans_order_query_response");
        return alipayFundTransOrderQueryResponse;
    }

    /**
     * 支付宝资金账户资产查询接口
     *
     * @param appId                 从app配置表里取支付宝对应的alipay_app_appid字段
     * @param privateKey            从app配置表里取支付宝对应的alipay_app_privateKey私钥字段
     * @param app_cert_path         从app配置表里取支付宝对应的alipay_app_cert_path应用公钥证书路径
     * @param alipay_cert_path      从app配置表里取支付宝对应的alipay_cert_path支付宝公钥证书文件路径
     * @param alipay_root_cert_path 从app配置表里取支付宝对应的alipay_root_cert_path支付宝CA根证书文件路径
     * @param alipay_user_id        从app配置表里取支付宝对应的alipay_alipay_Pid字段
     * @return {"alipay_fund_account_query_response":{"code":"10000","msg":"Success","available_amount":"55.17"},"alipay_cert_sn":"ff05e52121d7fc6517725c65788c1a46","sign":"IIqXbhqbGAHnx4wzG9a2tt928NYWoUWxwP00F8kZH1z59ejZp3ShkadgTY+Tarl0QNzJtbDnixmg6NDLPQiGPAIwe1ZMvBdhJ15Py+cYoDUcL1lk/58HMaY8VxLf3tuNEZW6c7G9QPuBg/FOl94og74kGk/xMSBeGX0wEV3AYpnTtlHE+1Xyw9PjkriqWJ4OqQ+zj5irsYN6jFsVnWww1+PqrE89yLL2U6ZzlZQwz8ALAv2Brivfj4nlzy5Pz0RdeBb8ixOe5IpLKm411fFImzCCyd5x/6OUUI2PeShsNetez9KTidIz8JxN8HfI1i+/W8cAXp9EiZ+1wTJQ+qZtog=="}
     */
    public static String alipayFundAccountQueryRequest(String appId, String privateKey, String app_cert_path, String alipay_cert_path, String alipay_root_cert_path, String alipay_user_id) {
        String result = "";
        JSONObject jsonParam = new JSONObject();
        try {
            CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
            certAlipayRequest.setServerUrl(gateway);
            certAlipayRequest.setAppId(appId);
            certAlipayRequest.setPrivateKey(privateKey);
            certAlipayRequest.setFormat("json");
            certAlipayRequest.setCharset("utf-8");
            certAlipayRequest.setSignType("RSA2");
            certAlipayRequest.setCertPath(app_cert_path);
            certAlipayRequest.setAlipayPublicCertPath(alipay_cert_path);
            certAlipayRequest.setRootCertPath(alipay_root_cert_path);
            AlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);
            com.alipay.api.request.AlipayFundAccountQueryRequest request = new com.alipay.api.request.AlipayFundAccountQueryRequest();
            jsonParam.put("alipay_user_id", alipay_user_id);
            jsonParam.put("account_type", "ACCTRANS_ACCOUNT");
            request.setBizContent(jsonParam.toString());
            AlipayFundAccountQueryResponse response = alipayClient.certificateExecute(request);
            if (response.isSuccess()) {
                log.info("支付宝资金账户资产查询成功：code:{},msg:{}", response.getCode(), response.getSubMsg());
                result = response.getBody();
            } else {
                String subMsg = response.getSubMsg();
                log.error("支付宝资金账户资产查询失败：code:{},msg:{}", response.getCode(), response.getSubMsg());
                throw new WeikbestException(ResultConstant.SUCCESS.getCode(), subMsg);
            }
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 支付宝资金账户资产查询接口
     *
     * @param alipayConfig 支付宝配置类
     * @return {"alipay_fund_account_query_response":{"code":"10000","msg":"Success","available_amount":"55.17"},"alipay_cert_sn":"ff05e52121d7fc6517725c65788c1a46","sign":"IIqXbhqbGAHnx4wzG9a2tt928NYWoUWxwP00F8kZH1z59ejZp3ShkadgTY+Tarl0QNzJtbDnixmg6NDLPQiGPAIwe1ZMvBdhJ15Py+cYoDUcL1lk/58HMaY8VxLf3tuNEZW6c7G9QPuBg/FOl94og74kGk/xMSBeGX0wEV3AYpnTtlHE+1Xyw9PjkriqWJ4OqQ+zj5irsYN6jFsVnWww1+PqrE89yLL2U6ZzlZQwz8ALAv2Brivfj4nlzy5Pz0RdeBb8ixOe5IpLKm411fFImzCCyd5x/6OUUI2PeShsNetez9KTidIz8JxN8HfI1i+/W8cAXp9EiZ+1wTJQ+qZtog=="}
     */
    public static JSONObject alipayFundAccountQueryRequest(AlipayConfig alipayConfig) {
        String response = alipayFundAccountQueryRequest(alipayConfig.getAppid(), alipayConfig.getPrivatekey(),
                alipayConfig.getAppcertpath(), alipayConfig.getCertpath(), alipayConfig.getRootcertpath(), alipayConfig.getPid());
        log.info("------alipayFundAccountQueryRequest:" + response);
        JSONObject alipayJsonObject = JsonUtils.json2Bean(response, JSONObject.class);
        JSONObject queryResponse = alipayJsonObject.getJSONObject("alipay_fund_account_query_response");
        return queryResponse;
    }

    public static void main(String[] args) {
        /**
         * 支付宝资金账户资产查询接口
         String result = AlipayFundAccountQueryRequest(appId,privateKey,app_cert_path,alipay_cert_path, alipay_root_cert_path,"2088831783638867");
         System.out.println(result);*/

        /*
         * 支付宝单笔转账接口*/
		/*String result = payAlipayTransfer(appId,privateKey,"20210522155400005", 0.10, "未拍中获得资金", "乔龙", "qiaolong6652@163.com", app_cert_path,
				alipay_cert_path, alipay_root_cert_path);
		System.out.println(result); */

        /**
         * 转账业务单据查询接口
         String result = AlipayFundTransCommonQueryRequest(appId,privateKey,app_cert_path,alipay_cert_path, alipay_root_cert_path,"2088831783638867","20210522155400001");
         System.out.println(result); */


    }
}
