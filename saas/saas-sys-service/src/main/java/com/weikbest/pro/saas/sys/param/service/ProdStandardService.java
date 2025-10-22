package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.ProdStandard;
import com.weikbest.pro.saas.sys.param.module.dto.ProdStandardDTO;
import com.weikbest.pro.saas.sys.param.module.qo.ProdStandardQO;

import java.util.List;

/**
 * <p>
 * 系统商品规范表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
public interface ProdStandardService extends IService<ProdStandard> {

    /**
     * 新增数据
     *
     * @param prodStandardDTO prodStandardDTO
     * @return 新增结果
     */
    boolean insert(ProdStandardDTO prodStandardDTO);

    /**
     * 更新数据
     *
     * @param id              ID
     * @param prodStandardDTO prodStandardDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdStandardDTO prodStandardDTO);

    /**
     * 添加或更新数据
     *
     * @param prodStandardDTO
     * @return
     */
    boolean saveOrUpdate(ProdStandardDTO prodStandardDTO);

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
    ProdStandard findById(Long id);

    /**
     * 查询单条数据
     *
     * @return
     */
    ProdStandard findProdStandard();

    /**
     * 查找记录,不存在则报错
     *
     * @param notnullFlag
     * @return
     */
    ProdStandard findProdStandard(boolean notnullFlag);

    /**
     * 分页查询
     *
     * @param prodStandardQO
     * @param pageReq
     * @return
     */
    IPage<ProdStandard> queryPage(ProdStandardQO prodStandardQO, PageReq pageReq);
}
