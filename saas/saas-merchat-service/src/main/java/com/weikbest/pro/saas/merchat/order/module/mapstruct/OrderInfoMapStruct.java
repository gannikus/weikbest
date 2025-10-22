package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderInfoDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderInfoQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoCustCouponRestrictVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoDetailVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface OrderInfoMapStruct extends BaseMapStruct {
    OrderInfoMapStruct INSTANCE = Mappers.getMapper(OrderInfoMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderInfo entity
     * @return dto
     */
    OrderInfoDTO converToDTO(OrderInfo orderInfo);

    /**
     * DTO转换为entity
     *
     * @param orderInfoDTO dto
     * @return entity
     */
    OrderInfo converToEntity(OrderInfoDTO orderInfoDTO);

    /**
     * entity转换为VO
     *
     * @param orderInfo entity
     * @return vo
     */
    OrderInfoVO converToVO(OrderInfo orderInfo);

    /**
     * VO转换为entity
     *
     * @param orderInfoVO vo
     * @return entity
     */
    OrderInfo converToEntity(OrderInfoVO orderInfoVO);

    /**
     * entity转换为OrderInfoDetailVO
     *
     * @param orderInfo entity
     * @return vo
     */
    OrderInfoDetailVO converToDetailVO(OrderInfo orderInfo);

    /**
     * entity转换为vo
     *
     * @param orderInfo entity
     * @return vo
     */
    OrderInfoCustCouponRestrictVO converToCustCouponRestrictVO(OrderInfo orderInfo);

}