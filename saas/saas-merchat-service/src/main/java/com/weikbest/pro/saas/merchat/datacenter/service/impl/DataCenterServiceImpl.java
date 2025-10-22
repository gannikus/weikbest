package com.weikbest.pro.saas.merchat.datacenter.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.merchat.datacenter.module.qo.DataCenterQO;
import com.weikbest.pro.saas.merchat.datacenter.module.vo.DataCenterVO;
import com.weikbest.pro.saas.merchat.datacenter.service.DataCenterService;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.mapper.OrderInfoMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderInfoDataCenterDTO;
import com.weikbest.pro.saas.merchat.shop.entity.ShopVisit;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopVisitMapper;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/4
 */
@Service
public class DataCenterServiceImpl implements DataCenterService {

    @Resource
    private AppletConfigService appletConfigService;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private ShopVisitMapper shopVisitMapper;

    @Override
    public List<DictEntry> queryApplet() {
        // 查询所有小程序
        return appletConfigService.queryDict();
    }

    /**
     * -- 查询订单数据统计 --
     * -- 店铺下单人数 = count(distinct b.customer_id)
     * -- 总支付金额 = b.pay_status=3 的 sum(b.pay_amount)
     * -- 总支付订单数 = b.pay_status=3 的 count()
     * -- 总支付人数 = b.pay_status=3 的 count(distinct b.customer_id)
     * -- 总支付件数 = b.pay_status=3 的 sum(c.buy_number)
     * -- 支付客单价 = 总支付金额 / 总支付人数
     *
     * -- 广告流量支付金额 = a.order_source=5 and b.pay_status=3 的 sum(b.pay_amount)
     * -- 广告流量支付订单数 = a.order_source=5 and b.pay_status=3 的 count()
     * -- 广告流量支付人数 = a.order_source=5 and b.pay_status=3 的 count(distinct b.customer_id)
     * -- 广告流量支付件数 = a.order_source=5 and b.pay_status=3 的 sum(c.buy_number)
     * -- 广告流量支付客单价 = a.order_source=5 的 sum(c.pay_amount)
     *
     * -- 自然流量支付金额 = a.order_source=1 and b.pay_status=3 的 sum(b.pay_amount)
     * -- 自然流量支付订单数 = a.order_source=1 and b.pay_status=3 的 count()
     * -- 自然流量支付人数 = a.order_source=1 and b.pay_status=3 的 count(distinct b.customer_id)
     * -- 自然流量支付件数 = a.order_source=1 and b.pay_status=3 的 sum(c.buy_number)
     * -- 自然流量支付客单价 = a.order_source=1 的 sum(c.pay_amount)
     *
     * -- 成功退款金额 = b.refund_status=1 的 sum(c.refund_amount)
     * -- 成功退款订单数 = b.refund_status=1 的 count()
     * -- 广告流量成功退款人数 = a.order_source=5 and b.refund_status=1 的 count(distinct b.customer_id)
     * -- 自然流量成功退款人数 = a.order_source=1 and b.refund_status=1 的 count(distinct b.customer_id)
     * -- 退款率 = 成功退款订单数 / 总支付订单数
     *
     * -- 流量概览数据统计 --
     * -- 访客数 = 店铺的访问类型=2 订单中所有商品的 count()
     * -- 浏览量 = 店铺的访问类型=1 的count()
     * -- 被访问商品数 = 店铺的访问类型=2 的count(distinct prodId)
     * -- 访问-下单转化率 = 店铺下单人数 / 访客数
     * -- 访问-支付转化率= 总支付人数 / 访客数
     *
     * @param businessId 商户ID
     * @param shopId 店铺ID
     * @param dataCenterQO 查询条件
     * @return
     */
    @Override
    public DataCenterVO queryData(Long businessId, Long shopId, DataCenterQO dataCenterQO) {
        DataCenterVO dataCenterVO = new DataCenterVO();

        List<OrderInfoDataCenterDTO> orderInfoDataCenterDTOList = Optional.ofNullable(orderInfoMapper.queryDataCenter(businessId, shopId, dataCenterQO)).orElseGet(ArrayList::new);

        // b.pay_status=3 的数据
        List<OrderInfoDataCenterDTO> successPayStatusOrderInfoDataCenterDTOList = orderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterDTO -> StrUtil.equals(orderInfoDataCenterDTO.getPayStatus(), DictConstant.PayStatus.success.getCode())).collect(Collectors.toList());

        // 总概览
        DataCenterVO.TotalVO totalVO = buildTotalVO(successPayStatusOrderInfoDataCenterDTOList);

        // 支付概览
        DataCenterVO.PayVO payVO = buildPayVO(successPayStatusOrderInfoDataCenterDTOList);

        // b.refund_status=1 的数据
        List<OrderInfoDataCenterDTO> successRefundStatusOrderInfoDataCenterDTOList = orderInfoMapper.queryRefundDataCenter(businessId, shopId, dataCenterQO);

        // 退款概况
        DataCenterVO.RefundVO refundVO = buildRefundVO(totalVO, successRefundStatusOrderInfoDataCenterDTOList);

        // 流量概览
        DataCenterVO.FlowVO flowVO = buildFlowVO(shopId, dataCenterQO, orderInfoDataCenterDTOList, totalVO);

        //货到付款信息
        buildCODVO(shopId , dataCenterQO , dataCenterVO);

        return dataCenterVO.setTotalVO(totalVO).setPayVO(payVO).setFlowVO(flowVO).setRefundVO(refundVO);
    }

    private DataCenterVO.TotalVO buildTotalVO(List<OrderInfoDataCenterDTO> successPayStatusOrderInfoDataCenterDTOList) {
        DataCenterVO.TotalVO totalVO = new DataCenterVO.TotalVO();
        // 总支付金额
        BigDecimal totalPayAmount = successPayStatusOrderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getPayAmount).reduce(BigDecimal.ZERO, NumberUtil::add);
        totalVO.setTotalPayAmount(totalPayAmount);
        // 总支付订单数
        int totalPayOrderNum = successPayStatusOrderInfoDataCenterDTOList.size();
        totalVO.setTotalPayOrderNum(totalPayOrderNum);
        // 总支付人数
        long totalPayCount = successPayStatusOrderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();
        totalVO.setTotalPayCount(totalPayCount);
        // 总支付件数
        int totalPayGoodsNum = successPayStatusOrderInfoDataCenterDTOList.stream().mapToInt(OrderInfoDataCenterDTO::getBuyNumber).sum();
        totalVO.setTotalPayGoodsNum(totalPayGoodsNum);
        // 支付客单价
        BigDecimal totalCustomerPayAmount = totalPayCount == WeikbestConstant.ZERO_LONG ? BigDecimal.ZERO : NumberUtil.div(totalPayAmount, NumberUtil.toBigDecimal(totalPayCount)).setScale(4, RoundingMode.HALF_EVEN);
        totalVO.setTotalCustomerPayAmount(totalCustomerPayAmount);

        return totalVO;
    }

    private DataCenterVO.PayVO buildPayVO(List<OrderInfoDataCenterDTO> successPayStatusOrderInfoDataCenterDTOList) {
        DataCenterVO.PayVO payVO = new DataCenterVO.PayVO();
        // a.order_source=5 and b.pay_status=3 的数据
        List<OrderInfoDataCenterDTO> advFlowStatusOrderInfoDataCenterDTOList = successPayStatusOrderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterDTO -> StrUtil.equals(orderInfoDataCenterDTO.getOrderSource(), DictConstant.OrderSource.TYPE_5.getCode())).collect(Collectors.toList());

        // 广告流量支付金额
        BigDecimal advFlowTotalPayAmount = advFlowStatusOrderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getPayAmount).reduce(BigDecimal.ZERO, NumberUtil::add);
        payVO.setAdvFlowTotalPayAmount(advFlowTotalPayAmount);
        // 广告流量支付订单数
        int advFlowTotalPayOrderNum = advFlowStatusOrderInfoDataCenterDTOList.size();
        payVO.setAdvFlowTotalPayOrderNum(advFlowTotalPayOrderNum);
        // 广告流量支付人数
        long advFlowTotalPayCount = advFlowStatusOrderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();
        payVO.setAdvFlowTotalPayCount(advFlowTotalPayCount);
        // 广告流量支付件数
        int advFlowTotalPayGoodsNum = advFlowStatusOrderInfoDataCenterDTOList.stream().mapToInt(OrderInfoDataCenterDTO::getBuyNumber).sum();
        payVO.setAdvFlowTotalPayGoodsNum(advFlowTotalPayGoodsNum);
        // 广告流量付客单价
        BigDecimal advFlowTotalCustomerPayAmount = advFlowTotalPayCount == WeikbestConstant.ZERO_LONG ? BigDecimal.ZERO : NumberUtil.div(advFlowTotalPayAmount, NumberUtil.toBigDecimal(advFlowTotalPayCount)).setScale(4, RoundingMode.HALF_EVEN);
        payVO.setAdvFlowTotalCustomerPayAmount(advFlowTotalCustomerPayAmount);

        // a.order_source=1 and b.pay_status=3 的数据
        List<OrderInfoDataCenterDTO> naturalFlowStatusOrderInfoDataCenterDTOList = successPayStatusOrderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterDTO -> StrUtil.equals(orderInfoDataCenterDTO.getOrderSource(), DictConstant.OrderSource.TYPE_1.getCode())).collect(Collectors.toList());

        // 自然流量支付金额
        BigDecimal naturalFlowTotalPayAmount = naturalFlowStatusOrderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getPayAmount).reduce(BigDecimal.ZERO, NumberUtil::add);
        payVO.setNaturalFlowTotalPayAmount(naturalFlowTotalPayAmount);
        // 自然流量支付订单数
        int naturalFlowTotalPayOrderNum = naturalFlowStatusOrderInfoDataCenterDTOList.size();
        payVO.setNaturalFlowTotalPayOrderNum(naturalFlowTotalPayOrderNum);
        // 自然流量支付人数
        long naturalFlowTotalPayCount = naturalFlowStatusOrderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();
        payVO.setNaturalFlowTotalPayCount(naturalFlowTotalPayCount);
        // 自然流量支付件数
        int naturalFlowTotalPayGoodsNum = naturalFlowStatusOrderInfoDataCenterDTOList.stream().mapToInt(OrderInfoDataCenterDTO::getBuyNumber).sum();
        payVO.setNaturalFlowTotalPayGoodsNum(naturalFlowTotalPayGoodsNum);
        // 自然流量付客单价
        BigDecimal naturalFlowTotalCustomerPayAmount = naturalFlowTotalPayCount == WeikbestConstant.ZERO_LONG ? BigDecimal.ZERO : NumberUtil.div(naturalFlowTotalPayAmount, NumberUtil.toBigDecimal(naturalFlowTotalPayCount)).setScale(4, RoundingMode.HALF_EVEN);
        payVO.setNaturalFlowTotalCustomerPayAmount(naturalFlowTotalCustomerPayAmount);
        return payVO;
    }

    private DataCenterVO.RefundVO buildRefundVO(DataCenterVO.TotalVO totalVO, List<OrderInfoDataCenterDTO> successRefundStatusOrderInfoDataCenterDTOList) {
        DataCenterVO.RefundVO refundVO = new DataCenterVO.RefundVO();
        // 成功退款金额
        BigDecimal refundAmount = successRefundStatusOrderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getRefundAmount).reduce(BigDecimal.ZERO, NumberUtil::add);
        refundVO.setRefundAmount(refundAmount);
        // 成功退款订单数
        int refundOrderCount = successRefundStatusOrderInfoDataCenterDTOList.size();
        refundVO.setRefundOrderCount(refundOrderCount);
        // 广告流量成功退款人数
        long advFlowRefundCount = successRefundStatusOrderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterDTO -> StrUtil.equals(orderInfoDataCenterDTO.getOrderSource(), DictConstant.OrderSource.TYPE_5.getCode()))
                .map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();
        refundVO.setAdvFlowRefundCount(advFlowRefundCount);
        // 自然流量成功退款人数
        long naturalFlowRefundCount = successRefundStatusOrderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterDTO -> StrUtil.equals(orderInfoDataCenterDTO.getOrderSource(), DictConstant.OrderSource.TYPE_1.getCode()))
                .map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();
        refundVO.setNaturalFlowRefundCount(naturalFlowRefundCount);
        // 退款率
        Integer totalPayOrderNum = totalVO.getTotalPayOrderNum();
        BigDecimal refundRatio = totalPayOrderNum == WeikbestConstant.ZERO_INT ? BigDecimal.ZERO : NumberUtil.div(NumberUtil.toBigDecimal(refundOrderCount), NumberUtil.toBigDecimal(totalPayOrderNum)).setScale(4, RoundingMode.HALF_EVEN);
        refundVO.setRefundRatio(refundRatio);
        return refundVO;
    }

    private DataCenterVO.FlowVO buildFlowVO(Long shopId, DataCenterQO dataCenterQO, List<OrderInfoDataCenterDTO> orderInfoDataCenterDTOList, DataCenterVO.TotalVO totalVO) {
        // 店铺下单人数
        long totalCount = orderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();

        // 订单访问的所有商品
        Set<Long> prodIdSet = orderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getProdId).collect(Collectors.toSet());

        // 查询店铺的所有访问数据
        List<ShopVisit> shopVisitList = shopVisitMapper.queryDataCenter(shopId, dataCenterQO);
        DataCenterVO.FlowVO flowVO = new DataCenterVO.FlowVO();
        // 访客数
        long visitorCount = shopVisitList.stream().filter(shopVisit -> StrUtil.equals(shopVisit.getVisitType(), DictConstant.VisitType.visitType_2.getCode()) && prodIdSet.contains(shopVisit.getProdId())).count();
//        // 访客数特殊处理(如果访客数为0，设置为下单人数；如果访客数小于下单人数，访客数 = 访客数+下单人数。其他情况则为访客数本身)
//        visitorCount = visitorCount == WeikbestConstant.ZERO_LONG ? totalCount : visitorCount < totalCount ? totalCount + visitorCount : visitorCount;
        // 访客数特殊处理(如果访客数为0，设置为下单人数；否则为访客数本身)
        visitorCount = visitorCount == WeikbestConstant.ZERO_LONG ? totalCount : visitorCount < totalCount ? totalCount + visitorCount : visitorCount;
        flowVO.setVisitorCount(visitorCount);

        // 浏览量
        long pageView = shopVisitList.stream().filter(shopVisit -> StrUtil.equals(shopVisit.getVisitType(), DictConstant.VisitType.visitType_1.getCode())).count();
        flowVO.setPageView(pageView);
        // 被访问商品数
        long visitorProdCount = shopVisitList.stream().filter(shopVisit -> StrUtil.equals(shopVisit.getVisitType(), DictConstant.VisitType.visitType_2.getCode()))
                .map(ShopVisit::getProdId).distinct().count();
        flowVO.setVisitorProdCount(visitorProdCount);

        // 访问-下单转化率
        BigDecimal visitorOrderConversionRate = totalCount == 0L ? new BigDecimal(totalCount) : NumberUtil.div(NumberUtil.toBigDecimal(totalCount), NumberUtil.toBigDecimal(visitorCount)).setScale(4, RoundingMode.HALF_EVEN) ;
        flowVO.setVisitorOrderConversionRate(visitorOrderConversionRate);
        // 访问-支付转化率
        BigDecimal visitorPayConversionRate = visitorCount == WeikbestConstant.ZERO_LONG ? BigDecimal.ZERO : NumberUtil.div(NumberUtil.toBigDecimal(totalVO.getTotalPayCount()), NumberUtil.toBigDecimal(visitorCount)).setScale(4, RoundingMode.HALF_EVEN);
        flowVO.setVisitorPayConversionRate(visitorPayConversionRate);
        return flowVO;
    }


    /**
     * 到付数据预览
     *
     * @param shopId       : 店铺Id
     * @param dataCenterQO : 查询条件
     * @param dataCenterVO
     * @return
     */
    private void buildCODVO(Long shopId, DataCenterQO dataCenterQO, DataCenterVO dataCenterVO) {
        BigDecimal decimal = new BigDecimal(BigInteger.ZERO);
        DataCenterVO.DeliveryVO vo = new DataCenterVO.DeliveryVO();
        DataCenterVO.DeliveryAdVO adVO = new DataCenterVO.DeliveryAdVO();
        Map map = orderInfoMapper.buildCODVO(shopId, dataCenterQO);
        vo.setDeliveryOrderCount((Long) map.get("sumTotal"));
        vo.setDeliveryOrderAmounts(map.containsKey("totalMoney") ? (BigDecimal) map.get("totalMoney") : decimal);
        vo.setDeliveryOrderTotalNumberOfPackages(map.containsKey("deliveryOrderTotalNumberOfPackages") ? (BigDecimal) map.get("deliveryOrderTotalNumberOfPackages") : decimal);
        adVO.setDeliveryOrderAdCallbackCount((Long) map.get("backSumTotal"));
        adVO.setDeliveryOrderAdCallbackAmounts(map.containsKey("backTotalMoney") ? (BigDecimal) map.get("backTotalMoney") : decimal);
        adVO.setDeliveryOrderAdTotalNumberOfPackages(map.containsKey("deliveryOrderAdTotalNumberOfPackages") ? (BigDecimal) map.get("deliveryOrderAdTotalNumberOfPackages") : decimal);

        List<OrderInfo> infos = orderInfoMapper.selectList(new QueryWrapper<OrderInfo>().eq(OrderInfo.SHOP_ID, shopId)
                .eq(OrderInfo.FLAG, BasicConstant.STATE_0)
                .eq(OrderInfo.PAY_TYPE, BasicConstant.STATE_2)
                .ge(OrderInfo.ORDER_TIME, dataCenterQO.getOrderStartTime())
                .le(OrderInfo.ORDER_TIME, dataCenterQO.getOrderEndTime()));

        Set<Long> deliveryOrderTotalNumberOfPeoples = new HashSet<>(); //货到付款总支付人数
        Set<Long> deliveryOrderAdTotalNumberOfPeoples = new HashSet<>(); //货到付款广告回传总支付人数
        for (OrderInfo info : infos) {
            deliveryOrderTotalNumberOfPeoples.add(info.getCustomerId());
            if (StringUtils.isNotBlank(info.getIsAdvBack()) && BasicConstant.STATE_1.equals(info.getIsAdvBack())){
                deliveryOrderAdTotalNumberOfPeoples.add(info.getCustomerId());
            }
        }
        vo.setDeliveryOrderTotalNumberOfPeople(deliveryOrderTotalNumberOfPeoples.size());
        adVO.setDeliveryOrderAdTotalNumberOfPeople(deliveryOrderAdTotalNumberOfPeoples.size());

        //客单价
        BigDecimal deliveryOrderTotalNumberOfPeople = new BigDecimal(vo.getDeliveryOrderTotalNumberOfPeople());
        BigDecimal deliveryOrderAdTotalNumberOfPeople = new BigDecimal(adVO.getDeliveryOrderAdTotalNumberOfPeople());
        if (deliveryOrderTotalNumberOfPeople.compareTo(BigDecimal.ZERO) == 0 || vo.getDeliveryOrderAmounts().compareTo(BigDecimal.ZERO) == 0 ){
            vo.setDeliveryOrderARPA(decimal);
        }else {
            vo.setDeliveryOrderARPA(vo.getDeliveryOrderAmounts().divide(deliveryOrderTotalNumberOfPeople , BigDecimal.ROUND_HALF_UP));
        }
        if (deliveryOrderAdTotalNumberOfPeople.compareTo(BigDecimal.ZERO) == 0 || adVO.getDeliveryOrderAdCallbackAmounts().compareTo(BigDecimal.ZERO) == 0){
            adVO.setDeliveryOrderAdARPA(decimal);
        }else {
            adVO.setDeliveryOrderAdARPA(adVO.getDeliveryOrderAdCallbackAmounts().divide(deliveryOrderAdTotalNumberOfPeople , BigDecimal.ROUND_HALF_UP));
        }
        dataCenterVO.setDeliveryVO(vo);
        dataCenterVO.setDeliveryAdVO(adVO);
    }
}
