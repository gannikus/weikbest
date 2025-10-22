package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.common.cache.Memory;
import com.weikbest.pro.saas.sys.common.cache.SysLocalCache;
import com.weikbest.pro.saas.sys.param.entity.Config;
import com.weikbest.pro.saas.sys.param.mapper.ConfigMapper;
import com.weikbest.pro.saas.sys.param.module.dto.ConfigDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.ConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.ConfigQO;
import com.weikbest.pro.saas.sys.param.service.ConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

    @Resource
    private SysLocalCache sysLocalCache;

    @Override
    public boolean insert(ConfigDTO configDTO) {
        Config config = ConfigMapStruct.INSTANCE.converToEntity(configDTO);
        boolean save = this.save(config);
        sysLocalCache.loadCache(Memory.CACHE_CONFIGNAME);
        return save;
    }

    @Override
    public boolean updateById(Long id, ConfigDTO configDTO) {
        Config config = this.findById(id);
        ConfigMapStruct.INSTANCE.copyProperties(configDTO, config);
        config.setId(id);
        boolean update = this.updateById(config);
        sysLocalCache.loadCache(Memory.CACHE_CONFIGNAME);
        return update;
    }

    @Override
    public Config findById(Long id) {
        Config config = this.getById(id);
        if (ObjectUtil.isNull(config)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return config;
    }

    @Override
    public IPage<Config> queryPage(ConfigQO configQO, PageReq pageReq) {
        QueryWrapper<Config> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(configQO.getNumber())) {
            wrapper.eq(Config.NUMBER, configQO.getNumber());
        }
        if (StrUtil.isNotBlank(configQO.getName())) {
            wrapper.eq(Config.NAME, configQO.getName());
        }
        if (StrUtil.isNotBlank(configQO.getIsPreset())) {
            wrapper.eq(Config.IS_PRESET, configQO.getIsPreset());
        }
        if (StrUtil.isNotBlank(configQO.getDescription())) {
            wrapper.eq(Config.DESCRIPTION, configQO.getDescription());
        }
        if (StrUtil.isNotBlank(configQO.getDataStatus())) {
            wrapper.eq(Config.DATA_STATUS, configQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<DictEntry> queryDict() {
        List<Config> configList = this.list();
        if (CollectionUtil.isEmpty(configList)) {
            return new ArrayList<>();
        }
        return configList.stream().map(ConfigMapStruct.INSTANCE::converToDictEntry).collect(Collectors.toList());
    }
}
