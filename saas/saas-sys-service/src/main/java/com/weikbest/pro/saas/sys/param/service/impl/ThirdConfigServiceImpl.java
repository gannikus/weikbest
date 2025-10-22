package com.weikbest.pro.saas.sys.param.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.third.alipay.config.AlipayConfig;
import com.weikbest.pro.saas.common.third.aliyun.logistics.AliyunWuliuService;
import com.weikbest.pro.saas.common.third.aliyun.logistics.AliyunWuliuServiceImpl;
import com.weikbest.pro.saas.common.third.aliyun.logistics.AliyunWuliuServiceSimple;
import com.weikbest.pro.saas.common.third.aliyun.logistics.config.AliyunWuliuAuthConfig;
import com.weikbest.pro.saas.common.third.aliyun.logistics.config.AliyunWuliuConfig;
import com.weikbest.pro.saas.common.third.aliyun.oss.AliyunOssService;
import com.weikbest.pro.saas.common.third.aliyun.oss.AliyunOssServiceImpl;
import com.weikbest.pro.saas.common.third.aliyun.oss.AliyunOssServiceSimple;
import com.weikbest.pro.saas.common.third.aliyun.oss.config.AliyunOssConfig;
import com.weikbest.pro.saas.common.third.aliyun.sms.AliyunSendSmsService;
import com.weikbest.pro.saas.common.third.aliyun.sms.AliyunSendSmsServiceImpl;
import com.weikbest.pro.saas.common.third.aliyun.sms.AliyunSendSmsServiceSimple;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsConfig;
import com.weikbest.pro.saas.common.third.bytedance.config.ByteDanceConfig;
import com.weikbest.pro.saas.common.third.wx.miniapp.WxMaServiceBuildFactory;
import com.weikbest.pro.saas.common.third.wx.miniapp.config.WxMaProperties;
import com.weikbest.pro.saas.common.third.wx.pay.WxPayServiceBuildFactory;
import com.weikbest.pro.saas.common.third.wx.pay.config.WxPayProperties;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.context.SpringContext;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.param.entity.ThirdConfig;
import com.weikbest.pro.saas.sys.param.mapper.ThirdConfigMapper;
import com.weikbest.pro.saas.sys.param.module.dto.ThirdConfigDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.ThirdConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.ThirdConfigQO;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 第三方平台配置表   服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
@Service
public class ThirdConfigServiceImpl extends ServiceImpl<ThirdConfigMapper, ThirdConfig> implements ThirdConfigService {

    /**
     * 第三方平台配置表ID
     */
    private static final Long THIRD_CONFIG_ID = 1010L;

    @Value("${thridconfig.properties}")
    private boolean thridconfigFlag;

    @Resource
    private AliyunWuliuConfig aliyunWuliuConfig;

    @Resource
    private RedisContext redisContext;

    @Resource
    private WxPayServiceBuildFactory wxPayServiceBuildFactory;

    @Resource
    private WxMaServiceBuildFactory wxMaServiceBuildFactory;

    @Override
    public boolean insert(ThirdConfigDTO thirdConfigDTO) {
        ThirdConfig thirdConfig = ThirdConfigMapStruct.INSTANCE.converToEntity(thirdConfigDTO);
        thirdConfig.setId(THIRD_CONFIG_ID);
        return this.save(thirdConfig);
    }

    @Override
    public boolean updateById(Long id, ThirdConfigDTO thirdConfigDTO) {
        ThirdConfig thirdConfig = this.findById(id);
        ThirdConfigMapStruct.INSTANCE.copyProperties(thirdConfigDTO, thirdConfig);
        thirdConfig.setId(id);
        return this.updateById(thirdConfig);
    }

    @Override
    public boolean saveOrUpdate(ThirdConfigDTO thirdConfigDTO) {
        try {
            redisContext.del(RedisKey.generalKey(RedisKey.THIRD_CONFIG_EXPIRE_KEY, THIRD_CONFIG_ID));
            ThirdConfig thirdConfig = this.getById(THIRD_CONFIG_ID);
            if (ObjectUtil.isEmpty(thirdConfig)) {
                // 新增
                return insert(thirdConfigDTO);
            } else {
                // 更新
                return updateById(THIRD_CONFIG_ID, thirdConfigDTO);
            }
        } finally {
            redisContext.del(RedisKey.generalKey(RedisKey.THIRD_CONFIG_EXPIRE_KEY, THIRD_CONFIG_ID));
        }
    }

    @Override
    public ThirdConfig findById(Long id) {
        ThirdConfig thirdConfig = this.getById(id);
        if (ObjectUtil.isNull(thirdConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return thirdConfig;
    }

    @Override
    public IPage<ThirdConfig> queryPage(ThirdConfigQO thirdConfigQO, PageReq pageReq) {
        QueryWrapper<ThirdConfig> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayAppId())) {
            wrapper.eq(ThirdConfig.WX_PAY_APP_ID, thirdConfigQO.getWxPayAppId());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPaySubAppId())) {
            wrapper.eq(ThirdConfig.WX_PAY_SUB_APP_ID, thirdConfigQO.getWxPaySubAppId());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayMchId())) {
            wrapper.eq(ThirdConfig.WX_PAY_MCH_ID, thirdConfigQO.getWxPayMchId());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayMchKey())) {
            wrapper.eq(ThirdConfig.WX_PAY_MCH_KEY, thirdConfigQO.getWxPayMchKey());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayEntPayKey())) {
            wrapper.eq(ThirdConfig.WX_PAY_ENT_PAY_KEY, thirdConfigQO.getWxPayEntPayKey());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPaySubMchId())) {
            wrapper.eq(ThirdConfig.WX_PAY_SUB_MCH_ID, thirdConfigQO.getWxPaySubMchId());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayNotifyUrl())) {
            wrapper.eq(ThirdConfig.WX_PAY_NOTIFY_URL, thirdConfigQO.getWxPayNotifyUrl());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayKeyPath())) {
            wrapper.eq(ThirdConfig.WX_PAY_KEY_PATH, thirdConfigQO.getWxPayKeyPath());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayPrivateKeyPath())) {
            wrapper.eq(ThirdConfig.WX_PAY_PRIVATE_KEY_PATH, thirdConfigQO.getWxPayPrivateKeyPath());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayPrivateCertPath())) {
            wrapper.eq(ThirdConfig.WX_PAY_PRIVATE_CERT_PATH, thirdConfigQO.getWxPayPrivateCertPath());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayApiV3Key())) {
            wrapper.eq(ThirdConfig.WX_PAY_API_V3_KEY, thirdConfigQO.getWxPayApiV3Key());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayApiCertSerialNo())) {
            wrapper.eq(ThirdConfig.WX_PAY_API_CERT_SERIAL_NO, thirdConfigQO.getWxPayApiCertSerialNo());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayApiServiceId())) {
            wrapper.eq(ThirdConfig.WX_PAY_API_SERVICE_ID, thirdConfigQO.getWxPayApiServiceId());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPayPayScoreNotifyUrl())) {
            wrapper.eq(ThirdConfig.WX_PAY_PAY_SCORE_NOTIFY_URL, thirdConfigQO.getWxPayPayScoreNotifyUrl());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxPaypayScorePermissionNotifyUrl())) {
            wrapper.eq(ThirdConfig.WX_PAY_PAY_SCORE_PERMISSION_NOTIFY_URL, thirdConfigQO.getWxPaypayScorePermissionNotifyUrl());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxMiniappAppId())) {
            wrapper.eq(ThirdConfig.WX_MINIAPP_APP_ID, thirdConfigQO.getWxMiniappAppId());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxMiniappAppSecret())) {
            wrapper.eq(ThirdConfig.WX_MINIAPP_APP_SECRET, thirdConfigQO.getWxMiniappAppSecret());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxMiniappToken())) {
            wrapper.eq(ThirdConfig.WX_MINIAPP_TOKEN, thirdConfigQO.getWxMiniappToken());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxMiniappAesKey())) {
            wrapper.eq(ThirdConfig.WX_MINIAPP_AES_KEY, thirdConfigQO.getWxMiniappAesKey());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxMiniappOriginalId())) {
            wrapper.eq(ThirdConfig.WX_MINIAPP_ORIGINAL_ID, thirdConfigQO.getWxMiniappOriginalId());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxMiniappMsgDataFormat())) {
            wrapper.eq(ThirdConfig.WX_MINIAPP_MSG_DATA_FORMAT, thirdConfigQO.getWxMiniappMsgDataFormat());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getWxMiniappColudEnv())) {
            wrapper.eq(ThirdConfig.WX_MINIAPP_COLUD_ENV, thirdConfigQO.getWxMiniappColudEnv());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunWuliuAppkey())) {
            wrapper.eq(ThirdConfig.ALIYUN_WULIU_APPKEY, thirdConfigQO.getAliyunWuliuAppkey());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunWuliuAppcode())) {
            wrapper.eq(ThirdConfig.ALIYUN_WULIU_APPCODE, thirdConfigQO.getAliyunWuliuAppcode());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunWuliuAppsecret())) {
            wrapper.eq(ThirdConfig.ALIYUN_WULIU_APPSECRET, thirdConfigQO.getAliyunWuliuAppsecret());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunSmsSignname())) {
            wrapper.eq(ThirdConfig.ALIYUN_SMS_SIGNNAME, thirdConfigQO.getAliyunSmsSignname());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunSmsAccesskeyId())) {
            wrapper.eq(ThirdConfig.ALIYUN_SMS_ACCESSKEY_ID, thirdConfigQO.getAliyunSmsAccesskeyId());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunSmsAccesskeySecret())) {
            wrapper.eq(ThirdConfig.ALIYUN_SMS_ACCESSKEY_SECRET, thirdConfigQO.getAliyunSmsAccesskeySecret());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunOssFileEndpoint())) {
            wrapper.eq(ThirdConfig.ALIYUN_OSS_FILE_ENDPOINT, thirdConfigQO.getAliyunOssFileEndpoint());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunOssFileKeyid())) {
            wrapper.eq(ThirdConfig.ALIYUN_OSS_FILE_KEYID, thirdConfigQO.getAliyunOssFileKeyid());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunOssFileKeysecret())) {
            wrapper.eq(ThirdConfig.ALIYUN_OSS_FILE_KEYSECRET, thirdConfigQO.getAliyunOssFileKeysecret());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunOssFileBucketname())) {
            wrapper.eq(ThirdConfig.ALIYUN_OSS_FILE_BUCKETNAME, thirdConfigQO.getAliyunOssFileBucketname());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunOcrAppsecret())) {
            wrapper.eq(ThirdConfig.ALIYUN_OCR_APPSECRET, thirdConfigQO.getAliyunOcrAppsecret());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAliyunOcrAppcode())) {
            wrapper.eq(ThirdConfig.ALIYUN_OCR_APPCODE, thirdConfigQO.getAliyunOcrAppcode());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAlipayAppid())) {
            wrapper.eq(ThirdConfig.ALIPAY_APPID, thirdConfigQO.getAlipayAppid());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAlipayPid())) {
            wrapper.eq(ThirdConfig.ALIPAY_PID, thirdConfigQO.getAlipayPid());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAlipayPrivateKey())) {
            wrapper.eq(ThirdConfig.ALIPAY_PRIVATE_KEY, thirdConfigQO.getAlipayPrivateKey());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAlipayPublicKey())) {
            wrapper.eq(ThirdConfig.ALIPAY_PUBLIC_KEY, thirdConfigQO.getAlipayPublicKey());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAlipayReturnUrl())) {
            wrapper.eq(ThirdConfig.ALIPAY_RETURN_URL, thirdConfigQO.getAlipayReturnUrl());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAlipayAppCertPath())) {
            wrapper.eq(ThirdConfig.ALIPAY_APP_CERT_PATH, thirdConfigQO.getAlipayAppCertPath());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAlipayCertPath())) {
            wrapper.eq(ThirdConfig.ALIPAY_CERT_PATH, thirdConfigQO.getAlipayCertPath());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getAlipayRootCertPath())) {
            wrapper.eq(ThirdConfig.ALIPAY_ROOT_CERT_PATH, thirdConfigQO.getAlipayRootCertPath());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getToutiaoAppid())) {
            wrapper.eq(ThirdConfig.TOUTIAO_APPID, thirdConfigQO.getToutiaoAppid());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getToutiaoAppsecret())) {
            wrapper.eq(ThirdConfig.TOUTIAO_APPSECRET, thirdConfigQO.getToutiaoAppsecret());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getToutiaoSalt())) {
            wrapper.eq(ThirdConfig.TOUTIAO_SALT, thirdConfigQO.getToutiaoSalt());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getToutiaoNotifyUrl())) {
            wrapper.eq(ThirdConfig.TOUTIAO_NOTIFY_URL, thirdConfigQO.getToutiaoNotifyUrl());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getToutiaoRefundNotifyUrl())) {
            wrapper.eq(ThirdConfig.TOUTIAO_REFUND_NOTIFY_URL, thirdConfigQO.getToutiaoRefundNotifyUrl());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getToutiaoAccessToken())) {
            wrapper.eq(ThirdConfig.TOUTIAO_ACCESS_TOKEN, thirdConfigQO.getToutiaoAccessToken());
        }
        if (StrUtil.isNotBlank(thirdConfigQO.getToutiaoAccessTokenUpdateTime())) {
            wrapper.eq(ThirdConfig.TOUTIAO_ACCESS_TOKEN_UPDATE_TIME, thirdConfigQO.getToutiaoAccessTokenUpdateTime());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }


    @Override
    public ThirdConfig findThirdConfig() {
        return findThirdConfig(true);
    }

    @Override
    public ThirdConfig findThirdConfig(boolean notnullFlag) {
        ThirdConfig thirdConfig;
        // 先从redis里查，redis查不到在到数据库里查
        String confRedis = (String) redisContext.get(RedisKey.generalKey(RedisKey.THIRD_CONFIG_EXPIRE_KEY, THIRD_CONFIG_ID));
        if (StrUtil.isNotEmpty(confRedis)) {
            thirdConfig = JsonUtils.json2Bean(confRedis, ThirdConfig.class);
        } else {
            thirdConfig = this.getById(THIRD_CONFIG_ID);
            if (notnullFlag && ObjectUtil.isEmpty(thirdConfig)) {
                throw new WeikbestException("请先配置thirdConfig表数据！");
            }
            // 存入Redis中,一天后失效
            if (ObjectUtil.isNotEmpty(thirdConfig)) {
                redisContext.set(RedisKey.generalKey(RedisKey.THIRD_CONFIG_EXPIRE_KEY, THIRD_CONFIG_ID), JsonUtils.bean2Json(thirdConfig), 24 * 60 * 60);
            }
        }
        return thirdConfig;
    }

    @Override
    public AliyunOssService aliyunOssService() {
        if (thridconfigFlag) {
            return SpringContext.getInstance().getBean(AliyunOssServiceSimple.class);
        }
        AliyunOssConfig aliyunOssConfig = aliyunOssConfig();
        return AliyunOssServiceImpl.build(aliyunOssConfig);
    }

    protected AliyunOssConfig aliyunOssConfig() {
        ThirdConfig thirdConfig = findThirdConfig(true);
        if (StrUtil.isEmpty(thirdConfig.getAliyunOssFileEndpoint()) || StrUtil.isEmpty(thirdConfig.getAliyunOssFileKeyid())
                || StrUtil.isEmpty(thirdConfig.getAliyunOssFileKeysecret()) || StrUtil.isEmpty(thirdConfig.getAliyunOssFileBucketname())) {
            throw new WeikbestException("请先配置thirdConfig表阿里云oss数据！");
        }
        return new AliyunOssConfig(thirdConfig.getAliyunOssFileEndpoint(), thirdConfig.getAliyunOssFileKeyid(),
                thirdConfig.getAliyunOssFileKeysecret(), thirdConfig.getAliyunOssFileBucketname());
    }

    @Override
    public AliyunWuliuService aliyunWuliuService() {
        if (thridconfigFlag) {
            return SpringContext.getInstance().getBean(AliyunWuliuServiceSimple.class);
        }
        AliyunWuliuAuthConfig aliyunWuliuAuthConfig = aliyunWuliuAuthConfig();
        return AliyunWuliuServiceImpl.build(aliyunWuliuConfig, aliyunWuliuAuthConfig);
    }

    protected AliyunWuliuAuthConfig aliyunWuliuAuthConfig() {
        ThirdConfig thirdConfig = findThirdConfig(true);
        if (StrUtil.isEmpty(thirdConfig.getAliyunWuliuAppcode()) || StrUtil.isEmpty(thirdConfig.getAliyunWuliuAppkey())
                || StrUtil.isEmpty(thirdConfig.getAliyunWuliuAppsecret())) {
            throw new WeikbestException("请先配置thirdConfig表阿里云物流数据！");
        }
        return new AliyunWuliuAuthConfig(thirdConfig.getAliyunWuliuAppcode(), thirdConfig.getAliyunWuliuAppkey(),
                thirdConfig.getAliyunWuliuAppsecret());
    }

    @Override
    public AliyunSendSmsService aliyunSmsService() {
        if (thridconfigFlag) {
            return SpringContext.getInstance().getBean(AliyunSendSmsServiceSimple.class);
        }
        AliyunSmsConfig aliyunSmsConfig = aliyunSmsConfig();
        return AliyunSendSmsServiceImpl.build(aliyunSmsConfig);
    }

    protected AliyunSmsConfig aliyunSmsConfig() {
        ThirdConfig thirdConfig = findThirdConfig(true);
        if (StrUtil.isEmpty(thirdConfig.getAliyunSmsSignname()) || StrUtil.isEmpty(thirdConfig.getAliyunSmsAccesskeyId())
                || StrUtil.isEmpty(thirdConfig.getAliyunSmsAccesskeySecret())) {
            throw new WeikbestException("请先配置thirdConfig表阿里云短信数据！");
        }
        return new AliyunSmsConfig(thirdConfig.getAliyunSmsSignname(),
                thirdConfig.getAliyunSmsAccesskeyId(), thirdConfig.getAliyunSmsAccesskeySecret());
    }

    @Override
    public WxPayService wxPayService() {
        WxPayProperties wxPayProperties = wxPayConfig();
        return wxPayServiceBuildFactory.wxPayService(wxPayProperties);
    }

    @Override
    public WxPayService wxPayService(WxPayConfig wxPayConfig) {
        return wxPayServiceBuildFactory.wxPayService(wxPayConfig);
    }

    @Override
    public WxMaService wxMaService() {
        WxMaProperties wxMaProperties = wxMaConfig();
        return wxMaServiceBuildFactory.wxMaService(wxMaProperties);
    }

    @Override
    public WxMaService wxMaService(String appId) {
        return wxMaServiceBuildFactory.wxMaService(appId);
    }

    @Override
    public WxMaService wxMaService(WxMaConfig wxMaConfig) {
        return wxMaServiceBuildFactory.wxMaService(wxMaConfig);
    }

    public WxPayProperties wxPayConfig() {
        ThirdConfig thirdConfig = findThirdConfig(true);
//        if (StrUtil.isEmpty(thirdConfig.getWxPayAppId()) ||
//                StrUtil.isEmpty(thirdConfig.getWxPayMchId()) ||
//                StrUtil.isEmpty(thirdConfig.getWxPayMchKey()) ||
//                StrUtil.isEmpty(thirdConfig.getWxPayApiV3Key()) ||
//                StrUtil.isEmpty(thirdConfig.getWxPayKeyPath()) ||
//                StrUtil.isEmpty(thirdConfig.getWxPayPrivateCertPath()) ||
//                StrUtil.isEmpty(thirdConfig.getWxPayPrivateKeyPath())) {
//            throw new WeikbestException("请先配置thirdConfig表微信支付数据！");
//        }
        return ThirdConfigMapStruct.INSTANCE.converToWxPayProperties(thirdConfig);
    }

    public WxMaProperties wxMaConfig() {
        ThirdConfig thirdConfig = findThirdConfig(true);
        if (StrUtil.isEmpty(thirdConfig.getWxMiniappAppId()) ||
                StrUtil.isEmpty(thirdConfig.getWxMiniappAppSecret())) {
            throw new WeikbestException("请先配置thirdConfig表微信小程序数据！");
        }
        return ThirdConfigMapStruct.INSTANCE.converToWxMaProperties(thirdConfig);
    }

    public ByteDanceConfig byteDanceConfig() {
        ThirdConfig thirdConfig = findThirdConfig(true);
        if (StrUtil.isEmpty(thirdConfig.getToutiaoAppid()) ||
                StrUtil.isEmpty(thirdConfig.getToutiaoAppsecret()) ||
                StrUtil.isEmpty(thirdConfig.getToutiaoSalt()) ||
                StrUtil.isEmpty(thirdConfig.getToutiaoNotifyUrl()) ||
                StrUtil.isEmpty(thirdConfig.getToutiaoRefundNotifyUrl())) {
            throw new WeikbestException("请先配置thirdConfig表抖音数据！");
        }
        return new ByteDanceConfig(thirdConfig.getToutiaoAppid(), thirdConfig.getToutiaoAppsecret(), thirdConfig.getToutiaoSalt(),
                thirdConfig.getToutiaoNotifyUrl(), thirdConfig.getToutiaoRefundNotifyUrl());
    }

    public AlipayConfig alipayConfig() {
        ThirdConfig thirdConfig = findThirdConfig(true);
        if (StrUtil.isEmpty(thirdConfig.getAlipayAppid()) ||
                StrUtil.isEmpty(thirdConfig.getAlipayPid()) ||
                StrUtil.isEmpty(thirdConfig.getAlipayPrivateKey()) ||
                StrUtil.isEmpty(thirdConfig.getAlipayPublicKey()) ||
                StrUtil.isEmpty(thirdConfig.getAlipayReturnUrl())) {
            throw new WeikbestException("请先配置thirdConfig表支付宝数据！");
        }
        return new AlipayConfig(thirdConfig.getAlipayAppid(), thirdConfig.getAlipayPid(), thirdConfig.getAlipayPrivateKey(),
                thirdConfig.getAlipayPublicKey(), thirdConfig.getAlipayReturnUrl(),
                thirdConfig.getAlipayAppCertPath(), thirdConfig.getAlipayCertPath(), thirdConfig.getAlipayRootCertPath());
    }
}
