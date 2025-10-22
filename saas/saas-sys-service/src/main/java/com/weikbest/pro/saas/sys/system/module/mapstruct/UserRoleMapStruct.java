package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.UserRole;
import com.weikbest.pro.saas.sys.system.module.dto.UserRoleDTO;
import com.weikbest.pro.saas.sys.system.module.vo.UserRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统用户角色关联表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface UserRoleMapStruct extends BaseMapStruct {
    UserRoleMapStruct INSTANCE = Mappers.getMapper(UserRoleMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param userRole entity
     * @return dto
     */
    UserRoleDTO converToDTO(UserRole userRole);

    /**
     * DTO转换为entity
     *
     * @param userRoleDTO dto
     * @return entity
     */
    UserRole converToEntity(UserRoleDTO userRoleDTO);

    /**
     * entity转换为VO
     *
     * @param userRole entity
     * @return vo
     */
    UserRoleVO converToVO(UserRole userRole);

    /**
     * VO转换为entity
     *
     * @param userRoleVO vo
     * @return entity
     */
    UserRole converToEntity(UserRoleVO userRoleVO);
}