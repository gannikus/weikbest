package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderReceiveRecord;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderReceiveRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderReceiveRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单分账记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-06
 */
@Mapper
public interface OrderReceiveRecordMapStruct extends BaseMapStruct {
    OrderReceiveRecordMapStruct INSTANCE = Mappers.getMapper(OrderReceiveRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderReceiveRecord entity
     * @return dto
     */
    OrderReceiveRecordDTO converToDTO(OrderReceiveRecord orderReceiveRecord);

    /**
     * DTO转换为entity
     *
     * @param orderReceiveRecordDTO dto
     * @return entity
     */
    OrderReceiveRecord converToEntity(OrderReceiveRecordDTO orderReceiveRecordDTO);

    /**
     * entity转换为VO
     *
     * @param orderReceiveRecord entity
     * @return vo
     */
    OrderReceiveRecordVO converToVO(OrderReceiveRecord orderReceiveRecord);

    /**
     * VO转换为entity
     *
     * @param orderReceiveRecordVO vo
     * @return entity
     */
    OrderReceiveRecord converToEntity(OrderReceiveRecordVO orderReceiveRecordVO);
}