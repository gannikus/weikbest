package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.entity.Dict;
import com.weikbest.pro.saas.sys.param.module.dto.DictDTO;
import com.weikbest.pro.saas.sys.param.module.vo.DictVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 字典表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Mapper
public interface DictMapStruct extends BaseMapStruct {
    DictMapStruct INSTANCE = Mappers.getMapper(DictMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param dict entity
     * @return dto
     */
    DictDTO converToDTO(Dict dict);

    /**
     * DTO转换为entity
     *
     * @param dictDTO dto
     * @return entity
     */
    Dict converToEntity(DictDTO dictDTO);

    /**
     * entity转换为VO
     *
     * @param dict entity
     * @return vo
     */
    DictVO converToVO(Dict dict);

    /**
     * VO转换为entity
     *
     * @param dictVO vo
     * @return entity
     */
    Dict converToEntity(DictVO dictVO);

    /**
     * entity转换为DictEntry
     *
     * @param dict entity
     * @return DictEntry
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "key", source = "number")
    @Mapping(target = "value", source = "name")
    @Mapping(target = "icon", ignore = true)
    @Mapping(target = "ord", source = "dictOrd")
    @Mapping(target = "pid", source = "dictTypeId")
    DictEntry converToDictEntry(Dict dict);
}