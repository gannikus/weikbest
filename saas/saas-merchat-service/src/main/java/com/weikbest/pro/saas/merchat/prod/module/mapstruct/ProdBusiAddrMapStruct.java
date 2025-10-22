package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdBusiAddr;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdBusiAddrDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdBusiAddrVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品与商家详细地址管理表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdBusiAddrMapStruct extends BaseMapStruct {
    ProdBusiAddrMapStruct INSTANCE = Mappers.getMapper(ProdBusiAddrMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodBusiAddr entity
     * @return dto
     */
    ProdBusiAddrDTO converToDTO(ProdBusiAddr prodBusiAddr);

    /**
     * DTO转换为entity
     *
     * @param prodBusiAddrDTO dto
     * @return entity
     */
    ProdBusiAddr converToEntity(ProdBusiAddrDTO prodBusiAddrDTO);

    /**
     * entity转换为VO
     *
     * @param prodBusiAddr entity
     * @return vo
     */
    ProdBusiAddrVO converToVO(ProdBusiAddr prodBusiAddr);

    /**
     * VO转换为entity
     *
     * @param prodBusiAddrVO vo
     * @return entity
     */
    ProdBusiAddr converToEntity(ProdBusiAddrVO prodBusiAddrVO);
}