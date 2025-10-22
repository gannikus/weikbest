package com.weikbest.pro.saas.sysmerchat.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusinessDTO;
import com.weikbest.pro.saas.sysmerchat.business.module.qo.SysBusiQO;
import com.weikbest.pro.saas.sysmerchat.business.module.vo.SysBusiListVO;
import com.weikbest.pro.saas.sysmerchat.business.module.vo.SysBusiVO;

import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
public interface SysBusiService {

    /**
     * 新商户入驻
     *
     * @param businessDTO
     * @return
     */
    boolean insert(BusinessDTO businessDTO);

    /**
     * 更新商户数据
     *
     * @param businessId
     * @param businessDTO
     * @return
     */
    boolean updateByBusinessId(Long businessId, BusinessDTO businessDTO);

    /**
     * 分页查询
     *
     * @param sysBusiQO
     * @param pageReq
     * @return
     */
    IPage<SysBusiListVO> queryPage(SysBusiQO sysBusiQO, PageReq pageReq);

//    /**
//     * 根据商户账户ID删除商户数据
//     *
//     * @param id
//     */
//    void removeById(Long id);
//
//    /**
//     * 根据商户账户ID删除商户数据
//     *
//     * @param ids
//     */
//    void removeBatchByIds(List<Long> ids);

    /**
     * 更新商户数据状态
     *
     * @param busiUserId
     * @param dataStatus
     * @return
     */
    boolean updateDataStatusById(Long busiUserId, String dataStatus);

    /**
     * 根据商户账户ID查询详细数据
     *
     * @param busiUserId
     * @return
     */
    SysBusiVO findVOById(Long busiUserId);

    /**
     * 删除商户数据
     *
     * @param businessIdList
     */
    void deleteBatchByIds(List<Long> businessIdList);
}
