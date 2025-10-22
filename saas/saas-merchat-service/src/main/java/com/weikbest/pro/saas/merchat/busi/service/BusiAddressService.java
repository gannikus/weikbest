package com.weikbest.pro.saas.merchat.busi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.busi.entity.BusiAddress;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiAddressDTO;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusiAddressQO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiAddressVO;

import java.util.List;

/**
 * <p>
 * 商家详细地址信息表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
public interface BusiAddressService extends IService<BusiAddress> {

    /**
     * 新增数据
     *
     * @param busiAddressDTO busiAddressDTO
     * @return 新增结果
     */
    boolean insert(BusiAddressDTO busiAddressDTO);

    /**
     * 更新数据
     *
     * @param id             ID
     * @param busiAddressDTO busiAddressDTO
     * @return 更新结果
     */
    boolean updateById(Long id, BusiAddressDTO busiAddressDTO);

    /**
     * 删除数据
     *
     * @param ids ID集合
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    BusiAddress findById(Long id);

    /**
     * 分页查询
     *
     * @param busiAddressQO
     * @param pageReq
     * @return
     */
    IPage<BusiAddress> queryPage(BusiAddressQO busiAddressQO, PageReq pageReq);

    /**
     * 查询商家配置的默认地址信息
     *
     * @param businessId
     * @return
     */
    BusiAddress getDefaultByBusinessId(Long businessId);

    /**
     * 查询商家配置的地址信息
     *
     * @param businessId
     * @return
     */
    List<BusiAddressVO> queryByBusinessId(Long businessId);
}
