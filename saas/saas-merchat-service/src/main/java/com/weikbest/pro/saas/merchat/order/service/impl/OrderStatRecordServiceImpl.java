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
import com.weikbest.pro.saas.merchat.order.entity.OrderStatRecord;
import com.weikbest.pro.saas.merchat.order.mapper.OrderStatRecordMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderStatRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderStatRecordMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderStatRecordQO;
import com.weikbest.pro.saas.merchat.order.service.OrderStatRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单状态变更记录表  服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class OrderStatRecordServiceImpl extends ServiceImpl<OrderStatRecordMapper, OrderStatRecord> implements OrderStatRecordService {

    @Override
    public boolean insert(OrderStatRecordDTO orderStatRecordDTO) {
        OrderStatRecord orderStatRecord = OrderStatRecordMapStruct.INSTANCE.converToEntity(orderStatRecordDTO);
        return this.save(orderStatRecord);
    }

    @Override
    public boolean updateById(Long id, OrderStatRecordDTO orderStatRecordDTO) {
        OrderStatRecord orderStatRecord = this.findById(id);
        OrderStatRecordMapStruct.INSTANCE.copyProperties(orderStatRecordDTO, orderStatRecord);
        orderStatRecord.setId(id);
        return this.updateById(orderStatRecord);
    }

    @Override
    public OrderStatRecord findById(Long id) {
        OrderStatRecord orderStatRecord = this.getById(id);
        if (ObjectUtil.isNull(orderStatRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderStatRecord;
    }

    @Override
    public IPage<OrderStatRecord> queryPage(OrderStatRecordQO orderStatRecordQO, PageReq pageReq) {
        QueryWrapper<OrderStatRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderStatRecordQO.getCurrentState())) {
            wrapper.eq(OrderStatRecord.CURRENT_STATE, orderStatRecordQO.getCurrentState());
        }
        if (StrUtil.isNotBlank(orderStatRecordQO.getChangeStatus())) {
            wrapper.eq(OrderStatRecord.CHANGE_STATUS, orderStatRecordQO.getChangeStatus());
        }
        if (StrUtil.isNotBlank(orderStatRecordQO.getChangerUser())) {
            wrapper.eq(OrderStatRecord.CHANGER_USER, orderStatRecordQO.getChangerUser());
        }
        if (StrUtil.isNotBlank(orderStatRecordQO.getDescription())) {
            wrapper.eq(OrderStatRecord.DESCRIPTION, orderStatRecordQO.getDescription());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
