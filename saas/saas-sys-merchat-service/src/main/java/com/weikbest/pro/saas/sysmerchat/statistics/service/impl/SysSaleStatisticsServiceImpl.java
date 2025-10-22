package com.weikbest.pro.saas.sysmerchat.statistics.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderInfoDataCenterDTO;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sysmerchat.statistics.mapper.SysSaleStatisticsMapper;
import com.weikbest.pro.saas.sysmerchat.statistics.module.qo.SysSaleStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.statistics.module.vo.SysSaleStatisticsVO;
import com.weikbest.pro.saas.sysmerchat.statistics.service.SysSaleStatisticsService;
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
public class SysSaleStatisticsServiceImpl implements SysSaleStatisticsService {

    @Resource
    private SysSaleStatisticsMapper sysSaleStatisticsMapper;

    /**
     * -- 查询订单数据统计 --
     * -- 订单数 = count()
     * -- 总支付金额 = b.pay_status=3 的 sum(b.pay_amount)
     * -- 订单数 = count()
     * -- 支付订单数 = b.pay_status=3 的 count()
     * -- 下单用户数 = count(distinct b.customer_id)
     * -- 支付用户数 = b.pay_status=3 的 count(distinct b.customer_id)
     * -- 下单件数 = sum(c.buy_number)
     * -- 支付件数 = b.pay_status=3 的 sum(c.buy_number)
     * -- 支付客单价 = 总支付金额 / 总支付人数
     *
     * @param sysRefundStatisticsQO@return
     */
    @Override
    public SysSaleStatisticsVO queryData(SysSaleStatisticsQO sysRefundStatisticsQO) {
        SysSaleStatisticsVO sysSaleStatisticsVO = new SysSaleStatisticsVO();

        List<OrderInfoDataCenterDTO> orderInfoDataCenterDTOList = sysSaleStatisticsMapper.querySaleStatistics(sysRefundStatisticsQO);
        if(CollectionUtil.isEmpty(orderInfoDataCenterDTOList)) {
            return sysSaleStatisticsVO;
        }

        // b.pay_status=3 的数据
        List<OrderInfoDataCenterDTO> successPayStatusOrderInfoDataCenterDTOList = orderInfoDataCenterDTOList.stream().filter(orderInfoDataCenterVO -> StrUtil.equals(orderInfoDataCenterVO.getPayStatus(), DictConstant.PayStatus.success.getCode())).collect(Collectors.toList());

        // 总支付金额
        BigDecimal totalPayAmount = successPayStatusOrderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getPayAmount).reduce(BigDecimal.ZERO, NumberUtil::add);
        sysSaleStatisticsVO.setTotalPayAmount(totalPayAmount);

        // 订单数
        int totalOrderNum = orderInfoDataCenterDTOList.size();
        sysSaleStatisticsVO.setTotalOrderNum(totalOrderNum);

        // 支付订单数
        int totalPayOrderNum = successPayStatusOrderInfoDataCenterDTOList.size();
        sysSaleStatisticsVO.setTotalPayOrderNum(totalPayOrderNum);

        // 下单用户数
        long totalConversionCount = orderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();
        sysSaleStatisticsVO.setTotalConversionCount(totalConversionCount);

        // 支付用户数
        long totalPayCount = successPayStatusOrderInfoDataCenterDTOList.stream().map(OrderInfoDataCenterDTO::getCustomerId).distinct().count();
        sysSaleStatisticsVO.setTotalPayCount(totalPayCount);

        // 下单件数
        int totalConversionGoodsNum = orderInfoDataCenterDTOList.stream().mapToInt(OrderInfoDataCenterDTO::getBuyNumber).sum();
        sysSaleStatisticsVO.setTotalConversionGoodsNum(totalConversionGoodsNum);

        // 支付件数
        int totalPayGoodsNum = successPayStatusOrderInfoDataCenterDTOList.stream().mapToInt(OrderInfoDataCenterDTO::getBuyNumber).sum();
        sysSaleStatisticsVO.setTotalPayGoodsNum(totalPayGoodsNum);

        // 客单价(元)
        BigDecimal totalCustomerPayAmount = totalPayCount == WeikbestConstant.ZERO_LONG ? BigDecimal.ZERO : NumberUtil.div(totalPayAmount, NumberUtil.toBigDecimal(totalPayCount)).setScale(4, RoundingMode.HALF_EVEN);
        sysSaleStatisticsVO.setTotalCustomerPayAmount(totalCustomerPayAmount);

        return sysSaleStatisticsVO;
    }

}
