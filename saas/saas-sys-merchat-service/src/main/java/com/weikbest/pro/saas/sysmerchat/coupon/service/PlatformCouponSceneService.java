package com.weikbest.pro.saas.sysmerchat.coupon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponScene;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponSceneAllDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponSceneDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponSceneGroupDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponSceneGroupVO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.qo.PlatformCouponSceneQO;

import java.util.List;

/**
 * <p>
 * 平台优惠券使用场景表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
public interface PlatformCouponSceneService extends IService<CouponScene> {

    /**
     * 批量保存
     *
     * @param platformCouponSceneGroupDTO
     * @return
     */
    boolean insertBatch(PlatformCouponSceneGroupDTO platformCouponSceneGroupDTO);

    /**
     * 保存全部优惠券场景数据
     *
     * @param platformCouponSceneAllDTO
     * @return
     */
    boolean insertAll(PlatformCouponSceneAllDTO platformCouponSceneAllDTO);

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
     * @param platformCouponSceneQO
     * @param pageReq
     * @return
     */
    IPage<CouponScene> queryPage(PlatformCouponSceneQO platformCouponSceneQO, PageReq pageReq);

    /**
     * 分组查询
     *
     * @return
     */
    List<PlatformCouponSceneGroupVO> queryPlatformCouponSceneGroupVOList();


}
