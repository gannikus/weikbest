package com.weikbest.pro.saas.merchat.busi.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.busi.entity.CustBusinessBind;
import com.weikbest.pro.saas.merchat.busi.module.dto.CustBusinessBindDTO;
import com.weikbest.pro.saas.merchat.busi.module.vo.CustBusinessBindVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 分账商户绑定表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Mapper
public interface CustBusinessBindMapStruct extends BaseMapStruct {
    CustBusinessBindMapStruct INSTANCE = Mappers.getMapper(CustBusinessBindMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param custBusinessBind entity
     * @return dto
     */
    CustBusinessBindDTO converToDTO(CustBusinessBind custBusinessBind);

    /**
     * DTO转换为entity
     *
     * @param custBusinessBindDTO dto
     * @return entity
     */
    CustBusinessBind converToEntity(CustBusinessBindDTO custBusinessBindDTO);

    /**
     * entity转换为VO
     *
     * @param custBusinessBind entity
     * @return vo
     */
    CustBusinessBindVO converToVO(CustBusinessBind custBusinessBind);

    /**
     * VO转换为entity
     *
     * @param custBusinessBindVO vo
     * @return entity
     */
    CustBusinessBind converToEntity(CustBusinessBindVO custBusinessBindVO);
}