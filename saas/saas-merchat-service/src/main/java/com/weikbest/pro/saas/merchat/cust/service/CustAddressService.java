package com.weikbest.pro.saas.merchat.cust.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.cust.entity.CustAddress;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustAddressDTO;
import com.weikbest.pro.saas.merchat.cust.module.qo.CustAddressQO;

/**
 * <p>
 * 客户收货地址表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface CustAddressService extends IService<CustAddress> {

    /**
     * 新增数据
     *
     * @param custAddressDTO custAddressDTO
     * @return 新增结果
     */
    boolean insert(CustAddressDTO custAddressDTO);

    /**
     * 更新数据
     *
     * @param id             ID
     * @param custAddressDTO custAddressDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CustAddressDTO custAddressDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    CustAddress findById(Long id);

    /**
     * 分页查询
     *
     * @param custAddressQO
     * @param pageReq
     * @return
     */
    IPage<CustAddress> queryPage(CustAddressQO custAddressQO, PageReq pageReq);
}
