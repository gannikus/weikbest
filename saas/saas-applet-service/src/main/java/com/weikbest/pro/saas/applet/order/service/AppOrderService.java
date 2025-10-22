package com.weikbest.pro.saas.applet.order.service;


import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.weikbest.pro.saas.applet.order.module.dto.AppOrderInfoDTO;
import com.weikbest.pro.saas.applet.order.module.qo.AppOrderInfoListQO;
import com.weikbest.pro.saas.applet.order.module.vo.AppOrderDetailVO;
import com.weikbest.pro.saas.applet.order.module.vo.AppOrderLogisticsVO;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.module.dto.AppOrderInfoListDTO;

import java.util.List;
import java.util.Map;


public interface AppOrderService {

    /**
     * 下单
     *
     * @return
     */
    String doOrder(AppOrderInfoDTO appOrderInfoDTO);

    /**
     * 获取我的订单列表
     *
     * @param appOrderInfoListQO
     * @param pageReq
     * @return
     */
    List<AppOrderInfoListDTO> queryPageAppOrderInfo(AppOrderInfoListQO appOrderInfoListQO, PageReq pageReq);

    /**
     * 获取订单详情
     *
     * @param number
     * @return
     */
    AppOrderDetailVO findOrderInfo(String number);

    /**
     * 订单支付
     *
     * @param number
     * @return
     */
    JSONObject placeOrder(String number) throws WxPayException;

    /**
     * 订单合单支付
     * @param number 主单号
     * @return
     */
    JSONObject placeCombineOrder(String number);

    Boolean changeOrderStatus(String number, String orderStatus);

    /**
     * 支付回调业务处理方法
     * @param ciphertext
     * @param reqParams
     * @return
     */
    Boolean wxPayNotifyUrl(String ciphertext, String reqParams);

    /**
     * 合单支付回调业务处理方法
     * @param ciphertext
     * @param reqParams
     */
    void wxCombinePayNotifyUrl(String ciphertext, String reqParams);

    Boolean delOrderInfo(String number);

    /**
     * 保存小程序订阅消息
     * @param number
     */
    void saveOrderInfoMessage(String number,String deliverNotify,String waitPayNotify);

    /**
     * 获取我的订单数
     */
    List<Map<String,Object>> queryCountOrderByStatus();

    /**
     * 根据订单ID查询订单物流记录
     * @param id
     * @return
     */
    AppOrderLogisticsVO queryOrderLogistics(Long id);

    /**
     * 到付下单
     * @param appOrderInfoDTO
     * @return
     */
    String doorderByCashOnDelivery(AppOrderInfoDTO appOrderInfoDTO);

    /**
     * 验证短信验证码
     * @param ip
     * @param phone
     * @param code
     */
    void checkVerifyCode(String ip,String phone, String code);

    /**
     * 批量下单
     * @param appOrderInfoDTOList
     * @return
     */
    String batchOrder(List<AppOrderInfoDTO> appOrderInfoDTOList);

    /**
     * 根据主单查询订单
     * @param mainNumber 主单号
     * @return
     */
    List<String> findOrderByMainNumber(String mainNumber);
}
