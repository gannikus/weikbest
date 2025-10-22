package com.weikbest.pro.saas.sysmerchat.coupon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.bean.marketing.BusiFavorStocksCreateRequest;
import com.github.binarywang.wxpay.bean.marketing.BusiFavorStocksCreateResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingBusiFavorService;
import com.github.binarywang.wxpay.service.MarketingMediaService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.oss.AliyunOssService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponProdEntry;
import com.weikbest.pro.saas.merchat.coupon.entity.CustCouponRestrict;
import com.weikbest.pro.saas.merchat.coupon.mapper.CouponMapper;
import com.weikbest.pro.saas.merchat.coupon.module.mapstruct.CouponMapStruct;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CustCouponRestrictQO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponPageVO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CustCouponRestrictPageVO;
import com.weikbest.pro.saas.merchat.coupon.service.CouponProdEntryService;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.merchat.coupon.service.CustCouponRestrictService;
import com.weikbest.pro.saas.merchat.coupon.util.CouponUtil;
import com.weikbest.pro.saas.merchat.prod.entity.Prod;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdShowDetailVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.Site;
import com.weikbest.pro.saas.sys.param.service.PayConfigService;
import com.weikbest.pro.saas.sys.param.service.SiteService;
import com.weikbest.pro.saas.sysmerchat.coupon.mapper.PlatformCouponMapper;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.mapstruct.PlatformCouponMapStruct;
import com.weikbest.pro.saas.sysmerchat.coupon.module.qo.PlatformCouponQO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponDetailVO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponShowVO;
import com.weikbest.pro.saas.sysmerchat.coupon.service.PlatformCouponService;
import com.weikbest.pro.saas.sys.param.entity.ProdStandard;
import com.weikbest.pro.saas.sys.param.service.ProdStandardService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import com.weikbest.pro.saas.sys.param.util.ThirdConfigUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 平台优惠券表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Service
public class PlatformCouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements PlatformCouponService {

    @Resource
    private CouponService couponService;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private PayConfigService payConfigService;

    @Resource
    private ProdService prodService;

    @Resource
    private ProdStandardService prodStandardService;

    @Resource
    private SiteService siteService;

    @Resource
    private CouponProdEntryService couponProdEntryService;

    @Resource
    private CustCouponRestrictService custCouponRestrictService;

    @Resource
    private PlatformCouponMapper platformCouponMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(PlatformCouponDTO platformCouponDTO) {
        Coupon coupon = PlatformCouponMapStruct.INSTANCE.converToEntity(platformCouponDTO);
        // 其他值赋值
        setValue(coupon);

        WxPayService wxPayService = payConfigService.findWxPayServiceByPayConfigType(DictConstant.PayConfigType.TYPE_3.getCode());

        if (StrUtil.isNotBlank(coupon.getCouponImageOssurl())) {
            // 卡包详情图片不为空，上传到微信图片
            AliyunOssService aliyunOssService = thirdConfigService.aliyunOssService();

            // 调用微信接口 上传图片
            MarketingMediaService marketingMediaService = wxPayService.getMarketingMediaService();
            String couponImageOssurl = coupon.getCouponImageOssurl();
            coupon.setCouponImageUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, couponImageOssurl));
        }

        String outRequestNo = OrderUtil.getNoncestr();
        ProdStandard prodStandard = prodStandardService.findProdStandard();
        Site site = siteService.findSite();
        try {
            MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();

            BusiFavorStocksCreateRequest request = CouponUtil.buildBusiFavorStocksCreateRequest(wxPayService, coupon, outRequestNo, prodStandard, site);
            // 调用微信接口创建商家券
            BusiFavorStocksCreateResult busiFavorStocksCreateResult = marketingBusiFavorService.createBusiFavorStocksV3(request);

            // 赋值
            coupon.setBindMchId(wxPayService.getConfig().getMchId());
            coupon.setOutRequestNo(outRequestNo);
            coupon.setStockId(busiFavorStocksCreateResult.getStockId());
            coupon.setCreateTime(busiFavorStocksCreateResult.getCreateTime());

            coupon.setMerchatLogoOssUrl(prodStandard.getPlatformMerchatLogoOssurl());
            coupon.setMerchatLogoUrl(prodStandard.getPlatformMerchatLogoUrl());
        } catch (WxPayException e) {
            log.error("微信商家券生成失败！", e);
            throw new WeikbestException(e);
        }

        boolean save = this.save(coupon);

        if(StrUtil.equals(platformCouponDTO.getCouponProdType(), DictConstant.CouponProdType.special_prod.getCode())) {
            // 添加商品关联
            couponProdEntryService.saveBatchWithCouponId(coupon.getId(), coupon.getCouponType(), platformCouponDTO.getProdIdList());
        }

        return save;
    }



    private void setValue(Coupon coupon) {
        coupon.setCouponType(DictConstant.CouponType.TYPE_3.getCode());
        if (StrUtil.isBlank(coupon.getCouponThemeType())) {
            // 优惠券样式默认为默认优惠券
            coupon.setCouponThemeType(DictConstant.CouponThemeType.default_img.getCode());
        }
        if (coupon.getCouponUsePrice().compareTo(BigDecimal.ZERO) > WeikbestConstant.ZERO_INT) {
            // 无使用门槛
            coupon.setCouponUseType(DictConstant.CouponUseType.order_amount.getCode());
        } else {
            // 有使用门槛
            coupon.setCouponUseType(DictConstant.CouponUseType.none.getCode());
        }
        if (ObjectUtil.equal(coupon.getDelayEnableDay(), WeikbestConstant.ZERO_INT)) {
            // 领券后立即生效
            coupon.setEnableType(DictConstant.CouponEnableType.now.getCode());
        } else {
            // 延迟生效
            coupon.setEnableType(DictConstant.CouponEnableType.delay.getCode());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, PlatformCouponDTO platformCouponDTO) {
        Coupon coupon = couponService.findById(id);

        WxPayService wxPayService = payConfigService.findWxPayServiceByPayConfigType(DictConstant.PayConfigType.TYPE_3.getCode());
        if (!StrUtil.equals(coupon.getCouponImageOssurl(), platformCouponDTO.getCouponImageOssurl())) {
            // 卡包详情图片不为空，上传到微信图片
            AliyunOssService aliyunOssService = thirdConfigService.aliyunOssService();

            // 调用微信接口 上传图片
            MarketingMediaService marketingMediaService = wxPayService.getMarketingMediaService();
            String couponImageOssurl = platformCouponDTO.getCouponImageOssurl();
            platformCouponDTO.setCouponImageUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, couponImageOssurl));
        }

        PlatformCouponMapStruct.INSTANCE.copyProperties(platformCouponDTO, coupon);
        coupon.setId(id);
        // 其他值赋值
        setValue(coupon);

        // 调用此接口前置条件是对接的商家劵的有效期结束时间前的优惠券都可以修改
//        if(DateUtil.compare(DateUtil.date(), coupon.getGetEndTime()) > WeikbestConstant.ZERO_INT) {
            ProdStandard prodStandard = prodStandardService.findProdStandard();
            Site site = siteService.findSite();
            try {
                MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();
                BusiFavorStocksCreateRequest request = CouponUtil.buildUpdateBusiFavorStocksCreateRequest(coupon, coupon.getOutRequestNo(), prodStandard, site);
                // 调用微信接口创建商家券（更新商家券）
                marketingBusiFavorService.updateBusiFavorStocksV3(coupon.getStockId(), request);
            } catch (WxPayException e) {
                log.error(String.format("微信商家券更新失败！stockId: %s", coupon.getStockId()), e);
                throw new WeikbestException(e);
            }
//        }

        if(StrUtil.equals(platformCouponDTO.getCouponProdType(), DictConstant.CouponProdType.special_prod.getCode())) {
            // 添加商品关联
            couponProdEntryService.saveBatchWithCouponId(id, coupon.getCouponType(), platformCouponDTO.getProdIdList());
        }

        return this.updateById(coupon);
    }

    @Override
    public IPage<CouponPageVO> queryPageVO(PlatformCouponQO platformCouponQO, PageReq pageReq) {
        IPage<CouponPageVO> page = platformCouponMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), platformCouponQO);

        // 查询优惠券其他汇总信息
        List<Long> couponIdList = page.getRecords().stream().map(CouponPageVO::getId).collect(Collectors.toList());
        Map<Long, List<CustCouponRestrict>> custCouponRestrictGroupMap = custCouponRestrictService.queryGroupByCouponId(couponIdList);
        // 优惠券使用张数
        Map<Long, List<CustCouponRestrict>> custUseCouponRestrictGroupMap = custCouponRestrictService.queryUseCouponGroupByCouponId(couponIdList);

        return page.convert(couponPageVO -> {
            List<CustCouponRestrict> custUseCouponRestrictList = custUseCouponRestrictGroupMap.get(couponPageVO.getId());
            couponPageVO.setUseTotal(CollectionUtil.isNotEmpty(custUseCouponRestrictList) ? custUseCouponRestrictList.size() : WeikbestConstant.ZERO_INT);

            List<CustCouponRestrict> custCouponRestrictList = custCouponRestrictGroupMap.get(couponPageVO.getId());
            couponPageVO.setRestrictTotal(CollectionUtil.isNotEmpty(custCouponRestrictList) ? custCouponRestrictList.size() : WeikbestConstant.ZERO_INT);
            return couponPageVO;
        });
    }

    @Override
    public PlatformCouponDetailVO findDetailVOById(Long id) {
        Coupon coupon = couponService.findById(id);

        PlatformCouponDetailVO platformCouponDetailVO = PlatformCouponMapStruct.INSTANCE.converToDetailVO(coupon);
        if(StrUtil.equals(platformCouponDetailVO.getCouponProdType(), DictConstant.CouponProdType.special_prod.getCode())) {
            // 添加商品关联
            List<CouponProdEntry> platformCouponProdEntryList = couponProdEntryService.queryByCouponId(id);
            List<Long> prodIdList = platformCouponProdEntryList.stream().map(couponProdEntry -> couponProdEntry.getProdId()).collect(Collectors.toList());
            List<ProdShowDetailVO> prodList = prodService.queryDetailVOById(prodIdList);
            platformCouponDetailVO.setProdList(prodList);
        }

        return platformCouponDetailVO;
    }

    @Override
    public IPage<CustCouponRestrictPageVO> queryCustCouponRestrictPage(Long id, CustCouponRestrictQO custCouponRestrictQO, PageReq pageReq) {
        // 强制查询平台优惠券
        custCouponRestrictQO.setCouponType(DictConstant.CouponType.TYPE_3.getCode());
        custCouponRestrictQO.setCouponId(id);
        return couponService.queryCustCouponRestrictPage(custCouponRestrictQO, pageReq);
    }
}
