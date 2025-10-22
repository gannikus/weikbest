package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdMainimg;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdMainimgDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdMainimgVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品详情页轮播图拆分多行表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdMainimgMapStruct extends BaseMapStruct {
    ProdMainimgMapStruct INSTANCE = Mappers.getMapper(ProdMainimgMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodMainimg entity
     * @return dto
     */
    ProdMainimgDTO converToDTO(ProdMainimg prodMainimg);

    /**
     * DTO转换为entity
     *
     * @param prodMainimgDTO dto
     * @return entity
     */
    ProdMainimg converToEntity(ProdMainimgDTO prodMainimgDTO);

    /**
     * DTO转换为entity
     *
     * @param prodMainimgDTO dto
     * @return entity
     */
    @Mapping(target = "id", source = "prodId")
    ProdMainimg converToEntity(ProdMainimgDTO prodMainimgDTO, Long prodId);

    /**
     * entity转换为VO
     *
     * @param prodMainimg entity
     * @return vo
     */
    ProdMainimgVO converToVO(ProdMainimg prodMainimg);

    /**
     * VO转换为entity
     *
     * @param prodMainimgVO vo
     * @return entity
     */
    ProdMainimg converToEntity(ProdMainimgVO prodMainimgVO);
}