package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.param.entity.AppletSmsConfig;
import com.weikbest.pro.saas.sys.param.mapper.AppletSmsConfigMapper;
import com.weikbest.pro.saas.sys.param.module.dto.AppletSmsConfigDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.AppletSmsConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.AppletSmsConfigQO;
import com.weikbest.pro.saas.sys.param.service.AppletSmsConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 小程序绑定短信配置表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-22
 */
@Service
public class AppletSmsConfigServiceImpl extends ServiceImpl<AppletSmsConfigMapper, AppletSmsConfig> implements AppletSmsConfigService {

    @Override
    public boolean insert(AppletSmsConfigDTO appletSmsConfigDTO) {
        AppletSmsConfig appletSmsConfig = AppletSmsConfigMapStruct.INSTANCE.converToEntity(appletSmsConfigDTO);
        return this.save(appletSmsConfig);
    }

    @Override
    public boolean updateById(Long id, AppletSmsConfigDTO appletSmsConfigDTO) {
        AppletSmsConfig appletSmsConfig = this.findById(id);
        AppletSmsConfigMapStruct.INSTANCE.copyProperties(appletSmsConfigDTO, appletSmsConfig);
        appletSmsConfig.setId(id);
        return this.updateById(appletSmsConfig);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public AppletSmsConfig findById(Long id) {
        AppletSmsConfig appletSmsConfig = this.getById(id);
        if (ObjectUtil.isNull(appletSmsConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return appletSmsConfig;
    }

    @Override
    public AppletSmsConfig findBySmsTemplateIdAndAppId(Long smsTemplateId, String appId) {
        AppletSmsConfig appletSmsConfig = this.getOne(new QueryWrapper<AppletSmsConfig>().eq(AppletSmsConfig.SMS_TEMPLATE_ID, smsTemplateId).eq(AppletSmsConfig.APP_ID, appId));
        if (ObjectUtil.isNull(appletSmsConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return appletSmsConfig;
    }

    @Override
    public IPage<AppletSmsConfig> queryPage(AppletSmsConfigQO appletSmsConfigQO, PageReq pageReq) {
        QueryWrapper<AppletSmsConfig> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(appletSmsConfigQO.getAppId())) {
            wrapper.eq(AppletSmsConfig.APP_ID, appletSmsConfigQO.getAppId());
        }
        if (StrUtil.isNotBlank(appletSmsConfigQO.getAppletPage())) {
            wrapper.eq(AppletSmsConfig.APPLET_PAGE, appletSmsConfigQO.getAppletPage());
        }
        if (StrUtil.isNotBlank(appletSmsConfigQO.getDescription())) {
            wrapper.eq(AppletSmsConfig.DESCRIPTION, appletSmsConfigQO.getDescription());
        }
        if (StrUtil.isNotBlank(appletSmsConfigQO.getDataStatus())) {
            wrapper.eq(AppletSmsConfig.DATA_STATUS, appletSmsConfigQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
