package com.weikbest.pro.saas.common.third.aliyun.sms.config;

import com.weikbest.pro.saas.common.util.json.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliyunSmsResponse {

    /**
     * 短信服务返回对象
     */
    private String sendResponse;

    /**
     * 短信服务返回对象
     */
    private Map resultMap;

    /**
     * 短信服务返回状态
     */
    private String status;

    /**
     * 解析返回参数
     *
     * @param sendResponse
     * @return
     */
    public static AliyunSmsResponse explainResponse(String sendResponse) {
        Map map = JsonUtils.json2Bean(sendResponse, Map.class);
        return new AliyunSmsResponse(sendResponse, map, String.valueOf(map.get("Code")));
    }
}
