package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.UserLogin;
import com.weikbest.pro.saas.sys.system.module.dto.UserLoginDTO;
import com.weikbest.pro.saas.sys.system.module.vo.UserLoginVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统用户登录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Mapper
public interface UserLoginMapStruct extends BaseMapStruct {
    UserLoginMapStruct INSTANCE = Mappers.getMapper(UserLoginMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param userLogin entity
     * @return dto
     */
    UserLoginDTO converToDTO(UserLogin userLogin);

    /**
     * DTO转换为entity
     *
     * @param userLoginDTO dto
     * @return entity
     */
    UserLogin converToEntity(UserLoginDTO userLoginDTO);

    /**
     * entity转换为VO
     *
     * @param userLogin entity
     * @return vo
     */
    UserLoginVO converToVO(UserLogin userLogin);

    /**
     * VO转换为entity
     *
     * @param userLoginVO vo
     * @return entity
     */
    UserLogin converToEntity(UserLoginVO userLoginVO);
}