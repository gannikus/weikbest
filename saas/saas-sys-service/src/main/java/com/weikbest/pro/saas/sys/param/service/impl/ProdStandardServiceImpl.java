package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.service.MarketingMediaService;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.third.aliyun.oss.AliyunOssService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.ProdStandard;
import com.weikbest.pro.saas.sys.param.mapper.ProdStandardMapper;
import com.weikbest.pro.saas.sys.param.module.dto.ProdStandardDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.ProdStandardMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.ProdStandardQO;
import com.weikbest.pro.saas.sys.param.service.PayConfigService;
import com.weikbest.pro.saas.sys.param.service.ProdStandardService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import com.weikbest.pro.saas.sys.param.util.ThirdConfigUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统商品规范表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
@Service
public class ProdStandardServiceImpl extends ServiceImpl<ProdStandardMapper, ProdStandard> implements ProdStandardService {

    /**
     * 系统商品规范表ID
     */
    private static final Long PROD_STANDARD_ID = 1010L;

    @Resource
    private RedisContext redisContext;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private PayConfigService payConfigService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(ProdStandardDTO prodStandardDTO) {
        ProdStandard prodStandard = ProdStandardMapStruct.INSTANCE.converToEntity(prodStandardDTO);
        prodStandard.setId(PROD_STANDARD_ID);
        return this.save(prodStandard);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, ProdStandardDTO prodStandardDTO) {
        ProdStandard prodStandard = this.findById(id);
        ProdStandardMapStruct.INSTANCE.copyProperties(prodStandardDTO, prodStandard);
        prodStandard.setId(id);
        return this.updateById(prodStandard);
    }

    @Override
    public boolean saveOrUpdate(ProdStandardDTO prodStandardDTO) {
        try {
            redisContext.del(RedisKey.PROD_STANDARD_EXPIRE_KEY);
            ProdStandard prodStandard = this.getById(PROD_STANDARD_ID);

            AliyunOssService aliyunOssService = thirdConfigService.aliyunOssService();

            // 调用微信接口 上传图片
            MarketingMediaService marketingMediaService = payConfigService.findWxPayServiceByPayConfigType(DictConstant.PayConfigType.TYPE_3.getCode()).getMarketingMediaService();

            // 回流优惠券卡包-默认图片
            String refluxCouponImageOssurl = prodStandardDTO.getRefluxCouponImageOssurl();
            prodStandardDTO.setRefluxCouponImageUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, refluxCouponImageOssurl));
            // 立减优惠券卡包-默认图标
            String promptlyMerchatLogoOssurl = prodStandardDTO.getPromptlyMerchatLogoOssurl();
            prodStandardDTO.setPromptlyMerchatLogoUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, promptlyMerchatLogoOssurl));
            // 立减优惠券卡包-默认图片
            String promptlyCouponImageOssurl = prodStandardDTO.getPromptlyCouponImageOssurl();
            prodStandardDTO.setPromptlyCouponImageUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, promptlyCouponImageOssurl));
            // 平台优惠券卡包-默认图标
            String platformMerchatLogoOssurl = prodStandardDTO.getPlatformMerchatLogoOssurl();
            prodStandardDTO.setPlatformMerchatLogoUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, platformMerchatLogoOssurl));
            // 平台优惠券卡包-默认图片
            String platformCouponImageOssurl = prodStandardDTO.getPlatformCouponImageOssurl();
            prodStandardDTO.setPlatformCouponImageUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, platformCouponImageOssurl));

            if (ObjectUtil.isEmpty(prodStandard)) {
                // 新增
                return insert(prodStandardDTO);
            } else {
                // 更新
                return updateById(PROD_STANDARD_ID, prodStandardDTO);
            }
        } finally {
            redisContext.del(RedisKey.PROD_STANDARD_EXPIRE_KEY);
        }
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public ProdStandard findById(Long id) {
        ProdStandard prodStandard = this.getById(id);
        if (ObjectUtil.isNull(prodStandard)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodStandard;
    }

    @Override
    public IPage<ProdStandard> queryPage(ProdStandardQO prodStandardQO, PageReq pageReq) {
        QueryWrapper<ProdStandard> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodStandardQO.getPayFailThemeUrl())) {
            wrapper.eq(ProdStandard.PAY_FAIL_THEME_URL, prodStandardQO.getPayFailThemeUrl());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getLeftSlideThemeUrl())) {
            wrapper.eq(ProdStandard.LEFT_SLIDE_THEME_URL, prodStandardQO.getLeftSlideThemeUrl());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getRefluxCouponThemeUrl())) {
            wrapper.eq(ProdStandard.REFLUX_COUPON_THEME_URL, prodStandardQO.getRefluxCouponThemeUrl());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getRefluxCouponImageOssurl())) {
            wrapper.eq(ProdStandard.REFLUX_COUPON_IMAGE_URL, prodStandardQO.getRefluxCouponImageOssurl());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getRefluxBackgroundColor())) {
            wrapper.eq(ProdStandard.REFLUX_BACKGROUND_COLOR, prodStandardQO.getRefluxBackgroundColor());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getPromptlyMerchatLogoOssurl())) {
            wrapper.eq(ProdStandard.PROMPTLY_MERCHAT_LOGO_URL, prodStandardQO.getPromptlyMerchatLogoOssurl());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getPromptlyCouponImageOssurl())) {
            wrapper.eq(ProdStandard.PROMPTLY_COUPON_IMAGE_URL, prodStandardQO.getPromptlyCouponImageOssurl());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getPromptlyBackgroundColor())) {
            wrapper.eq(ProdStandard.PROMPTLY_BACKGROUND_COLOR, prodStandardQO.getPromptlyBackgroundColor());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getPlatformMerchatLogoOssurl())) {
            wrapper.eq(ProdStandard.PLATFORM_MERCHAT_LOGO_URL, prodStandardQO.getPlatformMerchatLogoOssurl());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getPlatformCouponImageOssurl())) {
            wrapper.eq(ProdStandard.PLATFORM_COUPON_IMAGE_URL, prodStandardQO.getPlatformCouponImageOssurl());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getPlatformBackgroundColor())) {
            wrapper.eq(ProdStandard.PLATFORM_BACKGROUND_COLOR, prodStandardQO.getPlatformBackgroundColor());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getUseMethod())) {
            wrapper.eq(ProdStandard.USE_METHOD, prodStandardQO.getUseMethod());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getMiniProgramsPath())) {
            wrapper.eq(ProdStandard.MINI_PROGRAMS_PATH, prodStandardQO.getMiniProgramsPath());
        }
        if (StrUtil.isNotBlank(prodStandardQO.getCouponCodeMode())) {
            wrapper.eq(ProdStandard.COUPON_CODE_MODE, prodStandardQO.getCouponCodeMode());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public ProdStandard findProdStandard() {
        return this.findProdStandard(true);
    }

    @Override
    public ProdStandard findProdStandard(boolean notnullFlag) {
        ProdStandard prodStandard;
        // 先从redis里查，redis查不到在到数据库里查
        String confRedis = (String) redisContext.get(RedisKey.PROD_STANDARD_EXPIRE_KEY);
        if (StrUtil.isNotEmpty(confRedis)) {
            prodStandard = JsonUtils.json2Bean(confRedis, ProdStandard.class);
        } else {
            prodStandard = this.getById(PROD_STANDARD_ID);
            if (notnullFlag && ObjectUtil.isEmpty(prodStandard)) {
                throw new WeikbestException("请先配置prodStandard表数据！");
            }
            // 存入Redis中,一天后失效
            if (ObjectUtil.isNotEmpty(prodStandard)) {
                redisContext.set(RedisKey.PROD_STANDARD_EXPIRE_KEY, JsonUtils.bean2Json(prodStandard), 24 * 60 * 60);
            }
        }
        return prodStandard;
    }
}
