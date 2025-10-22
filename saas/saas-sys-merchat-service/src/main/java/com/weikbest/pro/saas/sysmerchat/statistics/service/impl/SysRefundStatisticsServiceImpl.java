package com.weikbest.pro.saas.sysmerchat.statistics.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderInfoDataCenterDTO;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sysmerchat.statistics.mapper.SysRefundStatisticsMapper;
import com.weikbest.pro.saas.sysmerchat.statistics.module.qo.SysRefundStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.statistics.module.vo.SysRefundStatisticsVO;
import com.weikbest.pro.saas.sysmerchat.statistics.service.SysRefundStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/4
 */
@Service
public class SysRefundStatisticsServiceImpl implements SysRefundStatisticsService {

    @Resource
    private SysRefundStatisticsMapper sysRefundStatisticsMapper;

    /**
     * -- 查询订单数据统计 --
     * -- 总支付订单数 = b.pay_status=3 的 count()
     *
     * -- 成功退款金额 = b.refund_status=1 的 sum(c.refund_amount)
     * -- 成功退款订单数 = b.refund_status=1 的 count()
     * -- 广告流量成功退款人数 = a.order_source=5 and b.refund_status=1 的 count(distinct b.customer_id)
     * -- 自然流量成功退款人数 = a.order_source=1 and b.refund_status=1 的 count(distinct b.customer_id)
     * -- 平台流量成功退款人数 = a.order_source=20 and b.refund_status=1 的 count(distinct b.customer_id)
     * -- 退款率 = 成功退款订单数 / 总支付订单数
     *
     * @param sysRefundStatisticsQO 查询条件
     * @return SysRefundStatisticsVO
     */
    @Override
    public SysRefundStatisticsVO queryData(SysRefundStatisticsQO sysRefundStatisticsQO) {
        SysRefundStatisticsVO sysRefundStatisticsVO = new SysRefundStatisticsVO();

        List<OrderInfoDataCenterDTO> orderInfoDataCenterDTOList = sysRefundStatisticsMapper.queryRefundStatistics(sysRefundStatisticsQO);
        if(CollectionUtil.isEmpty(orderInfoDataCenterDTOList)) {
            return sysRefundStatisticsVO;
        }

        // b.pay_status=3 的数据
        List<OrderInfoDataCenterDTO> successPayStatusOrderInfoDataCenterDTOList = orderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterVO -> StrUtil.equals(orderInfoDataCenterVO.getPayStatus(), DictConstant.PayStatus.success.getCode())).collect(Collectors.toList());

        // 总支付订单数
        int totalPayOrderNum = successPayStatusOrderInfoDataCenterDTOList.size();
        sysRefundStatisticsVO.setTotalPayOrderNum(totalPayOrderNum);


        // b.refund_status=1 的数据
        List<OrderInfoDataCenterDTO> successRefundStatusOrderInfoDataCenterDTOList = orderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterVO -> StrUtil.equals(orderInfoDataCenterVO.getRefundStatus(), DictConstant.RefundStatus.refundStatus_1.getCode())).collect(Collectors.toList());

        // 成功退款金额
        BigDecimal refundAmount = successRefundStatusOrderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getRefundAmount).reduce(BigDecimal.ZERO, NumberUtil::add);
        sysRefundStatisticsVO.setRefundAmount(refundAmount);

        // 成功退款订单数
        int refundOrderCount = successRefundStatusOrderInfoDataCenterDTOList.size();
        sysRefundStatisticsVO.setRefundOrderCount(refundOrderCount);

        // 广告流量成功退款人数
        long advFlowRefundCount = successRefundStatusOrderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterVO -> StrUtil.equals(orderInfoDataCenterVO.getOrderSource(), DictConstant.OrderSource.TYPE_5.getCode()))
                .map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();
        sysRefundStatisticsVO.setAdvFlowRefundCount(advFlowRefundCount);

        // 自然流量成功退款人数
        long naturalFlowRefundCount = successRefundStatusOrderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterVO -> StrUtil.equals(orderInfoDataCenterVO.getOrderSource(), DictConstant.OrderSource.TYPE_1.getCode()))
                .map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();
        sysRefundStatisticsVO.setNaturalFlowRefundCount(naturalFlowRefundCount);

        // 平台流量成功退款人数
        long platformFlowRefundCount = successRefundStatusOrderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterVO -> StrUtil.equals(orderInfoDataCenterVO.getOrderSource(), DictConstant.OrderSource.TYPE_20.getCode()))
                .map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();
        sysRefundStatisticsVO.setPlatformFlowRefundCount(platformFlowRefundCount);

        // 退款率
        BigDecimal refundRatio = totalPayOrderNum == WeikbestConstant.ZERO_INT ? BigDecimal.ZERO : NumberUtil.div(NumberUtil.toBigDecimal(refundOrderCount), NumberUtil.toBigDecimal(totalPayOrderNum)).setScale(4, RoundingMode.HALF_EVEN);
        sysRefundStatisticsVO.setRefundRatio(refundRatio);

        return sysRefundStatisticsVO;
    }
}
