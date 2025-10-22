package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUserRole;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserRoleDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopUserRoleQO;
import com.weikbest.pro.saas.sys.system.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 店铺用户角色关联表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
public interface ShopUserRoleService extends IService<ShopUserRole> {

    /**
     * 新增数据
     *
     * @param shopUserRoleDTO shopUserRoleDTO
     * @return 新增结果
     */
    boolean insert(ShopUserRoleDTO shopUserRoleDTO);

    /**
     * 更新数据
     *
     * @param id              ID
     * @param shopUserRoleDTO shopUserRoleDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopUserRoleDTO shopUserRoleDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ShopUserRole findById(Long id);

    /**
     * 分页查询
     *
     * @param shopUserRoleQO
     * @param pageReq
     * @return
     */
    IPage<ShopUserRole> queryPage(ShopUserRoleQO shopUserRoleQO, PageReq pageReq);

    /**
     * 店铺用户关联角色
     *
     * @param shopUserId     店铺用户ID
     * @param businessUserId 商户账户ID
     * @param roleIdList     关键角色ID集合
     * @return
     */
    boolean associateRoleList(Long shopUserId, Long businessUserId, List<Long> roleIdList);

    /**
     * 查询商户角色
     *
     * @param businessId
     * @param shopId
     * @return
     */
    List<Role> queryRule(Long businessId, Long shopId);

    /**
     * 分组查询商户角色名称
     *
     * @param shopUserIdList
     * @return
     */
    Map<Long, List<String>> queryRoleNameGroupByShopUseIdList(List<Long> shopUserIdList);

    /**
     * 查询店铺商户拥有的角色
     *
     * @param busiUserId
     * @param shopUserId
     * @return
     */
    List<Long> queryAuthRule(Long busiUserId, Long shopUserId);
}
