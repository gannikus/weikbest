package com.weikbest.pro.saas.sys.param.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.media.MarketingImageUploadResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingMediaService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.oss.AliyunOssService;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/6
 */
@Slf4j
public class ThirdConfigUtil {

    /**
     * 上传图片到微信
     *
     * @param aliyunOssService
     * @param marketingMediaService
     * @param fileUrl
     * @return
     */
    public static String getWxMediaUrl(AliyunOssService aliyunOssService, MarketingMediaService marketingMediaService, String fileUrl) {
        return aliyunOssService.downloadFileAvatar(fileUrl, ossObject -> {
            InputStream objectContent = ossObject.getObjectContent();
            try {
                // 调用微信接口 上传图片
                MarketingImageUploadResult marketingImageUploadResult = marketingMediaService.imageUploadV3(objectContent, SecureUtil.md5(fileUrl));
                return marketingImageUploadResult.getMediaUrl();
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new WeikbestException(e);
            }
        });
    }

    /**
     * 获取平台证书信息
     *
     * @param wxPayService
     * @return
     * @throws WxPayException
     */
    public static JsonObject getWxPlatformSerialNo(WxPayService wxPayService) throws WxPayException {
        String url = String.format("%s/v3/certificates", wxPayService.getPayBaseUrl());
        String response = wxPayService.getV3(url);
        JsonObject jsonObject = GsonParser.parse(response);
        JsonArray dataNode = jsonObject.getAsJsonArray("data");
        if (dataNode == null) {
            throw new WeikbestException("微信平台证书信息不存在！请检查配置信息！");
        }
        // 获取最后一个最新的证书信息
        return dataNode.get(dataNode.size() - 1).getAsJsonObject();
    }

    /**
     * 建立合作关系API
     * 商户在商户平台创建回流优惠券批次后，即可通过本接口将优惠券批次授权给平台端发布平台券指定的微信支付商户号
     *
     * @param wxPayService
     * @param stockId
     * @param partnerMerchantId
     * @return
     */
    public static String partnershipsBuild(WxPayService wxPayService, String stockId, String partnerMerchantId) throws WxPayException {
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/marketing/partnerships/build");
        JSONObject reqdataJSON = new JSONObject();
        /** 合作方信息 **/
        JSONObject authorizedData = new JSONObject();
        authorizedData.put("business_type", "BUSIFAVOR_STOCK");
        authorizedData.put("stock_id", stockId);
        /** 被授权数据 **/
        JSONObject partner = new JSONObject();
        partner.put("type", "MERCHANT");
        partner.put("merchant_id", partnerMerchantId);
        reqdataJSON.put("authorized_data", authorizedData);
        reqdataJSON.put("partner", partner);
        // 请求body参数
        StringEntity entity = new StringEntity(reqdataJSON.toString(), "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Idempotency-Key", stockId);
        return wxPayService.postV3("https://api.mch.weixin.qq.com/v3/marketing/partnerships/build", httpPost);
    }

    /**
     * 查询合作关系
     *
     * @return
     */
    public static String partnerships(WxPayService wxPayService) {
        String url = StrUtil.format("https://api.mch.weixin.qq.com/v3/marketing/partnerships?authorized_data=%7b%22business_type%22%3a%22BUSIFAVOR_STOCK%22%7d&partner=%7b%22type%22%3a%22MERCHANT%22%7d&offset=0&limit=50");
        try {
            // 调用微信接口 上传图片
            return wxPayService.getV3(url);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new WeikbestException(e);
        }
    }


    /**
     * 查询投诉单列表API[https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_11.shtml]
     * https://api.mch.weixin.qq.com/v3/merchant-service/complaints-v2
     *
     * @return
     */
    public static Map<String, Object> complaintsV2(WxPayService wxPayService, Map<String, Object> paramMap) {
        String mchId = wxPayService.getConfig().getMchId();
        List<String> paramList = new ArrayList<>();
        paramList.add("complainted_mchid=" + mchId);
        String beginDate = (String) paramMap.get("beginDate");
        paramList.add("begin_date=" + beginDate);
        String endDate = (String) paramMap.get("endDate");
        paramList.add("end_date=" + endDate);

        Integer offset = (Integer) paramMap.get("offset");
        if (ObjectUtil.isNotNull(offset)) {
            paramList.add("offset=" + offset);
        }
        Integer limit = (Integer) paramMap.get("limit");
        if (ObjectUtil.isNotNull(limit)) {
            paramList.add("limit=" + limit);
        }

        String param = StrUtil.join("&", paramList.toArray());

        String url = StrUtil.format("https://api.mch.weixin.qq.com/v3/merchant-service/complaints-v2?{}", param);
        String v3 = null;
        try {
            v3 = wxPayService.getV3(url);
        } catch (WxPayException e) {
            log.error(e.getMessage());
            throw new WeikbestException(e);
        }
        return JsonUtils.json2Bean(v3, Map.class);
    }
    /**
     * 查询投诉单列表API[https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_11.shtml]
     * https://api.mch.weixin.qq.com/v3/merchant-service/complaints-v2
     * 不分页
     * @return
     */
    public static Map<String, Object> listComplaintsV2(WxPayService wxPayService, Map<String, Object> paramMap) {
        String mchId = wxPayService.getConfig().getMchId();
        List<String> paramList = new ArrayList<>();
        paramList.add("complainted_mchid=" + mchId);
        String beginDate = (String) paramMap.get("beginDate");
        paramList.add("begin_date=" + beginDate);
        String endDate = (String) paramMap.get("endDate");
        paramList.add("end_date=" + endDate);
        paramList.add("limit=" + paramMap.get("limit"));
        paramList.add("offset=" + paramMap.get("offset"));

        String param = StrUtil.join("&", paramList.toArray());

        String url = StrUtil.format("https://api.mch.weixin.qq.com/v3/merchant-service/complaints-v2?{}", param);
        String v3 = null;
        try {
            v3 = wxPayService.getV3(url);
        } catch (WxPayException e) {
            log.error(e.getMessage());
//            throw new WeikbestException(e);
            //再次尝试,失败在报错
            if ("当前查询结果数据量过大，请缩小limit后重试".equals(e.getCustomErrorMsg())){
                paramList.set(3,"limit=" + 30);
                String param1 = StrUtil.join("&", paramList.toArray());
                String url1 = StrUtil.format("https://api.mch.weixin.qq.com/v3/merchant-service/complaints-v2?{}", param1);
                try {
                    v3 = wxPayService.getV3(url1);
                } catch (WxPayException ex) {
                    throw new WeikbestException(e);
                }
            }
        }
        return JsonUtils.json2Bean(v3, Map.class);
    }


    /**
     * 获取投诉列表的总数
     * @param wxPayService
     * @param paramMap
     * @return
     */
    public static Integer getlistComplaintsV2TotalCount(WxPayService wxPayService, Map<String, Object> paramMap) {
        String mchId = wxPayService.getConfig().getMchId();
        List<String> paramList = new ArrayList<>();
        paramList.add("complainted_mchid=" + mchId);
        String beginDate = (String) paramMap.get("beginDate");
        paramList.add("begin_date=" + beginDate);
        String endDate = (String) paramMap.get("endDate");
        paramList.add("end_date=" + endDate);
        paramList.add("limit=" + 50);

        String param = StrUtil.join("&", paramList.toArray());

        String url = StrUtil.format("https://api.mch.weixin.qq.com/v3/merchant-service/complaints-v2?{}", param);
        String v3 = null;
        try {
            v3 = wxPayService.getV3(url);
        } catch (WxPayException e) {
            log.error(e.getMessage());
//            throw new WeikbestException(e);
            return 50;
        }
        Map<String, Object> stringObjectMap = JsonUtils.json2Bean(v3, Map.class);
        return MapUtil.getInt(stringObjectMap, "total_count");
    }
}
