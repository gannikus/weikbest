package com.weikbest.pro.saas.merchat.busi.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUserRelate;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserRelateDTO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiUserRelateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商户与商户账户关联表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2023-02-23
 */
@Mapper
public interface BusiUserRelateMapStruct extends BaseMapStruct {
    BusiUserRelateMapStruct INSTANCE = Mappers.getMapper(BusiUserRelateMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param busiUserRelate entity
     * @return dto
     */
    BusiUserRelateDTO converToDTO(BusiUserRelate busiUserRelate);

    /**
     * DTO转换为entity
     *
     * @param busiUserRelateDTO dto
     * @return entity
     */
    BusiUserRelate converToEntity(BusiUserRelateDTO busiUserRelateDTO);

    /**
     * entity转换为VO
     *
     * @param busiUserRelate entity
     * @return vo
     */
    BusiUserRelateVO converToVO(BusiUserRelate busiUserRelate);

    /**
     * VO转换为entity
     *
     * @param busiUserRelateVO vo
     * @return entity
     */
    BusiUserRelate converToEntity(BusiUserRelateVO busiUserRelateVO);
}