package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskRecord;
import com.weikbest.pro.saas.sys.param.module.dto.DelayTaskRecordDTO;
import com.weikbest.pro.saas.sys.param.module.vo.DelayTaskRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统延时任务执行记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Mapper
public interface DelayTaskRecordMapStruct extends BaseMapStruct {
    DelayTaskRecordMapStruct INSTANCE = Mappers.getMapper(DelayTaskRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param delayTaskRecord entity
     * @return dto
     */
    DelayTaskRecordDTO converToDTO(DelayTaskRecord delayTaskRecord);

    /**
     * DTO转换为entity
     *
     * @param delayTaskRecordDTO dto
     * @return entity
     */
    DelayTaskRecord converToEntity(DelayTaskRecordDTO delayTaskRecordDTO);

    /**
     * entity转换为VO
     *
     * @param delayTaskRecord entity
     * @return vo
     */
    DelayTaskRecordVO converToVO(DelayTaskRecord delayTaskRecord);

    /**
     * VO转换为entity
     *
     * @param delayTaskRecordVO vo
     * @return entity
     */
    DelayTaskRecord converToEntity(DelayTaskRecordVO delayTaskRecordVO);
}