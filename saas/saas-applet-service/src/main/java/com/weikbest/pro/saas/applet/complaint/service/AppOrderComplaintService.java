package com.weikbest.pro.saas.applet.complaint.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.applet.complaint.module.dto.AppOrderComplaintDTO;
import com.weikbest.pro.saas.applet.complaint.module.vo.AppOrderComplaintVO;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaint;

import java.util.List;

/**
 * <p>
 * 订单投诉表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-29
 */
public interface AppOrderComplaintService extends IService<OrderComplaint> {

    /**
     * 新增数据
     *
     * @param appOrderComplaintDTO appOrderComplaintDTO
     * @return 新增结果
     */
    boolean insert(AppOrderComplaintDTO appOrderComplaintDTO);


    /**
     * 用户撤销
     * @param orderComplaintId
     * @return
     */
    boolean userUndo(Long orderComplaintId);

    /**
     * 用户是否认可
     * @param orderComplaintId
     * @return
     */
    boolean userDo(Long orderComplaintId,String complaintCustStatus);


    /**
     * 分页查询投诉数据
     * @return
     */
    List<AppOrderComplaintVO> queryListPage(PageReq pageReq);

}
