package com.weikbest.pro.saas.merchat.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.category.entity.ProdAttr;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdAttrDTO;
import com.weikbest.pro.saas.merchat.category.module.qo.ProdAttrQO;

/**
 * <p>
 * 商品属性表（本期不用） 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdAttrService extends IService<ProdAttr> {

    /**
     * 新增数据
     *
     * @param prodAttrDTO prodAttrDTO
     * @return 新增结果
     */
    boolean insert(ProdAttrDTO prodAttrDTO);

    /**
     * 更新数据
     *
     * @param id          ID
     * @param prodAttrDTO prodAttrDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdAttrDTO prodAttrDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdAttr findById(Long id);

    /**
     * 分页查询
     *
     * @param prodAttrQO
     * @param pageReq
     * @return
     */
    IPage<ProdAttr> queryPage(ProdAttrQO prodAttrQO, PageReq pageReq);
}
