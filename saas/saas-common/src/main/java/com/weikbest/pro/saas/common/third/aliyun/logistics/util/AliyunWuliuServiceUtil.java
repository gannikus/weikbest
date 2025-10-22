package com.weikbest.pro.saas.common.third.aliyun.logistics.util;

import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.logistics.config.AliyunWuliuCompanyResult;
import com.weikbest.pro.saas.common.util.http.HttpUtils;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
@Slf4j
public class AliyunWuliuServiceUtil {

    /**
     * 查询物流信息
     *
     * @param host
     * @param path
     * @param appCode
     * @param courierNumber
     * @return
     * @throws Exception
     */
    public static String queryAliyunWuliu(String host, String path, String appCode, String courierNumber) throws Exception {
        Map<String, String> headers = new HashMap<>(16);
        // 格式为:Authorization:APPCODE a864ad7b7e0541189b65c82c4b6ed591
        headers.put("Authorization", "APPCODE " + appCode);
        Map<String, String> querys = new HashMap<>(16);
        // 求参数  运单号
        querys.put("no", courierNumber);
        /*if (!StringUtils.isEmpty(logisticsFlow.getLogisticsCompanyId())) {
            // !!! 请求参数   快递公司
            querys.put("type", logisticsFlow.getLogisticsCompanyId());
        }*/

        // 物流信息
        log.info("---------开始查询运单号为{}的物流信息，查询条件：{}", courierNumber, querys);

        // 重新查询最新物流信息
        /*  错误码 错误信息    描述
            201 快递单号错误  快递单号错误
            203 快递公司不存在 快递公司不存在
            204 快递公司识别失败    快递公司识别失败
            205 没有信息    没有信息
            207 该单号被限制，错误单号 该单号被限制，错误单号；一个单号对应多个快递公司，请求须指定快递公司
            400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
        */
        HttpResponse response = HttpUtils.doGet(host, path, headers, querys);

        // 从返回信息中拿到
        String returnStr = EntityUtils.toString(response.getEntity());

        if (StringUtils.isEmpty(returnStr)) {
            throw new WeikbestException("查询物流信息失败");
        }
        return returnStr;
    }


    /**
     * 查询物流公司信息
     *
     * @param host
     * @param path
     * @param appCode
     * @return
     * @throws Exception
     */
    public static AliyunWuliuCompanyResult queryAliyunWuliuCompany(String host, String path, String appCode) throws Exception {
        Map<String, String> headers = new HashMap<>(16);
        // 格式为:Authorization:APPCODE a864ad7b7e0541189b65c82c4b6ed591
        headers.put("Authorization", "APPCODE " + appCode);

        // 重新查询最新物流公司信息
        HttpResponse response = HttpUtils.doGet(host, path, headers, null);

        // 从返回信息中拿到
        String returnStr = EntityUtils.toString(response.getEntity());

        if (StringUtils.isEmpty(returnStr)) {
            throw new WeikbestException("查询物流信息失败");
        }

        return JsonUtils.json2Bean(returnStr, AliyunWuliuCompanyResult.class);
    }
}
