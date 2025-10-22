package com.weikbest.pro.saas.applet.aftersale.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.weikbest.pro.saas.applet.aftersale.module.dto.AppOrderAfterLogisticsInfoDTO;
import com.weikbest.pro.saas.applet.aftersale.module.dto.AppOrderAfterSaleDTO;
import com.weikbest.pro.saas.applet.aftersale.module.mapstruct.AppOrderAfterSaleConsultRecordMapStruct;
import com.weikbest.pro.saas.applet.aftersale.module.mapstruct.AppOrderAfterSaleMapStruct;
import com.weikbest.pro.saas.applet.aftersale.module.qo.AppOrderAfterSaleQO;
import com.weikbest.pro.saas.applet.aftersale.module.vo.AppOrderAfterSaleDetailVO;
import com.weikbest.pro.saas.applet.aftersale.service.AppOrderAfterSaleService;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.merchat.aftersale.delaytaskprocess.OrderAfterSaleBusinessConfirmCustomerDeliveryTimeoutDelayTaskProcess;
import com.weikbest.pro.saas.merchat.aftersale.delaytaskprocess.OrderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess;
import com.weikbest.pro.saas.merchat.aftersale.delaytaskprocess.OrderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess;
import com.weikbest.pro.saas.merchat.aftersale.delaytaskprocess.OrderAfterSaleCustomerExecuteTimeoutDelayTaskProcess;
import com.weikbest.pro.saas.merchat.aftersale.entity.*;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.AppOrderAfterSaleApplyDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.AppOrderAfterSaleListDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleConsultRecordMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleImgHisMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.module.mapstruct.OrderAfterSaleLogisticsImgHisMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.service.*;
import com.weikbest.pro.saas.merchat.aftersale.util.OrderAfterSaleUtil;
import com.weikbest.pro.saas.merchat.order.delaytaskprocess.OrderSettlementByDepositDayDelayTaskProcess;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.entity.OrderPayRecord;
import com.weikbest.pro.saas.merchat.order.entity.OrderProdInfo;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderPayRecordService;
import com.weikbest.pro.saas.merchat.order.service.OrderProdInfoService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import com.weikbest.pro.saas.sys.param.service.DealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppOrderAfterSaleServiceImpl implements AppOrderAfterSaleService {

    @Autowired
    private OrderAfterSaleService orderAfterSaleService;

    @Autowired
    private OrderAfterSaleConsultRecordService orderAfterSaleConsultRecordService;

    @Autowired
    private OrderAfterSaleLogisticsImgService orderAfterSaleLogisticsImgService;

    @Autowired
    private OrderAfterSaleImgService orderAfterSaleImgService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderProdInfoService orderProdInfoService;

    @Resource
    private OrderAfterSaleImgHisService orderAfterSaleImgHisService;

    @Resource
    private OrderAfterSaleLogisticsImgHisService orderAfterSaleLogisticsImgHisService;

    @Resource
    private OrderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess;

    @Resource
    private OrderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess orderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess;

    @Resource
    private OrderAfterSaleCustomerExecuteTimeoutDelayTaskProcess orderAfterSaleCustomerExecuteTimeoutDelayTaskProcess;

    @Resource
    private OrderAfterSaleBusinessConfirmCustomerDeliveryTimeoutDelayTaskProcess orderAfterSaleBusinessConfirmCustomerDeliveryTimeoutDelayTaskProcess;

    @Resource
    private CurrentUserService currentUserService;

    @Resource
    private RedisLock redisLock;

    @Resource
    private DealService dealService;

    @Resource
    private OrderSettlementByDepositDayDelayTaskProcess orderSettlementByDepositDayDelayTaskProcess;

    @Resource
    private OrderPayRecordService orderPayRecordService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long apply(AppOrderAfterSaleDTO appOrderAfterSaleDTO) {

        // 根据订单号获取订单信息
        OrderInfo orderInfo = orderInfoService.findById(appOrderAfterSaleDTO.getOrderId());
        if (StrUtil.equals(orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_1.getCode())) {
            throw new WeikbestException(String.format("订单：%s,状态为待付款，无法发起售后单！", orderInfo.getNumber()));
        }
        if (appOrderAfterSaleDTO.getApplyAmount().compareTo(orderInfo.getPayAmount()) > WeikbestConstant.ZERO_INT) {
            throw new WeikbestException(String.format("订单：%s,售后申请金额不能大于支付金额！", orderInfo.getNumber()));
        }
        if (StrUtil.equals(orderInfo.getOrderAfterFlag(), DictConstant.Whether.yes.getCode())) {
            // 订单在售后中了，判断这个售后订单是否搞完了
            Long orderAfterSaleId = orderInfo.getOrderAfterSaleId();
            OrderAfterSale findOrderAfterSale = orderAfterSaleService.findById(orderAfterSaleId);
            if (!DictConstant.AfterSaleStatus.isFinish(findOrderAfterSale.getAfterSaleStatus())) {
                throw new WeikbestException(String.format("订单：%s,存在未完成的售后单，无法发起新的售后单！", orderInfo.getNumber()));
            }
        }
        // 已经发生了退款的订单不可再次申请售后
        OrderPayRecord orderPayRecord = orderPayRecordService.findByOrderId(orderInfo.getId());
        if (StrUtil.equals(orderPayRecord.getRefundStatus(), DictConstant.RefundStatus.refundStatus_1.getCode()) ||
                StrUtil.equals(orderPayRecord.getRefundStatus(), DictConstant.RefundStatus.refundStatus_2.getCode())) {
            throw new WeikbestException(String.format("订单：%s,已退款成功，不可在发起新的售后单！", orderInfo.getNumber()));
        }

        // 判断是否符合极速退款要求，符合则直接退款
        String applyType = appOrderAfterSaleDTO.getApplyType();
        BigDecimal payAmount = orderInfo.getPayAmount();
        Date orderTime = orderInfo.getOrderTime();
        String orderStatus = orderInfo.getOrderStatus();
        boolean fastRefund = OrderAfterSaleUtil.isFastRefund(dealService.findDeal(), payAmount, applyType, orderTime, orderStatus);

        // 保存售后单
        OrderAfterSale orderAfterSale = insertOrderAfterSale(appOrderAfterSaleDTO);
        Long id = orderAfterSale.getId();
        String afterSaleStatus = orderAfterSale.getAfterSaleStatus();
        if (fastRefund) {
            // 极速退款
            orderAfterSaleService.fastRefundById(id);
        } else {
            // 非极速退款单 将售后申请单加入延时队列中， 2天后过期
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
    public OrderAfterSale insertOrderAfterSale(AppOrderAfterSaleDTO appOrderAfterSaleDTO) {
        // 后台新建售后申请
        OrderAfterSale orderAfterSale = AppOrderAfterSaleMapStruct.INSTANCE.converToEntity(appOrderAfterSaleDTO);
        orderAfterSale.setApplySourceType(DictConstant.ApplySourceType.customer.getCode());
        // 设置售后ID
        Long id = GenerateIDUtil.nextId();
        orderAfterSale.setId(id);

        // 根据订单号获取订单信息
        OrderInfo orderInfo = orderInfoService.findById(appOrderAfterSaleDTO.getOrderId());
        Long orderId = orderInfo.getId();
        // 订单是否有当前售后记录
        Long orderAfterSaleId = orderInfo.getOrderAfterSaleId();
        if (WeikbestObjectUtil.isNotEmpty(orderAfterSaleId)) {
            OrderAfterSale currentOrderAfterSale = orderAfterSaleService.findById(orderAfterSaleId);
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
        String number = OrderUtil.getOrderAfterNumber(orderInfo.getNumber());
        orderAfterSale.setNumber(number);
        orderAfterSale.setOrderId(orderId);
        //orderAfterSale.setAppletId(orderInfo.getAppletId());
        orderAfterSale.setCustomerId(orderInfo.getCustomerId());
        orderAfterSale.setApplyTime(DateUtil.date());
        // 设置售后初始状态(根据售后类型)
        orderAfterSale.setAfterSaleStatus(DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode());
        orderAfterSale.setAfterSaleKey(DictConstant.AfterSaleApplyType.getAfterSaleKey(appOrderAfterSaleDTO.getApplyType()).getCode());
        orderAfterSale.setSendType(DictConstant.Whether.no.getCode());
        orderAfterSaleService.save(orderAfterSale);

        // 新增售后协商记录
        // 新建售后协商历史记录表
        OrderAfterSaleConsultRecord orderAfterSaleConsultRecord = OrderAfterSaleConsultRecordMapStruct.INSTANCE.converToEntity(orderAfterSale);
        // 设置售后协商历史ID
        Long historyId = GenerateIDUtil.nextId();
        orderAfterSaleConsultRecord.setHistoryId(historyId);
        // 设置变更人相关字段
        orderAfterSaleConsultRecord.setChangeType(DictConstant.AfterSaleChangeType.customer_apply.getCode());
        orderAfterSaleConsultRecord.setChangerUserType(currentUserService.getAppTokenUser().getRelateType());
        orderAfterSaleConsultRecord.setChangerUserId(currentUserService.getAppTokenUser().getId());
        orderAfterSaleConsultRecord.setChangeTime(DateUtil.date());
        orderAfterSaleConsultRecordService.save(orderAfterSaleConsultRecord);


        List<String> courierImgPathList = appOrderAfterSaleDTO.getImgpaths();
        if (CollectionUtil.isNotEmpty(courierImgPathList)) {
            // 新建售后凭证
            List<OrderAfterSaleImg> orderAfterSaleImgList = courierImgPathList.stream().map(courierImgPath -> {
                OrderAfterSaleImg orderAfterSaleImg = new OrderAfterSaleImg();
                orderAfterSaleImg.setCourierImgPath(courierImgPath);
                orderAfterSaleImg.setId(id);
                return orderAfterSaleImg;
            }).collect(Collectors.toList());
            orderAfterSaleImgService.saveBatch(orderAfterSaleImgList);

            // 新建售后凭证历史
            List<OrderAfterSaleImgHis> orderAfterSaleImgHisList = orderAfterSaleImgList.stream().map(orderAfterSaleImg -> {
                OrderAfterSaleImgHis orderAfterSaleImgHis = OrderAfterSaleImgHisMapStruct.INSTANCE.converToEntity(orderAfterSaleImg);
                orderAfterSaleImgHis.setHistoryId(historyId);
                return orderAfterSaleImgHis;
            }).collect(Collectors.toList());
            orderAfterSaleImgHisService.saveBatch(orderAfterSaleImgHisList);
        }

        // 修改订单状态
        orderInfoService.updateOrderInfo(orderInfo);

        return orderAfterSale;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long reapply(AppOrderAfterSaleDTO appOrderAfterSaleDTO) {
        Long id = appOrderAfterSaleDTO.getId();
        OrderAfterSale orderAfterSale = orderAfterSaleService.findById(id);
        String sourceAfterSaleStatus = orderAfterSale.getAfterSaleStatus();
        if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_10.getCode()) &&
                !StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode())) {
            throw new WeikbestException("售后单前置状态不是【客户申请售后，待商家处理】或【商家不同意申请，待客户处理】，请核实售后单信息！");
        }

        // 根据订单号获取订单信息
        OrderInfo orderInfo = orderInfoService.findById(appOrderAfterSaleDTO.getOrderId());
        if (appOrderAfterSaleDTO.getApplyAmount().compareTo(orderInfo.getPayAmount()) > WeikbestConstant.ZERO_INT) {
            throw new WeikbestException(String.format("订单：%s,售后申请金额不能大于支付金额！", orderInfo.getNumber()));
        }


        //更新订单售后表
        orderAfterSale.setTakeDeliveryType(appOrderAfterSaleDTO.getTakeDeliveryType());
        orderAfterSale.setApplyType(appOrderAfterSaleDTO.getApplyType());
        orderAfterSale.setApplyReason(appOrderAfterSaleDTO.getApplyReason());
        orderAfterSale.setGoodsNum(appOrderAfterSaleDTO.getGoodsNum());
        orderAfterSale.setApplyAmount(appOrderAfterSaleDTO.getApplyAmount());
        orderAfterSale.setCustomerPhone(appOrderAfterSaleDTO.getCustomerPhone());
        orderAfterSale.setQuestionDetail(appOrderAfterSaleDTO.getQuestionDetail());
        orderAfterSale.setApplyTime(DateUtil.date());
        orderAfterSale.setAfterSaleKey(DictConstant.AfterSaleApplyType.getAfterSaleKey(appOrderAfterSaleDTO.getApplyType()).getCode());
        orderAfterSale.setAfterSaleStatus(DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode());

        orderAfterSaleService.saveOrUpdate(orderAfterSale);


        //新增协商记录表
        OrderAfterSaleConsultRecord orderAfterSaleConsultRecord = AppOrderAfterSaleConsultRecordMapStruct.INSTANCE.converToEntity(orderAfterSale);
        Long historyId = GenerateIDUtil.nextId();
        orderAfterSaleConsultRecord.setHistoryId(historyId);

        orderAfterSaleConsultRecord.setChangeType(DictConstant.AfterSaleChangeType.customer_apply.getCode());
        orderAfterSaleConsultRecord.setChangerUserType(currentUserService.getAppTokenUser().getRelateType());
        orderAfterSaleConsultRecord.setChangerUserId(currentUserService.getAppTokenUser().getId());
        orderAfterSaleConsultRecord.setChangeTime(DateUtil.date());

        orderAfterSaleConsultRecordService.save(orderAfterSaleConsultRecord);

        //删除该订单售后单原图片信息
        QueryWrapper<OrderAfterSaleImg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(OrderAfterSaleImg.ID, id);
        orderAfterSaleImgService.remove(queryWrapper);

        //保存图片信息
        List<String> courierImgPathList = appOrderAfterSaleDTO.getImgpaths();
        if (CollectionUtil.isNotEmpty(courierImgPathList)) {
            // 新建售后凭证
            List<OrderAfterSaleImg> orderAfterSaleImgList = courierImgPathList.stream().map(courierImgPath -> {
                OrderAfterSaleImg orderAfterSaleImg = new OrderAfterSaleImg();
                orderAfterSaleImg.setCourierImgPath(courierImgPath);
                orderAfterSaleImg.setId(id);
                return orderAfterSaleImg;
            }).collect(Collectors.toList());
            orderAfterSaleImgService.saveBatch(orderAfterSaleImgList);

            // 新建售后凭证历史
            List<OrderAfterSaleImgHis> orderAfterSaleImgHisList = orderAfterSaleImgList.stream().map(orderAfterSaleImg -> {
                OrderAfterSaleImgHis orderAfterSaleImgHis = OrderAfterSaleImgHisMapStruct.INSTANCE.converToEntity(orderAfterSaleImg);
                orderAfterSaleImgHis.setHistoryId(historyId);
                return orderAfterSaleImgHis;
            }).collect(Collectors.toList());
            orderAfterSaleImgHisService.saveBatch(orderAfterSaleImgHisList);
        }

        if (StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_10.getCode())) {
            // 删除用户未处理延时队列
            orderAfterSaleCustomerExecuteTimeoutDelayTaskProcess.removeTask(id, sourceAfterSaleStatus);
        }
        // 此时用户申请后，商家还未处理，这里不需要删除单家处理超时的延时队列
        else if (StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode())) {
            // 删除商家处理超时延时队列
//            orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.removeTask(id, sourceAfterSaleStatus);
        }


        // 判断是否符合极速退款要求，符合则直接退款
        String applyType = appOrderAfterSaleDTO.getApplyType();
        BigDecimal payAmount = orderInfo.getPayAmount();
        Date orderTime = orderInfo.getOrderTime();
        String orderStatus = orderInfo.getOrderStatus();
        boolean fastRefund = OrderAfterSaleUtil.isFastRefund(dealService.findDeal(), payAmount, applyType, orderTime, orderStatus);
        log.info("reapply orderAfterSaleId:{}, fastRefund:{}", id, fastRefund);
        String afterSaleStatus = orderAfterSale.getAfterSaleStatus();
        if (fastRefund) {
            // 极速退款
            orderAfterSaleService.fastRefundById(id);
            // 此时用户申请后，商家还未处理，这里需要删除单家处理超时的延时队列，因为已经极速退款了
            if (StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode())) {
                // 删除商家处理超时延时队列
                log.info("reapply orderAfterSaleId:{}, sourceAfterSaleStatus:{} removeTask", id,sourceAfterSaleStatus);
                orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.removeTask(id, sourceAfterSaleStatus);
            }
        } else {
            log.info("reapply orderAfterSaleId:{}, afterSaleStatus:{} putTask", id, afterSaleStatus);
            // 非极速退款单 将售后申请单加入延时队列中， 2天后过期
            orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.putTask(id, afterSaleStatus);
        }

        // 如果该笔订单是分账订单，存在售后情况下，需要先删除发货7天押金回退清算延时任务
        if (StrUtil.equals(orderInfo.getIsReceiveOrder(), DictConstant.Whether.yes.getCode())) {
            //清除押金回退清算任务
            orderSettlementByDepositDayDelayTaskProcess.removeTask(orderInfo.getId());
        }

        return appOrderAfterSaleDTO.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean revoke(Long id) {
        OrderAfterSale orderAfterSale = orderAfterSaleService.getById(id);
        String sourceAfterSaleStatus = orderAfterSale.getAfterSaleStatus();

        if (!StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_10.getCode()) &&
                !StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode()) &&
                !StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_6.getCode()) &&
                !StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_201.getCode()) &&
                !StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_206.getCode()) &&
                !StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_301.getCode()) &&
                !StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_306.getCode())) {
            throw new WeikbestException("售后单前置状态不是【客户申请售后，待商家处理】或【商家不同意申请，待客户处理】，请核实售后单信息！");
        }

        // 设置客户撤销售后
        String afterSaleStatus = DictConstant.AfterSaleStatus.afterSaleStatus_4.getCode();

        boolean update;
        // 更新售后单单变更记录
        String key = RedisKey.generalKey(RedisKey.Lock.LOCK_ORDER_STATUS_KEY, orderAfterSale.getId());
        redisLock.lock(key);
        try {
            // 售后单状态
            orderAfterSale.setAfterSaleStatus(afterSaleStatus);
            orderAfterSale.setAfterSaleKey(DictConstant.AfterSaleStatus.getAfterSaleKey(orderAfterSale.getApplyType(), afterSaleStatus).getCode());
            update = orderAfterSaleService.updateById(orderAfterSale);

            // 新增售后协商记录
            orderAfterSaleConsultRecordService.appinsertByOrderAfterSale(orderAfterSale, DictConstant.AfterSaleStatus.getAfterSaleChangeType(afterSaleStatus).getCode());

            //如果是分账订单，则设置延时任务
            if (DictConstant.AfterSaleKey.isFinish(orderAfterSale.getAfterSaleKey())) {
                OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
                if (StrUtil.equals(orderInfo.getIsReceiveOrder(), DictConstant.Whether.yes.getCode())) {
                    //添加分账押金回退清算延时任务，发货后7天
                    orderSettlementByDepositDayDelayTaskProcess.putTask(orderInfo.getId());
                }
            }

            // 更新订单信息
            OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
            orderInfo.setOrderAfterFlag(DictConstant.Whether.no.getCode());
            orderInfoService.updateOrderInfo(orderInfo);
        } finally {
            redisLock.unlock(key);
        }

        // 删除用户未处理延时队列
        orderAfterSaleCustomerExecuteTimeoutDelayTaskProcess.removeTask(id, sourceAfterSaleStatus);
        if (StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode())) {
            // 删除商家处理超时延时队列
            orderAfterSaleBusinessRejectApplyExecuteTimeoutDelayTaskProcess.removeTask(id, sourceAfterSaleStatus);
        }

        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addlogistics(AppOrderAfterLogisticsInfoDTO appOrderAfterLogisticsInfoDTO) {
        Long id = appOrderAfterLogisticsInfoDTO.getId();
        OrderAfterSale orderAfterSale = orderAfterSaleService.findById(id);
        String sourceAfterSaleStatus = orderAfterSale.getAfterSaleStatus();
        if (StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
            throw new WeikbestException("申请类型是【仅退款】不存在客户发货处理，请核实售后单信息！");
        }
        if (!StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_201.getCode()) &&
                !StrUtil.equals(sourceAfterSaleStatus, DictConstant.AfterSaleStatus.afterSaleStatus_301.getCode())) {
            throw new WeikbestException("售后单前置状态不是【商家同意，待客户寄回商品】，请核实售后单信息！");
        }

        // 设置客户发货信息
        orderAfterSale.setBackLogisticsCompany(appOrderAfterLogisticsInfoDTO.getBackLogisticsCompany());
        orderAfterSale.setBackCourierNumber(appOrderAfterLogisticsInfoDTO.getBackCourierNumber());
        orderAfterSale.setBackCourierPhone(appOrderAfterLogisticsInfoDTO.getBackCourierPhone());
        orderAfterSale.setBackLogisticsTime(DateUtil.date());
        // 状态变更为： 6-客户已寄回商品，待商家收货
        orderAfterSale.setAfterSaleStatus(DictConstant.AfterSaleStatus.afterSaleStatus_6.getCode());
        orderAfterSale.setAfterSaleKey(DictConstant.AfterSaleStatus.getAfterSaleKey(orderAfterSale.getApplyType(), DictConstant.AfterSaleStatus.afterSaleStatus_6.getCode()).getCode());
        orderAfterSale.setQuestionDetail(appOrderAfterLogisticsInfoDTO.getBackDetail());
        boolean update = orderAfterSaleService.updateById(orderAfterSale);

        // 新增售后协商记录
        // 新建售后协商历史记录表
        OrderAfterSaleConsultRecord orderAfterSaleConsultRecord = OrderAfterSaleConsultRecordMapStruct.INSTANCE.converToEntity(orderAfterSale);
        // 设置售后协商历史ID
        Long historyId = GenerateIDUtil.nextId();
        orderAfterSaleConsultRecord.setHistoryId(historyId);
        // 设置变更人相关字段
        orderAfterSaleConsultRecord.setChangeType(DictConstant.AfterSaleChangeType.change_customer_logistics.getCode());
        orderAfterSaleConsultRecord.setChangerUserType(currentUserService.getAppTokenUser().getRelateType());
        orderAfterSaleConsultRecord.setChangerUserId(currentUserService.getAppTokenUser().getId());
        orderAfterSaleConsultRecord.setChangeTime(DateUtil.date());
        orderAfterSaleConsultRecordService.save(orderAfterSaleConsultRecord);

        List<String> courierImgPathList = appOrderAfterLogisticsInfoDTO.getBackCourierImgPaths();
        if (CollectionUtil.isNotEmpty(courierImgPathList)) {
            // 新建售后客户寄回商品凭证图片
            List<OrderAfterSaleLogisticsImg> orderAfterSaleLogisticsImgList = courierImgPathList.stream().map(courierImgPath -> {
                OrderAfterSaleLogisticsImg orderAfterSaleLogisticsImg = new OrderAfterSaleLogisticsImg();
                orderAfterSaleLogisticsImg.setCourierImgType(DictConstant.CourierImgType.courierImgType_2.getCode());
                orderAfterSaleLogisticsImg.setCourierImgPath(courierImgPath);
                orderAfterSaleLogisticsImg.setId(id);
                return orderAfterSaleLogisticsImg;
            }).collect(Collectors.toList());
            orderAfterSaleLogisticsImgService.saveBatch(orderAfterSaleLogisticsImgList);

            // 新建售后客户寄回商品凭证图片历史
            List<OrderAfterSaleLogisticsImgHis> orderAfterSaleLogisticsImgHisList = orderAfterSaleLogisticsImgList.stream().map(orderAfterSaleLogisticsImg -> {
                OrderAfterSaleLogisticsImgHis orderAfterSaleLogisticsImgHis = OrderAfterSaleLogisticsImgHisMapStruct.INSTANCE.converToEntity(orderAfterSaleLogisticsImg);
                orderAfterSaleLogisticsImgHis.setHistoryId(historyId);
                return orderAfterSaleLogisticsImgHis;
            }).collect(Collectors.toList());
            orderAfterSaleLogisticsImgHisService.saveBatch(orderAfterSaleLogisticsImgHisList);
        }

        // 删除客户寄回商品的延时队列
        orderAfterSaleCustomerDeliveryTimeoutDelayTaskProcess.removeTask(id, sourceAfterSaleStatus);
        // 添加商户确认客户寄回商品延时队列
        orderAfterSaleBusinessConfirmCustomerDeliveryTimeoutDelayTaskProcess.putTask(id, orderAfterSale.getAfterSaleStatus());

        return update;
    }

    @Override
    public AppOrderAfterSaleDetailVO detail(Long orderAfterId) {

        OrderAfterSale orderAfterSale = orderAfterSaleService.getById(orderAfterId);
        AppOrderAfterSaleDetailVO detailVO = AppOrderAfterSaleMapStruct.INSTANCE.converToVO(orderAfterSale);
        OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
        detailVO.setPayAmount(orderInfo.getPayAmount());
        detailVO.setName(orderInfo.getName());

        OrderProdInfo orderProdInfo = orderProdInfoService.findById(orderAfterSale.getOrderId());
        detailVO.setBuyNumber(orderProdInfo.getBuyNumber());
        detailVO.setProdName(orderProdInfo.getProdName());
        detailVO.setProdImg(orderProdInfo.getProdImg());
        detailVO.setProdComboAttrValues(orderProdInfo.getProdComboAttrValues());

        return detailVO;

    }

    @Override
    public List<AppOrderAfterSaleApplyDTO> queryPageAppOrderAfterApply(AppOrderAfterSaleQO appOrderAfterSaleQO, PageReq pageReq) {
        // 构建查询参数
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.putAll(BeanUtils.beanToMap(appOrderAfterSaleQO));
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());

        return orderAfterSaleService.queryPageAppOrderAfterApply(paramMap);
    }

    @Override
    public List<AppOrderAfterSaleListDTO> queryPageAppOrderAfterList(AppOrderAfterSaleQO appOrderAfterSaleQO, PageReq pageReq) {
        // 构建查询参数
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.putAll(BeanUtils.beanToMap(appOrderAfterSaleQO));
        paramMap.put("start", PageUtil.getStart(pageReq.getPage(), pageReq.getLimit()));
        paramMap.put("limit", pageReq.getLimit());

        return orderAfterSaleService.queryPageAppOrderAfterList(paramMap);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean confirmReceipt(Long id) {
        OrderAfterSale orderAfterSale = orderAfterSaleService.getById(id);
        if (!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_304.getCode())) {
            throw new WeikbestException("售后单前置状态不是【商家已重新发货，待客户确认】，请核实售后单信息！");
        }

        OrderInfo orderInfo = orderInfoService.findById(orderAfterSale.getOrderId());
        orderInfo.setOrderAfterFlag(DictConstant.Whether.no.getCode());
        orderInfoService.appupdateOrderStatus(orderInfo, orderInfo.getOrderStatus(), DictConstant.OrderStatus.orderStatus_30.getCode(), "客户售后确认收货");

        orderAfterSale.setTakeDeliveryType("1");
        orderAfterSale.setAfterSaleKey(DictConstant.AfterSaleStatus.getAfterSaleKey(orderAfterSale.getApplyType(), DictConstant.AfterSaleStatus.afterSaleStatus_1.getCode()).getCode());
        orderAfterSale.setAfterSaleStatus(DictConstant.AfterSaleStatus.afterSaleStatus_1.getCode());
        Boolean bool = orderAfterSaleService.saveOrUpdate(orderAfterSale);

        // 新增售后协商记录
        orderAfterSaleConsultRecordService.appinsertByOrderAfterSale(orderAfterSale, DictConstant.AfterSaleStatus.getAfterSaleChangeType(DictConstant.AfterSaleStatus.afterSaleStatus_1.getCode()).getCode());

        //如果是分账订单，则设置延时任务
        if (DictConstant.AfterSaleKey.isFinish(orderAfterSale.getAfterSaleKey())) {
            if (StrUtil.equals(orderInfo.getIsReceiveOrder(), DictConstant.Whether.yes.getCode())) {
                //添加分账押金回退清算延时任务，发货后7天
                orderSettlementByDepositDayDelayTaskProcess.putTask(orderInfo.getId());
            }
        }

        return bool;
    }

    @Override
    public Long whetherOrderAfter(Long id) {
        // 根据订单号获取订单信息
        OrderInfo orderInfo = orderInfoService.findById(id);

        if (StrUtil.equals(orderInfo.getOrderAfterFlag(), DictConstant.Whether.yes.getCode())) {
            // 订单在售后中了，判断这个售后订单是否搞完了
            Long orderAfterSaleId = orderInfo.getOrderAfterSaleId();
            if(StrUtil.isEmpty(orderAfterSaleId.toString()) || orderAfterSaleId == WeikbestConstant.ZERO_LONG){
                return 0L;
            }
            OrderAfterSale findOrderAfterSale = orderAfterSaleService.findById(orderAfterSaleId);
            if (DictConstant.AfterSaleStatus.isFinish(findOrderAfterSale.getAfterSaleStatus())) {
                return 0L;
            }
        }else{
            //如果订单售后中状态为否，或为空，则直接返回0L可重新申请售后
            return 0L;
        }
        return orderInfo.getOrderAfterSaleId();
    }

}
