package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdPurchaseRestrictions;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdPurchaseRestrictionsDto;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdPurchaseRestrictionsVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description
 *
 * @author 甘之萌  2023/06/15 13:27
 */
@Mapper
public interface ProdPurchaseRestrictionsMapStruct extends BaseMapStruct {

    ProdPurchaseRestrictionsMapStruct INSTANCE = Mappers.getMapper(ProdPurchaseRestrictionsMapStruct.class);


    /**
     * entity转换为VO
     *
     * @param prodPurchaseRestrictions entity
     * @return vo
     */
    ProdPurchaseRestrictionsVo converToVO(ProdPurchaseRestrictions prodPurchaseRestrictions);


    /**
     * dto转entity
     * @param prodPurchaseRestrictionsDto dto
     * @return
     */
    ProdPurchaseRestrictions convertDto(ProdPurchaseRestrictionsDto prodPurchaseRestrictionsDto);
}
