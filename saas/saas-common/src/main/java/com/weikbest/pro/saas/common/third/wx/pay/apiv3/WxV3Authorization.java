package com.weikbest.pro.saas.common.third.wx.pay.apiv3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class WxV3Authorization {
    /**
     * 作用：使用字段appId、timeStamp、nonceStr、package计算得出的签名值
     * 场景：根据微信统一下单接口返回的 prepay_id 生成调启支付所需的签名值
     *
     * @param appId
     * @param timestamp
     * @param nonceStr
     * @param pack      package
     * @return
     * @throws Exception
     */
    public static String getSign(String appId, long timestamp, String nonceStr, String pack, String privateKeyPath) throws Exception {
        String message = buildMessage(appId, timestamp, nonceStr, pack);
        String paySign = sign(message.getBytes("utf-8"), privateKeyPath);
        return paySign;
    }

    private static String buildMessage(String appId, long timestamp, String nonceStr, String pack) {
        return appId + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + pack + "\n";
    }

    private static String sign(byte[] message, String privateKeyPath) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        //这里需要一个PrivateKey类型的参数，就是商户的私钥。
        sign.initSign(getPrivateKey(privateKeyPath));
        sign.update(message);
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    /**
     * 获取私钥。
     *
     * @param filename 私钥文件路径 (privateKeyPath)
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

}
