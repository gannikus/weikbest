package com.weikbest.pro.saas.merchat.feight.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplateRegion;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateRegionDTO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateRegionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 运费模板可配送地区详情拆分多行表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface FeightTemplateRegionMapStruct extends BaseMapStruct {
    FeightTemplateRegionMapStruct INSTANCE = Mappers.getMapper(FeightTemplateRegionMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param feightTemplateRegion entity
     * @return dto
     */
    FeightTemplateRegionDTO converToDTO(FeightTemplateRegion feightTemplateRegion);

    /**
     * DTO转换为entity
     *
     * @param feightTemplateRegionDTO dto
     * @return entity
     */
    FeightTemplateRegion converToEntity(FeightTemplateRegionDTO feightTemplateRegionDTO);

    /**
     * entity转换为VO
     *
     * @param feightTemplateRegion entity
     * @return vo
     */
    FeightTemplateRegionVO converToVO(FeightTemplateRegion feightTemplateRegion);

    /**
     * VO转换为entity
     *
     * @param feightTemplateRegionVO vo
     * @return entity
     */
    FeightTemplateRegion converToEntity(FeightTemplateRegionVO feightTemplateRegionVO);
}