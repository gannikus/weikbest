package com.weikbest.pro.saas.merchat.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceDetail;
import com.weikbest.pro.saas.merchat.shop.module.excel.ShopFinanceDetailExcel;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopFinanceDetailQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopFinanceDetailPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 店铺资金明细表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ShopFinanceDetailMapper extends BaseMapper<ShopFinanceDetail> {

    /**
     * 分页查询
     *
     * @param page
     * @param shopFinanceDetailQO
     * @return
     */
    IPage<ShopFinanceDetailPageVO> queryPage(IPage<ShopFinanceDetailPageVO> page, @Param("shopFinanceDetail") ShopFinanceDetailQO shopFinanceDetailQO);

    /**
     * 查询明细
     *
     * @param shopFinanceDetailQO
     * @return
     */
    List<ShopFinanceDetailExcel> downloadDetail(@Param("shopFinanceDetail") ShopFinanceDetailQO shopFinanceDetailQO);
}
