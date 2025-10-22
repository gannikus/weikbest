package com.weikbest.pro.saas.merchat.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.category.entity.ProdAttrVal;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdAttrValDTO;
import com.weikbest.pro.saas.merchat.category.module.qo.ProdAttrValQO;

/**
 * <p>
 * 商品属性值表（本期不用） 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdAttrValService extends IService<ProdAttrVal> {

    /**
     * 新增数据
     *
     * @param prodAttrValDTO prodAttrValDTO
     * @return 新增结果
     */
    boolean insert(ProdAttrValDTO prodAttrValDTO);

    /**
     * 更新数据
     *
     * @param id             ID
     * @param prodAttrValDTO prodAttrValDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdAttrValDTO prodAttrValDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdAttrVal findById(Long id);

    /**
     * 分页查询
     *
     * @param prodAttrValQO
     * @param pageReq
     * @return
     */
    IPage<ProdAttrVal> queryPage(ProdAttrValQO prodAttrValQO, PageReq pageReq);
}
