package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.bean.marketing.BusiFavorCallbacksRequest;
import com.github.binarywang.wxpay.bean.marketing.BusiFavorCallbacksResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.MarketingBusiFavorService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.JsonObject;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.constant.ConfigConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.PayConfig;
import com.weikbest.pro.saas.sys.param.mapper.PayConfigMapper;
import com.weikbest.pro.saas.sys.param.module.dto.PayConfigDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.PayConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.PayConfigQO;
import com.weikbest.pro.saas.sys.param.service.PayConfigService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import com.weikbest.pro.saas.sys.param.util.ThirdConfigUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统支付商户号配置表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-14
 */
@Service
public class PayConfigServiceImpl extends ServiceImpl<PayConfigMapper, PayConfig> implements PayConfigService {

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private MemoryService memoryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(String payConfigType, PayConfigDTO payConfigDTO) {
        String wxPayMchId = payConfigDTO.getWxPayMchId();
        PayConfig findOne = this.getOne(new QueryWrapper<PayConfig>().eq(PayConfig.WX_PAY_MCH_ID, wxPayMchId));
        if (ObjectUtil.isNotNull(findOne)) {
            throw new WeikbestException("微信支付商户号重复，请检查数据！");
        }

        PayConfig payConfig = PayConfigMapStruct.INSTANCE.converToEntity(payConfigDTO);
        payConfig.setPayConfigType(payConfigType);

        // 更新平台证书和设置为wxPayService中的config信息
        // 根据平台配置的第三方微信信息设置config
        WxPayConfig wxPayConfig = PayConfigMapStruct.INSTANCE.converToWxPayConfig(payConfig);
        WxPayService wxPayService = thirdConfigService.wxPayService(wxPayConfig);
        // 获取平台证书信息
        setWxPlatformSerial(wxPayService, payConfig);

        if (StrUtil.equals(payConfigType, DictConstant.PayConfigType.TYPE_3.getCode())) {
            // 如果保存的是特约商户，则需绑定商家券事件回调地址信息
            // 绑定商家券事件回调地址信息
            setWxBusiFavorCallbacks(wxPayService, payConfig);
        }

        // 保存数据
        return this.save(payConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, String payConfigType, PayConfigDTO payConfigDTO) {
        String wxPayMchId = payConfigDTO.getWxPayMchId();
        PayConfig findOne = this.getOne(new QueryWrapper<PayConfig>().ne(PayConfig.ID, id).eq(PayConfig.WX_PAY_MCH_ID, wxPayMchId));
        if (ObjectUtil.isNotNull(findOne)) {
            throw new WeikbestException("微信支付商户号重复，请检查数据！");
        }

        PayConfig payConfig = this.findById(id);
        PayConfigMapStruct.INSTANCE.copyProperties(payConfigDTO, payConfig);
        payConfig.setId(id);
        payConfig.setPayConfigType(payConfigType);

        // 更新平台证书和设置为wxPayService中的config信息
        // 根据平台配置的第三方微信信息设置config
        WxPayConfig wxPayConfig = PayConfigMapStruct.INSTANCE.converToWxPayConfig(payConfig);
        WxPayService wxPayService = thirdConfigService.wxPayService(wxPayConfig);
        // 获取平台证书信息
        setWxPlatformSerial(wxPayService, payConfig);

        if (StrUtil.equals(payConfigType, DictConstant.PayConfigType.TYPE_3.getCode())) {
            // 如果保存的是特约商户，则需绑定商家券事件回调地址信息
            // 绑定商家券事件回调地址信息
            setWxBusiFavorCallbacks(wxPayService, payConfig);
        }

        // 更新数据
        return this.updateById(payConfig);
    }

    private void setWxPlatformSerial(WxPayService wxPayService, PayConfig payConfig) {
        try {
            // 获取平台证书信息
            JsonObject data = ThirdConfigUtil.getWxPlatformSerialNo(wxPayService);
            String serialNo = data.get("serial_no").getAsString();
            String effectiveTime = data.get("effective_time").getAsString();
            String expireTime = data.get("expire_time").getAsString();

            payConfig.setWxPlatformSerialNo(serialNo);
            payConfig.setWxPlatformEffectiveTime(DateUtil.parse(effectiveTime));
            payConfig.setWxPlatformExpireTime(DateUtil.parse(expireTime));
        } catch (Exception exp) {
            log.error(exp.getMessage());
            throw new WeikbestException(exp);
        }
    }

    private void setWxBusiFavorCallbacks(WxPayService wxPayService, PayConfig payConfig) {
        try {
            // 配置商家券事件通知
            MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();
            BusiFavorCallbacksRequest request = new BusiFavorCallbacksRequest();
            request.setMchid(wxPayService.getConfig().getMchId());
            request.setNotifyUrl(memoryService.queryConfig(ConfigConstant.WX_BUSI_FAVOR_NOTIFY_URL));
            BusiFavorCallbacksResult busiFavorCallbacks = marketingBusiFavorService.createBusiFavorCallbacks(request);
            payConfig.setIsBindBusiFavor(DictConstant.Whether.yes.getCode());
            payConfig.setBusiFavorCallback(JsonUtils.bean2Json(busiFavorCallbacks));

        } catch (Exception exp) {
            log.error(exp.getMessage());
            throw new WeikbestException(exp);
        }
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public PayConfig findById(Long id) {
        PayConfig payConfig = this.getById(id);
        if (ObjectUtil.isNull(payConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return payConfig;
    }

    @Override
    public PayConfig findByPayConfigType(String payConfigType) {
        PayConfig payConfig = this.getOne(new QueryWrapper<PayConfig>().eq(PayConfig.PAY_CONFIG_TYPE, payConfigType));
        if (ObjectUtil.isNull(payConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return payConfig;
    }

    @Override
    public IPage<PayConfig> queryPage(PayConfigQO payConfigQO, PageReq pageReq) {
        QueryWrapper<PayConfig> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(payConfigQO.getPayConfigType())) {
            wrapper.eq(PayConfig.PAY_CONFIG_TYPE, payConfigQO.getPayConfigType());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayAppId())) {
            wrapper.eq(PayConfig.WX_PAY_APP_ID, payConfigQO.getWxPayAppId());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPaySubAppId())) {
            wrapper.eq(PayConfig.WX_PAY_SUB_APP_ID, payConfigQO.getWxPaySubAppId());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayMchId())) {
            wrapper.eq(PayConfig.WX_PAY_MCH_ID, payConfigQO.getWxPayMchId());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayMchName())) {
            wrapper.eq(PayConfig.WX_PAY_MCH_NAME, payConfigQO.getWxPayMchName());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayMchKey())) {
            wrapper.eq(PayConfig.WX_PAY_MCH_KEY, payConfigQO.getWxPayMchKey());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayEntPayKey())) {
            wrapper.eq(PayConfig.WX_PAY_ENT_PAY_KEY, payConfigQO.getWxPayEntPayKey());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPaySubMchId())) {
            wrapper.eq(PayConfig.WX_PAY_SUB_MCH_ID, payConfigQO.getWxPaySubMchId());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayNotifyUrl())) {
            wrapper.eq(PayConfig.WX_PAY_NOTIFY_URL, payConfigQO.getWxPayNotifyUrl());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayKeyPath())) {
            wrapper.eq(PayConfig.WX_PAY_KEY_PATH, payConfigQO.getWxPayKeyPath());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayPrivateKeyPath())) {
            wrapper.eq(PayConfig.WX_PAY_PRIVATE_KEY_PATH, payConfigQO.getWxPayPrivateKeyPath());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayPrivateCertPath())) {
            wrapper.eq(PayConfig.WX_PAY_PRIVATE_CERT_PATH, payConfigQO.getWxPayPrivateCertPath());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayApiV3Key())) {
            wrapper.eq(PayConfig.WX_PAY_API_V3_KEY, payConfigQO.getWxPayApiV3Key());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayApiCertSerialNo())) {
            wrapper.eq(PayConfig.WX_PAY_API_CERT_SERIAL_NO, payConfigQO.getWxPayApiCertSerialNo());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayApiServiceId())) {
            wrapper.eq(PayConfig.WX_PAY_API_SERVICE_ID, payConfigQO.getWxPayApiServiceId());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayPayScoreNotifyUrl())) {
            wrapper.eq(PayConfig.WX_PAY_PAY_SCORE_NOTIFY_URL, payConfigQO.getWxPayPayScoreNotifyUrl());
        }
        if (StrUtil.isNotBlank(payConfigQO.getWxPayPayScorePermissionNotifyUrl())) {
            wrapper.eq(PayConfig.WX_PAY_PAY_SCORE_PERMISSION_NOTIFY_URL, payConfigQO.getWxPayPayScorePermissionNotifyUrl());
        }
        if (StrUtil.isNotBlank(payConfigQO.getDataStatus())) {
            wrapper.eq(PayConfig.DATA_STATUS, payConfigQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public WxPayService findWxPayServiceByPayConfigType(String payConfigType) {
        PayConfig payConfig = this.findByPayConfigType(payConfigType);
        WxPayConfig wxPayConfig = PayConfigMapStruct.INSTANCE.converToWxPayConfig(payConfig);
        return thirdConfigService.wxPayService(wxPayConfig);
    }
}
