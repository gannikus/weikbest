package com.weikbest.pro.saas.merchat.complaint.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImgHis;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintImgHisDTO;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintImgHisQO;

import java.util.List;

/**
 * <p>
 * 订单投诉图片拆分历史表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-05
 */
public interface OrderComplaintImgHisService extends IService<OrderComplaintImgHis> {

    /**
     * 新增数据
     *
     * @param orderComplaintImgHisDTO orderComplaintImgHisDTO
     * @return 新增结果
     */
    boolean insert(OrderComplaintImgHisDTO orderComplaintImgHisDTO);

    /**
     * 更新数据
     *
     * @param id                      ID
     * @param orderComplaintImgHisDTO orderComplaintImgHisDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderComplaintImgHisDTO orderComplaintImgHisDTO);

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
    OrderComplaintImgHis findById(Long id);

    /**
     * 分页查询
     *
     * @param orderComplaintImgHisQO
     * @param pageReq
     * @return
     */
    IPage<OrderComplaintImgHis> queryPage(OrderComplaintImgHisQO orderComplaintImgHisQO, PageReq pageReq);

    /**
     * 查询上传凭证信息
     *
     * @param historyId
     * @param imgType
     * @return
     */
    List<String> queryImgByHistoryIdAndImgType(Long historyId, String imgType);
}
