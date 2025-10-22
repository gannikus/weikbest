package com.weikbest.pro.saas.merchat.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
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
import com.weikbest.pro.saas.common.third.aliyun.logistics.AliyunWuliuService;
import com.weikbest.pro.saas.common.third.wx.miniapp.subscribe.SubscribeUtil;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleService;
import com.weikbest.pro.saas.merchat.api.module.dto.OrderLogisticsDto;
import com.weikbest.pro.saas.merchat.api.module.vo.OrderDeliveryErrorVo;
import com.weikbest.pro.saas.merchat.busi.entity.BusiAddress;
import com.weikbest.pro.saas.merchat.busi.util.BusiAddressUtil;
import com.weikbest.pro.saas.merchat.order.delaytaskprocess.OrderDeliverCustomerTimeoutDelayTaskProcess;
import com.weikbest.pro.saas.merchat.order.delaytaskprocess.OrderDeliverTimeoutDelayTaskProcess;
import com.weikbest.pro.saas.merchat.order.delaytaskprocess.OrderFundReleaseHourDelayTaskProcess;
import com.weikbest.pro.saas.merchat.order.delaytaskprocess.OrderSettlementByDepositDayDelayTaskProcess;
import com.weikbest.pro.saas.merchat.order.entity.*;
import com.weikbest.pro.saas.merchat.order.mapper.OrderLogisticsMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderBatchDeliverRecordExcelDTO;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderLogisticsImgMapStruct;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderLogisticsMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderLogisticsQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderLogisticsVO;
import com.weikbest.pro.saas.merchat.order.service.*;
import com.weikbest.pro.saas.merchat.prod.service.ProdBusiAddrService;
import com.weikbest.pro.saas.sys.common.cache.Memory;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.constant.AppletSubscribeConfigConstant;
import com.weikbest.pro.saas.sys.common.constant.ConfigConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.common.constant.SmsTemplateConstant;
import com.weikbest.pro.saas.sys.param.service.AppletSubscribeConfigService;
import com.weikbest.pro.saas.sys.param.service.SmsTemplateService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单物流记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Service
public class OrderLogisticsServiceImpl extends ServiceImpl<OrderLogisticsMapper, OrderLogistics> implements OrderLogisticsService {

    /**
     * 超过72小时不允许更新订单
     */
    private static final int HOUR_72 = 72;

    @Resource
    private SmsTemplateService smsTemplateService;

    @Resource
    private OrderLogisticsService orderLogisticsService;

    @Resource
    private OrderRecAddrService orderRecAddrService;

    @Resource
    private OrderLogisticsImgService orderLogisticsImgService;

    @Resource
    private MemoryService memoryService;

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private ProdBusiAddrService prodBusiAddrService;

    @Resource
    private OrderProdInfoService orderProdInfoService;

    @Resource
    private OrderCustInfoService orderCustInfoService;

    @Resource
    private AppletSubscribeConfigService appletSubscribeConfigService;

    @Resource
    private OrderDeliverTimeoutDelayTaskProcess orderDeliverTimeoutDelayTaskProcess;

    @Resource
    private OrderDeliverCustomerTimeoutDelayTaskProcess orderDeliverCustomerTimeoutDelayTaskProcess;

    @Resource
    private OrderFundReleaseHourDelayTaskProcess orderFundReleaseHourDelayTaskProcess;

    @Resource
    private OrderSettlementByDepositDayDelayTaskProcess orderSettlementByDepositDayDelayTaskProcess;

    @Autowired
    private OrderAfterSaleService orderAfterSaleService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(OrderLogisticsDTO orderLogisticsDTO) {
        OrderLogistics orderLogistics = OrderLogisticsMapStruct.INSTANCE.converToEntity(orderLogisticsDTO);
        return this.save(orderLogistics);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, OrderLogisticsDTO orderLogisticsDTO) {
        OrderLogistics orderLogistics = this.findById(id);
        // 发货时间超过72小时的不允许更新，已经更新过的不允许更新
        Date logisticsTime = orderLogistics.getLogisticsTime();
        if (DateUtil.between(logisticsTime, DateUtil.date(), DateUnit.HOUR) >= HOUR_72) {
            throw new WeikbestException("发货超过72小时订单不可修改");
        }
//        if (StrUtil.equals(orderLogistics.getIsUpdate(), DictConstant.Whether.yes.getCode())) {
//            throw new WeikbestException("已修改的订单物流信息不可再次修改");
//        }

        OrderLogisticsMapStruct.INSTANCE.copyProperties(orderLogisticsDTO, orderLogistics);
        orderLogistics.setId(id);
        orderLogistics.setIsUpdate(StringUtils.isBlank(orderLogistics.getIsUpdate()) ? "0" : String.valueOf(Integer.parseInt(orderLogistics.getIsUpdate()) + BasicConstant.INT_1));
        // 清空物流内容信息
        orderLogistics.setContent("");
        boolean update = this.updateById(orderLogistics);

        // 保存物流图片信息
        orderLogisticsImgService.saveBatchWithOrderLogistics(id, orderLogisticsDTO);

        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean orderDeliverAndUpdateOrderStatus(OrderLogisticsDTO orderLogisticsDTO) {
        Long orderId = orderLogisticsDTO.getOrderId();
        OrderInfo orderInfo = orderInfoService.findById(orderId);
        //判断当前订单是否存在售后,有售后不让发货
        List<OrderAfterSale> afterSales = orderAfterSaleService.getAfterSaleByOrderIdAndAfterSaleStatus(orderInfo.getId() , BasicConstant.STATE_2);
        if (CollectionUtil.isNotEmpty(afterSales)){
            throw new WeikbestException("订单还有售后未处理请先处理售后在上传！");
        }

        DateTime logisticsTime = DateUtil.date();
        OrderLogistics orderLogistics = OrderLogisticsMapStruct.INSTANCE.converToEntity(orderLogisticsDTO);
        orderLogistics.setLogisticsTime(logisticsTime);

        // 查询发货人信息
        BusiAddress busiAddress = prodBusiAddrService.findOneBusiAddressByProdIdOrBusinessId(orderInfo.getProdId(), orderInfo.getBusinessId());
        if (ObjectUtil.isNotNull(busiAddress)) {
            // 复制发货人数据
            orderLogistics.setConsigner(busiAddress.getName())
                    .setAddrProvince(busiAddress.getBusiProvince())
                    .setAddrCity(busiAddress.getBusiCity())
                    .setAddrDistrict(busiAddress.getBusiDistrict())
                    .setAddr(busiAddress.getAddr());
            orderLogistics.setContact(BusiAddressUtil.getContact(busiAddress));
        }

        //根据订单Id删除物流信息然后新增
        this.remove(new LambdaQueryWrapper<OrderLogistics>().eq(OrderLogistics::getOrderId , orderId));
        boolean save = this.save(orderLogistics);

        Long orderLogisticsId = orderLogistics.getId();
        // 保存物流图片信息
        orderLogisticsImgService.saveBatchWithOrderLogistics(orderLogisticsId, orderLogisticsDTO);

        // 订单关联物流记录,修改状态为：已发货
        orderInfo.setOrderLogisticsId(orderLogisticsId);
        orderInfoService.updateOrderStatus(orderInfo, DictConstant.OrderStatus.orderStatus_20.getCode(), "【客户订单】订单更新物流信息，后台自动变更状态，变更状态为：已发货");

        OrderCustInfo orderCustInfo = orderCustInfoService.findById(orderId);
        // 给客户发送已发货短信
        smsTemplateService.sendOneSmsJumpAppletAndSaveRecord(orderInfo.getAppId(), SmsTemplateConstant.ORDERDELIVERSUCCESS, orderCustInfo.getCustomerPhone(), orderInfo.getNumber());
        // 给客户推送已发货消息
        if (StrUtil.equals(orderInfo.getDeliverNotify(), DictConstant.Whether.yes.getCode())) {
            OrderProdInfo orderProdInfo = orderProdInfoService.findById(orderId);
            appletSubscribeConfigService.sendOneSubscribeAndSaveRecord(orderInfo.getAppId(), AppletSubscribeConfigConstant.ORDER_LOGISTICS_DELIVERY, orderCustInfo.getTpOpenid(),
                    orderInfo.getNumber(), orderInfo.getNumber(), SubscribeUtil.subThing(orderProdInfo.getProdName()), DateUtil.formatDateTime(logisticsTime),
                    orderLogistics.getCourierNumber(), Memory.getLogisticsCompanyName(orderLogistics.getLogisticsCompany()));
        }

        // 删除未发货的延时任务
        orderDeliverTimeoutDelayTaskProcess.removeTask(orderId);
        // 添加客户未确认收货的延时任务
        orderDeliverCustomerTimeoutDelayTaskProcess.putTask(orderId);

        //如果是分账订单，则设置延时任务
        if(StrUtil.equals(orderInfo.getIsReceiveOrder(), DictConstant.Whether.yes.getCode())) {
            //添加解冻资金延时任务
            orderFundReleaseHourDelayTaskProcess.putTask(orderId);
            //添加分账押金回退清算延时任务，发货后7天
            orderSettlementByDepositDayDelayTaskProcess.putTask(orderId);
        }

        return save;
    }

    @Override
    public void batchOrderDeliverAndUpdateOrderStatus(List<OrderBatchDeliverRecordExcelDTO> orderBatchDeliverRecordExcelDTOList) {
        // 批量发货
        for (OrderBatchDeliverRecordExcelDTO orderBatchDeliverRecordExcelDTO : orderBatchDeliverRecordExcelDTOList) {
            try {
                OrderInfo orderInfo = orderInfoService.findByOrderNumber(orderBatchDeliverRecordExcelDTO.getOrderNumber());

                OrderLogisticsDTO orderLogisticsDTO = new OrderLogisticsDTO()
                        .setOrderId(orderInfo.getId())
                        .setLogisticsCompany(Memory.getLogisticsCompanyNumberByName(orderBatchDeliverRecordExcelDTO.getLogisticsCompanyName()))
                        .setCourierNumber(orderBatchDeliverRecordExcelDTO.getCourierNumber());
                orderLogisticsService.orderDeliverAndUpdateOrderStatus(orderLogisticsDTO);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public OrderLogistics findById(Long id) {
        OrderLogistics orderLogistics = this.getById(id);
        if (ObjectUtil.isNull(orderLogistics)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderLogistics;
    }

    @Override
    public OrderLogisticsVO findVOAndResetContentByOrderId(Long orderId) {
        OrderLogistics orderLogistics = this.getOne(new QueryWrapper<OrderLogistics>().eq(OrderLogistics.ORDER_ID, orderId));
        if (ObjectUtil.isNull(orderLogistics)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }

        return findVOAndResetContentById(orderLogistics.getId());
    }

    @Override
    public OrderLogistics getByOrderId(Long orderId) {
        return this.getOne(new QueryWrapper<OrderLogistics>().eq(OrderLogistics.ORDER_ID, orderId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OrderDeliveryErrorVo> delivery(List<OrderLogisticsDto> orderLogisticsDtoList, Long businessId) {

        ArrayList<OrderDeliveryErrorVo> failedNumList = new ArrayList<>();

        for (OrderLogisticsDto orderLogisticsDto : orderLogisticsDtoList) {
            OrderInfo orderInfo = orderInfoService.findByOrderNumber(orderLogisticsDto.getOrderNumber());

            if (!businessId.equals(orderInfo.getBusinessId())) {
                OrderDeliveryErrorVo orderDeliveryErrorVo = new OrderDeliveryErrorVo().setNumber(orderLogisticsDto.getOrderNumber()).setReason("订单不属于该商户！");
                failedNumList.add(orderDeliveryErrorVo);
                continue;
            }
            //判断当前订单是否存在售后,有售后不让发货
            List<OrderAfterSale> afterSales = orderAfterSaleService.getAfterSaleByOrderIdAndAfterSaleStatus(orderInfo.getId(), BasicConstant.STATE_2);
            if (CollectionUtil.isNotEmpty(afterSales)) {
                OrderDeliveryErrorVo orderDeliveryErrorVo = new OrderDeliveryErrorVo().setNumber(orderLogisticsDto.getOrderNumber()).setReason("订单还有售后未处理请先处理售后在上传！");
                failedNumList.add(orderDeliveryErrorVo);
                continue;
            }

            DateTime logisticsTime = DateUtil.date();
            OrderLogistics orderLogistics = OrderLogisticsMapStruct.INSTANCE.converToEntity(orderLogisticsDto);
            orderLogistics.setLogisticsTime(logisticsTime);
            orderLogistics.setOrderId(orderInfo.getId());

            // 查询发货人信息
            BusiAddress busiAddress = prodBusiAddrService.findOneBusiAddressByProdIdOrBusinessId(orderInfo.getProdId(), orderInfo.getBusinessId());
            if (ObjectUtil.isNotNull(busiAddress)) {
                // 复制发货人数据
                orderLogistics.setConsigner(busiAddress.getName())
                        .setAddrProvince(busiAddress.getBusiProvince())
                        .setAddrCity(busiAddress.getBusiCity())
                        .setAddrDistrict(busiAddress.getBusiDistrict())
                        .setAddr(busiAddress.getAddr());
                orderLogistics.setContact(BusiAddressUtil.getContact(busiAddress));
            }

            //根据订单Id删除物流信息然后新增
            this.remove(new LambdaQueryWrapper<OrderLogistics>().eq(OrderLogistics::getOrderId, orderInfo.getId()));
            boolean save = this.save(orderLogistics);

            Long orderLogisticsId = orderLogistics.getId();
            // 保存物流图片信息
//            orderLogisticsImgService.saveBatchWithOrderLogistics(orderLogisticsId, orderLogisticsDto);

            // 订单关联物流记录,修改状态为：已发货
            orderInfo.setOrderLogisticsId(orderLogisticsId);
            orderInfoService.updateOrderStatus(orderInfo, DictConstant.OrderStatus.orderStatus_20.getCode(), "【客户订单】订单更新物流信息，后台自动变更状态，变更状态为：已发货");

            OrderCustInfo orderCustInfo = orderCustInfoService.findById(orderInfo.getId());
            // 给客户发送已发货短信
            smsTemplateService.sendOneSmsJumpAppletAndSaveRecord(orderInfo.getAppId(), SmsTemplateConstant.ORDERDELIVERSUCCESS, orderCustInfo.getCustomerPhone(), orderInfo.getNumber());
            // 给客户推送已发货消息
            if (StrUtil.equals(orderInfo.getDeliverNotify(), DictConstant.Whether.yes.getCode())) {
                OrderProdInfo orderProdInfo = orderProdInfoService.findById(orderInfo.getId());
                appletSubscribeConfigService.sendOneSubscribeAndSaveRecord(orderInfo.getAppId(), AppletSubscribeConfigConstant.ORDER_LOGISTICS_DELIVERY, orderCustInfo.getTpOpenid(),
                        orderInfo.getNumber(), orderInfo.getNumber(), SubscribeUtil.subThing(orderProdInfo.getProdName()), DateUtil.formatDateTime(logisticsTime),
                        orderLogistics.getCourierNumber(), Memory.getLogisticsCompanyName(orderLogistics.getLogisticsCompany()));
            }

            // 删除未发货的延时任务
            orderDeliverTimeoutDelayTaskProcess.removeTask(orderInfo.getId());
            // 添加客户未确认收货的延时任务
            orderDeliverCustomerTimeoutDelayTaskProcess.putTask(orderInfo.getId());

            //如果是分账订单，则设置延时任务
            if (StrUtil.equals(orderInfo.getIsReceiveOrder(), DictConstant.Whether.yes.getCode())) {
                //添加解冻资金延时任务
                orderFundReleaseHourDelayTaskProcess.putTask(orderInfo.getId());
                //添加分账押金回退清算延时任务，发货后7天
                orderSettlementByDepositDayDelayTaskProcess.putTask(orderInfo.getId());
            }
        }

        return failedNumList;
    }

    @Override
    public OrderLogisticsVO getVOAndResetContentByOrderId(Long orderId) {
        OrderLogistics orderLogistics = this.getOne(new QueryWrapper<OrderLogistics>().eq(OrderLogistics.ORDER_ID, orderId));
        if (ObjectUtil.isNull(orderLogistics)) {
            return null;
        }
        return findVOAndResetContentById(orderLogistics.getId());
    }

    @Override
    public OrderLogisticsVO findVOAndResetContentById(Long id) {
        OrderLogistics orderLogistics = this.findById(id);
        OrderRecAddr orderRecAddr = orderRecAddrService.findById(orderLogistics.getOrderId());
        if (StrUtil.isBlank(orderLogistics.getContent())) {
            // 查询物流信息
            queryAliyunWuliu(orderLogistics, orderRecAddr.getPhone());
            this.updateById(orderLogistics);
        } else {
            int defaultHour = Integer.parseInt(memoryService.queryConfig(ConfigConstant.QUERY_LOGISTICS_INTERVAL_TIME));
            // 根据最后一次更新时间和当前查询时间判断是否超过了默认3个小时，如果超过了则重新发起接口查询，如无则返回表中的信息
            long hour = DateUtil.between(orderLogistics.getGmtModified(), new Date(), DateUnit.HOUR);
            if (hour >= defaultHour) {
                queryAliyunWuliu(orderLogistics, orderRecAddr.getPhone());
                this.updateById(orderLogistics);
            }
        }

        List<OrderLogisticsImg> orderLogisticsImgList = orderLogisticsImgService.list(new QueryWrapper<OrderLogisticsImg>().eq(OrderLogisticsImg.ID, id));
        OrderLogisticsVO orderLogisticsVO = OrderLogisticsMapStruct.INSTANCE.converToVO(orderLogistics);
        if (CollectionUtil.isNotEmpty(orderLogisticsImgList)) {
            orderLogisticsVO.setOrderLogisticsImgVOList(orderLogisticsImgList.stream().map(OrderLogisticsImgMapStruct.INSTANCE::converToVO).collect(Collectors.toList()));
        }
        return orderLogisticsVO;
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
    public IPage<OrderLogistics> queryPage(OrderLogisticsQO orderLogisticsQO, PageReq pageReq) {
        QueryWrapper<OrderLogistics> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderLogisticsQO.getLogisticsCompany())) {
            wrapper.eq(OrderLogistics.LOGISTICS_COMPANY, orderLogisticsQO.getLogisticsCompany());
        }
        if (StrUtil.isNotBlank(orderLogisticsQO.getCourierNumber())) {
            wrapper.eq(OrderLogistics.COURIER_NUMBER, orderLogisticsQO.getCourierNumber());
        }
        if (StrUtil.isNotBlank(orderLogisticsQO.getContent())) {
            wrapper.eq(OrderLogistics.CONTENT, orderLogisticsQO.getContent());
        }
        if (StrUtil.isNotBlank(orderLogisticsQO.getDescription())) {
            wrapper.eq(OrderLogistics.DESCRIPTION, orderLogisticsQO.getDescription());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

}
