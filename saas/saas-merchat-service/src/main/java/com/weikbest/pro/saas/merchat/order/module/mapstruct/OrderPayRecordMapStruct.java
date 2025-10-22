package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderPayRecord;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderPayRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderPayRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单支付记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface OrderPayRecordMapStruct extends BaseMapStruct {
    OrderPayRecordMapStruct INSTANCE = Mappers.getMapper(OrderPayRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderPayRecord entity
     * @return dto
     */
    OrderPayRecordDTO converToDTO(OrderPayRecord orderPayRecord);

    /**
     * DTO转换为entity
     *
     * @param orderPayRecordDTO dto
     * @return entity
     */
    OrderPayRecord converToEntity(OrderPayRecordDTO orderPayRecordDTO);

    /**
     * entity转换为VO
     *
     * @param orderPayRecord entity
     * @return vo
     */
    OrderPayRecordVO converToVO(OrderPayRecord orderPayRecord);

    /**
     * VO转换为entity
     *
     * @param orderPayRecordVO vo
     * @return entity
     */
    OrderPayRecord converToEntity(OrderPayRecordVO orderPayRecordVO);
}