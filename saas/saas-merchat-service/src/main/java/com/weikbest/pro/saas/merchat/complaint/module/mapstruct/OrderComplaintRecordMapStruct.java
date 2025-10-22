package com.weikbest.pro.saas.merchat.complaint.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaint;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintRecord;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintRecordDTO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintDetailRecordDetailVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单投诉处理记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-05
 */
@Mapper
public interface OrderComplaintRecordMapStruct extends BaseMapStruct {
    OrderComplaintRecordMapStruct INSTANCE = Mappers.getMapper(OrderComplaintRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderComplaintRecord entity
     * @return dto
     */
    OrderComplaintRecordDTO converToDTO(OrderComplaintRecord orderComplaintRecord);

    /**
     * DTO转换为entity
     *
     * @param orderComplaintRecordDTO dto
     * @return entity
     */
    OrderComplaintRecord converToEntity(OrderComplaintRecordDTO orderComplaintRecordDTO);

    /**
     * orderComplaint转换为entity
     *
     * @param orderComplaint orderComplaint
     * @return entity
     */
    OrderComplaintRecord converToEntity(OrderComplaint orderComplaint);

    /**
     * entity转换为VO
     *
     * @param orderComplaintRecord entity
     * @return vo
     */
    OrderComplaintRecordVO converToVO(OrderComplaintRecord orderComplaintRecord);

    /**
     * VO转换为entity
     *
     * @param orderComplaintRecordVO vo
     * @return entity
     */
    OrderComplaintRecord converToEntity(OrderComplaintRecordVO orderComplaintRecordVO);

    /**
     * entity转换为VO
     *
     * @param orderComplaintRecord entity
     * @return vo
     */
    OrderComplaintDetailRecordDetailVO converToDetailVO(OrderComplaintRecord orderComplaintRecord);
}