package com.weikbest.pro.saas.sysmerchat.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sysmerchat.common.mapper.SysIndexMapper;
import com.weikbest.pro.saas.sysmerchat.common.module.dto.RealtimeDTO;
import com.weikbest.pro.saas.sysmerchat.common.module.qo.OrderStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.common.module.qo.RealtimeQO;
import com.weikbest.pro.saas.sysmerchat.common.module.qo.SalesStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.common.module.vo.OrderStatisticsVO;
import com.weikbest.pro.saas.sysmerchat.common.module.vo.RealtimeVO;
import com.weikbest.pro.saas.sysmerchat.common.module.vo.SalesStatisticsVO;
import com.weikbest.pro.saas.sysmerchat.common.service.SysIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/18
 */
@Slf4j
@Service
public class SysIndexServiceImpl implements SysIndexService {

    @Resource
    private SysIndexMapper sysIndexMapper;

    @Override
    public RealtimeVO queryRealtime() {
        RealtimeVO realtimeVO = new RealtimeVO();

        // 今日实时概况
        DateTime nowDate = DateUtil.date();
        RealtimeVO.DayRealtimeVO todayRealtimeVO = queryDayRealtime(DateUtil.beginOfDay(nowDate), nowDate);
        realtimeVO.setTodayRealtimeVO(todayRealtimeVO);

        // 昨日实时概况
        DateTime yesterday = DateUtil.yesterday();
        RealtimeVO.DayRealtimeVO yesterdayRealtimeVO = queryDayRealtime(DateUtil.beginOfDay(yesterday), DateUtil.endOfDay(yesterday));
        realtimeVO.setYesterdayRealtimeVO(yesterdayRealtimeVO);

        // 总概况
        RealtimeVO.DayRealtimeVO totalRealtimeVO = queryDayRealtime(null, null);
        realtimeVO.setTotalRealtimeVO(totalRealtimeVO);

        // 销售额日环比
        BigDecimal saleAmountDailyCycle = dailyCycle(todayRealtimeVO.getSaleAmount(), yesterdayRealtimeVO.getSaleAmount());
        realtimeVO.setSaleAmountDailyCycle(saleAmountDailyCycle);

        // 订单数日环比
        BigDecimal orderNumDailyCycle = dailyCycle(todayRealtimeVO.getOrderNum(), yesterdayRealtimeVO.getOrderNum());
        realtimeVO.setOrderNumDailyCycle(orderNumDailyCycle);

        // 支付人数日环比
        BigDecimal payCountDailyCycle = dailyCycle(todayRealtimeVO.getPayCount(), yesterdayRealtimeVO.getPayCount());
        realtimeVO.setPayCountDailyCycle(payCountDailyCycle);

        // 支付件数日环比
        BigDecimal payGoodsNumDailyCycle = dailyCycle(todayRealtimeVO.getPayGoodsNum(), yesterdayRealtimeVO.getPayGoodsNum());
        realtimeVO.setPayGoodsNumDailyCycle(payGoodsNumDailyCycle);

        // 更新时间
        realtimeVO.setUpdateTime(nowDate);
        return realtimeVO;
    }

    /**
     * 查询实时概况
     * 例：查询今日实时概况
     * 今日销售额=从今日0点0时0分到今日当前时间为止所有的实际支付订单金额
     * 今日订单数=从今日0点0时0分到今日当前时间为止所有下单的订单数（包括待付款订单和实际支付的订单）
     * 今日支付人数=从今日0点0时0分到今日当前时间为止实际支付的总人数（若一人购买了2单相同的或者不同商品，但是还是1个人购买的，统计为数量1）
     * 今日支付件数=从今日0点0时0分到今日当前时间为止实际支付的总商品件数（若一人购买了2单相同的商品，统计为数量2；若一个人购买了一个2件套的商品，该套餐包含2个单品，支付件数统计数量为1）
     *
     * @param beginDate
     * @param endDate
     */
    private RealtimeVO.DayRealtimeVO queryDayRealtime(Date beginDate, Date endDate) {
        RealtimeVO.DayRealtimeVO dayRealtimeVO = new RealtimeVO.DayRealtimeVO();

        RealtimeQO realtimeQO = new RealtimeQO();
        realtimeQO.setOrderStartTime(beginDate)
                .setOrderEndTime(endDate);

        List<RealtimeDTO> realtimeDTOList = sysIndexMapper.queryRealtime(realtimeQO);
        if (CollectionUtil.isEmpty(realtimeDTOList)) {
            return dayRealtimeVO;
        }

        // b.pay_status=3 的数据
        List<RealtimeDTO> successPayStatusOrderInfoRealtimeDTOList = realtimeDTOList.stream().filter(realtimeDTO -> StrUtil.equals(realtimeDTO.getPayStatus(), DictConstant.PayStatus.success.getCode())).collect(Collectors.toList());

        // 今日销售额 = b.pay_status=3 的 sum(b.pay_amount)
        BigDecimal saleAmount = successPayStatusOrderInfoRealtimeDTOList.stream().map(RealtimeDTO::getPayAmount).reduce(BigDecimal.ZERO, NumberUtil::add);
        dayRealtimeVO.setSaleAmount(saleAmount);

        // 今日订单数 = count()
        int orderNum = realtimeDTOList.size();
        dayRealtimeVO.setOrderNum(orderNum);

        // 今日支付人数 = b.pay_status=3 的 count(distinct b.customer_id)
        long payCount = successPayStatusOrderInfoRealtimeDTOList.stream().map(RealtimeDTO::getCustomerId).distinct().count();
        dayRealtimeVO.setPayCount(payCount);

        // 今日支付件数 = b.pay_status=3 的 sum(c.buy_number)
        int payGoodsNum = successPayStatusOrderInfoRealtimeDTOList.stream().mapToInt(RealtimeDTO::getBuyNumber).sum();
        dayRealtimeVO.setPayGoodsNum(payGoodsNum);

        return dayRealtimeVO;
    }


    /**
     * 日环比计算
     * 日环比计算公式：环比增长率 =（本期数－上期数）／上期数 ×100%，如总销售额里面的日环比=（当日销售总额-昨日销售总额）/昨日销售总额*100%
     *
     * @param todayAmount
     * @param yesterdayAmount
     * @return
     */
    private BigDecimal dailyCycle(Number todayAmount, Number yesterdayAmount) {
        Number yesterdayDevideAmount = yesterdayAmount;

        if (ObjectUtil.isNull(todayAmount)) {
            todayAmount = BigDecimal.ZERO;
        }
        if (ObjectUtil.isNull(yesterdayAmount)) {
            yesterdayAmount = BigDecimal.ZERO;
        }
        if (ObjectUtil.isNull(yesterdayAmount) || yesterdayAmount.intValue() == WeikbestConstant.ZERO_INT) {
            // 取除数为1
            yesterdayDevideAmount = BigDecimal.ONE;
        }
        return NumberUtil.sub(todayAmount, yesterdayAmount).divide(NumberUtil.toBigDecimal(yesterdayDevideAmount), 4, RoundingMode.HALF_EVEN);
    }


    @Override
    public SalesStatisticsVO querySalesStatistics(SalesStatisticsQO salesStatisticsQO) {

        RealtimeQO realtimeQO = new RealtimeQO();
        realtimeQO.setOrderStartTime(salesStatisticsQO.getOrderStartTime())
                .setOrderEndTime(salesStatisticsQO.getOrderEndTime())
                .setAppId(salesStatisticsQO.getAppId());

        List<RealtimeDTO> realtimeDTOList = sysIndexMapper.queryRealtime(realtimeQO);
        // b.pay_status=3 的数据
        List<RealtimeDTO> successPayStatusOrderInfoRealtimeDTOList = realtimeDTOList.stream().filter(realtimeDTO -> StrUtil.equals(realtimeDTO.getPayStatus(), DictConstant.PayStatus.success.getCode())).collect(Collectors.toList());

        List<BigDecimal> seriesData = new ArrayList<>();
        List<String> xAxisData;
        String isDaySale = salesStatisticsQO.getIsDaySale();
        if (StrUtil.equals(isDaySale, DictConstant.Whether.yes.getCode())) {
            // 按天统计，取开始时间到结束时间每1个小时的数据
            xAxisData = buildXAxisDataByTime(salesStatisticsQO.getOrderStartTime(), salesStatisticsQO.getOrderEndTime());
            // 遍历过滤数据集
            for (int i = 0; i < xAxisData.size() - 1; i++) {
                Date beginDateTime;
                Date endDateTime;
                if(i == 0) {
                    // 第一个时间点查询前1个小时的数据
                    endDateTime = DateUtil.parseDate(xAxisData.get(i));
                    beginDateTime = DateUtil.offsetHour(endDateTime, -1);
                } else {
                    beginDateTime = DateUtil.parseDate(xAxisData.get(i));
                    endDateTime = DateUtil.parseDate(xAxisData.get(i + 1));
                }

                BigDecimal saleAmount = salesAmountStatistics(successPayStatusOrderInfoRealtimeDTOList, beginDateTime, endDateTime);
                seriesData.add(saleAmount);
            }

        } else {
            // 按日统计，取开始时间到结束时间每1日的数据
            xAxisData = buildXAxisDataByDay(salesStatisticsQO.getOrderStartTime(), salesStatisticsQO.getOrderEndTime());
            // 遍历过滤数据集
            for (String xAxis : xAxisData) {
                Date dateTime = DateUtil.parseDate(xAxis);
                Date beginDateTime = DateUtil.beginOfDay(dateTime);
                Date endDateTime = DateUtil.endOfDay(dateTime);
                // 统计 beginDateTime <= 支付时间 < endDateTime 的数据
                BigDecimal saleAmount = salesAmountStatistics(successPayStatusOrderInfoRealtimeDTOList, beginDateTime, endDateTime);
                seriesData.add(saleAmount);
            }
        }


        return new SalesStatisticsVO()
                .setXAxisData(xAxisData)
                .setSeriesData(seriesData);
    }

    /**
     * 每个时间区间的销售额统计
     *
     * @param successPayStatusOrderInfoRealtimeDTOList
     * @param beginDateTime
     * @param endDateTime
     * @return
     */
    private BigDecimal salesAmountStatistics(List<RealtimeDTO> successPayStatusOrderInfoRealtimeDTOList, Date beginDateTime, Date endDateTime) {
        // 统计 beginDateTime <= 支付时间 < endDateTime 的数据
        return successPayStatusOrderInfoRealtimeDTOList.stream()
                .filter(realtimeDTO -> {
                    Date payTime = realtimeDTO.getPayTime();
                    return DateUtil.compare(beginDateTime, payTime) <= WeikbestConstant.ZERO_INT && DateUtil.compare(payTime, endDateTime) < WeikbestConstant.ZERO_INT;
                })
                .map(RealtimeDTO::getPayAmount)
                .reduce(BigDecimal.ZERO, NumberUtil::add);
    }

    /**
     * 按照时间 分割X轴数据
     *
     * @param orderStartTime
     * @param orderEndTime
     * @return
     */
    private List<String> buildXAxisDataByTime(Date orderStartTime, Date orderEndTime) {
        List<String> xAxisData = new ArrayList<>();
        Date beginDateTime = orderStartTime;
        do {
            String beginDateTimeStr = DateUtil.formatDateTime(beginDateTime);
            xAxisData.add(beginDateTimeStr);

            // 往后推1小时
            beginDateTime = DateUtil.offsetHour(beginDateTime, 1);

        } while (DateUtil.compare(beginDateTime, orderEndTime) < WeikbestConstant.ZERO_INT);

        String orderEndTimeStr = DateUtil.formatDateTime(orderEndTime);
        xAxisData.add(orderEndTimeStr);
        return xAxisData;
    }

    /**
     * 按照天 分割X轴数据
     * @param orderStartTime
     * @param orderEndTime
     * @return
     */
    private List<String> buildXAxisDataByDay(Date orderStartTime, Date orderEndTime) {
        List<String> xAxisData = new ArrayList<>();
        Date beginDateTime = orderStartTime;
        do {
            String beginDateStr = DateUtil.formatDate(beginDateTime);
            xAxisData.add(beginDateStr);

            // 往后推1日
            beginDateTime = DateUtil.offsetDay(beginDateTime, 1);

        } while (DateUtil.compare(beginDateTime, orderEndTime) < WeikbestConstant.ZERO_INT);

        String orderEndTimeStr = DateUtil.formatDate(orderEndTime);
        xAxisData.add(orderEndTimeStr);
        return xAxisData;
    }

    @Override
    public OrderStatisticsVO queryOrderStatistics(OrderStatisticsQO orderStatisticsQO) {
        RealtimeQO realtimeQO = new RealtimeQO();
        realtimeQO.setOrderStartTime(orderStatisticsQO.getOrderStartTime())
                .setOrderEndTime(orderStatisticsQO.getOrderEndTime())
                .setAppId(orderStatisticsQO.getAppId());

        List<RealtimeDTO> realtimeDTOList = sysIndexMapper.queryRealtime(realtimeQO);

        List<BigDecimal> seriesData = new ArrayList<>();
        List<String> xAxisData;
        String isDaySale = orderStatisticsQO.getIsDaySale();
        if (StrUtil.equals(isDaySale, DictConstant.Whether.yes.getCode())) {
            // 按天统计，取开始时间到结束时间每1个小时的数据
            xAxisData = buildXAxisDataByTime(orderStatisticsQO.getOrderStartTime(), orderStatisticsQO.getOrderEndTime());
            // 遍历过滤数据集
            for (int i = 0; i < xAxisData.size() - 1; i++) {
                Date beginDateTime;
                Date endDateTime;
                if(i == 0) {
                    // 第一个时间点查询前1个小时的数据
                    endDateTime = DateUtil.parseDate(xAxisData.get(i));
                    beginDateTime = DateUtil.offsetHour(endDateTime, -1);
                } else {
                    beginDateTime = DateUtil.parseDate(xAxisData.get(i));
                    endDateTime = DateUtil.parseDate(xAxisData.get(i + 1));
                }
                // 统计 beginDateTime <= 支付时间 < endDateTime 的数据
                long orderNum = orderNumStatistics(realtimeDTOList, beginDateTime, endDateTime);
                seriesData.add(NumberUtil.toBigDecimal(orderNum));
            }

        } else {
            // 按日统计，取开始时间到结束时间每1日的数据
            xAxisData = buildXAxisDataByDay(orderStatisticsQO.getOrderStartTime(), orderStatisticsQO.getOrderEndTime());
            // 遍历过滤数据集
            for (String xAxis : xAxisData) {
                Date dateTime = DateUtil.parseDate(xAxis);
                Date beginDateTime = DateUtil.beginOfDay(dateTime);
                Date endDateTime = DateUtil.endOfDay(dateTime);
                // 统计 beginDateTime <= 支付时间 < endDateTime 的数据
                long orderNum = orderNumStatistics(realtimeDTOList, beginDateTime, endDateTime);
                seriesData.add(NumberUtil.toBigDecimal(orderNum));
            }
        }

        return new OrderStatisticsVO()
                .setXAxisData(xAxisData)
                .setSeriesData(seriesData);
    }

    /**
     * 每个时间区间的订单统计
     *
     * @param realtimeDTOList
     * @param beginDateTime
     * @param endDateTime
     * @return
     */
    private long orderNumStatistics(List<RealtimeDTO> realtimeDTOList, Date beginDateTime, Date endDateTime) {
        // 统计 beginDateTime <= 支付时间 < endDateTime 的数据
        return realtimeDTOList.stream()
                .filter(realtimeDTO -> {
                    Date payTime = realtimeDTO.getPayTime();
                    return DateUtil.compare(beginDateTime, payTime) <= WeikbestConstant.ZERO_INT && DateUtil.compare(payTime, endDateTime) < WeikbestConstant.ZERO_INT;
                })
                .count();
    }
}
