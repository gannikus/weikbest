package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.merchat.shop.entity.ShopNonRegion;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopNonRegionMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopNonRegionDTO;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopNonRegionMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopNonRegionQO;
import com.weikbest.pro.saas.merchat.shop.service.ShopNonRegionService;
import com.weikbest.pro.saas.sys.param.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 不配送地区表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ShopNonRegionServiceImpl extends ServiceImpl<ShopNonRegionMapper, ShopNonRegion> implements ShopNonRegionService {

    @Resource
    private RegionService regionService;

    @Override
    public boolean insert(ShopNonRegionDTO shopNonRegionDTO) {
        ShopNonRegion shopNonRegion = ShopNonRegionMapStruct.INSTANCE.converToEntity(shopNonRegionDTO);
        return this.save(shopNonRegion);
    }

    @Override
    public boolean updateById(Long id, ShopNonRegionDTO shopNonRegionDTO) {
        ShopNonRegion shopNonRegion = this.findById(id);
        ShopNonRegionMapStruct.INSTANCE.copyProperties(shopNonRegionDTO, shopNonRegion);
        shopNonRegion.setId(id);
        return this.updateById(shopNonRegion);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdate(Long shopId, List<ShopNonRegionDTO> shopNonRegionDTOList) {
        this.remove(new QueryWrapper<ShopNonRegion>().eq(ShopNonRegion.SHOP_ID, shopId));

        List<ShopNonRegion> shopNonRegionList = shopNonRegionDTOList.stream().map(shopNonRegionDTO -> {
            ShopNonRegion shopNonRegion = ShopNonRegionMapStruct.INSTANCE.converToEntity(shopNonRegionDTO);
            shopNonRegion.setShopId(shopId);
            return shopNonRegion;
        }).collect(Collectors.toList());

        return this.saveBatch(shopNonRegionList);
    }

    @Override
    public ShopNonRegion findById(Long id) {
        ShopNonRegion shopNonRegion = this.getById(id);
        if (ObjectUtil.isNull(shopNonRegion)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopNonRegion;
    }

    @Override
    public IPage<ShopNonRegion> queryPage(ShopNonRegionQO shopNonRegionQO, PageReq pageReq) {
        QueryWrapper<ShopNonRegion> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(shopNonRegionQO.getRegionName())) {
            wrapper.eq(ShopNonRegion.REGION_NAME, shopNonRegionQO.getRegionName());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<Dtree> queryChooseTree(Long shopId) {
        List<ShopNonRegion> shopNonRegionList = this.list(new QueryWrapper<ShopNonRegion>().eq(ShopNonRegion.SHOP_ID, shopId));
        if (CollectionUtil.isEmpty(shopNonRegionList)) {
            return regionService.queryTree();
        }

        Set<Integer> regionAdcodeList = shopNonRegionList.stream().map(ShopNonRegion::getRegionAdcode).collect(Collectors.toSet());
        return regionService.queryTree(regionAdcodeList);
    }

    @Override
    public List<ShopNonRegion> listByShopId(Long shopId) {
        return this.list(new QueryWrapper<ShopNonRegion>().eq(ShopNonRegion.SHOP_ID,shopId));
    }
}
