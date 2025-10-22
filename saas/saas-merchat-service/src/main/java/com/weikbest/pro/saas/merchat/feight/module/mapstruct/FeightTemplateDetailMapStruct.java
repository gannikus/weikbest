package com.weikbest.pro.saas.merchat.feight.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplateDetail;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDetailDTO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 运费模板详情拆分多行表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface FeightTemplateDetailMapStruct extends BaseMapStruct {
    FeightTemplateDetailMapStruct INSTANCE = Mappers.getMapper(FeightTemplateDetailMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param feightTemplateDetail entity
     * @return dto
     */
    FeightTemplateDetailDTO converToDTO(FeightTemplateDetail feightTemplateDetail);

    /**
     * DTO转换为entity
     *
     * @param feightTemplateDetailDTO dto
     * @return entity
     */
    FeightTemplateDetail converToEntity(FeightTemplateDetailDTO feightTemplateDetailDTO);

    /**
     * entity转换为VO
     *
     * @param feightTemplateDetail entity
     * @return vo
     */
    FeightTemplateDetailVO converToVO(FeightTemplateDetail feightTemplateDetail);

    /**
     * VO转换为entity
     *
     * @param feightTemplateDetailVO vo
     * @return entity
     */
    FeightTemplateDetail converToEntity(FeightTemplateDetailVO feightTemplateDetailVO);
}