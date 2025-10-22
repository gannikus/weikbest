package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvConfig;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvConfigDTO;
import com.weikbest.pro.saas.sys.param.module.vo.TencentAdvConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 腾讯广告第三方应用配置表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Mapper
public interface TencentAdvConfigMapStruct extends BaseMapStruct {
    TencentAdvConfigMapStruct INSTANCE = Mappers.getMapper(TencentAdvConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param tencentAdvConfig entity
     * @return dto
     */
    TencentAdvConfigDTO converToDTO(TencentAdvConfig tencentAdvConfig);

    /**
     * DTO转换为entity
     *
     * @param tencentAdvConfigDTO dto
     * @return entity
     */
    TencentAdvConfig converToEntity(TencentAdvConfigDTO tencentAdvConfigDTO);

    /**
     * entity转换为VO
     *
     * @param tencentAdvConfig entity
     * @return vo
     */
    TencentAdvConfigVO converToVO(TencentAdvConfig tencentAdvConfig);

    /**
     * VO转换为entity
     *
     * @param tencentAdvConfigVO vo
     * @return entity
     */
    TencentAdvConfig converToEntity(TencentAdvConfigVO tencentAdvConfigVO);
}