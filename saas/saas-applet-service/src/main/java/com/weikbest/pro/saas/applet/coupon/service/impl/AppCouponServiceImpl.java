package com.weikbest.pro.saas.applet.coupon.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weikbest.pro.saas.applet.coupon.module.mapstruct.AppCouponDetailMapStruct;
import com.weikbest.pro.saas.applet.coupon.module.mapstruct.AppCouponMapStruct;
import com.weikbest.pro.saas.applet.coupon.module.mapstruct.AppCustCouponRestrictMapStruct;
import com.weikbest.pro.saas.applet.coupon.module.qo.AppCustProdCouponQO;
import com.weikbest.pro.saas.applet.coupon.module.vo.*;
import com.weikbest.pro.saas.applet.coupon.service.AppCouponService;
import com.weikbest.pro.saas.applet.shop.module.qo.AppShopCouponQO;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.third.util.Const;
import com.weikbest.pro.saas.common.third.wx.util.StringUtil;
import com.weikbest.pro.saas.common.third.wx.util.WXSignUtils;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.merchat.coupon.delaytaskprocess.AppCouponRestrictTypeExpiredDelayTaskProcess;
import com.weikbest.pro.saas.merchat.coupon.delaytaskprocess.AppCouponRestrictTypeNotUsedDelayTaskProcess;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponScene;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponSceneConfig;
import com.weikbest.pro.saas.merchat.coupon.entity.CustCouponRestrict;
import com.weikbest.pro.saas.merchat.coupon.module.dto.AppCustCouponRestrictDTO;
import com.weikbest.pro.saas.merchat.coupon.service.CouponSceneConfigService;
import com.weikbest.pro.saas.merchat.coupon.service.CouponSceneService;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.merchat.coupon.service.CustCouponRestrictService;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCoupon;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCouponRelate;
import com.weikbest.pro.saas.merchat.prod.module.dto.AppProdDTO;
import com.weikbest.pro.saas.merchat.prod.service.ProdCouponService;
import com.weikbest.pro.saas.merchat.prod.service.ProdCouponRelateService;
import com.weikbest.pro.saas.merchat.prod.service.ProdService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import com.weikbest.pro.saas.sys.param.entity.PayConfig;
import com.weikbest.pro.saas.sys.param.service.PayConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppCouponServiceImpl implements AppCouponService {

    private static final Gson GSON = new GsonBuilder().create();

    @Resource
    private CouponService couponService;

    @Resource
    private CustCouponRestrictService custCouponRestrictService;

    @Resource
    private ProdCouponService prodCouponService;

    @Resource
    private ProdCouponRelateService prodCouponRelateService;

    @Resource
    private ProdService prodService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private CustomerService customerService;

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private CouponSceneService couponSceneService;

    @Resource
    private PayConfigService payConfigService;

    @Resource
    private CouponSceneConfigService couponSceneConfigService;

    @Resource
    private AppCouponRestrictTypeNotUsedDelayTaskProcess appCouponRestrictTypeNotUsedDelayTaskProcess;

    @Resource
    private AppCouponRestrictTypeExpiredDelayTaskProcess appCouponRestrictTypeExpiredDelayTaskProcess;

    @Resource
    private RedisContext redisContext;


    @Override
    public List<AppCouponVO> queryshopcoupons(AppShopCouponQO appShopCouponQO) {

        QueryWrapper<CustCouponRestrict> custCouponRestrictQueryWrapper = new QueryWrapper<>();
        custCouponRestrictQueryWrapper.eq("customer_id", appShopCouponQO.getCustomerId());
        if (ObjectUtil.isNotNull(appShopCouponQO.getShopId())) {
            custCouponRestrictQueryWrapper.eq("shop_id", appShopCouponQO.getShopId());
        }
        List<CustCouponRestrict> custCouponRestrictList = custCouponRestrictService.list(custCouponRestrictQueryWrapper);
        if (CollectionUtil.isEmpty(custCouponRestrictList)) {
            return null;
        }
        List<Long> custCouponIds = new ArrayList<>();
        for (CustCouponRestrict custCouponRestrict : custCouponRestrictList) {
            custCouponIds.add(custCouponRestrict.getCouponId());
        }

        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(appShopCouponQO.getShopId())) {
            queryWrapper.eq("shop_id", appShopCouponQO.getShopId());
        }
        queryWrapper.in("id", custCouponIds);

        List<Coupon> coupons = couponService.list(queryWrapper);
        List<AppCouponVO> appShopCouponVOS = new ArrayList<>();
        for (Coupon coupon : coupons) {
            appShopCouponVOS.add(AppCouponMapStruct.INSTANCE.converToVO(coupon));
        }
        return appShopCouponVOS;
    }

    @Override
    public AppProdCouponVO queryCouponsByProdId(Long prodId,String appId){

        AppProdCouponVO appProdCouponVO = new AppProdCouponVO();
        //默认为0，不设置回流优惠券
        appProdCouponVO.setIsOpenCoupon("0");

        Map map = new TreeMap<>();

        Long customerId = currentUserService.getAppTokenUser().getId();

        ProdCoupon prodCoupon = prodCouponService.findByProdIdAndType(prodId,"2");
        if(ObjectUtil.isNotNull(prodCoupon) && "1".equals(prodCoupon.getIsOpenCoupon())){
            //查看该商品下的回流优惠券
            QueryWrapper<ProdCouponRelate> prodCouponRelateQueryWrapper = new QueryWrapper<>();
            prodCouponRelateQueryWrapper.eq(ProdCouponRelate.ID,prodId);
            prodCouponRelateQueryWrapper.eq(ProdCouponRelate.COUPON_TYPE,"2");
            prodCouponRelateQueryWrapper.select(ProdCouponRelate.COUPON_ID);

            List<Long> couponIds = prodCouponRelateService.listObjs(prodCouponRelateQueryWrapper, o -> Long.valueOf(o.toString()));
            //查看是否存在平台券
            QueryWrapper<CouponScene> couponSceneQueryWrapper = new QueryWrapper<>();
            couponSceneQueryWrapper.eq(CouponScene.COUPON_TYPE,"3");
            couponSceneQueryWrapper.eq(CouponScene.COUPON_SCENE_TYPE,"1");
            couponSceneQueryWrapper.select(CouponScene.COUPON_ID);

            Boolean syscoupon = false;
            Boolean flag = false;
            String syswxPayMchId = "";
            String sysv2key = "";
            List<Long> couponIds2 = couponSceneService.listObjs(couponSceneQueryWrapper, o -> Long.valueOf(o.toString()));
            if(couponIds2 != null && couponIds2.size() > 0) {
                couponIds.addAll(couponIds2);
                syscoupon = true;
                PayConfig payConfig = payConfigService.getOne(new QueryWrapper<PayConfig>().eq(PayConfig.PAY_CONFIG_TYPE ,"3"));
                syswxPayMchId = payConfig.getWxPayMchId();
                sysv2key = payConfig.getWxPayMchKey();
            }

            List<Long> couponIdlist = new ArrayList<>(couponIds);
            for (Long couponId: couponIdlist) {
                Coupon coupon = couponService.queryCouponById(couponId);
                if(DictConstant.Whether.yes.getCode().equals(coupon.getFlag())){
                    couponIds.remove(couponId);
                    continue;
                }

                int count = coupon.getRestrictCount();
                QueryWrapper<CustCouponRestrict> custCouponRestrictQueryWrapper1 = new QueryWrapper<>();
                custCouponRestrictQueryWrapper1.eq(CustCouponRestrict.COUPON_ID,couponId);
                custCouponRestrictQueryWrapper1.ne(CustCouponRestrict.COUPON_CODE,"");
                int rcount1 = (int)custCouponRestrictService.count(custCouponRestrictQueryWrapper1);
                if (rcount1>=coupon.getCouponNum()){
                    couponIds.remove(couponId);
                }

                QueryWrapper<CustCouponRestrict> custCouponRestrictQueryWrapper = new QueryWrapper<>();
                custCouponRestrictQueryWrapper.eq(CustCouponRestrict.COUPON_ID,couponId);
                custCouponRestrictQueryWrapper.eq(CustCouponRestrict.CUSTOMER_ID,customerId);
                custCouponRestrictQueryWrapper.ne(CustCouponRestrict.COUPON_CODE,"");
                int rcount = (int)custCouponRestrictService.count(custCouponRestrictQueryWrapper);
                if( rcount >= count){
                    couponIds.remove(couponId);
                }
            }

            if(couponIds != null && couponIds.size() > 0) {

                Long shopId = prodService.findById(prodId).getShopId();
                ShopThird shopThird = shopThirdService.findById(shopId);
                String wxPayMchId = shopThird.getWxPayMchId();
                String v2key = shopThird.getWxPayMchKey();

                QueryWrapper<Coupon> couponQueryWrapper = new QueryWrapper<>();
                couponQueryWrapper.in(Coupon.ID, couponIds);
                couponQueryWrapper.eq(Coupon.COUPON_STATUS, "10");
                couponQueryWrapper.le(Coupon.GET_START_TIME,new Date());
                couponQueryWrapper.ge(Coupon.GET_END_TIME,new Date());

                List<Coupon> shopCouponList = couponService.list(couponQueryWrapper);
                Map<String,String> paramsMap = new TreeMap<>();

                List<Map<String,String>> mapList = new ArrayList<>();
                List<AppCustCouponRestrictVO> custCouponRestrictList = new ArrayList<>();


                int i = 0;
                for (Coupon coupon : shopCouponList) {
                    //类型为回流券
                    if (DictConstant.CouponType.TYPE_2.getCode().equals(coupon.getCouponType())){
                        //存在比例
                        if (ObjectUtil.isNotNull(coupon) && ObjectUtil.isNotNull(coupon.getGetPercentage())){
                            //计算命中
                            boolean hitRandom = WeikbestObjectUtil.hitRandom(coupon.getGetPercentage());
                            if(!hitRandom) {
                                // 未命中
                                log.info("回流券ID：{}， 命中比率：{}，未命中，客户未领取到该回流券!", coupon.getId(), coupon.getGetPercentage());
                                continue;
                            }
                        }

                    }
                    String outrequestno = OrderUtil.getSendOutrefundNo();
                    Map<String,String> mapcoupon = new TreeMap<>();

                    paramsMap.put("out_request_no"+i,outrequestno);
                    paramsMap.put("stock_id"+i,coupon.getStockId());
                    String wxPayMchIdOrSys = wxPayMchId;
                    if(syscoupon){
                        for (Long sysId: couponIds2) {
                            if(sysId.equals(coupon.getId())){
                                wxPayMchIdOrSys = syswxPayMchId;
                                flag = true;
                            }
                        }
                    }
                    mapcoupon.put("create_coupon_merchant",wxPayMchIdOrSys);

                    mapcoupon.put("stock_id",coupon.getStockId());
                    mapcoupon.put("out_request_no",outrequestno);
                    mapList.add(mapcoupon);
                    i++;

//                    初始化优惠券领取表数据并保存
                    AppCustCouponRestrictVO custCoupon = new AppCustCouponRestrictVO();
                    Long id = GenerateIDUtil.nextId();
                    custCoupon.setId(id);
                    custCoupon.setCustomerId(customerId);
                    custCoupon.setShopId(coupon.getShopId());
                    custCoupon.setCouponId(coupon.getId());
                    custCoupon.setCouponType(coupon.getCouponType());
                    custCoupon.setGetRequestNo(outrequestno);
                    custCoupon.setProdId(prodId);
                    custCoupon.setAppId(appId);
                    custCouponRestrictList.add(custCoupon);

                }
                paramsMap.put("send_coupon_merchant",flag?syswxPayMchId:wxPayMchId);
//                i = 0;
//                for (Coupon coupon : shopCouponList) {
//                    paramsMap.put("stock_id"+i,coupon.getStockId());
//                    i++;
//                }
                appProdCouponVO.setIsOpenCoupon(DictConstant.Whether.yes.getCode());
                appProdCouponVO.setChargeOff(prodCoupon.getChargeOff());
                appProdCouponVO.setCouponOpenImg(prodCoupon.getCouponOpenImg());

                String sign = WXSignUtils.createSign(paramsMap, "HMAC-SHA256", flag?sysv2key:v2key, null);

                map.put("send_coupon_params",mapList);
                map.put("sign",sign);
                map.put("send_coupon_merchant",flag?syswxPayMchId:wxPayMchId);

                appProdCouponVO.setMap(map);

                List<AppCustCouponRestrictVO> voList = new ArrayList<>();

                for (AppCustCouponRestrictVO custCouponRestrict: custCouponRestrictList) {
                    if(flag && custCouponRestrict.getCouponType().equals(DictConstant.CouponType.TYPE_2.getCode())){
                        custCouponRestrict.setIsTerrace(DictConstant.Whether.yes.getCode());
                        custCouponRestrict.setTerraceWxPayMchId(syswxPayMchId);
                        custCouponRestrict.setTerraceWxPayMchKey(sysv2key);
                    }
                    voList.add(custCouponRestrict);
                }
                //设置用户领券信息
                appProdCouponVO.setAppCustCouponRestrictVOS(voList);
            }
        }

        return appProdCouponVO;
    }

    @Override
    public AppProdCouponVO queryReduceCouponByProdId(Long prodId,String appId){

        AppProdCouponVO appProdCouponVO = new AppProdCouponVO();
        //默认为0，不设置立减优惠券
        appProdCouponVO.setIsOpenCoupon("0");

        Map map = new TreeMap<>();

        Long customerId = currentUserService.getAppTokenUser().getId();

        //查看是否设置了立减券
        ProdCoupon prodCoupon = prodCouponService.findByProdIdAndType(prodId,"1");
        //if(ObjectUtil.isNotNull(prodCoupon) && "1".equals(prodCoupon.getIsOpenCoupon())){
        if(ObjectUtil.isNotNull(prodCoupon)){
            //查看该商品下的立减优惠券
            QueryWrapper<ProdCouponRelate> prodCouponRelateQueryWrapper = new QueryWrapper<>();
            prodCouponRelateQueryWrapper.eq(ProdCouponRelate.ID,prodId);
            prodCouponRelateQueryWrapper.eq(ProdCouponRelate.COUPON_TYPE,"1");
            prodCouponRelateQueryWrapper.select(ProdCouponRelate.COUPON_ID);

            List<Long> couponIds = prodCouponRelateService.listObjs(prodCouponRelateQueryWrapper, o -> Long.valueOf(o.toString()));

            List<Long> couponIdlist = new ArrayList<>();
            couponIdlist.addAll(couponIds);
            for (Long couponId: couponIdlist) {
                Coupon coupon = couponService.queryCouponById(couponId);
                if (ObjectUtil.isNotNull(coupon)){
                    if(DictConstant.Whether.yes.getCode().equals(coupon.getFlag())){
                        couponIds.remove(couponId);
                        continue;
                    }
                    int count = coupon.getRestrictCount();
                    QueryWrapper<CustCouponRestrict> custCouponRestrictQueryWrapper = new QueryWrapper<>();
                    custCouponRestrictQueryWrapper.eq(CustCouponRestrict.COUPON_ID,couponId);
                    custCouponRestrictQueryWrapper.eq(CustCouponRestrict.CUSTOMER_ID,customerId);
                    custCouponRestrictQueryWrapper.ne(CustCouponRestrict.COUPON_CODE,"");
                    int rcount = (int)custCouponRestrictService.count(custCouponRestrictQueryWrapper);
                    if( rcount >= count){
                        couponIds.remove(couponId);
                        throw new WeikbestException("您已超过该优惠券的领用次数，不可再领取！");
                    }
                }
            }

            if(couponIds != null && couponIds.size() > 0) {

                Long shopId = prodService.findById(prodId).getShopId();
                ShopThird shopThird = shopThirdService.findById(shopId);
                String wxPayMchId = shopThird.getWxPayMchId();
                String v2key = shopThird.getWxPayMchKey();

                QueryWrapper<Coupon> couponQueryWrapper = new QueryWrapper<>();
                couponQueryWrapper.in(Coupon.ID, couponIds);
                couponQueryWrapper.eq(Coupon.COUPON_STATUS, "10");
                couponQueryWrapper.le(Coupon.GET_START_TIME,new Date());
                couponQueryWrapper.ge(Coupon.GET_END_TIME,new Date());

                List<Coupon> shopCouponList = couponService.list(couponQueryWrapper);
                List<AppCouponDetailVO> detailVOS = new ArrayList<>();
                Map<String,String> paramsMap = new TreeMap<>();
                List<AppCustCouponRestrictVO> voList = new ArrayList<>();
                List<Map<String,String>> mapList = new ArrayList<>();
                int i = 0;
                for (Coupon coupon : shopCouponList) {
                    String outrequestno = OrderUtil.getSendOutrefundNo();
                    Map<String,String> mapcoupon = new TreeMap<>();
                    paramsMap.put("out_request_no"+i,outrequestno);

                    mapcoupon.put("create_coupon_merchant",wxPayMchId);
                    mapcoupon.put("stock_id",coupon.getStockId());
                    mapcoupon.put("out_request_no",outrequestno);
                    mapList.add(mapcoupon);
                    i++;

                    //初始化优惠券领取表数据并保存
                    AppCustCouponRestrictVO custCoupon = new AppCustCouponRestrictVO();
                    Long id = GenerateIDUtil.nextId();
                    custCoupon.setId(id);
                    custCoupon.setCustomerId(customerId);
                    custCoupon.setShopId(coupon.getShopId());
                    custCoupon.setCouponId(coupon.getId());
                    custCoupon.setCouponType(coupon.getCouponType());
                    custCoupon.setGetRequestNo(outrequestno);
                    custCoupon.setProdId(prodId);
                    custCoupon.setAppId(appId);
                    voList.add(custCoupon);

                    AppCouponDetailVO vo = AppCouponDetailMapStruct.INSTANCE.converToVO(coupon);
                    detailVOS.add(vo);

                }
                paramsMap.put("send_coupon_merchant",wxPayMchId);
                i = 0;
                for (Coupon coupon : shopCouponList) {
                    paramsMap.put("stock_id"+i,coupon.getStockId());
                    i++;
                }

                appProdCouponVO.setIsOpenCoupon(DictConstant.Whether.yes.getCode());
                appProdCouponVO.setChargeOff(prodCoupon.getChargeOff());

                String sign = WXSignUtils.createSign(paramsMap, "HMAC-SHA256", v2key, null);

                map.put("send_coupon_params",mapList);
                map.put("sign",sign);
                map.put("send_coupon_merchant",wxPayMchId);

                appProdCouponVO.setMap(map);

                //设置客户领券信息
                appProdCouponVO.setAppCustCouponRestrictVOS(voList);
                //设置立减券优惠券信息
                appProdCouponVO.setDetailVOS(detailVOS);

            }
        }

        return appProdCouponVO;
    }

    @Override
    public AppCouponSceneVO queryCouponScene(String appId){

        Long customerId = currentUserService.getAppTokenUser().getId();

        AppCouponSceneVO vo = new AppCouponSceneVO();

        Object str = redisContext.get("COUPON_SCENE:"+customerId);
        String datestr = DateUtil.format(new Date(),"yyyy-MM-dd");


        //查看是否存在平台券
        QueryWrapper<CouponScene> couponSceneQueryWrapper = new QueryWrapper<>();
        couponSceneQueryWrapper.eq(CouponScene.COUPON_TYPE,"3");
        couponSceneQueryWrapper.eq(CouponScene.COUPON_SCENE_TYPE,"2");

        List<CouponScene> couponScenes = couponSceneService.list(couponSceneQueryWrapper);

        if(couponScenes != null && couponScenes.size() > 0) {
            CouponSceneConfig couponSceneConfig = couponSceneConfigService.findById(couponScenes.get(0).getId());
            vo.setPlatformCouponReceiveOpenUrl(couponSceneConfig.getPlatformCouponReceiveOpenUrl());
            vo.setPlatformCouponReceiveBannerUrl(couponSceneConfig.getPlatformCouponReceiveBannerUrl());
        }

        if(ObjectUtil.isNotNull(str) && datestr.equals((String) str)){
            //当前该客户已领取了平台券，不能再次领取
            return vo;
        }

        couponSceneQueryWrapper.select(CouponScene.COUPON_ID);

        List<Long> couponIds = couponSceneService.listObjs(couponSceneQueryWrapper, o -> Long.valueOf(o.toString()));

        Map map = new TreeMap<>();

        List<Long> couponIdlist = new ArrayList<>();
        couponIdlist.addAll(couponIds);
        for (Long couponId: couponIdlist) {
            Coupon coupon = couponService.queryCouponById(couponId);
            if(DictConstant.Whether.yes.getCode().equals(coupon.getFlag())){
                couponIds.remove(couponId);
                continue;
            }
            int count = coupon.getRestrictCount();
            QueryWrapper<CustCouponRestrict> custCouponRestrictQueryWrapper = new QueryWrapper<>();
            custCouponRestrictQueryWrapper.eq(CustCouponRestrict.COUPON_ID,couponId);
            custCouponRestrictQueryWrapper.eq(CustCouponRestrict.CUSTOMER_ID,customerId);
            custCouponRestrictQueryWrapper.ne(CustCouponRestrict.COUPON_CODE,"");
            int rcount = (int)custCouponRestrictService.count(custCouponRestrictQueryWrapper);
            if( rcount >= count){
                couponIds.remove(couponId);
            }
        }

        if(couponIds != null && couponIds.size() > 0) {

            PayConfig payConfig = payConfigService.getOne(new QueryWrapper<PayConfig>().eq(PayConfig.PAY_CONFIG_TYPE ,"3"));
            String wxPayMchId = payConfig.getWxPayMchId();
            String v2key = payConfig.getWxPayMchKey();


            QueryWrapper<Coupon> couponQueryWrapper = new QueryWrapper<>();
            couponQueryWrapper.in(Coupon.ID, couponIds);
            couponQueryWrapper.eq(Coupon.COUPON_STATUS, "10");
            couponQueryWrapper.le(Coupon.GET_START_TIME,new Date());
            couponQueryWrapper.ge(Coupon.GET_END_TIME,new Date());

            List<Coupon> shopCouponList = couponService.list(couponQueryWrapper);
            Map<String,String> paramsMap = new TreeMap<>();
            List<AppCustCouponRestrictVO> voList = new ArrayList<>();
            List<Map<String,String>> mapList = new ArrayList<>();
            int i = 0;
            for (Coupon coupon : shopCouponList) {
                String outrequestno = OrderUtil.getSendOutrefundNo();
                Map<String,String> mapcoupon = new TreeMap<>();

                paramsMap.put("out_request_no"+i,outrequestno);

                mapcoupon.put("create_coupon_merchant",wxPayMchId);
                mapcoupon.put("stock_id",coupon.getStockId());
                mapcoupon.put("out_request_no",outrequestno);
                mapList.add(mapcoupon);
                i++;

                //初始化优惠券领取表数据并保存
                AppCustCouponRestrictVO custCoupon = new AppCustCouponRestrictVO();
                Long id = GenerateIDUtil.nextId();
                custCoupon.setId(id);
                custCoupon.setCustomerId(customerId);
                custCoupon.setShopId(coupon.getShopId());
                custCoupon.setCouponId(coupon.getId());
                custCoupon.setCouponType(coupon.getCouponType());
                custCoupon.setGetRequestNo(outrequestno);
                custCoupon.setAppId(appId);
                voList.add(custCoupon);
            }
            paramsMap.put("send_coupon_merchant",wxPayMchId);
            i = 0;
            for (Coupon coupon : shopCouponList) {
                paramsMap.put("stock_id"+i,coupon.getStockId());
                i++;
            }


            String sign = WXSignUtils.createSign(paramsMap, "HMAC-SHA256", v2key, null);

            map.put("send_coupon_params",mapList);
            map.put("sign",sign);
            map.put("send_coupon_merchant",wxPayMchId);

            vo.setMap(map);

            //设置客户领券信息
            vo.setAppCustCouponRestrictVOS(voList);

        }

        return vo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void notifybusiFavor(Map<String, Object> result){
        log.info("===================== 领劵回调参数: {} =====================" , result);

        QueryWrapper<Coupon> couponQueryWrapper = new QueryWrapper<>();
        couponQueryWrapper.eq(Coupon.STOCK_ID,result.get("stock_id"));
        Coupon coupon = couponService.getOne(couponQueryWrapper);

        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.eq(Customer.WX_OPENID,result.get("openid"));
        customerQueryWrapper.eq(Customer.WX_UNIONID,result.get("unionid"));
        Customer customer = customerService.getOne(customerQueryWrapper);

        if(ObjectUtil.isNotNull(coupon) && ObjectUtil.isNotNull(customer)){
            //设置优惠券用券开始时间和用券结束时间
            //如果设置为领券后立即生效
            if(DictConstant.CouponEnableType.now.getCode().equals(coupon.getEnableType())){
                DateTime dateTime = DateUtil.date();
                coupon.setUseStartTime(dateTime);
                coupon.setUseEndTime(DateUtil.offsetDay(dateTime, coupon.getValidityDay()));
            }
            //如果设置为领券后延迟生效
            if(DictConstant.CouponEnableType.delay.getCode().equals(coupon.getEnableType())){
                DateTime dateTime = DateUtil.date();
                coupon.setUseStartTime(DateUtil.offsetDay(dateTime , coupon.getDelayEnableDay()));
                coupon.setUseEndTime(DateUtil.offsetDay(dateTime , (coupon.getDelayEnableDay() + coupon.getValidityDay())));
            }

            couponService.saveOrUpdate(coupon);

            log.info("==============读取优惠券领券表信息根据请求单号================ "+result.get("send_req_no").toString());
            QueryWrapper<CustCouponRestrict> custCouponRestrictQueryWrapper = new QueryWrapper<>();
            custCouponRestrictQueryWrapper.eq(CustCouponRestrict.GET_REQUEST_NO,result.get("send_req_no").toString());
            //custCouponRestrictQueryWrapper.eq(CustCouponRestrict.CUSTOMER_ID,customer.getId());
            custCouponRestrictQueryWrapper.eq(CustCouponRestrict.COUPON_ID,coupon.getId());
            CustCouponRestrict custCoupon = custCouponRestrictService.getOne(custCouponRestrictQueryWrapper);
            custCoupon.setRestrictUserPhone(customer.getPhone());
            custCoupon.setRestrictDate(DateUtil.date());
            custCoupon.setCouponCode(result.get("coupon_code").toString());
            //设置领券初始状态
            //如果设置为领券后延迟生效
            if(DictConstant.CouponEnableType.delay.getCode().equals(coupon.getEnableType())){
                custCoupon.setRestrictType(DictConstant.RestrictType.TYPE_1.getCode());
            }
            //如果设置为领券后立即生效
            if(DictConstant.CouponEnableType.now.getCode().equals(coupon.getEnableType())){
                custCoupon.setRestrictType(DictConstant.RestrictType.TYPE_5.getCode());
            }
            log.info("======== 用户领取的优惠卷的状态为: {} ========" , custCoupon.getRestrictType());
            custCouponRestrictService.updateById(custCoupon);

            //如果是平台券，设置当天已领券，当天不能再次领券
            if(DictConstant.CouponType.TYPE_3.getCode().equals(custCoupon.getCouponType())){
                redisContext.set("COUPON_SCENE:"+customer.getId(),DateUtil.format(new Date(),"yyyy-MM-dd"));
            }

            //设置未生效到未使用延时队列
            appCouponRestrictTypeNotUsedDelayTaskProcess.putTask(custCoupon.getId(),coupon.getUseStartTime().getTime());
            //设置未使用到已过期延时队列
            appCouponRestrictTypeExpiredDelayTaskProcess.putTask(custCoupon.getId(),coupon.getUseEndTime().getTime());

            log.info("==============完成优惠券领券状态延时队列设置及回调结束================优惠券领券表ID "+custCoupon.getId());
        }else {
            log.error("============== 领劵回调失败: 参数: stock_id: {} , openid: {} , unionid: {} , 优惠劵对象: {} , 客户对象: {}" , result.get("stock_id") , result.get("openid") , result.get("unionid") , coupon , customer);
        }
    }

    @Override
    public int queryCountUseableCustCouponRestricts(){
        Long customerId = currentUserService.getAppTokenUser().getId();
        return custCouponRestrictService.queryCountUseableCustCouponRestricts(customerId);
    }

    @Override
    public int queryCountExpiredCustCouponRestricts(){
        Long customerId = currentUserService.getAppTokenUser().getId();
        return custCouponRestrictService.queryCountExpiredCustCouponRestricts(customerId);
    }

    @Override
    public int queryCountUsedCustCouponRestricts(){
        Long customerId = currentUserService.getAppTokenUser().getId();
        return custCouponRestrictService.queryCountUsedCustCouponRestricts(customerId);
    }

    @Override
    public int queryCountUseableCustCouponProds(AppCustProdCouponQO appCustProdCouponQO){
        Long customerId = currentUserService.getAppTokenUser().getId();
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put("customerId",customerId);
        paramMap.put("prodId",appCustProdCouponQO.getProdId());
        paramMap.put("price",appCustProdCouponQO.getComboPrice());
        return custCouponRestrictService.queryCountUseableCustCouponProds(paramMap);
    }

    @Override
    public int queryCountUnuseableCustCouponProds(AppCustProdCouponQO appCustProdCouponQO){
        Long customerId = currentUserService.getAppTokenUser().getId();
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put("customerId",customerId);
        paramMap.put("prodId",appCustProdCouponQO.getProdId());
        paramMap.put("price",appCustProdCouponQO.getComboPrice());
        return custCouponRestrictService.queryCountUnuseableCustCouponProds(paramMap);
    }

    @Override
    public List<AppCustCouponRestrictDTO> queryUseableCustCouponRestricts(PageReq pageReq){
        Long customerId = currentUserService.getAppTokenUser().getId();
        // 构建查询参数
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put("customerId",customerId);
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());
        return custCouponRestrictService.queryUseableCustCouponRestricts(paramMap);
    }

    @Override
    public List<AppCustCouponRestrictDTO> queryExpiredCustCouponRestricts(PageReq pageReq){
        Long customerId = currentUserService.getAppTokenUser().getId();
        // 构建查询参数
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put("customerId",customerId);
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());
        return custCouponRestrictService.queryExpiredCustCouponRestricts(paramMap);
    }

    @Override
    public List<AppCustCouponRestrictDTO> queryUsedCustCouponRestricts(PageReq pageReq){
        Long customerId = currentUserService.getAppTokenUser().getId();
        // 构建查询参数
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put("customerId",customerId);
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());
        return custCouponRestrictService.queryUsedCustCouponRestricts(paramMap);
    }

    @Override
    public List<AppCustCouponRestrictDTO> queryUseableCustCouponProds(AppCustProdCouponQO appCustProdCouponQO,PageReq pageReq){
        Long customerId = currentUserService.getAppTokenUser().getId();
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put("customerId",customerId);
        paramMap.put("prodId",appCustProdCouponQO.getProdId());
        paramMap.put("price",appCustProdCouponQO.getComboPrice());
        paramMap.put("couponType",appCustProdCouponQO.getCouponType());
        if ("1".equals(appCustProdCouponQO.getIsPage())){
            paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
            paramMap.put("limit", pageReq.getLimit());
            return custCouponRestrictService.queryUseableCustCouponProds(paramMap);
        }else {
            return custCouponRestrictService.listUseableCustCouponProds(paramMap);
        }

    }

    @Override
    public List<AppCustCouponRestrictDTO> queryUnuseableCustCouponProds(AppCustProdCouponQO appCustProdCouponQO,PageReq pageReq){
        Long customerId = currentUserService.getAppTokenUser().getId();
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put("customerId",customerId);
        paramMap.put("prodId",appCustProdCouponQO.getProdId());
        paramMap.put("price",appCustProdCouponQO.getComboPrice());
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());
        return custCouponRestrictService.queryUnuseableCustCouponProds(paramMap);
    }

    @Override
    public Map<String,Object> queryPageCommodityByCouponId(Long couponId, PageReq pageReq){

        Map<String, Object> map = new TreeMap<>();

        Coupon coupon = couponService.queryCouponById(couponId);

        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put("couponType","0");
        //如果是立减优惠券，则返回该券适用的商品信息
        if("1".equals(coupon.getCouponType())){
            //之前设置couponType为1，2023.1.6修改为3，待确认？
            paramMap.put("couponType","3");
        }
        //如果是平台券，并且是指定商品
        if("3".equals(coupon.getCouponType()) && "2".equals(coupon.getCouponProdType())){
            paramMap.put("couponType","3");
        }
        paramMap.put("couponId",couponId);
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());
        List<AppProdDTO> appProdDTOList = prodService.queryPageCommodityByCouponId(paramMap);

        map.put("couponType",coupon.getCouponType());
        map.put("content",appProdDTOList);

        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public  boolean saveCustCouponRestricts(List<AppCustCouponRestrictVO> appCustCouponRestrictVOS){

        if(appCustCouponRestrictVOS != null && appCustCouponRestrictVOS.size() > 0 ){
            for (AppCustCouponRestrictVO vo: appCustCouponRestrictVOS) {
                if(WeikbestObjectUtil.isNotEmpty(vo.getId())) {

                    CustCouponRestrict custCouponRestrict = AppCustCouponRestrictMapStruct.INSTANCE.converToEntity(vo);

                    custCouponRestrictService.save(custCouponRestrict);
                }
            }
        }

        return true;
    }

    @Override
    public AppCustCouponRestrictVO getCouponCodeByURL(String url){

        AppCustCouponRestrictVO vo = new AppCustCouponRestrictVO();
        String stockId = StringUtil.getUrlparameter(url,"stock_id");
        String nonce = StringUtil.getUrlparameter(url,"nonce");
        String associate = StringUtil.getUrlparameter(url,"associate");
        String ciphertext = StringUtil.getUrlparameter(url,"ciphertext");
        String key = "";

        //通过stockId获取当前优惠券对应的商家KEY
        Coupon coupon = couponService.queryCouponByStockId(stockId);

        if(coupon.getShopId() == 0L || coupon.getCouponType().equals(DictConstant.CouponType.TYPE_3.getCode())){
            PayConfig payConfig = payConfigService.getOne(new QueryWrapper<PayConfig>().eq(PayConfig.PAY_CONFIG_TYPE ,"3"));
            key = payConfig.getWxPayMchKey();
        }else{
            ShopThird shopThird = shopThirdService.findById(coupon.getShopId());
            key = shopThird.getWxPayMchKey();
        }
        try {
            ciphertext = URLDecoder.decode(ciphertext, "UTF-8");
            String couponCode = AesUtils.decryptToString(associate, nonce, ciphertext, key);
            QueryWrapper<CustCouponRestrict> custCouponRestrictQueryWrapper = new QueryWrapper<>();
            custCouponRestrictQueryWrapper.eq(CustCouponRestrict.COUPON_CODE,couponCode);
            vo = AppCustCouponRestrictMapStruct.INSTANCE.converToVO(custCouponRestrictService.getOne(custCouponRestrictQueryWrapper));
        }catch(Exception e){
            throw new WeikbestException("解密异常"+e.getMessage());
        }
        return vo;
    }
}
