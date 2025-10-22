package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.Deal;
import com.weikbest.pro.saas.sys.param.module.dto.DealDTO;
import com.weikbest.pro.saas.sys.param.module.vo.DealVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统交易规则表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
@Mapper
public interface DealMapStruct extends BaseMapStruct {
    DealMapStruct INSTANCE = Mappers.getMapper(DealMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param deal entity
     * @return dto
     */
    DealDTO converToDTO(Deal deal);

    /**
     * DTO转换为entity
     *
     * @param dealDTO dto
     * @return entity
     */
    Deal converToEntity(DealDTO dealDTO);

    /**
     * entity转换为VO
     *
     * @param deal entity
     * @return vo
     */
    DealVO converToVO(Deal deal);

    /**
     * VO转换为entity
     *
     * @param dealVO vo
     * @return entity
     */
    Deal converToEntity(DealVO dealVO);
}