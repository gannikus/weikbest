package com.weikbest.pro.saas.merchat.busi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusiUserQO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiUserPageVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商户账户表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
public interface BusiUserMapper extends BaseMapper<BusiUser> {

    /**
     * 商户账户表分页查询
     *
     * @param page
     * @param busiUserQO
     * @return
     */
    IPage<BusiUserPageVO> queryPage(Page<BusiUserPageVO> page, @Param("busiUser") BusiUserQO busiUserQO);
}
