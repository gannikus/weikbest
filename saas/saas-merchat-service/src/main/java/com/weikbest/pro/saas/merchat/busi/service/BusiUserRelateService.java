package com.weikbest.pro.saas.merchat.busi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUserRelate;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserRelateDTO;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusiUserRelateQO;

import java.util.List;

/**
 * <p>
 * 商户与商户账户关联表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2023-02-23
 */
public interface BusiUserRelateService extends IService<BusiUserRelate> {

    /**
     * 新增数据
     *
     * @param busiUserRelateDTO busiUserRelateDTO
     * @return 新增结果
     */
    boolean insert(BusiUserRelateDTO busiUserRelateDTO);

    /**
     * 更新数据
     *
     * @param id                ID
     * @param busiUserRelateDTO busiUserRelateDTO
     * @return 更新结果
     */
    boolean updateById(Long id, BusiUserRelateDTO busiUserRelateDTO);

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
    BusiUserRelate findById(Long id);

    /**
     * 分页查询
     *
     * @param busiUserRelateQO
     * @param pageReq
     * @return
     */
    IPage<BusiUserRelate> queryPage(BusiUserRelateQO busiUserRelateQO, PageReq pageReq);

    /**
     * 查询主账号信息
     *
     * @param businessId
     * @return
     */
    BusiUserRelate findMainByBusinessId(Long businessId);

    /**
     * 查询主账号信息
     *
     * @param busiUserId
     * @return
     */
    BusiUserRelate getMainByBusinessUserId(Long busiUserId);

    /**
     * 根据商户ID和商户账户ID查询
     *
     * @param businessId
     * @param busiUserId
     * @return
     */
    BusiUserRelate getByBusinessIdAndBusinessUserId(Long businessId, Long busiUserId);

    /**
     * 根据商户账户ID查询
     *
     * @param busiUserId
     * @return
     */
    List<BusiUserRelate> queryByBusinessUserId(Long busiUserId);

}
