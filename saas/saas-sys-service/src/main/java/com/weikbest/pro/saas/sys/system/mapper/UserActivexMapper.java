package com.weikbest.pro.saas.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weikbest.pro.saas.sys.system.entity.UserActivex;

import java.util.List;

/**
 * <p>
 * 系统用户控件关联表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-20
 */
public interface UserActivexMapper extends BaseMapper<UserActivex> {

    void disabledByIds(List<Long> idList);
}
