package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.PrivacyPolicy;
import com.weikbest.pro.saas.sys.param.module.dto.PrivacyPolicyDTO;
import com.weikbest.pro.saas.sys.param.module.vo.PrivacyPolicyVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统隐私声明表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-23
 */
@Mapper
public interface PrivacyPolicyMapStruct extends BaseMapStruct {
    PrivacyPolicyMapStruct INSTANCE = Mappers.getMapper(PrivacyPolicyMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param privacyPolicy entity
     * @return dto
     */
    PrivacyPolicyDTO converToDTO(PrivacyPolicy privacyPolicy);

    /**
     * DTO转换为entity
     *
     * @param privacyPolicyDTO dto
     * @return entity
     */
    PrivacyPolicy converToEntity(PrivacyPolicyDTO privacyPolicyDTO);

    /**
     * entity转换为VO
     *
     * @param privacyPolicy entity
     * @return vo
     */
    PrivacyPolicyVO converToVO(PrivacyPolicy privacyPolicy);

    /**
     * VO转换为entity
     *
     * @param privacyPolicyVO vo
     * @return entity
     */
    PrivacyPolicy converToEntity(PrivacyPolicyVO privacyPolicyVO);
}