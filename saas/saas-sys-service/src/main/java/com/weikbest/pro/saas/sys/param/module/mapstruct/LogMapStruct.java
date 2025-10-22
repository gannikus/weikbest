package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.Log;
import com.weikbest.pro.saas.sys.param.module.dto.LogDTO;
import com.weikbest.pro.saas.sys.param.module.vo.LogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统日志表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Mapper
public interface LogMapStruct extends BaseMapStruct {
    LogMapStruct INSTANCE = Mappers.getMapper(LogMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param log entity
     * @return dto
     */
    LogDTO converToDTO(Log log);

    /**
     * DTO转换为entity
     *
     * @param logDTO dto
     * @return entity
     */
    Log converToEntity(LogDTO logDTO);

    /**
     * entity转换为VO
     *
     * @param log entity
     * @return vo
     */
    LogVO converToVO(Log log);

    /**
     * VO转换为entity
     *
     * @param logVO vo
     * @return entity
     */
    Log converToEntity(LogVO logVO);
}