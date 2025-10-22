package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdLeftSlide;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdLeftSlideDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdLeftSlideVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品左滑设置拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface ProdLeftSlideMapStruct extends BaseMapStruct {
    ProdLeftSlideMapStruct INSTANCE = Mappers.getMapper(ProdLeftSlideMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodLeftSlide entity
     * @return dto
     */
    ProdLeftSlideDTO converToDTO(ProdLeftSlide prodLeftSlide);

    /**
     * DTO转换为entity
     *
     * @param prodLeftSlideDTO dto
     * @return entity
     */
    ProdLeftSlide converToEntity(ProdLeftSlideDTO prodLeftSlideDTO);

    /**
     * entity转换为VO
     *
     * @param prodLeftSlide entity
     * @return vo
     */
    ProdLeftSlideVO converToVO(ProdLeftSlide prodLeftSlide);

    /**
     * VO转换为entity
     *
     * @param prodLeftSlideVO vo
     * @return entity
     */
    ProdLeftSlide converToEntity(ProdLeftSlideVO prodLeftSlideVO);
}