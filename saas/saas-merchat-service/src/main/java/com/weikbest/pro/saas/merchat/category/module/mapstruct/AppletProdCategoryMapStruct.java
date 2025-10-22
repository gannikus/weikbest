package com.weikbest.pro.saas.merchat.category.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategory;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryDTO;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryInsertDTO;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryUpdateDTO;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletProdCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序商品类目表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Mapper
public interface AppletProdCategoryMapStruct extends BaseMapStruct {
    AppletProdCategoryMapStruct INSTANCE = Mappers.getMapper(AppletProdCategoryMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param appletProdCategory entity
     * @return dto
     */
    AppletProdCategoryDTO converToDTO(AppletProdCategory appletProdCategory);

    /**
     * DTO转换为entity
     *
     * @param appletProdCategoryDTO dto
     * @return entity
     */
    AppletProdCategory converToEntity(AppletProdCategoryDTO appletProdCategoryDTO);

    /**
     * DTO转换为entity
     *
     * @param appletProdCategoryInsertDTO dto
     * @return entity
     */
    AppletProdCategory converToEntity(AppletProdCategoryInsertDTO appletProdCategoryInsertDTO);

    /**
     * DTO转换为entity
     *
     * @param appletProdCategoryUpdateDTO dto
     * @return entity
     */
    AppletProdCategory converToEntity(AppletProdCategoryUpdateDTO appletProdCategoryUpdateDTO);

    /**
     * entity转换为VO
     *
     * @param appletProdCategory entity
     * @return vo
     */
    AppletProdCategoryVO converToVO(AppletProdCategory appletProdCategory);

    /**
     * VO转换为entity
     *
     * @param appletProdCategoryVO vo
     * @return entity
     */
    AppletProdCategory converToEntity(AppletProdCategoryVO appletProdCategoryVO);
}