package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvScopeConfig;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvScopeConfigDTO;
import com.weikbest.pro.saas.sys.param.module.vo.TencentAdvScopeConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 腾讯广告主授权腾讯广告第三方应用表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Mapper
public interface TencentAdvScopeConfigMapStruct extends BaseMapStruct {
    TencentAdvScopeConfigMapStruct INSTANCE = Mappers.getMapper(TencentAdvScopeConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param tencentAdvScopeConfig entity
     * @return dto
     */
    TencentAdvScopeConfigDTO converToDTO(TencentAdvScopeConfig tencentAdvScopeConfig);

    /**
     * DTO转换为entity
     *
     * @param tencentAdvScopeConfigDTO dto
     * @return entity
     */
    TencentAdvScopeConfig converToEntity(TencentAdvScopeConfigDTO tencentAdvScopeConfigDTO);

    /**
     * entity转换为VO
     *
     * @param tencentAdvScopeConfig entity
     * @return vo
     */
    TencentAdvScopeConfigVO converToVO(TencentAdvScopeConfig tencentAdvScopeConfig);

    /**
     * VO转换为entity
     *
     * @param tencentAdvScopeConfigVO vo
     * @return entity
     */
    TencentAdvScopeConfig converToEntity(TencentAdvScopeConfigVO tencentAdvScopeConfigVO);
}