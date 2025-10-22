package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdDecFloor;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdDecFloorDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdDecFloorVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品装修落地页拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface ProdDecFloorMapStruct extends BaseMapStruct {
    ProdDecFloorMapStruct INSTANCE = Mappers.getMapper(ProdDecFloorMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodDecFloor entity
     * @return dto
     */
    ProdDecFloorDTO converToDTO(ProdDecFloor prodDecFloor);

    /**
     * DTO转换为entity
     *
     * @param prodDecFloorDTO dto
     * @return entity
     */
    ProdDecFloor converToEntity(ProdDecFloorDTO prodDecFloorDTO);

    /**
     * entity转换为VO
     *
     * @param prodDecFloor entity
     * @return vo
     */
    ProdDecFloorVO converToVO(ProdDecFloor prodDecFloor);

    /**
     * VO转换为entity
     *
     * @param prodDecFloorVO vo
     * @return entity
     */
    ProdDecFloor converToEntity(ProdDecFloorVO prodDecFloorVO);
}