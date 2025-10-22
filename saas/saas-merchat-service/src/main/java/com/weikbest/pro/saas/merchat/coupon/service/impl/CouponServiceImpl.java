package com.weikbest.pro.saas.merchat.coupon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
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
import com.google.common.collect.Lists;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.constant.ImgBasicConstant;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.oss.AliyunOssService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.merchat.coupon.delaytaskprocess.CouponEventEndDelayTaskProcess;
import com.weikbest.pro.saas.merchat.coupon.delaytaskprocess.CouponEventStartDelayTaskProcess;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponCustomerEntry;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponProdEntry;
import com.weikbest.pro.saas.merchat.coupon.entity.CustCouponRestrict;
import com.weikbest.pro.saas.merchat.coupon.mapper.CouponMapper;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopPromptlyCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopRefluxCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.mapstruct.CouponMapStruct;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponProdEntryQO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponQO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CustCouponRestrictQO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.*;
import com.weikbest.pro.saas.merchat.coupon.service.CouponCustomerEntryService;
import com.weikbest.pro.saas.merchat.coupon.service.CouponProdEntryService;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.merchat.coupon.service.CustCouponRestrictService;
import com.weikbest.pro.saas.merchat.coupon.util.CouponUtil;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAppletRelate;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdShowDetailVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdAppletRelateService;
import com.weikbest.pro.saas.merchat.prod.service.ProdService;
import com.weikbest.pro.saas.merchat.prod.service.ProdThemeService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.ProdStandard;
import com.weikbest.pro.saas.sys.param.entity.Site;
import com.weikbest.pro.saas.sys.param.entity.ThirdConfig;
import com.weikbest.pro.saas.sys.param.service.PayConfigService;
import com.weikbest.pro.saas.sys.param.service.ProdStandardService;
import com.weikbest.pro.saas.sys.param.service.SiteService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import com.weikbest.pro.saas.sys.param.util.ThirdConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 优惠券表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
@Slf4j
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private PayConfigService payConfigService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private ProdStandardService prodStandardService;

    @Resource
    private SiteService siteService;

    @Resource
    private ProdService prodService;

    @Resource
    private ProdAppletRelateService prodAppletRelateService;

    @Resource
    private ProdThemeService prodThemeService;

    @Resource
    private CouponProdEntryService couponProdEntryService;

    @Resource
    private CouponCustomerEntryService couponCustomerEntryService;

    @Resource
    private CustCouponRestrictService custCouponRestrictService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private CouponEventStartDelayTaskProcess couponEventStartDelayTaskProcess;

    @Resource
    private CouponEventEndDelayTaskProcess couponEventEndDelayTaskProcess;

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public Coupon findById(Long id) {
        Coupon coupon = this.getById(id);
        if (ObjectUtil.isNull(coupon)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return coupon;
    }

    @Override
    public IPage<Coupon> queryPage(CouponQO couponQO, PageReq pageReq) {
        QueryWrapper<Coupon> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(couponQO.getName())) {
            wrapper.eq(Coupon.NAME, couponQO.getName());
        }
        if (StrUtil.isNotBlank(couponQO.getCouponType())) {
            wrapper.eq(Coupon.COUPON_TYPE, couponQO.getCouponType());
        }
        if (StrUtil.isNotBlank(couponQO.getCouponStatus())) {
            wrapper.eq(Coupon.COUPON_STATUS, couponQO.getCouponStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertShopRefluxCoupon(ShopRefluxCouponDTO shopRefluxCouponDTO) {
        //如果传过来的卡包详情图没有值,则设置一个默认值
        if (StringUtils.isBlank(shopRefluxCouponDTO.getCouponImageOssurl())){
            ThirdConfig thirdConfig = thirdConfigService.findThirdConfig(true);
            if (ImgBasicConstant.CY_ALIYUN_OSS_FILE_BUCKETNAME.equals(thirdConfig.getAliyunOssFileBucketname())){
                shopRefluxCouponDTO.setCouponImageOssurl(ImgBasicConstant.CY_COUPON_IMAGE_OSSURL);
            }else if (ImgBasicConstant.HW_ALIYUN_OSS_FILE_BUCKETNAME.equals(thirdConfig.getAliyunOssFileBucketname())){
                shopRefluxCouponDTO.setCouponImageOssurl(ImgBasicConstant.HW_COUPON_IMAGE_OSSURL);
            }
        }

        Coupon coupon = CouponMapStruct.INSTANCE.converToEntity(shopRefluxCouponDTO);
        // 其他值赋值
        setValue(coupon, DictConstant.CouponType.TYPE_2.getCode());

        WxPayService wxPayService = shopThirdService.findWxPayServiceById(coupon.getShopId());

        AliyunOssService aliyunOssService = thirdConfigService.aliyunOssService();
        MarketingMediaService marketingMediaService = wxPayService.getMarketingMediaService();
        // 卡包详情图片不为空，上传到微信图片
        if (StrUtil.isNotBlank(coupon.getCouponImageOssurl())) {
            // 调用微信接口 上传图片
            String couponImageOssurl = coupon.getCouponImageOssurl();
            coupon.setCouponImageUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, couponImageOssurl));
        }
        // 获取商品卡包图标对应的图片
        String showImg = prodThemeService.findById(shopRefluxCouponDTO.getProdId()).getShowImg();
        // 调用微信接口 上传图片
        coupon.setMerchatLogoUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, showImg));
        coupon.setMerchatLogoOssUrl(showImg);
        // 获取商品链接页面对应的小程序ID
        ProdAppletRelate prodAppletRelate = prodAppletRelateService.findById(shopRefluxCouponDTO.getProdId());
        coupon.setAppId(prodAppletRelate.getAppletAppId());

        String outRequestNo = OrderUtil.getNoncestr();
        ProdStandard prodStandard = prodStandardService.findProdStandard();
        Site site = siteService.findSite();
        try {
            MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();
            // 构建微信商家券请求
            BusiFavorStocksCreateRequest request = CouponUtil.buildBusiFavorStocksCreateRequest(wxPayService, coupon, outRequestNo, prodStandard, site);
            // 调用微信接口创建商家券
            BusiFavorStocksCreateResult busiFavorStocksCreateResult = marketingBusiFavorService.createBusiFavorStocksV3(request);

            // 赋值
            coupon.setBindMchId(wxPayService.getConfig().getMchId());
            coupon.setOutRequestNo(outRequestNo);
            coupon.setStockId(busiFavorStocksCreateResult.getStockId());
            coupon.setCreateTime(busiFavorStocksCreateResult.getCreateTime());
        } catch (WxPayException e) {
            log.error(String.format("微信商家券生成失败！店铺id:%s", coupon.getShopId()), e);
            throw new WeikbestException(e);
        }

        boolean save = this.save(coupon);

        // 添加商品关联
        couponProdEntryService.saveWithCouponId(coupon.getId(), coupon.getCouponType(), shopRefluxCouponDTO.getProdId());

        return save;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateShopRefluxCouponById(Long id, ShopRefluxCouponDTO shopRefluxCouponDTO) {
        //如果传过来的卡包详情图没有值,则设置一个默认值
        if (StringUtils.isBlank(shopRefluxCouponDTO.getCouponImageOssurl())){
            ThirdConfig thirdConfig = thirdConfigService.findThirdConfig(true);
            if (ImgBasicConstant.CY_ALIYUN_OSS_FILE_BUCKETNAME.equals(thirdConfig.getAliyunOssFileBucketname())){
                shopRefluxCouponDTO.setCouponImageOssurl(ImgBasicConstant.CY_COUPON_IMAGE_OSSURL);
            }else if (ImgBasicConstant.HW_ALIYUN_OSS_FILE_BUCKETNAME.equals(thirdConfig.getAliyunOssFileBucketname())){
                shopRefluxCouponDTO.setCouponImageOssurl(ImgBasicConstant.HW_COUPON_IMAGE_OSSURL);
            }
        }

        Coupon coupon = this.findById(id);

        AliyunOssService aliyunOssService = thirdConfigService.aliyunOssService();
        WxPayService wxPayService = shopThirdService.findWxPayServiceById(shopRefluxCouponDTO.getShopId());
        MarketingMediaService marketingMediaService = wxPayService.getMarketingMediaService();
        // 卡包详情图片不为空，上传到微信图片
        if (!StrUtil.equals(coupon.getCouponImageOssurl(), shopRefluxCouponDTO.getCouponImageOssurl())) {
            // 调用微信接口 上传图片
            String couponImageOssurl = shopRefluxCouponDTO.getCouponImageOssurl();
            shopRefluxCouponDTO.setCouponImageUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, couponImageOssurl));
        }
        // 获取商品卡包图标对应的图片
        String showImg = prodThemeService.findById(shopRefluxCouponDTO.getProdId()).getShowImg();
        if(!StrUtil.equals(coupon.getMerchatLogoOssUrl(), showImg)) {
            // 调用微信接口 上传图片
            coupon.setMerchatLogoUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, showImg));
            coupon.setMerchatLogoOssUrl(showImg);
        }

        // 获取商品链接页面对应的小程序ID
        ProdAppletRelate prodAppletRelate = prodAppletRelateService.findById(shopRefluxCouponDTO.getProdId());
        coupon.setAppId(prodAppletRelate.getAppletAppId());

        // 判断是否需要再次调用微信优惠券接口
//        boolean flag = DateUtil.compare(DateUtil.date(), coupon.getGetEndTime()) > WeikbestConstant.ZERO_INT
//                || !StrUtil.equals(shopRefluxCouponDTO.getCouponUseUrl(), coupon.getCouponUseUrl());
        boolean flag = !StrUtil.equals(shopRefluxCouponDTO.getCouponUseUrl(), coupon.getCouponUseUrl());

        // 数据转换
        CouponMapStruct.INSTANCE.copyProperties(shopRefluxCouponDTO, coupon);
        coupon.setId(id);
        // 其他值赋值
        setValue(coupon, DictConstant.CouponType.TYPE_2.getCode());

        // 调用此接口前置条件是对接的商家劵的有效期结束时间前的优惠券都可以修改
        if (flag) {
            ProdStandard prodStandard = prodStandardService.findProdStandard();
            Site site = siteService.findSite();
            try {
                MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();
                BusiFavorStocksCreateRequest request = CouponUtil.buildUpdateBusiFavorStocksCreateRequest(coupon, coupon.getOutRequestNo(), prodStandard, site);
                // 调用微信接口创建商家券（更新商家券）
                marketingBusiFavorService.updateBusiFavorStocksV3(coupon.getStockId(), request);
            } catch (WxPayException e) {
                log.error(String.format("微信商家券更新失败！shopId: %s, stockId: %s", coupon.getShopId(), coupon.getStockId()), e);
                throw new WeikbestException(e);
            }
        }

        // 添加商品关联
        couponProdEntryService.saveWithCouponId(id, coupon.getCouponType(), shopRefluxCouponDTO.getProdId());

        return this.updateById(coupon);
    }

    @Override
    public boolean insertShopPromptlyCoupon(ShopPromptlyCouponDTO shopPromptlyCouponDTO) {
        Coupon coupon = CouponMapStruct.INSTANCE.converToEntity(shopPromptlyCouponDTO);
        // 其他值赋值
        setValue(coupon, DictConstant.CouponType.TYPE_1.getCode());

        WxPayService wxPayService = shopThirdService.findWxPayServiceById(coupon.getShopId());

        AliyunOssService aliyunOssService = thirdConfigService.aliyunOssService();
        MarketingMediaService marketingMediaService = wxPayService.getMarketingMediaService();
        // 卡包详情图片不为空，上传到微信图片
        if (StrUtil.isNotBlank(coupon.getCouponImageOssurl())) {
            // 调用微信接口 上传图片
            String couponImageOssurl = coupon.getCouponImageOssurl();
            coupon.setCouponImageUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, couponImageOssurl));
        }

        String outRequestNo = OrderUtil.getNoncestr();
        ProdStandard prodStandard = prodStandardService.findProdStandard();
        Site site = siteService.findSite();
        try {
            MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();
            // 构建微信商家券请求
            BusiFavorStocksCreateRequest request = CouponUtil.buildBusiFavorStocksCreateRequest(wxPayService, coupon, outRequestNo, prodStandard, site);
            // 调用微信接口创建商家券
            BusiFavorStocksCreateResult busiFavorStocksCreateResult = marketingBusiFavorService.createBusiFavorStocksV3(request);

            // 赋值
            coupon.setBindMchId(wxPayService.getConfig().getMchId());
            coupon.setOutRequestNo(outRequestNo);
            coupon.setStockId(busiFavorStocksCreateResult.getStockId());
            coupon.setCreateTime(busiFavorStocksCreateResult.getCreateTime());

            coupon.setMerchatLogoOssUrl(prodStandard.getPromptlyMerchatLogoOssurl());
            coupon.setMerchatLogoUrl(prodStandard.getPromptlyMerchatLogoUrl());
        } catch (WxPayException e) {
            log.error(String.format("微信商家券生成失败！店铺id:%s", coupon.getShopId()), e);
            throw new WeikbestException(e);
        }

        boolean save = this.save(coupon);

        if (StrUtil.equals(shopPromptlyCouponDTO.getCouponProdType(), DictConstant.CouponProdType.special_prod.getCode())) {
            // 添加商品关联
            couponProdEntryService.saveBatchWithCouponId(coupon.getId(), coupon.getCouponType(), shopPromptlyCouponDTO.getProdIdList());
        }

        if (StrUtil.equals(shopPromptlyCouponDTO.getRestrictUserType(), DictConstant.RestrictUserType.special_customer.getCode())) {
            // 添加指定客户关联
            couponCustomerEntryService.saveBatchWithCouponId(coupon.getId(), coupon.getCouponType(), shopPromptlyCouponDTO.getCustomerPhoneList());
        }

        return save;
    }

    @Override
    public boolean updateShopPromptlyCouponById(Long id, ShopPromptlyCouponDTO shopPromptlyCouponDTO) {
        Coupon coupon = this.findById(id);
        AliyunOssService aliyunOssService = thirdConfigService.aliyunOssService();
        WxPayService wxPayService = shopThirdService.findWxPayServiceById(shopPromptlyCouponDTO.getShopId());
        MarketingMediaService marketingMediaService = wxPayService.getMarketingMediaService();
        // 卡包详情图片不为空，上传到微信图片
        if (!StrUtil.equals(coupon.getCouponImageOssurl(), shopPromptlyCouponDTO.getCouponImageOssurl())) {
            // 调用微信接口 上传图片
            String couponImageOssurl = shopPromptlyCouponDTO.getCouponImageOssurl();
            shopPromptlyCouponDTO.setCouponImageUrl(ThirdConfigUtil.getWxMediaUrl(aliyunOssService, marketingMediaService, couponImageOssurl));
        }

        CouponMapStruct.INSTANCE.copyProperties(shopPromptlyCouponDTO, coupon);
        coupon.setId(id);
        // 其他值赋值
        setValue(coupon, DictConstant.CouponType.TYPE_1.getCode());

        // 调用此接口前置条件是对接的商家劵的有效期结束时间前的优惠券都可以修改
//        if (DateUtil.compare(DateUtil.date(), coupon.getGetEndTime()) > WeikbestConstant.ZERO_INT) {
            ProdStandard prodStandard = prodStandardService.findProdStandard();
            Site site = siteService.findSite();
            try {
                MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();
                BusiFavorStocksCreateRequest request = CouponUtil.buildUpdateBusiFavorStocksCreateRequest(coupon, coupon.getOutRequestNo(), prodStandard, site);
                // 调用微信接口创建商家券（更新商家券）
                marketingBusiFavorService.updateBusiFavorStocksV3(coupon.getStockId(), request);
            } catch (WxPayException e) {
                log.error(String.format("微信商家券更新失败！shopId: %s, stockId: %s", coupon.getShopId(), coupon.getStockId()), e);
                throw new WeikbestException(e);
            }
//        }

        if (StrUtil.equals(shopPromptlyCouponDTO.getCouponProdType(), DictConstant.CouponProdType.special_prod.getCode())) {
            // 添加商品关联
            couponProdEntryService.saveBatchWithCouponId(id, coupon.getCouponType(), shopPromptlyCouponDTO.getProdIdList());
        }

        if (StrUtil.equals(shopPromptlyCouponDTO.getRestrictUserType(), DictConstant.RestrictUserType.special_customer.getCode())) {
            // 添加指定客户关联
            couponCustomerEntryService.saveBatchWithCouponId(coupon.getId(), coupon.getCouponType(), shopPromptlyCouponDTO.getCustomerPhoneList());
        }

        return this.updateById(coupon);
    }

    @Override
    public ShopRefluxCouponVO findShopRefluxCouponVOById(Long id) {
        Coupon coupon = this.findById(id);

        ShopRefluxCouponVO shopRefluxCouponVO = CouponMapStruct.INSTANCE.converToShopRefluxCouponVO(coupon);
        if (StrUtil.equals(shopRefluxCouponVO.getCouponProdType(), DictConstant.CouponProdType.special_prod.getCode())) {
            // 添加商品关联
            List<CouponProdEntry> couponProdEntryList = couponProdEntryService.queryByCouponId(id);
            List<Long> prodIdList = couponProdEntryList.stream().map(couponProdEntry -> couponProdEntry.getProdId()).collect(Collectors.toList());
            List<ProdShowDetailVO> prodList = prodService.queryDetailVOById(prodIdList);
            shopRefluxCouponVO.setProdList(prodList);
        }

        return shopRefluxCouponVO;
    }

    @Override
    public ShopPromptlyCouponVO findShopPromptlyCouponVOById(Long id) {
        Coupon coupon = this.findById(id);

        ShopPromptlyCouponVO shopPromptlyCouponVO = CouponMapStruct.INSTANCE.converToShopPromptlyCouponVO(coupon);
        if (StrUtil.equals(shopPromptlyCouponVO.getCouponProdType(), DictConstant.CouponProdType.special_prod.getCode())) {
            // 添加商品关联
            List<CouponProdEntry> couponProdEntryList = couponProdEntryService.queryByCouponId(id);
            List<Long> prodIdList = couponProdEntryList.stream().map(CouponProdEntry::getProdId).collect(Collectors.toList());
            List<ProdShowDetailVO> prodList = prodService.queryDetailVOById(prodIdList);
            shopPromptlyCouponVO.setProdList(prodList);
        }
        if (StrUtil.equals(shopPromptlyCouponVO.getCouponProdType(), DictConstant.CouponProdType.special_prod.getCode())) {
            // 添加客户关联
            List<CouponCustomerEntry> couponCustomerEntryList = couponCustomerEntryService.queryByCouponId(id);
            List<String> customerPhoneList = couponCustomerEntryList.stream().map(couponCustomerEntry -> String.valueOf(couponCustomerEntry.getRestrictUserPhone())).collect(Collectors.toList());
            shopPromptlyCouponVO.setCustomerPhoneList(customerPhoneList);
        }

        return shopPromptlyCouponVO;
    }

    private void setValue(Coupon coupon, String couponType) {
        coupon.setCouponType(couponType);
        if (StrUtil.equals(couponType, DictConstant.CouponType.TYPE_2.getCode())) {
            // 适用商品类型：部分商品
            coupon.setCouponProdType(DictConstant.CouponProdType.special_prod.getCode());
            //默认领取比例20%
            coupon.setGetPercentage(BigDecimal.valueOf(80));
        }

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
    public boolean updateDataStatus(List<Long> ids, String dataStatus) {
        List<Coupon> couponList = this.listByIds(ids);
        for (Coupon coupon : couponList) {
            // 失效优惠券
            coupon.setDataStatus(dataStatus);
            // 优惠券状态变成：已结束
            coupon.setCouponStatus(DictConstant.CouponStatus.TYPE_15.getCode());
        }
        boolean update = this.updateBatchById(couponList);

        for (Coupon coupon : couponList) {
            // 删除两条延时队列
            couponEventStartDelayTaskProcess.removeTask(coupon.getId());
            couponEventEndDelayTaskProcess.removeTask(coupon.getId());
        }
        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean publishCoupon(Long id) {
        Coupon coupon = this.findById(id);
        if (DateUtil.compare(coupon.getEventStartTime(), DateUtil.date()) > WeikbestConstant.ZERO_INT) {
            // 活动开始时间 大于 当前时间
            coupon.setCouponStatus(DictConstant.CouponStatus.TYPE_5.getCode());
            // 这里要创建延时队列让活动生效
            couponEventStartDelayTaskProcess.putTask(id, coupon.getEventStartTime().getTime());
        } else {
            // 活动开始时间 小于等于 当前时间
            if (DateUtil.compare(coupon.getEventEndTime(), DateUtil.date()) > WeikbestConstant.ZERO_INT) {
                // 活动结束时间 大于 当前时间
                coupon.setCouponStatus(DictConstant.CouponStatus.TYPE_10.getCode());
                // 这里要创建延时队列让活动结束
                couponEventEndDelayTaskProcess.putTask(id, coupon.getEventEndTime().getTime());
            } else {
                // 活动结束时间 小于等于 当前时间
                coupon.setCouponStatus(DictConstant.CouponStatus.TYPE_15.getCode());
            }
        }

        // 如果发布的是回流优惠券，则需要调用微信接口
        if (StrUtil.equals(coupon.getCouponType(), DictConstant.CouponType.TYPE_2.getCode())) {
            String mchId = payConfigService.findByPayConfigType(DictConstant.PayConfigType.TYPE_3.getCode()).getWxPayMchId();
            WxPayService wxPayService = shopThirdService.findWxPayServiceById(coupon.getShopId());
            try {
                String response = ThirdConfigUtil.partnershipsBuild(wxPayService, coupon.getStockId(), mchId);
                log.info("回流优惠券id:{}, stockId:{}, 建立合作关系结果: {}", coupon.getId(), coupon.getStockId(), response);
                coupon.setBindPartnerMerchantId(mchId);
            } catch (WxPayException e) {
                log.error("回流优惠券建立合作关系失败！", e);
                throw new WeikbestException(e);
            }
        }

        return this.updateById(coupon);
    }

    @Override
    public Map<String, List<Coupon>> queryHasExpiredCoupon(List<Long> idList) {
        Map<String, List<Coupon>> resultMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);
        resultMap.put(DictConstant.Whether.no.getCode(), new ArrayList<>());
        resultMap.put(DictConstant.Whether.yes.getCode(), new ArrayList<>());

        List<Coupon> couponList = this.listByIds(idList);
        if(CollectionUtil.isNotEmpty(couponList)) {
            DateTime date = DateUtil.date();
            couponList.forEach(coupon -> {
                // 如果活动结束时间小于当前时间，则说明优惠券已过期
                Date eventEndTime = coupon.getEventEndTime();
                if(DateUtil.compare(eventEndTime, date) < WeikbestConstant.ZERO_INT) {
                    resultMap.get(DictConstant.Whether.yes.getCode()).add(coupon);
                }
                else {
                    resultMap.get(DictConstant.Whether.no.getCode()).add(coupon);
                }
            });
        }
        return resultMap;
    }

    @Override
    public IPage<CouponPageVO> queryPageVO(CouponQO couponQO, PageReq pageReq) {
        IPage<CouponPageVO> page = this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), couponQO);
        if (CollectionUtil.isEmpty(page.getRecords())){
            return page;
        }
        // 查询其他汇总数据
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
    public List<Coupon> queryCouponProd(CouponProdEntryQO couponProdEntryQO) {
        // 商品关联的优惠券
        List<CouponProdEntry> couponProdEntryList = couponProdEntryService.queryCouponProd(couponProdEntryQO);
        if (CollectionUtil.isEmpty(couponProdEntryList)) {
            return new ArrayList<>();
        }
        List<Long> couponIdList = couponProdEntryList.stream().map(CouponProdEntry::getId).collect(Collectors.toList());
        // 全部商品的优惠券
        QueryWrapper<Coupon> couponQueryWrapper = new QueryWrapper<>();
        couponQueryWrapper.eq(Coupon.COUPON_PROD_TYPE, DictConstant.CouponProdType.all_prod.getCode());
        if (StrUtil.isNotBlank(couponProdEntryQO.getCouponType())) {
            couponQueryWrapper.eq(Coupon.COUPON_TYPE, couponProdEntryQO.getCouponType());
        }
        List<Coupon> couponList = this.list(couponQueryWrapper);
        if (CollectionUtil.isNotEmpty(couponList)) {
            couponIdList.addAll(couponList.stream().map(Coupon::getId).collect(Collectors.toList()));
        }
        // 返回所有结果
        return this.listByIds(couponIdList);
    }

    @Override
    public CouponDataStatisticsVO findDataStatisticsVOById(Long id) {
        Coupon coupon = this.findById(id);

        CouponDataStatisticsVO couponDataStatisticsVO = new CouponDataStatisticsVO();

        // 总领取量
        long totalCount = custCouponRestrictService.count(new QueryWrapper<CustCouponRestrict>().eq(CustCouponRestrict.COUPON_ID, id).ne(CustCouponRestrict.RESTRICT_TYPE, ""));
        couponDataStatisticsVO.setTotalCount((int) totalCount);

        // 用券核销
        long useCouponCount = custCouponRestrictService.count(new QueryWrapper<CustCouponRestrict>().eq(CustCouponRestrict.COUPON_ID, id).eq(CustCouponRestrict.RESTRICT_TYPE, DictConstant.RestrictType.TYPE_20.getCode()));
        couponDataStatisticsVO.setUseCouponCount((int) useCouponCount);

        // 主动核销
        long initiativeCouponCount = WeikbestConstant.ZERO_LONG;
        if (StrUtil.equals(coupon.getCouponType(), DictConstant.CouponType.TYPE_2.getCode())) {
            // 回流优惠券查询主动核销
            initiativeCouponCount = custCouponRestrictService.count(new QueryWrapper<CustCouponRestrict>().eq(CustCouponRestrict.COUPON_ID, id).eq(CustCouponRestrict.RESTRICT_TYPE, DictConstant.RestrictType.TYPE_25.getCode()));
        }
        couponDataStatisticsVO.setInitiativeCouponCount((int) initiativeCouponCount);

        // 剩余量
        int surplusCount = coupon.getCouponNum() - (int) totalCount;
        couponDataStatisticsVO.setSurplusCount(surplusCount);

        // 使用率%  = （总使用量/总领取量）%
        BigDecimal usageRate = new BigDecimal(WeikbestConstant.ZERO_INT);
        if (totalCount > WeikbestConstant.ZERO_LONG) {
            usageRate = new BigDecimal(useCouponCount + initiativeCouponCount).divide(new BigDecimal(totalCount), 2, RoundingMode.HALF_EVEN);
        }
        couponDataStatisticsVO.setUsageRate(usageRate);

        // 今日领取量
        long todayCount = custCouponRestrictService.count(new QueryWrapper<CustCouponRestrict>().eq(CustCouponRestrict.COUPON_ID, id).ge(CustCouponRestrict.RESTRICT_DATE, DateUtil.parseDate(DateUtil.today())).ne(CustCouponRestrict.RESTRICT_TYPE, ""));
        couponDataStatisticsVO.setTodayCount((int) todayCount);

        // 今日使用量
        long todayUseCount = custCouponRestrictService.count(new QueryWrapper<CustCouponRestrict>().eq(CustCouponRestrict.COUPON_ID, id)
                .eq(CustCouponRestrict.RESTRICT_TYPE, Lists.newArrayList(DictConstant.RestrictType.TYPE_20.getCode(), DictConstant.RestrictType.TYPE_25.getCode()))
                .ge(CustCouponRestrict.RESTRICT_DATE, DateUtil.parseDate(DateUtil.today())));
        couponDataStatisticsVO.setTodayUseCount((int) todayUseCount);

        // 支付订单数
        List<OrderInfo> orderInfoList = orderInfoService.list(new QueryWrapper<OrderInfo>().eq(OrderInfo.COUPON_ID, id).ne(OrderInfo.ORDER_STATUS, DictConstant.OrderStatus.orderStatus_1.getCode()));
        couponDataStatisticsVO.setPayOrderCount(orderInfoList.size());

        // 支付总金额（元）
        int payAmountTotal = orderInfoList.stream().mapToInt(orderInfo -> {
            // 金额向上取整
            BigDecimal payAmount = orderInfo.getPayAmount().multiply(new BigDecimal(100));
            return payAmount.intValue();
        }).sum();
        // 向下还原
        couponDataStatisticsVO.setPayAmountTotal(new BigDecimal(payAmountTotal).divide(new BigDecimal(100), 2, RoundingMode.UP));

        return couponDataStatisticsVO;
    }

    @Override
    public IPage<CustCouponRestrictPageVO> queryCustCouponRestrictPage(CustCouponRestrictQO custCouponRestrictQO, PageReq pageReq) {
        return custCouponRestrictService.queryVOPage(custCouponRestrictQO, pageReq);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void couponEventStartExecute(Long id) {
        Coupon coupon = this.findById(id);
        if (StrUtil.equals(coupon.getCouponStatus(), DictConstant.CouponStatus.TYPE_5.getCode())) {
            // 状态变更为进行中
            coupon.setCouponStatus(DictConstant.CouponStatus.TYPE_10.getCode());
            // 这里要创建延时队列让活动结束
            couponEventEndDelayTaskProcess.putTask(id, coupon.getEventEndTime().getTime());
            this.updateById(coupon);
            return;
        }
        log.warn("优惠券：{}，状态不是活动未开始，跳过活动开始处理任务...", id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void couponEventEndExecute(Long id) {
        Coupon coupon = this.findById(id);
        if (StrUtil.equals(coupon.getCouponStatus(), DictConstant.CouponStatus.TYPE_10.getCode())) {
            // 状态变更为已结束
            coupon.setCouponStatus(DictConstant.CouponStatus.TYPE_15.getCode());
            this.updateById(coupon);
            return;
        }
        log.warn("优惠券：{}，状态不是活动进行中，跳过活动结束处理任务...", id);
    }

    @Override
    public Coupon queryCouponById(Long id){
        return this.baseMapper.queryCouponById(id);
    }

    @Override
    public Coupon queryCouponByStockId(String stockId){
        return this.baseMapper.queryCouponByStockId(stockId);
    }

    @Override
    public void updateShopRefluxCoupon(Long id, BigDecimal getPercentage) {
        Coupon coupon = new Coupon();
        coupon.setId(id).setGetPercentage(getPercentage);
        this.updateById(coupon);
    }
}
