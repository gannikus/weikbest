package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.system.entity.Role;
import com.weikbest.pro.saas.sys.system.module.dto.RoleDTO;
import com.weikbest.pro.saas.sys.system.module.qo.RoleQO;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface RoleService extends IService<Role> {

    /**
     * 新增数据
     *
     * @param roleDTO roleDTO
     * @return 新增结果
     */
    boolean insert(RoleDTO roleDTO);

    /**
     * 新增数据
     *
     * @param id
     * @param roleDTO
     * @return
     */
    boolean insert(Long id, RoleDTO roleDTO);

    /**
     * 更新数据
     *
     * @param id      ID
     * @param roleDTO roleDTO
     * @return 更新结果
     */
    boolean updateById(Long id, RoleDTO roleDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Role findById(Long id);

    /**
     * 分页查询
     *
     * @param roleQO
     * @param pageReq
     * @return
     */
    IPage<Role> queryPage(RoleQO roleQO, PageReq pageReq);

    /**
     * 查询菜单被哪些角色使用
     *
     * @param menuId
     * @return
     */
    List<String> queryFromRuleMenu(Long menuId);

    /**
     * 根据ID列表删除角色
     *
     * @param idList
     */
    void deleteBatchByIds(List<Long> idList);

    /**
     * 角色关联用户
     *
     * @param roleId
     * @param userIdList
     * @return
     */
    boolean associateUserList(Long roleId, List<Long> userIdList);

}
