package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAttrRelate;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdAttrRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdAttrRelateQO;

/**
 * <p>
 * 商品销售规格属性关联表（本期不用） 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdAttrRelateService extends IService<ProdAttrRelate> {

    /**
     * 新增数据
     *
     * @param prodAttrRelateDTO prodAttrRelateDTO
     * @return 新增结果
     */
    boolean insert(ProdAttrRelateDTO prodAttrRelateDTO);

    /**
     * 更新数据
     *
     * @param id                ID
     * @param prodAttrRelateDTO prodAttrRelateDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdAttrRelateDTO prodAttrRelateDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdAttrRelate findById(Long id);

    /**
     * 分页查询
     *
     * @param prodAttrRelateQO
     * @param pageReq
     * @return
     */
    IPage<ProdAttrRelate> queryPage(ProdAttrRelateQO prodAttrRelateQO, PageReq pageReq);
}
