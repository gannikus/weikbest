package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.shop.entity.ShopRole;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopRoleDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopRoleQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopRoleVO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 店铺角色表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
public interface ShopRoleService extends IService<ShopRole> {

    /**
     * 新增数据
     *
     * @param shopRoleDTO shopRoleDTO
     * @return 新增结果
     */
    boolean insert(ShopRoleDTO shopRoleDTO);

    /**
     * 更新数据
     *
     * @param id          ID
     * @param shopRoleDTO shopRoleDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopRoleDTO shopRoleDTO);

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
    ShopRole findById(Long id);

    /**
     * 分页查询
     *
     * @param shopRoleQO
     * @param pageReq
     * @return
     */
    IPage<ShopRoleVO> queryPage(ShopRoleQO shopRoleQO, PageReq pageReq);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    ShopRoleVO findVOById(Long id);

    /**
     * 查询所有角色
     *
     * @param businessId
     * @param shopId
     * @return
     */
    List<ShopRoleVO> queryAllRole(Long businessId, Long shopId);

    /**
     * 查询商户店铺权限
     *
     * @param businessId
     * @param shopId
     * @param busiUserId
     * @return
     */
    List<MenuVO> queryAuth(Long businessId, Long shopId, Long busiUserId);
}
