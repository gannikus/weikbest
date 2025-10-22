package com.weikbest.pro.saas.sys.capital.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.capital.entity.CapitalPool;
import com.weikbest.pro.saas.sys.capital.module.dto.CapitalPoolDTO;
import com.weikbest.pro.saas.sys.capital.module.vo.CapitalPoolVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 平台资金池表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface CapitalPoolMapStruct extends BaseMapStruct {
    CapitalPoolMapStruct INSTANCE = Mappers.getMapper(CapitalPoolMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param capitalPool entity
     * @return dto
     */
    CapitalPoolDTO converToDTO(CapitalPool capitalPool);

    /**
     * DTO转换为entity
     *
     * @param capitalPoolDTO dto
     * @return entity
     */
    CapitalPool converToEntity(CapitalPoolDTO capitalPoolDTO);

    /**
     * entity转换为VO
     *
     * @param capitalPool entity
     * @return vo
     */
    CapitalPoolVO converToVO(CapitalPool capitalPool);

    /**
     * VO转换为entity
     *
     * @param capitalPoolVO vo
     * @return entity
     */
    CapitalPool converToEntity(CapitalPoolVO capitalPoolVO);
}