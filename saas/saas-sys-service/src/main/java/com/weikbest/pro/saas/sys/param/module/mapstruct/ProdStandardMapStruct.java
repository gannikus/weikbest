package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.ProdStandard;
import com.weikbest.pro.saas.sys.param.module.dto.ProdStandardDTO;
import com.weikbest.pro.saas.sys.param.module.vo.ProdStandardVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统商品规范表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
@Mapper
public interface ProdStandardMapStruct extends BaseMapStruct {
    ProdStandardMapStruct INSTANCE = Mappers.getMapper(ProdStandardMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodStandard entity
     * @return dto
     */
    ProdStandardDTO converToDTO(ProdStandard prodStandard);

    /**
     * DTO转换为entity
     *
     * @param prodStandardDTO dto
     * @return entity
     */
    ProdStandard converToEntity(ProdStandardDTO prodStandardDTO);

    /**
     * entity转换为VO
     *
     * @param prodStandard entity
     * @return vo
     */
    ProdStandardVO converToVO(ProdStandard prodStandard);

    /**
     * VO转换为entity
     *
     * @param prodStandardVO vo
     * @return entity
     */
    ProdStandard converToEntity(ProdStandardVO prodStandardVO);
}