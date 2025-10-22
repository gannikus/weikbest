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
import com.weikbest.pro.saas.sys.param.entity.Dict;
import com.weikbest.pro.saas.sys.param.mapper.DictMapper;
import com.weikbest.pro.saas.sys.param.module.dto.DictDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.DictMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.DictQO;
import com.weikbest.pro.saas.sys.param.service.DictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private SysLocalCache sysLocalCache;

    @Override
    public boolean insert(DictDTO dictDTO) {
        Dict dict = DictMapStruct.INSTANCE.converToEntity(dictDTO);
        boolean save = this.save(dict);
        sysLocalCache.loadCache(Memory.CACHE_DICTNAME);
        return save;
    }

    @Override
    public boolean updateById(Long id, DictDTO dictDTO) {
        Dict dict = this.findById(id);
        DictMapStruct.INSTANCE.copyProperties(dictDTO, dict);
        dict.setId(id);
        boolean update = this.updateById(dict);
        sysLocalCache.loadCache(Memory.CACHE_DICTNAME);
        return update;
    }

    @Override
    public Dict findById(Long id) {
        Dict dict = this.getById(id);
        if (ObjectUtil.isNull(dict)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return dict;
    }

    @Override
    public IPage<Dict> queryPage(DictQO dictQO, PageReq pageReq) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(dictQO.getNumber())) {
            wrapper.eq(Dict.NUMBER, dictQO.getNumber());
        }
        if (StrUtil.isNotBlank(dictQO.getName())) {
            wrapper.eq(Dict.NAME, dictQO.getName());
        }
        if (StrUtil.isNotBlank(dictQO.getIsPreset())) {
            wrapper.eq(Dict.IS_PRESET, dictQO.getIsPreset());
        }
        if (StrUtil.isNotBlank(dictQO.getDescription())) {
            wrapper.eq(Dict.DESCRIPTION, dictQO.getDescription());
        }
        if (StrUtil.isNotBlank(dictQO.getDataStatus())) {
            wrapper.eq(Dict.DATA_STATUS, dictQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<DictEntry> queryDict() {
        List<Dict> dictList = this.list();
        if (CollectionUtil.isEmpty(dictList)) {
            return new ArrayList<>();
        }
        return dictList.stream().map(DictMapStruct.INSTANCE::converToDictEntry).collect(Collectors.toList());
    }

    @Override
    public List<DictEntry> queryDictByDictTypeId(Long dictTypeId) {
        List<Dict> dictList = this.list(new QueryWrapper<Dict>().eq(Dict.DICT_TYPE_ID, dictTypeId));
        if (CollectionUtil.isEmpty(dictList)) {
            return new ArrayList<>();
        }
        return dictList.stream().map(DictMapStruct.INSTANCE::converToDictEntry).collect(Collectors.toList());
    }
}
