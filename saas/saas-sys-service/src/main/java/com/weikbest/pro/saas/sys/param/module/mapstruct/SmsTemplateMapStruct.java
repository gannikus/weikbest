package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.SmsTemplate;
import com.weikbest.pro.saas.sys.param.module.dto.SmsTemplateDTO;
import com.weikbest.pro.saas.sys.param.module.vo.SmsTemplateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 短信模板表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Mapper
public interface SmsTemplateMapStruct extends BaseMapStruct {
    SmsTemplateMapStruct INSTANCE = Mappers.getMapper(SmsTemplateMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param smsTemplate entity
     * @return dto
     */
    SmsTemplateDTO converToDTO(SmsTemplate smsTemplate);

    /**
     * DTO转换为entity
     *
     * @param smsTemplateDTO dto
     * @return entity
     */
    SmsTemplate converToEntity(SmsTemplateDTO smsTemplateDTO);

    /**
     * entity转换为VO
     *
     * @param smsTemplate entity
     * @return vo
     */
    SmsTemplateVO converToVO(SmsTemplate smsTemplate);

    /**
     * VO转换为entity
     *
     * @param smsTemplateVO vo
     * @return entity
     */
    SmsTemplate converToEntity(SmsTemplateVO smsTemplateVO);
}