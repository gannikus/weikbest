package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.SmsRecord;
import com.weikbest.pro.saas.sys.param.module.dto.SmsRecordDTO;
import com.weikbest.pro.saas.sys.param.module.vo.SmsRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 短信发送记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface SmsRecordMapStruct extends BaseMapStruct {
    SmsRecordMapStruct INSTANCE = Mappers.getMapper(SmsRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param smsRecord entity
     * @return dto
     */
    SmsRecordDTO converToDTO(SmsRecord smsRecord);

    /**
     * DTO转换为entity
     *
     * @param smsRecordDTO dto
     * @return entity
     */
    SmsRecord converToEntity(SmsRecordDTO smsRecordDTO);

    /**
     * entity转换为VO
     *
     * @param smsRecord entity
     * @return vo
     */
    SmsRecordVO converToVO(SmsRecord smsRecord);

    /**
     * VO转换为entity
     *
     * @param smsRecordVO vo
     * @return entity
     */
    SmsRecord converToEntity(SmsRecordVO smsRecordVO);
}