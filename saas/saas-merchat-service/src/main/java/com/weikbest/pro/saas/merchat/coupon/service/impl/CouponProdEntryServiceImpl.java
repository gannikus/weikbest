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
import com.weikbest.pro.saas.merchat.coupon.entity.CouponProdEntry;
import com.weikbest.pro.saas.merchat.coupon.mapper.CouponProdEntryMapper;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponProdEntryDTO;
import com.weikbest.pro.saas.merchat.coupon.module.mapstruct.CouponProdEntryMapStruct;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponProdEntryQO;
import com.weikbest.pro.saas.merchat.coupon.service.CouponProdEntryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 优惠券与适用商品拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Service
public class CouponProdEntryServiceImpl extends ServiceImpl<CouponProdEntryMapper, CouponProdEntry> implements CouponProdEntryService {

    @Override
    public boolean insert(CouponProdEntryDTO couponProdEntryDTO) {
        CouponProdEntry couponProdEntry = CouponProdEntryMapStruct.INSTANCE.converToEntity(couponProdEntryDTO);
        return this.save(couponProdEntry);
    }

    @Override
    public boolean updateById(Long id, CouponProdEntryDTO couponProdEntryDTO) {
        CouponProdEntry couponProdEntry = this.findById(id);
        CouponProdEntryMapStruct.INSTANCE.copyProperties(couponProdEntryDTO, couponProdEntry);
        couponProdEntry.setId(id);
        return this.updateById(couponProdEntry);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public CouponProdEntry findById(Long id) {
        CouponProdEntry couponProdEntry = this.getById(id);
        if (ObjectUtil.isNull(couponProdEntry)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return couponProdEntry;
    }

    @Override
    public IPage<CouponProdEntry> queryPage(CouponProdEntryQO couponProdEntryQO, PageReq pageReq) {
        QueryWrapper<CouponProdEntry> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(couponProdEntryQO.getCouponType())) {
            wrapper.eq(CouponProdEntry.COUPON_TYPE, couponProdEntryQO.getCouponType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBatchWithCouponId(Long id, String shopCouponType, List<Long> prodIdList) {
        // 先删除
        this.remove(new QueryWrapper<CouponProdEntry>().eq(CouponProdEntry.ID, id));
        // 在新增
        List<CouponProdEntry> couponProdEntryList = prodIdList.stream().map(prodId -> {
            CouponProdEntry couponProdEntry = new CouponProdEntry();
            couponProdEntry.setProdId(prodId).setCouponType(shopCouponType).setId(id);
            return couponProdEntry;
        }).collect(Collectors.toList());
        this.saveBatch(couponProdEntryList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveWithCouponId(Long id, String shopCouponType, Long prodId) {
        // 先删除
        this.remove(new QueryWrapper<CouponProdEntry>().eq(CouponProdEntry.ID, id));
        // 在新增
        CouponProdEntry couponProdEntry = new CouponProdEntry();
        couponProdEntry.setProdId(prodId).setCouponType(shopCouponType).setId(id);
        this.save(couponProdEntry);
    }

    @Override
    public List<CouponProdEntry> queryByCouponId(Long id) {
        return this.list(new QueryWrapper<CouponProdEntry>().eq(CouponProdEntry.ID, id));
    }

    @Override
    public List<CouponProdEntry> queryCouponProd(CouponProdEntryQO couponProdEntryQO) {
        QueryWrapper<CouponProdEntry> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(couponProdEntryQO.getProdId())) {
            wrapper.eq(CouponProdEntry.PROD_ID, couponProdEntryQO.getProdId());
        }
        if (StrUtil.isNotBlank(couponProdEntryQO.getCouponType())) {
            wrapper.eq(CouponProdEntry.COUPON_TYPE, couponProdEntryQO.getCouponType());
        }
        return this.list(wrapper);
    }
}
