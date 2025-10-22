package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderCustInfo;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderCustInfoDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderCustInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户订单与客户关联拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface OrderCustInfoMapStruct extends BaseMapStruct {
    OrderCustInfoMapStruct INSTANCE = Mappers.getMapper(OrderCustInfoMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderCustInfo entity
     * @return dto
     */
    OrderCustInfoDTO converToDTO(OrderCustInfo orderCustInfo);

    /**
     * DTO转换为entity
     *
     * @param orderCustInfoDTO dto
     * @return entity
     */
    OrderCustInfo converToEntity(OrderCustInfoDTO orderCustInfoDTO);

    /**
     * entity转换为VO
     *
     * @param orderCustInfo entity
     * @return vo
     */
    OrderCustInfoVO converToVO(OrderCustInfo orderCustInfo);

    /**
     * VO转换为entity
     *
     * @param orderCustInfoVO vo
     * @return entity
     */
    OrderCustInfo converToEntity(OrderCustInfoVO orderCustInfoVO);
}