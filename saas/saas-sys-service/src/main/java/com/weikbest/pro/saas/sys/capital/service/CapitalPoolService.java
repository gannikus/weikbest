package com.weikbest.pro.saas.sys.capital.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.capital.entity.CapitalPool;
import com.weikbest.pro.saas.sys.capital.module.dto.CapitalPoolDTO;
import com.weikbest.pro.saas.sys.capital.module.qo.CapitalPoolQO;

/**
 * <p>
 * 平台资金池表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface CapitalPoolService extends IService<CapitalPool> {

    /**
     * 新增数据
     *
     * @param capitalPoolDTO capitalPoolDTO
     * @return 新增结果
     */
    boolean insert(CapitalPoolDTO capitalPoolDTO);

    /**
     * 更新数据
     *
     * @param id             ID
     * @param capitalPoolDTO capitalPoolDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CapitalPoolDTO capitalPoolDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    CapitalPool findById(Long id);

    /**
     * 分页查询
     *
     * @param capitalPoolQO
     * @param pageReq
     * @return
     */
    IPage<CapitalPool> queryPage(CapitalPoolQO capitalPoolQO, PageReq pageReq);
}
