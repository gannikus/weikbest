package com.weikbest.pro.saas.merchat.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.merchat.shop.entity.ShopStatementDetail;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopStatementDetailQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopStatementDetailPageVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 * 店铺对账单明细表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ShopStatementDetailMapper extends BaseMapper<ShopStatementDetail> {

    /**
     * 支付总金额（元）
     *
     * @return
     */
    BigDecimal getPayTotalAmount();

    /**
     * 退款总金额（元）
     *
     * @return
     */
    BigDecimal getRefundTotalAmount();

    /**
     * 分页查询
     *
     * @param page
     * @param shopStatementDetailQO
     * @return
     */
    IPage<ShopStatementDetailPageVO> queryPage(IPage<ShopStatementDetailPageVO> page, @Param("shopStatementDetail") ShopStatementDetailQO shopStatementDetailQO);
}
