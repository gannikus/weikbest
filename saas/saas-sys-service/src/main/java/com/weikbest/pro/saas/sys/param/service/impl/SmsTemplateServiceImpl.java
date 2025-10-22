package com.weikbest.pro.saas.sys.param.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaSchemeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.scheme.WxMaGenerateSchemeRequest;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.sms.AliyunSendSmsService;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsResponse;
import com.weikbest.pro.saas.common.third.wx.miniapp.link.AppletLinkUtil;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.TemplateEngineUtil;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.entity.AppletSmsConfig;
import com.weikbest.pro.saas.sys.param.entity.SmsTemplate;
import com.weikbest.pro.saas.sys.param.mapper.SmsTemplateMapper;
import com.weikbest.pro.saas.sys.param.module.dto.SmsTemplateDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.SmsTemplateMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.SmsTemplateQO;
import com.weikbest.pro.saas.sys.param.service.*;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 短信模板表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Slf4j
@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate> implements SmsTemplateService {

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private SmsRecordService smsRecordService;

    @Resource
    private AppletConfigService appletConfigService;

    @Resource
    private AppletSmsConfigService appletSmsConfigService;

    @Override
    public boolean insert(SmsTemplateDTO smsTemplateDTO) {
        SmsTemplate smsTemplate = SmsTemplateMapStruct.INSTANCE.converToEntity(smsTemplateDTO);
        return this.save(smsTemplate);
    }

    @Override
    public boolean updateById(Long id, SmsTemplateDTO smsTemplateDTO) {
        SmsTemplate smsTemplate = this.findById(id);
        SmsTemplateMapStruct.INSTANCE.copyProperties(smsTemplateDTO, smsTemplate);
        smsTemplate.setId(id);
        return this.updateById(smsTemplate);
    }

    @Override
    public SmsTemplate findById(Long id) {
        SmsTemplate smsTemplate = this.getById(id);
        if (ObjectUtil.isNull(smsTemplate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return smsTemplate;
    }

    @Override
    public IPage<SmsTemplate> queryPage(SmsTemplateQO smsTemplateQO, PageReq pageReq) {
        QueryWrapper<SmsTemplate> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(smsTemplateQO.getNumber())) {
            wrapper.eq(SmsTemplate.NUMBER, smsTemplateQO.getNumber());
        }
        if (StrUtil.isNotBlank(smsTemplateQO.getName())) {
            wrapper.eq(SmsTemplate.NAME, smsTemplateQO.getName());
        }
        if (StrUtil.isNotBlank(smsTemplateQO.getSmsType())) {
            wrapper.eq(SmsTemplate.SMS_TYPE, smsTemplateQO.getSmsType());
        }
        if (StrUtil.isNotBlank(smsTemplateQO.getContent())) {
            wrapper.eq(SmsTemplate.CONTENT, smsTemplateQO.getContent());
        }
        if (StrUtil.isNotBlank(smsTemplateQO.getParams())) {
            wrapper.eq(SmsTemplate.PARAMS, smsTemplateQO.getParams());
        }
        if (StrUtil.isNotBlank(smsTemplateQO.getBindUrl())) {
            wrapper.eq(SmsTemplate.BIND_URL, smsTemplateQO.getBindUrl());
        }
        if (StrUtil.isNotBlank(smsTemplateQO.getIsPreset())) {
            wrapper.eq(SmsTemplate.IS_PRESET, smsTemplateQO.getIsPreset());
        }
        if (StrUtil.isNotBlank(smsTemplateQO.getDescription())) {
            wrapper.eq(SmsTemplate.DESCRIPTION, smsTemplateQO.getDescription());
        }
        if (StrUtil.isNotBlank(smsTemplateQO.getDataStatus())) {
            wrapper.eq(SmsTemplate.DATA_STATUS, smsTemplateQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public SmsTemplate getSmsTemplateByBindUrl(String bindUrl) {
        List<SmsTemplate> smsTemplateList = this.list(new QueryWrapper<SmsTemplate>().like(SmsTemplate.BIND_URL, bindUrl));
        if (CollectionUtil.isEmpty(smsTemplateList)) {
            throw new WeikbestException("未匹配到短信模板，请确认URL是否输入正确！");
        }
        if (smsTemplateList.size() > 1) {
            log.info("匹配到多条短信模板: number: {}", smsTemplateList.stream().map(SmsTemplate::getNumber).collect(Collectors.toList()));
//            throw new WeikbestException("匹配到多条短信模板，请确认URL是否配置正确！");
        }
        // 取第一条
        return smsTemplateList.get(WeikbestConstant.ZERO_INT);
    }

    @Override
    public void sendOneSmsAndSaveRecord(String bindUrl, String phoneNum, String... smsParam) {
        SmsTemplate smsTemplate = getSmsTemplateByBindUrl(bindUrl);
        try {
            // 发送短信
            AliyunSendSmsService aliyunSendSmsService = thirdConfigService.aliyunSmsService();
            String templateParam = TemplateEngineUtil.renderTemplate(smsTemplate.getParams(), smsParam);
            AliyunSmsResponse aliyunSmsResponse = aliyunSendSmsService.doSendOneSms(phoneNum, smsTemplate.getNumber(), templateParam);
            smsRecordService.saveRecord(phoneNum, smsTemplate, templateParam, aliyunSmsResponse);
        } catch (Exception e) {
            log.error(String.format("%s，短信发送失败！", smsTemplate.getName()), e);
        }
    }


    @Override
    public void sendBatchSmsAndSaveRecord(String bindUrl, String phoneNum, String... smsParam) {
        SmsTemplate smsTemplate = getSmsTemplateByBindUrl(bindUrl);
        try {
            // 发送短信
            AliyunSendSmsService aliyunSendSmsService = thirdConfigService.aliyunSmsService();
            String templateParam = TemplateEngineUtil.renderTemplate(smsTemplate.getParams(), smsParam);
            AliyunSmsResponse aliyunSmsResponse = aliyunSendSmsService.doSendBathSms(phoneNum, smsTemplate.getNumber(), templateParam);
            smsRecordService.saveRecord(phoneNum, smsTemplate, templateParam, aliyunSmsResponse);
        } catch (Exception e) {
            log.error(String.format("%s，短信发送失败！", smsTemplate.getName()), e);
        }
    }


    @Override
    public void sendOneSmsJumpAppletAndSaveRecord(String appId, String bindUrl, String phoneNum, String... appletUrlParam) {
        SmsTemplate smsTemplate = getSmsTemplateByBindUrl(bindUrl);
        // 获取小程序信息
        AppletSmsConfig appletSmsConfig = appletSmsConfigService.findBySmsTemplateIdAndAppId(smsTemplate.getId(), appId);
        AppletConfig appletConfig = appletConfigService.findById(appletSmsConfig.getAppletConfigId());

        // 获取微信服务类
        WxMaService wxMaService = appletConfigService.wxMaService(appId);
        WxMaSchemeService wxMaSchemeService = wxMaService.getWxMaSchemeService();

        try {
            // 获取短信服务码
            String queryStr = TemplateEngineUtil.renderTemplate(appletSmsConfig.getAppletUrlParam(), appletUrlParam);
            String jumpWxaEnvVersion = DictConstant.WxMiniappType.getWxMiniappTypeByCode(appletConfig.getWxMiniappType()).getJumpWxaEnvVersion();
            WxMaGenerateSchemeRequest request = WxMaGenerateSchemeRequest.newBuilder()
                    .jumpWxa(WxMaGenerateSchemeRequest.JumpWxa.newBuilder().path(appletSmsConfig.getAppletPage()).query(queryStr).envVersion(jumpWxaEnvVersion).build())
                    .expireType(WeikbestConstant.ZERO_INT)
                    .build();
            // weixin://dl/business/?t=XaZrVA3Gv4i
            String generate = wxMaSchemeService.generate(request);
            log.info("appId:{}, appletLink:{}", appId, generate);
            String code = AppletLinkUtil.getCode(generate);

            // 发送短信
            AliyunSendSmsService aliyunSendSmsService = thirdConfigService.aliyunSmsService();
            String templateParam = TemplateEngineUtil.renderTemplate(smsTemplate.getParams(), code);
            AliyunSmsResponse aliyunSmsResponse = aliyunSendSmsService.doSendOneSms(phoneNum, smsTemplate.getNumber(), templateParam);
            smsRecordService.saveRecord(phoneNum, smsTemplate, templateParam, aliyunSmsResponse);

        } catch (WxErrorException e) {
            log.error(String.format("%s，生成短信服务码失败！", smsTemplate.getName()), e);
        } catch (Exception e) {
            log.error(String.format("%s，短信发送失败！", smsTemplate.getName()), e);
        }


    }
}
