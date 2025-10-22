package com.weikbest.pro.saas.applet.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.github.binarywang.wxpay.bean.marketing.*;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingReceiver;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingRequest;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingResult;
import com.github.binarywang.wxpay.bean.request.CombineTransactionsRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.CombineTransactionsResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingBusiFavorService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.applet.order.module.dto.AppOrderInfoDTO;
import com.weikbest.pro.saas.applet.order.module.mapstruct.AppOrderInfoMapStruct;
import com.weikbest.pro.saas.applet.order.module.mapstruct.AppOrderLogisticsMapStruct;
import com.weikbest.pro.saas.applet.order.module.mapstruct.AppOrderRecAddsMapStruct;
import com.weikbest.pro.saas.applet.order.module.qo.AppOrderInfoListQO;
import com.weikbest.pro.saas.applet.order.module.vo.AppOrderDetailVO;
import com.weikbest.pro.saas.applet.order.module.vo.AppOrderLogisticsVO;
import com.weikbest.pro.saas.applet.order.module.vo.AppOrderRecAddrVO;
import com.weikbest.pro.saas.applet.order.service.AppOrderService;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.redis.RedisService;
import com.weikbest.pro.saas.common.third.aliyun.logistics.AliyunWuliuService;
import com.weikbest.pro.saas.common.third.wx.util.WxPayAmountConvertUtil;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.merchat.busi.entity.Business;
import com.weikbest.pro.saas.merchat.busi.entity.CustBusinessBind;
import com.weikbest.pro.saas.merchat.busi.service.BusinessService;
import com.weikbest.pro.saas.merchat.busi.service.CustBusinessBindService;
import com.weikbest.pro.saas.merchat.coupon.delaytaskprocess.AppCouponRestrictTypeExpiredDelayTaskProcess;
import com.weikbest.pro.saas.merchat.coupon.delaytaskprocess.AppCouponRestrictTypeNotUsedDelayTaskProcess;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.entity.CustCouponRestrict;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.merchat.coupon.service.CustCouponRestrictService;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import com.weikbest.pro.saas.merchat.order.delaytaskprocess.*;
import com.weikbest.pro.saas.merchat.order.entity.*;
import com.weikbest.pro.saas.merchat.order.module.dto.AppOrderInfoListDTO;
import com.weikbest.pro.saas.merchat.order.service.*;
import com.weikbest.pro.saas.merchat.prod.entity.*;
import com.weikbest.pro.saas.merchat.prod.module.vo.PayBackProdAdvBackAccountVO;
import com.weikbest.pro.saas.merchat.prod.service.*;
import com.weikbest.pro.saas.merchat.shop.entity.*;
import com.weikbest.pro.saas.merchat.shop.service.*;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.constant.ConfigConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import com.weikbest.pro.saas.sys.param.entity.LogisticsCompany;
import com.weikbest.pro.saas.sys.param.entity.Settle;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUserrecordSendDTO;
import com.weikbest.pro.saas.sys.param.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.Resource;

/**
 * <p>
 * 小程序订单 业务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-03
 */
@Slf4j
@Service
public class AppOrderServiceImpl implements AppOrderService {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private OrderRecAddrService orderRecAddrService;

    @Resource
    private OrderProdInfoService orderProdInfoService;

    @Resource
    private OrderLogisticsService orderLogisticsService;

    @Resource
    private ProdService prodService;

    @Resource
    private ProdDecFloorService prodDecFloorService;

    @Resource
    private OrderCustInfoService orderCustInfoService;

    @Resource
    private ProdComboService prodComboService;

    @Resource
    private OrderPayRecordService orderPayRecordService;

    @Resource
    private OrderStatRecordService orderStatRecordService;

    @Resource
    private TencentAdvUserrecordService tencentAdvUserrecordService;

    @Resource
    private OrderDeliverTimeoutDelayTaskProcess orderDeliverTimeoutDelayTaskProcess;

    @Resource
    private OrderPayTimeoutDelayTaskProcess orderPayTimeoutDelayTaskProcess;

    @Resource
    private OrderWaitPayTimeoutDelayTaskProcess orderWaitPayTimeoutDelayTaskProcess;

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private RedisLock redisLock;

    @Resource
    private CustomerService customerService;

    @Resource
    private OrderDeliverCustomerTimeoutDelayTaskProcess orderDeliverCustomerTimeoutDelayTaskProcess;

    @Resource
    private CustCouponRestrictService custCouponRestrictService;

    @Resource
    private AppCouponRestrictTypeExpiredDelayTaskProcess appCouponRestrictTypeExpiredDelayTaskProcess;

    @Resource
    private AppCouponRestrictTypeNotUsedDelayTaskProcess appCouponRestrictTypeNotUsedDelayTaskProcess;

    @Resource
    private CouponService couponService;

    @Resource
    private LogisticsCompanyService logisticsCompanyService;

    @Resource
    private MemoryService memoryService;

    @Resource
    private BusinessService businessService;

    @Resource
    private CustBusinessBindService custBusinessBindService;

    @Resource
    private OrderSourceScaleService orderSourceScaleService;

    @Resource
    private ShopThirdReceiveService shopThirdReceiveService;

    @Resource
    private OrderReceiveRecordService orderReceiveRecordService;

    @Resource
    private SettleService settleService;

    @Resource
    private AppOrderCustBusinessBindDelayTaskProcess appOrderCustBusinessBindDelayTaskProcess;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private ShopFinanceAccountService shopFinanceAccountService;

    @Resource
    private ShopFinanceDetailService shopFinanceDetailService;

    @Resource
    private ShopStatementDetailService shopStatementDetailService;

    @Resource
    private ProdReturnService prodReturnService;

    @Resource
    private ShopNonRegionService shopNonRegionService;

    @Resource
    private PayConfigService payConfigService;

    @Autowired
    private ProdPriceService prodPriceService;

    @Resource
    private RedisContext redisContext;
    @Resource
    private RedisService redisService;
    @Resource
    private ProdPurchaseRestrictionsService prodPurchaseRestrictionsService;


    @Autowired
    private ProdLeftSlideService prodLeftSlideService;

    @Autowired
    private DealService dealService;

    @Resource
    private ProdCouponRelateService prodCouponRelateService;

    @Resource
    private ProdCouponService prodCouponService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String doOrder(AppOrderInfoDTO appOrderInfoDTO) {

        OrderInfo orderInfo = AppOrderInfoMapStruct.INSTANCE.converToEntity(appOrderInfoDTO);
        Long orderId = GenerateIDUtil.nextId();
        String number = OrderUtil.getOrderNumber();
        //校验订单信息 并赋值
        Prod prod = checkOrderInfoAndSetValue(appOrderInfoDTO, orderInfo, orderId, number);

        //保存分账相关信息
        Business business = businessService.findById(prod.getBusinessId());
        if(DictConstant.BusinessType.platform.getCode().equals(business.getBusinessType())){
            if(WeikbestObjectUtil.isNotEmpty(orderInfo.getSourceProdId())){
                Prod sourceProd = prodService.findById(orderInfo.getSourceProdId());
                Long businessId = sourceProd.getBusinessId();
                Business sourceBusiness = businessService.findById(businessId);
                if(ObjectUtil.isNotNull(sourceBusiness) &&
                        !DictConstant.BusinessType.platform.getCode().equals(sourceBusiness.getBusinessType())){
                    ShopThird shopThird = shopThirdService.findById(sourceProd.getShopId());
                    String mchId = shopThird.getWxPayMchId();
                    orderInfo.setReceiveBussinessId(businessId);
                    orderInfo.setWxPayReceiveMchId(mchId);
                }
            }
            if(ObjectUtil.isNotEmpty((orderInfo.getSourceCouponCode()))){
                QueryWrapper<CustCouponRestrict> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(CustCouponRestrict.COUPON_CODE,orderInfo.getSourceCouponCode());
                CustCouponRestrict ccr = custCouponRestrictService.getOne(queryWrapper);
                if(ObjectUtil.isNotNull(ccr) && !ccr.getCouponType().equals(DictConstant.CouponType.TYPE_1.getCode())){
                    if(WeikbestObjectUtil.isNotEmpty(ccr.getProdId())){
                        Prod sourceProd = prodService.findById(ccr.getProdId());
                        Long businessId = sourceProd.getBusinessId();
                        Business sourceBusiness = businessService.findById(businessId);
                        if(ObjectUtil.isNotNull(sourceBusiness) &&
                                !DictConstant.BusinessType.platform.getCode().equals(sourceBusiness.getBusinessType())) {
                            ShopThird shopThird = shopThirdService.findById(sourceProd.getShopId());
                            String mchId = shopThird.getWxPayMchId();
                            orderInfo.setReceiveBussinessId(businessId);
                            orderInfo.setWxPayReceiveMchId(mchId);
                        }
                    }
                }
            }
        }
        //设置是否绑定商户 1.0 取消绑定商户功能，2.0再放开
        /**
         if(DictConstant.OrderSource.TYPE_1.getCode().equals(orderInfo.getOrderSource())){
         QueryWrapper<CustBusinessBind> queryWrapper = new QueryWrapper<>();
         queryWrapper.eq(CustBusinessBind.CUSTOMER_ID,customerId);
         queryWrapper.eq(CustBusinessBind.BIND_STATUS,"1");
         queryWrapper.orderByDesc(CustBusinessBind.BIND_TIME);
         List<CustBusinessBind> binds = custBusinessBindService.list(queryWrapper);
         if(binds != null && binds.size() > 0){
         //该客户已绑定商户
         if(DictConstant.OrderPath.TYPE_1.getCode().equals(orderInfo.getOrderPath())){
         orderInfo.setOrderPath(DictConstant.OrderPath.TYPE_10.getCode());
         }
         if(DictConstant.OrderPath.TYPE_5.getCode().equals(orderInfo.getOrderPath())){
         orderInfo.setOrderPath(DictConstant.OrderPath.TYPE_15.getCode());
         }
         CustBusinessBind bind = binds.get(0);
         ShopThird shopThird = shopThirdService.findById(bind.getShopId());
         String mchId = shopThird.getWxPayMchId();
         orderInfo.setReceiveBussinessId(bind.getBusinessId());
         orderInfo.setWxPayReceiveMchId(mchId);
         }
         }**/

        //设置订单来源
        orderInfo.setOrderSource(getOrderSource(orderInfo.getOrderPath(),orderInfo.getAdAid(),orderInfo.getClickId()));

        // 保存订单信息
        orderInfoService.saveOrUpdate(orderInfo);

        // 保存订单其他信息
        saveRelateOrder(orderInfo.getId(), appOrderInfoDTO, prod, number);

        // 超过24小时未支付修改订单状态
        orderPayTimeoutDelayTaskProcess.putTask(orderInfo.getId());
        // 超过20分钟未支付短信/微信消息提醒延时任务
//        orderWaitPayTimeoutDelayTaskProcess.putTask(orderInfo.getId());

        return number;
    }

    /**
     * 检查配送地区
     * @param orderInfo 订单信息
     * @param appOrderInfoDTO 小程序传输订单信息
     */
    private void checkShopUnRegion(OrderInfo orderInfo,AppOrderInfoDTO appOrderInfoDTO) {
        List<ShopNonRegion> shopNonRegions = shopNonRegionService.listByShopId(orderInfo.getShopId());
        if (CollectionUtil.isNotEmpty(shopNonRegions)){
            String collectRegionNames = shopNonRegions.stream().map(ShopNonRegion::getRegionName).collect(Collectors.joining(","));
            if(collectRegionNames.contains(appOrderInfoDTO.getAddrProvince())){
                throw new WeikbestException("该地区不在配送范围！");
            }
        }
    }

    /**
     * 优惠券核销
     * @param orderInfo 订单信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void checkCoupon(OrderInfo orderInfo) {
        //5，优惠券核销

        log.info("==============进入下单方法优惠券核销业务===============订单号： " + orderInfo.getNumber());


        List<String> type = new ArrayList<>();
        type.add(DictConstant.RestrictType.TYPE_1.getCode());
        type.add(DictConstant.RestrictType.TYPE_5.getCode());
        type.add(DictConstant.RestrictType.TYPE_15.getCode());
        QueryWrapper<CustCouponRestrict> custCouponRestrictQueryWrapper = new QueryWrapper<>();
        custCouponRestrictQueryWrapper.eq(CustCouponRestrict.COUPON_TYPE,DictConstant.CouponType.TYPE_2.getCode());
        custCouponRestrictQueryWrapper.eq(CustCouponRestrict.CUSTOMER_ID, orderInfo.getCustomerId());
        custCouponRestrictQueryWrapper.eq(CustCouponRestrict.PROD_ID, orderInfo.getProdId());
        custCouponRestrictQueryWrapper.in(CustCouponRestrict.RESTRICT_TYPE,type);
        List<CustCouponRestrict> custCouponRestrictList = custCouponRestrictService.list(custCouponRestrictQueryWrapper);

        if(WeikbestObjectUtil.isNotEmpty(orderInfo.getCustCouponRestrictId())){
            CustCouponRestrict custCouponRestrict = custCouponRestrictService.findById(orderInfo.getCustCouponRestrictId());
            orderInfo.setCouponId(custCouponRestrict.getCouponId());
            orderInfo.setCouponType(custCouponRestrict.getCouponType());
            custCouponRestrict.setRestrictType(DictConstant.RestrictType.TYPE_20.getCode());
            custCouponRestrict.setRestrictUseDate(DateUtil.date());
            //删除设置领券状态已过期延时队列
            appCouponRestrictTypeExpiredDelayTaskProcess.removeTask(custCouponRestrict.getId());
            Coupon coupon = couponService.queryCouponById(custCouponRestrict.getCouponId());
            //核销该券
            changCouponUse(orderInfo.getNumber(),custCouponRestrict,coupon.getStockId(), orderInfo.getShopId(), orderInfo.getAppId());

            if(DictConstant.CouponType.TYPE_2.getCode().equals(orderInfo.getCouponType())){
                //当处理的是回流优惠券时，该商品该客户下的其他回流优惠券需失效

                if(custCouponRestrictList != null && custCouponRestrictList.size() > 0) {
                    for (CustCouponRestrict custCouponRestrict1 : custCouponRestrictList) {
                        if (!custCouponRestrict.getId().equals(custCouponRestrict1.getId())) {
                            //当前回流优惠券已核销，只需失效未核销的回流券
                            changCouponDeactivate(orderInfo.getNumber(),custCouponRestrict1, orderInfo.getShopId());
                        }
                    }
                }
            }

        }else{
            //当不存在需要核销的优惠券时，只处理该商品该客户名下回流优惠券，使之失效
            if(custCouponRestrictList != null && custCouponRestrictList.size() > 0) {
                for (CustCouponRestrict custCouponRestrict1 : custCouponRestrictList) {
                    changCouponDeactivate(orderInfo.getNumber(),custCouponRestrict1, orderInfo.getShopId());
                }
            }
        }
        log.info("==============结束下单方法优惠券核销业务===============订单号： " + orderInfo.getNumber());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doorderByCashOnDelivery(AppOrderInfoDTO appOrderInfoDTO) {

        if (!DictConstant.PayType.TYPE_2.getCode().equals(appOrderInfoDTO.getPayType())){
            throw new WeikbestException("支付方式非货到付款！");
        }

        OrderInfo orderInfo = AppOrderInfoMapStruct.INSTANCE.converToEntity(appOrderInfoDTO);
        Long orderId = GenerateIDUtil.nextId();
        String number = OrderUtil.getOrderNumber();
        Map<String , BigDecimal> discountAmountMap = new HashMap<>();
        BigDecimal discountAmount = new BigDecimal(BigInteger.ZERO);
        //校验并赋值
        Prod prod = checkOrderInfoAndSetValue(appOrderInfoDTO, orderInfo, orderId, number);
        //设置订单来源
        orderInfo.setOrderSource(getOrderSource(orderInfo.getOrderPath(),orderInfo.getAdAid(),orderInfo.getClickId()));
        //设置订单状态为待发货
        orderInfo.setOrderStatus(DictConstant.OrderStatus.orderStatus_10.getCode());
        //设置支付方式
        orderInfo.setPayType(DictConstant.PayType.TYPE_2.getCode());

        // 保存订单其他信息-到付类型
        saveRelateOrderByDelivery(orderInfo.getId(), appOrderInfoDTO, prod);

        // 保存订单信息
        orderInfoService.saveOrUpdate(orderInfo);
        //广告回传处理
        //另起线程执行广告回传
        ThreadUtil.execute(()->doAdCallback(orderInfo));



        return number;
    }

    @Override
    public void checkVerifyCode(String ip, String phone, String code) {
        // 判断验证码是否失效
        String verifyKey = SecureUtil.md5(ip + phone);
        // 通过手机号+手机验证码登录
        redisService.validateVerify(verifyKey, code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String batchOrder(List<AppOrderInfoDTO> appOrderInfoDTOList) {
        if (CollectionUtil.isEmpty(appOrderInfoDTOList)) {
            throw new WeikbestException("订单信息为空！");

        }
        //查询是否有订单编号
        String orderNumber = appOrderInfoDTOList.stream()
                .map(AppOrderInfoDTO::getNumber)
                .collect(Collectors.toList()).get(0);
        Map<String, Long> map = new HashMap<>();
        if (StringUtils.isNotBlank(orderNumber)){
            //根据订单编码查出该合单的信息
            String mainNumber = orderInfoService.getMainNumberByNumber(orderNumber);
            if (StringUtils.isNotBlank(mainNumber)){
                List<OrderInfo> mainOrders = Optional.ofNullable(orderInfoService.findByOrderMainNumber(mainNumber)).orElseGet(ArrayList::new);
                map = mainOrders.stream().collect(Collectors.toMap(OrderInfo::getNumber, OrderInfo::getId));
            }
        }

        String mainNumber = BasicConstant.OrderNumber.MAIN_NUMBER_START_WITH+OrderUtil.getOrderNumber();
        for (AppOrderInfoDTO appOrderInfoDTO : appOrderInfoDTOList) {
            OrderInfo orderInfo = AppOrderInfoMapStruct.INSTANCE.converToEntity(appOrderInfoDTO);
            orderInfo.setMainNumber(mainNumber);
            String orderNumber1 = appOrderInfoDTO.getNumber();
            Long orderId = GenerateIDUtil.nextId();
            String number = OrderUtil.getOrderNumber();
            if (!map.isEmpty()){
                if (StringUtils.isNotBlank(orderNumber1)){
                    number = orderNumber1;
                    orderId = map.get(orderNumber1);
                }else {
                    for (String key : map.keySet()) {
                        if (!key.equals(orderNumber1)){
                            number = key;
                            orderId = map.get(key);
                        }
                    }
                }
            }

            //校验订单信息 并赋值
            Prod prod = checkOrderInfoAndSetValue(appOrderInfoDTO, orderInfo, orderId, number);

            //保存分账相关信息
            Business business = businessService.findById(prod.getBusinessId());
            if(DictConstant.BusinessType.platform.getCode().equals(business.getBusinessType())){
                if(WeikbestObjectUtil.isNotEmpty(orderInfo.getSourceProdId())){
                    Prod sourceProd = prodService.findById(orderInfo.getSourceProdId());
                    Long businessId = sourceProd.getBusinessId();
                    Business sourceBusiness = businessService.findById(businessId);
                    if(ObjectUtil.isNotNull(sourceBusiness) &&
                            !DictConstant.BusinessType.platform.getCode().equals(sourceBusiness.getBusinessType())){
                        ShopThird shopThird = shopThirdService.findById(sourceProd.getShopId());
                        String mchId = shopThird.getWxPayMchId();
                        orderInfo.setReceiveBussinessId(businessId);
                        orderInfo.setWxPayReceiveMchId(mchId);
                    }
                }
                if(ObjectUtil.isNotEmpty((orderInfo.getSourceCouponCode()))){
                    QueryWrapper<CustCouponRestrict> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq(CustCouponRestrict.COUPON_CODE,orderInfo.getSourceCouponCode());
                    CustCouponRestrict ccr = custCouponRestrictService.getOne(queryWrapper);
                    if(ObjectUtil.isNotNull(ccr) && !ccr.getCouponType().equals(DictConstant.CouponType.TYPE_1.getCode())){
                        if(WeikbestObjectUtil.isNotEmpty(ccr.getProdId())){
                            Prod sourceProd = prodService.findById(ccr.getProdId());
                            Long businessId = sourceProd.getBusinessId();
                            Business sourceBusiness = businessService.findById(businessId);
                            if(ObjectUtil.isNotNull(sourceBusiness) &&
                                    !DictConstant.BusinessType.platform.getCode().equals(sourceBusiness.getBusinessType())) {
                                ShopThird shopThird = shopThirdService.findById(sourceProd.getShopId());
                                String mchId = shopThird.getWxPayMchId();
                                orderInfo.setReceiveBussinessId(businessId);
                                orderInfo.setWxPayReceiveMchId(mchId);
                            }
                        }
                    }
                }
            }

            //设置订单来源
            orderInfo.setOrderSource(getOrderSource(orderInfo.getOrderPath(),orderInfo.getAdAid(),orderInfo.getClickId()));

            // 保存订单信息
            orderInfoService.saveOrUpdate(orderInfo);

            // 保存订单其他信息
            saveRelateOrder(orderInfo.getId(), appOrderInfoDTO, prod, number);

            // 超过24小时未支付修改订单状态
            orderPayTimeoutDelayTaskProcess.putTask(orderInfo.getId());
            // 超过20分钟未支付短信/微信消息提醒延时任务
            orderWaitPayTimeoutDelayTaskProcess.putTask(orderInfo.getId());
        }

        return mainNumber;
    }

    @Override
    public List<String> findOrderByMainNumber(String mainNumber) {
        List<OrderInfo> orderInfoList = orderInfoService.findByOrderMainNumber(mainNumber);
        return orderInfoList.stream().sorted(Comparator.comparing(OrderInfo::getOrderAmount).reversed()).map(OrderInfo::getNumber).collect(Collectors.toList());
    }

    private void saveRelateOrderByDelivery(Long orderId, AppOrderInfoDTO infoDTO, Prod prod) {
        Customer ct = customerService.findById(infoDTO.getCustomerId());

        //保存客户订单与客户关联拆分表
        OrderCustInfo custInfo = new OrderCustInfo();
        custInfo.setId(orderId).setCustomerId(infoDTO.getCustomerId()).setCustomerName(ct.getName())
                .setCustomerProvince(infoDTO.getAddrProvince()).setCustomerCity(infoDTO.getAddrCity())
                .setCustomerDistrict(infoDTO.getAddrDistrict()).setCustomerAddr(infoDTO.getAddr())
                .setCustomerPhone(infoDTO.getPhone()).setCustomerDescription(infoDTO.getCustomerDescription())
                .setTpType(infoDTO.getTpType()).setTpOpenid(infoDTO.getTpOpenid())
                .setTpName(infoDTO.getTpName()).setTpPhoto(infoDTO.getTpPhoto())
                .setTpQrcode("");

        orderCustInfoService.saveOrUpdate(custInfo);

        //保存收货地址信息
        OrderRecAddr orderRecAddr = AppOrderRecAddsMapStruct.INSTANCE.converToEntity(infoDTO);
        orderRecAddr.setId(orderId);
        orderRecAddrService.saveOrUpdate(orderRecAddr);


        //保存订单商品参数详细表
        OrderProdInfo prodInfo = new OrderProdInfo();
        prodInfo.setId(orderId);
        prodInfo.setProdId(infoDTO.getProdId());
        prodInfo.setCategoryId(prod.getCategoryId());
        prodInfo.setBrandId(0L);
        prodInfo.setBuyNumber(infoDTO.getBuyNumber());
        prodInfo.setProdName(prod.getName());
        prodInfo.setProdComboId(infoDTO.getProdComboId());
        prodInfo.setProdComboAttrValues(infoDTO.getProdComboAttrValues());
        prodInfo.setProdImg(infoDTO.getProdImg());
        prodInfo.setTips(prod.getTips());

        ProdCombo prodCombo = prodComboService.findById(infoDTO.getProdComboId());
        if (ObjectUtil.isNotNull(prodCombo.getSetMealType()) && BasicConstant.INT_2.equals(prodCombo.getSetMealType())){
            //多规格
            prodInfo.setProdComboTitle(infoDTO.getProdComboAttrValues());
        }else {
            //普通套餐
            prodInfo.setProdComboTitle(prodCombo.getComboTitle());
        }
        prodInfo.setProdComboPrice(prodCombo.getComboPrice());
        prodInfo.setProdComboStandardPrice(prodCombo.getComboStandardPrice());
        prodInfo.setProdComboCostPrice(prodCombo.getComboCostPrice());
        orderProdInfoService.saveOrUpdate(prodInfo);
    }

    /**
     * 校验按订单信息和赋值
     *
     * @param appOrderInfoDTO
     * @param orderInfo
     * @param orderId
     * @param number
     */
    @Transactional(rollbackFor = Exception.class)
    public Prod checkOrderInfoAndSetValue(AppOrderInfoDTO appOrderInfoDTO, OrderInfo orderInfo, Long orderId, String number) {
        //如果有订单编号则说明该订单有历史订单,将之前的订单重新编辑
        if (StringUtils.isNotBlank(appOrderInfoDTO.getNumber())){
            OrderInfo byOrderNumber = orderInfoService.getByOrderNumber(appOrderInfoDTO.getNumber());
            if (ObjectUtil.isNotNull(byOrderNumber)){
                orderId = byOrderNumber.getId();
                number = byOrderNumber.getNumber();
            }
        }

        Map<String, BigDecimal> discountAmountMap = new HashMap<String, BigDecimal>();
        BigDecimal discountAmount = new BigDecimal(BigInteger.ZERO);
        orderInfo.setId(orderId);
        orderInfo.setNumber(number);

        Long customerId = currentUserService.getAppTokenUser().getId();
        orderInfo.setCustomerId(customerId);

        //点击id不为空，去redis查询数据，有就更新广告id,没有直接用原始的传过来的数据
        if (StrUtil.isNotBlank(orderInfo.getClickId())){
            String key = RedisKey.generalKey(RedisKey.AD_CLICK_MONITORING_KEY, orderInfo.getClickId());
            Object adgroupId = redisContext.hget(key, "adgroupId");
            if (ObjectUtil.isNotNull(adgroupId)){
                orderInfo.setAdAid(String.valueOf(adgroupId));
            }
        }

        if(StrUtil.isEmpty(orderInfo.getUserBehavior())){
            throw new WeikbestException("用户行为不能为空！");
        }
        if(StrUtil.isEmpty(orderInfo.getOrderPath())){
            throw new WeikbestException("下单路径不能为空！");
        }

        if(WeikbestObjectUtil.isNotEmpty(appOrderInfoDTO.getCustCouponRestrictId())){
            CustCouponRestrict custCouponRestrict = custCouponRestrictService.findById(appOrderInfoDTO.getCustCouponRestrictId());
            if(ObjectUtil.isNull(custCouponRestrict)){
                throw new WeikbestException("该优惠券不存在！");
            }
            if(!custCouponRestrict.getAppId().equals(appOrderInfoDTO.getAppId())){
                throw new WeikbestException("该优惠券不是当前小程序领取，不能使用！");
            }
            //优惠劵不是待使用额,也不是冻结中, 不可用
            if(!custCouponRestrict.getRestrictType().equals(DictConstant.RestrictType.TYPE_5.getCode()) && !custCouponRestrict.getRestrictType().equals(DictConstant.RestrictType.TYPE_15.getCode())){
                throw new WeikbestException("该优惠劵暂不可用！");
            }
            //优惠劵是冻结中,但没有支付失败优惠 , 不可用
            if (custCouponRestrict.getRestrictType().equals(DictConstant.RestrictType.TYPE_15.getCode()) && !BasicConstant.INT_1.equals(appOrderInfoDTO.getIsThereAFailureMoney())){
                throw new WeikbestException("该优惠劵暂不可用！");
            }
            //设置优惠劵状态为冻结
            CustCouponRestrict restrict = new CustCouponRestrict();
            restrict.setId(custCouponRestrict.getId());
            restrict.setRestrictType(DictConstant.RestrictType.TYPE_15.getCode());
            custCouponRestrictService.updateById(restrict);

            Coupon coupon = couponService.findById(custCouponRestrict.getCouponId());
            discountAmountMap.put("coupon" , coupon.getDiscountAmount());
        }

        Prod prod = prodService.findById(appOrderInfoDTO.getProdId());
        if (ObjectUtil.isEmpty(prod)) {
            throw new WeikbestException("当前商品信息不存在！");
        }
        orderInfo.setName(prod.getName());
        orderInfo.setBusinessId(prod.getBusinessId());
        orderInfo.setCustomerId(appOrderInfoDTO.getCustomerId());
        orderInfo.setShopId(prod.getShopId());
        orderInfo.setOrderTime(new Date());
        //商品订单不配送地区校验
        checkShopUnRegion(orderInfo,appOrderInfoDTO);
        //校验订单累计限购件数
        checkOrderLimit(prod,orderInfo,appOrderInfoDTO);
        //设置优惠金额
        setDiscountAmount(discountAmountMap , appOrderInfoDTO.getProdReturnPageId() , appOrderInfoDTO.getIsThereAFailureMoney() , appOrderInfoDTO.getProdId() , appOrderInfoDTO.getBuyNumber() , appOrderInfoDTO.getProdComboId());
        for (String key : discountAmountMap.keySet()) {
            discountAmount = discountAmount.add(discountAmountMap.get(key));
        }
        orderInfo.setDiscountAmount(discountAmount);
        orderInfo.setOrderDiscountRecord(JSONObject.toJSONString(discountAmountMap));
        return prod;
    }

    /**
     *
     * @param prod
     * @param orderInfo
     * @param appOrderInfoDTO
     */
    private void checkOrderLimit(Prod prod, OrderInfo orderInfo,AppOrderInfoDTO appOrderInfoDTO) {
        //初始化商品累计限购数
        Integer orderLimit = Integer.MAX_VALUE;
        List<String> orderStatusList = Arrays.asList(DictConstant.OrderStatus.orderStatus_10.getCode(), DictConstant.OrderStatus.orderStatus_20.getCode(), DictConstant.OrderStatus.orderStatus_30.getCode());

        //限购验证开启
        if (BasicConstant.STATE_1.equals(prod.getIsOpenOrderLimit())) {
            ProdPurchaseRestrictions ps = prodPurchaseRestrictionsService.getById(prod.getId());
            if (ObjectUtil.isNotNull(ps)) {
                orderLimit = ObjectUtil.isNull(ps.getUmulativePurchasesNum()) ? orderLimit : ps.getUmulativePurchasesNum();
                if (ObjectUtil.isNotNull(appOrderInfoDTO)){
                    if (orderLimit.compareTo(appOrderInfoDTO.getBuyNumber())<0){
                        throw new WeikbestException("超出累计购买件数 ！该商品累计限购：" + orderLimit + "件");
                    }
                }else {
                    OrderProdInfo byId = orderProdInfoService.findById(orderInfo.getId());
                    if (orderLimit.compareTo(byId.getBuyNumber())<0){
                        throw new WeikbestException("超出累计购买件数 ！该商品累计限购：" + orderLimit + "件");
                    }
                }
            }
            List<OrderInfo> list = orderInfoService.list(new QueryWrapper<OrderInfo>().eq(OrderInfo.PROD_ID, prod.getId()).eq(OrderInfo.CUSTOMER_ID, orderInfo.getCustomerId()).in(OrderInfo.ORDER_STATUS, orderStatusList));
            if (CollectionUtil.isEmpty(list)) {
                return;
            }
            List<Long> idList = list.stream().map(OrderInfo::getId).collect(Collectors.toList());
            List<OrderProdInfo> orderProdInfos = orderProdInfoService.list(new QueryWrapper<OrderProdInfo>().in(OrderProdInfo.ID, idList));

            if (CollectionUtil.isEmpty(orderProdInfos)) {
                return;
            }
            Integer orderNums = orderProdInfos.stream().map(OrderProdInfo::getBuyNumber).reduce(0, Integer::sum);

            if (ObjectUtil.isNotNull(appOrderInfoDTO)){
                orderNums+=appOrderInfoDTO.getBuyNumber();
            }else {
                OrderProdInfo byId = orderProdInfoService.findById(orderInfo.getId());
                orderNums+=byId.getBuyNumber();
            }
            if (orderLimit.compareTo(orderNums) < 0) {
                throw new WeikbestException("超出累计购买件数 ！该商品累计限购：" + orderLimit + "件");
            }
        }
    }

    public String getOrderSource(String orderPath,String AdId,String clickId){

        String orderSource =  DictConstant.OrderSource.TYPE_1.getCode();


        //1-自然流量【50-聚合页非首产品直接成交、55-聚合页非首产品支付失败成交、60-聚合页商户主页直接成交、65-聚合页商户主页支付失败成交】
        if(DictConstant.OrderPath.TYPE_50.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_55.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_60.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_65.getCode().equals(orderPath)){
            orderSource = DictConstant.OrderSource.TYPE_1.getCode();
        }
        //5-广告引流【20-广告直接提交、25-广告支付失败成交】
        if(DictConstant.OrderPath.TYPE_20.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_25.getCode().equals(orderPath)){
            orderSource = DictConstant.OrderSource.TYPE_5.getCode();
            if (StrUtil.isBlank(AdId) || StrUtil.isBlank(clickId)){
                orderSource = DictConstant.OrderSource.TYPE_1.getCode();
            }
        }
        //10-回流流量【30-左滑操作直接成交、35-左滑操作支付失败成交、40-聚合页首产品直接成交、45-聚合页首产品支付失败成交】
        if(DictConstant.OrderPath.TYPE_30.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_35.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_40.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_45.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_70.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_75.getCode().equals(orderPath)){
            orderSource = DictConstant.OrderSource.TYPE_10.getCode();
            if (StrUtil.isBlank(AdId) || StrUtil.isBlank(clickId)){
                orderSource = DictConstant.OrderSource.TYPE_1.getCode();
            }
        }
        //1-自然流量【1-小程序直接成交、5-小程序支付失败成交、80-限时优惠直接成交、85-限时优惠支付失败成交】
        if(DictConstant.OrderPath.TYPE_1.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_5.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_10.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_15.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_80.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_85.getCode().equals(orderPath)){
            orderSource = DictConstant.OrderSource.TYPE_1.getCode();
        }

        //20-营销流量【70-回流优惠券页直接成交、75-回流优惠券页支付失败成交】
        if (DictConstant.OrderPath.TYPE_70.getCode().equals(orderPath)
                || DictConstant.OrderPath.TYPE_75.getCode().equals(orderPath)){
            orderSource = DictConstant.OrderSource.TYPE_20.getCode();
        }
        return orderSource;
    }

    private void saveRelateOrder(Long orderId, AppOrderInfoDTO infoDTO, Prod prod, String number) {

        Customer ct = customerService.findById(infoDTO.getCustomerId());
        ProdCombo combo = prodComboService.getById(infoDTO.getProdComboId());

        //保存客户订单与客户关联拆分表
        OrderCustInfo custInfo = new OrderCustInfo();
        custInfo.setId(orderId).setCustomerId(infoDTO.getCustomerId()).setCustomerName(ct.getName())
                .setCustomerProvince(infoDTO.getAddrProvince()).setCustomerCity(infoDTO.getAddrCity())
                .setCustomerDistrict(infoDTO.getAddrDistrict()).setCustomerAddr(infoDTO.getAddr())
                .setCustomerPhone(infoDTO.getPhone()).setCustomerDescription(infoDTO.getCustomerDescription())
                .setTpType(infoDTO.getTpType()).setTpOpenid(infoDTO.getTpOpenid())
                .setTpName(infoDTO.getTpName()).setTpPhoto(infoDTO.getTpPhoto())
                .setTpQrcode("");

        orderCustInfoService.saveOrUpdate(custInfo);

        //保存收货地址信息
        OrderRecAddr orderRecAddr = AppOrderRecAddsMapStruct.INSTANCE.converToEntity(infoDTO);
        orderRecAddr.setId(orderId);
        orderRecAddrService.saveOrUpdate(orderRecAddr);


        //保存订单商品参数详细表
        OrderProdInfo prodInfo = new OrderProdInfo();
        prodInfo.setId(orderId);
        prodInfo.setProdId(infoDTO.getProdId());
        prodInfo.setCategoryId(prod.getCategoryId());
        prodInfo.setBrandId(0L);
        prodInfo.setBuyNumber(infoDTO.getBuyNumber());
        prodInfo.setProdName(prod.getName());
        prodInfo.setProdComboId(infoDTO.getProdComboId());
        prodInfo.setProdComboAttrValues(infoDTO.getProdComboAttrValues());
        prodInfo.setComboCode(combo.getComboCode());
        prodInfo.setProdImg(infoDTO.getProdImg());
        prodInfo.setTips(prod.getTips());

        ProdCombo prodCombo = prodComboService.findById(infoDTO.getProdComboId());
        if (ObjectUtil.isNotNull(prodCombo.getSetMealType()) && BasicConstant.INT_2.equals(prodCombo.getSetMealType())){
            //多规格
            prodInfo.setProdComboTitle(infoDTO.getProdComboAttrValues());
        }else {
            //普通套餐
            prodInfo.setProdComboTitle(prodCombo.getComboTitle());
        }
        prodInfo.setProdComboPrice(prodCombo.getComboPrice());
        prodInfo.setProdComboStandardPrice(prodCombo.getComboStandardPrice());
        prodInfo.setProdComboCostPrice(prodCombo.getComboCostPrice());
        orderProdInfoService.saveOrUpdate(prodInfo);

        //保存订单支付记录表
        OrderPayRecord payRecord = new OrderPayRecord();
        Long payRecordId = GenerateIDUtil.nextId();
        payRecord.setId(payRecordId);
        payRecord.setOrderId(orderId);
        payRecord.setCustomerId(infoDTO.getCustomerId());
        payRecord.setPayMode(infoDTO.getTpType());
        //支付状态设置为未付款
        payRecord.setPayStatus(DictConstant.PayStatus.no_pay.getCode());
        payRecord.setOutTradeNo(number);

        orderPayRecordService.saveOrUpdate(payRecord);

    }


    @Override
    public List<AppOrderInfoListDTO> queryPageAppOrderInfo(AppOrderInfoListQO appOrderInfoListQO, PageReq pageReq) {

        // 构建查询参数
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.putAll(BeanUtils.beanToMap(appOrderInfoListQO));
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());

        return orderInfoService.queryPageAppOrderInfo(paramMap);
    }

    @Override
    public AppOrderDetailVO findOrderInfo(String number) {

        //获取订单信息
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(OrderInfo.NUMBER, number);
        OrderInfo orderInfo = orderInfoService.getOne(queryWrapper);
        AppOrderDetailVO detailVO = AppOrderInfoMapStruct.INSTANCE.converToVO(orderInfo);
        if (ObjectUtil.isNotNull(orderInfo.getOrderTime())){
            detailVO.setEndTime(DateUtil.offsetMinute(orderInfo.getOrderTime(), dealService.findDeal().getCloseOrderMinute()));
        }
        if(DictConstant.PayType.TYPE_1.getCode().equals(orderInfo.getPayType())){
            OrderPayRecord orderPayRecord = orderPayRecordService.findByOrderId(orderInfo.getId());
            detailVO.setTradingFlow(orderPayRecord.getTradingFlow());
        }
        //获取订单商品参数信息
        OrderProdInfo orderProdInfo = orderProdInfoService.findById(orderInfo.getId());
        detailVO.setProdComboTitle(orderProdInfo.getProdComboTitle());
        detailVO.setProdComboAttrValues(orderProdInfo.getProdComboAttrValues());
        detailVO.setProdComboPrice(orderProdInfo.getProdComboPrice());
        detailVO.setProdComboStandardPrice(orderProdInfo.getProdComboStandardPrice());
        detailVO.setProdComboCostPrice(orderProdInfo.getProdComboCostPrice());
        detailVO.setProdImg(orderProdInfo.getProdImg());
        detailVO.setBuyNumber(orderProdInfo.getBuyNumber());

        //获取收货地址信息
        OrderRecAddr orderRecAddr = orderRecAddrService.findById(orderInfo.getId());
        AppOrderRecAddrVO appOrderRecAddrVO = AppOrderRecAddsMapStruct.INSTANCE.converToVO(orderRecAddr);
        detailVO.setAppOrderRecAddrVO(appOrderRecAddrVO);

        ShopThird shopThird = shopThirdService.findById(orderInfo.getShopId());
        detailVO.setWxBusiness(shopThird.getWxBusiness());

        return detailVO;
    }

    @Override
    public AppOrderLogisticsVO queryOrderLogistics(Long id){

        OrderRecAddr orderRecAddr = orderRecAddrService.findById(id);
        //获取物流信息
        QueryWrapper<OrderLogistics> orderLogisticsQueryWrapper = new QueryWrapper<>();
        orderLogisticsQueryWrapper.eq(OrderLogistics.ORDER_ID, id);
        OrderLogistics orderLogistics = orderLogisticsService.getOne(orderLogisticsQueryWrapper);

        if (StrUtil.isBlank(orderLogistics.getContent())) {
            // 查询物流信息
            queryAliyunWuliu(orderLogistics, orderRecAddr.getPhone());
            orderLogisticsService.updateById(orderLogistics);
        } else {
            int defaultHour = Integer.parseInt(memoryService.queryConfig(ConfigConstant.QUERY_LOGISTICS_INTERVAL_TIME));
            // 根据最后一次更新时间和当前查询时间判断是否超过了默认3个小时，如果超过了则重新发起接口查询，如无则返回表中的信息
            long hour = DateUtil.between(orderLogistics.getGmtModified(), new Date(), DateUnit.HOUR);
            if (hour >= defaultHour) {
                queryAliyunWuliu(orderLogistics, orderRecAddr.getPhone());
                orderLogisticsService.updateById(orderLogistics);
            }
        }

        AppOrderLogisticsVO appOrderLogisticsVO = AppOrderLogisticsMapStruct.INSTANCE.converToVO(orderLogistics);
        if (ObjectUtil.isNotNull(orderLogistics)) {
            LogisticsCompany logisticsCompany = logisticsCompanyService.getOne(new QueryWrapper<LogisticsCompany>().eq(LogisticsCompany.NUMBER,orderLogistics.getLogisticsCompany()));
            appOrderLogisticsVO.setLogisticsCompanyName(logisticsCompany.getName());
        }
        return appOrderLogisticsVO;
    }


    /**
     * 查询物流信息
     *
     * @param orderLogistics
     * @param phone
     */
    private void queryAliyunWuliu(OrderLogistics orderLogistics, String phone) {
        try {
            AliyunWuliuService aliyunWuliuService = thirdConfigService.aliyunWuliuService();
            String courierNumber = orderLogistics.getCourierNumber();
            // 顺丰物流还需要额外用手机号查询
            String returnStr = (WeikbestConstant.SFEXPRESS.equals(orderLogistics.getLogisticsCompany())) ?
                    aliyunWuliuService.queryAliyunWuliu(courierNumber, phone) :
                    aliyunWuliuService.queryAliyunWuliu(courierNumber);
            orderLogistics.setContent(returnStr);
            log.info("---------查询订单{},运单号为{}的物流信息结束,物流信息：{}", orderLogistics.getOrderId(), courierNumber, returnStr);
        } catch (Exception e) {
            throw new WeikbestException(e);
        }
    }

    @Override
    public void saveOrderInfoMessage(String number,String deliverNotify,String waitPayNotify) {
        if (number.startsWith(BasicConstant.OrderNumber.MAIN_NUMBER_START_WITH)){
            List<OrderInfo> orderInfoList = orderInfoService.findByOrderMainNumber(number);
            if (CollectionUtil.isNotEmpty(orderInfoList)){
                orderInfoList.forEach(orderInfo ->{
                    orderInfo.setDeliverNotify(deliverNotify);
                    orderInfo.setWaitPayNotify(waitPayNotify);
                });
                orderInfoService.updateBatchById(orderInfoList);
            }
        }else {
            OrderInfo orderInfo = orderInfoService.findByOrderNumber(number);
            orderInfo.setDeliverNotify(deliverNotify);
            orderInfo.setWaitPayNotify(waitPayNotify);
            orderInfoService.updateById(orderInfo);
        }

    }

    @Override
    public JSONObject placeOrder(String number) {

        log.info("进入微信支付service，订单号：" + number);
        JSONObject json = new JSONObject();
        Customer customer;
        Business business;
        WxPayService wxPayService;
        WxPayConfig config;
        int total;
        boolean ProfitSharing = true;
        String appId;
        String name;
        //合并订单支付
        if (number.startsWith(BasicConstant.OrderNumber.MAIN_NUMBER_START_WITH)) {
            List<OrderInfo> orderInfoList = orderInfoService.findByOrderMainNumber(number);
            if (CollectionUtil.isEmpty(orderInfoList)) {
                throw new WeikbestException("订单信息不存在！");
            }
            wxPayService = shopThirdService.findWxPayServiceByOrderNumber(orderInfoList.get(0).getNumber());
            config = wxPayService.getConfig();
            customer = customerService.findById(orderInfoList.get(0).getCustomerId());
            business = businessService.findById(orderInfoList.get(0).getBusinessId());
            appId = orderInfoList.get(0).getAppId();
            name = orderInfoList.get(0).getName();
            if (DictConstant.BusinessType.platform.getCode().equals(business.getBusinessType())) {
                //设置普通商户才进行分账，特约（平台）商户不进行分账
                ProfitSharing = false;
            }
            //计算总金额
            BigDecimal reduce = orderInfoList.stream().map(OrderInfo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            total = reduce.multiply(new BigDecimal(100)).setScale(0,RoundingMode.HALF_UP).intValue();
        } else {//单订单支付
            wxPayService = shopThirdService.findWxPayServiceByOrderNumber(number);
            config = wxPayService.getConfig();

            OrderInfo orderInfo = orderInfoService.findByOrderNumber(number);
            Prod prod = prodService.findById(orderInfo.getProdId());
            //订单限购
            checkOrderLimit(prod,orderInfo,null);
            customer = customerService.findById(orderInfo.getCustomerId());
            business = businessService.findById(orderInfo.getBusinessId());
            appId = orderInfo.getAppId();
            name = orderInfo.getName();
            if (DictConstant.BusinessType.platform.getCode().equals(business.getBusinessType())) {
                //设置普通商户才进行分账，特约（平台）商户不进行分账
                ProfitSharing = false;
            }
            //获取订单支付金额，转换成int类型，单位分
            BigDecimal orderAmount = orderInfo.getOrderAmount();
            BigDecimal valuebig = new BigDecimal(100);
            BigDecimal totalbig = orderAmount.multiply(valuebig).setScale(0, BigDecimal.ROUND_HALF_UP);
            total = totalbig.intValue();
        }


//        log.info("读取config：MchId-" + config.getMchId() + " AppId-" + orderInfo.getAppId());

        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
        request.setMchid(config.getMchId());
        request.setAppid(appId);
        request.setDescription(name);
        request.setNotifyUrl(memoryService.queryConfig(ConfigConstant.WX_PAY_NOTIFY_URL));
        request.setOutTradeNo(number);
        request.setAmount(new WxPayUnifiedOrderV3Request.Amount().setCurrency("CNY").setTotal(total));
        request.setPayer(new WxPayUnifiedOrderV3Request.Payer().setOpenid(customer.getWxOpenid()));
        request.setSettleInfo(new WxPayUnifiedOrderV3Request.SettleInfo().setProfitSharing(ProfitSharing));

        log.info("微信支付开始调用第三方接口，订单号：" + number);
        log.info("微信合单支付开始调用第三方接口，参数：" + JSONObject.toJSONString(request));
        try {
            WxPayUnifiedOrderV3Result.JsapiResult jsapiResult = wxPayService.createOrderV3(TradeTypeEnum.JSAPI, request);
            log.info("订单号：" + number + "，微信支付返回参数：" + StringUtils.join(jsapiResult, " ; "));
            QueryWrapper<OrderPayRecord> orderPayRecordQueryWrapper = new QueryWrapper<>();
            if (number.startsWith(BasicConstant.OrderNumber.MAIN_NUMBER_START_WITH)){
                List<OrderInfo> orderInfoList = orderInfoService.findByOrderMainNumber(number);
                orderPayRecordQueryWrapper.in(OrderPayRecord.OUT_TRADE_NO, orderInfoList.stream().map(OrderInfo::getNumber).collect(Collectors.toList()));
                List<OrderPayRecord> payRecords = orderPayRecordService.list(orderPayRecordQueryWrapper);
                //支付中
                payRecords.forEach(orderPayRecord->{
                    orderPayRecord.setPayStatus(DictConstant.PayStatus.paying.getCode());
                    orderPayRecord.setPayRespContent(jsapiResult.toString());
                });
                orderPayRecordService.saveOrUpdateBatch(payRecords);
            }else {
                orderPayRecordQueryWrapper.eq(OrderPayRecord.OUT_TRADE_NO, number);
                OrderPayRecord payRecord = orderPayRecordService.getOne(orderPayRecordQueryWrapper);
                //支付中
                payRecord.setPayStatus(DictConstant.PayStatus.paying.getCode());
                payRecord.setPayRespContent(jsapiResult.toString());
                orderPayRecordService.saveOrUpdate(payRecord);
            }
            json.put("timeStamp", jsapiResult.getTimeStamp());
            json.put("packageValue", jsapiResult.getPackageValue());
            json.put("paySign", jsapiResult.getPaySign());
            json.put("appId", jsapiResult.getAppId());
            json.put("signType", jsapiResult.getSignType());
            json.put("nonceStr", jsapiResult.getNonceStr());
        } catch (WxPayException e) {
            log.error("调用微信支付失败！, {}", e.getCustomErrorMsg(), e);
            throw new WeikbestException("调用微信支付失败!" + e.getCustomErrorMsg());
        }
        return json;
    }

    @Override
    public JSONObject placeCombineOrder(String number) {
        log.info("进入微信合单支付service，主订单号：" + number);
        JSONObject json = new JSONObject();

        List<OrderInfo> orderInfoList = orderInfoService.findByOrderMainNumber(number);
        if (CollectionUtil.isEmpty(orderInfoList)){
            throw new WeikbestException("订单信息不存在！");
        }

        Customer customer = customerService.findById(orderInfoList.get(0).getCustomerId());
        boolean ProfitSharing = true;
        Business business = businessService.findById(orderInfoList.get(0).getBusinessId());
        if(DictConstant.BusinessType.platform.getCode().equals(business.getBusinessType())){
            //设置普通商户才进行分账，特约（平台）商户不进行分账
            ProfitSharing = false;
        }
//        PayConfig one = payConfigService.getOne(new QueryWrapper<PayConfig>().eq(PayConfig.WX_PAY_MCH_ID, "1630082993"));
//        WxPayConfig wxPayConfig = null;
//        if (ObjectUtil.isNotNull(one)) {
//             wxPayConfig = PayConfigMapStruct.INSTANCE.converToWxPayConfig(one);
//        }
//        WxPayService wxPayService1 = thirdConfigService.wxPayService(wxPayConfig);
        WxPayService wxPayService = shopThirdService.findWxPayServiceByOrderNumber(orderInfoList.get(0).getNumber());
        WxPayConfig config = wxPayService.getConfig();
        //合单支付请求参数
        CombineTransactionsRequest combineTransactionsRequest = new CombineTransactionsRequest();
        combineTransactionsRequest.setCombineAppid(orderInfoList.get(0).getAppId());
        combineTransactionsRequest.setCombineMchid(config.getMchId());
        combineTransactionsRequest.setCombineOutTradeNo(number);
        ArrayList<CombineTransactionsRequest.SubOrders> subOrdersArrayList = new ArrayList<>();
        //子单信息配置
        for (OrderInfo orderInfo : orderInfoList) {
            //子单信息

            CombineTransactionsRequest.SubOrders subOrders = new CombineTransactionsRequest.SubOrders();
            subOrders.setMchid(config.getMchId());
            subOrders.setAttach(orderInfo.getAppId());
            //订单金额信息
            BigDecimal total = orderInfo.getOrderAmount().multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
            CombineTransactionsRequest.Amount amount = new CombineTransactionsRequest.Amount();
            amount.setCurrency("CNY");
            amount.setTotalAmount(total.intValue());
            subOrders.setAmount(amount);
            subOrders.setOutTradeNo(orderInfo.getNumber());
            subOrders.setDescription(orderInfo.getName());
            //结算信息
            CombineTransactionsRequest.SettleInfo settleInfo = new CombineTransactionsRequest.SettleInfo();
            settleInfo.setProfitSharing(ProfitSharing);
            subOrders.setSettleInfo(settleInfo);
            subOrdersArrayList.add(subOrders);
        }
        combineTransactionsRequest.setSubOrders(subOrdersArrayList);
        //支付者信息
        CombineTransactionsRequest.CombinePayerInfo combinePayerInfo = new CombineTransactionsRequest.CombinePayerInfo();
        combinePayerInfo.setOpenid(customer.getWxOpenid());
        combineTransactionsRequest.setCombinePayerInfo(combinePayerInfo);
        //回调地址
        combineTransactionsRequest.setNotifyUrl(memoryService.queryConfig(ConfigConstant.WX_COMBINE_PAY_NOTIFY_URL));
        log.info("微信合单支付开始调用第三方接口，参数：" + JSONObject.toJSONString(combineTransactionsRequest));
        try {
            CombineTransactionsResult.JsapiResult combineResult = wxPayService.combineTransactions(TradeTypeEnum.JSAPI, combineTransactionsRequest);
            log.info("主订单号：" + number + "，微信支付返回参数：" + StringUtils.join(combineResult, " ; "));
            QueryWrapper<OrderPayRecord> orderPayRecordQueryWrapper = new QueryWrapper<>();
            orderPayRecordQueryWrapper.in(OrderPayRecord.OUT_TRADE_NO, orderInfoList.stream().map(OrderInfo::getNumber).collect(Collectors.toList()));
            List<OrderPayRecord> payRecords = orderPayRecordService.list(orderPayRecordQueryWrapper);
            //支付中
            payRecords.forEach(orderPayRecord->{
                orderPayRecord.setPayStatus(DictConstant.PayStatus.paying.getCode());
                orderPayRecord.setPayRespContent(combineResult.toString());
            });
            orderPayRecordService.saveOrUpdateBatch(payRecords);
            //封装返回参数
            json.put("timeStamp", combineResult.getTimeStamp());
            json.put("packageValue", combineResult.getPackageValue());
            json.put("paySign", combineResult.getPaySign());
            json.put("appId", combineResult.getAppId());
            json.put("signType", combineResult.getSignType());
            json.put("nonceStr", combineResult.getNonceStr());
        }catch (WxPayException e){
            log.error("调用微信支付失败！, {}",e.getCustomErrorMsg(),e);
            throw new WeikbestException("调用微信支付失败!"+e.getCustomErrorMsg());
        }
        return json;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean changeOrderStatus(String number, String orderStatus) {

        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(OrderInfo.NUMBER, number);
        OrderInfo orderInfo = orderInfoService.getOne(queryWrapper);

        OrderStatRecord statRecord = new OrderStatRecord();
        Long statRecordId = GenerateIDUtil.nextId();
        statRecord.setId(statRecordId);
        statRecord.setOrderId(orderInfo.getId());
        statRecord.setCurrentState(orderInfo.getOrderStatus());
        statRecord.setChangeStatus(orderStatus);
        statRecord.setChangerUserId(currentUserService.getAppTokenUser().getUserId());
        statRecord.setChangerUser(currentUserService.getAppTokenUser().getLoginNameOrPhone());
        statRecord.setChangeTime(DateUtil.date());
        statRecord.setDescription("订单状态变更");
        orderStatRecordService.save(statRecord);

        boolean bool = false;

        // 更新订单变更记录
        String key = RedisKey.generalKey(RedisKey.Lock.LOCK_ORDER_STATUS_KEY, orderInfo.getId());
        redisLock.lock(key);
        try {
            orderInfo.setOrderStatus(orderStatus);
            if(DictConstant.OrderStatus.orderStatus_30.getCode().equals(orderStatus)){
                orderInfo.setOrderFinishTime(DateUtil.date());
            }
            bool = orderInfoService.updateById(orderInfo);
        } finally {
            redisLock.unlock(key);
        }

        if(DictConstant.OrderStatus.orderStatus_99.getCode().equals(orderStatus)){
            //如果是待付款订单，则将优惠劵回复到之前的状态
            if (DictConstant.OrderStatus.orderStatus_1.getCode().equals(statRecord.getCurrentState())){
                Long custCouponRestrictId = orderInfo.getCustCouponRestrictId();
                if (ObjectUtils.isNotEmpty(custCouponRestrictId) && custCouponRestrictId != 0){
                    custCouponRestrictService.saveCustCouponRestrictType(custCouponRestrictId);
                }
            }
            /*// 如果该笔订单使用了优惠券（平台劵，立减劵），则还需要做退券处理
            if (StrUtil.isNotBlank(orderInfo.getCouponType()) && !StrUtil.equals(orderInfo.getCouponType(), DictConstant.CouponType.TYPE_2.getCode())) {


                // 查询客户领券信息
                Long custCouponRestrictId = orderInfo.getCustCouponRestrictId();
                CustCouponRestrict custCouponRestrict = custCouponRestrictService.findById(custCouponRestrictId);

                WxPayService wxPayService = null;

                if(custCouponRestrict.getCouponType().equals(DictConstant.CouponType.TYPE_3.getCode())){
                    //如果是平台优惠券，则取平台数据
                    wxPayService = shopThirdService.findCouponWxPayServicePayConfig();
                }else{
                    wxPayService = shopThirdService.findWxPayServiceByOrderNumber(number);
                }

                // 查询优惠券信息
                Coupon coupon = couponService.queryCouponById(orderInfo.getCouponId());
                String returnRequestNo = OrderUtil.getNoncestr();
                try {
                    // 调用微信退券接口
                    MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();
                    BusiFavorCouponsReturnRequest request = new BusiFavorCouponsReturnRequest();
                    request.setCouponCode(custCouponRestrict.getCouponCode());
                    request.setStockId(coupon.getStockId());
                    request.setReturnRequestNo(returnRequestNo);
                    BusiFavorCouponsReturnResult busiFavorCouponsReturnResult = marketingBusiFavorService.returnBusiFavorCoupons(request);

                    // 更新客户领券信息
                    custCouponRestrict.setIsCouponsReturn(DictConstant.Whether.yes.getCode()).setReturnRequestNo(returnRequestNo).setCouponsReturnTime(DateUtil.parse(busiFavorCouponsReturnResult.getWechatpayReturnTime()));
                    custCouponRestrictService.updateById(custCouponRestrict);
                    custCouponRestrictService.saveCustCouponRestrictType(custCouponRestrictId);
                    // 重新设置延时队列
                    // 设置未生效到未使用延时队列
                    appCouponRestrictTypeNotUsedDelayTaskProcess.putTask(custCouponRestrictId, coupon.getUseStartTime().getTime());
                    // 设置未使用到已过期延时队列
                    appCouponRestrictTypeExpiredDelayTaskProcess.putTask(custCouponRestrictId, coupon.getUseEndTime().getTime());
                } catch (WxPayException e) {
                    log.error("调用微信退券接口失败! " + e.getCustomErrorMsg(), e);
                    throw new WeikbestException(ResultConstant.THIRD_SERVICE_FAIL.getCode(), e.getCustomErrorMsg(), e);
                }
            }*/
        }

        if(DictConstant.OrderStatus.orderStatus_30.getCode().equals(orderStatus)){
            orderDeliverCustomerTimeoutDelayTaskProcess.removeTask(orderInfo.getId());
        }

        return bool;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean wxPayNotifyUrl(String ciphertext, String reqParams) {
        Boolean bool = false;
        //1，保存订单信息
        //获取订单支付金额
        int payerTotal = JSONObject.parseObject(ciphertext).getJSONObject("amount").getInteger("payer_total");
        //订单总金额
        int total = JSONObject.parseObject(ciphertext).getJSONObject("amount").getInteger("total");
        //获取微信支付订单号
        String transactionId = JSONObject.parseObject(ciphertext).getString("transaction_id");
        //获取订单号
        String outtradeno = JSONObject.parseObject(ciphertext).getString("out_trade_no");
        //获取支付时间
        String successtime = JSONObject.parseObject(ciphertext).getString("success_time");

        log.info("支付回调信息处理：单号：{}，流水号：{}，支付金额：{}",outtradeno,transactionId,payerTotal);
        //回调返回是M开头的合单
        if (outtradeno.startsWith(BasicConstant.OrderNumber.MAIN_NUMBER_START_WITH)) {
            List<OrderInfo> orderInfoList = orderInfoService.findByOrderMainNumber(outtradeno);
            if (CollectionUtil.isEmpty(orderInfoList)) {
                return false;
            }
            BigDecimal reduce = orderInfoList.stream().map(OrderInfo::getOrderAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            int totalInt = reduce.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).intValue();
            if (totalInt == total) {//订单总金额和回调支付金额相同
                for (OrderInfo orderInfo : orderInfoList) {
                    int payAmount = orderInfo.getOrderAmount().multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).intValue();
                    savePayInfoAndShopFinance(ciphertext, reqParams, payAmount, total, transactionId, orderInfo.getNumber(), successtime);
                }
            } else {//金额不同
                //计算其他优惠金额
                int discountAmount = totalInt - payerTotal;
                //按订单金额排序
                orderInfoList.sort(Comparator.comparing(OrderInfo::getOrderAmount).reversed());
                for (int i = 0; i < orderInfoList.size(); i++) {
                    if (i == 0) {
                        //在金额大的上面减去支付优惠的金额
                        int payAmount = orderInfoList.get(i).getOrderAmount().multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).intValue() - discountAmount;
                        savePayInfoAndShopFinance(ciphertext, reqParams, payAmount, total, transactionId, orderInfoList.get(i).getNumber(), successtime);
                    } else {
                        int payAmount = orderInfoList.get(i).getOrderAmount().multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).intValue();
                        savePayInfoAndShopFinance(ciphertext, reqParams, payAmount, total,transactionId, orderInfoList.get(i).getNumber(), successtime);
                    }
                }
            }


        } else {//普通支付
            bool = savePayInfoAndShopFinance(ciphertext, reqParams, total, total, transactionId, outtradeno, successtime);

        }
        //处理支付后业务逻辑
        return bool;
    }

    /**
     * 1.保存支付相关信息
     * 2.优惠券状态更新
     * 3.分账处理
     * 4.延迟队列配置
     * @param ciphertext
     * @param reqParams
     * @param payertotal
     * @param total
     * @param transactionId
     * @param outtradeno
     * @param successtime
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean savePayInfoAndShopFinance(String ciphertext, String reqParams, int payertotal, int total, String transactionId, String outtradeno, String successtime) {
        QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>();
        orderInfoQueryWrapper.eq(OrderInfo.NUMBER, outtradeno);
        OrderInfo orderInfo = orderInfoService.getOne(orderInfoQueryWrapper);
        //处理回归消息复制通知
        if (!"1".equals(orderInfo.getOrderStatus())) {
            return null;
        }

        //待发货
        orderInfo.setOrderStatus(DictConstant.OrderStatus.orderStatus_10.getCode());
        DateTime dateTime = new DateTime(successtime);
        Long timeInMillis = dateTime.toCalendar(Locale.getDefault()).getTimeInMillis();
        orderInfo.setPayTime(new Date(timeInMillis));
        orderInfo.setPayAmount(WxPayAmountConvertUtil.divideConvert(payertotal));


        //2，保存订单支付记录信息
        QueryWrapper<OrderPayRecord> orderPayRecordQueryWrapper = new QueryWrapper<>();
        orderPayRecordQueryWrapper.eq(OrderPayRecord.OUT_TRADE_NO, outtradeno);
        OrderPayRecord payRecord = orderPayRecordService.getOne(orderPayRecordQueryWrapper);
        payRecord.setPayAmount(WxPayAmountConvertUtil.divideConvert(payertotal));
        payRecord.setTotalAmount(WxPayAmountConvertUtil.divideConvert(total));
        payRecord.setPayStatus(DictConstant.PayStatus.success.getCode());
        payRecord.setPayTime(orderInfo.getPayTime());
        payRecord.setPayActivityPeriod(0);
        payRecord.setPayContent(ciphertext);
        payRecord.setTradingFlow(transactionId);
        payRecord.setCallbackContent(reqParams);
        payRecord.setCallbackTime(new Date());
        //默认设置为未结算
        payRecord.setIsSettle("0");
        orderPayRecordService.saveOrUpdate(payRecord);

        //3，保存订单店铺资金明细表，交易账单记录
        QueryWrapper<ShopFinanceAccount> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq(ShopFinanceAccount.SHOP_ID,orderInfo.getShopId());
        ShopFinanceAccount account = shopFinanceAccountService.getOne(accountQueryWrapper);

        ShopFinanceDetail detail = new ShopFinanceDetail();
        Long sfdId = GenerateIDUtil.nextId();
        detail.setId(sfdId);
        detail.setShopId(orderInfo.getShopId());
        detail.setFinanceAccountId(account.getId());
        detail.setFinanceType(DictConstant.FinanceType.financetype_1.getCode());
        detail.setCapitalFlowType("1");
        detail.setEnterTime(DateUtil.date());
        detail.setWxOrderId(transactionId);
        detail.setAmountDetail(orderInfo.getPayAmount());
        detail.setNumber(orderInfo.getNumber());
        shopFinanceDetailService.save(detail);

        //保存店铺对账单明细表
        ShopStatementDetail shopStatementDetail = new ShopStatementDetail();
        Long shopStatementDetailId = GenerateIDUtil.nextId();
        shopStatementDetail.setId(shopStatementDetailId);
        //对账单类型 1-卖出交易
        shopStatementDetail.setStatementType("1");
        shopStatementDetail.setShopId(orderInfo.getShopId());
        shopStatementDetail.setCustomerId(orderInfo.getCustomerId());
        shopStatementDetail.setProdName(orderInfo.getName());
        shopStatementDetail.setOrderTime(orderInfo.getOrderTime());
        shopStatementDetail.setPayTraceId(transactionId);
        //结算状态 0-未结算 1-已结算
        shopStatementDetail.setSettleType("0");
        shopStatementDetail.setPayType("1");
        shopStatementDetail.setPayTime(orderInfo.getPayTime());
        shopStatementDetail.setPayAmount(orderInfo.getPayAmount());
        shopStatementDetail.setNumber(orderInfo.getNumber());
        shopStatementDetailService.save(shopStatementDetail);

        //4，保存订单状态变更信息
        OrderStatRecord statRecord = new OrderStatRecord();
        Long statRecordId = GenerateIDUtil.nextId();
        statRecord.setId(statRecordId);
        statRecord.setOrderId(orderInfo.getId());
        statRecord.setCurrentState("1");
        statRecord.setChangeStatus("10");
        statRecord.setChangerUserId(0L);
        statRecord.setChangerUser("系统");
        statRecord.setChangeTime(DateUtil.date());
        statRecord.setDescription("支付回调通知");
        orderStatRecordService.save(statRecord);

        //5，优惠券核销
        ThreadUtil.execute(()->checkCoupon(orderInfo));

        //6，分账业务
        log.info("==============进入处理分账业务===============订单号： " +orderInfo.getNumber());
        Business business = businessService.findById(orderInfo.getBusinessId());

        //1.0先注释平台分账，只处理商家分账，等2.0再放开
        /**
         //读取系统配置的 订单来源分账比例表
         QueryWrapper<OrderSourceScale> orderSourceScaleQueryWrapper = new QueryWrapper<>();
         orderSourceScaleQueryWrapper.eq(OrderSourceScale.ORDER_SOURCE,orderInfo.getOrderSource());
         orderSourceScaleQueryWrapper.eq(OrderSourceScale.ORDER_PATH,orderInfo.getOrderPath());
         List<OrderSourceScale> orderSourceScales = orderSourceScaleService.list(orderSourceScaleQueryWrapper);
         if(orderSourceScales != null && orderSourceScales.size() >0) {
         OrderSourceScale orderSourceScale = orderSourceScales.get(0);

         if (business.getBusinessType().equals(DictConstant.BusinessType.platform.getCode())) {
         //该订单如果是特约商户（平台商户），则分账给普通商户
         log.info("==============平台订单分账给普通商户===============商户分账比例： " +orderSourceScale.getBusinessScale());
         if(ObjectUtil.isNotEmpty(orderInfo.getWxPayReceiveMchId()) &&
         !orderSourceScale.getBusinessScale().equals(BigDecimal.ZERO)){

         BigDecimal orderAmount = orderInfo.getOrderAmount();
         BigDecimal valuebig = new BigDecimal(100);
         BigDecimal totalbig = orderAmount.multiply(valuebig).
         multiply(orderSourceScale.getBusinessScale()).
         setScale(0,BigDecimal.ROUND_HALF_UP);
         Long total = totalbig.longValue();

         String sta = doreceiver(orderInfo,transactionId,orderInfo.getWxPayReceiveMchId(),total,"1");
         //设置为分账订单
         orderInfo.setIsReceiveOrder("1");
         orderInfo.setOrderReceiveStatus(sta);
         }
         }else {
         //该订单如果是普通商户，则分账给平台
         log.info("==============普通商户订单分账给平台===============平台分账比例： " +orderSourceScale.getPlatformScale());
         //先查询该商户是否设置平台分账方信息，如果为空则不分账
         ShopThirdReceive shopThirdReceive = shopThirdReceiveService.getById(orderInfo.getShopId());
         if(ObjectUtil.isNotNull(shopThirdReceive) &&
         !orderSourceScale.getPlatformScale().equals(BigDecimal.ZERO)){

         BigDecimal orderAmount = orderInfo.getOrderAmount();
         BigDecimal valuebig = new BigDecimal(100);
         BigDecimal totalbig = orderAmount.multiply(valuebig).
         multiply(orderSourceScale.getPlatformScale()).
         setScale(0,BigDecimal.ROUND_HALF_UP);
         Long total = totalbig.longValue();

         String sta = doreceiver(orderInfo,transactionId,shopThirdReceive.getWxPayReceiveMchId(),total,"3");
         //设置为分账订单
         orderInfo.setIsReceiveOrder("1");
         orderInfo.setOrderReceiveStatus(sta);
         }
         }
         }**/

        Settle settle = settleService.findSettle();
        BigDecimal receiveScale =  settle.getSplitDepositRatio();
        //该订单如果是普通商户，则分账押金
        log.info("==============普通商户订单分账押金给平台===============分账押金比率： " +receiveScale);
        //先查询该商户是否设置平台分账方信息，如果为空则不分账
        QueryWrapper<ShopThirdReceive> shopThirdReceiveQueryWrapper = new QueryWrapper<>();
        shopThirdReceiveQueryWrapper.eq(ShopThirdReceive.ID,orderInfo.getShopId());
        List<ShopThirdReceive> shopThirdReceives = shopThirdReceiveService.list(shopThirdReceiveQueryWrapper);
        //如果是普通商户并设置了分账方，则进行分账并设置该订单为分账订单
        if(business.getBusinessType().equals(DictConstant.BusinessType.normal.getCode()) &&
                shopThirdReceives != null && shopThirdReceives.size() > 0) {
            ShopThirdReceive shopThirdReceive = shopThirdReceives.get(0);
            if (ObjectUtil.isNotNull(shopThirdReceive) &&
                    receiveScale.compareTo(BigDecimal.ZERO) != WeikbestConstant.ZERO_INT) {

                BigDecimal valuebig = new BigDecimal(100);
                BigDecimal deposit = receiveScale.divide(valuebig);
                BigDecimal orderAmount = orderInfo.getPayAmount();
                BigDecimal totalbig = orderAmount.multiply(valuebig).
                        multiply(deposit).
                        setScale(0, BigDecimal.ROUND_HALF_UP);
                Long totalAmount = totalbig.longValue();

                //设置为分账订单
                orderInfo.setIsReceiveOrder("1");

                ThreadUtil.execute(() -> {
                    doreceiver(receiveScale, orderInfo.getId(), transactionId, shopThirdReceive.getWxPayReceiveMchId(), totalAmount, "3");
                });
            }
        }

        log.info("==============结束处理分账业务===============订单号： " +orderInfo.getNumber());
        log.info("订单信息设置成功,当前订单参数信息: {}" , orderInfo);

        //保存订单相关信息
        Boolean bool = orderInfoService.saveOrUpdate(orderInfo);

        //另起线程执行广告回传
        ThreadUtil.execute(()->doAdCallback(orderInfo));

        //7，客户绑定商家
        //1.0 取消客户绑定商家功能，2.0再放开
        /***
         log.info("==============进入客户绑定商户业务===============订单号： " +orderInfo.getNumber());
         if(DictConstant.OrderSource.TYPE_5.getCode().equals(orderInfo.getOrderSource())){
         //只有客户通过广告投放进入小程序才处理绑定关系
         dobind(orderInfo);
         }
         log.info("==============结束客户绑定商户业务===============订单号： " +orderInfo.getNumber());
         **/

        //9，延时任务
        //把订单信息加入到延时队列中
        orderDeliverTimeoutDelayTaskProcess.putTask(orderInfo.getId());
        //取消超过24小时未支付修改订单状态
        orderPayTimeoutDelayTaskProcess.removeTask(orderInfo.getId());
        //取消超过20分钟未支付短信/微信消息提醒延时任务
//        orderWaitPayTimeoutDelayTaskProcess.removeTask(orderInfo.getId());

        return bool;
    }

    /**
     * 处理腾讯回传业务
     * @param orderInfo
     */
    @Transactional(rollbackFor = Exception.class)
    public void doAdCallback(OrderInfo orderInfo) {
        // 8.处理腾讯广告业务
        if(StrUtil.isNotEmpty(orderInfo.getAdAid())) {
            PayBackProdAdvBackAccountVO payBackProdAdvBackAccountVO = prodDecFloorService.findPayBackAndBackRatio(orderInfo.getProdId());
            //广告引流，必回传
            if (DictConstant.OrderSource.TYPE_5.getCode().equals(orderInfo.getOrderSource())){
                // 设置广告账户和回传比率
                orderInfo.setAdvAccountId(payBackProdAdvBackAccountVO.getAdvAccountId())
                        .setBackRatio(new BigDecimal(100));
                //执行返回方法,需要回传腾讯广告，
                adReturnback(orderInfo, payBackProdAdvBackAccountVO,DictConstant.AdReturnSource.ADVERTISING_LEADS_INPUT.getCode());
            }
            else if (DictConstant.OrderSource.TYPE_20.getCode().equals(orderInfo.getOrderSource())){
                if (ObjectUtil.isNull(orderInfo.getCouponId()) || ObjectUtil.isNull(orderInfo.getProdId())){
                    return;
                }
                ProdCouponRelate one = prodCouponRelateService.getOne(new QueryWrapper<ProdCouponRelate>()
                        .lambda()
                        .eq(ProdCouponRelate::getId, orderInfo.getProdId())
                        .eq(ProdCouponRelate::getCouponId, orderInfo.getCouponId()));
                if (ObjectUtil.isNull(one) || ObjectUtil.isNull(one.getEntryId())){
                    return;
                }
                ProdCoupon prodCoupon = prodCouponService.findById(one.getEntryId());
                if (ObjectUtil.isNull(prodCoupon) || ObjectUtil.isNull(prodCoupon.getCallbackProportion())){
                    return;
                }
                orderInfo.setAdvAccountId(payBackProdAdvBackAccountVO.getAdvAccountId())
                        .setBackRatio(prodCoupon.getCallbackProportion());
                payBackProdAdvBackAccountVO.setBackRatio(prodCoupon.getCallbackProportion());
                adReturnback(orderInfo, payBackProdAdvBackAccountVO,DictConstant.AdReturnSource.RETURN_PAGES_INPUT.getCode());
            }
            if (ObjectUtil.isNotNull(payBackProdAdvBackAccountVO) &&
                    StrUtil.equals(payBackProdAdvBackAccountVO.getSuccessPayBack(), DictConstant.Whether.yes.getCode())) {
                // 设置广告账户和回传比率
                orderInfo.setAdvAccountId(payBackProdAdvBackAccountVO.getAdvAccountId())
                        .setBackRatio(payBackProdAdvBackAccountVO.getBackRatio());
                log.info("订单的prodId为: {} , 广告账号为: {}" , orderInfo.getProdId() , orderInfo.getAdvAccountId());
                //查询返回页表，判断订单下单来源，看该返回页是否开启回传
                if (ObjectUtil.isNotNull(orderInfo.getProdReturnPageId()) && DictConstant.OrderSource.TYPE_10.getCode().equals(orderInfo.getOrderSource())){
                    ProdReturn one = prodReturnService.getOne(new QueryWrapper<ProdReturn>().lambda().eq(ProdReturn::getId, orderInfo.getProdReturnPageId()).eq(ProdReturn::getProdId, orderInfo.getProdId()));
                    //返回页开启回传
                    if (ObjectUtil.isNotNull(one) && Objects.equals(one.getOpenOrNot(), Integer.valueOf(DictConstant.Whether.yes.getCode()))){
                        //执行返回方法
                        // 需要回传腾讯广告,返回页进入 需要判定命中率
                        adReturnback(orderInfo, payBackProdAdvBackAccountVO,DictConstant.AdReturnSource.RETURN_PAGES_INPUT.getCode());
                    }
                }
            }

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void wxCombinePayNotifyUrl(String ciphertext, String reqParams) {
        //将返回参数变为对象
        JSONObject cipher = JSONObject.parseObject(ciphertext);
        List<Map> orderList = (List<Map>) cipher.get("sub_orders");
        if (CollectionUtil.isNotEmpty(orderList)) {
            orderList.forEach(order->{
                //订单号
                String outTradeNo = MapUtil.getStr(order, "out_trade_no");
                //交易成功时间
                String successTime = MapUtil.getStr(order, "success_time");
                //流水号
                String transactionId = MapUtil.getStr(order, "transaction_id");
                //金额
                Map amount = MapUtil.get(order, "amount", Map.class);
                Integer payerAmount = MapUtil.getInt(amount, "payer_amount");
                Integer totalAmount = MapUtil.getInt(amount, "total_amount");

                log.info("合单支付回调信息处理：单号：{}，流水号：{}，支付金额：{}",outTradeNo,transactionId,payerAmount);

                //处理支付后业务逻辑
                savePayInfoAndShopFinance(ciphertext, reqParams, payerAmount, totalAmount,transactionId, outTradeNo, successTime);
            });
        }


    }

    /**
     * 广告回传
     * @param orderInfo
     * @param payBackProdAdvBackAccountVO
     * @param source 来源 {@link DictConstant.AdReturnSource}
     */
    @Transactional(rollbackFor = Exception.class)
    public void adReturnback(OrderInfo orderInfo, PayBackProdAdvBackAccountVO payBackProdAdvBackAccountVO,String source) {
        Customer customer = customerService.findById(orderInfo.getCustomerId());
        TencentAdvUserrecordSendDTO tencentAdvUserrecordSendDTO = new TencentAdvUserrecordSendDTO();
        tencentAdvUserrecordSendDTO.setOrderId(orderInfo.getId())
                .setBackRatio(ObjectUtil.isNull(payBackProdAdvBackAccountVO) ? BigDecimal.ZERO : payBackProdAdvBackAccountVO.getBackRatio())
                .setAdAid(orderInfo.getAdAid())
                .setClickId(orderInfo.getClickId())
                .setProdId(orderInfo.getProdId())
                .setWechatAppId(orderInfo.getAppId())
                .setWechatOpenid(customer.getWxOpenid())
                .setWechatUnionid(customer.getWxUnionid());
        if (DictConstant.PayType.TYPE_2.getCode().equals(orderInfo.getPayType())){
            tencentAdvUserrecordSendDTO.setPayAmount(orderInfo.getDeliveryPayAmount());
        }else {
            tencentAdvUserrecordSendDTO.setPayAmount(orderInfo.getPayAmount());
        }
        String isAdvBack = tencentAdvUserrecordService.sendWithRatio(tencentAdvUserrecordSendDTO, source,orderInfo.getPayType());
        orderInfo.setIsAdvBack(isAdvBack);
        if (DictConstant.Whether.yes.getCode().equals(isAdvBack) && DictConstant.OrderSource.TYPE_10.getCode().equals(orderInfo.getOrderSource())) {
            orderInfo.setOrderSource(DictConstant.OrderSource.TYPE_15.getCode());
        }
        boolean b = orderInfoService.updateById(orderInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void dobind(OrderInfo orderInfo){
        try{

            QueryWrapper<CustBusinessBind> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(CustBusinessBind.CUSTOMER_ID,orderInfo.getCustomerId());
            queryWrapper.eq(CustBusinessBind.BIND_STATUS,"1");
            queryWrapper.orderByDesc(CustBusinessBind.BIND_TIME);
            List<CustBusinessBind> binds = custBusinessBindService.list(queryWrapper);
            if(binds != null && binds.size() > 0){
                CustBusinessBind bind = binds.get(0);
                log.info("订单号： "+orderInfo.getNumber()+" 对应客户已存在绑定关系，无需再次绑定，绑定ID： " +bind.getId());
            }else{

                //查询商家店铺连续21天是否有广告流量订单真实成交，如果有则绑定，没有则不进行绑定
                Settle settle = settleService.findSettle(true);
                Date continueTime = DateUtil.offsetDay(DateUtil.date(), -settle.getBindCustomerContinueDay());
                QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>();
                orderInfoQueryWrapper.eq(OrderInfo.BUSINESS_ID,orderInfo.getBusinessId());
                orderInfoQueryWrapper.eq(OrderInfo.ORDER_SOURCE,DictConstant.OrderSource.TYPE_5.getCode());
                orderInfoQueryWrapper.eq(OrderInfo.ORDER_STATUS,DictConstant.OrderStatus.orderStatus_30.getCode());
                orderInfoQueryWrapper.ge(OrderInfo.ORDER_FINISH_TIME,continueTime);
                Long count = orderInfoService.count(orderInfoQueryWrapper);
                if(count > 0){
                    //存在21天广告流量订单真实成交数据，进行绑定
                    log.info("订单号： "+orderInfo.getNumber()+" 对应客户无绑定商户，现在开始进行绑定，商户ID： " +orderInfo.getBusinessId());

                    CustBusinessBind bind = new CustBusinessBind();
                    Long Id = GenerateIDUtil.nextId();
                    bind.setId(Id);
                    bind.setCustomerId(orderInfo.getCustomerId());
                    bind.setBusinessId(orderInfo.getBusinessId());
                    bind.setShopId(orderInfo.getShopId());
                    bind.setProdId(orderInfo.getProdId());
                    bind.setNumber(orderInfo.getNumber());
                    bind.setAppId(orderInfo.getAppId());
                    bind.setBindTime(DateUtil.date());
                    bind.setValidityDay(settle.getBindCustomerMaxDay().toString());
                    bind.setBindStatus("1");
                    custBusinessBindService.save(bind);
                    log.info("订单号： "+orderInfo.getNumber()+" 绑定成功，商户ID： " +orderInfo.getBusinessId());

                    //设置解除绑定延时队列
                    appOrderCustBusinessBindDelayTaskProcess.putTask(Id,DateUtil.offsetDay(DateUtil.date(), settle.getBindCustomerMaxDay()).getTime());

                }
            }

        }catch(Exception e){
            log.warn("订单号：" + orderInfo.getNumber() + " 客户绑定商户失败，跳过..."+e.getMessage());
        }
    }

    /**
     * 分账
     * @param scale
     * @param orderId
     * @param transactionId
     * @param mchId
     * @param amount
     * @param businessType
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String doreceiver(BigDecimal scale,Long orderId,String transactionId,String mchId,Long amount,String businessType){

        String status = "0";
        try{

            Thread.sleep(60 * 1000);

            OrderInfo orderInfo = orderInfoService.findById(orderId);

            String outRequestNo = OrderUtil.getOutRequestNo();
            log.info("==============进入微信分账接口==============申请号： "+outRequestNo);
            WxPayService wxPayService = shopThirdService.findWxPayServiceById(orderInfo.getShopId());
            ProfitSharingRequest request = new ProfitSharingRequest();
            request.setAppid(orderInfo.getAppId());
            request.setTransactionId(transactionId);
            request.setOutOrderNo(outRequestNo);
            List<ProfitSharingReceiver> receivers = new ArrayList<>();
            ProfitSharingReceiver receiver = new ProfitSharingReceiver();
            receiver.setType("MERCHANT_ID");
            receiver.setAccount(mchId);
            receiver.setAmount(amount);
            receiver.setDescription("分账给商户（ID）或平台（空）："+orderInfo.getReceiveBussinessId());
            receivers.add(receiver);
            request.setReceivers(receivers);
            request.setUnfreezeUnsplit(false);
            ProfitSharingResult response = wxPayService.getProfitSharingV3Service().profitSharing(request);
            String wxorderId = response.getOrderId();
            String state = response.getState();
            String result = response.getReceivers().get(0).getResult();
            log.info("===分账成功，申请号： "+response.getOutOrderNo() +" 分账状态： "+ state +" 分账结果： "+result);

            BigDecimal valuebig = new BigDecimal(100);
            BigDecimal amountbig = new BigDecimal(amount);
            BigDecimal vamountbig = amountbig.divide(valuebig);

            OrderReceiveRecord record = new OrderReceiveRecord();
            Long recordId = GenerateIDUtil.nextId();
            record.setId(recordId);
            record.setNumber(orderInfo.getNumber());
            record.setBusinessType(businessType);
            record.setWxOrderId(wxorderId);
            record.setOutTradeNo(outRequestNo);
            record.setTransactionId(transactionId);
            record.setMchId(mchId);
            record.setAmount(amount.intValue());
            record.setReceiveTime(DateUtil.date());
            record.setDetailId(response.getReceivers().get(0).getDetailId());
            record.setReceiveStates(state);
            record.setReceiveResult(result);
            record.setReceiveFailReason(response.getReceivers().get(0).getFailReason());
            //设置为已分账
            if("SUCCESS".equals(result)){
                record.setReceiveRecordStatus("1");
                status =  "1";
            }else if("CLOSED".equals(result)){
                record.setReceiveRecordStatus("4");
                status =  "3";
            }else{
                record.setReceiveRecordStatus("0");
                //todo 如果未返回成功 主动调用分账结果查询接口
            }
            //设置分账类型，分账类型：1-平台分账扣款、2-平台分账回退、3-平台售后分账回退、4-技术服务费回退
            record.setReceiveType("1");
            record.setDescription("平台分账扣款，分账押金");
            record.setReceiveScale(scale);

            orderReceiveRecordService.save(record);

            //保存订单店铺资金明细表，交易账单记录
            QueryWrapper<ShopFinanceAccount> accountQueryWrapper = new QueryWrapper<>();
            accountQueryWrapper.eq(ShopFinanceAccount.SHOP_ID,orderInfo.getShopId());
            ShopFinanceAccount account = shopFinanceAccountService.getOne(accountQueryWrapper);

            ShopFinanceDetail detail = new ShopFinanceDetail();
            Long sfdId = GenerateIDUtil.nextId();
            detail.setId(sfdId);
            detail.setShopId(orderInfo.getShopId());
            detail.setFinanceAccountId(account.getId());
            //分账扣款
            detail.setFinanceType(DictConstant.FinanceType.financetype_20.getCode());
            //2.支出状态
            detail.setCapitalFlowType("2");
            detail.setEnterTime(DateUtil.date());
            detail.setWxOrderId(wxorderId);
            detail.setAmountDetail(vamountbig);
            detail.setNumber(orderInfo.getNumber());
            shopFinanceDetailService.save(detail);

            //更新店铺资金账户表
            BigDecimal settleAmount = account.getSettleAmount();
            BigDecimal vsettleAmoutnt = settleAmount.add(vamountbig);
            account.setSettleAmount(vsettleAmoutnt);
            shopFinanceAccountService.updateById(account);


        }catch(Exception e){
            log.error("订单ID ：" + orderId + " 分账失败，跳过..."+e.getMessage());
        }
        return status;
    }

//    @Transactional(rollbackFor = Exception.class)
    public void changCouponUse(String number,CustCouponRestrict custCouponRestrict,String stockId,Long shopId,String appId){
        try {
            //存在需要核销的优惠券时，需根据情况处理回流券，立减券，平台券

            WxPayService wxPayService = null;

            if(custCouponRestrict.getCouponType().equals(DictConstant.CouponType.TYPE_3.getCode())){
                //如果是平台优惠券，则取平台数据
                wxPayService = shopThirdService.findCouponWxPayServicePayConfig();
            }else{
                wxPayService = shopThirdService.findWxPayServiceById(shopId);
            }

            MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();

            BusiFavorCouponsUseRequest request = new BusiFavorCouponsUseRequest();

            String outRequestNo = OrderUtil.getOutRequestNo();

            request.setCouponCode(custCouponRestrict.getCouponCode());
            request.setStockId(stockId);
            request.setAppId(appId);
            request.setUseTime(DateUtil.format(new Date(),"yyyy-MM-dd'T'HH:mm:ssXXX"));
            request.setUseRequestNo(outRequestNo);

            //设置是否核销标识
            custCouponRestrict.setIsCouponsUse("1");
            custCouponRestrict.setCouponsUseTime(DateUtil.date());
            custCouponRestrict.setOutRequestNo(outRequestNo);
            //如果是回流优惠券则进行核销，其他只进行冰结
            if(DictConstant.CouponType.TYPE_2.getCode().equals(custCouponRestrict.getCouponType())){
                custCouponRestrict.setRestrictType(DictConstant.RestrictType.TYPE_20.getCode());
            }

            custCouponRestrictService.updateById(custCouponRestrict);

            //删除优惠券领券状态已过期延时队列
            appCouponRestrictTypeExpiredDelayTaskProcess.removeTask(custCouponRestrict.getId());

            BusiFavorCouponsUseResult busiFavorCouponsUseResult = marketingBusiFavorService.verifyBusiFavorCouponsUseV3(request);
            //成功核销的时间
            log.info("订单号：" + number + " 成功核销返回时间..." + busiFavorCouponsUseResult.getWechatpayUseTime());
        }catch(Exception e){
            log.warn("订单号：" + number + " 核销优惠券失败"+e.getMessage());
//            throw new WeikbestException("优惠券核销异常！"+e.getMessage());
        }
    }

//    @Transactional(rollbackFor = Exception.class)
    public void changCouponDeactivate(String number,CustCouponRestrict custCouponRestrict,Long shopId){
        try {

            WxPayService wxPayService = null;
            if(custCouponRestrict.getCouponType().equals(DictConstant.CouponType.TYPE_3.getCode())){
                //如果是平台优惠券，则取平台数据
                wxPayService = shopThirdService.findCouponWxPayServicePayConfig();
            }else{
                wxPayService = shopThirdService.findWxPayServiceById(shopId);
            }

            MarketingBusiFavorService marketingBusiFavorService = wxPayService.getMarketingBusiFavorService();

            BusiFavorCouponsDeactivateRequest request = new BusiFavorCouponsDeactivateRequest();

            String outRequestNo = OrderUtil.getOutRequestNo();

            Coupon coupon = couponService.queryCouponById(custCouponRestrict.getCouponId());

            request.setCouponCode(custCouponRestrict.getCouponCode());
            request.setStockId(coupon.getStockId());
            request.setDeactivateRequestNo(outRequestNo);
            request.setDeactivateReason("客户操作下单");

            //设置是否失效标识
            custCouponRestrict.setIsCouponsDeactivate("1");
            custCouponRestrict.setCouponsDeactivateTime(DateUtil.date());
            custCouponRestrict.setDeactivateRequestNo(outRequestNo);
            custCouponRestrict.setRestrictType(DictConstant.RestrictType.TYPE_25.getCode());

            custCouponRestrictService.updateById(custCouponRestrict);

            //删除优惠券领券状态未使用延时队列
            appCouponRestrictTypeNotUsedDelayTaskProcess.removeTask(custCouponRestrict.getId());
            //删除优惠券领券状态已过期延时队列
            appCouponRestrictTypeExpiredDelayTaskProcess.removeTask(custCouponRestrict.getId());

            BusiFavorCouponsDeactivateResult busiFavorCouponsDeactivateResult = marketingBusiFavorService.deactiveBusiFavorCoupons(request);
            log.info("订单号：" + number + " 优惠券成功失效返回时间..." + busiFavorCouponsDeactivateResult.getWechatpayDeactivateTime());
        }catch(Exception e){
            log.warn("订单号：" + number + " 失效优惠券失败"+e.getMessage());
//            throw new WeikbestException("优惠券失效异常！"+e.getMessage());
        }
    }

    @Override
    public Boolean delOrderInfo(String number) {

        OrderInfo orderInfo = orderInfoService.findByOrderNumber(number);
        orderInfo.setCustomerOperateDel("1");
        orderInfo.setDataStatus("0");
        Boolean bool = orderInfoService.saveOrUpdate(orderInfo);

        return bool;
    }

    @Override
    public List<Map<String,Object>> queryCountOrderByStatus(){
        Long id = currentUserService.getAppTokenUser().getId();
        Customer byId = customerService.getById(id);
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(byId.getPhone())){
            customerQueryWrapper.eq(Customer.PHONE,byId.getPhone());
        }else{
            customerQueryWrapper.eq(Customer.ID,byId.getId());
        }
        List<Customer> customerList = customerService.list(customerQueryWrapper);
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        wrapper.select("order_status,count(0) as  countStatus");
        if (!CollectionUtil.isEmpty(customerList)){
            List<Long> customerIds = customerList.stream().map(Customer::getId).collect(Collectors.toList());
            wrapper.in(OrderInfo.CUSTOMER_ID,customerIds);
        } else {
            wrapper.eq(OrderInfo.CUSTOMER_ID,id);
        }
        wrapper.eq(OrderInfo.DATA_STATUS,DictConstant.Whether.yes.getCode());
        wrapper.groupBy(OrderInfo.ORDER_STATUS);
        List<Map<String,Object>> list = orderInfoService.listMaps(wrapper);
        return list;

    }


    /**
     * 设置订单优惠金额
     *
     * @param discountAmountMap    : 优惠金额 Map
     * @param prodReturnPageId     : 返回页Id
     * @param isThereAFailureMoney : 是否有支付失败金额
     * @param prodId               : 商品订单Id
     */
    private void setDiscountAmount(Map<String, BigDecimal> discountAmountMap, Long prodReturnPageId, Integer isThereAFailureMoney, Long prodId , Integer buyNumber , Long prodComboId ) {
        //返回页
        if (ObjectUtil.isNotNull(prodReturnPageId)){
            //判断是否符合使用反减的资格
            ProdCombo prodCombo = prodComboService.getById(prodComboId);
            ProdLeftSlide prodLeftSlide = prodLeftSlideService.getById(prodId);
            if (ObjectUtil.isNotNull(prodCombo) && ObjectUtil.isNotNull(prodLeftSlide)){
                ProdReturn aReturn = prodReturnService.getById(prodReturnPageId);
                if (ObjectUtil.isNotNull(aReturn)){
                    if (ObjectUtil.isNull(prodLeftSlide.getFullDiscountOnOff()) || BasicConstant.INT_0.equals(prodLeftSlide.getFullDiscountOnOff())){
                        //没开关直接过
                        discountAmountMap.put("returnPage" , aReturn.getReturnAmount());
                    }else if (BasicConstant.INT_1.equals(prodLeftSlide.getFullDiscountOnOff()) && (prodCombo.getComboPrice().multiply(new BigDecimal(buyNumber)).compareTo(prodLeftSlide.getFullDiscountMoney()) >= 0)){
                        discountAmountMap.put("returnPage" , aReturn.getReturnAmount());
                    }
                }
            }
        }
        //是否有失败优惠金额
        if (BasicConstant.INT_1.equals(isThereAFailureMoney)){
            ProdPrice prodPrice = prodPriceService.findById(prodId);
            if (ObjectUtil.isNotNull(prodPrice) && prodPrice.getDiscountPrice() != null){
                discountAmountMap.put("discountPrice" , prodPrice.getDiscountPrice());
            }
        }
    }

}
