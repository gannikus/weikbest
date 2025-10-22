package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.AppletSmsConfig;
import com.weikbest.pro.saas.sys.param.module.dto.AppletSmsConfigDTO;
import com.weikbest.pro.saas.sys.param.module.vo.AppletSmsConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序绑定短信配置表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-22
 */
@Mapper
public interface AppletSmsConfigMapStruct extends BaseMapStruct {
    AppletSmsConfigMapStruct INSTANCE = Mappers.getMapper(AppletSmsConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param appletSmsConfig entity
     * @return dto
     */
    AppletSmsConfigDTO converToDTO(AppletSmsConfig appletSmsConfig);

    /**
     * DTO转换为entity
     *
     * @param appletSmsConfigDTO dto
     * @return entity
     */
    AppletSmsConfig converToEntity(AppletSmsConfigDTO appletSmsConfigDTO);

    /**
     * entity转换为VO
     *
     * @param appletSmsConfig entity
     * @return vo
     */
    AppletSmsConfigVO converToVO(AppletSmsConfig appletSmsConfig);

    /**
     * VO转换为entity
     *
     * @param appletSmsConfigVO vo
     * @return entity
     */
    AppletSmsConfig converToEntity(AppletSmsConfigVO appletSmsConfigVO);
}