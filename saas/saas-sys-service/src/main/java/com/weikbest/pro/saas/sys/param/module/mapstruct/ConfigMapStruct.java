package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.entity.Config;
import com.weikbest.pro.saas.sys.param.module.dto.ConfigDTO;
import com.weikbest.pro.saas.sys.param.module.vo.ConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统配置表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface ConfigMapStruct extends BaseMapStruct {
    ConfigMapStruct INSTANCE = Mappers.getMapper(ConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param config entity
     * @return dto
     */
    ConfigDTO converToDTO(Config config);

    /**
     * DTO转换为entity
     *
     * @param configDTO dto
     * @return entity
     */
    Config converToEntity(ConfigDTO configDTO);

    /**
     * entity转换为VO
     *
     * @param config entity
     * @return vo
     */
    ConfigVO converToVO(Config config);

    /**
     * VO转换为entity
     *
     * @param configVO vo
     * @return entity
     */
    Config converToEntity(ConfigVO configVO);

    /**
     * entity转换为DictEntry
     *
     * @param config entity
     * @return DictEntry
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "key", source = "number")
    @Mapping(target = "value", source = "name")
    @Mapping(target = "icon", ignore = true)
    @Mapping(target = "ord", ignore = true)
    @Mapping(target = "pid", ignore = true)
    DictEntry converToDictEntry(Config config);
}