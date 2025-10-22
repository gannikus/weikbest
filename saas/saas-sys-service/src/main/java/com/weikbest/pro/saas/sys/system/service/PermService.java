package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.system.entity.Perm;
import com.weikbest.pro.saas.sys.system.module.dto.PermDTO;
import com.weikbest.pro.saas.sys.system.module.qo.PermQO;

import java.util.List;

/**
 * <p>
 * 系统权限项表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface PermService extends IService<Perm> {

    /**
     * 新增数据
     *
     * @param permDTO permDTO
     * @return 新增结果
     */
    boolean insert(PermDTO permDTO);

    /**
     * 更新数据
     *
     * @param id      ID
     * @param permDTO permDTO
     * @return 更新结果
     */
    boolean updateById(Long id, PermDTO permDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Perm findById(Long id);

    /**
     * 分页查询
     *
     * @param permQO
     * @param pageReq
     * @return
     */
    IPage<Perm> queryPage(PermQO permQO, PageReq pageReq);

    /**
     * 根据ID列表删除权限项
     *
     * @param idList
     */
    void deleteBatchByIds(List<Long> idList);
}
