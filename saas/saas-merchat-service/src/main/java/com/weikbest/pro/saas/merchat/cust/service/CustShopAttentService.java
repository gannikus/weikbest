package com.weikbest.pro.saas.merchat.cust.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.cust.entity.CustShopAttent;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustShopAttentDTO;
import com.weikbest.pro.saas.merchat.cust.module.qo.CustShopAttentQO;

/**
 * <p>
 * 客户关注店铺表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-18
 */
public interface CustShopAttentService extends IService<CustShopAttent> {

    /**
     * 新增数据
     *
     * @param custShopAttentDTO custShopAttentDTO
     * @return 新增结果
     */
    boolean insert(CustShopAttentDTO custShopAttentDTO);

    /**
     * 更新数据
     *
     * @param id                ID
     * @param custShopAttentDTO custShopAttentDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CustShopAttentDTO custShopAttentDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    CustShopAttent findById(Long id);

    /**
     * 分页查询
     *
     * @param custShopAttentQO
     * @param pageReq
     * @return
     */
    IPage<CustShopAttent> queryPage(CustShopAttentQO custShopAttentQO, PageReq pageReq);
}
