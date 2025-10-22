package com.weikbest.pro.saas.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.system.entity.MenuPerm;
import com.weikbest.pro.saas.sys.system.module.dto.MenuPermDTO;
import com.weikbest.pro.saas.sys.system.module.qo.MenuPermQO;

import java.util.List;

/**
 * <p>
 * 系统菜单权限项关联表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface MenuPermService extends IService<MenuPerm> {

    /**
     * 新增数据
     *
     * @param menuPermDTO menuPermDTO
     * @return 新增结果
     */
    boolean insert(MenuPermDTO menuPermDTO);

    /**
     * 更新数据
     *
     * @param id          ID
     * @param menuPermDTO menuPermDTO
     * @return 更新结果
     */
    boolean updateById(Long id, MenuPermDTO menuPermDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    MenuPerm findById(Long id);

    /**
     * 分页查询
     *
     * @param menuPermQO
     * @param pageReq
     * @return
     */
    IPage<MenuPerm> queryPage(MenuPermQO menuPermQO, PageReq pageReq);

    /**
     * 更新关联权限项
     *
     * @param menuId
     * @param permIdList
     * @return
     */
    boolean saveAssociatePermList(Long menuId, List<Long> permIdList);

    /**
     * 根据菜单ID查询菜单关联的权限项
     *
     * @param menuId
     * @return
     */
    List<MenuPerm> queryByMenuId(Long menuId);
}
