package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvUsersource;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUsersourceDTO;
import com.weikbest.pro.saas.sys.param.module.vo.TencentAdvUsersourceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 腾讯广告数据上报用户行为数据源表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Mapper
public interface TencentAdvUsersourceMapStruct extends BaseMapStruct {
    TencentAdvUsersourceMapStruct INSTANCE = Mappers.getMapper(TencentAdvUsersourceMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param tencentAdvUsersource entity
     * @return dto
     */
    TencentAdvUsersourceDTO converToDTO(TencentAdvUsersource tencentAdvUsersource);

    /**
     * DTO转换为entity
     *
     * @param tencentAdvUsersourceDTO dto
     * @return entity
     */
    TencentAdvUsersource converToEntity(TencentAdvUsersourceDTO tencentAdvUsersourceDTO);

    /**
     * entity转换为VO
     *
     * @param tencentAdvUsersource entity
     * @return vo
     */
    TencentAdvUsersourceVO converToVO(TencentAdvUsersource tencentAdvUsersource);

    /**
     * VO转换为entity
     *
     * @param tencentAdvUsersourceVO vo
     * @return entity
     */
    TencentAdvUsersource converToEntity(TencentAdvUsersourceVO tencentAdvUsersourceVO);
}