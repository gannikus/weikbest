package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdFeight;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdFeightDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdFeightVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品运费信息表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdFeightMapStruct extends BaseMapStruct {
    ProdFeightMapStruct INSTANCE = Mappers.getMapper(ProdFeightMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodFeight entity
     * @return dto
     */
    ProdFeightDTO converToDTO(ProdFeight prodFeight);

    /**
     * DTO转换为entity
     *
     * @param prodFeightDTO dto
     * @return entity
     */
    ProdFeight converToEntity(ProdFeightDTO prodFeightDTO);

    /**
     * DTO转换为entity
     *
     * @param prodFeightDTO dto
     * @return entity
     */
    @Mapping(target = "id", source = "prodId")
    ProdFeight converToEntity(ProdFeightDTO prodFeightDTO, Long prodId);

    /**
     * entity转换为VO
     *
     * @param prodFeight entity
     * @return vo
     */
    ProdFeightVO converToVO(ProdFeight prodFeight);

    /**
     * VO转换为entity
     *
     * @param prodFeightVO vo
     * @return entity
     */
    ProdFeight converToEntity(ProdFeightVO prodFeightVO);
}