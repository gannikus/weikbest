package com.weikbest.pro.saas.sysmerchat.coupon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CustCouponRestrictQO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponPageVO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CustCouponRestrictPageVO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.qo.PlatformCouponQO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponDetailVO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponShowVO;

/**
 * <p>
 * 平台优惠券表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
public interface PlatformCouponService extends IService<Coupon> {

    /**
     * 新增数据
     *
     * @param platformCouponDTO platformCouponDTO
     * @return 新增结果
     */
    boolean insert(PlatformCouponDTO platformCouponDTO);

    /**
     * 更新数据
     *
     * @param id                ID
     * @param platformCouponDTO platformCouponDTO
     * @return 更新结果
     */
    boolean updateById(Long id, PlatformCouponDTO platformCouponDTO);


    /**
     * 分页查询
     *
     * @param platformCouponQO
     * @param pageReq
     * @return
     */
    IPage<CouponPageVO> queryPageVO(PlatformCouponQO platformCouponQO, PageReq pageReq);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    PlatformCouponDetailVO findDetailVOById(Long id);

    /**
     * 分页查询平台优惠券领取数据
     *
     * @param id
     * @param custCouponRestrictQO
     * @param pageReq
     * @return
     */
    IPage<CustCouponRestrictPageVO> queryCustCouponRestrictPage(Long id, CustCouponRestrictQO custCouponRestrictQO, PageReq pageReq);
}
