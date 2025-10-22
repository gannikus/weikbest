package com.weikbest.pro.saas.merchat.coupon.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponSceneConfig;
import com.weikbest.pro.saas.merchat.coupon.mapper.CouponSceneConfigMapper;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponSceneConfigDTO;
import com.weikbest.pro.saas.merchat.coupon.module.mapstruct.CouponSceneConfigMapStruct;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponSceneConfigQO;
import com.weikbest.pro.saas.merchat.coupon.service.CouponSceneConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券使用场景配置表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-15
 */
@Service
public class CouponSceneConfigServiceImpl extends ServiceImpl<CouponSceneConfigMapper, CouponSceneConfig> implements CouponSceneConfigService {

    @Override
    public boolean insert(CouponSceneConfigDTO couponSceneConfigDTO) {
        CouponSceneConfig couponSceneConfig = CouponSceneConfigMapStruct.INSTANCE.converToEntity(couponSceneConfigDTO);
        return this.save(couponSceneConfig);
    }

    @Override
    public boolean updateById(Long id, CouponSceneConfigDTO couponSceneConfigDTO) {
        CouponSceneConfig couponSceneConfig = this.findById(id);
        CouponSceneConfigMapStruct.INSTANCE.copyProperties(couponSceneConfigDTO, couponSceneConfig);
        couponSceneConfig.setId(id);
        return this.updateById(couponSceneConfig);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public CouponSceneConfig findById(Long id) {
        CouponSceneConfig couponSceneConfig = this.getById(id);
        if (ObjectUtil.isNull(couponSceneConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return couponSceneConfig;
    }

    @Override
    public IPage<CouponSceneConfig> queryPage(CouponSceneConfigQO couponSceneConfigQO, PageReq pageReq) {
        QueryWrapper<CouponSceneConfig> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(couponSceneConfigQO.getCouponSceneType())) {
            wrapper.eq(CouponSceneConfig.COUPON_SCENE_TYPE, couponSceneConfigQO.getCouponSceneType());
        }
        if (StrUtil.isNotBlank(couponSceneConfigQO.getPlatformCouponReceiveOpenUrl())) {
            wrapper.eq(CouponSceneConfig.PLATFORM_COUPON_RECEIVE_OPEN_URL, couponSceneConfigQO.getPlatformCouponReceiveOpenUrl());
        }
        if (StrUtil.isNotBlank(couponSceneConfigQO.getPlatformCouponReceiveBannerUrl())) {
            wrapper.eq(CouponSceneConfig.PLATFORM_COUPON_RECEIVE_BANNER_URL, couponSceneConfigQO.getPlatformCouponReceiveBannerUrl());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public CouponSceneConfig getOrCreateCouponSceneConfigByCouponSceneType(String couponSceneType) {
        CouponSceneConfig couponSceneConfig = this.getOne(new QueryWrapper<CouponSceneConfig>().eq(CouponSceneConfig.COUPON_SCENE_TYPE, couponSceneType));
        if(ObjectUtil.isNull(couponSceneConfig)) {
            couponSceneConfig = new CouponSceneConfig();
            couponSceneConfig.setCouponSceneType(couponSceneType);
        }

        return couponSceneConfig;
    }
}
