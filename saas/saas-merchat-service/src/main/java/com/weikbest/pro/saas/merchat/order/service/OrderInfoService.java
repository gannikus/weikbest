package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.api.module.qo.OrderInfoQo;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.module.dto.AppOrderInfoListDTO;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderInfoDTO;
import com.weikbest.pro.saas.merchat.order.module.excel.OrderInfoDetailExcel;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderInfoQO;
import com.weikbest.pro.saas.merchat.order.module.vo.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * 新增数据
     *
     * @param orderInfoDTO orderInfoDTO
     * @return 新增结果
     */
    boolean insert(OrderInfoDTO orderInfoDTO);

    /**
     * 更新数据
     *
     * @param id           ID
     * @param orderInfoDTO orderInfoDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderInfoDTO orderInfoDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderInfo findById(Long id);

    /**
     * 分页查询
     *
     * @param orderInfoQO
     * @param pageReq
     * @return
     */
    IPage<OrderInfoListVO> queryPage(OrderInfoQO orderInfoQO, PageReq pageReq);

    /**
     * 查询小程序订单列表信息
     *
     * @param paramMap
     * @return
     */
    List<AppOrderInfoListDTO> queryPageAppOrderInfo(Map<String, Object> paramMap);

    /**
     * 更新订单备注信息
     *
     * @param id
     * @param description
     * @return
     */
    boolean updateDescriptionById(Long id, String description);

    /**
     * 查询商户店铺订单状态分组数据
     *
     * @param orderInfoQO
     * @return
     */
    List<OrderStatusGroupVO> queryGroupOrderStatus(OrderInfoQO orderInfoQO);

    /**
     * 更新订单信息，订单数据已拼好
     *
     * @param orderInfo
     */
    void updateOrderInfo(OrderInfo orderInfo);

    /**
     * 修改订单状态
     *
     * @param orderInfo
     * @param orderStatus
     * @param description
     */
    void updateOrderStatus(OrderInfo orderInfo, String orderStatus, String description);


    /**
     * 修改订单状态
     *
     * @param orderInfo
     * @param currOrderStatus
     * @param changeOrderStatus
     * @param description
     */
    void updateOrderStatus(OrderInfo orderInfo, String currOrderStatus, String changeOrderStatus, String description);

    /**
     * 小程序端修改订单状态
     *
     * @param orderInfo
     * @param currOrderStatus
     * @param changeOrderStatus
     * @param description
     */
    void appupdateOrderStatus(OrderInfo orderInfo, String currOrderStatus, String changeOrderStatus, String description);


    /**
     * 根据订单ID查询订单详情
     *
     * @param id
     * @return
     */
    OrderInfoDetailVO findDetailVOById(Long id);

    /**
     * 根据订单号查询订单
     *
     * @param orderNumber
     * @return
     */
    OrderInfo getByOrderNumber(String orderNumber);

    /**
     * 根据订单号查询订单
     *
     * @param orderNumber
     * @return
     */
    OrderInfo findByOrderNumber(String orderNumber);

    /**
     * 根据订单ID查询
     *
     * @param id
     * @return
     */
    OrderInfoVO findVOById(Long id);

    /**
     * 订单支付超时提醒处理
     *
     * @param id
     */
    void waitPayTimeoutExecute(Long id);

    /**
     * 订单支付超时，取消支付处理
     *
     * @param id
     */
    void cancelPayTimeoutExecute(Long id);

    /**
     * 订单发货超时处理
     *
     * @param id
     */
    void deliverTimeoutExecute(Long id);

    /**
     * 订单确认收货超时处理
     *
     * @param id
     */
    void deliverCustomerTimeoutExecute(Long id);

    /**
     * 订单自动完成处理
     *
     * @param id
     */
    void finishTimeoutExecute(Long id);


    /**
     * 根据优惠券Id和客户ID记录查询
     *
     * @param couponId
     * @param customerIdList
     * @return
     */
    Map<Long, OrderInfoCustCouponRestrictVO> queryCustCouponRestrictVOCustomerIdMapByCouponIdAndCustomerIdList(Long couponId, List<Long> customerIdList);


    /**
     * 到期解除客户绑定商户信息
     */
    void taskUnBind(Long custBusinessBindId);

    /**
     * 到期解冻冰结资金
     * @param orderId
     */
    String orderFundReleaseHourTimeout(Long orderId);

    /**
     * 发货后7天无售后或售后完成后7天进行结算
     * @param orderId
     */
    void doOrderSettlement(Long orderId);

    /**
     * 订单下载
     *
     * @param orderInfoQO
     * @return
     */
    List<OrderInfoDetailExcel> downloadDetail(OrderInfoQO orderInfoQO);

    /**
     * 广告回传
     * @param id
     */
    void adCallback(Long id);

    /**
     * 广告监测
     * @param clickId
     * @param adgroupId
     * @param adId
     * @param callback
     * @param accountId
     * @param clickTime
     * @param wechatOpenid
     * @param requestId
     */
    void clickMonitoring(String clickId, String adgroupId, String adId, String callback, String accountId, String clickTime,String wechatOpenid,String requestId);

    /**
     * 广告点击监测old
     * @param clickId
     * @param adgroupId
     * @param clickTime
     * @param requestId
     */
    void clickMonitoringOld(Integer clickId, String adgroupId, Integer clickTime, String requestId);

    /**
     * 根据主单号查询订单
     * @param number
     * @return
     */
    List<OrderInfo> findByOrderMainNumber(String number);

    /**
     * 取消到付订单
     * @param id
     * @return
     */
    boolean channelDeliveryOrder(Long id);

    /**
     * 根据订单号批量查询
     *
     * @param numberList
     * @param businessId
     * @return
     */
    List<OrderInfoDetailVO> listByBatch(List<String> numberList, Long businessId);

    /**
     * 根据订单编号查询合单的主单号
     * @param number
     * @return
     */
    String getMainNumberByNumber(String number);

    /**
     * 分页查询订单
     * @param orderInfoQo
     * @param pageReq
     * @param businessId
     * @return
     */
    IPage<OrderInfoDetailVO> listByBatch(OrderInfoQo orderInfoQo, PageReq pageReq, Long businessId);
}
