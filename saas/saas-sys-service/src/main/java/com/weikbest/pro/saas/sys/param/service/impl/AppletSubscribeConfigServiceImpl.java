package com.weikbest.pro.saas.sys.param.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.wx.miniapp.subscribe.SubscribeUtil;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.TemplateEngineUtil;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.entity.AppletSubscribeConfig;
import com.weikbest.pro.saas.sys.param.mapper.AppletSubscribeConfigMapper;
import com.weikbest.pro.saas.sys.param.module.dto.AppletSubscribeConfigDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.AppletSubscribeConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.AppletSubscribeConfigQO;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import com.weikbest.pro.saas.sys.param.service.AppletSubscribeConfigService;
import com.weikbest.pro.saas.sys.param.service.AppletSubscribeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 小程序订阅消息配置表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Slf4j
@Service
public class AppletSubscribeConfigServiceImpl extends ServiceImpl<AppletSubscribeConfigMapper, AppletSubscribeConfig> implements AppletSubscribeConfigService {

    @Resource
    private AppletConfigService appletConfigService;

    @Resource
    private AppletSubscribeRecordService appletSubscribeRecordService;

    @Override
    public boolean insert(AppletSubscribeConfigDTO appletSubscribeConfigDTO) {
        AppletSubscribeConfig appletSubscribeConfig = AppletSubscribeConfigMapStruct.INSTANCE.converToEntity(appletSubscribeConfigDTO);
        return this.save(appletSubscribeConfig);
    }

    @Override
    public boolean updateById(Long id, AppletSubscribeConfigDTO appletSubscribeConfigDTO) {
        AppletSubscribeConfig appletSubscribeConfig = this.findById(id);
        AppletSubscribeConfigMapStruct.INSTANCE.copyProperties(appletSubscribeConfigDTO, appletSubscribeConfig);
        appletSubscribeConfig.setId(id);
        return this.updateById(appletSubscribeConfig);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public AppletSubscribeConfig findById(Long id) {
        AppletSubscribeConfig appletSubscribeConfig = this.getById(id);
        if (ObjectUtil.isNull(appletSubscribeConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return appletSubscribeConfig;
    }

    @Override
    public IPage<AppletSubscribeConfig> queryPage(AppletSubscribeConfigQO appletSubscribeConfigQO, PageReq pageReq) {
        QueryWrapper<AppletSubscribeConfig> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(appletSubscribeConfigQO.getAppId())) {
            wrapper.eq(AppletSubscribeConfig.APP_ID, appletSubscribeConfigQO.getAppId());
        }
        if (StrUtil.isNotBlank(appletSubscribeConfigQO.getNumber())) {
            wrapper.eq(AppletSubscribeConfig.NUMBER, appletSubscribeConfigQO.getNumber());
        }
        if (StrUtil.isNotBlank(appletSubscribeConfigQO.getName())) {
            wrapper.eq(AppletSubscribeConfig.NAME, appletSubscribeConfigQO.getName());
        }
        if (StrUtil.isNotBlank(appletSubscribeConfigQO.getSubscribeType())) {
            wrapper.eq(AppletSubscribeConfig.SUBSCRIBE_TYPE, appletSubscribeConfigQO.getSubscribeType());
        }
        if (StrUtil.isNotBlank(appletSubscribeConfigQO.getContent())) {
            wrapper.eq(AppletSubscribeConfig.CONTENT, appletSubscribeConfigQO.getContent());
        }
        if (StrUtil.isNotBlank(appletSubscribeConfigQO.getParams())) {
            wrapper.eq(AppletSubscribeConfig.PARAMS, appletSubscribeConfigQO.getParams());
        }
        if (StrUtil.isNotBlank(appletSubscribeConfigQO.getBindUrl())) {
            wrapper.eq(AppletSubscribeConfig.BIND_URL, appletSubscribeConfigQO.getBindUrl());
        }
        if (StrUtil.isNotBlank(appletSubscribeConfigQO.getDescription())) {
            wrapper.eq(AppletSubscribeConfig.DESCRIPTION, appletSubscribeConfigQO.getDescription());
        }
        if (StrUtil.isNotBlank(appletSubscribeConfigQO.getDataStatus())) {
            wrapper.eq(AppletSubscribeConfig.DATA_STATUS, appletSubscribeConfigQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public AppletSubscribeConfig getAppletSubscribeConfigByBindUrl(String appId, String bindUrl) {
        List<AppletSubscribeConfig> appletSubscribeConfigList = this.list(new QueryWrapper<AppletSubscribeConfig>().eq(AppletSubscribeConfig.APP_ID, appId).like(AppletSubscribeConfig.BIND_URL, bindUrl));
        if (CollectionUtil.isEmpty(appletSubscribeConfigList)) {
            throw new WeikbestException("未匹配到订阅消息模板，请确认URL是否输入正确！");
        }
        if (appletSubscribeConfigList.size() > 1) {
            log.info("匹配到多条订阅消息模板: number: {}", appletSubscribeConfigList.stream().map(AppletSubscribeConfig::getNumber).collect(Collectors.toList()));
//            throw new WeikbestException("匹配到多条短信模板，请确认URL是否配置正确！");
        }
        // 取第一条
        return appletSubscribeConfigList.get(WeikbestConstant.ZERO_INT);
    }

    @Override
    public void sendOneSubscribeAndSaveRecord(String appId, String bindUrl, String openid, String orderNumber, String... subscribeParam) {
        AppletSubscribeConfig appletSubscribeConfig = getAppletSubscribeConfigByBindUrl(appId, bindUrl);
        AppletConfig appletConfig = appletConfigService.findById(appletSubscribeConfig.getAppletConfigId());

        try {
            WxMaService wxMaService = appletConfigService.wxMaService(appId);

            // 发送订阅消息
            String templateParam = TemplateEngineUtil.renderTemplate(appletSubscribeConfig.getParams(), subscribeParam);
            String appletPage = appletSubscribeConfig.getAppletPage();
            WxMaSubscribeMessage wxMaSubscribeMessage = SubscribeUtil.buildSendSubscribeMsg(appletSubscribeConfig.getNumber(), openid, String.format(appletPage, orderNumber), appletConfig.getWxMiniappType(), templateParam);

            String response = SubscribeUtil.doSendOneSubscribe(wxMaService, wxMaSubscribeMessage);
            appletSubscribeRecordService.saveRecord(appletSubscribeConfig, openid, JsonUtils.bean2Json(wxMaSubscribeMessage), templateParam, response);
        } catch (Exception e) {
            log.error("订阅消息发送失败！", e);
        }
    }
}
