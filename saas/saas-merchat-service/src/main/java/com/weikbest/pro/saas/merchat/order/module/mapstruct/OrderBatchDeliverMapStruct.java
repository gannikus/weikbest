package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderBatchDeliver;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderBatchDeliverDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderBatchDeliverListVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderBatchDeliverVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单批量发货记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-26
 */
@Mapper
public interface OrderBatchDeliverMapStruct extends BaseMapStruct {
    OrderBatchDeliverMapStruct INSTANCE = Mappers.getMapper(OrderBatchDeliverMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderBatchDeliver entity
     * @return dto
     */
    OrderBatchDeliverDTO converToDTO(OrderBatchDeliver orderBatchDeliver);

    /**
     * DTO转换为entity
     *
     * @param orderBatchDeliverDTO dto
     * @return entity
     */
    OrderBatchDeliver converToEntity(OrderBatchDeliverDTO orderBatchDeliverDTO);

    /**
     * entity转换为VO
     *
     * @param orderBatchDeliver entity
     * @return vo
     */
    OrderBatchDeliverVO converToVO(OrderBatchDeliver orderBatchDeliver);

    /**
     * VO转换为entity
     *
     * @param orderBatchDeliverVO vo
     * @return entity
     */
    OrderBatchDeliver converToEntity(OrderBatchDeliverVO orderBatchDeliverVO);

    /**
     * entity转换为VO
     *
     * @param orderBatchDeliver entity
     * @return vo
     */
    OrderBatchDeliverListVO converToListVO(OrderBatchDeliver orderBatchDeliver);
}