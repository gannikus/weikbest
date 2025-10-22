package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderSourceScale;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderSourceScaleDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderSourceScaleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单来源分账比例表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Mapper
public interface OrderSourceScaleMapStruct extends BaseMapStruct {
    OrderSourceScaleMapStruct INSTANCE = Mappers.getMapper(OrderSourceScaleMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderSourceScale entity
     * @return dto
     */
    OrderSourceScaleDTO converToDTO(OrderSourceScale orderSourceScale);

    /**
     * DTO转换为entity
     *
     * @param orderSourceScaleDTO dto
     * @return entity
     */
    OrderSourceScale converToEntity(OrderSourceScaleDTO orderSourceScaleDTO);

    /**
     * entity转换为VO
     *
     * @param orderSourceScale entity
     * @return vo
     */
    OrderSourceScaleVO converToVO(OrderSourceScale orderSourceScale);

    /**
     * VO转换为entity
     *
     * @param orderSourceScaleVO vo
     * @return entity
     */
    OrderSourceScale converToEntity(OrderSourceScaleVO orderSourceScaleVO);
}