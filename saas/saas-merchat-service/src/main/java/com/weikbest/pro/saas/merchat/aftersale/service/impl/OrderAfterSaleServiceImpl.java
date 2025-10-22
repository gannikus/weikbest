package com.weikbest.pro.saas.merchat.aftersale.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.third.aliyun.logistics.AliyunWuliuService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.merchat.aftersale.delaytaskprocess.*;
import com.weikbest.pro.saas.merchat.aftersale.entity.*;
import com.weikbest.pro.saas.merchat.aftersale.event.OrderAfterSaleEvent;
import com.weikbest.pro.saas.merchat.aftersale.mapper.OrderAfterSaleMapper;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.*;
import com.weikbest.pro.saas.merchat.aftersale.module.excel.OrderAfterSaleDetailExcel;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleImgHisMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleLogisticsImgHisMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleQO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.*;
import com.weikbest.pro.saas.merchat.aftersale.service.*;
import com.weikbest.pro.saas.merchat.aftersale.util.OrderAfterSaleUtil;
import com.weikbest.pro.saas.merchat.busi.entity.BusiAddress;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiAddressVO;
import com.weikbest.pro.saas.merchat.busi.service.BusiAddressService;
import com.weikbest.pro.saas.merchat.busi.util.BusiAddressUtil;
import com.weikbest.pro.saas.merchat.order.delaytaskprocess.OrderSettlementByDepositDayDelayTaskProcess;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.entity.OrderPayRecord;
import com.weikbest.pro.saas.merchat.order.module.vo.*;
import com.weikbest.pro.saas.merchat.order.service.*;
import com.weikbest.pro.saas.merchat.prod.service.ProdBusiAddrService;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.service.ShopService;
import com.weikbest.pro.saas.sys.common.cache.Memory;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.constant.ConfigConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskRecord;
import com.weikbest.pro.saas.sys.param.service.DealService;
import com.weikbest.pro.saas.sys.param.service.DelayTaskRecordService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import com.weikbest.pro.saas.sys.param.util.DelayTaskRecordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单售后表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Slf4j
@Service
public class OrderAfterSaleServiceImpl extends ServiceImpl<OrderAfterSaleMapper, OrderAfterSale> implements OrderAfterSaleService, ApplicationEventPublisherAware {

    @Resource
    private MemoryService memoryService;

    @Resource
    private RedisLock redisLock;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private DelayTaskRecordService delayTaskRecordService;

    @Resource
    private OrderAfterSaleService orderAfterSaleService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderCustInfoService orderCustInfoService;

    @Resource
    private OrderPayRecordService orderPayRecordService;

    @Resource
    private OrderAfterSaleImgService orderAfterSaleImgService;

    @Resource
    private OrderAfterSaleLogisticsImgService orderAfterSaleLogisticsImgService;

    @Resource
    private OrderAfterSaleConsultRecordService orderAfterSaleConsultRecordService;

    @Resource
    private OrderAfterSaleImgHisService orderAfterSaleImgHisService;

    @Resource
    private OrderAfterSaleLogisticsImgHisService orderAfterSaleLogisticsImgHisService;

    @Resource
    private OrderProdInfoService orderProdInfoService;

    @Resource
    private ProdBusiAddrService prodBusiAddrService;

    @Resource
    private ShopService shopService;

    @Resource
    private DealService dealService;

    @Resource
    private BusiAddressService busiAddressService;

    @Resource
    private OrderRecAddrService orderRecAddrService;

    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    OrderLogisticsService orderLogisticsService;



    @Override
    public void setApplicationEventPublisher(@NotNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Resource
    private OrderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess;

    @Resource
    private OrderAfterSaleCustomerExecuteTimeoutDelayTaskProcess orderAfterSaleCustomerExecuteTimeoutDelayTaskProcess;

    @Resource
    private OrderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess orderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess;

    @Resource
    private OrderAfterSaleBusinessConfirmCustomerDeliveryTimeoutDelayTaskProcess orderAfterSaleBusinessConfirmCustomerDeliveryTimeoutDelayTaskProcess;

    @Resource
    private OrderAfterSaleBusinessRejectDeliveryCustomerExecuteTimeoutDelayTaskProcess orderAfterSaleBusinessRejectDeliveryCustomerExecuteTimeoutDelayTaskProcess;

    @Resource
    private OrderAfterSaleBusinessDeliveryAfterTimeoutDelayTaskProcess orderAfterSaleBusinessDeliveryAfterTimeoutDelayTaskProcess;

    @Resource
    private OrderSettlementByDepositDayDelayTaskProcess orderSettlementByDepositDayDelayTaskProcess;

    @Resource
    private OrderAfterSaleRefundExecuteTimeOutDelayTaskProcess orderAfterSaleRefundExecuteTimeOutDelayTaskProcess;

    @Override
    public Long insert(OrderAfterSaleDTO orderAfterSaleDTO, String applySourceType) {
        // 根据订单号获取订单信息
        OrderInfo orderInfo = orderInfoService.findByOrderNumber(orderAfterSaleDTO.getOrderNumber());
        if (StrUtil.equals(orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_1.getCode())) {
            throw new WeikbestException(String.format("订单：%s,状态为待付款，无法发起售后单！", orderAfterSaleDTO.getOrderNumber()));
        }
        if (orderAfterSaleDTO.getApplyAmount().compareTo(orderInfo.getPayAmount()) > WeikbestConstant.ZERO_INT) {
            throw new WeikbestException(String.format("订单：%s,售后申请金额不能大于支付金额！", orderAfterSaleDTO.getOrderNumber()));
        }
        if (StrUtil.equals(orderInfo.getOrderAfterFlag(), DictConstant.Whether.yes.getCode())) {
            // 订单在售后中了，判断这个售后订单是否搞完了
            Long orderAfterSaleId = orderInfo.getOrderAfterSaleId();
            OrderAfterSale findOrderAfterSale = this.findById(orderAfterSaleId);
            if (!DictConstant.AfterSaleStatus.isFinish(findOrderAfterSale.getAfterSaleStatus())) {
                throw new WeikbestException(String.format("订单：%s,存在未完成的售后单，无法发起新的售后单！", orderAfterSaleDTO.getOrderNumber()));
            }
        }
        // 已经发生了退款的订单不可再次申请售后
        OrderPayRecord orderPayRecord = orderPayRecordService.findByOrderId(orderInfo.getId());
        if (StrUtil.equals(orderPayRecord.getRefundStatus(), DictConstant.RefundStatus.refundStatus_1.getCode()) ||
                StrUtil.equals(orderPayRecord.getRefundStatus(), DictConstant.RefundStatus.refundStatus_2.getCode())) {
            throw new WeikbestException(String.format("订单：%s,已退款成功，不可在发起新的售后单！", orderAfterSaleDTO.getOrderNumber()));
        }

        // 判断是否符合极速退款要求，符合则直接退款
        String applyType = orderAfterSaleDTO.getApplyType();
        BigDecimal payAmount = orderInfo.getPayAmount();
        Date orderTime = orderInfo.getOrderTime();
        String orderStatus = orderInfo.getOrderStatus();
        boolean fastRefund = OrderAfterSaleUtil.isFastRefund(dealService.findDeal(), payAmount, applyType, orderTime, orderStatus);

        // 保存售后单
        OrderAfterSale orderAfterSale = orderAfterSaleService.insertOrderAfterSale(orderAfterSaleDTO, applySourceType);
        Long id = orderAfterSale.getId();
        String afterSaleStatus = orderAfterSale.getAfterSaleStatus();
        if (fastRefund) {
            // 极速退款
            orderAfterSaleService.fastRefundById(id);
        } else {
            // 非极速退款单 将售后申请单加入商家处理超时延时队列中， 2天后过期
            orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
        }

        // 如果该笔订单是分账订单，存在售后情况下，需要先删除发货7天押金回退清算延时任务
        if (StrUtil.equals(orderInfo.getIsReceiveOrder(), DictConstant.Whether.yes.getCode())) {
            //清除押金回退清算任务
            orderSettlementByDepositDayDelayTaskProcess.removeTask(orderInfo.getId());
        }

        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderAfterSale insertOrderAfterSale(OrderAfterSaleDTO orderAfterSaleDTO, String applySourceType) {
        // 后台新建售后申请
        OrderAfterSale orderAfterSale = OrderAfterSaleMapStruct.INSTANCE.converToEntity(orderAfterSaleDTO);
        orderAfterSale.setApplySourceType(applySourceType);
        // 设置售后ID
        Long id = GenerateIDUtil.nextId();
        orderAfterSale.setId(id);

        // 根据订单号获取订单信息
        OrderInfo orderInfo = orderInfoService.findByOrderNumber(orderAfterSaleDTO.getOrderNumber());
        Long orderId = orderInfo.getId();
        // 订单是否有当前售后记录
        Long orderAfterSaleId = orderInfo.getOrderAfterSaleId();
        if (WeikbestObjectUtil.isNotEmpty(orderAfterSaleId)) {
            OrderAfterSale currentOrderAfterSale = this.findById(orderAfterSaleId);
            // 获取订单当前售后次数,新的售后+1
            Integer afterSaleNum = currentOrderAfterSale.getAfterSaleNum();
            orderAfterSale.setAfterSaleNum(afterSaleNum + 1);
            orderInfo.setOrderAfterFlag(DictConstant.Whether.yes.getCode());
            orderInfo.setOrderAfterSaleId(id);
            orderInfo.setOrderAfterSaleNum(afterSaleNum + 1);
        } else {
            // 之前没有售后记录,则这次是第一次售后
            orderAfterSale.setAfterSaleNum(WeikbestConstant.ONE_INT);
            orderInfo.setOrderAfterFlag(DictConstant.Whether.yes.getCode());
            orderInfo.setOrderAfterSaleId(id);
            orderInfo.setOrderAfterSaleNum(WeikbestConstant.ONE_INT);
        }
        // 设置订单售后附属信息
        // 设置售后编号
        String number = OrderUtil.getOrderAfterNumber(orderAfterSaleDTO.getOrderNumber());
        orderAfterSale.setNumber(number);
        orderAfterSale.setOrderId(orderId);
        orderAfterSale.setAppId(orderInfo.getAppId());
        orderAfterSale.setApplySourceType(orderInfo.getOrderAppletType());
        orderAfterSale.setCustomerId(orderInfo.getCustomerId());
        orderAfterSale.setApplyTime(DateUtil.date());
        // 设置售后初始状态(根据售后类型)
        orderAfterSale.setAfterSaleStatus(DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode());
        orderAfterSale.setAfterSaleKey(DictConstant.AfterSaleApplyType.getAfterSaleKey(orderAfterSaleDTO.getApplyType()).getCode());
        if (!StrUtil.equals(orderAfterSaleDTO.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
            // 设置待发货状态 0-未发货
            orderAfterSale.setSendType(DictConstant.Whether.no.getCode());
        }
        this.save(orderAfterSale);

        // 新增售后协商记录
        Long historyId = orderAfterSaleConsultRecordService.insertByOrderAfterSale(orderAfterSale, DictConstant.AfterSaleChangeType.customer_apply.getCode());
        List<String> courierImgPathList = orderAfterSaleDTO.getCourierImgPathList();
        if (CollectionUtil.isNotEmpty(courierImgPathList)) {
            // 新建售后凭证图片
            List<OrderAfterSaleImg> orderAfterSaleImgList = courierImgPathList.stream().map(courierImgPath -> {
                OrderAfterSaleImg orderAfterSaleImg = new OrderAfterSaleImg();
                orderAfterSaleImg.setCourierImgPath(courierImgPath);
                orderAfterSaleImg.setId(id);
                return orderAfterSaleImg;
            }).collect(Collectors.toList());
            orderAfterSaleImgService.saveBatch(orderAfterSaleImgList);

            // 新建售后凭证图片历史
            List<OrderAfterSaleImgHis> orderAfterSaleImgHisList = orderAfterSaleImgList.stream().map(orderAfterSaleImg -> {
                OrderAfterSaleImgHis orderAfterSaleImgHis = OrderAfterSaleImgHisMapStruct.INSTANCE.converToEntity(orderAfterSaleImg);
                orderAfterSaleImgHis.setHistoryId(historyId);
                return orderAfterSaleImgHis;
            }).collect(Collectors.toList());
            orderAfterSaleImgHisService.saveBatch(orderAfterSaleImgHisList);
        }

        // 修改订单信息
        orderInfoService.updateOrderInfo(orderInfo);
        return orderAfterSale;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void fastRefundById(Long id) {
        OrderAfterSale orderAfterSale = this.findById(id);
        // 极速退款单
        orderAfterSale.setIsFastSale(DictConstant.Whether.yes.getCode());
        updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_8.getCode());
        // 退款
        orderPayRecordService.refund(orderAfterSale.getOrderId(), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSale.getApplyReason()), orderAfterSale.getApplyAmount());
    }

    @Override
    public boolean updateById(Long id, OrderAfterSaleDTO orderAfterSaleDTO) {
        OrderAfterSale orderAfterSale = this.findById(id);
        OrderAfterSaleMapStruct.INSTANCE.copyProperties(orderAfterSaleDTO, orderAfterSale);
        orderAfterSale.setId(id);
        return this.updateById(orderAfterSale);
    }

    @Override
    public OrderAfterSale findById(Long id) {
        OrderAfterSale orderAfterSale = this.getById(id);
        if (ObjectUtil.isNull(orderAfterSale)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderAfterSale;
    }

    @Override
    public OrderAfterSaleVO findVOById(Long id) {
        OrderAfterSale orderAfterSale = this.findById(id);
        return OrderAfterSaleMapStruct.INSTANCE.converToVO(orderAfterSale);
    }

    @Override
    public OrderAfterSaleDetailVO findOrderAfterSaleDetailVOById(Long id) {
        // 查询售后信息
        OrderAfterSale orderAfterSale = findAndResetContentById(id);
        OrderAfterSaleDetailVO orderAfterSaleDetailVO = OrderAfterSaleMapStruct.INSTANCE.converToOrderAfterSaleDetailVO(orderAfterSale);

        // 根据售后单的状态构建redis的key
        // 查询售后单过期时间信息
        List<DelayTaskRecord> delayTaskRecordList = delayTaskRecordService.queryLikeDelayTask(String.valueOf(id));
        Date timeoutDate = DelayTaskRecordUtil.getMaxTimeoutDate(delayTaskRecordList);
        orderAfterSaleDetailVO.setTimeout(ObjectUtil.isNotNull(timeoutDate) ? timeoutDate : orderAfterSale.getFinishTime());

        // 查询订单信息
        Long orderId = orderAfterSale.getOrderId();
        OrderInfoVO orderInfoVO = orderInfoService.findVOById(orderId);
        orderAfterSaleDetailVO.setOrderInfoVO(orderInfoVO);

        //客户收货地址信息
        OrderRecAddrVO orderRecAddrVO = orderRecAddrService.findVOById(orderId);
        orderAfterSaleDetailVO.setOrderRecAddrVO(orderRecAddrVO);
        // 查询店铺信息
        Shop shop = shopService.findById(orderInfoVO.getShopId());
        orderAfterSaleDetailVO.setShopName(shop.getName());

        // 查询商品信息
        OrderProdInfoVO orderProdInfoVO = orderProdInfoService.findVOById(orderId);
        orderAfterSaleDetailVO.setOrderProdInfoVO(orderProdInfoVO);

        // 查询订单客户信息
        OrderCustInfoVO orderCustInfoVO = orderCustInfoService.findVOById(orderId);
        orderAfterSaleDetailVO.setOrderCustInfoVO(orderCustInfoVO);

        // 查询订单支付记录信息
        OrderPayRecordVO orderPayRecordVO = orderPayRecordService.findVOByOrderId(orderId);
        orderAfterSaleDetailVO.setOrderPayRecordVO(orderPayRecordVO);

        // 查询协商历史信息
        List<OrderAfterSaleConsultRecordDetailVO> orderAfterSaleConsultRecordDetailVOList = orderAfterSaleConsultRecordService.queryDetailVOByOrderAfterSaleId(id);
        orderAfterSaleDetailVO.setOrderAfterSaleConsultRecordDetailVOList(orderAfterSaleConsultRecordDetailVOList);

        QueryWrapper<OrderAfterSaleImg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(OrderAfterSaleImg.ID, id);
        List<OrderAfterSaleImg> orderAfterSaleImgs = orderAfterSaleImgService.list(queryWrapper);
        List<String> imglist = new ArrayList<>();
        for (OrderAfterSaleImg orderAfterSaleImg : orderAfterSaleImgs) {
            imglist.add(orderAfterSaleImg.getCourierImgPath());
        }
        orderAfterSaleDetailVO.setImglist(imglist);

        // 查询商户地址信息
        List<BusiAddressVO> busiAddressVOList = busiAddressService.queryByBusinessId(orderInfoVO.getBusinessId());
        orderAfterSaleDetailVO.setBusiAddressList(busiAddressVOList);

        return orderAfterSaleDetailVO;
    }

    @NotNull
    private OrderAfterSale findAndResetContentById(Long id) {
        OrderAfterSale orderAfterSale = this.findById(id);

        boolean modify = false;
        if (StrUtil.isNotBlank(orderAfterSale.getBackCourierNumber())) {
            // 客户寄回快递单号不为空，则查询物流信息
            if (StrUtil.isBlank(orderAfterSale.getBackContent())) {
                // 查询物流信息
                String backContent = queryAliyunWuliu(orderAfterSale.getOrderId(), orderAfterSale.getBackCourierNumber(), orderAfterSale.getBackLogisticsCompany(), orderAfterSale.getBackCourierPhone());
                orderAfterSale.setBackContent(backContent);
                modify = true;
            } else {
                int defaultHour = Integer.parseInt(memoryService.queryConfig(ConfigConstant.QUERY_LOGISTICS_INTERVAL_TIME));
                // 根据最后一次更新时间和当前查询时间判断是否超过了默认3个小时，如果超过了则重新发起接口查询，如无则返回表中的信息
                long hour = DateUtil.between(orderAfterSale.getGmtModified(), new Date(), DateUnit.HOUR);
                if (hour >= defaultHour) {
                    String backContent = queryAliyunWuliu(orderAfterSale.getOrderId(), orderAfterSale.getBackCourierNumber(), orderAfterSale.getBackLogisticsCompany(), orderAfterSale.getBackCourierPhone());
                    orderAfterSale.setBackContent(backContent);
                    modify = true;
                }
            }
        }
        if (StrUtil.isNotBlank(orderAfterSale.getSendCourierNumber())) {
            // 商家再次发送快递单号不为空，则查询物流信息
            if (StrUtil.isBlank(orderAfterSale.getSendContent())) {
                // 查询物流信息
                String backContent = queryAliyunWuliu(orderAfterSale.getOrderId(), orderAfterSale.getSendCourierNumber(), orderAfterSale.getSendLogisticsCompany(), orderAfterSale.getSendCourierPhone());
                orderAfterSale.setSendContent(backContent);
                modify = true;
            } else {
                int defaultHour = Integer.parseInt(memoryService.queryConfig(ConfigConstant.QUERY_LOGISTICS_INTERVAL_TIME));
                // 根据最后一次更新时间和当前查询时间判断是否超过了默认3个小时，如果超过了则重新发起接口查询，如无则返回表中的信息
                long hour = DateUtil.between(orderAfterSale.getGmtModified(), new Date(), DateUnit.HOUR);
                if (hour >= defaultHour) {
                    String backContent = queryAliyunWuliu(orderAfterSale.getOrderId(), orderAfterSale.getSendCourierNumber(), orderAfterSale.getSendLogisticsCompany(), orderAfterSale.getSendCourierPhone());
                    orderAfterSale.setSendContent(backContent);
                    modify = true;
                }
            }
        }

        // 存在变更
        if (modify) {
            this.updateById(orderAfterSale);
            // 查询最新记录
            return this.findById(id);
        }
        // 返回当前查询结果
        return orderAfterSale;
    }

    /**
     * 查询物流信息
     *
     * @param orderId
     * @param courierNumber
     * @param logisticsCompany
     * @param phone
     */
    private String queryAliyunWuliu(Long orderId, String courierNumber, String logisticsCompany, String phone) {
        try {
            AliyunWuliuService aliyunWuliuService = thirdConfigService.aliyunWuliuService();
            // 顺丰物流还需要额外用手机号查询
            String returnStr = (WeikbestConstant.SFEXPRESS.equals(logisticsCompany)) ?
                    aliyunWuliuService.queryAliyunWuliu(courierNumber, phone) :
                    aliyunWuliuService.queryAliyunWuliu(courierNumber);
            log.info("---------查询订单{},运单号为{}的售后物流信息结束,物流信息：{}", orderId, courierNumber, returnStr);
            return returnStr;
        } catch (Exception e) {
            throw new WeikbestException(e);
        }
    }

    @Override
    public IPage<OrderAfterSaleListVO> queryPage(OrderAfterSaleQO orderAfterSaleQO, PageReq pageReq) {
        IPage<OrderAfterSaleListVO> queryPage = this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), orderAfterSaleQO);
        return queryPage.convert(orderAfterSaleListVO -> {
            if ("asc".equals(orderAfterSaleQO.getOrderByOrderTime())){
                // 查询售后单过期时间信息
                List<DelayTaskRecord> delayTaskRecordList = delayTaskRecordService.queryLikeDelayTask(String.valueOf(orderAfterSaleListVO.getId()));
                Date timeoutDate = DelayTaskRecordUtil.getMaxTimeoutDate(delayTaskRecordList);
                orderAfterSaleListVO.setTimeOut(ObjectUtil.isNotNull(timeoutDate) ? timeoutDate : orderAfterSaleListVO.getFinishTime());
            }
            orderAfterSaleListVO.setTimeOut(ObjectUtil.isNotNull(orderAfterSaleListVO.getTimeOut()) ? orderAfterSaleListVO.getTimeOut() : orderAfterSaleListVO.getFinishTime());
            orderAfterSaleListVO.setOrderSendType(StringUtils.isNotBlank(orderAfterSaleListVO.getOrderSendType()) && !DictConstant.Whether.no.getCode().equals(orderAfterSaleListVO.getOrderSendType()) ? DictConstant.Whether.yes.getCode() : DictConstant.Whether.no.getCode());
            return orderAfterSaleListVO;
        });
    }

    @Override
    public List<OrderAfterSaleDetailExcel> downloadDetail(OrderAfterSaleQO orderAfterSaleQO) {
        List<OrderAfterSaleDetailExcel> orderAfterSaleDetailExcelList = this.baseMapper.downloadDetail(orderAfterSaleQO);
        // 字典值转换
        orderAfterSaleDetailExcelList.forEach(orderAfterSaleDetailExcel -> {
            orderAfterSaleDetailExcel.setAfterSaleApplyType(Memory.getDict(DictConstant.AfterSaleApplyType.getDictTypeNumber(), orderAfterSaleDetailExcel.getAfterSaleApplyType()));
            orderAfterSaleDetailExcel.setOrderSource(Memory.getDict(DictConstant.OrderSource.getDictTypeNumber(), orderAfterSaleDetailExcel.getOrderSource()));
            orderAfterSaleDetailExcel.setAfterSaleStatus(Memory.getDict(DictConstant.AfterSaleStatus.getDictTypeNumber(), orderAfterSaleDetailExcel.getAfterSaleStatus()));
            orderAfterSaleDetailExcel.setApplyReason(Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSaleDetailExcel.getApplyReason()));
            orderAfterSaleDetailExcel.setLogisticsCompany(Memory.getLogisticsCompanyName(orderAfterSaleDetailExcel.getLogisticsCompany()));
            orderAfterSaleDetailExcel.setBackLogisticsCompany(Memory.getDict(DictConstant.OrderStatus.getDictTypeNumber(), orderAfterSaleDetailExcel.getBackLogisticsCompany()));
            orderAfterSaleDetailExcel.setSendFlag(Memory.getDict(DictConstant.OrderStatus.getDictTypeNumber(), orderAfterSaleDetailExcel.getOrderStatus()));
        });
        return orderAfterSaleDetailExcelList;
    }

    @Override
    public List<AppOrderAfterSaleApplyDTO> queryPageAppOrderAfterApply(Map<String, Object> paramMap) {
        return this.baseMapper.queryPageAppOrderAfterApply(paramMap);
    }

    @Override
    public List<AppOrderAfterSaleListDTO> queryPageAppOrderAfterList(Map<String, Object> paramMap) {
        return this.baseMapper.queryPageAppOrderAfterList(paramMap);
    }

    @Override
    public List<OrderAfterSaleKeyGroupVO> queryGroupOrderAfterSaleKey(Long businessId, Long shopId) {
        // 查询订单售后状态字典
        Map<String, String> orderAfterSaleKeyDictMap = memoryService.queryDictReturnMap(DictConstant.AfterSaleKey.getDictTypeNumber());
        List<OrderAfterSaleKeyGroupVO> resultList = new ArrayList<>();
        // 初始总数
        resultList.add(new OrderAfterSaleKeyGroupVO().setAfterSaleKey("").setTotal(WeikbestConstant.ZERO_INT));
        // 初始数据
        orderAfterSaleKeyDictMap.forEach((key, value) -> resultList.add(new OrderAfterSaleKeyGroupVO().setAfterSaleKey(key).setTotal(WeikbestConstant.ZERO_INT)));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("businessId", businessId);
        paramMap.put("shopId", shopId);
        List<OrderAfterSaleKeyGroupVO> orderAfterSaleKeyGroupVOList = this.baseMapper.queryGroupOrderAfterSaleKey(paramMap);
        Map<String, OrderAfterSaleKeyGroupVO> orderAfterSaleKeyGroupVOMap = orderAfterSaleKeyGroupVOList.stream().collect(Collectors.toMap(OrderAfterSaleKeyGroupVO::getAfterSaleKey, orderAfterSaleKeyGroupVO -> orderAfterSaleKeyGroupVO));

        // 遍历数据赋值
        resultList.forEach(orderAfterSaleKeyGroupVO -> {
            OrderAfterSaleKeyGroupVO totalVO = orderAfterSaleKeyGroupVOMap.get(orderAfterSaleKeyGroupVO.getAfterSaleKey());
            if (ObjectUtil.isNotEmpty(totalVO)) {
                orderAfterSaleKeyGroupVO.setTotal(totalVO.getTotal());
            }
        });

        // 查询总数
        int sum = orderAfterSaleKeyGroupVOList.stream().mapToInt(OrderAfterSaleKeyGroupVO::getTotal).sum();
        resultList.get(WeikbestConstant.ZERO_INT).setTotal(sum);
        return resultList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean businessConfirmCustomerApply(Long id, OrderAfterSaleBusinessConfirmCustomerApplyDTO orderAfterSaleBusinessConfirmCustomerApplyDTO) {
        OrderAfterSale orderAfterSale = this.findById(id);
        String sourceAfterSaleStatus = orderAfterSale.getAfterSaleStatus();
        Boolean businessConfirmFlag = orderAfterSaleBusinessConfirmCustomerApplyDTO.getBusinessConfirmFlag();
        String afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_10.getCode();
        // 如果拒绝客户申请
        if (!businessConfirmFlag) {
            orderAfterSale.setRejectReason(orderAfterSaleBusinessConfirmCustomerApplyDTO.getRejectReason());
            orderAfterSale.setRejectDetail(orderAfterSaleBusinessConfirmCustomerApplyDTO.getRejectDetail());
            //删除商家待处理任务延迟队列
//            orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.removeTask(id,sourceAfterSaleStatus);
            // 添加商家拒绝后客户处理延时队列
            orderAfterSaleCustomerExecuteTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
        }
        // 如果同意客户申请
        else {
            if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
                // 仅退款
                afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_101.getCode();
                orderAfterSale.setRefundTrend(orderAfterSaleBusinessConfirmCustomerApplyDTO.getRefundTrend());
            } else {
                // 退货退款、换货类型
                Long businessAddressId = orderAfterSaleBusinessConfirmCustomerApplyDTO.getBusiAddressId();
                if (ObjectUtil.isNull(businessAddressId)) {
                    throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "商家同意客户申请，请选择商家地址信息");
                }

                BusiAddress busiAddress = busiAddressService.findById(businessAddressId);
                orderAfterSale.setBackAddress(BusiAddressUtil.busiAddress(busiAddress));

                if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.return_and_refund.getCode())) {
                    // 退货退款类型
                    afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_201.getCode();
                    orderAfterSale.setRefundTrend(orderAfterSaleBusinessConfirmCustomerApplyDTO.getRefundTrend());

                    // 添加商家同意后客户发货处理延时队列
                    orderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
                }
                if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.exchange.getCode())) {
                    // 换货类型
                    afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_301.getCode();
                    // 添加商家同意后客户发货处理延时队列
                    orderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
                }
            }

        }
        // 更新售后信息
        boolean update = this.updateAfterSaleStatus(orderAfterSale, afterSaleStatus);

        // 删除商家处理售后单延时队列
        orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.removeTask(orderAfterSale.getId(), sourceAfterSaleStatus);

        // 如果同意客户申请 并且 仅退款
        if (businessConfirmFlag && StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
            // 商家退款
            orderPayRecordService.refund(orderAfterSale.getOrderId(), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSale.getApplyReason()), orderAfterSale.getApplyAmount());
            // 退款完成，状态变更为： 1-售后成功
            orderAfterSaleFinish(orderAfterSale.getId());
        }

        return update;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean businessConfirmCustomerApplyForApi(Long id, OrderAfterSaleBusinessConfirmCustomerApplyDTO orderAfterSaleBusinessConfirmCustomerApplyDTO) {
        OrderAfterSale orderAfterSale = this.findById(id);
        String sourceAfterSaleStatus = orderAfterSale.getAfterSaleStatus();
        if(!DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode().equals(sourceAfterSaleStatus) && !DictConstant.AfterSaleStatus.afterSaleStatus_5.getCode().equals(sourceAfterSaleStatus)){
            throw new WeikbestException("售后单前置状态不是【客户申请售后，待商家处理】或【客户修改申请信息，待商家处理】，请核实售后单信息！");
        }
        Boolean businessConfirmFlag = orderAfterSaleBusinessConfirmCustomerApplyDTO.getBusinessConfirmFlag();
        String afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_10.getCode();
        // 如果拒绝客户申请
        if (!businessConfirmFlag) {
            orderAfterSale.setRejectReason(orderAfterSaleBusinessConfirmCustomerApplyDTO.getRejectReason());
            orderAfterSale.setRejectDetail(orderAfterSaleBusinessConfirmCustomerApplyDTO.getRejectDetail());
            //删除商家待处理任务延迟队列
//            orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.removeTask(id,sourceAfterSaleStatus);
            // 添加商家拒绝后客户处理延时队列
            orderAfterSaleCustomerExecuteTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
        }
        // 如果同意客户申请
        else {
            if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
                // 仅退款
                afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_101.getCode();
                orderAfterSale.setRefundTrend(orderAfterSaleBusinessConfirmCustomerApplyDTO.getRefundTrend());
            } else {
                // 退货退款、换货类型
                Long businessAddressId = orderAfterSaleBusinessConfirmCustomerApplyDTO.getBusiAddressId();
                if (ObjectUtil.isNull(businessAddressId)) {
                    throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "商家同意客户申请，请选择商家地址信息");
                }

                BusiAddress busiAddress = busiAddressService.findById(businessAddressId);
                orderAfterSale.setBackAddress(BusiAddressUtil.busiAddress(busiAddress));

                if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.return_and_refund.getCode())) {
                    // 退货退款类型
                    afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_201.getCode();
                    orderAfterSale.setRefundTrend(orderAfterSaleBusinessConfirmCustomerApplyDTO.getRefundTrend());

                    // 添加商家同意后客户发货处理延时队列
                    orderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
                }
                if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.exchange.getCode())) {
                    // 换货类型
                    afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_301.getCode();
                    // 添加商家同意后客户发货处理延时队列
                    orderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
                }
            }

        }
        // 更新售后信息
        boolean update = this.updateAfterSaleStatus(orderAfterSale, afterSaleStatus);

        // 删除商家处理售后单延时队列
        orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.removeTask(orderAfterSale.getId(), sourceAfterSaleStatus);

        // 如果同意客户申请 并且 仅退款
        if (businessConfirmFlag && StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
            // 商家退款
            orderPayRecordService.refund(orderAfterSale.getOrderId(), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSale.getApplyReason()), orderAfterSale.getApplyAmount());
            // 退款完成，状态变更为： 1-售后成功
            orderAfterSaleFinish(orderAfterSale.getId());
        }

        return update;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean businessConfirmCustomerDelivery(Long id, OrderAfterSaleBusinessConfirmCustomerDeliveryDTO orderAfterSaleBusinessConfirmCustomerApplyDTO) {
        OrderAfterSale orderAfterSale = this.findById(id);
        String sourceOrderAfterSale = orderAfterSale.getAfterSaleStatus();
        Boolean businessConfirmFlag = orderAfterSaleBusinessConfirmCustomerApplyDTO.getBusinessConfirmFlag();
        if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
            throw new WeikbestException("申请类型是【仅退款】不存在商家收货处理，请核实售后单信息！");
        }
        if (!StrUtil.equals(sourceOrderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_6.getCode())) {
            throw new WeikbestException("售后单前置状态不是【客户已寄回商品，待商家收货】，请核实售后单信息！");
        }
        String afterSaleStatus = "";
        // 如果拒绝收货
        if (!businessConfirmFlag) {
            orderAfterSale.setRejectReason(orderAfterSaleBusinessConfirmCustomerApplyDTO.getRejectReason());
            orderAfterSale.setRejectDetail(orderAfterSaleBusinessConfirmCustomerApplyDTO.getRejectDetail());
            if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.return_and_refund.getCode())) {
                // 退货退款类型
                afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_206.getCode();
            }
            if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.exchange.getCode())) {
                // 换货类型
                afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_306.getCode();
            }
            // 添加商家拒绝后客户处理延时队列
            orderAfterSaleBusinessRejectDeliveryCustomerExecuteTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
        }
        // 如果确认收货
        else {
            if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.return_and_refund.getCode())) {
                // 退货退款类型
                afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_203.getCode();
                // 添加商家收货后超时退款处理延时队列
                orderAfterSaleBusinessDeliveryAfterTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
            }
            if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.exchange.getCode())) {
                // 换货类型
                afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_303.getCode();
                // 添加商家收货后超时发货处理延时队列
                orderAfterSaleBusinessDeliveryAfterTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
            }
        }
        // 删除客户寄回商品后商家确认收货处理过期延时任务
        orderAfterSaleBusinessConfirmCustomerDeliveryTimeoutDelayTaskProcess.removeTask(id, sourceOrderAfterSale);

        // 更新售后信息
        return this.updateAfterSaleStatus(orderAfterSale, afterSaleStatus);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean businessConfirmDeliveryAndRefund(Long id) {
        OrderAfterSale orderAfterSale = this.findById(id);
        if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
            throw new WeikbestException("申请类型是【仅退款】不存在商家收货退款处理，请核实售后单信息！");
        }
        if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_203.getCode())) {
            throw new WeikbestException("售后单前置状态不是【商家已收货，待商家确认签收】，请核实售后单信息！");
        }

        // 状态变更为： 205-商家已同意退款
        boolean update = this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_205.getCode());

        // 商家退款
        orderPayRecordService.refund(orderAfterSale.getOrderId(), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSale.getApplyReason()), orderAfterSale.getApplyAmount());
        // 退款完成，状态变更为： 1-售后成功
        orderAfterSaleFinish(orderAfterSale.getId());
        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean businessConfirmAndReDelivery(OrderAfterSaleBusinessConfirmAndReDeliveryDTO orderAfterSaleBusinessConfirmAndReDeliveryDTO) {
        Long id = orderAfterSaleBusinessConfirmAndReDeliveryDTO.getId();
        OrderAfterSale orderAfterSale = this.findById(orderAfterSaleBusinessConfirmAndReDeliveryDTO.getId());
        OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
        if (!StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.exchange.getCode())) {
            throw new WeikbestException("申请类型不是【换货】不存在商家再次发货处理，请核实售后单信息！");
        }
        if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_303.getCode())) {
            throw new WeikbestException("售后单前置状态不是【商家已收货，待商家确认】，请核实售后单信息！");
        }

        // 设置发货信息
        orderAfterSale.setSendCourierNumber(orderAfterSaleBusinessConfirmAndReDeliveryDTO.getSendCourierNumber());
        orderAfterSale.setSendLogisticsCompany(orderAfterSaleBusinessConfirmAndReDeliveryDTO.getSendLogisticsCompany());
        orderAfterSale.setDescription(orderAfterSaleBusinessConfirmAndReDeliveryDTO.getDescription());
        orderAfterSale.setSendLogisticsTime(DateUtil.date());
        orderAfterSale.setSendType(DictConstant.Whether.yes.getCode());
        // 设置发货人手机号
        BusiAddress busiAddress = prodBusiAddrService.findOneBusiAddressByProdIdOrBusinessId(orderInfo.getProdId(), orderInfo.getBusinessId());
        orderAfterSale.setSendCourierPhone(BusiAddressUtil.getContact(busiAddress));
        // 状态变更为： 304-商家已重新发货，待客户确认
        orderAfterSale.setAfterSaleStatus(DictConstant.AfterSaleStatus.afterSaleStatus_304.getCode());
        orderAfterSale.setAfterSaleKey(DictConstant.AfterSaleStatus.getAfterSaleKey(orderAfterSale.getApplyType(), DictConstant.AfterSaleStatus.afterSaleStatus_304.getCode()).getCode());
        //删除商家超时处理任务
        orderAfterSaleBusinessDeliveryAfterTimeoutDelayTaskProcess.removeTask(id,DictConstant.AfterSaleStatus.afterSaleStatus_303.getCode());
        //添加客户超时处理任务
        orderAfterSaleCustomerExecuteTimeoutDelayTaskProcess.putTask(id,DictConstant.AfterSaleStatus.afterSaleStatus_304.getCode());

        boolean update = this.updateById(orderAfterSale);

        // 新增售后协商记录
        Long historyId = orderAfterSaleConsultRecordService.insertByOrderAfterSale(orderAfterSale, DictConstant.AfterSaleChangeType.change_business_logistics.getCode());
        List<String> courierImgPathList = orderAfterSaleBusinessConfirmAndReDeliveryDTO.getCourierImgPathList();
        if (CollectionUtil.isNotEmpty(courierImgPathList)) {
            // 新建售后商家再次发货凭证图片
            List<OrderAfterSaleLogisticsImg> orderAfterSaleLogisticsImgList = courierImgPathList.stream().map(courierImgPath -> {
                OrderAfterSaleLogisticsImg orderAfterSaleLogisticsImg = new OrderAfterSaleLogisticsImg();
                orderAfterSaleLogisticsImg.setCourierImgType(DictConstant.CourierImgType.courierImgType_3.getCode());
                orderAfterSaleLogisticsImg.setCourierImgPath(courierImgPath);
                orderAfterSaleLogisticsImg.setId(id);
                return orderAfterSaleLogisticsImg;
            }).collect(Collectors.toList());
            orderAfterSaleLogisticsImgService.saveBatch(orderAfterSaleLogisticsImgList);

            // 新建售后商家再次发货凭证图片历史
            List<OrderAfterSaleLogisticsImgHis> orderAfterSaleLogisticsImgHisList = orderAfterSaleLogisticsImgList.stream().map(orderAfterSaleLogisticsImg -> {
                OrderAfterSaleLogisticsImgHis orderAfterSaleLogisticsImgHis = OrderAfterSaleLogisticsImgHisMapStruct.INSTANCE.converToEntity(orderAfterSaleLogisticsImg);
                orderAfterSaleLogisticsImgHis.setHistoryId(historyId);
                return orderAfterSaleLogisticsImgHis;
            }).collect(Collectors.toList());
            orderAfterSaleLogisticsImgHisService.saveBatch(orderAfterSaleLogisticsImgHisList);
        }

        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean batchUpdateOnlyRefund(List<Long> ids) {
        List<OrderAfterSale> orderAfterSaleList = this.listByIds(ids);
        // 同意客户申请 仅退款
        for (OrderAfterSale orderAfterSale : orderAfterSaleList) {
            String sourceAfterSaleStatus = orderAfterSale.getAfterSaleStatus();
            String afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_101.getCode();
            orderAfterSale.setRefundTrend(DictConstant.RefundTrend.refundTrend_1.getCode());

            // 更新售后信息
            this.updateAfterSaleStatus(orderAfterSale, afterSaleStatus);
            // 删除商家处理售后单延时队列
            orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.removeTask(orderAfterSale.getId(), sourceAfterSaleStatus);

            // 商家退款
            orderPayRecordService.refund(orderAfterSale.getOrderId(), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSale.getApplyReason()), orderAfterSale.getApplyAmount());
            // 退款完成，状态变更为： 1-售后成功
            orderAfterSaleFinish(orderAfterSale.getId());
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean batchUpdateReturnAndRefundApply(List<Long> ids) {
        List<OrderAfterSale> orderAfterSaleList = this.listByIds(ids);
        // 同意客户申请 退货退款
        for (OrderAfterSale orderAfterSale : orderAfterSaleList) {
            String sourceAfterSaleStatus = orderAfterSale.getAfterSaleStatus();
            OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());

            String afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_201.getCode();
            orderAfterSale.setRefundTrend(DictConstant.RefundTrend.refundTrend_1.getCode());
            BusiAddress busiAddress = prodBusiAddrService.findOneBusiAddressByProdIdOrBusinessId(orderInfo.getProdId(), orderInfo.getBusinessId());
            orderAfterSale.setBackAddress(BusiAddressUtil.busiAddress(busiAddress));
            // 添加商家同意后客户发货处理延时队列
            orderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess.putTask(orderAfterSale.getId(), afterSaleStatus);

            // 更新售后信息
            this.updateAfterSaleStatus(orderAfterSale, afterSaleStatus);
            // 删除商家处理售后单延时队列
            orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.removeTask(orderAfterSale.getId(), sourceAfterSaleStatus);
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean batchUpdateExchangeApply(List<Long> ids) {
        List<OrderAfterSale> orderAfterSaleList = this.listByIds(ids);
        // 同意客户申请 换货
        for (OrderAfterSale orderAfterSale : orderAfterSaleList) {
            String sourceAfterSaleStatus = orderAfterSale.getAfterSaleStatus();
            OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());

            String afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_301.getCode();
            BusiAddress busiAddress = prodBusiAddrService.findOneBusiAddressByProdIdOrBusinessId(orderInfo.getProdId(), orderInfo.getBusinessId());
            orderAfterSale.setBackAddress(BusiAddressUtil.busiAddress(busiAddress));
            // 添加商家同意后客户发货处理延时队列
            orderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess.putTask(orderAfterSale.getId(), afterSaleStatus);

            // 更新售后信息
            this.updateAfterSaleStatus(orderAfterSale, afterSaleStatus);
            // 删除商家处理售后单延时队列
            orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.removeTask(orderAfterSale.getId(), sourceAfterSaleStatus);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean batchUpdateReturnAndRefund(List<Long> ids) {
        List<OrderAfterSale> orderAfterSaleList = this.listByIds(ids);
        // 同意客户 退货退款（商家已收到货）
        for (OrderAfterSale orderAfterSale : orderAfterSaleList) {

            // 状态变更为： 205-商家已同意退款
            this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_205.getCode());

            // 商家退款
            orderPayRecordService.refund(orderAfterSale.getOrderId(), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSale.getApplyReason()), orderAfterSale.getApplyAmount());
            // 退款完成，状态变更为： 1-售后成功
            orderAfterSaleFinish(orderAfterSale.getId());
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void businessExecuteTimeout(Long id, String afterSaleStatus) {
        OrderAfterSale orderAfterSale = this.findById(id);
        if (afterSaleStatus != null) {
            // 判断状态是否一致
            if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), afterSaleStatus)) {
                log.info("当前订单状态：{}，超时订单状态：{}，不一致，商户超时处理结束...", orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode());
                return;
            }
        } else {
            // 判断状态是否一致
            if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode())) {
                log.info("当前订单状态：{}，超时订单状态：{}，不一致，商户超时处理结束...", orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode());
                return;
            }
        }

        // 判断执行哪种超时处理
        if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
            // 仅退款类型，平台执行退款
            businessExecuteTimeoutByOnlyRefund(orderAfterSale);
        } else {
            // 退货退款类型、换货类型，自动同意客户申请
            businessExecuteTimeoutByAutoAgreeApply(orderAfterSale);
        }
    }


    /**
     * 仅退款类型售后单商家超时处理，平台执行退款
     * 客户发起申请后，商家未处理
     *
     * @param orderAfterSale 售后实体
     */
    @Transactional(rollbackFor = Exception.class)
    public void businessExecuteTimeoutByOnlyRefund(OrderAfterSale orderAfterSale) {
        // 状态变更为： 11-商家未处理，平台同意申请，并需要进行退款
        this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_11.getCode());

        // 商家退款
        orderPayRecordService.refund(orderAfterSale.getOrderId(), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSale.getApplyReason()), orderAfterSale.getApplyAmount());
        // 退款完成，状态变更为： 0-售后关闭
        orderAfterSaleClose(orderAfterSale.getId());
    }

    /**
     * 售后单关闭
     * 订单完成
     *
     * @param id 售后单ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void orderAfterSaleClose(Long id) {
        OrderAfterSale orderAfterSale = this.findById(id);
        orderAfterSale.setFinishTime(DateUtil.date());
        this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_0.getCode());

        OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
        orderInfo.setOrderFinishTime(DateUtil.date());
        orderInfo.setOrderAfterFlag(DictConstant.Whether.no.getCode());
        if (StrUtil.equals(orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_10.getCode()) || StrUtil.equals(orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_30.getCode())) {
            orderInfoService.updateOrderStatus(orderInfo, DictConstant.OrderStatus.orderStatus_99.getCode(), "售后单处理超时关闭，订单自动关闭");
        }
    }

    /**
     * 售后单关闭
     * 订单完成
     *
     * @param id 售后单ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void orderAfterSaleFinish(Long id) {
        OrderAfterSale orderAfterSale = this.findById(id);
        orderAfterSale.setFinishTime(DateUtil.date());
        this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_1.getCode());

        OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
        orderInfo.setOrderFinishTime(DateUtil.date());
        orderInfo.setOrderAfterFlag(DictConstant.Whether.no.getCode());
        orderInfoService.updateOrderStatus(orderInfo, DictConstant.OrderStatus.orderStatus_99.getCode(), "售后单完成，订单自动关闭");
    }

    /**
     * 退货退款类型、换货类型售后单商家超时处理，自动同意客户申请
     * 客户发起申请后，商家未处理
     *
     * @param orderAfterSale
     */
    @Transactional(rollbackFor = Exception.class)
    public void businessExecuteTimeoutByAutoAgreeApply(OrderAfterSale orderAfterSale) {
        // 状态变更为： 11-商家未处理，平台同意申请，并需要进行退款
        this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_11.getCode());

        orderAfterSale = this.findById(orderAfterSale.getId());
        String applyType = orderAfterSale.getApplyType();
        // 设置退货地址
        OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
        BusiAddress busiAddress = busiAddressService.getDefaultByBusinessId(orderInfo.getBusinessId());
        orderAfterSale.setBackAddress(BusiAddressUtil.busiAddress(busiAddress));

        if (StrUtil.equals(applyType, DictConstant.AfterSaleApplyType.return_and_refund.getCode())) {
            // 状态变更为： 201-商家同意退货退款，待客户寄回商品
            this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_201.getCode());
        } else {
            // 状态变更为： 301-商家同意换货，待客户寄回商品
            this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_301.getCode());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void customerExecuteTimeout(Long id, String afterSaleStatus) {
        OrderAfterSale orderAfterSale = this.findById(id);
        if (afterSaleStatus != null) {
            // 判断状态是否一致
            if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), afterSaleStatus)) {
                log.info("当前订单状态：{}，超时订单状态：{}，不一致，客户超时处理结束...", orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_10.getCode());
                return;
            }
        } else {
            // 判断状态是否一致
            if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_10.getCode())) {
                log.info("当前订单状态：{}，超时订单状态：{}，不一致，商户超时处理结束...", orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode());
                return;
            }
        }

        // 状态变更：客户未处理，平台自动关闭售后
        this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_3.getCode());

        // 状态变更：售后关闭
        orderAfterSaleClose(orderAfterSale.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void customerDeliveryTimeout(Long id, String afterSaleStatus) {
        OrderAfterSale orderAfterSale = this.findById(id);
        // 判断状态是否一致
        if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), afterSaleStatus)) {
            log.info("当前订单状态：{}，超时订单状态：{}，不一致，客户发货超时处理结束...", orderAfterSale.getAfterSaleStatus(), afterSaleStatus);
            return;
        }

        // 状态变更：客户未处理，平台自动关闭售后
        this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_3.getCode());

        // 状态变更：售后关闭
        orderAfterSaleClose(orderAfterSale.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void businessConfirmCustomerDeliveryTimeout(Long id, String afterSaleStatus) {
        OrderAfterSale orderAfterSale = this.findById(id);
        if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
            throw new WeikbestException("申请类型是【仅退款】不存在商家收货处理，请核实售后单信息！客户寄回商品后商家确认收货超时处理结束...");
        }
        // 判断状态是否一致
        if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), afterSaleStatus)) {
            log.info("当前订单状态：{}，超时订单状态：{}，不一致，客户寄回商品后商家确认收货超时处理结束...", orderAfterSale.getAfterSaleStatus(), afterSaleStatus);
            return;
        }

        // 如果是换货流程，系统默认处理为确认收货自动进入下一阶段- 商家已收货，待商家确认 TODO
        // 如果是退货退款流程，系统默认处理为确认收货自动进入下一阶段-商家已收货，待商家确认签收
        String updateAfterSaleStatus = "";
        if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.return_and_refund.getCode())) {
            // 退货退款类型
            updateAfterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_203.getCode();
            // 添加商家收货后超时退款处理延时队列
            orderAfterSaleBusinessDeliveryAfterTimeoutDelayTaskProcess.putTask(id, updateAfterSaleStatus);
        }
        if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.exchange.getCode())) {
            // 换货类型
            updateAfterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_303.getCode();
            // 添加商家收货后超时发货处理延时队列
            orderAfterSaleBusinessDeliveryAfterTimeoutDelayTaskProcess.putTask(id, updateAfterSaleStatus);
        }

        // 更新售后信息
        this.updateAfterSaleStatus(orderAfterSale, updateAfterSaleStatus);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void businessRejectDeliveryCustomerExecuteTimeout(Long id, String afterSaleStatus) {
        OrderAfterSale orderAfterSale = this.findById(id);
        // 判断状态是否一致
        if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), afterSaleStatus)) {
            log.info("当前订单状态：{}，超时订单状态：{}，不一致，商户拒绝收货后客户超时处理结束...", orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_201.getCode());
            return;
        }

        // 状态变更：客户未处理，平台自动关闭售后
        this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_3.getCode());

        // 状态变更：售后关闭
        orderAfterSaleClose(orderAfterSale.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void businessDeliveryAfterTimeout(Long id, String afterSaleStatus) {
        OrderAfterSale orderAfterSale = this.findById(id);
        // 判断状态是否一致
        if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), afterSaleStatus)) {
            log.info("当前订单状态：{}，超时订单状态：{}，不一致，商户确认收货后超时处理结束...", orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_201.getCode());
            return;
        }

        // 平台退款
        // 状态变更为： 11-商家未处理，平台同意申请，并需要进行退款
        this.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_11.getCode());

        // 商家退款
        orderPayRecordService.refund(orderAfterSale.getOrderId(), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSale.getApplyReason()), orderAfterSale.getApplyAmount());
        // 退款完成，状态变更为： 0-售后关闭
        orderAfterSaleClose(orderAfterSale.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateAfterSaleStatus(OrderAfterSale orderAfterSale, String afterSaleStatus) {
        boolean update;
        // 更新售后单单变更记录
        String key = RedisKey.generalKey(RedisKey.Lock.LOCK_ORDER_STATUS_KEY, orderAfterSale.getId());
        redisLock.lock(key);
        try {
            // 售后单状态
            orderAfterSale.setAfterSaleStatus(afterSaleStatus);
            orderAfterSale.setAfterSaleKey(DictConstant.AfterSaleStatus.getAfterSaleKey(orderAfterSale.getApplyType(), afterSaleStatus).getCode());
            update = this.updateById(orderAfterSale);

            // 新增售后协商记录
            orderAfterSaleConsultRecordService.insertByOrderAfterSale(orderAfterSale, DictConstant.AfterSaleStatus.getAfterSaleChangeType(afterSaleStatus).getCode());

            //如果是分账订单，则设置延时任务
            if (DictConstant.AfterSaleKey.isFinish(orderAfterSale.getAfterSaleKey())) {
                OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
                if (StrUtil.equals(orderInfo.getIsReceiveOrder(), DictConstant.Whether.yes.getCode())) {
                    //添加分账押金回退清算延时任务，发货后7天
                    orderSettlementByDepositDayDelayTaskProcess.putTask(orderInfo.getId());
                }
            }

        } finally {
            redisLock.unlock(key);
        }

        return update;
    }

    @Override
    public List<OrderAfterSaleVO> queryOrderAfterSaleVOHisByOrderId(Long orderId) {
        OrderInfo orderInfo = orderInfoService.findById(orderId);

        List<OrderAfterSale> orderAfterSaleList = this.list(new QueryWrapper<OrderAfterSale>().eq(OrderAfterSale.ORDER_ID, orderId).ne(OrderAfterSale.ID, orderInfo.getOrderAfterSaleId()).orderByDesc(OrderAfterSale.AFTER_SALE_NUM));
        return orderAfterSaleList.stream().map(OrderAfterSaleMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean refundRightNow(Long id) {
        //售后单信息
        OrderAfterSale orderAfterSale = this.findById(id);
        if (DictConstant.AfterSaleStatus.afterSaleStatus_12.isEquals(orderAfterSale.getAfterSaleStatus())){
            throw new WeikbestException("退款正在处理中，请勿重复申请！");
        }
        //订单信息
        OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
        //支付信息
        OrderPayRecord orderPayRecord = orderPayRecordService.findByOrderId(orderInfo.getId());
        if (ObjectUtil.isNull(orderPayRecord)) {
            throw new WeikbestException("不存在支付记录，无法退款！");
        }
        if (StrUtil.isNotBlank(orderPayRecord.getRefundStatus()) && (DictConstant.RefundStatus.refundStatus_2.getCode().equals(orderPayRecord.getRefundStatus()) || DictConstant.RefundStatus.refundStatus_1.getCode().equals(orderPayRecord.getRefundStatus()))){
            throw new WeikbestException("已存在退款记录，无法退款！");
        }

        // 商家退款
        try {
            //立即退款
            orderPayRecordService.refund(orderAfterSale.getOrderId(), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSale.getApplyReason()), orderAfterSale.getApplyAmount());
            //退款成功，直接完结售后单
            orderAfterSaleFinish(orderAfterSale.getId());
            return true;
        } catch (WeikbestException e) {
            // 2.重新入队执行
            orderAfterSaleRefundExecuteTimeOutDelayTaskProcess.putTask(id, orderAfterSale.getAfterSaleStatus());
            //发布异步事件
            // 更新售后单状态 退款中
            applicationEventPublisher.publishEvent(new OrderAfterSaleEvent(orderAfterSale.getId()));
            //退款出现异常，退款失败
            log.error("退款失败!" + e.getMsg(), e);
            //抛出异常回滚
            throw new WeikbestException("退款异常："+ e.getMessage());

        }
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void refundDelayTimeOut(Long id) {
        log.info("进入定时退款方法：售后单：{}",id);
        OrderAfterSale orderAfterSale = this.findById(id);
        //订单信息
        OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
        //没有订单信息 直接返回
        if (ObjectUtil.isNull(orderInfo)) {
            return;
        }
        //支付信息
        OrderPayRecord orderPayRecord = orderPayRecordService.findByOrderId(orderInfo.getId());
        // 1. 没有支付记录直接返回不执行
        if (ObjectUtil.isNull(orderPayRecord)) {
            return;
        }
        // 已退款 直接返回
        if (StrUtil.isNotBlank(orderPayRecord.getRefundStatus()) && DictConstant.RefundStatus.refundStatus_1.getCode().equals(orderPayRecord.getRefundStatus())){
            return;
        }
        // 商家退款
        try {
            orderPayRecordService.refund(orderAfterSale.getOrderId(), Memory.getDict(DictConstant.AfterSaleApplyReason.getDictTypeNumber(), orderAfterSale.getApplyReason()), orderAfterSale.getApplyAmount());
        } catch (WeikbestException e) {
            // 2.另起一个线程，重新入队执行
            orderAfterSaleRefundExecuteTimeOutDelayTaskProcess.putTask(id, orderAfterSale.getAfterSaleStatus());
            //退款出现异常，退款失败
            log.error("退款失败!" + e.getMsg(), e);
            // 1. 记录异常信息
            orderAfterSale.setRefundFailureReason("退款失败!" + e.getMsg());
            Integer time = Optional.ofNullable(orderAfterSale.getRefundFailureTimes()).orElse(0);
            orderAfterSale.setRefundFailureTimes(time + 1);
            this.updateById(orderAfterSale);

            //抛出异常回滚
            throw new WeikbestException(ResultConstant.THIRD_SERVICE_FAIL.getCode(), e.getMessage(), e);

        }
        // 退款完成，状态变更为： 1-售后成功
        orderAfterSaleFinish(orderAfterSale.getId());
    }


    public List<OrderAfterSale> getAfterSaleByOrderIdAndAfterSaleStatus(Long orderId, String afterSaleStatus){
        return this.list(new LambdaQueryWrapper<OrderAfterSale>().eq(OrderAfterSale::getOrderId , orderId).eq(OrderAfterSale::getAfterSaleStatus , afterSaleStatus).eq(OrderAfterSale::getFlag , BasicConstant.STATE_0));
    }

    @Override
    public List<OrderAfterSale> listByOrderNumbers(List<String> orderNumbers, BusiUser busiUser) {
        List<OrderInfo> list = orderInfoService.list(new QueryWrapper<OrderInfo>().in(OrderInfo.NUMBER, orderNumbers).eq(OrderInfo.BUSINESS_ID, busiUser.getBusinessId()));
        List<OrderAfterSale> orderAfterSaleList = this.list(new QueryWrapper<OrderAfterSale>().in(OrderAfterSale.ORDER_ID, list.stream().map(OrderInfo::getId).collect(Collectors.toList())));

        return orderAfterSaleList.stream().filter(orderAfterSale -> !DictConstant.AfterSaleKey.isFinish(orderAfterSale.getAfterSaleKey())).collect(Collectors.toList());
    }
}
