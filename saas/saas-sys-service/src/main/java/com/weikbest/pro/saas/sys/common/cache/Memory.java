package com.weikbest.pro.saas.sys.common.cache;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2019/6/16 0016
 * @project mystery-boxes
 * @jdk 1.8
 * 简单内存记录类
 */
public class Memory {

    public static final String CACHE_CONFIGNAME = "config";
    public static final String CACHE_DICTNAME = "dict";
    public static final String CACHE_DICTTYPENAME = "dictType";
    public static final String CACHE_LOGISTICSCOMPANY = "logisticsCompany";

    private static final Map<String, List<DictEntry>> CACHE_MAP = new HashMap<>();

    public static List<DictEntry> getCache(String key) {
        return CACHE_MAP.get(key);
    }

    public static void setCache(String key, List<DictEntry> value) {
        CACHE_MAP.put(key, value);
    }

    public static void clearCache() {
        CACHE_MAP.clear();
    }

    /***
     * 获取Config配置
     * @param number
     * @return
     */
    public static String getConfig(String number) {
        List<DictEntry> dictEntryList = Memory.getCache(CACHE_CONFIGNAME);
        if (CollectionUtil.isNotEmpty(dictEntryList)) {
            for (DictEntry dictEntry : dictEntryList) {
                if (StrUtil.equals(number, dictEntry.getKey())) {
                    return dictEntry.getValue();
                }
            }
        }
        return null;
    }

    /***
     * 获取Config配置
     * @return
     */
    public static Map<String, String> getConfig() {
        Map<String, String> map = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        List<DictEntry> dictEntryList = Memory.getCache(CACHE_CONFIGNAME);
        if (CollectionUtil.isNotEmpty(dictEntryList)) {
            for (DictEntry dictEntry : dictEntryList) {
                map.put(dictEntry.getKey(), dictEntry.getValue());
            }
        }
        return map;
    }

    /***
     * 获取Dict
     * @param typeCodes
     * @return
     */
    public static Map<String, Map<String, String>> getDicts(String... typeCodes) {
        Map<String, Map<String, String>> resultMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        for (String typeCode : typeCodes) {
            Map<String, String> dictMap = getDict(typeCode);
            if (dictMap.size() > WeikbestConstant.ZERO_INT) {
                resultMap.put(typeCode, dictMap);
            }
        }
        return resultMap;
    }

    private static DictEntry getDictType(String number) {
        List<DictEntry> dictEntryList = Memory.getCache(CACHE_DICTTYPENAME);
        if (CollectionUtil.isNotEmpty(dictEntryList)) {
            for (DictEntry dictEntry : dictEntryList) {
                if (StrUtil.equals(number, dictEntry.getKey())) {
                    return dictEntry;
                }
            }
        }
        return null;
    }

    private static DictEntry getDictType(Long dictTypeId) {
        List<DictEntry> dictEntryList = Memory.getCache(CACHE_DICTTYPENAME);
        if (CollectionUtil.isNotEmpty(dictEntryList)) {
            for (DictEntry dictEntry : dictEntryList) {
                if (dictTypeId.equals(dictEntry.getId())) {
                    return dictEntry;
                }
            }
        }
        return null;
    }


    /***
     * 获取Dict
     * @param typeNumber
     * @return
     */
    public static Map<String, String> getDict(String typeNumber) {
        List<DictEntry> dictEntryList = Memory.getCache(CACHE_DICTNAME);
        Map<String, String> map = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        DictEntry dictTypeEntry = getDictType(typeNumber);
        if (ObjectUtil.isNull(dictTypeEntry)) {
            return Maps.newHashMap();
        }
        for (DictEntry dictEntry : dictEntryList) {
            if (ObjectUtil.equal(dictTypeEntry.getId(), dictEntry.getPid())) {
                map.put(dictEntry.getKey(), dictEntry.getValue());
            }
        }
        return map;
    }


    /***
     * 获取Dict
     * @param typeNumber
     * @return
     */
    public static String getDict(String typeNumber, String dictNumber) {
        Map<String, String> dictMap = getDict(typeNumber);
        return dictMap.getOrDefault(dictNumber, "");
    }


    /***
     * 获取Dict
     * @param dictTypeId
     * @return
     */
    public static Map<String, String> getDict(Long dictTypeId) {
        List<DictEntry> dictEntryList = Memory.getCache(CACHE_DICTNAME);
        Map<String, String> map = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        DictEntry dictTypeEntry = getDictType(dictTypeId);
        if (ObjectUtil.isNull(dictTypeEntry)) {
            return Maps.newHashMap();
        }
        for (DictEntry dictEntry : dictEntryList) {
            if (ObjectUtil.equal(dictTypeEntry.getId(), dictEntry.getPid())) {
                map.put(dictEntry.getKey(), dictEntry.getValue());
            }
        }
        return map;
    }

    /***
     * 获取Dict
     * @return
     */
    public static Map<String, Map<String, String>> getDicts() {
        Map<String, Map<String, String>> resultMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        List<DictEntry> dictEntryList = Memory.getCache(CACHE_DICTTYPENAME);
        if (CollectionUtil.isNotEmpty(dictEntryList)) {
            for (DictEntry dictEntry : dictEntryList) {
                DictEntry dictTypeEntry = getDictType(dictEntry.getId());
                if (ObjectUtil.isEmpty(dictTypeEntry)) {
                    continue;
                }
                Map<String, String> dictMap = getDict(dictTypeEntry.getId());
                if (dictMap.size() > WeikbestConstant.ZERO_INT) {
                    resultMap.put(dictTypeEntry.getKey(), dictMap);
                }
            }
        }
        return resultMap;
    }

    /**
     * 获取快递物流公司
     *
     * @return
     */
    public static Map<String, String> getLogisticsCompany() {
        Map<String, String> map = new LinkedHashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        List<DictEntry> dictEntryList = Memory.getCache(CACHE_LOGISTICSCOMPANY);
        if (CollectionUtil.isNotEmpty(dictEntryList)) {
            for (DictEntry dictEntry : dictEntryList) {
                map.put(dictEntry.getKey(), dictEntry.getValue());
            }
        }
        return map;
    }

    /**
     * 获取快递物流公司
     *
     * @return
     */
    public static String getLogisticsCompanyName(String number) {
        List<DictEntry> dictEntryList = Memory.getCache(CACHE_LOGISTICSCOMPANY);
        if (CollectionUtil.isNotEmpty(dictEntryList)) {
            for (DictEntry dictEntry : dictEntryList) {
                if (StrUtil.equals(number, dictEntry.getKey())) {
                    return dictEntry.getValue();
                }
            }
        }
        return "";
    }

    /**
     * 获取快递物流公司
     *
     * @param name
     * @return
     */
    public static String getLogisticsCompanyNumberByName(String name) {
        List<DictEntry> dictEntryList = Memory.getCache(CACHE_LOGISTICSCOMPANY);
        if (CollectionUtil.isNotEmpty(dictEntryList)) {
            for (DictEntry dictEntry : dictEntryList) {
                if (StrUtil.equals(name, dictEntry.getValue())) {
                    return dictEntry.getKey();
                }
            }
        }
        return "";
    }
}
