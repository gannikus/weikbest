package com.weikbest.pro.saas.merchat.category.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfigEntry;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigEntryDTO;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletDecConfigEntryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序装修配置分录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Mapper
public interface AppletDecConfigEntryMapStruct extends BaseMapStruct {
    AppletDecConfigEntryMapStruct INSTANCE = Mappers.getMapper(AppletDecConfigEntryMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param appletDecConfigEntry entity
     * @return dto
     */
    AppletDecConfigEntryDTO converToDTO(AppletDecConfigEntry appletDecConfigEntry);

    /**
     * DTO转换为entity
     *
     * @param appletDecConfigEntryDTO dto
     * @return entity
     */
    AppletDecConfigEntry converToEntity(AppletDecConfigEntryDTO appletDecConfigEntryDTO);

    /**
     * entity转换为VO
     *
     * @param appletDecConfigEntry entity
     * @return vo
     */
    AppletDecConfigEntryVO converToVO(AppletDecConfigEntry appletDecConfigEntry);

    /**
     * VO转换为entity
     *
     * @param appletDecConfigEntryVO vo
     * @return entity
     */
    AppletDecConfigEntry converToEntity(AppletDecConfigEntryVO appletDecConfigEntryVO);
}