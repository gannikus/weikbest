package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderRecAddr;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderRecAddrDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderRecAddrVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单收货地址拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface OrderRecAddrMapStruct extends BaseMapStruct {
    OrderRecAddrMapStruct INSTANCE = Mappers.getMapper(OrderRecAddrMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderRecAddr entity
     * @return dto
     */
    OrderRecAddrDTO converToDTO(OrderRecAddr orderRecAddr);

    /**
     * DTO转换为entity
     *
     * @param orderRecAddrDTO dto
     * @return entity
     */
    OrderRecAddr converToEntity(OrderRecAddrDTO orderRecAddrDTO);

    /**
     * entity转换为VO
     *
     * @param orderRecAddr entity
     * @return vo
     */
    OrderRecAddrVO converToVO(OrderRecAddr orderRecAddr);

    /**
     * VO转换为entity
     *
     * @param orderRecAddrVO vo
     * @return entity
     */
    OrderRecAddr converToEntity(OrderRecAddrVO orderRecAddrVO);
}