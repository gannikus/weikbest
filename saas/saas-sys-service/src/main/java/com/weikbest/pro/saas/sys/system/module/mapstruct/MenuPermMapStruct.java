package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.MenuPerm;
import com.weikbest.pro.saas.sys.system.module.dto.MenuPermDTO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuPermVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统菜单权限项关联表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface MenuPermMapStruct extends BaseMapStruct {
    MenuPermMapStruct INSTANCE = Mappers.getMapper(MenuPermMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param menuPerm entity
     * @return dto
     */
    MenuPermDTO converToDTO(MenuPerm menuPerm);

    /**
     * DTO转换为entity
     *
     * @param menuPermDTO dto
     * @return entity
     */
    MenuPerm converToEntity(MenuPermDTO menuPermDTO);

    /**
     * entity转换为VO
     *
     * @param menuPerm entity
     * @return vo
     */
    MenuPermVO converToVO(MenuPerm menuPerm);

    /**
     * VO转换为entity
     *
     * @param menuPermVO vo
     * @return entity
     */
    MenuPerm converToEntity(MenuPermVO menuPermVO);
}