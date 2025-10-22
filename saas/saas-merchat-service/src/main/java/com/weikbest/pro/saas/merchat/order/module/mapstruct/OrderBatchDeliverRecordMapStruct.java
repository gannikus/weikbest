package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderBatchDeliverRecord;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderBatchDeliverRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderBatchDeliverRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单批量发货记录拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Mapper
public interface OrderBatchDeliverRecordMapStruct extends BaseMapStruct {
    OrderBatchDeliverRecordMapStruct INSTANCE = Mappers.getMapper(OrderBatchDeliverRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderBatchDeliverRecord entity
     * @return dto
     */
    OrderBatchDeliverRecordDTO converToDTO(OrderBatchDeliverRecord orderBatchDeliverRecord);

    /**
     * DTO转换为entity
     *
     * @param orderBatchDeliverRecordDTO dto
     * @return entity
     */
    OrderBatchDeliverRecord converToEntity(OrderBatchDeliverRecordDTO orderBatchDeliverRecordDTO);

    /**
     * entity转换为VO
     *
     * @param orderBatchDeliverRecord entity
     * @return vo
     */
    OrderBatchDeliverRecordVO converToVO(OrderBatchDeliverRecord orderBatchDeliverRecord);

    /**
     * VO转换为entity
     *
     * @param orderBatchDeliverRecordVO vo
     * @return entity
     */
    OrderBatchDeliverRecord converToEntity(OrderBatchDeliverRecordVO orderBatchDeliverRecordVO);
}