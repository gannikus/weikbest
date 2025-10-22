package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdSku;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdSkuDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdSkuQO;

/**
 * <p>
 * 商品销售属性表（本期不用） 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdSkuService extends IService<ProdSku> {

    /**
     * 新增数据
     *
     * @param prodSkuDTO prodSkuDTO
     * @return 新增结果
     */
    boolean insert(ProdSkuDTO prodSkuDTO);

    /**
     * 更新数据
     *
     * @param id         ID
     * @param prodSkuDTO prodSkuDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdSkuDTO prodSkuDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdSku findById(Long id);

    /**
     * 分页查询
     *
     * @param prodSkuQO
     * @param pageReq
     * @return
     */
    IPage<ProdSku> queryPage(ProdSkuQO prodSkuQO, PageReq pageReq);
}
