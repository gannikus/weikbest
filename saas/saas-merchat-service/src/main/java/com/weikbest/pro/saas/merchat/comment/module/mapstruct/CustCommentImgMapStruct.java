package com.weikbest.pro.saas.merchat.comment.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.comment.entity.CustCommentImg;
import com.weikbest.pro.saas.merchat.comment.module.dto.CustCommentImgDTO;
import com.weikbest.pro.saas.merchat.comment.module.vo.CustCommentImgVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 用户评论图片表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface CustCommentImgMapStruct extends BaseMapStruct {
    CustCommentImgMapStruct INSTANCE = Mappers.getMapper(CustCommentImgMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param custCommentImg entity
     * @return dto
     */
    CustCommentImgDTO converToDTO(CustCommentImg custCommentImg);

    /**
     * DTO转换为entity
     *
     * @param custCommentImgDTO dto
     * @return entity
     */
    CustCommentImg converToEntity(CustCommentImgDTO custCommentImgDTO);

    /**
     * entity转换为VO
     *
     * @param custCommentImg entity
     * @return vo
     */
    CustCommentImgVO converToVO(CustCommentImg custCommentImg);

    /**
     * VO转换为entity
     *
     * @param custCommentImgVO vo
     * @return entity
     */
    CustCommentImg converToEntity(CustCommentImgVO custCommentImgVO);
}