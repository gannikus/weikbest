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
import com.weikbest.pro.saas.merchat.order.entity.OrderReceiveRecord;
import com.weikbest.pro.saas.merchat.order.mapper.OrderReceiveRecordMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderReceiveRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderReceiveRecordMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderReceiveRecordQO;
import com.weikbest.pro.saas.merchat.order.service.OrderReceiveRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单分账记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-06
 */
@Service
public class OrderReceiveRecordServiceImpl extends ServiceImpl<OrderReceiveRecordMapper, OrderReceiveRecord> implements OrderReceiveRecordService {

    @Override
    public boolean insert(OrderReceiveRecordDTO orderReceiveRecordDTO) {
        OrderReceiveRecord orderReceiveRecord = OrderReceiveRecordMapStruct.INSTANCE.converToEntity(orderReceiveRecordDTO);
        return this.save(orderReceiveRecord);
    }

    @Override
    public boolean updateById(Long id, OrderReceiveRecordDTO orderReceiveRecordDTO) {
        OrderReceiveRecord orderReceiveRecord = this.findById(id);
        OrderReceiveRecordMapStruct.INSTANCE.copyProperties(orderReceiveRecordDTO, orderReceiveRecord);
        orderReceiveRecord.setId(id);
        return this.updateById(orderReceiveRecord);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public OrderReceiveRecord findById(Long id) {
        OrderReceiveRecord orderReceiveRecord = this.getById(id);
        if (ObjectUtil.isNull(orderReceiveRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderReceiveRecord;
    }

    @Override
    public IPage<OrderReceiveRecord> queryPage(OrderReceiveRecordQO orderReceiveRecordQO, PageReq pageReq) {
        QueryWrapper<OrderReceiveRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderReceiveRecordQO.getNumber())) {
            wrapper.eq(OrderReceiveRecord.NUMBER, orderReceiveRecordQO.getNumber());
        }
        if (StrUtil.isNotBlank(orderReceiveRecordQO.getBusinessType())) {
            wrapper.eq(OrderReceiveRecord.BUSINESS_TYPE, orderReceiveRecordQO.getBusinessType());
        }
        if (StrUtil.isNotBlank(orderReceiveRecordQO.getOutTradeNo())) {
            wrapper.eq(OrderReceiveRecord.OUT_TRADE_NO, orderReceiveRecordQO.getOutTradeNo());
        }
        if (StrUtil.isNotBlank(orderReceiveRecordQO.getTransactionId())) {
            wrapper.eq(OrderReceiveRecord.TRANSACTION_ID, orderReceiveRecordQO.getTransactionId());
        }
        if (StrUtil.isNotBlank(orderReceiveRecordQO.getMchId())) {
            wrapper.eq(OrderReceiveRecord.MCH_ID, orderReceiveRecordQO.getMchId());
        }
        if (StrUtil.isNotBlank(orderReceiveRecordQO.getDetailId())) {
            wrapper.eq(OrderReceiveRecord.DETAIL_ID, orderReceiveRecordQO.getDetailId());
        }
        if (StrUtil.isNotBlank(orderReceiveRecordQO.getReceiveStates())) {
            wrapper.eq(OrderReceiveRecord.RECEIVE_STATES, orderReceiveRecordQO.getReceiveStates());
        }
        if (StrUtil.isNotBlank(orderReceiveRecordQO.getReceiveResult())) {
            wrapper.eq(OrderReceiveRecord.RECEIVE_RESULT, orderReceiveRecordQO.getReceiveResult());
        }
        if (StrUtil.isNotBlank(orderReceiveRecordQO.getReceiveFailReason())) {
            wrapper.eq(OrderReceiveRecord.RECEIVE_FAIL_REASON, orderReceiveRecordQO.getReceiveFailReason());
        }
        if (StrUtil.isNotBlank(orderReceiveRecordQO.getReceiveRecordStatus())) {
            wrapper.eq(OrderReceiveRecord.RECEIVE_RECORD_STATUS, orderReceiveRecordQO.getReceiveRecordStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<OrderReceiveRecord> queryByNumber(String number) {
        return this.list(new QueryWrapper<OrderReceiveRecord>().eq(OrderReceiveRecord.NUMBER, number));
    }
}
