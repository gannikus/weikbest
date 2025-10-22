package com.weikbest.pro.saas.merchat.feight.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplate;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDTO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 运费模板表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface FeightTemplateMapStruct extends BaseMapStruct {
    FeightTemplateMapStruct INSTANCE = Mappers.getMapper(FeightTemplateMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param feightTemplate entity
     * @return dto
     */
    FeightTemplateDTO converToDTO(FeightTemplate feightTemplate);

    /**
     * DTO转换为entity
     *
     * @param feightTemplateDTO dto
     * @return entity
     */
    FeightTemplate converToEntity(FeightTemplateDTO feightTemplateDTO);

    /**
     * entity转换为VO
     *
     * @param feightTemplate entity
     * @return vo
     */
    FeightTemplateVO converToVO(FeightTemplate feightTemplate);

    /**
     * VO转换为entity
     *
     * @param feightTemplateVO vo
     * @return entity
     */
    FeightTemplate converToEntity(FeightTemplateVO feightTemplateVO);
}