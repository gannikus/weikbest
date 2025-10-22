package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.shop.entity.ShopStatementDetail;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopStatementDetailDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopStatementDetailQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopStatementDetailPageVO;

import java.util.Map;

/**
 * <p>
 * 店铺对账单明细表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ShopStatementDetailService extends IService<ShopStatementDetail> {

    /**
     * 新增数据
     *
     * @param shopStatementDetailDTO shopStatementDetailDTO
     * @return 新增结果
     */
    boolean insert(ShopStatementDetailDTO shopStatementDetailDTO);

    /**
     * 更新数据
     *
     * @param id                     ID
     * @param shopStatementDetailDTO shopStatementDetailDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopStatementDetailDTO shopStatementDetailDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ShopStatementDetail findById(Long id);

    /**
     * 分页查询
     *
     * @param shopStatementDetailQO
     * @param pageReq
     * @return
     */
    IPage<ShopStatementDetailPageVO> queryPage(ShopStatementDetailQO shopStatementDetailQO, PageReq pageReq);

    /**
     * 查询交易类型
     *
     * @return
     */
    Map<String, String> queryStatementTypeGroup();
}
