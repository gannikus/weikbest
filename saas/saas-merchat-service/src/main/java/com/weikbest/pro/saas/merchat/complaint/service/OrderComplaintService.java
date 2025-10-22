package com.weikbest.pro.saas.merchat.complaint.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintBusinessConfirmDTO;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaint;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintDTO;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintQO;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintWxQO;
import com.weikbest.pro.saas.merchat.complaint.module.resp.WxOrderComplaintResp;
import com.weikbest.pro.saas.merchat.complaint.module.vo.ComplaintBusiStatusGroupVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintDetailVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintPageVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单投诉表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
public interface OrderComplaintService extends IService<OrderComplaint> {

    /**
     * 新增数据
     *
     * @param orderComplaintDTO orderComplaintDTO
     * @return 新增结果
     */
    boolean insert(OrderComplaintDTO orderComplaintDTO);

    /**
     * 更新数据
     *
     * @param id                ID
     * @param orderComplaintDTO orderComplaintDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderComplaintDTO orderComplaintDTO);

    /**
     * 删除数据
     *
     * @param ids ID集合
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderComplaint findById(Long id);

    /**
     * 分页查询
     *
     * @param orderComplaintQO
     * @param pageReq
     * @return
     */
    IPage<OrderComplaintPageVO> queryPage(OrderComplaintQO orderComplaintQO, PageReq pageReq);

    /**
     * 查询商户店铺订单售后状态汇总
     *
     * @param businessId
     * @param shopId
     * @param complaintType
     * @return
     */
    List<ComplaintBusiStatusGroupVO> queryGroupComplaintBusiStatus(Long businessId, Long shopId, String complaintType);

    /**
     * 微信订单投诉分页查询
     *
     * @param orderComplaintWxQO
     * @param pageReq
     * @return
     */
    WxOrderComplaintResp queryPageWithWx(OrderComplaintWxQO orderComplaintWxQO, PageReq pageReq);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    OrderComplaintDetailVO findOrderComplaintDetailVOById(Long id);

    /**
     * 商家处理投诉
     *
     * @param id
     * @param orderComplaintBusinessConfirmDTO
     * @return
     */
    boolean businessExecuteComplaint(Long id, OrderComplaintBusinessConfirmDTO orderComplaintBusinessConfirmDTO);

    /**
     * 商家处理超时
     *
     * @param id
     */
    void businessExecuteTimeout(Long id);


    /**
     * 获取微信投诉订单未处理的数量
     * @param orderComplaintWxQO
     * @return
     */
    Map<String , Integer> getPageWithWxCount(OrderComplaintWxQO orderComplaintWxQO);
}
