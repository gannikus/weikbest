package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdStock;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdStockDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdStockQO;

/**
 * <p>
 * 商品库存表（本期不用） 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdStockService extends IService<ProdStock> {

    /**
     * 新增数据
     *
     * @param prodStockDTO prodStockDTO
     * @return 新增结果
     */
    boolean insert(ProdStockDTO prodStockDTO);

    /**
     * 更新数据
     *
     * @param id           ID
     * @param prodStockDTO prodStockDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdStockDTO prodStockDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdStock findById(Long id);

    /**
     * 分页查询
     *
     * @param prodStockQO
     * @param pageReq
     * @return
     */
    IPage<ProdStock> queryPage(ProdStockQO prodStockQO, PageReq pageReq);
}
