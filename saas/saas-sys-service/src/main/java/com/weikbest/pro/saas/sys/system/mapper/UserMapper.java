package com.weikbest.pro.saas.sys.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weikbest.pro.saas.sys.system.entity.User;
import com.weikbest.pro.saas.sys.system.module.qo.UserQO;
import com.weikbest.pro.saas.sys.system.module.vo.UserVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户表分页查询
     *
     * @param page
     * @param userQO
     * @return
     */
    IPage<UserVO> queryPage(Page<UserVO> page, @Param("user")  UserQO userQO);
}
