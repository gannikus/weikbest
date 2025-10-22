package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.system.entity.MenuModule;
import com.weikbest.pro.saas.sys.system.module.dto.MenuModuleDTO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuModuleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统模块表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface MenuModuleMapStruct extends BaseMapStruct {
    MenuModuleMapStruct INSTANCE = Mappers.getMapper(MenuModuleMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param menuModule entity
     * @return dto
     */
    MenuModuleDTO converToDTO(MenuModule menuModule);

    /**
     * DTO转换为entity
     *
     * @param menuModuleDTO dto
     * @return entity
     */
    MenuModule converToEntity(MenuModuleDTO menuModuleDTO);

    /**
     * entity转换为VO
     *
     * @param menuModule entity
     * @return vo
     */
    MenuModuleVO converToVO(MenuModule menuModule);

    /**
     * VO转换为entity
     *
     * @param menuModuleVO vo
     * @return entity
     */
    MenuModule converToEntity(MenuModuleVO menuModuleVO);

    /**
     * entity转换为DictEntry
     *
     * @param menuModule entity
     * @return DictEntry
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "key", source = "number")
    @Mapping(target = "value", source = "name")
    @Mapping(target = "icon", source = "iconClass")
    @Mapping(target = "ord", source = "moduleOrd")
    @Mapping(target = "pid", ignore = true)
    DictEntry converToDictEntry(MenuModule menuModule);
}