package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.entity.OrderBatchDeliver;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderBatchDeliverDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderBatchDeliverQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderBatchDeliverListVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 订单批量发货记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-26
 */
public interface OrderBatchDeliverService extends IService<OrderBatchDeliver> {

    /**
     * 新增数据
     *
     * @param orderBatchDeliverDTO orderBatchDeliverDTO
     * @return 新增结果
     */
    boolean insert(OrderBatchDeliverDTO orderBatchDeliverDTO);

    /**
     * 更新数据
     *
     * @param id                   ID
     * @param orderBatchDeliverDTO orderBatchDeliverDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderBatchDeliverDTO orderBatchDeliverDTO);

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
    OrderBatchDeliver findById(Long id);

    /**
     * 分页查询
     *
     * @param orderBatchDeliverQO
     * @param pageReq
     * @return
     */
    IPage<OrderBatchDeliverListVO> queryPage(OrderBatchDeliverQO orderBatchDeliverQO, PageReq pageReq);

    /**
     * 批量发货
     *
     * @param businessId
     * @param shopId
     * @param excelFile
     * @return
     */
    Long batchOrderDeliverAndUpdateOrderStatus(Long businessId, Long shopId, MultipartFile excelFile);
}
