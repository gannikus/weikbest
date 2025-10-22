package com.weikbest.pro.saas.sys.common.cache;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.ext.filter.AllowDomainsService;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.common.constant.ConfigConstant;
import com.weikbest.pro.saas.sys.param.entity.DictType;
import com.weikbest.pro.saas.sys.param.service.ConfigService;
import com.weikbest.pro.saas.sys.param.service.DictService;
import com.weikbest.pro.saas.sys.param.service.DictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wisdomelon
 * @date 2021/5/15
 * @project paipai-pig
 * @jdk 1.8
 */
@Slf4j
@Component
public class MemoryService implements AllowDomainsService {

    @Resource
    private ConfigService configService;

    @Resource
    private DictTypeService dictTypeService;

    @Resource
    private DictService dictService;

    /**
     * 查询字典值,设置到map中
     *
     * @param dictTypeNumber
     * @param map
     */
    public void setDictToMap(String dictTypeNumber, Map<String, Object> map) {
        List<DictEntry> dictEntryList = Memory.getCache(dictTypeNumber);
        if (CollectionUtils.isEmpty(dictEntryList)) {
            DictType dictType = dictTypeService.getOne(new QueryWrapper<DictType>().eq(DictType.NUMBER, dictTypeNumber));
            if (ObjectUtil.isNotNull(dictType)) {
                dictEntryList = dictService.queryDictByDictTypeId(dictType.getId());
                if (!CollectionUtils.isEmpty(dictEntryList)) {
                    for (DictEntry dictEntry : dictEntryList) {
                        map.put(dictEntry.getKey(), dictEntry.getValue());
                    }
                    Memory.setCache(dictTypeNumber, dictEntryList);
                }
            }

        } else {
            for (DictEntry dictEntry : dictEntryList) {
                map.put(dictEntry.getKey(), dictEntry.getValue());
            }
        }
    }

    /**
     * 查询字典值，并返回
     *
     * @param dictTypeNumber
     * @return
     */
    public List<DictEntry> queryDict(String dictTypeNumber) {
        List<DictEntry> dictEntryList = Memory.getCache(dictTypeNumber);
        if (CollectionUtils.isEmpty(dictEntryList)) {
            DictType dictType = dictTypeService.getOne(new QueryWrapper<DictType>().eq(DictType.NUMBER, dictTypeNumber));
            if (ObjectUtil.isNotNull(dictType)) {
                dictEntryList = dictService.queryDictByDictTypeId(dictType.getId());
                if (!CollectionUtils.isEmpty(dictEntryList)) {
                    Memory.setCache(dictTypeNumber, dictEntryList);
                }
            }

        }
        return dictEntryList;
    }

    /**
     * 查询字典值，并返回Map
     *
     * @param dictTypeNumber
     * @return
     */
    public Map<String, String> queryDictReturnMap(String dictTypeNumber) {
        List<DictEntry> dictEntryList = queryDict(dictTypeNumber);

        if (!CollectionUtils.isEmpty(dictEntryList)) {
            return dictEntryList.stream().collect(Collectors.toMap(DictEntry::getKey, DictEntry::getValue, (k1, k2) -> k1));
        }
        return MapUtil.empty();
    }


    /**
     * 查询字典值，并返回
     *
     * @param dictTypeNumber
     * @param dictNumber
     * @return
     */
    public String queryDict(String dictTypeNumber, String dictNumber) {
        String value = "";
        List<DictEntry> dictEntryList = Memory.getCache(dictTypeNumber);
        if (CollectionUtils.isEmpty(dictEntryList)) {
            DictType dictType = dictTypeService.getOne(new QueryWrapper<DictType>().eq(DictType.NUMBER, dictTypeNumber));
            if (ObjectUtil.isNotNull(dictType)) {
                dictEntryList = dictService.queryDictByDictTypeId(dictType.getId());
                if (!CollectionUtils.isEmpty(dictEntryList)) {
                    Memory.setCache(dictTypeNumber, dictEntryList);
                    for (DictEntry dictEntry : dictEntryList) {
                        if (StrUtil.equals(dictNumber, dictEntry.getKey())) {
                            value = dictEntry.getValue();
                        }
                    }
                }
            }
        }

        for (DictEntry dictEntry : dictEntryList) {
            if (StrUtil.equals(dictNumber, dictEntry.getKey())) {
                value = dictEntry.getValue();
            }
        }

        return value;
    }

    /**
     * 查询config表的记录
     *
     * @param configKey
     */
    public String queryConfig(String configKey) {
        String configValue = Memory.getConfig(configKey);
        if (StrUtil.isEmpty(configValue)) {
            List<DictEntry> configList = configService.queryDict();
            if (!CollectionUtils.isEmpty(configList)) {
                Memory.setCache(Memory.CACHE_CONFIGNAME, configList);
            }
            return Memory.getConfig(configKey);
        }
        return configValue;
    }

    /**
     * 封号处理
     *
     * @param useless
     */
    public void ban(String useless) {
        String ban = this.queryConfig(ConfigConstant.BAN);
        if (StrUtil.isNotEmpty(ban)) {
            if (ban.contains(useless)) {
                throw new WeikbestException("您非法操作，已被系统检测永久禁用！");
            }
        }
    }

    @Override
    public String accessControlAllowOriginURL() {
        String queryConfig = this.queryConfig(ConfigConstant.ACCESSCONTROLALLOWORIGIN_URL);
        return StrUtil.isNotBlank(queryConfig) ? queryConfig : "";
    }
}
