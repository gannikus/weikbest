package com.weikbest.pro.saas.merchat.category.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfig;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfigEntry;
import com.weikbest.pro.saas.merchat.category.mapper.AppletDecConfigEntryMapper;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigDTO;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigEntryDTO;
import com.weikbest.pro.saas.merchat.category.module.mapstruct.AppletDecConfigEntryMapStruct;
import com.weikbest.pro.saas.merchat.category.module.qo.AppletDecConfigEntryQO;
import com.weikbest.pro.saas.merchat.category.service.AppletDecConfigEntryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 小程序装修配置分录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Service
public class AppletDecConfigEntryServiceImpl extends ServiceImpl<AppletDecConfigEntryMapper, AppletDecConfigEntry> implements AppletDecConfigEntryService {

    @Override
    public boolean insert(AppletDecConfigEntryDTO appletDecConfigEntryDTO) {
        AppletDecConfigEntry appletDecConfigEntry = AppletDecConfigEntryMapStruct.INSTANCE.converToEntity(appletDecConfigEntryDTO);
        return this.save(appletDecConfigEntry);
    }

    @Override
    public boolean updateById(Long id, AppletDecConfigEntryDTO appletDecConfigEntryDTO) {
        AppletDecConfigEntry appletDecConfigEntry = this.findById(id);
        AppletDecConfigEntryMapStruct.INSTANCE.copyProperties(appletDecConfigEntryDTO, appletDecConfigEntry);
        appletDecConfigEntry.setId(id);
        return this.updateById(appletDecConfigEntry);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBatchAppletDecConfigEntry(Long id, AppletDecConfigDTO appletDecConfigDTO) {
        this.remove(new QueryWrapper<AppletDecConfigEntry>().eq(AppletDecConfig.ID, id));

        List<AppletDecConfigEntryDTO> appletDecConfigEntryDTOList = appletDecConfigDTO.getAppletDecConfigEntryDTOList();
        List<AppletDecConfigEntry> appletDecConfigEntryList = appletDecConfigEntryDTOList.stream().map(appletDecConfigEntryDTO -> {
            AppletDecConfigEntry appletDecConfigEntry = AppletDecConfigEntryMapStruct.INSTANCE.converToEntity(appletDecConfigEntryDTO);
            appletDecConfigEntry.setId(id);
            return appletDecConfigEntry;
        }).collect(Collectors.toList());
        this.saveBatch(appletDecConfigEntryList);
    }

    @Override
    public AppletDecConfigEntry findById(Long id) {
        AppletDecConfigEntry appletDecConfigEntry = this.getById(id);
        if (ObjectUtil.isNull(appletDecConfigEntry)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return appletDecConfigEntry;
    }

    @Override
    public IPage<AppletDecConfigEntry> queryPage(AppletDecConfigEntryQO appletDecConfigEntryQO, PageReq pageReq) {
        QueryWrapper<AppletDecConfigEntry> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(appletDecConfigEntryQO.getAppletConfigType())) {
            wrapper.eq(AppletDecConfigEntry.APPLET_CONFIG_TYPE, appletDecConfigEntryQO.getAppletConfigType());
        }
        if (StrUtil.isNotBlank(appletDecConfigEntryQO.getEntryImg())) {
            wrapper.eq(AppletDecConfigEntry.ENTRY_IMG, appletDecConfigEntryQO.getEntryImg());
        }
        if (StrUtil.isNotBlank(appletDecConfigEntryQO.getEntryUrl())) {
            wrapper.eq(AppletDecConfigEntry.ENTRY_URL, appletDecConfigEntryQO.getEntryUrl());
        }
        if (StrUtil.isNotBlank(appletDecConfigEntryQO.getDescription())) {
            wrapper.eq(AppletDecConfigEntry.DESCRIPTION, appletDecConfigEntryQO.getDescription());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
