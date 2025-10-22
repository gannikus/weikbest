package com.weikbest.pro.saas.sysmerchat.shop.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weikbest.pro.saas.sysmerchat.shop.module.qo.SysShopFinanceDetailQO;
import com.weikbest.pro.saas.sysmerchat.shop.module.vo.SysShopFinanceDetailPageVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/24
 */
public interface SysShopFinanceDetailMapper {

    /**
     * 列表分页查询
     *
     * @param page
     * @param sysShopFinanceDetailQO
     * @return
     */
    IPage<SysShopFinanceDetailPageVO> queryPage(IPage<SysShopFinanceDetailPageVO> page, @Param("sysShopFinanceDetail") SysShopFinanceDetailQO sysShopFinanceDetailQO);
}
