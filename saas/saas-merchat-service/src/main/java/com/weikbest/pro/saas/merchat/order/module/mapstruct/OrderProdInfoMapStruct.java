package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderProdInfo;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderProdInfoDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderProdInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单商品参数详细表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-21
 */
@Mapper
public interface OrderProdInfoMapStruct extends BaseMapStruct {
    OrderProdInfoMapStruct INSTANCE = Mappers.getMapper(OrderProdInfoMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderProdInfo entity
     * @return dto
     */
    OrderProdInfoDTO converToDTO(OrderProdInfo orderProdInfo);

    /**
     * DTO转换为entity
     *
     * @param orderProdInfoDTO dto
     * @return entity
     */
    OrderProdInfo converToEntity(OrderProdInfoDTO orderProdInfoDTO);

    /**
     * entity转换为VO
     *
     * @param orderProdInfo entity
     * @return vo
     */
    OrderProdInfoVO converToVO(OrderProdInfo orderProdInfo);

    /**
     * VO转换为entity
     *
     * @param orderProdInfoVO vo
     * @return entity
     */
    OrderProdInfo converToEntity(OrderProdInfoVO orderProdInfoVO);
}