package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCouponRelate;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdCouponDTO;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdCouponRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdCouponRelateQO;

import java.util.List;

/**
 * <p>
 * 商品与优惠券绑定拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdCouponRelateService extends IService<ProdCouponRelate> {

    /**
     * 新增数据
     *
     * @param prodCouponRelateDTO prodCouponRelateDTO
     * @return 新增结果
     */
    boolean insert(ProdCouponRelateDTO prodCouponRelateDTO);

    /**
     * 更新数据
     *
     * @param entryId             ID
     * @param prodCouponRelateDTO prodCouponRelateDTO
     * @return 更新结果
     */
    boolean updateById(Long entryId, ProdCouponRelateDTO prodCouponRelateDTO);

    /**
     * 根据ID查询
     *
     * @param entryId ID
     * @return 查询结果
     */
    ProdCouponRelate findById(Long entryId);

    /**
     * 分页查询
     *
     * @param prodCouponRelateQO
     * @param pageReq
     * @return
     */
    IPage<ProdCouponRelate> queryPage(ProdCouponRelateQO prodCouponRelateQO, PageReq pageReq);

    /**
     * 保存商品时，保存商品关联优惠券信息
     *
     * @param prodId
     * @param prodCouponRelateDTOList
     */
//    void insertBatchWithProd(Long prodId, List<ProdCouponRelateDTO> prodCouponRelateDTOList);

    /**
     * 根据商品ID和优惠券类型类型查询商品优惠券信息
     *
     * @param prodId
     * @param couponType
     * @return
     */
    List<DictEntry> queryDictEntry(Long prodId, String couponType);

    /**
     * 根据商品ID更新商品回流优惠券关联信息
     *
     * @param prodId
     * @param entryId
     * @param prodCouponDTO
     */
    void updateProdCouponRelate(Long prodId, Long entryId, ProdCouponDTO prodCouponDTO);
}
