package com.weikbest.pro.saas.sysmerchat.prod.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.sysmerchat.prod.module.qo.SysProdQO;
import com.weikbest.pro.saas.sysmerchat.prod.module.vo.SysProdListVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
public interface SysProdMapper {

    /**
     * 分页查询列表数据
     *
     * @param page
     * @param sysProdQO
     * @return
     */
    IPage<SysProdListVO> queryPage(IPage<SysProdListVO> page, @Param("sysProd") SysProdQO sysProdQO);
}
