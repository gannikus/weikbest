package com.weikbest.pro.saas.merchat.coupon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.entity.CustCouponRestrict;
import com.weikbest.pro.saas.merchat.coupon.module.dto.AppCustCouponRestrictDTO;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CustCouponRestrictDTO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CustCouponRestrictQO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CustCouponRestrictPageVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户领用优惠券表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
public interface CustCouponRestrictService extends IService<CustCouponRestrict> {

    /**
     * 新增数据
     *
     * @param custCouponRestrictDTO custCouponRestrictDTO
     * @return 新增结果
     */
    boolean insert(CustCouponRestrictDTO custCouponRestrictDTO);

    /**
     * 更新数据
     *
     * @param id                    ID
     * @param custCouponRestrictDTO custCouponRestrictDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CustCouponRestrictDTO custCouponRestrictDTO);

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
    CustCouponRestrict findById(Long id);

    /**
     * 分页查询
     *
     * @param custCouponRestrictQO
     * @param pageReq
     * @return
     */
    IPage<CustCouponRestrict> queryPage(CustCouponRestrictQO custCouponRestrictQO, PageReq pageReq);

    /**
     * 根据优惠券ID分组查询
     *
     * @param couponIdList
     * @return
     */
    Map<Long, List<CustCouponRestrict>> queryGroupByCouponId(List<Long> couponIdList);

    /**
     * 根据优惠券ID分组查询
     * 优惠券使用数据
     *
     * @param couponIdList
     * @return
     */
    Map<Long, List<CustCouponRestrict>> queryUseCouponGroupByCouponId(List<Long> couponIdList);

    /**
     * 查询优惠券信息
     *
     * @param custCouponRestrictQO
     * @param pageReq
     * @return
     */
    IPage<CustCouponRestrictPageVO> queryVOPage(CustCouponRestrictQO custCouponRestrictQO, PageReq pageReq);

    /**
     * 获取可用优惠券总数
     *
     * @param customerId
     * @return
     */
    int queryCountUseableCustCouponRestricts(Long customerId);

    /**
     * 获取我的已过期优惠券总数
     *
     * @param customerId
     * @return
     */
    int queryCountExpiredCustCouponRestricts(Long customerId);

    /**
     * 获取我的已使用优惠券总数
     *
     * @param customerId
     * @return
     */
    int queryCountUsedCustCouponRestricts(Long customerId);

    /**
     * 获取当前商品下我领取的可用优惠券总数
     *
     * @param paramMap
     * @return
     */
    int queryCountUseableCustCouponProds(Map<String, Object> paramMap);

    /**
     * 获取当前商品下我领取的不可用优惠券总数
     *
     * @param paramMap
     * @return
     */
    int queryCountUnuseableCustCouponProds(Map<String, Object> paramMap);

    /**
     * 获取可用优惠券列表，分页
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUseableCustCouponRestricts(Map<String, Object> paramMap);

    /**
     * 获取已过期优惠券列表，分页
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> queryExpiredCustCouponRestricts(Map<String, Object> paramMap);

    /**
     * 获取已使用优惠券列表，分页
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUsedCustCouponRestricts(Map<String, Object> paramMap);

    /**
     * 获取当前商品下我领取的可用优惠券信息
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUseableCustCouponProds(Map<String, Object> paramMap);

    /**
     * 获取当前商品下我领取的可用优惠券信息
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> listUseableCustCouponProds(Map<String, Object> paramMap);

    /**
     * 获取当前商品下我领取的不可用优惠券信息
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUnuseableCustCouponProds(Map<String, Object> paramMap);

    /**
     * 根据领券ID生成初始领券状态并保存
     * @param id
     * @return
     */
    boolean saveCustCouponRestrictType(Long id);

    /**
     * 设置优惠券领券状态为未使用
     * @param id
     */
    void couponRestrictTypeNotUsedExecute(Long id);

    /**
     * 设置优惠券领券状态为已过期
     * @param id
     */
    void couponRestrictTypeExpiredExecute(Long id);
}
