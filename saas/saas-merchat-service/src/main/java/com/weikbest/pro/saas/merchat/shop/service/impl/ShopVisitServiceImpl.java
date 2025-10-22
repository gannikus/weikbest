package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.shop.entity.ShopVisit;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopVisitMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopVisitDTO;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopVisitMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopVisitQO;
import com.weikbest.pro.saas.merchat.shop.service.ShopVisitService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 店铺访问表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2023-02-04
 */
@Service
public class ShopVisitServiceImpl extends ServiceImpl<ShopVisitMapper, ShopVisit> implements ShopVisitService {

    @Override
    public boolean insert(ShopVisitDTO shopVisitDTO) {
        ShopVisit shopVisit = ShopVisitMapStruct.INSTANCE.converToEntity(shopVisitDTO);
        return this.save(shopVisit);
    }

    @Override
    public boolean updateById(Long id, ShopVisitDTO shopVisitDTO) {
        ShopVisit shopVisit = this.findById(id);
        ShopVisitMapStruct.INSTANCE.copyProperties(shopVisitDTO, shopVisit);
        shopVisit.setId(id);
        return this.updateById(shopVisit);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public ShopVisit findById(Long id) {
        ShopVisit shopVisit = this.getById(id);
        if (ObjectUtil.isNull(shopVisit)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopVisit;
    }

    @Override
    public IPage<ShopVisit> queryPage(ShopVisitQO shopVisitQO, PageReq pageReq) {
        QueryWrapper<ShopVisit> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(shopVisitQO.getAppId())) {
            wrapper.eq(ShopVisit.APP_ID, shopVisitQO.getAppId());
        }
        if (StrUtil.isNotBlank(shopVisitQO.getVisitShopPage())) {
            wrapper.eq(ShopVisit.VISIT_SHOP_PAGE, shopVisitQO.getVisitShopPage());
        }
        if (StrUtil.isNotBlank(shopVisitQO.getVisitProdPage())) {
            wrapper.eq(ShopVisit.VISIT_PROD_PAGE, shopVisitQO.getVisitProdPage());
        }
        if (StrUtil.isNotBlank(shopVisitQO.getVisitType())) {
            wrapper.eq(ShopVisit.VISIT_TYPE, shopVisitQO.getVisitType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
