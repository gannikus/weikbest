package com.weikbest.pro.saas.common.third.alipay.pay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.factory.Factory.Payment;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;

public class AlipayTradeAppPay {
    /**
     * 从app配置表里取支付宝对应的alipay_app_appid字段。
     */
    private static final String appId = "2021002145628669";
    /**
     * 从app配置表里取支付宝对应的alipay_app_privateKey私钥字段
     */
    private static final String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQClQCPaQ3t1f6xF1ewBFz/DPItXqBTsHM9MCJRODXuzuBlNHtiSqkEs/nbjIjyeTMVx7pVmfeD8zY5izAVr9QjfoSJCGx7ZyBRZbU7kjsjMFnLWzstzHrJ2fOWwyE8dqQ0LGZTtZycrwdC9Zkdai9oHsh7nGsd7tbnfrG+RD8EJraEIYclvLVhVhxx34ScEZTwgEddh5ASG5dcfWEaKMu5Vc8QImqsRcu42h5Df61NOPs1Y+B+BTfITlBHK7k+bv6QD4S8Y8ppJsce7sYgD3kQhB7YT6U+Jx8GXGD9HTbn0u3/E6Tmi4ulcxpOecSBBSBhu4a3P9Yi5m+paiGF+6ehdAgMBAAECggEAawy07RTgKorNt+PP4hkEhl53Sg6plxdGttOr9zBG3XK6eTotRD2kwRgLPjKUoFvNYHQVNnL/R+WFgrz2Gix/VhdVUQuunZg+jnwAKpHQxF0agcsvAuF07MsWJMJudiFoQLOOxYgYWQFSTw+t05tbX7bkzAOODLb5tUtIbumMvM5CTo3rL6/MhZs06U6rXuwinpbqkZ8wJAq+jxKkno/huqfPNH0K6njVBdhQ1LopjNcC4+Bt8ipKEEdngDimpNRYkcWIgYIp+3uq1eU32tAefBj6EWNTPvb4ulkmkffK4I6RMTcIRSheWwGSBw9p4+yzNo2x/VXo0CBiLPMg1eNkgQKBgQDP7wWK5HICsy0iiW6CuZQMYvXacVLQHL3BZnZbiwHg+D9rWh35pG9aF4bXsRR0niQzU+i74ocu4FnoJHm41OX3YmhWZ7qRqZhyCqPy9UFwP7PiV1Nbks1LTDAtWSu4gsxg0o2wx+GrLe30zIU2gTQwVCk72wqaABdnmEsDrLSuvQKBgQDLcz2pgko8dn9e5jUbwmY7Cllre55VUxY6BfxcaYnGTF2fPF6VUTrKoitkm20Sx8MMhcEwMYsI6h+ss1o9MxG4UOZUdkdJUozj3I1yteN8AUPe9lBT6Td/rFg6GEFwxfPD81nkO04EybsiR8eSMy9tJZlYeV8Ub3/OPTxu4rwKIQKBgFmjFKSXbn6zdxWYYUeBcM0WLB4LFEQa38JrxUIZXqn42n4QzzM7K7WOxtZcxBCxy7FEzuOP9+7BUIC+mKqRPMG0bn4xIUCB9/HRnHO0Po2BKK6+LnUfh9iZ83rZIfvchvaGTgaTZDlDB32sXRDuCe17mnrvJlbVC0HhaRUgcpORAoGAcV1nkJqfDw3gb1rLYQpzuJZNuUyKr3S6v94x/rR4JtzKlj4nXgCDSAIRN+A7aOmSz9mACoCwvmjm+W5+6/cW+qNplMrLchnjk9yDnNWpSqHR1d1eaUNcv3GjP8vgOxyydg+VmU1KbfSSp8ljdR28YY8/4ULQLizrBFqu4ALLUMECgYB6u5SDg+5eKfssjRt1wp13TwHdtorLEBVkQIvzC9Hhmj7g7Fy4t4Byw9qGW51dpmx25rOJUfPK3VdTmiYoeZJFSNV/P/NW7pYTg5ZRUpuRgl6Xsph37hF3n7p/D1vfzC5WLjY1Y7tlCZ5Sj7e0xVjiCa2iS2bGSlEPiHHbOYxlMQ==";
    /**
     * 从app配置表里取支付宝对应的alipay_app_publicKey公钥字段
     */
    private static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqXTByyRe6/HFdVCyUm/S05AAD0Ii31rVnm58pzmUrKUia1cX0/3NV4zbmCAmTjbapaymNdbQWDu/Uvk5UDyqzEvNRmQxwueqBcDiWhk5LxIP2DgkbAfU7AdNWx/na6bk9xdUAMHag6hj42E7YK37sPyMlhiOhED4azzOZyXqveol7jn0p1Lc+dWK98lQac1QPqgtXlNGdLUbD+Z8Waz6DaAg2quJ7dthnWXDE+FeMLoxHOaI6t/qLJRNG4dgOEdxvEZkwPmFdiRSeZ/oG2bQbkFqWuklShMnphF5BFYHbzmyz4Eb6FRvi8ZLKqOdzFOGHMLuBkrNU/1361KwpR41KwIDAQAB";
    /**
     * 从app配置表里取支付宝对应的alipay_app_notifyUrl字段。
     */
    private static final String notifyUrl = "https://xxxx/business/api/pay/alipay/doCallback";

    /**
     * 手机APP生成订单串
     *
     * @param subject     订单标题
     * @param outTradeNo  商户订单号
     * @param totalAmount 订单总金额
     * @return JSONObject
     * @throws Exception
     */
    public static String payAlipayDoCallback(String subject, String outTradeNo, String totalAmount) throws Exception {
        String result = "";
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(getOptions());
        try {
            //2. 发起API调用（手机APP生成订单串）
            AlipayTradeAppPayResponse response = Payment.App().asyncNotify("https://xxxx/business/api/pay/alipay/doCallback").pay(subject, outTradeNo, totalAmount);
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功" + response.getBody());
                result = response.getBody();
            } else {
                System.err.println("调用失败，原因：" + response.getBody());
                result = "fail";
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    private static Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";
        config.appId = appId;
        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = privateKey;
        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        /*config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
        config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
        config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";*/
        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = publicKey;
        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = notifyUrl;
        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
        //config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";
        return config;
    }

    public static void main(String[] args) {
        try {
            payAlipayDoCallback("测试数据", "2234567890", "0.01");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
