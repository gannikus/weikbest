package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.system.entity.UserRole;
import com.weikbest.pro.saas.sys.system.module.dto.UserRoleDTO;
import com.weikbest.pro.saas.sys.system.module.qo.UserRoleQO;

import java.util.List;

/**
 * <p>
 * 系统用户角色关联表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 新增数据
     *
     * @param userRoleDTO userRoleDTO
     * @return 新增结果
     */
    boolean insert(UserRoleDTO userRoleDTO);

    /**
     * 更新数据
     *
     * @param id          ID
     * @param userRoleDTO userRoleDTO
     * @return 更新结果
     */
    boolean updateById(Long id, UserRoleDTO userRoleDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    UserRole findById(Long id);

    /**
     * 分页查询
     *
     * @param userRoleQO
     * @param pageReq
     * @return
     */
    IPage<UserRole> queryPage(UserRoleQO userRoleQO, PageReq pageReq);

    /**
     * 更新用户角色关联
     *
     * @param roleId
     * @param userIdList
     * @return
     */
    boolean associateUserList(Long roleId, List<Long> userIdList);

    /**
     * 更新用户角色关联
     *
     * @param userId
     * @param roleIdList
     * @return
     */
    boolean associateRoleList(Long userId, List<Long> roleIdList);
}
