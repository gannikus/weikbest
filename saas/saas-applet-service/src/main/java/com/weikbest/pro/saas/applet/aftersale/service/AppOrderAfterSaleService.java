package com.weikbest.pro.saas.applet.aftersale.service;


import com.weikbest.pro.saas.applet.aftersale.module.dto.AppOrderAfterLogisticsInfoDTO;
import com.weikbest.pro.saas.applet.aftersale.module.dto.AppOrderAfterSaleDTO;
import com.weikbest.pro.saas.applet.aftersale.module.qo.AppOrderAfterSaleQO;
import com.weikbest.pro.saas.applet.aftersale.module.vo.AppOrderAfterSaleDetailVO;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.AppOrderAfterSaleApplyDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.AppOrderAfterSaleListDTO;

import java.util.List;


public interface AppOrderAfterSaleService {

    /**
     * 售后申请
     *
     * @return
     */
    Long apply(AppOrderAfterSaleDTO appOrderAfterSaleDTO);

    /**
     * 修改售后
     *
     * @param appOrderAfterSaleDTO
     * @return
     */
    Long reapply(AppOrderAfterSaleDTO appOrderAfterSaleDTO);

    /**
     * 撤销申请
     *
     * @return
     */
    Boolean revoke(Long id);

    /**
     * 增加客户售后物流信息
     *
     * @param appOrderAfterLogisticsInfoDTO
     * @return
     */
    Boolean addlogistics(AppOrderAfterLogisticsInfoDTO appOrderAfterLogisticsInfoDTO);

    /**
     * 订单售后详情信息
     *
     * @param orderAfterId
     * @return
     */
    AppOrderAfterSaleDetailVO detail(Long orderAfterId);

    /**
     * 获取分页申请售后列表
     *
     * @param appOrderAfterSaleQO
     * @param pageReq
     * @return
     */
    List<AppOrderAfterSaleApplyDTO> queryPageAppOrderAfterApply(AppOrderAfterSaleQO appOrderAfterSaleQO, PageReq pageReq);

    /**
     * 获取分布我的售后申请列表
     *
     * @param appOrderAfterSaleQO
     * @param pageReq
     * @return
     */
    List<AppOrderAfterSaleListDTO> queryPageAppOrderAfterList(AppOrderAfterSaleQO appOrderAfterSaleQO, PageReq pageReq);

    /**
     * 售后单客户确认收货
     *
     * @param id
     * @return
     */
    Boolean confirmReceipt(Long id);


    /**
     * 根据订单ID查询是否存在售后单
     * @param id
     * @return
     */
    Long whetherOrderAfter(Long id);
}
