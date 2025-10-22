package com.weikbest.pro.saas.sys.capital.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.capital.entity.CapitalRecord;
import com.weikbest.pro.saas.sys.capital.module.dto.CapitalRecordDTO;
import com.weikbest.pro.saas.sys.capital.module.vo.CapitalRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 平台资金出入账记录表 实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface CapitalRecordMapStruct extends BaseMapStruct {
    CapitalRecordMapStruct INSTANCE = Mappers.getMapper(CapitalRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param capitalRecord entity
     * @return dto
     */
    CapitalRecordDTO converToDTO(CapitalRecord capitalRecord);

    /**
     * DTO转换为entity
     *
     * @param capitalRecordDTO dto
     * @return entity
     */
    CapitalRecord converToEntity(CapitalRecordDTO capitalRecordDTO);

    /**
     * entity转换为VO
     *
     * @param capitalRecord entity
     * @return vo
     */
    CapitalRecordVO converToVO(CapitalRecord capitalRecord);

    /**
     * VO转换为entity
     *
     * @param capitalRecordVO vo
     * @return entity
     */
    CapitalRecord converToEntity(CapitalRecordVO capitalRecordVO);
}