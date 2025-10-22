package com.weikbest.pro.saas.merchat.complaint.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaint;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintDTO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintDetailVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单投诉表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Mapper
public interface OrderComplaintMapStruct extends BaseMapStruct {
    OrderComplaintMapStruct INSTANCE = Mappers.getMapper(OrderComplaintMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderComplaint entity
     * @return dto
     */
    OrderComplaintDTO converToDTO(OrderComplaint orderComplaint);

    /**
     * DTO转换为entity
     *
     * @param orderComplaintDTO dto
     * @return entity
     */
    OrderComplaint converToEntity(OrderComplaintDTO orderComplaintDTO);

    /**
     * entity转换为VO
     *
     * @param orderComplaint entity
     * @return vo
     */
    OrderComplaintVO converToVO(OrderComplaint orderComplaint);

    /**
     * VO转换为entity
     *
     * @param orderComplaintVO vo
     * @return entity
     */
    OrderComplaint converToEntity(OrderComplaintVO orderComplaintVO);

    /**
     * entity转换为VO
     *
     * @param orderComplaint entity
     * @return vo
     */
    OrderComplaintDetailVO converToDetailVO(OrderComplaint orderComplaint);
}