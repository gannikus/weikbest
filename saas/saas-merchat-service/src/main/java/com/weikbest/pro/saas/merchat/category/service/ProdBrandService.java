package com.weikbest.pro.saas.merchat.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.category.entity.ProdBrand;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdBrandDTO;
import com.weikbest.pro.saas.merchat.category.module.qo.ProdBrandQO;

/**
 * <p>
 * 商品品牌表（本期不用） 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdBrandService extends IService<ProdBrand> {

    /**
     * 新增数据
     *
     * @param prodBrandDTO prodBrandDTO
     * @return 新增结果
     */
    boolean insert(ProdBrandDTO prodBrandDTO);

    /**
     * 更新数据
     *
     * @param id           ID
     * @param prodBrandDTO prodBrandDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdBrandDTO prodBrandDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdBrand findById(Long id);

    /**
     * 分页查询
     *
     * @param prodBrandQO
     * @param pageReq
     * @return
     */
    IPage<ProdBrand> queryPage(ProdBrandQO prodBrandQO, PageReq pageReq);
}
