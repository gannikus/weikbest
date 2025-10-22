package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.Settle;
import com.weikbest.pro.saas.sys.param.module.dto.SettleDTO;
import com.weikbest.pro.saas.sys.param.module.qo.SettleQO;

import java.util.List;

/**
 * <p>
 * 系统结算规则表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
public interface SettleService extends IService<Settle> {

    /**
     * 新增数据
     *
     * @param settleDTO settleDTO
     * @return 新增结果
     */
    boolean insert(SettleDTO settleDTO);

    /**
     * 更新数据
     *
     * @param id        ID
     * @param settleDTO settleDTO
     * @return 更新结果
     */
    boolean updateById(Long id, SettleDTO settleDTO);

    /**
     * 添加或更新数据
     *
     * @param settleDTO
     * @return
     */
    boolean saveOrUpdate(SettleDTO settleDTO);

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
    Settle findById(Long id);

    /**
     * 查询单条数据
     *
     * @return
     */
    Settle findSettle();

    /**
     * 查找记录,不存在则报错
     *
     * @param notnullFlag
     * @return
     */
    Settle findSettle(boolean notnullFlag);

    /**
     * 分页查询
     *
     * @param settleQO
     * @param pageReq
     * @return
     */
    IPage<Settle> queryPage(SettleQO settleQO, PageReq pageReq);
}
