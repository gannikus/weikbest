package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCombo;
import com.weikbest.pro.saas.merchat.prod.entity.ProdPrice;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdPriceDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdPriceQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdPriceVO;

/**
 * <p>
 * 商品价格信息拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdPriceService extends IService<ProdPrice> {

    /**
     * 新增数据
     *
     * @param prodPriceDTO prodPriceDTO
     * @return 新增结果
     */
    boolean insert(ProdPriceDTO prodPriceDTO);

    /**
     * 更新数据
     *
     * @param id           ID
     * @param prodPriceDTO prodPriceDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdPriceDTO prodPriceDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdPrice findById(Long id);

    /**
     * 分页查询
     *
     * @param prodPriceQO
     * @param pageReq
     * @return
     */
    IPage<ProdPrice> queryPage(ProdPriceQO prodPriceQO, PageReq pageReq);

    /**
     * 商品保存时，根据最低价格套餐生成商品价格信息
     *
     * @param prodId
     * @param prodCombo
     * @param prodPriceDTO
     */
    void saveOrUpdateByProdComboWithProd(Long prodId, ProdCombo prodCombo, ProdPriceDTO prodPriceDTO);

    /**
     * 根据商品ID查询
     *
     * @param id
     * @return
     */
    ProdPriceVO getVOById(Long id);
}
