package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.AppletSubscribeConfig;
import com.weikbest.pro.saas.sys.param.entity.AppletSubscribeRecord;
import com.weikbest.pro.saas.sys.param.module.dto.AppletSubscribeRecordDTO;
import com.weikbest.pro.saas.sys.param.module.vo.AppletSubscribeRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序订阅消息发送记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Mapper
public interface AppletSubscribeRecordMapStruct extends BaseMapStruct {
    AppletSubscribeRecordMapStruct INSTANCE = Mappers.getMapper(AppletSubscribeRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param appletSubscribeRecord entity
     * @return dto
     */
    AppletSubscribeRecordDTO converToDTO(AppletSubscribeRecord appletSubscribeRecord);

    /**
     * DTO转换为entity
     *
     * @param appletSubscribeRecordDTO dto
     * @return entity
     */
    AppletSubscribeRecord converToEntity(AppletSubscribeRecordDTO appletSubscribeRecordDTO);


    /**
     * appletSubscribeConfig转换为entity
     *
     * @param appletSubscribeConfig appletSubscribeConfig
     * @return entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subscribeConfigId", source = "id")
    AppletSubscribeRecord converToEntity(AppletSubscribeConfig appletSubscribeConfig);


    /**
     * entity转换为VO
     *
     * @param appletSubscribeRecord entity
     * @return vo
     */
    AppletSubscribeRecordVO converToVO(AppletSubscribeRecord appletSubscribeRecord);

    /**
     * VO转换为entity
     *
     * @param appletSubscribeRecordVO vo
     * @return entity
     */
    AppletSubscribeRecord converToEntity(AppletSubscribeRecordVO appletSubscribeRecordVO);
}