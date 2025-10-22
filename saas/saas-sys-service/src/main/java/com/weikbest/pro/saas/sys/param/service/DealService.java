package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.Deal;
import com.weikbest.pro.saas.sys.param.module.dto.DealDTO;
import com.weikbest.pro.saas.sys.param.module.qo.DealQO;

/**
 * <p>
 * 系统交易规则表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface DealService extends IService<Deal> {

    /**
     * 新增数据
     *
     * @param dealDTO dealDTO
     * @return 新增结果
     */
    boolean insert(DealDTO dealDTO);

    /**
     * 更新数据
     *
     * @param id      ID
     * @param dealDTO dealDTO
     * @return 更新结果
     */
    boolean updateById(Long id, DealDTO dealDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Deal findById(Long id);

    /**
     * 分页查询
     *
     * @param dealQO
     * @param pageReq
     * @return
     */
    IPage<Deal> queryPage(DealQO dealQO, PageReq pageReq);

    /**
     * 查询单条数据
     *
     * @return
     */
    Deal findDeal();

    /**
     * 查找不存在则报错
     *
     * @param notnullFlag
     * @return
     */
    Deal findDeal(boolean notnullFlag);

    /**
     * 新增或更新数据
     *
     * @param dealDTO
     * @return
     */
    boolean saveOrUpdate(DealDTO dealDTO);
}
