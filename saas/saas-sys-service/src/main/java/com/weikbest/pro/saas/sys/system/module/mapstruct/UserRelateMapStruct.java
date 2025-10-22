package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.UserRelate;
import com.weikbest.pro.saas.sys.system.module.dto.UserRelateDTO;
import com.weikbest.pro.saas.sys.system.module.vo.UserRelateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统用户关联表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Mapper
public interface UserRelateMapStruct extends BaseMapStruct {
    UserRelateMapStruct INSTANCE = Mappers.getMapper(UserRelateMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param userRelate entity
     * @return dto
     */
    UserRelateDTO converToDTO(UserRelate userRelate);

    /**
     * DTO转换为entity
     *
     * @param userRelateDTO dto
     * @return entity
     */
    UserRelate converToEntity(UserRelateDTO userRelateDTO);

    /**
     * entity转换为VO
     *
     * @param userRelate entity
     * @return vo
     */
    UserRelateVO converToVO(UserRelate userRelate);

    /**
     * VO转换为entity
     *
     * @param userRelateVO vo
     * @return entity
     */
    UserRelate converToEntity(UserRelateVO userRelateVO);
}