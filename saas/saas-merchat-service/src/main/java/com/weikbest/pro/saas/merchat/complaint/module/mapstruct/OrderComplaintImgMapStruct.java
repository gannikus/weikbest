package com.weikbest.pro.saas.merchat.complaint.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImg;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintImgDTO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintImgVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单投诉图片拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Mapper
public interface OrderComplaintImgMapStruct extends BaseMapStruct {
    OrderComplaintImgMapStruct INSTANCE = Mappers.getMapper(OrderComplaintImgMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderComplaintImg entity
     * @return dto
     */
    OrderComplaintImgDTO converToDTO(OrderComplaintImg orderComplaintImg);

    /**
     * DTO转换为entity
     *
     * @param orderComplaintImgDTO dto
     * @return entity
     */
    OrderComplaintImg converToEntity(OrderComplaintImgDTO orderComplaintImgDTO);

    /**
     * entity转换为VO
     *
     * @param orderComplaintImg entity
     * @return vo
     */
    OrderComplaintImgVO converToVO(OrderComplaintImg orderComplaintImg);

    /**
     * VO转换为entity
     *
     * @param orderComplaintImgVO vo
     * @return entity
     */
    OrderComplaintImg converToEntity(OrderComplaintImgVO orderComplaintImgVO);
}