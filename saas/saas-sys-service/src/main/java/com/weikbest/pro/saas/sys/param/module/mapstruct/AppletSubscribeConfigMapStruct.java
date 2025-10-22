package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.AppletSubscribeConfig;
import com.weikbest.pro.saas.sys.param.module.dto.AppletSubscribeConfigDTO;
import com.weikbest.pro.saas.sys.param.module.vo.AppletSubscribeConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序订阅消息配置表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Mapper
public interface AppletSubscribeConfigMapStruct extends BaseMapStruct {
    AppletSubscribeConfigMapStruct INSTANCE = Mappers.getMapper(AppletSubscribeConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param appletSubscribeConfig entity
     * @return dto
     */
    AppletSubscribeConfigDTO converToDTO(AppletSubscribeConfig appletSubscribeConfig);

    /**
     * DTO转换为entity
     *
     * @param appletSubscribeConfigDTO dto
     * @return entity
     */
    AppletSubscribeConfig converToEntity(AppletSubscribeConfigDTO appletSubscribeConfigDTO);

    /**
     * entity转换为VO
     *
     * @param appletSubscribeConfig entity
     * @return vo
     */
    AppletSubscribeConfigVO converToVO(AppletSubscribeConfig appletSubscribeConfig);

    /**
     * VO转换为entity
     *
     * @param appletSubscribeConfigVO vo
     * @return entity
     */
    AppletSubscribeConfig converToEntity(AppletSubscribeConfigVO appletSubscribeConfigVO);
}