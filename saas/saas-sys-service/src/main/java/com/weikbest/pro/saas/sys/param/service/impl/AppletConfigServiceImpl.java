package com.weikbest.pro.saas.sys.param.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.wx.miniapp.config.WxMaProperties;
import com.weikbest.pro.saas.common.third.wx.module.WxMapStruct;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.entity.Config;
import com.weikbest.pro.saas.sys.param.mapper.AppletConfigMapper;
import com.weikbest.pro.saas.sys.param.module.dto.AppletConfigDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.AppletConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.AppletConfigQO;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 小程序配置表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Service
public class AppletConfigServiceImpl extends ServiceImpl<AppletConfigMapper, AppletConfig> implements AppletConfigService {

    @Resource
    private ThirdConfigService thirdConfigService;

    @Autowired
    private AppletConfigMapper appletConfigMapper;

    @Override
    public boolean insert(AppletConfigDTO appletConfigDTO) {
        AppletConfig appletConfig = AppletConfigMapStruct.INSTANCE.converToEntity(appletConfigDTO);
        return this.save(appletConfig);
    }

    @Override
    public boolean updateById(Long id, AppletConfigDTO appletConfigDTO) {
        AppletConfig appletConfig = this.findById(id);
        AppletConfigMapStruct.INSTANCE.copyProperties(appletConfigDTO, appletConfig);
        appletConfig.setId(id);
        return this.updateById(appletConfig);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public AppletConfig findById(Long id) {
        AppletConfig appletConfig = this.getById(id);
        if (ObjectUtil.isNull(appletConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return appletConfig;
    }

    @Override
    public AppletConfig findByAppIdAndAppletType(String appId, String appletType) {
        QueryWrapper<AppletConfig> wrapper = new QueryWrapper<>();
        wrapper.eq(AppletConfig.DATA_STATUS, DictConstant.Whether.yes.getCode());
        wrapper.eq(AppletConfig.APPLET_TYPE, appletType);
        wrapper.eq(AppletConfig.WX_MINIAPP_APP_ID, appId);
        AppletConfig appletConfig = this.getOne(wrapper);
//        if (ObjectUtil.isNull(appletConfig)) {
//            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
//        }
        return appletConfig;
    }

    @Override
    public IPage<AppletConfig> queryPage(AppletConfigQO appletConfigQO, PageReq pageReq) {
        QueryWrapper<AppletConfig> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(appletConfigQO.getAppletName())) {
            wrapper.like(AppletConfig.APPLET_NAME, appletConfigQO.getAppletName());
        }
        if (StrUtil.isNotBlank(appletConfigQO.getAppletHealthType())) {
            wrapper.eq(AppletConfig.APPLET_HEALTH_TYPE, appletConfigQO.getAppletHealthType());
        }
        if (ObjectUtil.isNotNull(appletConfigQO.getMinAppletDealTrialScore())) {
            wrapper.ge(AppletConfig.APPLET_DEAL_TRIAL_SCORE, appletConfigQO.getMinAppletDealTrialScore());
        }
        if (ObjectUtil.isNotNull(appletConfigQO.getMaxAppletDealTrialScore())) {
            wrapper.le(AppletConfig.APPLET_DEAL_TRIAL_SCORE, appletConfigQO.getMaxAppletDealTrialScore());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public WxMaService wxMaService(String appId) {
        QueryWrapper<AppletConfig> wrapper = new QueryWrapper<>();
        wrapper.eq(AppletConfig.DATA_STATUS, DictConstant.Whether.yes.getCode());
        wrapper.eq(AppletConfig.APPLET_TYPE, "1");
        wrapper.eq(AppletConfig.WX_MINIAPP_APP_ID, appId);
        AppletConfig appletConfig = this.getOne(wrapper);
        if (ObjectUtil.isNull(appletConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }

        WxMaProperties wxMaProperties = AppletConfigMapStruct.INSTANCE.converToWxMaProperties(appletConfig);
        WxMaDefaultConfigImpl wxMaDefaultConfig = WxMapStruct.INSTANCE.converToWxMaDefaultConfigImpl(wxMaProperties);
        return thirdConfigService.wxMaService(wxMaDefaultConfig);
    }

    @Override
    public List<DictEntry> queryDict() {
        List<AppletConfig> appletConfigList = this.list(new QueryWrapper<AppletConfig>().eq(AppletConfig.DATA_STATUS, DictConstant.Status.enable.getCode()));
        if (CollectionUtil.isEmpty(appletConfigList)) {
            return new ArrayList<>();
        }
        return appletConfigList.stream().map(AppletConfigMapStruct.INSTANCE::converToDictEntry).collect(Collectors.toList());
    }


    @Override
    public AppletConfig getStochasticAllocation(){
        return appletConfigMapper.getStochasticAllocation();
    }
}
