package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.busi.entity.BusiAddress;
import com.weikbest.pro.saas.merchat.prod.entity.ProdBusiAddr;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdBusiAddrDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdBusiAddrQO;

/**
 * <p>
 * 商品与商家详细地址管理表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdBusiAddrService extends IService<ProdBusiAddr> {

    /**
     * 新增数据
     *
     * @param prodBusiAddrDTO prodBusiAddrDTO
     * @return 新增结果
     */
    boolean insert(ProdBusiAddrDTO prodBusiAddrDTO);

    /**
     * 更新数据
     *
     * @param id              ID
     * @param prodBusiAddrDTO prodBusiAddrDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdBusiAddrDTO prodBusiAddrDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdBusiAddr findById(Long id);

    /**
     * 分页查询
     *
     * @param prodBusiAddrQO
     * @param pageReq
     * @return
     */
    IPage<ProdBusiAddr> queryPage(ProdBusiAddrQO prodBusiAddrQO, PageReq pageReq);

    /**
     * 根据商品ID查询一条商家地址信息
     *
     * @param prodId
     * @param businessId
     * @return
     */
    BusiAddress findOneBusiAddressByProdIdOrBusinessId(Long prodId, Long businessId);

}
