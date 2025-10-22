package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.CodeRule;
import com.weikbest.pro.saas.sys.param.module.dto.CodeRuleDTO;
import com.weikbest.pro.saas.sys.param.module.vo.CodeRuleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统编码规则表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Mapper
public interface CodeRuleMapStruct extends BaseMapStruct {
    CodeRuleMapStruct INSTANCE = Mappers.getMapper(CodeRuleMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param codeRule entity
     * @return dto
     */
    CodeRuleDTO converToDTO(CodeRule codeRule);

    /**
     * DTO转换为entity
     *
     * @param codeRuleDTO dto
     * @return entity
     */
    @Mapping(target = "initDigit", source = "initDigit")
    @Mapping(target = "realDigit", source = "initDigit")
    CodeRule converToEntity(CodeRuleDTO codeRuleDTO);

    /**
     * entity转换为VO
     *
     * @param codeRule entity
     * @return vo
     */
    CodeRuleVO converToVO(CodeRule codeRule);

    /**
     * VO转换为entity
     *
     * @param codeRuleVO vo
     * @return entity
     */
    CodeRule converToEntity(CodeRuleVO codeRuleVO);
}