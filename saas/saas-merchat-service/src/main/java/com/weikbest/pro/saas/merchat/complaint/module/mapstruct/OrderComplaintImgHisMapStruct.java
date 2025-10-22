package com.weikbest.pro.saas.merchat.complaint.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImg;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImgHis;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintImgHisDTO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintImgHisVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单投诉图片拆分历史表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-05
 */
@Mapper
public interface OrderComplaintImgHisMapStruct extends BaseMapStruct {
    OrderComplaintImgHisMapStruct INSTANCE = Mappers.getMapper(OrderComplaintImgHisMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderComplaintImgHis entity
     * @return dto
     */
    OrderComplaintImgHisDTO converToDTO(OrderComplaintImgHis orderComplaintImgHis);

    /**
     * DTO转换为entity
     *
     * @param orderComplaintImgHisDTO dto
     * @return entity
     */
    OrderComplaintImgHis converToEntity(OrderComplaintImgHisDTO orderComplaintImgHisDTO);

    /**
     * entity转换为entity
     *
     * @param orderComplaintImg orderComplaintImg
     * @return entity
     */
    OrderComplaintImgHis converToEntity(OrderComplaintImg orderComplaintImg);

    /**
     * entity转换为VO
     *
     * @param orderComplaintImgHis entity
     * @return vo
     */
    OrderComplaintImgHisVO converToVO(OrderComplaintImgHis orderComplaintImgHis);

    /**
     * VO转换为entity
     *
     * @param orderComplaintImgHisVO vo
     * @return entity
     */
    OrderComplaintImgHis converToEntity(OrderComplaintImgHisVO orderComplaintImgHisVO);
}