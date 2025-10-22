package com.weikbest.pro.saas.merchat.busi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.busi.entity.CustBusinessBind;
import com.weikbest.pro.saas.merchat.busi.module.dto.CustBusinessBindDTO;
import com.weikbest.pro.saas.merchat.busi.module.qo.CustBusinessBindQO;

import java.util.List;

/**
 * <p>
 * 分账商户绑定表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
public interface CustBusinessBindService extends IService<CustBusinessBind> {

    /**
     * 新增数据
     *
     * @param custBusinessBindDTO custBusinessBindDTO
     * @return 新增结果
     */
    boolean insert(CustBusinessBindDTO custBusinessBindDTO);

    /**
     * 更新数据
     *
     * @param id                  ID
     * @param custBusinessBindDTO custBusinessBindDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CustBusinessBindDTO custBusinessBindDTO);

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
    CustBusinessBind findById(Long id);

    /**
     * 分页查询
     *
     * @param custBusinessBindQO
     * @param pageReq
     * @return
     */
    IPage<CustBusinessBind> queryPage(CustBusinessBindQO custBusinessBindQO, PageReq pageReq);
}
