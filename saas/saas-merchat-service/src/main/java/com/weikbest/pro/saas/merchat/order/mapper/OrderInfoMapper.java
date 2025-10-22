package com.weikbest.pro.saas.merchat.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.merchat.datacenter.module.qo.DataCenterQO;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.module.dto.AppOrderInfoListDTO;
import com.weikbest.pro.saas.merchat.order.module.excel.OrderInfoDetailExcel;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderInfoQO;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderInfoDataCenterDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoListVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderStatusGroupVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    /**
     * 查询小程序订单列表信息
     *
     * @param paramMap
     * @return
     */
    List<AppOrderInfoListDTO> queryPageAppOrderInfo(Map<String, Object> paramMap);

    /**
     * 订单后台分页查询
     *
     * @param page
     * @param orderInfoQO
     * @return
     */
    IPage<OrderInfoListVO> queryPage(IPage<OrderInfoListVO> page, @Param("orderInfo") OrderInfoQO orderInfoQO);

    /**
     * 查询商户店铺订单状态汇总数据
     *
     * @param orderInfoQO
     * @return
     */
    List<OrderStatusGroupVO> queryGroupOrderStatus(@Param("orderInfo") OrderInfoQO orderInfoQO);

    /**
     * 15-24小时待发货状态 汇总数据
     *
     * @param orderInfoQO
     * @return
     */
    long queryCountWaitDeliverCount(@Param("orderInfo") OrderInfoQO  orderInfoQO);

    /**
     * 40-发货已超时 汇总数据
     *
     * @param orderInfoQO
     * @return
     */
    long queryCountTimeoutDeliverCount(@Param("orderInfo") OrderInfoQO orderInfoQO);

    /**
     * 60-售后中 汇总数据
     *
     * @param orderInfoQO
     * @return
     */
    long queryCountOrderAfterSaleCount(@Param("orderInfo") OrderInfoQO orderInfoQO);

    /**
     * 订单后台导出数据查询
     *
     * @param orderInfoQO
     * @return
     */
    List<OrderInfoDetailExcel> downloadDetail(@Param("orderInfo") OrderInfoQO orderInfoQO);

    /**
     * 查询数据中心记录
     *
     * @param businessId
     * @param shopId
     * @param dataCenterQO
     * @return
     */
    List<OrderInfoDataCenterDTO> queryDataCenter(@Param("businessId") Long businessId, @Param("shopId") Long shopId, @Param("dataCenter") DataCenterQO dataCenterQO);

    /**
     * 查询数据中心退款记录
     *
     * @param businessId
     * @param shopId
     * @param dataCenterQO
     * @return
     */
    List<OrderInfoDataCenterDTO> queryRefundDataCenter(@Param("businessId") Long businessId, @Param("shopId") Long shopId, @Param("dataCenter") DataCenterQO dataCenterQO);

    /**
     * 查询自定义时间内店铺的到付的订单数据
     * @param shopId
     * @param dataCenterQO
     * @return
     */
    Map buildCODVO(@Param("shopId") Long shopId, @Param("dataCenterQO") DataCenterQO dataCenterQO);

    String getMainNumberByNumber(@Param("number") String number);
}
