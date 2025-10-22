package com.weikbest.pro.saas.sysmerchat.coupon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponScene;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponSceneConfig;
import com.weikbest.pro.saas.merchat.coupon.mapper.CouponSceneMapper;
import com.weikbest.pro.saas.merchat.coupon.service.CouponSceneConfigService;
import com.weikbest.pro.saas.merchat.coupon.service.CouponSceneService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponSceneAllDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponSceneGroupDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.mapstruct.PlatformCouponSceneMapStruct;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponSceneDetailVO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponSceneGroupVO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.qo.PlatformCouponSceneQO;
import com.weikbest.pro.saas.sysmerchat.coupon.service.PlatformCouponSceneService;
import com.weikbest.pro.saas.sysmerchat.coupon.service.PlatformCouponService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 平台优惠券使用场景表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Service
public class PlatformCouponSceneServiceImpl extends ServiceImpl<CouponSceneMapper, CouponScene> implements PlatformCouponSceneService {


    @Resource
    private PlatformCouponSceneService platformCouponSceneService;

    @Resource
    private PlatformCouponService platformCouponService;

    @Resource
    private CouponSceneConfigService couponSceneConfigService;

    @Resource
    private CouponSceneService couponSceneService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertBatch(PlatformCouponSceneGroupDTO platformCouponSceneGroupDTO) {
        // 保存或更新优惠券场景图片配置记录
        CouponSceneConfig couponSceneConfig = couponSceneConfigService.getOrCreateCouponSceneConfigByCouponSceneType(platformCouponSceneGroupDTO.getCouponSceneType());
        couponSceneConfig.setCouponSceneType(platformCouponSceneGroupDTO.getCouponSceneType());
        couponSceneConfig.setPlatformCouponReceiveOpenUrl(platformCouponSceneGroupDTO.getPlatformCouponReceiveOpenUrl());
        couponSceneConfig.setPlatformCouponReceiveBannerUrl(platformCouponSceneGroupDTO.getPlatformCouponReceiveBannerUrl());
        couponSceneConfigService.saveOrUpdate(couponSceneConfig);

        Long id = couponSceneConfig.getId();

        //设置前先删除垃圾数据
        QueryWrapper<CouponScene> couponSceneQueryWrapper = new QueryWrapper<>();
        couponSceneQueryWrapper.eq(CouponScene.ID,id);
        couponSceneService.remove(couponSceneQueryWrapper);

        // 保存优惠券场景记录
        String couponSceneType = platformCouponSceneGroupDTO.getCouponSceneType();
        List<CouponScene> couponSceneList = platformCouponSceneGroupDTO.getPlatformCouponIdList().stream().map(couponId -> {
            CouponScene couponScene = this.getOne(new QueryWrapper<CouponScene>().eq(CouponScene.ID, id).eq(CouponScene.COUPON_ID, couponId).eq(CouponScene.COUPON_SCENE_TYPE, couponSceneType));
            if(ObjectUtil.isNull(couponScene)) {
                couponScene = new CouponScene();
                couponScene.setId(id);
                couponScene.setCouponId(couponId);
                couponScene.setCouponSceneType(couponSceneType);
            }
            return couponScene;
        }).collect(Collectors.toList());

        return this.saveOrUpdateBatch(couponSceneList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertAll(PlatformCouponSceneAllDTO platformCouponSceneAllDTO) {

        if(CollectionUtil.isNotEmpty(platformCouponSceneAllDTO.getPlatformCouponId1List())) {
            PlatformCouponSceneGroupDTO platformCouponSceneGroupDTO = new PlatformCouponSceneGroupDTO();
            platformCouponSceneGroupDTO.setPlatformCouponIdList(platformCouponSceneAllDTO.getPlatformCouponId1List());
            platformCouponSceneGroupDTO.setCouponSceneType(DictConstant.CouponSceneType.TYPE_1.getCode());
            platformCouponSceneService.insertBatch(platformCouponSceneGroupDTO);
        }

        if(CollectionUtil.isNotEmpty(platformCouponSceneAllDTO.getPlatformCouponId2List())) {
            PlatformCouponSceneGroupDTO platformCouponSceneGroupDTO = new PlatformCouponSceneGroupDTO();
            platformCouponSceneGroupDTO.setPlatformCouponIdList(platformCouponSceneAllDTO.getPlatformCouponId2List());
            platformCouponSceneGroupDTO.setCouponSceneType(DictConstant.CouponSceneType.TYPE_2.getCode());
            platformCouponSceneGroupDTO.setPlatformCouponReceiveBannerUrl(platformCouponSceneGroupDTO.getPlatformCouponReceiveBannerUrl());
            platformCouponSceneGroupDTO.setPlatformCouponReceiveOpenUrl(platformCouponSceneGroupDTO.getPlatformCouponReceiveOpenUrl());
            platformCouponSceneService.insertBatch(platformCouponSceneGroupDTO);
        }

        return true;
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public CouponScene findById(Long id) {
        CouponScene couponScene = this.getById(id);
        if (ObjectUtil.isNull(couponScene)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return couponScene;
    }

    @Override
    public IPage<CouponScene> queryPage(PlatformCouponSceneQO platformCouponSceneQO, PageReq pageReq) {
        QueryWrapper<CouponScene> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(platformCouponSceneQO.getCouponSceneType())) {
            wrapper.eq(CouponScene.COUPON_SCENE_TYPE, platformCouponSceneQO.getCouponSceneType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<PlatformCouponSceneGroupVO> queryPlatformCouponSceneGroupVOList() {
        List<CouponSceneConfig> couponSceneConfigList = couponSceneConfigService.list();

        List<CouponScene> couponSceneList = this.list();
        Map<String, List<CouponScene>> couponSceneListGroupMap = couponSceneList.stream().collect(Collectors.groupingBy(CouponScene::getCouponSceneType));

        return couponSceneConfigList.stream().map(couponSceneConfig -> {
            List<CouponScene> value = couponSceneListGroupMap.get(couponSceneConfig.getCouponSceneType());

            List<PlatformCouponSceneDetailVO> platformCouponSceneDetailVOList = new ArrayList<>();
            if(CollectionUtil.isNotEmpty(value)) {
                List<Long> platformCouponIdList = value.stream().map(CouponScene::getCouponId).collect(Collectors.toList());
                List<Coupon> platformCouponList = platformCouponService.listByIds(platformCouponIdList);
                Map<Long, Coupon> platformCouponMap = platformCouponList.stream().collect(Collectors.toMap(Coupon::getId, platformCoupon -> platformCoupon));

                platformCouponSceneDetailVOList.addAll(value.stream().map(couponScene -> {
                    PlatformCouponSceneDetailVO platformCouponSceneDetailVO = PlatformCouponSceneMapStruct.INSTANCE.converToDetailVO(couponScene);
                    platformCouponSceneDetailVO.setName(platformCouponMap.get(couponScene.getCouponId()).getName());
                    return platformCouponSceneDetailVO;
                }).collect(Collectors.toList()));
            }

            PlatformCouponSceneGroupVO platformCouponSceneGroupVO = new PlatformCouponSceneGroupVO();
            platformCouponSceneGroupVO.setCouponSceneType(couponSceneConfig.getCouponSceneType());
            platformCouponSceneGroupVO.setPlatformCouponReceiveOpenUrl(couponSceneConfig.getPlatformCouponReceiveOpenUrl());
            platformCouponSceneGroupVO.setPlatformCouponReceiveBannerUrl(couponSceneConfig.getPlatformCouponReceiveBannerUrl());
            platformCouponSceneGroupVO.setPlatformCouponSceneDetailVOList(platformCouponSceneDetailVOList);
            return platformCouponSceneGroupVO;
        }).collect(Collectors.toList());
    }
}
