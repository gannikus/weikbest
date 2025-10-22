package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskConfig;
import com.weikbest.pro.saas.sys.param.module.dto.DelayTaskConfigDTO;
import com.weikbest.pro.saas.sys.param.module.vo.DelayTaskConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统延时任务表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Mapper
public interface DelayTaskConfigMapStruct extends BaseMapStruct {
    DelayTaskConfigMapStruct INSTANCE = Mappers.getMapper(DelayTaskConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param delayTaskConfig entity
     * @return dto
     */
    DelayTaskConfigDTO converToDTO(DelayTaskConfig delayTaskConfig);

    /**
     * DTO转换为entity
     *
     * @param delayTaskConfigDTO dto
     * @return entity
     */
    DelayTaskConfig converToEntity(DelayTaskConfigDTO delayTaskConfigDTO);

    /**
     * entity转换为VO
     *
     * @param delayTaskConfig entity
     * @return vo
     */
    DelayTaskConfigVO converToVO(DelayTaskConfig delayTaskConfig);

    /**
     * VO转换为entity
     *
     * @param delayTaskConfigVO vo
     * @return entity
     */
    DelayTaskConfig converToEntity(DelayTaskConfigVO delayTaskConfigVO);
}