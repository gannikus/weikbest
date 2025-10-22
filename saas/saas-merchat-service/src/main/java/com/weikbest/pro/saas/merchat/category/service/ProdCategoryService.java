package com.weikbest.pro.saas.merchat.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.category.entity.ProdCategory;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdCategoryDTO;
import com.weikbest.pro.saas.merchat.category.module.qo.ProdCategoryQO;

/**
 * <p>
 * 商品分类表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdCategoryService extends IService<ProdCategory> {

    /**
     * 新增数据
     *
     * @param prodCategoryDTO prodCategoryDTO
     * @return 新增结果
     */
    boolean insert(ProdCategoryDTO prodCategoryDTO);

    /**
     * 更新数据
     *
     * @param id              ID
     * @param prodCategoryDTO prodCategoryDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdCategoryDTO prodCategoryDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdCategory findById(Long id);

    /**
     * 分页查询
     *
     * @param prodCategoryQO
     * @param pageReq
     * @return
     */
    IPage<ProdCategory> queryPage(ProdCategoryQO prodCategoryQO, PageReq pageReq);
}
