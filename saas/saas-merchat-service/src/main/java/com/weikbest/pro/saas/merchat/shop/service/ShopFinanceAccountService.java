package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceAccount;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopFinanceAccountDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopFinanceAccountQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopAccountSettleAmountVO;

/**
 * <p>
 * 店铺资金账户表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ShopFinanceAccountService extends IService<ShopFinanceAccount> {

    /**
     * 新增数据
     *
     * @param shopFinanceAccountDTO shopFinanceAccountDTO
     * @return 新增结果
     */
    boolean insert(ShopFinanceAccountDTO shopFinanceAccountDTO);

    /**
     * 更新数据
     *
     * @param id                    ID
     * @param shopFinanceAccountDTO shopFinanceAccountDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopFinanceAccountDTO shopFinanceAccountDTO);


    /**
     * 新增数据
     *
     * @param shopId shopId
     * @return 新增结果
     */
    boolean insertWithShop(Long shopId);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ShopFinanceAccount findById(Long id);

    /**
     * 分页查询
     *
     * @param shopFinanceAccountQO
     * @param pageReq
     * @return
     */
    IPage<ShopFinanceAccount> queryPage(ShopFinanceAccountQO shopFinanceAccountQO, PageReq pageReq);

    /**
     * 查询待结算金额
     *
     * @param businessId 商户ID
     * @return
     */
    ShopAccountSettleAmountVO findShopAccountSettleAmount(Long businessId);

    /**
     * 根据店铺ID查询
     *
     * @param shopId
     * @return
     */
    ShopFinanceAccount findByShopId(Long shopId);
}
