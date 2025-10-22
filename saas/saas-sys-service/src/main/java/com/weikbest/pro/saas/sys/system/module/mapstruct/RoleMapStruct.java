package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.Role;
import com.weikbest.pro.saas.sys.system.module.dto.RoleDTO;
import com.weikbest.pro.saas.sys.system.module.vo.RoleVO;
import com.weikbest.pro.saas.sys.system.module.vo.UserRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统角色表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface RoleMapStruct extends BaseMapStruct {
    RoleMapStruct INSTANCE = Mappers.getMapper(RoleMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param role entity
     * @return dto
     */
    RoleDTO converToDTO(Role role);

    /**
     * DTO转换为entity
     *
     * @param roleDTO dto
     * @return entity
     */
    Role converToEntity(RoleDTO roleDTO);

    /**
     * entity转换为VO
     *
     * @param role entity
     * @return vo
     */
    RoleVO converToVO(Role role);

    /**
     * VO转换为entity
     *
     * @param roleVO vo
     * @return entity
     */
    Role converToEntity(RoleVO roleVO);

    /**
     * entity转换为UserRoleVO
     *
     * @param role entity
     * @return UserRoleVO
     */
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "roleName", source = "role.name")
    @Mapping(target = "checked", ignore = true)
    UserRoleVO converToUserRoleVO(Role role, Long userId);
}