package com.weikbest.pro.saas.sysmerchat.business.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.sysmerchat.business.module.qo.SysBusiQO;
import com.weikbest.pro.saas.sysmerchat.business.module.vo.SysBusiListVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
public interface SysBusiMapper {

    /**
     * 分页查询数据
     *
     * @param page
     * @param sysBusiQO
     * @return
     */
    IPage<SysBusiListVO> queryPage(IPage<SysBusiListVO> page, @Param("sysBusi") SysBusiQO sysBusiQO);
}
