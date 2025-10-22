package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.entity.LogisticsCompany;
import com.weikbest.pro.saas.sys.param.module.dto.LogisticsCompanyDTO;
import com.weikbest.pro.saas.sys.param.module.vo.LogisticsCompanyVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 物流快递公司表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-20
 */
@Mapper
public interface LogisticsCompanyMapStruct extends BaseMapStruct {
    LogisticsCompanyMapStruct INSTANCE = Mappers.getMapper(LogisticsCompanyMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param logisticsCompany entity
     * @return dto
     */
    LogisticsCompanyDTO converToDTO(LogisticsCompany logisticsCompany);

    /**
     * DTO转换为entity
     *
     * @param logisticsCompanyDTO dto
     * @return entity
     */
    LogisticsCompany converToEntity(LogisticsCompanyDTO logisticsCompanyDTO);

    /**
     * entity转换为VO
     *
     * @param logisticsCompany entity
     * @return vo
     */
    LogisticsCompanyVO converToVO(LogisticsCompany logisticsCompany);

    /**
     * VO转换为entity
     *
     * @param logisticsCompanyVO vo
     * @return entity
     */
    LogisticsCompany converToEntity(LogisticsCompanyVO logisticsCompanyVO);

    /**
     * entity转换为DictEntry
     *
     * @param logisticsCompany entity
     * @return DictEntry
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "key", source = "number")
    @Mapping(target = "value", source = "name")
    DictEntry converToDictEntry(LogisticsCompany logisticsCompany);
}