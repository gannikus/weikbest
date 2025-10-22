package com.weikbest.pro.saas.merchat.coupon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponScene;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponSceneDTO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponSceneQO;

import java.util.List;

/**
 * <p>
 * 优惠券使用场景表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
public interface CouponSceneService extends IService<CouponScene> {

    /**
     * 新增数据
     *
     * @param couponSceneDTO couponSceneDTO
     * @return 新增结果
     */
    boolean insert(CouponSceneDTO couponSceneDTO);

    /**
     * 更新数据
     *
     * @param id             ID
     * @param couponSceneDTO couponSceneDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CouponSceneDTO couponSceneDTO);

    /**
     * 删除数据
     *
     * @param ids ID集合
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    CouponScene findById(Long id);

    /**
     * 分页查询
     *
     * @param couponSceneQO
     * @param pageReq
     * @return
     */
    IPage<CouponScene> queryPage(CouponSceneQO couponSceneQO, PageReq pageReq);
}
