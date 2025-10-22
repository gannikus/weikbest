package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.api.module.dto.OrderLogisticsDto;
import com.weikbest.pro.saas.merchat.order.entity.OrderLogistics;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderLogisticsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单物流记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface OrderLogisticsMapStruct extends BaseMapStruct {
    OrderLogisticsMapStruct INSTANCE = Mappers.getMapper(OrderLogisticsMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderLogistics entity
     * @return dto
     */
    OrderLogisticsDTO converToDTO(OrderLogistics orderLogistics);

    /**
     * DTO转换为entity
     *
     * @param orderLogisticsDTO dto
     * @return entity
     */
    OrderLogistics converToEntity(OrderLogisticsDTO orderLogisticsDTO);

    /**
     *  DTO转换为entity
     * @param orderLogisticsDto
     * @return
     */
    OrderLogistics converToEntity(OrderLogisticsDto orderLogisticsDto);

    /**
     * entity转换为VO
     *
     * @param orderLogistics entity
     * @return vo
     */
    OrderLogisticsVO converToVO(OrderLogistics orderLogistics);

    /**
     * VO转换为entity
     *
     * @param orderLogisticsVO vo
     * @return entity
     */
    OrderLogistics converToEntity(OrderLogisticsVO orderLogisticsVO);
}
