package com.weikbest.pro.saas.sysmerchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sysmerchat.shop.module.qo.SysShopFinanceDetailQO;
import com.weikbest.pro.saas.sysmerchat.shop.module.vo.SysShopFinanceDetailPageVO;
import com.weikbest.pro.saas.sysmerchat.shop.module.vo.SysShopFinanceDetailVO;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/24
 */
public interface SysShopFinanceDetailService {

    /**
     * 财务概况-平台概况
     *
     * @return
     */
    SysShopFinanceDetailVO findSysShopFinanceDetail();

    /**
     * 资金明细分页查询
     *
     * @param sysShopFinanceDetailQO
     * @param pageReq
     * @return
     */
    IPage<SysShopFinanceDetailPageVO> queryPage(SysShopFinanceDetailQO sysShopFinanceDetailQO, PageReq pageReq);
}
