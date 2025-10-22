package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
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
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopThirdMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopThirdDTO;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopThirdMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopThirdQO;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.constant.ConfigConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.PayConfig;
import com.weikbest.pro.saas.sys.param.module.mapstruct.PayConfigMapStruct;
import com.weikbest.pro.saas.sys.param.service.PayConfigService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import com.weikbest.pro.saas.sys.param.util.ThirdConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 店铺第三方平台拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
@Slf4j
@Service
public class ShopThirdServiceImpl extends ServiceImpl<ShopThirdMapper, ShopThird> implements ShopThirdService {

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private MemoryService memoryService;

    @Resource
    private PayConfigService payConfigService;


    @Override
    public boolean insert(ShopThirdDTO shopThirdDTO) {
        ShopThird shopThird = ShopThirdMapStruct.INSTANCE.converToEntity(shopThirdDTO);
        return this.save(shopThird);
    }

    @Override
    public boolean updateById(Long id, ShopThirdDTO shopThirdDTO) {
        ShopThird shopThird = this.findById(id);
        ShopThirdMapStruct.INSTANCE.copyProperties(shopThirdDTO, shopThird);
        shopThird.setId(id);
        return this.updateById(shopThird);
    }

    @Override
    public ShopThird findById(Long id) {
        ShopThird shopThird = this.getById(id);
        if (ObjectUtil.isNull(shopThird)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopThird;
    }

    @Override
    public IPage<ShopThird> queryPage(ShopThirdQO shopThirdQO, PageReq pageReq) {
        QueryWrapper<ShopThird> wrapper = new QueryWrapper<>();

        if (StrUtil.isNotBlank(shopThirdQO.getIsAddReceive())) {
            wrapper.eq(ShopThird.IS_ADD_RECEIVE, shopThirdQO.getIsAddReceive());
        }
        if (StrUtil.isNotBlank(shopThirdQO.getWxMchType())) {
            wrapper.eq(ShopThird.WX_MCH_TYPE, shopThirdQO.getWxMchType());
        }
        if (StrUtil.isNotBlank(shopThirdQO.getWxPayMchId())) {
            wrapper.eq(ShopThird.WX_PAY_MCH_ID, shopThirdQO.getWxPayMchId());
        }
        if (StrUtil.isNotBlank(shopThirdQO.getWxPayMchKey())) {
            wrapper.eq(ShopThird.WX_PAY_MCH_KEY, shopThirdQO.getWxPayMchKey());
        }
        if (StrUtil.isNotBlank(shopThirdQO.getWxPayKeyPath())) {
            wrapper.eq(ShopThird.WX_PAY_KEY_PATH, shopThirdQO.getWxPayKeyPath());
        }
        if (StrUtil.isNotBlank(shopThirdQO.getWxPayPrivateKeyPath())) {
            wrapper.eq(ShopThird.WX_PAY_PRIVATE_KEY_PATH, shopThirdQO.getWxPayPrivateKeyPath());
        }
        if (StrUtil.isNotBlank(shopThirdQO.getWxPayPrivateCertPath())) {
            wrapper.eq(ShopThird.WX_PAY_PRIVATE_CERT_PATH, shopThirdQO.getWxPayPrivateCertPath());
        }
        if (StrUtil.isNotBlank(shopThirdQO.getWxPayApiV3Key())) {
            wrapper.eq(ShopThird.WX_PAY_API_V3_KEY, shopThirdQO.getWxPayApiV3Key());
        }
        if (StrUtil.isNotBlank(shopThirdQO.getWxPayApiCertSerialNo())) {
            wrapper.eq(ShopThird.WX_PAY_API_CERT_SERIAL_NO, shopThirdQO.getWxPayApiCertSerialNo());
        }
        if (StrUtil.isNotBlank(shopThirdQO.getWxPlatformSerialNo())) {
            wrapper.eq(ShopThird.WX_PLATFORM_SERIAL_NO, shopThirdQO.getWxPlatformSerialNo());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public Map<Long, ShopThird> queryMapByIds(List<Long> shopIdList) {
        List<ShopThird> shopThirdList = this.listByIds(shopIdList);
        Map<Long, ShopThird> shopThirdMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        if (CollectionUtil.isNotEmpty(shopThirdList)) {
            shopThirdMap.putAll(shopThirdList.stream().collect(Collectors.toMap(ShopThird::getId, shopThird -> shopThird)));
        }
        return shopThirdMap;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertWithShop(Long shopId, ShopThirdDTO shopThirdDTO) {
        ShopThird findOne = this.getOne(new QueryWrapper<ShopThird>().eq(ShopThird.WX_PAY_MCH_ID, shopThirdDTO.getWxPayMchId()));
        if (ObjectUtil.isNotNull(findOne)) {
            throw new WeikbestException(String.format("微信支付-商户号[%s]，已存在！请更换", shopThirdDTO.getWxPayMchId()));
        }
        findOne = this.getOne(new QueryWrapper<ShopThird>().eq(ShopThird.WX_PAY_API_CERT_SERIAL_NO, shopThirdDTO.getWxPayApiCertSerialNo()));
        if (ObjectUtil.isNotNull(findOne)) {
            throw new WeikbestException(String.format("微信支付-APIv3证书序列号[%s]，已存在！请更换", shopThirdDTO.getWxPayApiCertSerialNo()));
        }

        // 店铺第三方平台关联表
        ShopThird shopThird = ShopThirdMapStruct.INSTANCE.converToEntity(shopThirdDTO);
        shopThird.setId(shopId);
        // 更新平台证书和设置为wxPayService中的config信息,绑定商家券事件回调地址信息
        // 根据店铺的第三方微信信息设置config
        WxPayConfig wxPayConfig = ShopThirdMapStruct.INSTANCE.converToWxPayConfig(shopThird);
        WxPayService wxPayService = thirdConfigService.wxPayService(wxPayConfig);
        // 获取平台证书信息
        setWxPlatformSerial(wxPayService, shopThird);
        // 绑定商家券事件回调地址信息
        setWxBusiFavorCallbacks(wxPayService, shopThird);

        findOne = this.getOne(new QueryWrapper<ShopThird>().eq(ShopThird.WX_PLATFORM_SERIAL_NO, shopThird.getWxPlatformSerialNo()));
        if (ObjectUtil.isNotNull(findOne)) {
            throw new WeikbestException(String.format("微信支付-平台证书序列号[%s]，已存在！请更换证书信息", shopThird.getWxPlatformSerialNo()));
        }

        return this.save(shopThird);
    }

    @Override
    public boolean updateWithShop(Long id, ShopThirdDTO shopThirdDTO) {
        ShopThird findOne = this.getOne(new QueryWrapper<ShopThird>().eq(ShopThird.WX_PAY_MCH_ID, shopThirdDTO.getWxPayMchId()));
        if (ObjectUtil.isNotNull(findOne) && !ObjectUtil.equal(findOne.getId(), id)) {
            throw new WeikbestException(String.format("微信支付-商户号[%s]，已存在！请更换", shopThirdDTO.getWxPayMchId()));
        }
        findOne = this.getOne(new QueryWrapper<ShopThird>().eq(ShopThird.WX_PAY_API_CERT_SERIAL_NO, shopThirdDTO.getWxPayApiCertSerialNo()));
        if (ObjectUtil.isNotNull(findOne) && !ObjectUtil.equal(findOne.getId(), id)) {
            throw new WeikbestException(String.format("微信支付-APIv3证书序列号[%s]，已存在！请更换", shopThirdDTO.getWxPayApiCertSerialNo()));
        }

        ShopThird shopThird = this.findById(id);
        ShopThirdMapStruct.INSTANCE.copyProperties(shopThirdDTO, shopThird);
        shopThird.setId(id);
        // 更新平台证书和设置为wxPayService中的config信息,绑定商家券事件回调地址信息
        // 根据店铺的第三方微信信息设置config
        WxPayConfig wxPayConfig = ShopThirdMapStruct.INSTANCE.converToWxPayConfig(shopThird);
        WxPayService wxPayService = thirdConfigService.wxPayService(wxPayConfig);
        // 获取平台证书信息
        setWxPlatformSerial(wxPayService, shopThird);
        // 绑定商家券事件回调地址信息
        setWxBusiFavorCallbacks(wxPayService, shopThird);

        findOne = this.getOne(new QueryWrapper<ShopThird>().eq(ShopThird.WX_PLATFORM_SERIAL_NO, shopThird.getWxPlatformSerialNo()));
        if (ObjectUtil.isNotNull(findOne) && !ObjectUtil.equal(findOne.getId(), id)) {
            throw new WeikbestException(String.format("微信支付-平台证书序列号[%s]，已存在！请更换证书信息", shopThird.getWxPlatformSerialNo()));
        }

        return this.updateById(shopThird);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateCreateBusiFavorCallbacks(Long id) {
        ShopThird shopThird = this.findById(id);
        // 根据店铺的第三方微信信息设置config
        WxPayConfig wxPayConfig = ShopThirdMapStruct.INSTANCE.converToWxPayConfig(shopThird);
        WxPayService wxPayService = thirdConfigService.wxPayService(wxPayConfig);
        // 绑定商家券事件回调地址信息
        setWxBusiFavorCallbacks(wxPayService, shopThird);
        return this.updateById(shopThird);
    }

    private void setWxPlatformSerial(WxPayService wxPayService, ShopThird shopThird) {
        try {
            // 获取平台证书信息
            JsonObject data = ThirdConfigUtil.getWxPlatformSerialNo(wxPayService);
            String serialNo = data.get("serial_no").getAsString();
            String effectiveTime = data.get("effective_time").getAsString();
            String expireTime = data.get("expire_time").getAsString();

            shopThird.setWxPlatformSerialNo(serialNo);
            shopThird.setWxPlatformEffectiveTime(DateUtil.parse(effectiveTime));
            shopThird.setWxPlatformExpireTime(DateUtil.parse(expireTime));
        } catch (Exception exp) {
            log.error(exp.getMessage());
            throw new WeikbestException(exp);
        }
    }

    private void setWxBusiFavorCallbacks(WxPayService wxPayService, ShopThird shopThird) {
        try {
            // 配置商家券事件通知
            MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();
            BusiFavorCallbacksRequest request = new BusiFavorCallbacksRequest();
            request.setMchid(wxPayService.getConfig().getMchId());
            request.setNotifyUrl(memoryService.queryConfig(ConfigConstant.WX_BUSI_FAVOR_NOTIFY_URL));
            BusiFavorCallbacksResult busiFavorCallbacks = marketingBusiFavorService.createBusiFavorCallbacks(request);
            shopThird.setIsBindBusiFavor(DictConstant.Whether.yes.getCode());
            shopThird.setBusiFavorCallback(JsonUtils.bean2Json(busiFavorCallbacks));

        } catch (Exception exp) {
            log.error(exp.getMessage());
            throw new WeikbestException(exp);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateWxBusiness(Long id, String wxBusiness) {
        ShopThird shopThird = this.findById(id);
        shopThird.setWxBusiness(wxBusiness);
        return this.updateById(shopThird);
    }

    @Override
    public WxPayService findWxPayServiceById(Long id) {
        ShopThird shopThird = this.findById(id);
        // 根据店铺的第三方微信信息设置config
        WxPayConfig wxPayConfig = ShopThirdMapStruct.INSTANCE.converToWxPayConfig(shopThird);
        // 获取平台证书信息
        return thirdConfigService.wxPayService(wxPayConfig);
    }

    @Override
    public WxPayService findWxPayServiceByOrderNumber(String orderNumber) {
        OrderInfo orderInfo = orderInfoService.findByOrderNumber(orderNumber);
        Long shopId = orderInfo.getShopId();
        return findWxPayServiceById(shopId);
    }

    @Override
    public WxPayService findWxPayServiceByWxPlatformSerialNo(String wxPlatformSerialNo) {
        ShopThird shopThird = this.getOne(new QueryWrapper<ShopThird>().eq(ShopThird.WX_PLATFORM_SERIAL_NO, wxPlatformSerialNo));
        if (ObjectUtil.isNull(shopThird)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        // 根据店铺的第三方微信信息设置config
        WxPayConfig wxPayConfig = ShopThirdMapStruct.INSTANCE.converToWxPayConfig(shopThird);
        // 获取平台证书信息
        return thirdConfigService.wxPayService(wxPayConfig);
    }

    @Override
    public WxPayService findCouponWxPayServiceByWxPlatformSerialNo(String wxPlatformSerialNo) {

        PayConfig payConfig = payConfigService.getOne(new QueryWrapper<PayConfig>().eq(PayConfig.WX_PLATFORM_SERIAL_NO, wxPlatformSerialNo).eq(PayConfig.PAY_CONFIG_TYPE, "3"));
        WxPayConfig wxPayConfig = null;

        if (ObjectUtil.isNotNull(payConfig)) {
            wxPayConfig = PayConfigMapStruct.INSTANCE.converToWxPayConfig(payConfig);
        } else {

            ShopThird shopThird = this.getOne(new QueryWrapper<ShopThird>().eq(ShopThird.WX_PLATFORM_SERIAL_NO, wxPlatformSerialNo));
            if (ObjectUtil.isNull(shopThird)) {
                throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
            }
            // 根据店铺的第三方微信信息设置config
            wxPayConfig = ShopThirdMapStruct.INSTANCE.converToWxPayConfig(shopThird);
        }
        // 获取平台证书信息
        return thirdConfigService.wxPayService(wxPayConfig);
    }

    @Override
    public WxPayService findCouponWxPayServicePayConfig() {

        PayConfig payConfig = payConfigService.getOne(new QueryWrapper<PayConfig>().eq(PayConfig.PAY_CONFIG_TYPE, "3"));

        WxPayConfig wxPayConfig = PayConfigMapStruct.INSTANCE.converToWxPayConfig(payConfig);
        ;

        return thirdConfigService.wxPayService(wxPayConfig);
    }
}
