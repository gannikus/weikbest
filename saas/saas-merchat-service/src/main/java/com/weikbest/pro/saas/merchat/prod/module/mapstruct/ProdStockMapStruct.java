package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdStock;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdStockDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdStockVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品库存表（本期不用）实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdStockMapStruct extends BaseMapStruct {
    ProdStockMapStruct INSTANCE = Mappers.getMapper(ProdStockMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodStock entity
     * @return dto
     */
    ProdStockDTO converToDTO(ProdStock prodStock);

    /**
     * DTO转换为entity
     *
     * @param prodStockDTO dto
     * @return entity
     */
    ProdStock converToEntity(ProdStockDTO prodStockDTO);

    /**
     * entity转换为VO
     *
     * @param prodStock entity
     * @return vo
     */
    ProdStockVO converToVO(ProdStock prodStock);

    /**
     * VO转换为entity
     *
     * @param prodStockVO vo
     * @return entity
     */
    ProdStock converToEntity(ProdStockVO prodStockVO);
}