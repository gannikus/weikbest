package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.third.aliyun.logistics.AliyunWuliuService;
import com.weikbest.pro.saas.common.third.aliyun.logistics.config.AliyunWuliuCompanyResult;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.param.entity.LogisticsCompany;
import com.weikbest.pro.saas.sys.param.mapper.LogisticsCompanyMapper;
import com.weikbest.pro.saas.sys.param.module.dto.LogisticsCompanyDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.LogisticsCompanyMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.LogisticsCompanyQO;
import com.weikbest.pro.saas.sys.param.service.LogisticsCompanyService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 物流快递公司表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-20
 */
@Service
public class LogisticsCompanyServiceImpl extends ServiceImpl<LogisticsCompanyMapper, LogisticsCompany> implements LogisticsCompanyService {

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private RedisLock redisLock;

    @Resource
    private RedisContext redisContext;


    @Override
    public boolean insert(LogisticsCompanyDTO logisticsCompanyDTO) {
        LogisticsCompany logisticsCompany = LogisticsCompanyMapStruct.INSTANCE.converToEntity(logisticsCompanyDTO);
        return this.save(logisticsCompany);
    }

    @Override
    public boolean updateById(Long id, LogisticsCompanyDTO logisticsCompanyDTO) {
        LogisticsCompany logisticsCompany = this.findById(id);
        LogisticsCompanyMapStruct.INSTANCE.copyProperties(logisticsCompanyDTO, logisticsCompany);
        logisticsCompany.setId(id);
        return this.updateById(logisticsCompany);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean sync() {
        boolean tryLock = redisLock.tryLock(RedisKey.Lock.LOCK_LOGISTICSCOMPANY_KEY);
        if (!tryLock) {
            throw new WeikbestException("平台快递物流公司正在同步中...");
        }
        try {
            AliyunWuliuService aliyunWuliuService = thirdConfigService.aliyunWuliuService();
            AliyunWuliuCompanyResult aliyunWuliuCompanyResult = aliyunWuliuService.queryAliyunWuliuCompany();
            Map<String, String> result = aliyunWuliuCompanyResult.getResult();
            // 全部删除
            this.remove(new QueryWrapper<LogisticsCompany>().le(LogisticsCompany.GMT_CREATE, DateUtil.date()));
            List<LogisticsCompany> logisticsCompanyList = result.entrySet().stream().map(entry -> new LogisticsCompany().setNumber(entry.getKey()).setName(entry.getValue())).collect(Collectors.toList());

            if (logisticsCompanyList.size() > WeikbestConstant.MAX_SAVE_BATCH_SIZE) {
                Iterator<LogisticsCompany> iterator = logisticsCompanyList.iterator();
                List<LogisticsCompany> saveList = new ArrayList<>(WeikbestConstant.MAX_SAVE_BATCH_SIZE);
                while (iterator.hasNext()) {
                    saveList.add(iterator.next());
                    if (saveList.size() == WeikbestConstant.MAX_SAVE_BATCH_SIZE) {
                        this.saveBatch(saveList);
                        saveList.clear();
                    }
                }
                if (saveList.size() > WeikbestConstant.ZERO_LONG) {
                    this.saveBatch(saveList);
                }
            } else {
                this.saveBatch(logisticsCompanyList);
            }
            // 删除redis数据
            redisContext.del(RedisKey.MANAGE_LOGISTICSCOMPANY_KEY);
        } catch (Exception e) {
            throw new WeikbestException("平台快递物流公司同步异常...", e);
        } finally {
            redisLock.unlock(RedisKey.Lock.LOCK_LOGISTICSCOMPANY_KEY);
        }

        return true;
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public LogisticsCompany findById(Long id) {
        LogisticsCompany logisticsCompany = this.getById(id);
        if (ObjectUtil.isNull(logisticsCompany)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return logisticsCompany;
    }

    @Override
    public IPage<LogisticsCompany> queryPage(LogisticsCompanyQO logisticsCompanyQO, PageReq pageReq) {
        QueryWrapper<LogisticsCompany> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(logisticsCompanyQO.getNumber())) {
            wrapper.eq(LogisticsCompany.NUMBER, logisticsCompanyQO.getNumber());
        }
        if (StrUtil.isNotBlank(logisticsCompanyQO.getName())) {
            wrapper.eq(LogisticsCompany.NAME, logisticsCompanyQO.getName());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<DictEntry> queryDict() {
        String cacheLogisticsCompanyDictEntry = (String) redisContext.get(RedisKey.MANAGE_LOGISTICSCOMPANY_KEY);

        if (StrUtil.isNotBlank(cacheLogisticsCompanyDictEntry)) {
            return JsonUtils.json2Array(cacheLogisticsCompanyDictEntry, DictEntry.class);
        }

        // 查询全部数据
        List<LogisticsCompany> logisticsCompanyList = this.list(new QueryWrapper<LogisticsCompany>().orderByDesc(LogisticsCompany.SORT_BY));
        if (CollectionUtil.isEmpty(logisticsCompanyList)) {
            // 数据同步
//            sync();
            return new ArrayList<>();
        }
        // 再次查询
        logisticsCompanyList = this.list(new QueryWrapper<LogisticsCompany>().orderByDesc(LogisticsCompany.SORT_BY));
        List<DictEntry> dictEntryList = logisticsCompanyList.stream().map(LogisticsCompanyMapStruct.INSTANCE::converToDictEntry).collect(Collectors.toList());

        // 放入缓存
        redisContext.set(RedisKey.MANAGE_LOGISTICSCOMPANY_KEY, JsonUtils.bean2Json(dictEntryList));
        return dictEntryList;
    }


    public List<LogisticsCompany> getLogisticsCompanys(){
        return this.list(new QueryWrapper<LogisticsCompany>().eq(LogisticsCompany.FLAG, BasicConstant.STATE_0));
    }
}
