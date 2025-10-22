package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.sys.system.entity.Perm;
import com.weikbest.pro.saas.sys.system.entity.RoleMenu;
import com.weikbest.pro.saas.sys.system.module.dto.RoleMenuDTO;
import com.weikbest.pro.saas.sys.system.module.qo.RoleMenuQO;

import java.util.List;

/**
 * <p>
 * 系统角色菜单关联表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 新增数据
     *
     * @param roleMenuDTO roleMenuDTO
     * @return 新增结果
     */
    boolean insert(RoleMenuDTO roleMenuDTO);

    /**
     * 更新数据
     *
     * @param id          ID
     * @param roleMenuDTO roleMenuDTO
     * @return 更新结果
     */
    boolean updateById(Long id, RoleMenuDTO roleMenuDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    RoleMenu findById(Long id);

    /**
     * 分页查询
     *
     * @param roleMenuQO
     * @param pageReq
     * @return
     */
    IPage<RoleMenu> queryPage(RoleMenuQO roleMenuQO, PageReq pageReq);

    /**
     * 刷新关联权限项
     *
     * @param menuId
     * @param permIdList
     */
    void refreshAssociatePermList(Long menuId, List<Long> permIdList);

    /**
     * 查询当前角色下对应的菜单和权限项,配置菜单权限
     *
     * @param roleId 角色ID
     * @return
     */
    List<Dtree> queryAuthRoleMenuPerm(Long roleId);

    /**
     * 授权角色菜单权限项
     *
     * @param purviewList
     * @param roleId
     */
    void authRoleMenuPerm(List<String> purviewList, Long roleId);

    /**
     * 查询当前用户菜单对应的权限
     *
     * @param tokenUser
     * @param menuId
     * @return
     */
    List<Perm> queryPurviewMenuByTokenUser(TokenUser tokenUser, Long menuId);
}
