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
import com.weikbest.pro.saas.merchat.coupon.entity.CouponScene;
import com.weikbest.pro.saas.merchat.coupon.mapper.CouponSceneMapper;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponSceneDTO;
import com.weikbest.pro.saas.merchat.coupon.module.mapstruct.CouponSceneMapStruct;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponSceneQO;
import com.weikbest.pro.saas.merchat.coupon.service.CouponSceneService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券使用场景表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
@Service
public class CouponSceneServiceImpl extends ServiceImpl<CouponSceneMapper, CouponScene> implements CouponSceneService {

    @Override
    public boolean insert(CouponSceneDTO couponSceneDTO) {
        CouponScene couponScene = CouponSceneMapStruct.INSTANCE.converToEntity(couponSceneDTO);
        return this.save(couponScene);
    }

    @Override
    public boolean updateById(Long id, CouponSceneDTO couponSceneDTO) {
        CouponScene couponScene = this.findById(id);
        CouponSceneMapStruct.INSTANCE.copyProperties(couponSceneDTO, couponScene);
        couponScene.setId(id);
        return this.updateById(couponScene);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public CouponScene findById(Long id) {
        CouponScene couponScene = this.getById(id);
        if (ObjectUtil.isNull(couponScene)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return couponScene;
    }

    @Override
    public IPage<CouponScene> queryPage(CouponSceneQO couponSceneQO, PageReq pageReq) {
        QueryWrapper<CouponScene> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(couponSceneQO.getCouponType())) {
            wrapper.eq(CouponScene.COUPON_TYPE, couponSceneQO.getCouponType());
        }
        if (StrUtil.isNotBlank(couponSceneQO.getCouponSceneType())) {
            wrapper.eq(CouponScene.COUPON_SCENE_TYPE, couponSceneQO.getCouponSceneType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
