package com.weikbest.pro.saas.sysmerchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.Prod;
import com.weikbest.pro.saas.sysmerchat.prod.module.vo.SysProdVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
@Mapper
public interface SysProdMapStruct extends BaseMapStruct {

    SysProdMapStruct INSTANCE = Mappers.getMapper(SysProdMapStruct.class);

    /**
     * entity转换为vo
     *
     * @param prod entity
     * @return vo
     */
    SysProdVO converToVO(Prod prod);
}
