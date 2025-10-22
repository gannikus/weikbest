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
import com.weikbest.pro.saas.merchat.order.entity.OrderSourceScale;
import com.weikbest.pro.saas.merchat.order.mapper.OrderSourceScaleMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderSourceScaleDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderSourceScaleMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderSourceScaleQO;
import com.weikbest.pro.saas.merchat.order.service.OrderSourceScaleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单来源分账比例表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Service
public class OrderSourceScaleServiceImpl extends ServiceImpl<OrderSourceScaleMapper, OrderSourceScale> implements OrderSourceScaleService {

    @Override
    public boolean insert(OrderSourceScaleDTO orderSourceScaleDTO) {
        OrderSourceScale orderSourceScale = OrderSourceScaleMapStruct.INSTANCE.converToEntity(orderSourceScaleDTO);
        return this.save(orderSourceScale);
    }

    @Override
    public boolean updateById(Long id, OrderSourceScaleDTO orderSourceScaleDTO) {
        OrderSourceScale orderSourceScale = this.findById(id);
        OrderSourceScaleMapStruct.INSTANCE.copyProperties(orderSourceScaleDTO, orderSourceScale);
        orderSourceScale.setId(id);
        return this.updateById(orderSourceScale);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public OrderSourceScale findById(Long id) {
        OrderSourceScale orderSourceScale = this.getById(id);
        if (ObjectUtil.isNull(orderSourceScale)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderSourceScale;
    }

    @Override
    public IPage<OrderSourceScale> queryPage(OrderSourceScaleQO orderSourceScaleQO, PageReq pageReq) {
        QueryWrapper<OrderSourceScale> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderSourceScaleQO.getOrderSource())) {
            wrapper.eq(OrderSourceScale.ORDER_SOURCE, orderSourceScaleQO.getOrderSource());
        }
        if (StrUtil.isNotBlank(orderSourceScaleQO.getOrderPath())) {
            wrapper.eq(OrderSourceScale.ORDER_PATH, orderSourceScaleQO.getOrderPath());
        }
        if (StrUtil.isNotBlank(orderSourceScaleQO.getDataStatus())) {
            wrapper.eq(OrderSourceScale.DATA_STATUS, orderSourceScaleQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
