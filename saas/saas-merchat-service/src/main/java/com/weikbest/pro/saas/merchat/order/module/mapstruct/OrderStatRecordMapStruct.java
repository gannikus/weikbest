package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderStatRecord;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderStatRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderStatRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单状态变更记录表 实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface OrderStatRecordMapStruct extends BaseMapStruct {
    OrderStatRecordMapStruct INSTANCE = Mappers.getMapper(OrderStatRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderStatRecord entity
     * @return dto
     */
    OrderStatRecordDTO converToDTO(OrderStatRecord orderStatRecord);

    /**
     * DTO转换为entity
     *
     * @param orderStatRecordDTO dto
     * @return entity
     */
    OrderStatRecord converToEntity(OrderStatRecordDTO orderStatRecordDTO);

    /**
     * entity转换为VO
     *
     * @param orderStatRecord entity
     * @return vo
     */
    OrderStatRecordVO converToVO(OrderStatRecord orderStatRecord);

    /**
     * VO转换为entity
     *
     * @param orderStatRecordVO vo
     * @return entity
     */
    OrderStatRecord converToEntity(OrderStatRecordVO orderStatRecordVO);
}