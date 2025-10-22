package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.shop.entity.ShopRole;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopRoleDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopRoleVO;
import com.weikbest.pro.saas.sys.system.entity.Role;
import com.weikbest.pro.saas.sys.system.module.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 店铺角色表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
@Mapper
public interface ShopRoleMapStruct extends BaseMapStruct {
    ShopRoleMapStruct INSTANCE = Mappers.getMapper(ShopRoleMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shopRole entity
     * @return dto
     */
    ShopRoleDTO converToDTO(ShopRole shopRole);

    /**
     * DTO转换为entity
     *
     * @param shopRoleDTO dto
     * @return entity
     */
    ShopRole converToEntity(ShopRoleDTO shopRoleDTO);

    /**
     * entity转换为VO
     *
     * @param shopRole entity
     * @return vo
     */
    ShopRoleVO converToVO(ShopRole shopRole);

    /**
     * VO转换为entity
     *
     * @param shopRoleVO vo
     * @return entity
     */
    ShopRole converToEntity(ShopRoleVO shopRoleVO);

    /**
     * DTO转换为RoleDTO
     *
     * @param shopRoleDTO
     * @return RoleDTO
     */
    RoleDTO converToRoleDTO(ShopRoleDTO shopRoleDTO);

    /**
     * entity转换为VO
     *
     * @param shopRole
     * @param role
     * @return
     */
    @Mapping(target = "id", source = "shopRole.id")
    ShopRoleVO converToVO(ShopRole shopRole, Role role);
}