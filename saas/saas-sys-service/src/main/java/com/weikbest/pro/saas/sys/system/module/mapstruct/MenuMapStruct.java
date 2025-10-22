package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.Menu;
import com.weikbest.pro.saas.sys.system.module.dto.MenuDTO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统菜单表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface MenuMapStruct extends BaseMapStruct {
    MenuMapStruct INSTANCE = Mappers.getMapper(MenuMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param menu entity
     * @return dto
     */
    MenuDTO converToDTO(Menu menu);

    /**
     * DTO转换为entity
     *
     * @param menuDTO dto
     * @return entity
     */
    Menu converToEntity(MenuDTO menuDTO);

    /**
     * entity转换为VO
     *
     * @param menu entity
     * @return vo
     */
    MenuVO converToVO(Menu menu);

    /**
     * VO转换为entity
     *
     * @param menuVO vo
     * @return entity
     */
    Menu converToEntity(MenuVO menuVO);
}