package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.User;
import com.weikbest.pro.saas.sys.system.entity.UserActivex;
import com.weikbest.pro.saas.sys.system.module.dto.UserDTO;
import com.weikbest.pro.saas.sys.system.module.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统用户表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Mapper
public interface UserMapStruct extends BaseMapStruct {
    UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param user entity
     * @return dto
     */
    UserDTO converToDTO(User user);

    /**
     * DTO转换为entity
     *
     * @param userDTO dto
     * @return entity
     */
    User converToEntity(UserDTO userDTO);

    /**
     * entity转换为VO
     *
     * @param user entity
     * @return vo
     */
    UserVO converToVO(User user);

    /**
     * VO转换为entity
     *
     * @param userVO vo
     * @return entity
     */
    User converToEntity(UserVO userVO);

    /**
     * entity转换为UserActivexEntity
     *
     * @param user entity
     * @return UserActivex
     */
    @Mapping(target = "activexDataStatus", source = "dataStatus")
    UserActivex converToUserActivexEntity(User user);
}