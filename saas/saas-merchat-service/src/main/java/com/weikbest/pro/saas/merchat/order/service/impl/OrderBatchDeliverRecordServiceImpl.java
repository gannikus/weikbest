package com.weikbest.pro.saas.merchat.order.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.order.entity.OrderBatchDeliverRecord;
import com.weikbest.pro.saas.merchat.order.mapper.OrderBatchDeliverRecordMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderBatchDeliverRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderBatchDeliverRecordMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderBatchDeliverRecordQO;
import com.weikbest.pro.saas.merchat.order.service.OrderBatchDeliverRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单批量发货记录拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Service
public class OrderBatchDeliverRecordServiceImpl extends ServiceImpl<OrderBatchDeliverRecordMapper, OrderBatchDeliverRecord> implements OrderBatchDeliverRecordService {

    @Override
    public boolean insert(OrderBatchDeliverRecordDTO orderBatchDeliverRecordDTO) {
        OrderBatchDeliverRecord orderBatchDeliverRecord = OrderBatchDeliverRecordMapStruct.INSTANCE.converToEntity(orderBatchDeliverRecordDTO);
        return this.save(orderBatchDeliverRecord);
    }

    @Override
    public boolean updateById(Long id, OrderBatchDeliverRecordDTO orderBatchDeliverRecordDTO) {
        OrderBatchDeliverRecord orderBatchDeliverRecord = this.findById(id);
        OrderBatchDeliverRecordMapStruct.INSTANCE.copyProperties(orderBatchDeliverRecordDTO, orderBatchDeliverRecord);
        orderBatchDeliverRecord.setId(id);
        return this.updateById(orderBatchDeliverRecord);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public OrderBatchDeliverRecord findById(Long id) {
        OrderBatchDeliverRecord orderBatchDeliverRecord = this.getById(id);
        if (ObjectUtil.isNull(orderBatchDeliverRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderBatchDeliverRecord;
    }

    @Override
    public IPage<OrderBatchDeliverRecord> queryPage(OrderBatchDeliverRecordQO orderBatchDeliverRecordQO, PageReq pageReq) {
        QueryWrapper<OrderBatchDeliverRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderBatchDeliverRecordQO.getOrderNumber())) {
            wrapper.eq(OrderBatchDeliverRecord.ORDER_NUMBER, orderBatchDeliverRecordQO.getOrderNumber());
        }
        if (StrUtil.isNotBlank(orderBatchDeliverRecordQO.getImportStatus())) {
            wrapper.eq(OrderBatchDeliverRecord.IMPORT_STATUS, orderBatchDeliverRecordQO.getImportStatus());
        }
        if (StrUtil.isNotBlank(orderBatchDeliverRecordQO.getLogisticsCompanyName())) {
            wrapper.eq(OrderBatchDeliverRecord.LOGISTICS_COMPANY_NAME, orderBatchDeliverRecordQO.getLogisticsCompanyName());
        }
        if (StrUtil.isNotBlank(orderBatchDeliverRecordQO.getCourierNumber())) {
            wrapper.eq(OrderBatchDeliverRecord.COURIER_NUMBER, orderBatchDeliverRecordQO.getCourierNumber());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
