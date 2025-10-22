package com.weikbest.pro.saas.applet.commodity.module.mapstruct;

import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdLeftSlideVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdLeftSlide;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdLeftSlideDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdLeftSlideVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品左滑设置拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface AppProdLeftSlideMapStruct extends BaseMapStruct {
    AppProdLeftSlideMapStruct INSTANCE = Mappers.getMapper(AppProdLeftSlideMapStruct.class);

    /**
     * entity转换为VO
     *
     * @param prodLeftSlide entity
     * @return vo
     */
    AppProdLeftSlideVO converToVO(ProdLeftSlide prodLeftSlide);

}