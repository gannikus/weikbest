package com.weikbest.pro.saas.merchat.coupon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponCustomerEntry;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponCustomerEntryDTO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponCustomerEntryQO;

import java.util.List;

/**
 * <p>
 * 优惠券与适用客户拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
public interface CouponCustomerEntryService extends IService<CouponCustomerEntry> {

    /**
     * 新增数据
     *
     * @param couponCustomerEntryDTO couponCustomerEntryDTO
     * @return 新增结果
     */
    boolean insert(CouponCustomerEntryDTO couponCustomerEntryDTO);

    /**
     * 更新数据
     *
     * @param id                     ID
     * @param couponCustomerEntryDTO couponCustomerEntryDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CouponCustomerEntryDTO couponCustomerEntryDTO);

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
    CouponCustomerEntry findById(Long id);

    /**
     * 分页查询
     *
     * @param couponCustomerEntryQO
     * @param pageReq
     * @return
     */
    IPage<CouponCustomerEntry> queryPage(CouponCustomerEntryQO couponCustomerEntryQO, PageReq pageReq);

    /**
     * 优惠券关联客户
     *
     * @param id
     * @param shopCouponType
     * @param customerPhoneList
     */
    void saveBatchWithCouponId(Long id, String shopCouponType, List<String> customerPhoneList);

    /**
     * 根据优惠券ID查询
     *
     * @param id
     * @return
     */
    List<CouponCustomerEntry> queryByCouponId(Long id);
}
