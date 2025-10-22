package com.weikbest.pro.saas.merchat.complaint.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaint;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintRecord;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintRecordDTO;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintRecordQO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintDetailRecordDetailVO;

import java.util.List;

/**
 * <p>
 * 订单投诉处理记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-05
 */
public interface OrderComplaintRecordService extends IService<OrderComplaintRecord> {

    /**
     * 新增数据
     *
     * @param orderComplaintRecordDTO orderComplaintRecordDTO
     * @return 新增结果
     */
    boolean insert(OrderComplaintRecordDTO orderComplaintRecordDTO);

    /**
     * 更新数据
     *
     * @param id                      ID
     * @param orderComplaintRecordDTO orderComplaintRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderComplaintRecordDTO orderComplaintRecordDTO);

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
    OrderComplaintRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param orderComplaintRecordQO
     * @param pageReq
     * @return
     */
    IPage<OrderComplaintRecord> queryPage(OrderComplaintRecordQO orderComplaintRecordQO, PageReq pageReq);

    /**
     * 新增投诉沟通记录
     *
     * @param orderComplaint
     * @return
     */
    Long insertByOrderComplaintRecord(OrderComplaint orderComplaint);

    /**
     * 根据投诉记录ID获取处理记录
     *
     * @param orderComplaintId
     * @return
     */
    List<OrderComplaintDetailRecordDetailVO> queryDetailVOByOrderComplaintId(Long orderComplaintId);


}
