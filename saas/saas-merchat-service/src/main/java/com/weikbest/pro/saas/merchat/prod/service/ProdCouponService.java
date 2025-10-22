package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCoupon;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdCouponDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdCouponQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdCouponVO;

import java.util.List;

/**
 * <p>
 * 广告商品优惠劵拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface ProdCouponService extends IService<ProdCoupon> {

    /**
     * 新增数据
     *
     * @param prodId
     * @param prodCouponDTO prodCouponDTO
     * @return 新增结果
     */
    boolean insert(Long prodId, ProdCouponDTO prodCouponDTO);

    /**
     * 更新数据
     *
     * @param prodId        ID
     * @param prodCouponDTO prodCouponDTO
     * @return 更新结果
     */
    boolean updateById(Long prodId, ProdCouponDTO prodCouponDTO);

    /**
     * 根据商品ID查询和优惠券类型查询
     *
     * @param prodId
     * @param type
     * @return
     */
    ProdCoupon findByProdIdAndType(Long prodId, String type);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdCoupon findById(Long id);

    /**
     * 分页查询
     *
     * @param prodCouponQO
     * @param pageReq
     * @return
     */
    IPage<ProdCoupon> queryPage(ProdCouponQO prodCouponQO, PageReq pageReq);

    /**
     * 新增或更新商品回流优惠券信息
     *
     * @param prodId
     * @param prodCouponDTO
     * @return
     */
    boolean saveOrUpdateProdCouponDTO(Long prodId, ProdCouponDTO prodCouponDTO);

    /**
     * 根据商品ID查询
     *
     * @param prodId
     * @return
     */
    List<ProdCouponVO> queryProdCouponVOById(Long prodId);
}
