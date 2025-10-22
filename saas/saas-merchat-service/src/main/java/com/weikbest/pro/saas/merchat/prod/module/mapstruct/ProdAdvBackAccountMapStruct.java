package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAdvBackAccount;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdAdvBackAccountDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdAdvBackAccountVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品广告回传信息关联广告账户拆分多行表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdAdvBackAccountMapStruct extends BaseMapStruct {
    ProdAdvBackAccountMapStruct INSTANCE = Mappers.getMapper(ProdAdvBackAccountMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodAdvBackAccount entity
     * @return dto
     */
    ProdAdvBackAccountDTO converToDTO(ProdAdvBackAccount prodAdvBackAccount);

    /**
     * DTO转换为entity
     *
     * @param prodAdvBackAccountDTO dto
     * @return entity
     */
    ProdAdvBackAccount converToEntity(ProdAdvBackAccountDTO prodAdvBackAccountDTO);

    /**
     * DTO转换为entity
     *
     * @param prodAdvBackAccountDTO dto
     * @return entity
     */
    @Mapping(target = "id", source = "prodId")
    ProdAdvBackAccount converToEntity(ProdAdvBackAccountDTO prodAdvBackAccountDTO, Long prodId);

    /**
     * entity转换为VO
     *
     * @param prodAdvBackAccount entity
     * @return vo
     */
    ProdAdvBackAccountVO converToVO(ProdAdvBackAccount prodAdvBackAccount);

    /**
     * VO转换为entity
     *
     * @param prodAdvBackAccountVO vo
     * @return entity
     */
    ProdAdvBackAccount converToEntity(ProdAdvBackAccountVO prodAdvBackAccountVO);
}
