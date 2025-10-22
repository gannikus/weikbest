package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.system.entity.Org;
import com.weikbest.pro.saas.sys.system.module.dto.OrgDTO;
import com.weikbest.pro.saas.sys.system.module.qo.OrgQO;

/**
 * <p>
 * 系统部门表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-22
 */
public interface OrgService extends IService<Org> {

    /**
     * 新增数据
     *
     * @param orgDTO orgDTO
     * @return 新增结果
     */
    boolean insert(OrgDTO orgDTO);

    /**
     * 更新数据
     *
     * @param id     ID
     * @param orgDTO orgDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrgDTO orgDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Org findById(Long id);

    /**
     * 分页查询
     *
     * @param orgQO
     * @param pageReq
     * @return
     */
    IPage<Org> queryPage(OrgQO orgQO, PageReq pageReq);
}
