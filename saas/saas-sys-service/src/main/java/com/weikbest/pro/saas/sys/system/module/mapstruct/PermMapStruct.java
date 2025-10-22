package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.Perm;
import com.weikbest.pro.saas.sys.system.module.dto.PermDTO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuPermVO;
import com.weikbest.pro.saas.sys.system.module.vo.PermVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统权限项表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface PermMapStruct extends BaseMapStruct {
    PermMapStruct INSTANCE = Mappers.getMapper(PermMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param perm entity
     * @return dto
     */
    PermDTO converToDTO(Perm perm);

    /**
     * DTO转换为entity
     *
     * @param permDTO dto
     * @return entity
     */
    Perm converToEntity(PermDTO permDTO);

    /**
     * entity转换为VO
     *
     * @param perm entity
     * @return vo
     */
    PermVO converToVO(Perm perm);

    /**
     * entity转换为VO
     *
     * @param perm entity
     * @return vo
     */
    @Mapping(target = "menuId", source = "menuId")
    @Mapping(target = "permId", source = "perm.id")
    @Mapping(target = "permName", source = "perm.name")
    @Mapping(target = "permNumber", source = "perm.number")
    @Mapping(target = "checked", ignore = true)
    MenuPermVO converToMenuPermVO(Perm perm, Long menuId);

    /**
     * VO转换为entity
     *
     * @param permVO vo
     * @return entity
     */
    Perm converToEntity(PermVO permVO);
}