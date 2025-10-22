package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.common.third.aliyun.areas.config.AliyunAreas;
import com.weikbest.pro.saas.sys.param.entity.Region;
import com.weikbest.pro.saas.sys.param.module.dto.RegionDTO;
import com.weikbest.pro.saas.sys.param.module.vo.RegionVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 平台行政区划表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-03
 */
@Mapper
public interface RegionMapStruct extends BaseMapStruct {
    RegionMapStruct INSTANCE = Mappers.getMapper(RegionMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param region entity
     * @return dto
     */
    RegionDTO converToDTO(Region region);

    /**
     * DTO转换为entity
     *
     * @param regionDTO dto
     * @return entity
     */
    Region converToEntity(RegionDTO regionDTO);

    /**
     * aliyunAreas转换为entity
     *
     * @param aliyunAreas dto
     * @return entity
     */
    @Mapping(target = "adcode", source = "adcode")
    @Mapping(target = "regionLevel", source = "level")
    @Mapping(target = "lng", source = "lng")
    @Mapping(target = "lat", source = "lat")
    @Mapping(target = "parentName", source = "parent")
    @Mapping(target = "parentAdcode", source = "parentAdcode")
    Region converToEntity(AliyunAreas aliyunAreas);

    /**
     * entity转换为VO
     *
     * @param region entity
     * @return vo
     */
    RegionVO converToVO(Region region);

    /**
     * VO转换为entity
     *
     * @param regionVO vo
     * @return entity
     */
    Region converToEntity(RegionVO regionVO);
}