package com.weikbest.pro.saas.merchat.comment.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.comment.entity.CustComment;
import com.weikbest.pro.saas.merchat.comment.module.dto.CustCommentDTO;
import com.weikbest.pro.saas.merchat.comment.module.vo.CustCommentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户评论表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-24
 */
@Mapper
public interface CustCommentMapStruct extends BaseMapStruct {
    CustCommentMapStruct INSTANCE = Mappers.getMapper(CustCommentMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param custComment entity
     * @return dto
     */
    CustCommentDTO converToDTO(CustComment custComment);

    /**
     * DTO转换为entity
     *
     * @param custCommentDTO dto
     * @return entity
     */
    CustComment converToEntity(CustCommentDTO custCommentDTO);

    /**
     * entity转换为VO
     *
     * @param custComment entity
     * @return vo
     */
    CustCommentVO converToVO(CustComment custComment);

    /**
     * VO转换为entity
     *
     * @param custCommentVO vo
     * @return entity
     */
    CustComment converToEntity(CustCommentVO custCommentVO);
}