package com.weikbest.pro.saas.sys.common.cache;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.annotation.cache.LocalCache;
import com.weikbest.pro.saas.common.cache.ILocalCache;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.service.ConfigService;
import com.weikbest.pro.saas.sys.param.service.DictService;
import com.weikbest.pro.saas.sys.param.service.DictTypeService;
import com.weikbest.pro.saas.sys.param.service.LogisticsCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/8/31
 */
@LocalCache(name = "sys")
@Slf4j
@Component
public class SysLocalCache implements ILocalCache {

    @Resource
    private ConfigService configService;

    @Resource
    private DictTypeService dictTypeService;

    @Resource
    private DictService dictService;

    @Resource
    private LogisticsCompanyService logisticsCompanyService;

    @Override
    public void loadCache() {
        loadCache(Memory.CACHE_CONFIGNAME);
        loadCache(Memory.CACHE_DICTTYPENAME);
        loadCache(Memory.CACHE_DICTNAME);
        loadCache(Memory.CACHE_LOGISTICSCOMPANY);
        log.info("初始化系统参数缓存类结束...");
    }

    public void loadCache(String cacheKey) {
        if (StrUtil.equals(cacheKey, Memory.CACHE_CONFIGNAME)) {
            List<DictEntry> configDictEntryList = configService.queryDict();
            Memory.setCache(Memory.CACHE_CONFIGNAME, configDictEntryList);
        }

        if (StrUtil.equals(cacheKey, Memory.CACHE_DICTTYPENAME)) {
            List<DictEntry> dictTypeDictEntryList = dictTypeService.queryDict();
            Memory.setCache(Memory.CACHE_DICTTYPENAME, dictTypeDictEntryList);
        }

        if (StrUtil.equals(cacheKey, Memory.CACHE_DICTNAME)) {
            List<DictEntry> dictDictEntryList = dictService.queryDict();
            Memory.setCache(Memory.CACHE_DICTNAME, dictDictEntryList);
        }

        if (StrUtil.equals(cacheKey, Memory.CACHE_LOGISTICSCOMPANY)) {
            List<DictEntry> dictDictEntryList = logisticsCompanyService.queryDict();
            Memory.setCache(Memory.CACHE_LOGISTICSCOMPANY, dictDictEntryList);
        }
    }

    @Override
    public void refreshCache() {
        Memory.clearCache();

        loadCache();
    }
}
