package com.weikbest.pro.saas.common.util.json;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/6/11 0011
 * @project saas
 * @jdk 1.8
 */
public class JsonUtils {

    public static JsonMapper jsonMapper = new JsonMapper();

    /*public static String bean2Json(Object o) {
        try {
            return jsonMapper.bean2Json(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public static String bean2Json(Object o) {
        return JSON.toJSONString(o);
//        return JSONUtil.toJsonStr(o);
    }

    /*public static <T> T json2Bean(String jsonString, Class<T> c) {
        try {
            return jsonMapper.json2Bean(jsonString, c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public static <T> T json2Bean(String jsonString, Class<T> c) {
        return JSON.parseObject(jsonString, c);
//        return JSONUtil.toBean(jsonString, c);
    }

    public static <T> List<T> json2Array(String jsonString, Class<T> c) {
        return JSON.parseArray(jsonString, c);
    }

}
