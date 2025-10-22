package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceDetail;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopFinanceDetailDTO;
import com.weikbest.pro.saas.merchat.shop.module.excel.ShopFinanceDetailExcel;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopFinanceDetailQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopFinanceDetailPageVO;

import java.util.List;

/**
 * <p>
 * 店铺资金明细表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ShopFinanceDetailService extends IService<ShopFinanceDetail> {

    /**
     * 新增数据
     *
     * @param shopFinanceDetailDTO shopFinanceDetailDTO
     * @return 新增结果
     */
    boolean insert(ShopFinanceDetailDTO shopFinanceDetailDTO);

    /**
     * 更新数据
     *
     * @param id                   ID
     * @param shopFinanceDetailDTO shopFinanceDetailDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopFinanceDetailDTO shopFinanceDetailDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ShopFinanceDetail findById(Long id);

    /**
     * 分页查询
     *
     * @param shopFinanceDetailQO
     * @param pageReq
     * @return
     */
    IPage<ShopFinanceDetailPageVO> queryPage(ShopFinanceDetailQO shopFinanceDetailQO, PageReq pageReq);

    /**
     * 查询明细
     *
     * @param shopFinanceDetailQO
     * @return
     */
    List<ShopFinanceDetailExcel> downloadDetail(ShopFinanceDetailQO shopFinanceDetailQO);
}
