package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.sys.system.entity.Menu;
import com.weikbest.pro.saas.sys.system.module.dto.MenuDTO;
import com.weikbest.pro.saas.sys.system.module.qo.MenuQO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuPermVO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface MenuService extends IService<Menu> {

    /**
     * 新增数据
     *
     * @param menuDTO menuDTO
     * @return 新增结果
     */
    boolean insert(MenuDTO menuDTO);

    /**
     * 更新数据
     *
     * @param id      ID
     * @param menuDTO menuDTO
     * @return 更新结果
     */
    boolean updateById(Long id, MenuDTO menuDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Menu findById(Long id);

    /**
     * 分页查询
     *
     * @param menuQO
     * @param pageReq
     * @return
     */
    IPage<Menu> queryPage(MenuQO menuQO, PageReq pageReq);

    /**
     * 根据ID列表删除菜单
     *
     * @param idList
     */
    void deleteBatchByIds(List<Long> idList);

    /**
     * 根据父ID查找数据库中全部的子id记录
     *
     * @param parentId 父Id
     * @param subIds   子Id集合
     */
    void queryAllSubIdByParentId(Long parentId, List<Long> subIds);

    /**
     * 更新关联权限项
     *
     * @param id
     * @param permIdList
     * @return
     */
    boolean saveAssociatePermList(Long id, List<Long> permIdList);

    /**
     * 查询菜单被哪些角色使用
     *
     * @param id
     * @return
     */
    List<String> queryFromRuleMenu(Long id);

    /**
     * 查询所有权限项和菜单ID关联的权限项
     *
     * @param id
     * @return
     */
    List<MenuPermVO> queryMenuPerm(Long id);

    /**
     * 根据模块ID查询
     *
     * @param moduleId
     * @return
     */
    List<MenuVO> queryByModuleId(Long moduleId);

    /**
     * 根据模块类型查询
     *
     * @param type
     * @return
     */
    List<MenuVO> queryByType(String type);

    /**
     * 根据模块ID查询当前登录用户有权限的菜单
     *
     * @param tokenUser
     * @param moduleId
     * @return
     */
    List<MenuVO> queryAuthMenuByTokenUser(TokenUser tokenUser, Long moduleId);

    /**
     * 根据角色ID列表查询
     *
     * @param roleIdList
     * @return
     */
    List<MenuVO> queryAuthMenuByRoleIdList(List<Long> roleIdList);

    /**
     * 根据菜单类型查询权限菜单
     *
     * @param type
     * @return
     */
    List<MenuVO> queryAuthMenuByType(String type);
}
