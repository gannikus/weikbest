package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.Settle;
import com.weikbest.pro.saas.sys.param.module.dto.SettleDTO;
import com.weikbest.pro.saas.sys.param.module.vo.SettleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统结算规则表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
@Mapper
public interface SettleMapStruct extends BaseMapStruct {
    SettleMapStruct INSTANCE = Mappers.getMapper(SettleMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param settle entity
     * @return dto
     */
    SettleDTO converToDTO(Settle settle);

    /**
     * DTO转换为entity
     *
     * @param settleDTO dto
     * @return entity
     */
    Settle converToEntity(SettleDTO settleDTO);

    /**
     * entity转换为VO
     *
     * @param settle entity
     * @return vo
     */
    SettleVO converToVO(Settle settle);

    /**
     * VO转换为entity
     *
     * @param settleVO vo
     * @return entity
     */
    Settle converToEntity(SettleVO settleVO);
}