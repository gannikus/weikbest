package com.weikbest.pro.saas.merchat.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceAccount;

import java.math.BigDecimal;

/**
 * <p>
 * 店铺资金账户表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ShopFinanceAccountMapper extends BaseMapper<ShopFinanceAccount> {

    /**
     * 店铺已结算总金额（元）
     *
     * @return
     */
    BigDecimal getSettleTotalAmount();

    /**
     * 平台押账总金额（元）
     *
     * @return
     */
    BigDecimal getDepositAccountTotalAmount();
}
