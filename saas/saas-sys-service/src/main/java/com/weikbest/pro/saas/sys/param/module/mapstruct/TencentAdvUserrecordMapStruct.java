package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvUserrecord;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUserrecordDTO;
import com.weikbest.pro.saas.sys.param.module.vo.TencentAdvUserrecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 腾讯广告数据上报用户行为数据记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Mapper
public interface TencentAdvUserrecordMapStruct extends BaseMapStruct {
    TencentAdvUserrecordMapStruct INSTANCE = Mappers.getMapper(TencentAdvUserrecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param tencentAdvUserrecord entity
     * @return dto
     */
    TencentAdvUserrecordDTO converToDTO(TencentAdvUserrecord tencentAdvUserrecord);

    /**
     * DTO转换为entity
     *
     * @param tencentAdvUserrecordDTO dto
     * @return entity
     */
    TencentAdvUserrecord converToEntity(TencentAdvUserrecordDTO tencentAdvUserrecordDTO);

    /**
     * entity转换为VO
     *
     * @param tencentAdvUserrecord entity
     * @return vo
     */
    TencentAdvUserrecordVO converToVO(TencentAdvUserrecord tencentAdvUserrecord);

    /**
     * VO转换为entity
     *
     * @param tencentAdvUserrecordVO vo
     * @return entity
     */
    TencentAdvUserrecord converToEntity(TencentAdvUserrecordVO tencentAdvUserrecordVO);
}