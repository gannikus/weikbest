package com.weikbest.pro.saas.applet.commodity.module.mapstruct;

import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdDecFloorVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdDecFloor;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdDecFloorDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdDecFloorVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品装修落地页拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface AppProdDecFloorMapStruct extends BaseMapStruct {
    AppProdDecFloorMapStruct INSTANCE = Mappers.getMapper(AppProdDecFloorMapStruct.class);


    /**
     * entity转换为VO
     *
     * @param prodDecFloor entity
     * @return vo
     */
    AppProdDecFloorVO converToVO(ProdDecFloor prodDecFloor);

}