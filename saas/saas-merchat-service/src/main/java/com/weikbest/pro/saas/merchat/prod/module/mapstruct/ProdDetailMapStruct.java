package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdDetail;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdDetailDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品详情拆分多行表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdDetailMapStruct extends BaseMapStruct {
    ProdDetailMapStruct INSTANCE = Mappers.getMapper(ProdDetailMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodDetail entity
     * @return dto
     */
    ProdDetailDTO converToDTO(ProdDetail prodDetail);

    /**
     * DTO转换为entity
     *
     * @param prodDetailDTO dto
     * @return entity
     */
    ProdDetail converToEntity(ProdDetailDTO prodDetailDTO);

    /**
     * DTO转换为entity
     *
     * @param prodDetailDTO dto
     * @return entity
     */
    @Mapping(target = "id", source = "prodId")
    ProdDetail converToEntity(ProdDetailDTO prodDetailDTO, Long prodId);

    /**
     * entity转换为VO
     *
     * @param prodDetail entity
     * @return vo
     */
    ProdDetailVO converToVO(ProdDetail prodDetail);

    /**
     * VO转换为entity
     *
     * @param prodDetailVO vo
     * @return entity
     */
    ProdDetail converToEntity(ProdDetailVO prodDetailVO);

}