package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.UserLoginRecord;
import com.weikbest.pro.saas.sys.system.module.dto.UserLoginRecordDTO;
import com.weikbest.pro.saas.sys.system.module.vo.UserLoginRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统用户登录记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface UserLoginRecordMapStruct extends BaseMapStruct {
    UserLoginRecordMapStruct INSTANCE = Mappers.getMapper(UserLoginRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param userLoginRecord entity
     * @return dto
     */
    UserLoginRecordDTO converToDTO(UserLoginRecord userLoginRecord);

    /**
     * DTO转换为entity
     *
     * @param userLoginRecordDTO dto
     * @return entity
     */
    UserLoginRecord converToEntity(UserLoginRecordDTO userLoginRecordDTO);

    /**
     * entity转换为VO
     *
     * @param userLoginRecord entity
     * @return vo
     */
    UserLoginRecordVO converToVO(UserLoginRecord userLoginRecord);

    /**
     * VO转换为entity
     *
     * @param userLoginRecordVO vo
     * @return entity
     */
    UserLoginRecord converToEntity(UserLoginRecordVO userLoginRecordVO);
}