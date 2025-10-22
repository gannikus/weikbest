package com.weikbest.pro.saas.merchat.coupon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponSceneConfig;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponSceneConfigDTO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponSceneConfigQO;

import java.util.List;

/**
 * <p>
 * 优惠券使用场景配置表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-15
 */
public interface CouponSceneConfigService extends IService<CouponSceneConfig> {

    /**
     * 新增数据
     *
     * @param couponSceneConfigDTO couponSceneConfigDTO
     * @return 新增结果
     */
    boolean insert(CouponSceneConfigDTO couponSceneConfigDTO);

    /**
     * 更新数据
     *
     * @param id                   ID
     * @param couponSceneConfigDTO couponSceneConfigDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CouponSceneConfigDTO couponSceneConfigDTO);

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
    CouponSceneConfig findById(Long id);

    /**
     * 分页查询
     *
     * @param couponSceneConfigQO
     * @param pageReq
     * @return
     */
    IPage<CouponSceneConfig> queryPage(CouponSceneConfigQO couponSceneConfigQO, PageReq pageReq);

    /**
     * 根据场景类型查询
     *
     * @param couponSceneType
     * @return
     */
    CouponSceneConfig getOrCreateCouponSceneConfigByCouponSceneType(String couponSceneType);
}
