package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.entity.DictType;
import com.weikbest.pro.saas.sys.param.module.dto.DictTypeDTO;
import com.weikbest.pro.saas.sys.param.module.vo.DictTypeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 字典类型表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface DictTypeMapStruct extends BaseMapStruct {
    DictTypeMapStruct INSTANCE = Mappers.getMapper(DictTypeMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param dictType entity
     * @return dto
     */
    DictTypeDTO converToDTO(DictType dictType);

    /**
     * DTO转换为entity
     *
     * @param dictTypeDTO dto
     * @return entity
     */
    DictType converToEntity(DictTypeDTO dictTypeDTO);

    /**
     * entity转换为VO
     *
     * @param dictType entity
     * @return vo
     */
    DictTypeVO converToVO(DictType dictType);

    /**
     * VO转换为entity
     *
     * @param dictTypeVO vo
     * @return entity
     */
    DictType converToEntity(DictTypeVO dictTypeVO);

    /**
     * entity转换为DictEntry
     *
     * @param dictType entity
     * @return DictEntry
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "key", source = "number")
    @Mapping(target = "value", source = "name")
    @Mapping(target = "icon", ignore = true)
    @Mapping(target = "ord", ignore = true)
    @Mapping(target = "pid", ignore = true)
    DictEntry converToDictEntry(DictType dictType);
}