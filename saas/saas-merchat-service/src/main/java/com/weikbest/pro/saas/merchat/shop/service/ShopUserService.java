package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUser;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserSaveDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopUserQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserDetailVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserListVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserRoleVO;

import java.util.List;

/**
 * <p>
 * 商户店铺用户表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ShopUserService extends IService<ShopUser> {

    /**
     * 新增数据
     *
     * @param shopUserSaveDTO shopUserSaveDTO
     * @return 新增结果
     */
    boolean insert(ShopUserSaveDTO shopUserSaveDTO);

    /**
     * 更新数据
     *
     * @param id              ID
     * @param shopUserSaveDTO shopUserDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopUserSaveDTO shopUserSaveDTO);

    /**
     * 店铺账号分配角色
     *
     * @param shopUserDTO
     * @param roleIdList
     * @return
     */
    boolean associateRoleList(ShopUserDTO shopUserDTO, List<Long> roleIdList);

    /**
     * 查询所有商户角色和商户账户ID关联的角色
     *
     * @param shopUserDTO
     * @return
     */
    List<ShopUserRoleVO> queryShopUserRole(ShopUserDTO shopUserDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ShopUser findById(Long id);

    /**
     * 分页查询
     *
     * @param shopUserQO
     * @param pageReq
     * @return
     */
    IPage<ShopUserListVO> queryPage(ShopUserQO shopUserQO, PageReq pageReq);

    /**
     * 禁用/启用商户店铺账户
     *
     * @param id
     * @param dataStatus
     * @return
     */
    boolean updateDataStatusById(Long id, String dataStatus);

    /**
     * 查询商户店铺账号信息
     *
     * @param id
     * @return
     */
    ShopUserDetailVO findDetailById(Long id);

    /**
     * 根据商户账户ID查询
     *
     * @param businessUserId
     * @return
     */
    List<ShopUser> queryByBusinessUserId(Long businessUserId);

    /**
     * 查询店铺用户ID
     *
     * @param businessId
     * @param shopId
     * @param busiUserId
     * @return
     */
    Long findId(Long businessId, Long shopId, Long busiUserId);
}
