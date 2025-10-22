package com.weikbest.pro.saas.common.third.aliyun.sms.example;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.weikbest.pro.saas.common.exception.WeikbestException;

/**
 * @author wisdomelon
 * @date 2019/9/4 0004
 * @project saas
 * @jdk 1.8
 */
public class SendSmsAlipayExample {

    /**
     * 发送单条短信
     */
    public static void SendSms() {

        String regionId = "cn-hangzhou";// 固定值
        String accessKeyId = System.getenv("ALIYUN_ACCESS_KEY_ID");// 阿里云短信服务KEY-每个商家都有一个不同的KEY
        String secret = System.getenv("ALIYUN_ACCESS_KEY_SECRET");// 阿里云短信服务密钥-每个商家都有一个不同的KEY
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);// 固定值
        request.setDomain("dysmsapi.aliyuncs.com");// 固定值
        request.setVersion("2017-05-25");// 固定值
        request.setAction("SendSms");// 固定值
        request.putQueryParameter("RegionId", "cn-hangzhou");// 固定值
        request.putQueryParameter("PhoneNumbers", "YOUR_PHONE_NUMBER");// 接收短信的手机号码
        request.putQueryParameter("SignName", "YOUR_SIGN_NAME");// 阿里云短信签名名称-每个商家都有一个短信签名名称
        request.putQueryParameter("TemplateCode", "YOUR_TEMPLATE_CODE");// 短信模板code,需要在阿里云申请模板内容--每个商家都会有一个唯一的模板code
        String code = "123456";// 短信验证码-随机生成6位数
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");//短信模板变量对应的实际值，JSON格式。
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            // {"Message":"OK","RequestId":"BBBD33F8-CE2F-41C1-A22A-8911DBB3B443","BizId":"393623066543551020^0","Code":"OK"}
        } catch (WeikbestException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送多条短信-并每个手机号接收到的是同一个模板code，且短信内容不一样。
     */
    public static void SendBatchSms() {
        String regionId = "cn-hangzhou";// 固定值
        String accessKeyId = System.getenv("ALIYUN_ACCESS_KEY_ID");// 阿里云短信服务KEY-每个商家都有一个不同的KEY
        String secret = System.getenv("ALIYUN_ACCESS_KEY_SECRET");// 阿里云短信服务密钥-每个商家都有一个不同的KEY
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);// 固定值
        request.setDomain("dysmsapi.aliyuncs.com");// 固定值
        request.setVersion("2017-05-25");// 固定值
        request.setAction("SendBatchSms");// 固定值
        request.putQueryParameter("RegionId", "cn-hangzhou");// 固定值
        request.putQueryParameter("PhoneNumberJson", "[\"YOUR_PHONE_NUMBER_1\",\"YOUR_PHONE_NUMBER_2\"]");// 接收短信的手机号码,多个用,隔开
        request.putQueryParameter("SignNameJson", "[\"YOUR_SIGN_NAME_1\",\"YOUR_SIGN_NAME_2\"]");// 阿里云短信签名名称-每个商家都有一个短信签名名称
        request.putQueryParameter("TemplateCode", "YOUR_TEMPLATE_CODE");// 短信模板code,需要在阿里云申请模板内容。
        request.putQueryParameter("TemplateParamJson", "[{\"order\":\"1234\"},{\"order\":\"1111\"}]");//短信模板变量对应的实际值，JSON格式。
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (WeikbestException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}