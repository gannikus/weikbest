package com.weikbest.pro.saas.sys.param.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;

/**
 * <p>
 * 小程序配置表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
public interface AppletConfigMapper extends BaseMapper<AppletConfig> {

    AppletConfig getStochasticAllocation();

}
