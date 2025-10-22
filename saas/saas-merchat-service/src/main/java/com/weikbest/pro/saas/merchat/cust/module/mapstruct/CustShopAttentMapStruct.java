package com.weikbest.pro.saas.merchat.cust.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.cust.entity.CustShopAttent;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustShopAttentDTO;
import com.weikbest.pro.saas.merchat.cust.module.vo.CustShopAttentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户关注店铺表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-18
 */
@Mapper
public interface CustShopAttentMapStruct extends BaseMapStruct {
    CustShopAttentMapStruct INSTANCE = Mappers.getMapper(CustShopAttentMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param custShopAttent entity
     * @return dto
     */
    CustShopAttentDTO converToDTO(CustShopAttent custShopAttent);

    /**
     * DTO转换为entity
     *
     * @param custShopAttentDTO dto
     * @return entity
     */
    CustShopAttent converToEntity(CustShopAttentDTO custShopAttentDTO);

    /**
     * entity转换为VO
     *
     * @param custShopAttent entity
     * @return vo
     */
    CustShopAttentVO converToVO(CustShopAttent custShopAttent);

    /**
     * VO转换为entity
     *
     * @param custShopAttentVO vo
     * @return entity
     */
    CustShopAttent converToEntity(CustShopAttentVO custShopAttentVO);
}