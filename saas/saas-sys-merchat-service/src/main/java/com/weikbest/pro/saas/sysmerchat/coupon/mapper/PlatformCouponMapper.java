package com.weikbest.pro.saas.sysmerchat.coupon.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponPageVO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.qo.PlatformCouponQO;
import org.apache.ibatis.annotations.Param;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/15
 */
public interface PlatformCouponMapper {

    /**
     * 分页查询列表数据
     *
     * @param page
     * @param platformCouponQO
     * @return
     */
    IPage<CouponPageVO> queryPage(IPage<CouponPageVO> page, @Param("platformCoupon") PlatformCouponQO platformCouponQO);
}


