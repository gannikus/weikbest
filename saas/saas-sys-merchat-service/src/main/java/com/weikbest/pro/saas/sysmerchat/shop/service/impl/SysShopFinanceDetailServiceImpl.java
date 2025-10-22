package com.weikbest.pro.saas.sysmerchat.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopFinanceAccountMapper;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopFinanceDetailMapper;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopStatementDetailMapper;
import com.weikbest.pro.saas.sysmerchat.shop.mapper.SysShopFinanceDetailMapper;
import com.weikbest.pro.saas.sysmerchat.shop.module.qo.SysShopFinanceDetailQO;
import com.weikbest.pro.saas.sysmerchat.shop.module.vo.SysShopFinanceDetailPageVO;
import com.weikbest.pro.saas.sysmerchat.shop.module.vo.SysShopFinanceDetailVO;
import com.weikbest.pro.saas.sysmerchat.shop.service.SysShopFinanceDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/24
 */
@Service
public class SysShopFinanceDetailServiceImpl implements SysShopFinanceDetailService {

    @Resource
    private ShopFinanceAccountMapper shopFinanceAccountMapper;

    @Resource
    private SysShopFinanceDetailMapper sysShopFinanceDetailMapper;

    @Resource
    private ShopStatementDetailMapper shopStatementDetailMapper;

    @Override
    public SysShopFinanceDetailVO findSysShopFinanceDetail() {
        SysShopFinanceDetailVO sysShopFinanceDetailVO = new SysShopFinanceDetailVO();

        // 支付总金额（元）
        BigDecimal payTotalAmount = shopStatementDetailMapper.getPayTotalAmount();
        sysShopFinanceDetailVO.setPayTotalAmount(payTotalAmount);

        // 店铺已结算总金额（元）
        BigDecimal settleTotalAmount = shopFinanceAccountMapper.getSettleTotalAmount();
        sysShopFinanceDetailVO.setSettleTotalAmount(settleTotalAmount);

        // 平台押账总金额（元）
        BigDecimal depositAccountTotalAmount = shopFinanceAccountMapper.getDepositAccountTotalAmount();
        sysShopFinanceDetailVO.setDepositAccountTotalAmount(depositAccountTotalAmount);

        // 退款总金额（元）
        BigDecimal refundTotalAmount = shopStatementDetailMapper.getRefundTotalAmount();
        sysShopFinanceDetailVO.setRefundTotalAmount(refundTotalAmount);

        // 平台总抽佣（元）
        sysShopFinanceDetailVO.setCommissionTotalAmount(BigDecimal.ZERO);

        return sysShopFinanceDetailVO;
    }

    @Override
    public IPage<SysShopFinanceDetailPageVO> queryPage(SysShopFinanceDetailQO sysShopFinanceDetailQO, PageReq pageReq) {
        return sysShopFinanceDetailMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), sysShopFinanceDetailQO);
    }
}
