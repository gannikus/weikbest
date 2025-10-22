package com.weikbest.pro.saas.sys.system.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.system.entity.Org;
import com.weikbest.pro.saas.sys.system.module.dto.OrgDTO;
import com.weikbest.pro.saas.sys.system.module.vo.OrgVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统部门表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-22
 */
@Mapper
public interface OrgMapStruct extends BaseMapStruct {
    OrgMapStruct INSTANCE = Mappers.getMapper(OrgMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param org entity
     * @return dto
     */
    OrgDTO converToDTO(Org org);

    /**
     * DTO转换为entity
     *
     * @param orgDTO dto
     * @return entity
     */
    Org converToEntity(OrgDTO orgDTO);

    /**
     * entity转换为VO
     *
     * @param org entity
     * @return vo
     */
    OrgVO converToVO(Org org);

    /**
     * VO转换为entity
     *
     * @param orgVO vo
     * @return entity
     */
    Org converToEntity(OrgVO orgVO);
}