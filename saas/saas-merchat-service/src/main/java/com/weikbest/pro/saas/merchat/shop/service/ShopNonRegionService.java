package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.merchat.shop.entity.ShopNonRegion;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopNonRegionDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopNonRegionQO;

import java.util.List;

/**
 * <p>
 * 不配送地区表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ShopNonRegionService extends IService<ShopNonRegion> {

    /**
     * 新增数据
     *
     * @param shopNonRegionDTO shopNonRegionDTO
     * @return 新增结果
     */
    boolean insert(ShopNonRegionDTO shopNonRegionDTO);

    /**
     * 更新数据
     *
     * @param id               ID
     * @param shopNonRegionDTO shopNonRegionDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopNonRegionDTO shopNonRegionDTO);


    /**
     * 新增或更新数据
     *
     * @param shopId
     * @param shopNonRegionDTOList
     * @return
     */
    boolean saveOrUpdate(Long shopId, List<ShopNonRegionDTO> shopNonRegionDTOList);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ShopNonRegion findById(Long id);

    /**
     * 分页查询
     *
     * @param shopNonRegionQO
     * @param pageReq
     * @return
     */
    IPage<ShopNonRegion> queryPage(ShopNonRegionQO shopNonRegionQO, PageReq pageReq);

    /**
     * 查询不配送地区
     *
     * @param shopId
     * @return
     */
    List<Dtree> queryChooseTree(Long shopId);

    List<ShopNonRegion> listByShopId(Long shopId);
}
