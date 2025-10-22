package com.weikbest.pro.saas.applet.comm.module.mapstruct;

import com.weikbest.pro.saas.applet.comm.module.vo.AppDictVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.Dict;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 字典表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Mapper
public interface AppDictMapStruct extends BaseMapStruct {
    AppDictMapStruct INSTANCE = Mappers.getMapper(AppDictMapStruct.class);


    /**
     * entity转换为VO
     *
     * @param dict entity
     * @return vo
     */
    AppDictVO converToVO(Dict dict);

}