package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdJumpLink;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdJumpLinkDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdJumpLinkVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品跳转链接拆分多行表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdJumpLinkMapStruct extends BaseMapStruct {
    ProdJumpLinkMapStruct INSTANCE = Mappers.getMapper(ProdJumpLinkMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodJumpLink entity
     * @return dto
     */
    ProdJumpLinkDTO converToDTO(ProdJumpLink prodJumpLink);

    /**
     * DTO转换为entity
     *
     * @param prodJumpLinkDTO dto
     * @return entity
     */
    ProdJumpLink converToEntity(ProdJumpLinkDTO prodJumpLinkDTO);

    /**
     * DTO转换为entity
     *
     * @param prodJumpLinkDTO dto
     * @return entity
     */
    @Mapping(target = "id", source = "prodId")
    ProdJumpLink converToEntity(ProdJumpLinkDTO prodJumpLinkDTO, Long prodId);

    /**
     * entity转换为VO
     *
     * @param prodJumpLink entity
     * @return vo
     */
    ProdJumpLinkVO converToVO(ProdJumpLink prodJumpLink);

    /**
     * VO转换为entity
     *
     * @param prodJumpLinkVO vo
     * @return entity
     */
    ProdJumpLink converToEntity(ProdJumpLinkVO prodJumpLinkVO);
}