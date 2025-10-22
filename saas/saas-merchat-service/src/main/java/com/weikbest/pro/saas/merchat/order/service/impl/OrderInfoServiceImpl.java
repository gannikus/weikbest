package com.weikbest.pro.saas.merchat.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.bean.marketing.BusiFavorCouponsReturnRequest;
import com.github.binarywang.wxpay.bean.marketing.BusiFavorCouponsReturnResult;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingReturnRequest;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingReturnResult;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingUnfreezeRequest;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingUnfreezeResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingBusiFavorService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.third.wx.miniapp.subscribe.SubscribeUtil;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleVO;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleService;
import com.weikbest.pro.saas.merchat.api.module.qo.OrderInfoQo;
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
import com.weikbest.pro.saas.merchat.order.delaytaskprocess.OrderSettlementByDepositDayDelayTaskProcess;
import com.weikbest.pro.saas.merchat.order.entity.*;
import com.weikbest.pro.saas.merchat.order.mapper.OrderInfoMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.AppOrderInfoListDTO;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderInfoDTO;
import com.weikbest.pro.saas.merchat.order.module.excel.OrderInfoDetailExcel;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderInfoMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderInfoQO;
import com.weikbest.pro.saas.merchat.order.module.vo.*;
import com.weikbest.pro.saas.merchat.order.service.*;
import com.weikbest.pro.saas.merchat.prod.service.ProdDecFloorService;
import com.weikbest.pro.saas.merchat.prod.service.ProdReturnService;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceAccount;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceDetail;
import com.weikbest.pro.saas.merchat.shop.entity.ShopStatementDetail;
import com.weikbest.pro.saas.merchat.shop.service.*;
import com.weikbest.pro.saas.sys.capital.entity.CapitalRecord;
import com.weikbest.pro.saas.sys.capital.service.CapitalRecordService;
import com.weikbest.pro.saas.sys.common.cache.Memory;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.constant.AppletSubscribeConfigConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.constant.SmsTemplateConstant;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.entity.Settle;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUserrecordSendDTO;
import com.weikbest.pro.saas.sys.param.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private MemoryService memoryService;

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private RedisLock redisLock;

    @Resource
    private AppletConfigService appletConfigService;

    @Resource
    private SmsTemplateService smsTemplateService;

    @Resource
    private AppletSubscribeConfigService appletSubscribeConfigService;

    @Resource
    private BusinessService businessService;

    @Resource
    private ShopService shopService;

    @Resource
    private OrderStatRecordService orderStatRecordService;

    @Resource
    private OrderRecAddrService orderRecAddrService;

    @Resource
    private OrderLogisticsService orderLogisticsService;

    @Resource
    private OrderProdInfoService orderProdInfoService;

    @Resource
    private OrderCustInfoService orderCustInfoService;

    @Resource
    private OrderPayRecordService orderPayRecordService;

    @Resource
    private OrderAfterSaleService orderAfterSaleService;

    @Resource
    private CustCouponRestrictService custCouponRestrictService;

    @Resource
    private CouponService couponService;

    @Resource
    private DealService dealService;

    @Resource
    private AppCouponRestrictTypeExpiredDelayTaskProcess appCouponRestrictTypeExpiredDelayTaskProcess;

    @Resource
    private AppCouponRestrictTypeNotUsedDelayTaskProcess appCouponRestrictTypeNotUsedDelayTaskProcess;

    @Resource
    private CustBusinessBindService custBusinessBindService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private OrderReceiveRecordService orderReceiveRecordService;

    @Resource
    private SettleService settleService;

    @Resource
    private OrderSettlementByDepositDayDelayTaskProcess orderSettlementByDepositDayDelayTaskProcess;

    @Resource
    private ShopFinanceAccountService shopFinanceAccountService;

    @Resource
    private ShopFinanceDetailService shopFinanceDetailService;

    @Resource
    private CapitalRecordService capitalRecordService;

    @Resource
    private RedisContext redisContext;

    @Resource
    private TencentAdvUserrecordService tencentAdvUserrecordService;

    @Resource
    private CustomerService customerService;
    @Resource
    private ProdDecFloorService prodDecFloorService;
    @Resource
    private ProdReturnService prodReturnService;

    @Autowired
    private ShopStatementDetailService shopStatementDetailService;


    @Override
    public boolean insert(OrderInfoDTO orderInfoDTO) {
        OrderInfo orderInfo = OrderInfoMapStruct.INSTANCE.converToEntity(orderInfoDTO);
        return this.save(orderInfo);
    }

    @Override
    public boolean updateById(Long id, OrderInfoDTO orderInfoDTO) {
        OrderInfo orderInfo = this.findById(id);
        OrderInfoMapStruct.INSTANCE.copyProperties(orderInfoDTO, orderInfo);
        orderInfo.setId(id);
        return this.updateById(orderInfo);
    }

    @Override
    public boolean updateDescriptionById(Long id, String description) {
        OrderInfo orderInfo = this.findById(id);
        orderInfo.setDescription(description);
        return this.updateById(orderInfo);
    }

    @Override
    public OrderInfo findById(Long id) {
        OrderInfo orderInfo = this.getById(id);
        if (ObjectUtil.isNull(orderInfo)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderInfo;
    }

    @Override
    public OrderInfoVO findVOById(Long id) {
        OrderInfo orderInfo = this.findById(id);
        return OrderInfoMapStruct.INSTANCE.converToVO(orderInfo);
    }

    @Override
    public OrderInfo getByOrderNumber(String orderNumber) {
        return this.getOne(new QueryWrapper<OrderInfo>().eq(OrderInfo.NUMBER, orderNumber));
    }

    @Override
    public OrderInfo findByOrderNumber(String orderNumber) {
        OrderInfo orderInfo = this.getOne(new QueryWrapper<OrderInfo>().eq(OrderInfo.NUMBER, orderNumber));
        if (ObjectUtil.isNull(orderInfo)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderInfo;
    }

    @Override
    public List<OrderStatusGroupVO> queryGroupOrderStatus(OrderInfoQO orderInfoQO) {
        // 查询订单状态字典
        Map<String, String> orderStatusDictMap = memoryService.queryDictReturnMap(DictConstant.OrderGroupStatus.getDictTypeNumber());
        List<OrderStatusGroupVO> resultList = new ArrayList<>();
        // 初始总数
        resultList.add(new OrderStatusGroupVO().setOrderGroupStatus("").setTotal(WeikbestConstant.ZERO_INT));
        // 初始数据
        orderStatusDictMap.forEach((key, value) -> resultList.add(new OrderStatusGroupVO().setOrderGroupStatus(key).setTotal(WeikbestConstant.ZERO_INT)));

        // 订单状态
        List<OrderStatusGroupVO> orderStatusGroupVOList = this.baseMapper.queryGroupOrderStatus(orderInfoQO);
        Map<String, OrderStatusGroupVO> orderStatusGroupVOMap = orderStatusGroupVOList.stream().collect(Collectors.toMap(OrderStatusGroupVO::getOrderGroupStatus, orderStatusGroupVO -> orderStatusGroupVO));
        // 特殊状态赋值：15-24小时待发货状态 40-发货已超时 60-售后中
        long waitDeliverCount = this.baseMapper.queryCountWaitDeliverCount(orderInfoQO);
//        long waitDeliverCount = this.count(new QueryWrapper<OrderInfo>().eq(OrderInfo.ORDER_STATUS, DictConstant.OrderStatus.orderStatus_10.getCode()).eq(OrderInfo.ORDER_DELIVER_TIMEOUT, DictConstant.Whether.no.getCode()));
        long timeoutDeliverCount = this.baseMapper.queryCountTimeoutDeliverCount(orderInfoQO);
//        long timeoutDeliverCount = this.count(new QueryWrapper<OrderInfo>().eq(OrderInfo.ORDER_STATUS, DictConstant.OrderStatus.orderStatus_10.getCode()).eq(OrderInfo.ORDER_DELIVER_TIMEOUT, DictConstant.Whether.yes.getCode()));
        long orderAfterSaleCount = this.baseMapper.queryCountOrderAfterSaleCount(orderInfoQO);
//        long orderAfterSaleCount = this.count(new QueryWrapper<OrderInfo>().eq(OrderInfo.ORDER_AFTER_FLAG, DictConstant.Whether.yes.getCode()));
        orderStatusGroupVOMap.put(DictConstant.OrderGroupStatus.orderStatus_15.getCode(), new OrderStatusGroupVO(DictConstant.OrderGroupStatus.orderStatus_15.getCode(), (int) waitDeliverCount));
        orderStatusGroupVOMap.put(DictConstant.OrderGroupStatus.orderStatus_40.getCode(), new OrderStatusGroupVO(DictConstant.OrderGroupStatus.orderStatus_40.getCode(), (int) timeoutDeliverCount));
        orderStatusGroupVOMap.put(DictConstant.OrderGroupStatus.orderStatus_60.getCode(), new OrderStatusGroupVO(DictConstant.OrderGroupStatus.orderStatus_60.getCode(), (int) orderAfterSaleCount));

        // 遍历订单状态数据赋值
        resultList.forEach(orderStatusGroupVO -> {
            OrderStatusGroupVO totalVO = orderStatusGroupVOMap.get(orderStatusGroupVO.getOrderGroupStatus());
            if (ObjectUtil.isNotEmpty(totalVO)) {
                orderStatusGroupVO.setTotal(totalVO.getTotal());
            }
        });

        // 查询总数
        int sum = orderStatusGroupVOList.stream().mapToInt(OrderStatusGroupVO::getTotal).sum();
        resultList.get(WeikbestConstant.ZERO_INT).setTotal(sum);
        return resultList;
    }

    @Override
    public IPage<OrderInfoListVO> queryPage(OrderInfoQO orderInfoQO, PageReq pageReq) {
        // 参数转换
        String orderGroupStatus = orderInfoQO.getOrderGroupStatus();
        if (StrUtil.isNotBlank(orderGroupStatus)) {
            // 订单状态
            if (DictConstant.OrderGroupStatus.isOrderStatus(orderGroupStatus)) {
                orderInfoQO.setOrderStatus(orderGroupStatus);
            }
            // 售后中
            else if (StrUtil.equals(orderGroupStatus, DictConstant.OrderGroupStatus.orderStatus_60.getCode())) {
                orderInfoQO.setOrderAfterFlag(DictConstant.Whether.yes.getCode());
            }
            // 24小时待发货
            else if (StrUtil.equals(orderGroupStatus, DictConstant.OrderGroupStatus.orderStatus_15.getCode())) {
                orderInfoQO.setOrderStatus(DictConstant.OrderStatus.orderStatus_10.getCode());
                orderInfoQO.setOrderDeliverTimeout(DictConstant.Whether.no.getCode());
            }
            // 发货超时
            else if (StrUtil.equals(orderGroupStatus, DictConstant.OrderGroupStatus.orderStatus_40.getCode())) {
                orderInfoQO.setOrderStatus(DictConstant.OrderStatus.orderStatus_10.getCode());
                orderInfoQO.setOrderDeliverTimeout(DictConstant.Whether.yes.getCode());
            }
        }

        return this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), orderInfoQO);
    }

    @Override
    public List<OrderInfoDetailExcel> downloadDetail(OrderInfoQO orderInfoQO) {
        // 参数转换
        String orderGroupStatus = orderInfoQO.getOrderGroupStatus();
        if (StrUtil.isNotBlank(orderGroupStatus)) {
            // 订单状态
            if (DictConstant.OrderGroupStatus.isOrderStatus(orderGroupStatus)) {
                orderInfoQO.setOrderStatus(orderGroupStatus);
            }
            // 售后中
            else if (StrUtil.equals(orderGroupStatus, DictConstant.OrderGroupStatus.orderStatus_60.getCode())) {
                orderInfoQO.setOrderAfterFlag(DictConstant.Whether.yes.getCode());
            }
            // 24小时待发货
            else if (StrUtil.equals(orderGroupStatus, DictConstant.OrderGroupStatus.orderStatus_15.getCode())) {
                orderInfoQO.setOrderStatus(DictConstant.OrderStatus.orderStatus_10.getCode());
                orderInfoQO.setOrderDeliverTimeout(DictConstant.Whether.no.getCode());
            }
            // 发货超时
            else if (StrUtil.equals(orderGroupStatus, DictConstant.OrderGroupStatus.orderStatus_40.getCode())) {
                orderInfoQO.setOrderStatus(DictConstant.OrderStatus.orderStatus_10.getCode());
                orderInfoQO.setOrderDeliverTimeout(DictConstant.Whether.yes.getCode());
            }
        }

        List<OrderInfoDetailExcel> orderInfoDetailExcelList = this.baseMapper.downloadDetail(orderInfoQO);
        // 字典值转换
        orderInfoDetailExcelList.forEach(orderInfoDetailExcel -> {
            orderInfoDetailExcel.setOrderSource(Memory.getDict(DictConstant.OrderSource.getDictTypeNumber(), orderInfoDetailExcel.getOrderSource()));
            orderInfoDetailExcel.setAdvChannelType(Memory.getDict(DictConstant.AdvChannelType.getDictTypeNumber(), orderInfoDetailExcel.getAdvChannelType()));
            orderInfoDetailExcel.setOrderStatus(Memory.getDict(DictConstant.OrderStatus.getDictTypeNumber(), orderInfoDetailExcel.getOrderStatus()));
//            orderInfoDetailExcel.setPayMode(Memory.getDict(DictConstant.PayType.getDictTypeNumber(), orderInfoDetailExcel.getPayMode()));
            orderInfoDetailExcel.setPayType(Memory.getDict(DictConstant.PayType.getDictTypeNumber(), orderInfoDetailExcel.getPayType()));
            orderInfoDetailExcel.setAfterSaleStatus(Memory.getDict(DictConstant.AfterSaleStatus.getDictTypeNumber(), orderInfoDetailExcel.getAfterSaleStatus()));
            orderInfoDetailExcel.setAfterSaleApplyType(Memory.getDict(DictConstant.AfterSaleApplyType.getDictTypeNumber(), orderInfoDetailExcel.getAfterSaleApplyType()));
            orderInfoDetailExcel.setLogisticsCompany(Memory.getLogisticsCompanyName(orderInfoDetailExcel.getLogisticsCompany()));
            // 商品购买型号 + 商品套餐名称 = 商品规格
            String prodCombo = "";

            String prodComboAttrValues = orderInfoDetailExcel.getProdComboAttrValues();
            if (StrUtil.isNotBlank(prodComboAttrValues)) {
                if (JSONUtil.isJson(prodComboAttrValues)) {
                    Map<String, String> map = JsonUtils.json2Bean(prodComboAttrValues, Map.class);
                    prodCombo += map.entrySet().stream().map(entry -> String.format("%s:%s", entry.getKey(), entry.getValue())).collect(Collectors.joining(" "));
                } else {
                    prodCombo += prodComboAttrValues;
                }
            }
            String prodComboTitle = orderInfoDetailExcel.getProdComboTitle();
            if (StrUtil.isNotBlank(prodComboTitle)) {
                prodCombo += " 套餐：" + prodComboTitle;
            }
            orderInfoDetailExcel.setProdCombo(prodCombo);
        });
        return orderInfoDetailExcelList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adCallback(Long id) {
        // 1.查询订单信息
        OrderInfo orderInfo = getById(id);
        //2. 判断订单信息和状态
        if (ObjectUtil.isNull(orderInfo)) {
            throw new WeikbestException("订单不存在！");
        }
        if (BasicConstant.STATE_1.equals(orderInfo.getOrderStatus())
                || BasicConstant.STATE_30.equals(orderInfo.getOrderStatus())
                || BasicConstant.STATE_99.equals(orderInfo.getOrderStatus())) {
            //不支持: 待付款 , 已关闭 , 已完成 的订单
            throw new WeikbestException("订单状态不支持！");
        }
        if (StringUtils.isEmpty(orderInfo.getAdAid())) {
            throw new WeikbestException("广告id为空！");
        }
        //设置回传金额
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(orderInfo.getPayType())){
            if (orderInfo.getPayType().equals(BasicConstant.STATE_1)){
                orderAmount = orderAmount.add(orderInfo.getPayAmount());
            }else if (orderInfo.getPayType().equals(BasicConstant.STATE_2)){
                orderAmount = orderAmount.add(orderInfo.getDeliveryPayAmount());
            }
        }

        // 需要回传腾讯广告
        Customer customer = customerService.findById(orderInfo.getCustomerId());
        TencentAdvUserrecordSendDTO tencentAdvUserrecordSendDTO = new TencentAdvUserrecordSendDTO();
        tencentAdvUserrecordSendDTO.setOrderId(orderInfo.getId())
                .setBackRatio(new BigDecimal(100))
                .setAdAid(orderInfo.getAdAid())
                .setClickId(orderInfo.getClickId())
                .setProdId(orderInfo.getProdId())
                .setPayAmount(orderAmount)
                .setWechatAppId(orderInfo.getAppId())
                .setWechatOpenid(customer.getWxOpenid())
                .setWechatUnionid(customer.getWxUnionid());
        tencentAdvUserrecordService.sendWithRatio(tencentAdvUserrecordSendDTO,DictConstant.AdReturnSource.MANUAL_INPUT.getCode(),orderInfo.getPayType());
    }

    @Override
    public void clickMonitoring(String clickId, String adgroupId, String adId, String callback, String accountId, String clickTime, String wechatOpenid, String requestId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("clickId",clickId);
        map.put("adgroupId",adgroupId);
        map.put("callback",callback);
        map.put("accountId",accountId);
        map.put("clickTime",clickTime);
        map.put("wechatOpenid",wechatOpenid);
        map.put("requestId",requestId);
        map.put("adId",adId);
        //2.根据点击id去匹配订单
        LambdaQueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>().lambda().eq(OrderInfo::getClickId, clickId);
        OrderInfo one = this.getOne(queryWrapper);
        //2.1匹配到了去更新订单的广告id
        if (ObjectUtil.isNotNull(one)){
            one.setAdAid(adgroupId);
            this.updateById(one);
        }
        else{
            //2.2未匹配到，存入redis 设置过期时间1天
            String key = RedisKey.generalKey(RedisKey.AD_CLICK_MONITORING_KEY, clickId);
            redisContext.hmset(key,map,24*3600);
        }

    }

    @Override
    public void clickMonitoringOld(Integer clickId, String adgroupId, Integer clickTime, String requestId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("clickId",clickId);
        map.put("adgroupId",adgroupId);
        map.put("clickTime",clickTime);
        map.put("requestId",requestId);
        //2.根据点击id去匹配订单
        LambdaQueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>().lambda().eq(OrderInfo::getClickId, clickId);
        OrderInfo one = this.getOne(queryWrapper);
        //2.1匹配到了去更新订单的广告id
        if (ObjectUtil.isNotNull(one)){
            one.setAdAid(adgroupId);
            this.updateById(one);
        }
        else{
            //2.2未匹配到，存入redis 设置过期时间1天
            String key = RedisKey.generalKey(RedisKey.AD_CLICK_MONITORING_KEY, clickId);
            redisContext.hmset(key,map,24*3600);
        }
    }

    @Override
    public List<OrderInfo> findByOrderMainNumber(String number) {
        return this.list(new QueryWrapper<OrderInfo>().eq(OrderInfo.MAIN_NUMBER,number));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean channelDeliveryOrder(Long id) {
        OrderInfo orderInfo = this.getById(id);
        orderInfo.setOrderStatus(DictConstant.OrderStatus.orderStatus_99.getCode());
        return this.updateById(orderInfo);
    }

    @Override
    public List<OrderInfoDetailVO> listByBatch(List<String> numberList, Long businessId) {
        List<Shop> shops = shopService.queryByBusinessId(businessId);
        if (CollectionUtil.isEmpty(shops)) {
            throw new WeikbestException("该商户号下无店铺！");
        }
        if (CollectionUtil.isEmpty(numberList)) {
            throw new WeikbestException("查询订单不能为空！");
        }
        if (numberList.size()>100){
            throw new WeikbestException("查询订单数过大，订单数需小于100！");
        }

        ArrayList<OrderInfoDetailVO> orderInfoDetailVOS = new ArrayList<>();

        List<OrderInfo> list = this.list(new QueryWrapper<OrderInfo>().in(OrderInfo.NUMBER, numberList).in(OrderInfo.SHOP_ID,shops.stream().map(Shop::getId).collect(Collectors.toList())));
        if (CollectionUtil.isEmpty(list)){
            return orderInfoDetailVOS;
        }
        list.forEach(orderInfo -> {
            OrderInfoDetailVO orderInfoDetailVO = OrderInfoMapStruct.INSTANCE.converToDetailVO(orderInfo);

            // 查询商家名称
            Business business = businessService.findById(orderInfo.getBusinessId());
            orderInfoDetailVO.setBusinessName(business.getName());

            // 查询店铺名称
            Shop shop = shopService.findById(orderInfo.getShopId());
            orderInfoDetailVO.setShopName(shop.getName());

            // 查询小程序名称
            AppletConfig appletConfig = appletConfigService.findByAppIdAndAppletType(orderInfo.getAppId(), orderInfo.getOrderAppletType());
            orderInfoDetailVO.setAppletName(ObjectUtil.isNull(appletConfig) ? "" : appletConfig.getAppletName());

            // 客户信息
            OrderCustInfoVO orderCustInfoVO = orderCustInfoService.findVOById(orderInfo.getId());
            orderInfoDetailVO.setOrderCustInfoVO(orderCustInfoVO);

            // 收货信息
            OrderRecAddrVO orderRecAddrVO = orderRecAddrService.findVOById(orderInfo.getId());
            orderInfoDetailVO.setOrderRecAddrVO(orderRecAddrVO);

            // 物流信息
            OrderLogisticsVO orderLogisticsVO = orderLogisticsService.getVOAndResetContentByOrderId(orderInfo.getId());
            orderInfoDetailVO.setOrderLogisticsVO(orderLogisticsVO);

            // 商品信息
            OrderProdInfoVO orderProdInfoVO = orderProdInfoService.findVOById(orderInfo.getId());
            orderInfoDetailVO.setOrderProdInfoVO(orderProdInfoVO);

            if (DictConstant.PayType.TYPE_1.getCode().equals(orderInfo.getPayType())){
                // 订单支付信息
                OrderPayRecordVO orderPayRecordVO = orderPayRecordService.findVOByOrderId(orderInfo.getId());
                orderInfoDetailVO.setOrderPayRecordVO(orderPayRecordVO);
            }

            // 订单售后信息
            if (StrUtil.equals(orderInfo.getOrderAfterFlag(), DictConstant.Whether.yes.getCode())) {
                OrderAfterSaleVO orderAfterSaleVO = orderAfterSaleService.findVOById(orderInfo.getOrderAfterSaleId());
                orderInfoDetailVO.setOrderAfterSaleVO(orderAfterSaleVO);
            }
            orderInfoDetailVOS.add(orderInfoDetailVO);
        });

        return orderInfoDetailVOS;
    }

    @Override
    public List<AppOrderInfoListDTO> queryPageAppOrderInfo(Map<String, Object> paramMap) {
        return this.baseMapper.queryPageAppOrderInfo(paramMap);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderInfo(OrderInfo orderInfo) {
        Long id = orderInfo.getId();
        OrderInfo dbOrderInfo = this.findById(id);

        // 如果修改了订单状态，则进行状态变更
        if (!StrUtil.equals(dbOrderInfo.getOrderStatus(), orderInfo.getOrderStatus())) {
            this.updateOrderStatus(orderInfo, dbOrderInfo.getOrderStatus(), orderInfo.getOrderStatus(), orderInfo.getDescription());
        } else {
            this.updateById(orderInfo);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(OrderInfo orderInfo, String orderStatus, String description) {
        updateOrderStatus(orderInfo, orderInfo.getOrderStatus(), orderStatus, description);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(OrderInfo orderInfo, String currOrderStatus, String changeOrderStatus, String description) {
        // 更新订单变更记录
        String key = RedisKey.generalKey(RedisKey.Lock.LOCK_ORDER_STATUS_KEY, orderInfo.getId());
        redisLock.lock(key);
        try {
            // 修改订单状态表
            OrderStatRecord orderStatRecord = new OrderStatRecord();
            orderStatRecord.setOrderId(orderInfo.getId())
                    .setCurrentState(currOrderStatus)
                    .setChangeStatus(changeOrderStatus)
                    .setChangeTime(DateUtil.date())
                    .setChangerUserId(currentUserService.getTokenUser().getUserId())
                    .setChangerUser(currentUserService.getTokenUser().getLoginNameOrPhone())
                    .setDescription(description);
            orderStatRecordService.save(orderStatRecord);

            orderInfo.setOrderStatus(changeOrderStatus);
            this.updateById(orderInfo);
        } finally {
            redisLock.unlock(key);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void appupdateOrderStatus(OrderInfo orderInfo, String currOrderStatus, String changeOrderStatus, String description) {
        // 更新订单变更记录
        String key = RedisKey.generalKey(RedisKey.Lock.LOCK_ORDER_STATUS_KEY, orderInfo.getId());
        redisLock.lock(key);
        try {
            // 修改订单状态表
            OrderStatRecord orderStatRecord = new OrderStatRecord();
            orderStatRecord.setOrderId(orderInfo.getId())
                    .setCurrentState(currOrderStatus)
                    .setChangeStatus(changeOrderStatus)
                    .setChangeTime(DateUtil.date())
                    .setChangerUserId(currentUserService.getAppTokenUser().getUserId())
                    .setChangerUser(currentUserService.getAppTokenUser().getLoginNameOrPhone())
                    .setDescription(description);
            orderStatRecordService.save(orderStatRecord);

            orderInfo.setOrderStatus(changeOrderStatus);
            this.updateById(orderInfo);
        } finally {
            redisLock.unlock(key);
        }
    }

    @Override
    public OrderInfoDetailVO findDetailVOById(Long id) {
        OrderInfo orderInfo = this.findById(id);
        OrderInfoDetailVO orderInfoDetailVO = OrderInfoMapStruct.INSTANCE.converToDetailVO(orderInfo);

        // 查询商家名称
        Business business = businessService.findById(orderInfo.getBusinessId());
        orderInfoDetailVO.setBusinessName(business.getName());

        // 查询店铺名称
        Shop shop = shopService.findById(orderInfo.getShopId());
        orderInfoDetailVO.setShopName(shop.getName());

        // 查询小程序名称
        AppletConfig appletConfig = appletConfigService.findByAppIdAndAppletType(orderInfo.getAppId(), orderInfo.getOrderAppletType());
        orderInfoDetailVO.setAppletName(ObjectUtil.isNull(appletConfig) ? "" : appletConfig.getAppletName());

        // 客户信息
        OrderCustInfoVO orderCustInfoVO = orderCustInfoService.findVOById(id);
        orderInfoDetailVO.setOrderCustInfoVO(orderCustInfoVO);

        // 收货信息
        OrderRecAddrVO orderRecAddrVO = orderRecAddrService.findVOById(id);
        orderInfoDetailVO.setOrderRecAddrVO(orderRecAddrVO);

        // 物流信息
        OrderLogisticsVO orderLogisticsVO = orderLogisticsService.getVOAndResetContentByOrderId(id);
        orderInfoDetailVO.setOrderLogisticsVO(orderLogisticsVO);

        // 商品信息
        OrderProdInfoVO orderProdInfoVO = orderProdInfoService.findVOById(id);
        orderInfoDetailVO.setOrderProdInfoVO(orderProdInfoVO);

        if (DictConstant.PayType.TYPE_1.getCode().equals(orderInfo.getPayType())){
            // 订单支付信息
            OrderPayRecordVO orderPayRecordVO = orderPayRecordService.findVOByOrderId(id);
            orderInfoDetailVO.setOrderPayRecordVO(orderPayRecordVO);
        }

        // 订单售后信息
        if (StrUtil.equals(orderInfo.getOrderAfterFlag(), DictConstant.Whether.yes.getCode())) {
            OrderAfterSaleVO orderAfterSaleVO = orderAfterSaleService.findVOById(orderInfo.getOrderAfterSaleId());
            orderInfoDetailVO.setOrderAfterSaleVO(orderAfterSaleVO);
        }

        return orderInfoDetailVO;
    }

    @Override
    public void waitPayTimeoutExecute(Long id) {
        OrderInfo orderInfo = this.findById(id);
        log.info("【订单等待支付超时延时队列】waitPayTimeoutExecute, orderInfo: {}", orderInfo);

        // 如果当前状态还是待支付，说明支付超时
        if (StrUtil.equals(orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_1.getCode())) {
            // 1.给用户发短信
            OrderCustInfo orderCustInfo = orderCustInfoService.findById(id);
            if (StrUtil.isNotBlank(orderCustInfo.getCustomerPhone())) {
                smsTemplateService.sendOneSmsJumpAppletAndSaveRecord(orderInfo.getAppId(), SmsTemplateConstant.WAITPAYTIMEOUT, orderCustInfo.getCustomerPhone(), orderInfo.getNumber());
            }

            // 2.微信推送
            if (StrUtil.equals(orderInfo.getWaitPayNotify(), DictConstant.Whether.yes.getCode())) {
                // 温馨提醒：
                String message = String.format("请在%s之前完成支付", DateUtil.format(DateUtil.offsetDay(orderInfo.getOrderTime(), 1), "HH:mm"));
                OrderProdInfo orderProdInfo = orderProdInfoService.findById(id);
                appletSubscribeConfigService.sendOneSubscribeAndSaveRecord(orderInfo.getAppId(), AppletSubscribeConfigConstant.ORDER_WAITPAYTIMEOUT, orderCustInfo.getTpOpenid(),
                        orderInfo.getNumber(), SubscribeUtil.subThing(orderProdInfo.getProdName()), String.valueOf(orderProdInfo.getBuyNumber()), orderInfo.getOrderAmount().toString(),
                        DateUtil.format(orderInfo.getOrderTime(), DatePattern.NORM_DATE_PATTERN), message);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelPayTimeoutExecute(Long id) {
        OrderInfo orderInfo = this.findById(id);
        log.info("【订单支付超时延时队列】cancelPayTimeoutExecute, orderInfo: {}", orderInfo);

        // 如果当前状态还是待支付，说明支付超时
        if (StrUtil.equals(orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_1.getCode())) {
            // 修改订单状态为已关闭
            orderInfo.setOrderFinishTime(DateUtil.date());

            updateOrderStatus(orderInfo, DictConstant.OrderStatus.orderStatus_99.getCode(), String.format("客户超过%s小时未支付，订单自动关闭", dealService.findDeal().getCloseOrderMinute() / 60));

            //修改客户优惠劵的状态为待使用
            Long custCouponRestrictId = orderInfo.getCustCouponRestrictId();
            if (ObjectUtils.isNotEmpty(custCouponRestrictId)){
                custCouponRestrictService.saveCustCouponRestrictType(custCouponRestrictId);
            }

            //如果是已关闭，存在优惠券核销的订单，则需进行退券处理
            // 如果该笔订单使用了优惠券（平台劵，立减劵），则还需要做退券处理
            /*if (StrUtil.isNotBlank(orderInfo.getCouponType()) && !StrUtil.equals(orderInfo.getCouponType(), DictConstant.CouponType.TYPE_2.getCode())) {
                // 查询客户领券信息
                Long custCouponRestrictId = orderInfo.getCustCouponRestrictId();
                CustCouponRestrict custCouponRestrict = custCouponRestrictService.findById(custCouponRestrictId);

                WxPayService wxPayService = null;

                if (custCouponRestrict.getCouponType().equals(DictConstant.CouponType.TYPE_3.getCode())) {
                    //如果是平台优惠券，则取平台数据
                    wxPayService = shopThirdService.findCouponWxPayServicePayConfig();
                } else {
                    wxPayService = shopThirdService.findWxPayServiceByOrderNumber(orderInfo.getNumber());
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
    }

    @Override
    public void deliverTimeoutExecute(Long id) {
        OrderInfo orderInfo = this.findById(id);
        log.info("【订单商家发货超时延时队列】deliverTimeoutExecute, orderInfo: {}", orderInfo);

        // 如果当前状态还是待发货，说明发货超时
        if (StrUtil.equals(orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_10.getCode())) {
            // 修改订单为发货超时
            orderInfo.setOrderDeliverTimeout(DictConstant.Whether.yes.getCode());
            updateOrderInfo(orderInfo);
            // TODO 添加消息推送给商家处理

        }
    }

    @Override
    public void deliverCustomerTimeoutExecute(Long id) {
        OrderInfo orderInfo = this.findById(id);
        log.info("【订单商家发货客户确认收货超时延时队列】deliverCustomerTimeoutExecute, orderInfo: {}", orderInfo);

        // 如果当前状态还是已发货，说明发货超时
        if (StrUtil.equals(orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_20.getCode())) {
            // 修改订单状态为已完成
            orderInfo.setOrderFinishTime(DateUtil.date());
            updateOrderStatus(orderInfo, DictConstant.OrderStatus.orderStatus_30.getCode(), String.format("客户收货时间：%s天超时，订单自动完成", dealService.findDeal().getAutoOrderReceive()));
            // TODO 已完成的订单需要设置别的内容？
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doOrderSettlement(Long orderId) {


        //查询该订单是否存在实际退款情况，如果存在则不进行清算
        OrderPayRecord orderPayRecord = orderPayRecordService.findByOrderId(orderId);
        if (orderPayRecord.getRefundStatus().equals(DictConstant.RefundStatus.refundStatus_1.getCode()) ||
                orderPayRecord.getRefundStatus().equals(DictConstant.RefundStatus.refundStatus_2.getCode())) {
            log.info("该订单已存在退款信息，不能进行分账清算回退", orderId);
            return;
        }

        OrderInfo orderInfo = this.findById(orderId);

        // 查询分账记录
        List<OrderReceiveRecord> orderReceiveRecordList = orderReceiveRecordService.list(new QueryWrapper<OrderReceiveRecord>().eq(OrderReceiveRecord.NUMBER, orderInfo.getNumber()).eq(OrderReceiveRecord.RECEIVE_TYPE, "1"));
        if (orderReceiveRecordList != null && orderReceiveRecordList.size() > 0) {
            OrderReceiveRecord orderReceiveRecord = orderReceiveRecordList.get(0);
            if (orderReceiveRecord.getReceiveRecordStatus().equals(DictConstant.ReceiveRecordStatus.code_8.getCode())) {
                //如果存在已结算情况，则不需要再次结算
                return;
            }
        }

        boolean falg = false;
        Integer amountFraction;
        BigDecimal amountYuan = new BigDecimal(0);

        //获取自然流量或技术服务费
        BigDecimal receiveScale = getReceiveScale(orderInfo);

        WxPayService wxPayService = shopThirdService.findWxPayServiceByOrderNumber(orderInfo.getNumber());


        for (OrderReceiveRecord orderReceiveRecord : orderReceiveRecordList) {
            String outReturnNo = OrderUtil.getOutrefundno();

            if (receiveScale.compareTo(orderReceiveRecord.getReceiveScale()) == WeikbestConstant.ZERO_INT) {
                //处理存在分账比例和押金比例相同的情况
                falg = true;
                BigDecimal bd = new BigDecimal(orderReceiveRecord.getAmount());
                BigDecimal valuebig = new BigDecimal(100);
                amountYuan = bd.divide(valuebig);
                break;
            }

            try {
                // 押金比例getReceiveScale 是20  技术服务费比例receiveScale是5
                BigDecimal deposit = orderReceiveRecord.getReceiveScale().subtract(receiveScale);
                BigDecimal orderAmount = orderInfo.getPayAmount();
                BigDecimal valuebig = new BigDecimal(100);

                deposit = deposit.divide(valuebig);
                BigDecimal totalbig = orderAmount.multiply(valuebig).
                        multiply(deposit).
                        setScale(0, BigDecimal.ROUND_HALF_UP);
                Long total = totalbig.longValue();

                // 调用微信请求分账回退接口
                ProfitSharingReturnRequest request = new ProfitSharingReturnRequest();
                request.setOrderId(orderReceiveRecord.getWxOrderId());
                request.setOutReturnNo(outReturnNo);
                request.setReturnMchid(orderReceiveRecord.getMchId());
                request.setAmount(total);
                request.setDescription("平台分账回退");
                ProfitSharingReturnResult response = wxPayService.getProfitSharingV3Service().profitSharingReturn(request);

                // 更新分账记录
                String receiveRecordStatus = StrUtil.isNotBlank(response.getFailReason()) ? DictConstant.ReceiveRecordStatus.code_5.getCode() : DictConstant.ReceiveRecordStatus.code_3.getCode();

                OrderReceiveRecord record = new OrderReceiveRecord();
                Long recordId = GenerateIDUtil.nextId();
                record.setId(recordId);
                record.setNumber(orderInfo.getNumber());
                record.setBusinessType(orderReceiveRecord.getBusinessType());
                record.setWxOrderId(response.getReturnId());
                record.setOutTradeNo(outReturnNo);
                record.setTransactionId(orderReceiveRecord.getTransactionId());
                record.setMchId(orderReceiveRecord.getMchId());
                record.setAmount(total.intValue());
                record.setReceiveTime(DateUtil.date());
                record.setReceiveResult(response.getResult());
                record.setReceiveFailReason(response.getFailReason());
                //平台分账回退
                record.setReceiveType("2");
                record.setReceiveRecordStatus(receiveRecordStatus);
                record.setReceiveScale(orderReceiveRecord.getReceiveScale().subtract(receiveScale));
                orderReceiveRecordService.save(record);

                //保存订单店铺资金明细表，交易账单记录
                QueryWrapper<ShopFinanceAccount> accountQueryWrapper = new QueryWrapper<>();
                accountQueryWrapper.eq(ShopFinanceAccount.SHOP_ID, orderInfo.getShopId());
                ShopFinanceAccount account = shopFinanceAccountService.getOne(accountQueryWrapper);

                //计算技术服务费 = 分账押金 - 回退金额；
                amountFraction = orderReceiveRecord.getAmount() - record.getAmount();
                amountYuan = new BigDecimal(amountFraction).divide(valuebig);

                ShopFinanceDetail detail = new ShopFinanceDetail();
                Long sfdId = GenerateIDUtil.nextId();
                detail.setId(sfdId);
                detail.setShopId(orderInfo.getShopId());
                detail.setFinanceAccountId(account.getId());
                detail.setFinanceType(DictConstant.FinanceType.financetype_10.getCode());
                detail.setCapitalFlowType("1");
                detail.setEnterTime(DateUtil.date());
                detail.setWxOrderId(response.getReturnId());
                detail.setAmountDetail(totalbig.divide(valuebig));
                detail.setNumber(orderInfo.getNumber());
                detail.setDescription("押金扣除技术服务费：" + amountYuan + "元");
                shopFinanceDetailService.save(detail);

                //分账回退请求成功之后,修改结算状态为: 已结算
                if (BasicConstant.STATE_3.equals(receiveRecordStatus)){
                    List<ShopStatementDetail> details = shopStatementDetailService.list(new QueryWrapper<ShopStatementDetail>().eq(ShopStatementDetail.NUMBER, orderInfo.getNumber())
                            .eq(ShopStatementDetail.SETTLE_TYPE, DictConstant.SettleType.settleType_0.getCode()));
                    if(CollectionUtil.isNotEmpty(details)) {
                        details.forEach(d -> {
                            d.setSettleType(DictConstant.SettleType.settleType_1.getCode());
                        });
                        shopStatementDetailService.updateBatchById(details);
                    }

                }

            } catch (WxPayException e) {
                log.error("调用分账回退接口失败! " + e.getCustomErrorMsg(), e);
                //throw new WeikbestException(ResultConstant.THIRD_SERVICE_FAIL.getCode(), e.getCustomErrorMsg(), e);
            }
        }

        if (falg) {
            //如果存在分账押金比例和技术服务费比例相同的情况，则变更 20-分账押金扣款 为 50-技术服务费-自然流量
            QueryWrapper<ShopFinanceDetail> detailQueryWrapper = new QueryWrapper<>();
            detailQueryWrapper.eq(ShopFinanceDetail.NUMBER, orderInfo.getNumber());
            detailQueryWrapper.eq(ShopFinanceDetail.FINANCE_TYPE, DictConstant.FinanceType.financetype_20.getCode());
            List<ShopFinanceDetail> details = shopFinanceDetailService.list(detailQueryWrapper);
            for (ShopFinanceDetail detail : details) {
                detail.setFinanceType(DictConstant.FinanceType.financetype_50.getCode());
                shopFinanceDetailService.updateById(detail);
            }

            //订单结算
            List<ShopStatementDetail> shopStatementDetails = shopStatementDetailService.list(new QueryWrapper<ShopStatementDetail>().eq(ShopStatementDetail.NUMBER, orderInfo.getNumber())
                    .eq(ShopStatementDetail.SETTLE_TYPE, DictConstant.SettleType.settleType_0.getCode()));
            if(CollectionUtil.isNotEmpty(shopStatementDetails)) {
                shopStatementDetails.forEach(d -> {
                    d.setSettleType(DictConstant.SettleType.settleType_1.getCode());
                });
                shopStatementDetailService.updateBatchById(shopStatementDetails);
            }
        }

        if (orderReceiveRecordList != null && orderReceiveRecordList.size() > 0) {
            OrderReceiveRecord orderReceiveRecord = orderReceiveRecordList.get(0);
            orderReceiveRecord.setReceiveRecordStatus(DictConstant.ReceiveRecordStatus.code_8.getCode());
            orderReceiveRecordService.updateById(orderReceiveRecord);

            BigDecimal valuebig = new BigDecimal(100);
            BigDecimal amount = new BigDecimal(orderReceiveRecord.getAmount());

            //店铺资金账户表数据
            ShopFinanceAccount account = shopFinanceAccountService.findByShopId(orderInfo.getShopId());
            account.setSettleAmount(account.getSettleAmount().subtract(amount.divide(valuebig)));
            account.setBalanceAmount(account.getBalanceAmount().add(amount.divide(valuebig)));

            shopFinanceAccountService.updateById(account);
        }

        if (amountYuan.compareTo(BigDecimal.ZERO) > 0) {
            //保存平台技术服务费流向信息
            CapitalRecord capitalRecord = new CapitalRecord();
            Long capitalId = GenerateIDUtil.nextId();
            capitalRecord.setId(capitalId).setBillAmount(amountYuan.toString())
                    .setBillTime(DateUtil.date()).setShopId(orderInfo.getShopId().toString())
                    .setNumber(orderInfo.getNumber());
            capitalRecordService.save(capitalRecord);
        }

    }

    public BigDecimal getReceiveScale(OrderInfo orderInfo) {

        Settle settle = settleService.findSettle();
        BigDecimal receiveScale = new BigDecimal(WeikbestConstant.ZERO_INT);
        //广告引流
        if (DictConstant.UserBehavior.TYPE_5.getCode().equals(orderInfo.getUserBehavior())) {
            if (DictConstant.OrderPath.TYPE_25.getCode().equals(orderInfo.getOrderPath())
                    || DictConstant.OrderPath.TYPE_30.getCode().equals(orderInfo.getOrderPath())
                    || DictConstant.OrderPath.TYPE_35.getCode().equals(orderInfo.getOrderPath())
                    || DictConstant.OrderPath.TYPE_40.getCode().equals(orderInfo.getOrderPath())
                    || DictConstant.OrderPath.TYPE_45.getCode().equals(orderInfo.getOrderPath())) {
                receiveScale = settle.getTechnicalLedgerRatio();
            }
            if (DictConstant.OrderPath.TYPE_50.getCode().equals(orderInfo.getOrderPath())
                    || DictConstant.OrderPath.TYPE_55.getCode().equals(orderInfo.getOrderPath())
                    || DictConstant.OrderPath.TYPE_60.getCode().equals(orderInfo.getOrderPath())
                    || DictConstant.OrderPath.TYPE_65.getCode().equals(orderInfo.getOrderPath())) {
                receiveScale = settle.getPlatformSplittingRatio();
            }
        }
        if (DictConstant.UserBehavior.TYPE_10.getCode().equals(orderInfo.getUserBehavior())) {
            if (DictConstant.OrderPath.TYPE_70.getCode().equals(orderInfo.getOrderPath())
                    || DictConstant.OrderPath.TYPE_75.getCode().equals(orderInfo.getOrderPath())) {
                receiveScale = settle.getTechnicalLedgerRatio();
            }
        }
        if (DictConstant.OrderSource.TYPE_1.getCode().equals(orderInfo.getOrderSource())){
            receiveScale = settle.getPlatformSplittingRatio();
        }
        return receiveScale;
    }

    @Override
    public void finishTimeoutExecute(Long id) {
        OrderInfo orderInfo = this.findById(id);
        log.info("【订单自动完成延时队列】finishTimeoutExecute, orderInfo: {}", orderInfo);

        // 如果当前状态还是已发货，说明发货超时
        if (StrUtil.equals(orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_20.getCode())) {
            // 修改订单状态为已完成
            orderInfo.setOrderFinishTime(DateUtil.date());
            updateOrderStatus(orderInfo, DictConstant.OrderStatus.orderStatus_30.getCode(), String.format("%s天未处理订单，订单自动完成", dealService.findDeal().getAutoOrderComplete()));
            // TODO 已完成的订单需要设置别的内容？

        }
    }

    @Override
    public Map<Long, OrderInfoCustCouponRestrictVO> queryCustCouponRestrictVOCustomerIdMapByCouponIdAndCustomerIdList(Long couponId, List<Long> customerIdList) {
        if (CollectionUtil.isEmpty(customerIdList)) {
            return new HashMap<>(WeikbestConstant.ONE_INT);
        }

        List<OrderInfo> orderInfoList = this.list(new QueryWrapper<OrderInfo>().eq(OrderInfo.COUPON_ID, couponId).in(OrderInfo.CUSTOMER_ID, customerIdList));

        List<Long> orderIdList = orderInfoList.stream().map(OrderInfo::getId).collect(Collectors.toList());
        Map<Long, OrderProdInfo> orderProdInfoMap = orderProdInfoService.queryMapByIdList(orderIdList);

        return orderInfoList.stream().map(orderInfo -> {
            OrderInfoCustCouponRestrictVO orderInfoCustCouponRestrictVO = OrderInfoMapStruct.INSTANCE.converToCustCouponRestrictVO(orderInfo);
            Long id = orderInfo.getId();
            OrderProdInfo orderProdInfo = orderProdInfoMap.get(id);
            if (ObjectUtil.isNotNull(orderProdInfo)) {
                orderInfoCustCouponRestrictVO.setProdName(orderProdInfo.getProdName())
                        .setProdComboTitle(orderProdInfo.getProdComboTitle())
                        .setProdImg(orderProdInfo.getProdImg());
            }
            return orderInfoCustCouponRestrictVO;
        }).collect(Collectors.toMap(OrderInfoCustCouponRestrictVO::getCustomerId, orderInfoCustCouponRestrictVO -> orderInfoCustCouponRestrictVO, (k1, k2) -> k1));
    }

    @Override
    public void taskUnBind(Long custBusinessBindId) {
        CustBusinessBind custBusinessBind = custBusinessBindService.findById(custBusinessBindId);
        //设置解除绑定
        custBusinessBind.setBindStatus("2");
        //数据状态设置为禁用
        custBusinessBind.setDataStatus("0");
        custBusinessBindService.updateById(custBusinessBind);
    }

    @Override
    public String orderFundReleaseHourTimeout(Long orderId) {
        List<Long> orderIds = new ArrayList<>();
        String outReturnNo = OrderUtil.getOutrefundno();
        String state = "";

        OrderInfo orderInfo = this.findById(orderId);

        //合单支付
        if (StrUtil.isNotBlank(orderInfo.getMainNumber())){

            orderIds = this.findByOrderMainNumber(orderInfo.getMainNumber()).stream().map(OrderInfo::getId).collect(Collectors.toList());

            orderIds.forEach(id-> {

                if (!redisContext.hasKey("UNFREEZE_TRADE_NO:" + id)){
                    redisContext.set("UNFREEZE_TRADE_NO:" + id, outReturnNo, 30 * 24 * 60 * 60);
                }
            });

        }else {

            redisContext.set("UNFREEZE_TRADE_NO:" + orderId, outReturnNo, 30 * 24 * 60 * 60);

        }

        try {

            WxPayService wxPayService = shopThirdService.findWxPayServiceById(orderInfo.getShopId());
            OrderPayRecord orderPayRecord = orderPayRecordService.findByOrderId(orderId);
            ProfitSharingUnfreezeRequest request = new ProfitSharingUnfreezeRequest();
            //支付流水号
            request.setTransactionId(orderPayRecord.getTradingFlow());
            //商户分账单号
            request.setOutOrderNo(outReturnNo);
            request.setDescription("解冻订单资金");

            ProfitSharingUnfreezeResult response = wxPayService.getProfitSharingV3Service().profitSharingUnfreeze(request);
            state = response.getState();

            if (StrUtil.isNotBlank(orderInfo.getMainNumber())){
                List<OrderPayRecord> list = orderPayRecordService.list(new QueryWrapper<OrderPayRecord>().in(OrderPayRecord.ORDER_ID, orderIds));
                list.forEach(orderPayRecord1 -> orderPayRecord1.setUnfreezeTradeNo(outReturnNo));
                orderPayRecordService.updateBatchById(list);
            }else {
                orderPayRecord.setUnfreezeTradeNo(outReturnNo);
                orderPayRecordService.updateById(orderPayRecord);
            }

            log.info("订单号：" + orderInfo.getNumber() + " 解冻剩余资金成功，微信分账单号" + response.getOrderId());
        } catch (Exception e) {
            log.error("订单号：" + orderInfo.getNumber() + " 解冻剩余资金失败，跳过..." + e.getMessage());
        }

        return state;

    }


    public String getMainNumberByNumber(String number){
        return this.baseMapper.getMainNumberByNumber(number);
    }

    @Override
    public IPage<OrderInfoDetailVO> listByBatch(OrderInfoQo orderInfoQo, PageReq pageReq, Long businessId) {
        LambdaQueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>().lambda();
        if (ObjectUtil.isNotNull(businessId)){
            queryWrapper.eq(OrderInfo::getBusinessId,businessId);
        }
        if (ObjectUtil.isNotNull(orderInfoQo)){
            if (ObjectUtil.isNotEmpty(orderInfoQo.getNumberList())){
                queryWrapper.in(OrderInfo::getNumber,orderInfoQo.getNumberList());
            }
            if (ObjectUtil.isNotNull(orderInfoQo.getStartDate())){
                queryWrapper.gt(OrderInfo::getGmtModified,orderInfoQo.getStartDate());
            }
            if (ObjectUtil.isNotNull(orderInfoQo.getEndDate())){
                queryWrapper.le(OrderInfo::getGmtModified,orderInfoQo.getEndDate());
            }
        }
        IPage<OrderInfo> infoPage = this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), queryWrapper);

        return infoPage.convert(orderInfo -> {
            OrderInfoDetailVO orderInfoDetailVO = OrderInfoMapStruct.INSTANCE.converToDetailVO(orderInfo);

            // 查询商家名称
            Business business = businessService.findById(orderInfo.getBusinessId());
            orderInfoDetailVO.setBusinessName(business.getName());

            // 查询店铺名称
            Shop shop = shopService.findById(orderInfo.getShopId());
            orderInfoDetailVO.setShopName(shop.getName());

            // 查询小程序名称
            AppletConfig appletConfig = appletConfigService.findByAppIdAndAppletType(orderInfo.getAppId(), orderInfo.getOrderAppletType());
            orderInfoDetailVO.setAppletName(ObjectUtil.isNull(appletConfig) ? "" : appletConfig.getAppletName());

            // 客户信息
            OrderCustInfoVO orderCustInfoVO = orderCustInfoService.findVOById(orderInfo.getId());
            orderInfoDetailVO.setOrderCustInfoVO(orderCustInfoVO);

            // 收货信息
            OrderRecAddrVO orderRecAddrVO = orderRecAddrService.findVOById(orderInfo.getId());
            orderInfoDetailVO.setOrderRecAddrVO(orderRecAddrVO);

            // 物流信息
            OrderLogisticsVO orderLogisticsVO = orderLogisticsService.getVOAndResetContentByOrderId(orderInfo.getId());
            orderInfoDetailVO.setOrderLogisticsVO(orderLogisticsVO);

            // 商品信息
            OrderProdInfoVO orderProdInfoVO = orderProdInfoService.findVOById(orderInfo.getId());
            orderInfoDetailVO.setOrderProdInfoVO(orderProdInfoVO);

            if (DictConstant.PayType.TYPE_1.getCode().equals(orderInfo.getPayType())) {
                // 订单支付信息
                OrderPayRecordVO orderPayRecordVO = orderPayRecordService.findVOByOrderId(orderInfo.getId());
                orderInfoDetailVO.setOrderPayRecordVO(orderPayRecordVO);
            }

            // 订单售后信息
            if (StrUtil.equals(orderInfo.getOrderAfterFlag(), DictConstant.Whether.yes.getCode())) {
                OrderAfterSaleVO orderAfterSaleVO = orderAfterSaleService.findVOById(orderInfo.getOrderAfterSaleId());
                orderInfoDetailVO.setOrderAfterSaleVO(orderAfterSaleVO);
            }
            return orderInfoDetailVO;
        });
    }
}
