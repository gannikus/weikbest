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
import com.weikbest.pro.saas.sys.param.entity.DictType;
import com.weikbest.pro.saas.sys.param.mapper.DictTypeMapper;
import com.weikbest.pro.saas.sys.param.module.dto.DictTypeDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.DictTypeMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.DictTypeQO;
import com.weikbest.pro.saas.sys.param.service.DictTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService {

    @Resource
    private SysLocalCache sysLocalCache;

    @Override
    public boolean insert(DictTypeDTO dictTypeDTO) {
        DictType dictType = DictTypeMapStruct.INSTANCE.converToEntity(dictTypeDTO);
        boolean save = this.save(dictType);
        sysLocalCache.loadCache(Memory.CACHE_DICTTYPENAME);
        return save;
    }

    @Override
    public boolean updateById(Long id, DictTypeDTO dictTypeDTO) {
        DictType dictType = this.findById(id);
        DictTypeMapStruct.INSTANCE.copyProperties(dictTypeDTO, dictType);
        dictType.setId(id);
        boolean update = this.updateById(dictType);
        sysLocalCache.loadCache(Memory.CACHE_DICTTYPENAME);
        return update;
    }

    @Override
    public DictType findById(Long id) {
        DictType dictType = this.getById(id);
        if (ObjectUtil.isNull(dictType)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return dictType;
    }

    @Override
    public IPage<DictType> queryPage(DictTypeQO dictTypeQO, PageReq pageReq) {
        QueryWrapper<DictType> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(dictTypeQO.getNumber())) {
            wrapper.eq(DictType.NUMBER, dictTypeQO.getNumber());
        }
        if (StrUtil.isNotBlank(dictTypeQO.getName())) {
            wrapper.eq(DictType.NAME, dictTypeQO.getName());
        }
        if (StrUtil.isNotBlank(dictTypeQO.getIsPreset())) {
            wrapper.eq(DictType.IS_PRESET, dictTypeQO.getIsPreset());
        }
        if (StrUtil.isNotBlank(dictTypeQO.getDescription())) {
            wrapper.eq(DictType.DESCRIPTION, dictTypeQO.getDescription());
        }
        if (StrUtil.isNotBlank(dictTypeQO.getDataStatus())) {
            wrapper.eq(DictType.DATA_STATUS, dictTypeQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<DictEntry> queryDict() {
        List<DictType> dictTypeList = this.list();
        if (CollectionUtil.isEmpty(dictTypeList)) {
            return new ArrayList<>();
        }
        return dictTypeList.stream().map(DictTypeMapStruct.INSTANCE::converToDictEntry).collect(Collectors.toList());
    }
}
