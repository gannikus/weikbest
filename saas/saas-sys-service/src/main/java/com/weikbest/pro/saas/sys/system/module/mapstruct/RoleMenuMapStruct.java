package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.RoleMenu;
import com.weikbest.pro.saas.sys.system.module.dto.RoleMenuDTO;
import com.weikbest.pro.saas.sys.system.module.vo.RoleMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统角色菜单关联表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface RoleMenuMapStruct extends BaseMapStruct {
    RoleMenuMapStruct INSTANCE = Mappers.getMapper(RoleMenuMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param roleMenu entity
     * @return dto
     */
    RoleMenuDTO converToDTO(RoleMenu roleMenu);

    /**
     * DTO转换为entity
     *
     * @param roleMenuDTO dto
     * @return entity
     */
    RoleMenu converToEntity(RoleMenuDTO roleMenuDTO);

    /**
     * entity转换为VO
     *
     * @param roleMenu entity
     * @return vo
     */
    RoleMenuVO converToVO(RoleMenu roleMenu);

    /**
     * VO转换为entity
     *
     * @param roleMenuVO vo
     * @return entity
     */
    RoleMenu converToEntity(RoleMenuVO roleMenuVO);
}