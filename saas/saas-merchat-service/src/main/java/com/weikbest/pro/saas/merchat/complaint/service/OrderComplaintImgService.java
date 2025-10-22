package com.weikbest.pro.saas.merchat.complaint.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaintImg;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintImgDTO;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintImgQO;

import java.util.List;

/**
 * <p>
 * 订单投诉图片拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
public interface OrderComplaintImgService extends IService<OrderComplaintImg> {

    /**
     * 新增数据
     *
     * @param orderComplaintImgDTO orderComplaintImgDTO
     * @return 新增结果
     */
    boolean insert(OrderComplaintImgDTO orderComplaintImgDTO);

    /**
     * 更新数据
     *
     * @param id                   ID
     * @param orderComplaintImgDTO orderComplaintImgDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderComplaintImgDTO orderComplaintImgDTO);

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
    OrderComplaintImg findById(Long id);

    /**
     * 分页查询
     *
     * @param orderComplaintImgQO
     * @param pageReq
     * @return
     */
    IPage<OrderComplaintImg> queryPage(OrderComplaintImgQO orderComplaintImgQO, PageReq pageReq);
}
