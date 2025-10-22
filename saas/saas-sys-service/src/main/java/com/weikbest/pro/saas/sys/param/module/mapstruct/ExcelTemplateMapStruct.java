package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.ExcelTemplate;
import com.weikbest.pro.saas.sys.param.module.dto.ExcelTemplateDTO;
import com.weikbest.pro.saas.sys.param.module.vo.ExcelTemplateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统excel模板表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Mapper
public interface ExcelTemplateMapStruct extends BaseMapStruct {
    ExcelTemplateMapStruct INSTANCE = Mappers.getMapper(ExcelTemplateMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param excelTemplate entity
     * @return dto
     */
    ExcelTemplateDTO converToDTO(ExcelTemplate excelTemplate);

    /**
     * DTO转换为entity
     *
     * @param excelTemplateDTO dto
     * @return entity
     */
    ExcelTemplate converToEntity(ExcelTemplateDTO excelTemplateDTO);

    /**
     * entity转换为VO
     *
     * @param excelTemplate entity
     * @return vo
     */
    ExcelTemplateVO converToVO(ExcelTemplate excelTemplate);

    /**
     * VO转换为entity
     *
     * @param excelTemplateVO vo
     * @return entity
     */
    ExcelTemplate converToEntity(ExcelTemplateVO excelTemplateVO);
}