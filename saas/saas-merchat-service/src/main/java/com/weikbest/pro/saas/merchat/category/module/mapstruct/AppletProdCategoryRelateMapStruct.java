package com.weikbest.pro.saas.merchat.category.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategoryRelate;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryRelateDTO;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletProdCategoryRelateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序商品类目关联商品表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Mapper
public interface AppletProdCategoryRelateMapStruct extends BaseMapStruct {
    AppletProdCategoryRelateMapStruct INSTANCE = Mappers.getMapper(AppletProdCategoryRelateMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param appletProdCategoryRelate entity
     * @return dto
     */
    AppletProdCategoryRelateDTO converToDTO(AppletProdCategoryRelate appletProdCategoryRelate);

    /**
     * DTO转换为entity
     *
     * @param appletProdCategoryRelateDTO dto
     * @return entity
     */
    AppletProdCategoryRelate converToEntity(AppletProdCategoryRelateDTO appletProdCategoryRelateDTO);

    /**
     * entity转换为VO
     *
     * @param appletProdCategoryRelate entity
     * @return vo
     */
    AppletProdCategoryRelateVO converToVO(AppletProdCategoryRelate appletProdCategoryRelate);

    /**
     * VO转换为entity
     *
     * @param appletProdCategoryRelateVO vo
     * @return entity
     */
    AppletProdCategoryRelate converToEntity(AppletProdCategoryRelateVO appletProdCategoryRelateVO);
}