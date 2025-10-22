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
import com.weikbest.pro.saas.merchat.category.mapper.AppletDecConfigMapper;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigDTO;
import com.weikbest.pro.saas.merchat.category.module.mapstruct.AppletDecConfigMapStruct;
import com.weikbest.pro.saas.merchat.category.module.qo.AppletDecConfigQO;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletDecConfigVO;
import com.weikbest.pro.saas.merchat.category.service.AppletDecConfigEntryService;
import com.weikbest.pro.saas.merchat.category.service.AppletDecConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 小程序装修配置表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Service
public class AppletDecConfigServiceImpl extends ServiceImpl<AppletDecConfigMapper, AppletDecConfig> implements AppletDecConfigService {

    /**
     * 小程序装修配置表ID
     */
    private static final Long APPLET_DEC_CONFIG_ID = 1010L;

    @Resource
    private AppletDecConfigEntryService appletDecConfigEntryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(AppletDecConfigDTO appletDecConfigDTO) {
        AppletDecConfig appletDecConfig = AppletDecConfigMapStruct.INSTANCE.converToEntity(appletDecConfigDTO);
        appletDecConfig.setId(APPLET_DEC_CONFIG_ID);
        boolean save = this.save(appletDecConfig);

        // 小程序装修配置分录表数据保存
        appletDecConfigEntryService.saveBatchAppletDecConfigEntry(APPLET_DEC_CONFIG_ID, appletDecConfigDTO);
        return save;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, AppletDecConfigDTO appletDecConfigDTO) {
        AppletDecConfig appletDecConfig = this.findById(id);
        AppletDecConfigMapStruct.INSTANCE.copyProperties(appletDecConfigDTO, appletDecConfig);
        appletDecConfig.setId(id);
        boolean update = this.updateById(appletDecConfig);

        // 小程序装修配置分录表数据保存
        appletDecConfigEntryService.saveBatchAppletDecConfigEntry(APPLET_DEC_CONFIG_ID, appletDecConfigDTO);
        return update;
    }

    @Override
    public boolean saveOrUpdate(AppletDecConfigDTO appletDecConfigDTO) {
        AppletDecConfig appletDecConfig = this.getById(APPLET_DEC_CONFIG_ID);
        if (ObjectUtil.isEmpty(appletDecConfig)) {
            // 新增
            this.insert(appletDecConfigDTO);
        }
        // 更新
        return this.updateById(APPLET_DEC_CONFIG_ID, appletDecConfigDTO);
    }

    @Override
    public AppletDecConfigVO findAppletDecConfigVO() {
        AppletDecConfig appletDecConfig = this.getById(APPLET_DEC_CONFIG_ID);
        if (ObjectUtil.isEmpty(appletDecConfig)) {
            return new AppletDecConfigVO().setId(APPLET_DEC_CONFIG_ID);
        }
        AppletDecConfigVO appletDecConfigVO = AppletDecConfigMapStruct.INSTANCE.converToVO(appletDecConfig);

        // 将区域数据查询出来后分组
        List<AppletDecConfigEntry> appletDecConfigEntryList = appletDecConfigEntryService.list(new QueryWrapper<AppletDecConfigEntry>().eq(AppletDecConfigEntry.ID, APPLET_DEC_CONFIG_ID));
        appletDecConfigVO.getEntryMap().putAll(appletDecConfigEntryList.stream().collect(Collectors.groupingBy(AppletDecConfigEntry::getAppletConfigType)));

        return appletDecConfigVO;
    }

    @Override
    public AppletDecConfig findById(Long id) {
        AppletDecConfig appletDecConfig = this.getById(id);
        if (ObjectUtil.isNull(appletDecConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return appletDecConfig;
    }

    @Override
    public IPage<AppletDecConfig> queryPage(AppletDecConfigQO appletDecConfigQO, PageReq pageReq) {
        QueryWrapper<AppletDecConfig> wrapper = new QueryWrapper<>();

        if (StrUtil.isNotBlank(appletDecConfigQO.getName())) {
            wrapper.eq(AppletDecConfig.NAME, appletDecConfigQO.getName());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
