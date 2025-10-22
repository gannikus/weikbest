package com.weikbest.pro.saas.merchat.category.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfig;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigDTO;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletDecConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序装修配置表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Mapper
public interface AppletDecConfigMapStruct extends BaseMapStruct {
    AppletDecConfigMapStruct INSTANCE = Mappers.getMapper(AppletDecConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param appletDecConfig entity
     * @return dto
     */
    AppletDecConfigDTO converToDTO(AppletDecConfig appletDecConfig);

    /**
     * DTO转换为entity
     *
     * @param appletDecConfigDTO dto
     * @return entity
     */
    AppletDecConfig converToEntity(AppletDecConfigDTO appletDecConfigDTO);

    /**
     * entity转换为VO
     *
     * @param appletDecConfig entity
     * @return vo
     */
    AppletDecConfigVO converToVO(AppletDecConfig appletDecConfig);

    /**
     * VO转换为entity
     *
     * @param appletDecConfigVO vo
     * @return entity
     */
    AppletDecConfig converToEntity(AppletDecConfigVO appletDecConfigVO);
}