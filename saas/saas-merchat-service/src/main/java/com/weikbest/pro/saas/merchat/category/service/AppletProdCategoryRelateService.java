package com.weikbest.pro.saas.merchat.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategoryRelate;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryRelateDTO;
import com.weikbest.pro.saas.merchat.category.module.qo.AppletProdCategoryRelateQO;

/**
 * <p>
 * 小程序商品类目关联商品表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
public interface AppletProdCategoryRelateService extends IService<AppletProdCategoryRelate> {

    /**
     * 新增数据
     *
     * @param appletProdCategoryRelateDTO appletProdCategoryRelateDTO
     * @return 新增结果
     */
    boolean insert(AppletProdCategoryRelateDTO appletProdCategoryRelateDTO);

    /**
     * 更新数据
     *
     * @param id                          ID
     * @param appletProdCategoryRelateDTO appletProdCategoryRelateDTO
     * @return 更新结果
     */
    boolean updateById(Long id, AppletProdCategoryRelateDTO appletProdCategoryRelateDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    AppletProdCategoryRelate findById(Long id);

    /**
     * 分页查询
     *
     * @param appletProdCategoryRelateQO
     * @param pageReq
     * @return
     */
    IPage<AppletProdCategoryRelate> queryPage(AppletProdCategoryRelateQO appletProdCategoryRelateQO, PageReq pageReq);
}
