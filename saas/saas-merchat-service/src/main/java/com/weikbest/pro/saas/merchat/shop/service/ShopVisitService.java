package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.shop.entity.ShopVisit;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopVisitDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopVisitQO;

import java.util.List;

/**
 * <p>
 * 店铺访问表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2023-02-04
 */
public interface ShopVisitService extends IService<ShopVisit> {

    /**
     * 新增数据
     *
     * @param shopVisitDTO shopVisitDTO
     * @return 新增结果
     */
    boolean insert(ShopVisitDTO shopVisitDTO);

    /**
     * 更新数据
     *
     * @param id           ID
     * @param shopVisitDTO shopVisitDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopVisitDTO shopVisitDTO);

    /**
     * 删除数据
     *
     * @param ids ID集合
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ShopVisit findById(Long id);

    /**
     * 分页查询
     *
     * @param shopVisitQO
     * @param pageReq
     * @return
     */
    IPage<ShopVisit> queryPage(ShopVisitQO shopVisitQO, PageReq pageReq);
}
