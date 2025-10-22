package com.weikbest.pro.saas.sys.common.general;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.delay.DelayQueueManager;
import com.weikbest.pro.saas.common.delay.DelayTaskVO;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.common.cache.Memory;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.cache.SysLocalCache;
import com.weikbest.pro.saas.sys.system.entity.UserActivex;
import com.weikbest.pro.saas.sys.system.service.UserActivexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2019/6/16 0016
 * @project mystery-boxes
 * @jdk 1.8
 */
@Slf4j
@Api(tags = {"Manage——缓存接口"})
@RestController
@RequestMapping("/general")
public class GeneralController {

    @Autowired
    private MemoryService memoryService;

    @Resource
    private SysLocalCache sysLocalCache;

    @Resource
    private RedisContext redisContext;

    @Resource
    private UserActivexService userActivexService;

    @Resource
    private DelayQueueManager delayQueueManager;

    @PassToken
    @ApiOperation(value = "生成traceid")
    @PostMapping("/traceid")
    public DataResp<String> traceid() {
        // 生成一个traceid
        return DataResp.ok(IdUtil.fastSimpleUUID());
    }

    @UseToken
    @QueryLog("获取缓存")
    @ApiOperation(value = "获取缓存")
    @PostMapping("/loadCache")
    public DataResp<Map<String, Object>> loadCache() {
        Map<String, Object> cacheMap = loadCacheMap();
        return DataResp.BuilderMap.create().ok().put(cacheMap).build();
    }

    private Map<String, Object> loadCacheMap() {
        Map<String, Object> cacheMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        Map<String, String> configMap = Memory.getConfig();
        Map<String, Map<String, String>> dictsMap = Memory.getDicts();
        Map<String, String> logisticsCompanyMap = Memory.getLogisticsCompany();

        cacheMap.put("config", configMap);
        cacheMap.put("dict", dictsMap);
        cacheMap.put("logisticsCompany", logisticsCompanyMap);
        return cacheMap;
    }

    @UseToken
    @QueryLog("刷新缓存")
    @ApiOperation(value = "刷新缓存")
    @PostMapping("/refreshCache")
    public DataResp<Map<String, Object>> refreshCache() {
        sysLocalCache.refreshCache();

        Map<String, Object> cacheMap = loadCacheMap();
        return DataResp.BuilderMap.create().ok().put(cacheMap).build();
    }

    @UseToken
    @QueryLog("查询指定缓存信息")
    @ApiOperation(value = "查询指定缓存信息")
    @GetMapping("/querySelect/{dictTypeNumber}")
    public DataResp<Map<String, Object>> querySelect(
            @ApiParam(name = "dictTypeNumber", value = "字典大类", required = true)
            @PathVariable("dictTypeNumber") String dictTypeNumber) {

        Map<String, Object> resultMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        memoryService.setDictToMap(dictTypeNumber, resultMap);

        return DataResp.BuilderMap.create().ok().put(resultMap).build();
    }

    @UseToken
    @ApiOperation(value = "查询指定缓存信息集")
    @GetMapping("/querySelects/{paramId}")
    public DataResp<Map<String, Object>> querySelects(
            @ApiParam(name = "dictTypeNumbers", value = "字典大类集 多个参数用,分割", required = true)
                    String dictTypeNumbers) {

        Map<String, Object> resultMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);

        String[] dictTypeNumberSplit = StrUtil.split(dictTypeNumbers, ",");
        if (dictTypeNumberSplit != null && dictTypeNumberSplit.length > WeikbestConstant.ZERO_INT) {
            for (String dictTypeNumber : dictTypeNumberSplit) {
                Map<String, Object> map = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
                memoryService.setDictToMap(dictTypeNumber, map);
                resultMap.put(dictTypeNumber, map);
            }
        }

        return DataResp.BuilderMap.create().ok().put(resultMap).build();
    }

    @UseToken
    @ApiOperation(value = "查询全量平台用户缓存数据")
    @GetMapping("/queryUserActiveX")
    public DataResp<Map<String, Object>> queryUser() {
        Map<String, Object> resultMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        Map<Object, Object> hmget = redisContext.hmget(RedisKey.MANAGE_USER_ACTIVEX_KEY);
        if (CollectionUtil.isEmpty(hmget)) {
            // 缓存查出为空，就去删除
            resultMap.putAll(userActivexService.refreshCache());
            return DataResp.BuilderMap.create().ok().put(resultMap).build();
        } else {
            hmget.forEach((key, value) -> resultMap.put(String.valueOf(key), JsonUtils.json2Bean(String.valueOf(value), UserActivex.class)));
            return DataResp.BuilderMap.create().ok().put(resultMap).build();
        }
    }


    @UseToken
    @ApiOperation(value = "查询全量平台用户缓存数据")
    @GetMapping("/queryUserActiveX/{userId}")
    public DataResp<UserActivex> queryUser(@ApiParam(name = "userId", value = "用户ID", required = true)
                                           @PathVariable Long userId) {

        UserActivex userActivex = userActivexService.getByCache(userId);
        return DataResp.ok(userActivex);
    }


    @UseToken
    @ApiOperation(value = "查询平台延时队列中的数据")
    @GetMapping("/queryDelayQueue")
    public DataResp<List<DelayTaskVO>> queryDelayQueue() {

        List<DelayTaskVO> delayQueueTask = delayQueueManager.getDelayQueueTask();
        return DataResp.ok(delayQueueTask);
    }
}

