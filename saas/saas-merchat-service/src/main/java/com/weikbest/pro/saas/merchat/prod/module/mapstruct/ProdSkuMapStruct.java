package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdSku;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdSkuDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdSkuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品销售属性表（本期不用）实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdSkuMapStruct extends BaseMapStruct {
    ProdSkuMapStruct INSTANCE = Mappers.getMapper(ProdSkuMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodSku entity
     * @return dto
     */
    ProdSkuDTO converToDTO(ProdSku prodSku);

    /**
     * DTO转换为entity
     *
     * @param prodSkuDTO dto
     * @return entity
     */
    ProdSku converToEntity(ProdSkuDTO prodSkuDTO);

    /**
     * entity转换为VO
     *
     * @param prodSku entity
     * @return vo
     */
    ProdSkuVO converToVO(ProdSku prodSku);

    /**
     * VO转换为entity
     *
     * @param prodSkuVO vo
     * @return entity
     */
    ProdSku converToEntity(ProdSkuVO prodSkuVO);
}