package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.UserActivex;
import com.weikbest.pro.saas.sys.system.module.dto.UserActivexDTO;
import com.weikbest.pro.saas.sys.system.module.vo.UserActivexVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统用户控件关联表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-20
 */
@Mapper
public interface UserActivexMapStruct extends BaseMapStruct {
    UserActivexMapStruct INSTANCE = Mappers.getMapper(UserActivexMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param userActivex entity
     * @return dto
     */
    UserActivexDTO converToDTO(UserActivex userActivex);

    /**
     * DTO转换为entity
     *
     * @param userActivexDTO dto
     * @return entity
     */
    UserActivex converToEntity(UserActivexDTO userActivexDTO);

    /**
     * entity转换为VO
     *
     * @param userActivex entity
     * @return vo
     */
    UserActivexVO converToVO(UserActivex userActivex);

    /**
     * VO转换为entity
     *
     * @param userActivexVO vo
     * @return entity
     */
    UserActivex converToEntity(UserActivexVO userActivexVO);
}