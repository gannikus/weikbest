package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUserRole;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserRoleDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserRoleVO;
import com.weikbest.pro.saas.sys.system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 店铺用户角色关联表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
@Mapper
public interface ShopUserRoleMapStruct extends BaseMapStruct {
    ShopUserRoleMapStruct INSTANCE = Mappers.getMapper(ShopUserRoleMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shopUserRole entity
     * @return dto
     */
    ShopUserRoleDTO converToDTO(ShopUserRole shopUserRole);

    /**
     * DTO转换为entity
     *
     * @param shopUserRoleDTO dto
     * @return entity
     */
    ShopUserRole converToEntity(ShopUserRoleDTO shopUserRoleDTO);

    /**
     * entity转换为VO
     *
     * @param shopUserRole entity
     * @return vo
     */
    ShopUserRoleVO converToVO(ShopUserRole shopUserRole);

    /**
     * VO转换为entity
     *
     * @param shopUserRoleVO vo
     * @return entity
     */
    ShopUserRole converToEntity(ShopUserRoleVO shopUserRoleVO);

    /**
     * role转换为VO
     *
     * @param role           role
     * @param shopUserId     商户店铺用户ID
     * @param businessUserId 商户账户ID
     * @return VO
     */
    @Mapping(target = "shopUserId", source = "shopUserId")
    @Mapping(target = "businessUserId", source = "businessUserId")
    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "roleName", source = "role.name")
    @Mapping(target = "checked", ignore = true)
    ShopUserRoleVO converRoleToShopUserRoleVO(Role role, Long shopUserId, Long businessUserId);
}